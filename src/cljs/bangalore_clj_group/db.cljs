(ns bangalore-clj-group.db
  (:require [cljs-time.core :as t]
            [cljs-time.coerce :as c]
            [bangalore-clj-group.util :as u])
  (:require-macros [bangalore-clj-group.macros :refer [readfile]]))


(def articles
  [{:slug "this-is-article-one"
    :title "This is article 1, This is article 1, This is article 1"
    :content (readfile "md/articles/articleone.md")
    :topics "Clojure | ClojureScript"
    :date "20 12 2018"}
   {:slug "this-is-article-two"
    :title "This is article 2, This is article 2, This is article 2"
    :content (readfile "md/articles/art2.md")
    :topics "Programming | Logic Programming"
    :date "18 12 2018"}
   {:slug "this-is-article-myfile"
    :title "This is myfile, This is myfile, This is myfile"
    :content (readfile "md/articles/test-file.md")
    :topics "Clojure | ClojureScript | Programming | Logic Programming"
    :date "17 12 2019"}
   {:slug "this-is-another-article"
    :title "This is another-article, This is another-article, This is another-article"
    :content (readfile "md/articles/test-file.md")
    :topics "Clojure | ClojureScript | Programming | Logic Programming"
    :date "18 12 2019"}
   {:slug "hack-session-meetup-sep-2018"
    :title "The hack session during Sept 2018 meetup"
    :content (readfile "md/articles/meetup-sep-2018.md")
    :topics "Clojure | ClojureScript | Hackathon"
    :date "18 9 2018"}])

;; TODO: write fns to make sure that slug and title are unique


(def events
  [{:slug "may-2019-meetup"
    :title "May 2019 meetup"
    :content (readfile "md/events/event1.md")
    :venue "Clojure | ClojureScript"
    :date "20 12 2018"}
   {:slug "dec-2019-meetup"
    :title "Dec 2018 meetup"
    :content (readfile "md/events/event2.md")
    :venue "Programming | Logic Programming"
    :date "18 12 2018"}
   {:slug "june-2019-meetup"
    :title "June 2019 meetup"
    :content (readfile "md/events/event3.md")
    :venue "Clojure | ClojureScript | Programming | Logic Programming"
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
   :events (sorted events)
   :contribute (contribute)
   :learn (learn)
   :why-clojure (why-clojure)
   :about (about)
   :venue-hosts venue-hosts
   :jobs jobs})

