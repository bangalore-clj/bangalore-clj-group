(ns bangalore-clj-group.views
  (:require
   [re-frame.core :as re-frame]
   [bangalore-clj-group.subs :as subs
    [coderafting-web.routes :as routes]
    [coderafting-web.events :as events]
    [markdown-to-hiccup.core :as m]
    [soda-ash.core :as sa]
    [reagent.core :as r]]))


;; WIP codes

;; Home

(defn home []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1 (str "Hello from " @name ". This is the Home Page.")]

     [:div
      [:a {:href "#/about"}
       "go to About Page"]]]))


;; Article

(defn article []
  [:div
   [:h1 "This is the Article Page."]

   [:div
    [:a {:href "#/"}
     "go to Home Page"]]])



;; Learn

(defn learn []
  [:div
   [:h1 "This is the Learn Page."]

   [:div
    [:a {:href "#/"}
     "go to Home Page"]]])



;; About

(defn about []
  [:div
   [:h1 "This is the About Page."]

   [:div
    [:a {:href "#/"}
     "go to Home Page"]]])



;; main

(def view-panels
  {:home [home]
   :article [article]
   :about [about]
   :hammock [learn]})

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [:div
     [logo]
     [menu @active-panel]
     [view-panels @active-panel]]))

