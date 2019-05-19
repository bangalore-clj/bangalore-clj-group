(ns bangalore-clj-group.subs
  (:require
    [re-frame.core :as re-frame]
    [bangalore-clj-group.db :as bdb]))


(re-frame/reg-sub
 ::active-panel
 (fn [db _]
   (:active-panel db)))


(re-frame/reg-sub
 ::articles
 (fn [db _]
   (:articles db)))


(re-frame/reg-sub
 ::current-article
 (fn [db _]
   (:current-article db)))


(re-frame/reg-sub
 ::about
 (fn [db _]
   cdb/about))

(re-frame/reg-sub
 ::learn
 (fn [db _]
   cdb/learn))

