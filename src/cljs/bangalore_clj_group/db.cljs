(ns bangalore-clj-group.db
  (:require [cljs-time.core :as t]
            [cljs-time.coerce :as c])
  (:require-macros [bangalore-clj-group.macros :refer [readfile]]))



(def articles
  (reverse (sort-by #(:id %) ; will be done properly with timestamp later, using cljs-time
                    [{:id 3
                      :slug "this-is-article-one"
                      :title "This is article 1, This is article 1, This is article 1"
                      :content (readfile "md/articles/articleone.md")
                      :topics "Clojure | ClojureScript"
                      :date "Dec 20, 2018"}
                     {:id 2
                      :slug "this-is-article-two"
                      :title "This is article 2, This is article 2, This is article 2"
                      :content (readfile "md/articles/art2.md")
                      :topics "Programming | Logic Programming"
                      :date "Dec 18, 2018"}
                     {:id 1
                      :slug "this-is-test file"
                      :title "This is myfile, This is myfile, This is myfile"
                      :content (readfile "md/articles/test-file.md")
                      :topics "Clojure | ClojureScript | Programming | Logic Programming"
                      :date "Dec 17, 2018"}])))


(def about (readfile "md/about/about.md"))

(def learn (readfile "md/learn/clojure.md"))

