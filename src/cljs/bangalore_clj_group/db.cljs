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
    :date "18 12 2019"}])

;; TODO: write fns to make sure that slug and title are unique


(def events
  [{:slug "this-is-article-one"
    :title "This is EVENT 1, This is EVENT 1, This is EVENT 1"
    :content (readfile "md/events/event1.md")
    :venue "Clojure | ClojureScript"
    :date "20 12 2018"}
   {:slug "this-is-article-two"
    :title "This is EVENT 2, This is EVENT 2, This is EVENT 2"
    :content (readfile "md/events/event2.md")
    :venue "Programming | Logic Programming"
    :date "18 12 2018"}
   {:slug "this-is-article-myfile"
    :title "This is EVENT, This is EVENT, This is EVENT"
    :content (readfile "md/events/event3.md")
    :venue "Clojure | ClojureScript | Programming | Logic Programming"
    :date "17 12 2019"}])


(defn sorted
  [arts]
  (reverse (sort-by #(c/to-long (:datetime (u/date-format (:date %)))) arts)))


(defn contribute [] (readfile "md/contribute/contribute.md"))
(defn learn [] (readfile "md/learn/clojure.md"))
(defn about [] (readfile "md/about/about.md"))


;; Main DB

(def main-db
  {:articles (sorted articles)
   :events (sorted events)
   :contribute (contribute)
   :learn (learn)
   :about (about)})

