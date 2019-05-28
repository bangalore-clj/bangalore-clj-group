(ns bangalore-clj-group.subs
  (:require
    [re-frame.core :as re-frame]))




(re-frame/reg-sub
  ::articles
  (fn [db _]
    (:articles (:db db))))


(re-frame/reg-sub
  ::current-article
  (fn [db _]
    (:current-article  db)))


(re-frame/reg-sub
  ::events
  (fn [db _]
    (:events (:db db))))


(re-frame/reg-sub
  ::current-event
  (fn [db _]
    (:current-event db)))


(re-frame/reg-sub
  ::contribute
  (fn [db _]
    (:contribute (:db db))))


(re-frame/reg-sub
  ::learn
  (fn [db _]
    (:learn (:db db))))


(re-frame/reg-sub
  ::about
  (fn [db _]
    (:about (:db db))))


(re-frame/reg-sub
  ::user
  (fn [db _] (:user db)))


(re-frame/reg-sub
  ::newly-added-member
  (fn [db _] (:member-added db)))

(re-frame/reg-sub
  ::newly-added-attendee
  (fn [db _] (:attendee-added db)))


