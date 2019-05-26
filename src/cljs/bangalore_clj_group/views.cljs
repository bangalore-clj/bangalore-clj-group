(ns bangalore-clj-group.views
  (:require
    [re-frame.core :as re-frame]
    [bangalore-clj-group.subs :as subs]
    [bangalore-clj-group.events :as events]
    [markdown-to-hiccup.core :as m]
    [soda-ash.core :as sa]
    [reagent.core :as r]
    [reitit.core :as rt]
    [reitit.frontend :as rf]
    [reitit.frontend.easy :as rfe]
    [reitit.coercion :as rc]
    [reitit.coercion.spec :as rss]
    [spec-tools.data-spec :as ds]
    [bangalore-clj-group.util :as u]
    [com.degel.re-frame-firebase :as firebase]))


;; Features
; RSVP enable/disable based on the meetup date
; Count of RSVPed using G Forms/Sheet API
; Auto reminder email using G Forms/Sheet API, if possible

;; Sections
; - Home
; - About/ Team
; - Write
; - Contribute
; - Events
; - Learn
; - Supporters/ previous Hosts / Sponsorships
; - Subscribe



(defn art-date
  [date-string]
  (:date (u/date-format date-string)))


(defn render-md [md-content]
  (-> md-content
      (m/md->hiccup)
      (m/component)))




;; Header menu

(defn header-old []
  [:div
   [sa/Menu {:stackable true
             :color "teal"
             :inverted true
             :size "large"}
    [sa/Container
     [sa/MenuItem {}
      [sa/Image {:src "img/new-logo.png"
                 :size "mini"
                 :centered true
                 :circular true}]]
     [sa/MenuItem {:content "Bangalore Clojure"
                   :icon "home"
                   :active true
                   :onClick #(println "Good")}]
     [sa/MenuItem {:content "About"}]]]])


(defn header-1 []
  [:div.navbar
   [sa/Container
    [sa/Grid {:stackable true}
              ;:centered true}
     [sa/GridRow
      [sa/GridColumn {:textAlign "left"}
       [sa/Image {:src "img/new-logo.png"
                  :size "mini"
                  :circular true
                  :centered true}]]
      [sa/GridColumn {:textAlign "right"}
       [sa/Button {:content "Bangalore Clojure"
                   :basic true}]]]]]])


(defn header []
  [:div.navbar
   [sa/Container {:textAlign "left"}
     [sa/ButtonGroup
      [sa/Button {:content "Bangalore Clojure"
                  :basic true
                  :onClick #(rfe/push-state ::home)}]
      [sa/Button {:content "Home"
                  :basic true
                  :onClick #(rfe/push-state ::home)}]
      [sa/Button {:content "Events"
                  :basic true
                  :onClick #(rfe/push-state ::events)}]
      [sa/Button {:content "Learn"
                  :basic true
                  :onClick #(rfe/push-state ::learn)}]
      [sa/Button {:content "Contribute"
                  :basic true
                  :onClick #(rfe/push-state ::contribute)}]
      [sa/Button {:content "About"
                  :basic true
                  :onClick #(rfe/push-state ::about)}]]]])




;; Articles

(defn article-card [slug title content topics pub-date]
  [sa/Card {:header title
            :description"this is description"
            :extra topics
            :meta (art-date pub-date)
            :onClick #(do
                        (re-frame/dispatch [::events/current-article {:slug slug
                                                                      :title title
                                                                      :content content
                                                                      :topics topics
                                                                      :date pub-date}])
                        (rfe/push-state ::article))}])



(defn articles-list []
  (let [articles (re-frame/subscribe [::subs/articles])]
    [:div
     [sa/CardGroup {:stackable true
                    :itemsPerRow 1}
       (for [a @articles]
         ^{:key (:title a)}
         [article-card (:slug a) (:title a) (:content a) (:topics a) (:date a)])]]))



;; Article page

(defn article []
  (let [a (re-frame/subscribe [::subs/current-article])]
    [:div#content-body
     (render-md (:content @a))]))



;; Side bars

(defn email-subscription []
  [:div
   [sa/Button {:content "Subscribe!"
               :icon "mail"
               :size "large"
               :color "facebook"
               :fluid true
               :onClick #(.open js/window "https://forms.gle/EKmukUHe4mwQoDCKA")}]])


(defn learn-side-bar
  []
  [:div
   [sa/Button {:content "Hammock time!"
               :fluid true
               :size "big"
               :basic true
               :color "brown"
               :onClick #(do
                           (rfe/push-state ::learn))}]])

(defn upcoming-event
  []
  [:div#side-bar
   [:h4 "Upcoming Events"]
   [:a {:href ""} "December 2019 Meetup"]])


(defn venue-sponsors
  []
  [:div])


(defn side-bar
  []
  [:div
   [email-subscription]
   [:br]
   [upcoming-event]
   [:br]
   [venue-sponsors]
   [:br]
   [learn-side-bar]
   [:br]])




;; Home body

(defn home []
  [:div#main-body
   [sa/Grid {:stackable true}
    [sa/GridRow
     [sa/GridColumn {:width "12"}
      [articles-list]]
     [sa/GridColumn {:width "4"}
      [side-bar]]]]])

;; Events

(defn event-card [slug title content venue pub-date]
  [sa/Card {:header title
            :description"this is description"
            :extra (str "Venue: " venue)
            :meta (art-date pub-date)
            :onClick #(do
                        (re-frame/dispatch [::events/current-event {:slug slug
                                                                    :title title
                                                                    :content content
                                                                    :venue venue
                                                                    :date pub-date}])
                        (rfe/push-state ::event {:slug slug}))}])


(defn events-list []
  (let [es (re-frame/subscribe [::subs/events])]
    [:div
     [sa/CardGroup {:stackable true
                    :itemsPerRow 1}
       (for [a @es]
         ^{:key (:title a)}
         [event-card (:slug a) (:title a) (:content a) (:venue a) (:date a)])]]))



(defn events-page []
  [:div#main-body
   [sa/Grid {:stackable true}
    [sa/GridRow
     [sa/GridColumn {:width "12"}
      [events-list]]
     [sa/GridColumn {:width "4"}
      [side-bar]]]]])


(defn event []
  (let [e (re-frame/subscribe [::subs/current-event])]
    [:div#content-body
     (render-md (:content @e))]))

;; Events page TODOs
; RSVP form enabled/disabled
; RSVP count
; Members

(defn rsvp-form []
  [:div#side-bar
   [:h3 "RSVP to Attend"]
   [sa/Form
    [sa/FormInput {:placeholder "Name (required)"
                   :required true
                   :type "text"}]
    [sa/FormInput {:placeholder "Email (required)"
                   :required true
                   :type "email"}]
    [sa/FormInput {:placeholder "Web profile (optional)"
                   :type "url"}]
    [sa/FormButton {:content "RSVP"
                    :primary true}]]])


(defn attendees [m]
  (let [as (re-frame/subscribe [:firestore/on-snapshot {:path-collection [m]}])]
    [:div#side-bar
     [:h4 "Attendees"]
     [:p (count (:docs @as))]]))

(defn organizers []
  (let [as (re-frame/subscribe [:firestore/on-snapshot {:path-collection ["members"]
                                                        :where [[:organizer :== true]]}])]
    [:div#side-bar
     [:h4 "Organizers"]
     [:p (pr-str (:docs @as))]]))


(defn add-attendee-btn [m n e w]
  [:div
   [sa/Button {:content "Add"
               :onClick #(re-frame/dispatch [::events/add-attendee m n e w])}]])


(defn attendees-count []
  [:div
   [sa/Statistic {:color "blue"}
                  ;:horizontal true}
    [sa/StatisticValue {:content 30}]
    [sa/StatisticLabel {:content "attending"}]]])


(defn event-page-side-bar []
  [:div
   [attendees-count]
   [:br]
   [rsvp-form]
   [:br]
   [add-attendee-btn "may2019meetup" "Shantanu" "sh@gmail.com" ""]
   [:br]
   [attendees "may2019meetup"]
   [:br]
   [organizers]])


(defn event-page []
  [:div#main-body
   [sa/Grid {:stackable true}
    [sa/GridRow
     [sa/GridColumn {:width "12"}
      [event]]
     [sa/GridColumn {:width "4"}
      [event-page-side-bar]]]]])



;; Contribute

(defn contribute []
  (let [c (re-frame/subscribe [::subs/contribute])]
    [:div#content-body
     [:p "It should work"]
     (render-md @c)]))





;; About page

(defn visible-email
  [c]
  [sa/Button {:content "amarjeet001@gmail.com"
              :icon "mail"
              :color "google plus"
              :circular true
              :onClick #(reset! c "")}])

(defn hidden-email
  [c]
  [sa/Button {:icon "mail"
              :color "google plus"
              :circular true
              :size "big"
              :onClick #(reset! c "show")}])


(defn twitter []
  [:div
   [sa/Icon {:name "twitter"
             :size "large"
             :color "blue"
             :circular true
             :link true
             :inverted true
             :onClick #(.open js/window "https://twitter.com/amarjeet000")}]])

(defn github []
  [:div
   [sa/Icon {:name "github"
             :size "large"
             :color "black"
             :circular true
             :link true
             :inverted true
             :onClick #(.open js/window "https://github.com/amarjeet000")}]])


(defn about []
  (let [ab (re-frame/subscribe [::subs/about])]
    [:div#content-body
     [:p "Hmm"]
     (render-md @ab)]))

(defn about-old []
  (let [a (re-frame/subscribe [::subs/about])
        c (r/atom "")]
    (fn []
      [:div#content-body
       [:p "Hmm"]
       (render-md @a)
       [:br]
       [sa/Grid
        [sa/GridRow {:centered true}
         [sa/GridColumn {:textAlign "center"}
          (if (empty? @c) [hidden-email c] [visible-email c])]]]
       [:br]
       [:h3 "More online presence:"]
       [sa/Grid {:stackable true}
        [sa/GridRow
         [sa/GridColumn {:textAlign "center"}
          [twitter]]
         [sa/GridColumn {:textAlign "center"}
          [github]]]]])))


;; Learn

(defn learn []
  (let [h (re-frame/subscribe [::subs/learn])]
    [:div#content-body
     [:p "Is it working"]
     [:p "Is it working"]
     (render-md @h)]))


;; Routes
;; TODOs:
; Dynamic re-frame event-dispatch when routes gets loaded
; params in routes

(def routes
  [["/"
    {:name ::home
     :view home}]

   ["/article...slug"
    {:name ::article
     :view article}]

   ["/events"
    {:name ::events
     :view events-page}]

   ["/event/:slug"
    {:name ::event
     :view event-page
     :parameters {:path {:slug string?}}
     :controllers
     [{:params (fn [m] (:path (:parameters m))) ; WIP
       :start (fn [params]
                (rfe/push-state ::event {:slug (:slug params)}))
       :stop (fn [& params] (js/console.log "Leaving sub-page 2"))}]}]

   ["/contribute"
    {:name ::contribute
     :view contribute}]

   ["/learn"
    {:name ::learn
     :view learn}]

   ["/about"
    {:name ::about
     :view about
     :controllers
     [{:start (fn [& params] (do (rfe/push-state ::about) (js/console.log "Entering sub-page 2")))
       :stop (fn [& params] (js/console.log "Leaving sub-page 2"))}]}]])





;; Main

(defonce match (r/atom nil))

(defn current-page []
  [:div
   [header]
   [:br]
   [:br]
   [:br]
   [:br]
   (if @match
     (let [view (:view (:data @match))]
       [view @match]))])














