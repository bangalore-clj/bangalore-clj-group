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
  ::add-member
  (fn [_ [_ name email organizer? web-url]]
    {:firestore/set {:path ["members" email]
                     :data {:name name
                            :email email
                            :organizer organizer?
                            :web-profile web-url}}}))

(re-frame/reg-event-fx
  ::add-attendee
  (fn [_ [_ meetup name email web-url]]
    {:firestore/set {:path [meetup email]
                     :data {:name name
                            :email email
                            :web-profile web-url}}}))


