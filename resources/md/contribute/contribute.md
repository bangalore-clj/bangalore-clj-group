# Contribute

[The code is hosted on GitHub](https://github.com/bangalore-clj/bangalore-clj-group). Its just ClojureScript. Go ahead and explore.

We are looking forward to your contribution, and we are keen to make this process as easy as we can. The contribution process is managed on GitHub using Pull Requests (PR).

There are broadly two ways to contribute:

1. Add content in different sections
2. Add or improve website features


## Adding content in different sections

Some types of content additions are simple. Adding content to `Jobs` and `Articles` are no brainers. Other sections may require discussion with the organizing team. In such a case, you can either open a GitHub issue or contact one of us via one of the ways mentioned on the `About` page.


### Adding or modifying an article

Please follow the following steps:

- Add your article's markdown (`.md`) file to `resources/md/artciles` directory.
- Add a map with the details of your article in the `articles` vector in the `src/cljs/bangalore_clj_group/db.cljs` file.
- Create a PR

If you have images or videos or other content to embed, look at the `Embedding images and videos in markdown files` section below.


### Adding or modifying a job

Please follow the following steps:

- Add a map with the details of your job in the `jobs` vector in the `src/cljs/bangalore_clj_group/db.cljs` file.
- Create a PR


### Embedding images, videos, or slideshare style presentation links in markdown files

This requires you to touch a bit of `html`. Below is what you do:

#### Image

Just replace the `src` value with your image source in the below code. Leave other props unchanged.

```
<img src="img/meetups/sep2018/sept-meetup-1.png" width="100%" height="100%" />
```

You can store your images inside the `resources/public/img` directory. And the path of your image file will just start with `img/.....png`. Make sure you have `png` files.


#### Videos or Slideshare style presentations

Just replace the value of `src` with the embed link that you get from sources such as YouTube or Slideshare. Leave other props unchanged.

```
<iframe src="//www.slideshare.net/slideshow/embed_code/key/FlfZAuRCLTgjOH" width="760px" height="570px"></iframe>
```


## Adding or improving a features of the website

The website is written in ClojureScript. Therefore, it's easy to contribute if you are a Clojure programmer. We have a development plan, but it is not set in stone. Our tentative development plan can be found in the form of [GitHub issues](https://github.com/bangalore-clj/bangalore-clj-group/issues). We have added labels to each issue to help you find the part that you can work on.

If you have some ideas that are not mentioned but you would like to work on, please create a GitHub issue and we will discuss.
