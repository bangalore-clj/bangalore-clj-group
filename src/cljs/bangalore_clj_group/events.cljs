(ns bangalore-clj-group.events
  (:require
    [re-frame.core :as re-frame]
    [bangalore-clj-group.db :as db]))



(re-frame/reg-event-db
 ::initialize-db
 (fn [db _]
   (assoc db :articles db/articles :active-panel :home)))

(re-frame/reg-event-db
 ::set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))


(re-frame/reg-event-db
 ::current-article
 (fn [db [_ article]]
   (assoc db :current-article article)))
