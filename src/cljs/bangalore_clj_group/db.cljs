(ns bangalore-clj-group.db
  (:require [cljs-time.core :as t]
            [cljs-time.coerce :as c]
            [bangalore-clj-group.util :as u])
  (:require-macros [bangalore-clj-group.macros :refer [readfile]]))


(def articles
  [{:slug "may-june-2019-meetup"
    :title "May and June 2019 meetups"
    :summary "Will be updated soon!"
    :content (readfile "md/articles/meetup-may-jun-2019.md")
    :topics "Clojure | ClojureScript | Meetup"
    :date "6 6 2019"
    :author ""}
   {:slug "april-2019-meetup"
    :title "April 2019 meetup"
    :summary "Will be updated soon!"
    :content (readfile "md/articles/meetup-apr-2019.md")
    :topics "Clojure | ClojureScript | Meetup"
    :date "14 5 2019"
    :author ""}
   {:slug "march-2019-meetup"
    :title "March 2019 meetup"
    :summary "Will be updated soon!"
    :content (readfile "md/articles/meetup-mar-2019.md")
    :topics "Clojure | ClojureScript | Meetup"
    :date "7 5 2019"
    :author ""}
   {:slug "days-of-the-past"
    :title "Summary of the Meetups that happened before March 2019"
    :summary "Will be updated soon!"
    :content (readfile "md/articles/days-of-the-past.md")
    :topics "Clojure | ClojureScript | Meetup"
    :date "1 5 2019"
    :author ""}
   {:slug "hack-session-meetup-sep-2018"
    :title "The hack session during Sept 2018 meetup"
    :summary "Learn about what clojurians built during this hackathon."
    :content (readfile "md/articles/meetup-sep-2018.md")
    :topics "Clojure | ClojureScript | Hackathon"
    :date "15 9 2018"
    :author "Amarjeet"}
   {:slug "mocking-with-var-redefinition-considered-harmful"
    :title "Mocking with var redefinition considered harmful"
    :summary "Many Clojure developers tend to rely on redefining vars for mocking dependencies during testing. While the approach works for very simple cases, it breaks down as soon as the needs become complex. In this post I want to list several kinds of pitfalls with redefining vars for mocking."
    :content (readfile "md/articles/mocking-with-vars.md")
    :topics "Clojure"
    :date "6 12 2016"
    :author "Shantanu Kumar"}])


;; TODO: write fns to make sure that slug and title are unique


#_(def events
    [{:slug "may-2019-meetup"
      :title "May 2019 meetup"
      :content (readfile "md/events/may2019meetup.md")
      :venue "Quintype, Old Airport Road, Bangalore"
      :date "20 12 2018"}
     {:slug "dec-2019-meetup"
      :title "Dec 2018 meetup"
      :content (readfile "md/events/dec2018meetup.md")
      :venue "SAP Concur"
      :date "18 12 2018"}
     {:slug "june-2019-meetup"
      :title "June 2019 meetup"
      :content (readfile "md/events/june2019meetup.md")
      :venue "Go-Jek Tech, 4th Floor, Tower B, Diamond District, HAL Old Airport Road, Domlur, Bengaluru, Karnataka 560008"
      :date "17 12 2019"}])


(defn sorted
  [arts]
  (reverse (sort-by #(c/to-long (:datetime (u/date-format (:date %)))) arts)))


(defn contribute [] (readfile "md/contribute/contribute.md"))
(defn learn [] (readfile "md/learn/clojure.md"))
(defn why-clojure [] (readfile "md/learn/why-clojure.md"))
(defn about [] (readfile "md/about/about.md"))


(def venue-hosts
  [{:name "Quintype"
    :web "https://www.quintype.com/"}
   {:name "SAP Concur"
    :web "https://www.concur.co.in/"}
   {:name "Nilenso"
    :web "https://nilenso.com/"}
   {:name "Go-Jek Tech"
    :web "https://www.gojek.io/"}])


(def jobs
  [{:title "Clojure Developer, FormCept, Bangalore"
    :web "https://www.formcept.com/careers/"}
   {:title "Clojure Developer, WebEngage, Bangalore & Mumbai"
    :web "https://webengage.com/current-openings/#op-197822-clojure-programmer"}])


;; Hardcoded previous event attendees count


;; Main DB

(def main-db
  {:articles (sorted articles)
   ;:events (sorted events)
   :contribute (contribute)
   :learn (learn)
   :why-clojure (why-clojure)
   :about (about)
   :venue-hosts venue-hosts
   :jobs jobs})

