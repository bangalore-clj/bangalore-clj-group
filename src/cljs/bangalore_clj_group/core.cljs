(ns bangalore-clj-group.core
  (:require
    [reagent.core :as reagent]
    [re-frame.core :as re-frame]
    [bangalore-clj-group.events :as events]
    [bangalore-clj-group.views :as v]
    [bangalore-clj-group.subs :as subs]
    [bangalore-clj-group.config :as config]
    [reitit.frontend :as rf]
    [reitit.frontend.easy :as rfe]
    [reitit.coercion.spec :as rss]
    [com.degel.re-frame-firebase :as firebase]
    [config.firebase :as fb]))




(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))


(defonce firebase-config
  (reagent/atom nil))


#_(defn init-fb-config []
    (reset! firebase-config
            {:apiKey (System/getenv "FIREBASE_API_KEY")
             :authDomain (System/getenv "FIREBASE_AUTH_DOMAIN")
             :databaseURL (System/getenv "FIREBASE_DATABASE_URL")
             :storageBucket (System/getenv "FIREBASE_STORAGE_BUCKET")
             :projectId (System/getenv "FIREBASE_PROJECT_ID")
             :appId (System/getenv "FIREBASE_APP_ID")}))



(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (rfe/start!
    (rf/router v/routes {:data {:coercion rss/coercion}})
    (fn [m] (reset! v/match m))
    ;; set to false to enable HistoryAPI
    {:use-fragment true})
  (reagent/render [v/current-page]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  ;(init-fb-config) ; uncomment this before deploying to Heroku
  (firebase/init :firebase-app-info      fb/firebase-config ; @firebase-config
                 ; See: https://firebase.google.com/docs/reference/js/firebase.firestore.Settings
                 :firestore-settings     {:timestampsInSnapshots true}
                 :get-user-sub           [::subs/user]
                 :set-user-event         [::events/set-user])
                 ;:default-error-handler  [:firebase-error])
  (mount-root))
