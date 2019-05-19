(ns bangalore-clj-group.routes
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require
    [secretary.core :as secretary]
    [goog.events :as gevents]
    [goog.history.EventType :as EventType]
    [re-frame.core :as re-frame]
    [bangalore-clj-group.events :as events]
    [bangalore-clj-group.db :as db]))


(defn hook-browser-navigation! []
  (doto (History.)
    (gevents/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn article-from-slug [slug]
  (some #(when (= slug (:slug %)) %) db/articles))

(defn app-routes []
  (secretary/set-config! :prefix "#")
  ;; --------------------
  ;; define routes here
  (defroute articles-list "/" []
    (re-frame/dispatch [::events/set-active-panel :home]))

  (defroute article-page "/article/:slug" [slug]
    (do
      (re-frame/dispatch [::events/set-active-panel :article])
      (re-frame/dispatch [::events/current-article (article-from-slug slug)])))

  (defroute about "/about" []
    (re-frame/dispatch [::events/set-active-panel :about]))

  (defroute hammock "/learn" []
    (re-frame/dispatch [::events/set-active-panel :hammock]))


  ;; --------------------
  (hook-browser-navigation!))

