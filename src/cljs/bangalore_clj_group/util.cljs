(ns bangalore-clj-group.util
  (:require [cljs-time.core :as t]
            [cljs-time.coerce :as c]
            [cljs.reader :as r]
            [clojure.string :as st]))


(def month-index
  {"1" "Jan" "2" "Feb" "3" "Mar" "4" "Apr" "5" "May" "6" "June"
   "7" "Jul" "8" "Aug" "9" "Sep" "10" "Oct" "11" "Nov" "12" "Dec"})



(defn date-format
  [ddmmyyyy-string]
  (let [ds (clojure.string/split ddmmyyyy-string #" ")
        d (first ds)
        m (month-index (second ds))
        y (last ds)]
    {:day (r/read-string d)
     :month (r/read-string (second ds))
     :year (r/read-string y)
     :date (str m " " d ", " y)
     :datetime (t/date-time (r/read-string y) (r/read-string (second ds)) (r/read-string d))
     :future-event? (t/after? (t/date-time (r/read-string y) (r/read-string (second ds)) (r/read-string d))
                              (t/now))}))


(defn name-email?
  [n e]
  (and (> (count (st/trim n)) 0) (> (count (st/trim e)) 0)))


;; The following two fns are taken directly from:
;; https://github.com/reagent-project/reagent-utils/blob/master/src/reagent/validation.cljs
;; It will be replaced with 'clova' and web-url validation will also be included

(defn matches-regex?
  "Returns true if the string matches the given regular expression"
  [v regex]
  (boolean (re-matches regex v)))


(defn is-email?
  "Returns true if v is an email address"
  [v]
  (if (nil? v)
    false
    (matches-regex? v #"(?i)[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")))
