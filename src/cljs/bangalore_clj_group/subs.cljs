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
  ::members
  (fn [_ _]
    (re-frame/subscribe [:firestore/on-snapshot {:path-collection ["members"]}]))
  (fn [value _]
    (count (:docs value))))

