(ns bangalore-clj-group.events
  (:require
    [re-frame.core :as re-frame]
    [bangalore-clj-group.db :as bdb]))



(re-frame/reg-event-db
  ::initialize-db
  (fn [db _]
    (assoc db :db bdb/main-db)))


(re-frame/reg-event-db
  ::current-article
  (fn [db [_ article]]
    (assoc db :current-article article)))



(re-frame/reg-event-db
  ::current-event
  (fn [db [_ event]]
    (assoc db :current-event event)))


(re-frame/reg-event-db
 ::set-user
 (fn [db [_ user]]
   (assoc db :user user)))




(re-frame/reg-event-fx
  ::example-1-set
  (fn [_ [_ name email]] {:firestore/set {:path ["members" email]
                                          :data {:name name
                                                 :email email}}}))


