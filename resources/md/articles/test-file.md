# Random copied content

Please send a pull-request for's any $ & contribution. Below – are - different ways to contribute. Before sending PRs, please test the changes by running the application on your local machine:


## Random copied content


Please send a pull-request for's any $ & contribution. Below – are - different ways to contribute. Before sending PRs, please test the changes by running the application on your local machine:


### Random copied content

Please send a pull-request for's any $ & contribution. Below – are - different ways to contribute. Before sending PRs, please test the changes by running the application on your local machine:



```clojure
lein ring server
```

This website has been built using [Cryogen](http://cryogenweb.org/index.html). You can visit the detailed documentation at [Cryogen documentation](http://cryogenweb.org/docs/home.html) page in case you have any doubts. Or, you can email us on `bangaloreclojure@gmail.com`



![alt text](img/logo.png "Logo Title Text 1")


## Writing a post (article)

To add a post, please put your content inside a markdown (.md) file. Name that file as `<YYYY-MM-DD>-file-name.md`
Then, put this file in the `resources/templates/md/posts` directory.

You can add a settings map on top of the markdown (.md) file as below:

```clojure
  {:title "This is a demo post"
   :layout :post
   :tags  ["Clojure" "ClojureScript"]
   :author "your-name"}

```


```clojure
(defn me
  []
  (+ 2 3))
```


## Creating a page (ex: About, Contribute, etc.)

To add a page, please put your content inside a markdown (.md) file. Name that file as `file-name.md`

Then, put this file in the `resources/templates/md/pages` directory.

You can add a settings map on top of the MD file as below:
```clojure
{:title "Contribute"
 :layout :page
 :page-index 0
 :navbar? true}
```


## Making changes to the website (structure, looks, etc)

1. Base config can be modified at `resources/templates/config.edn`.

2. CSS and Layouts for the currently used `blue` theme can be modified at `resources/templates/themes/blue`.
