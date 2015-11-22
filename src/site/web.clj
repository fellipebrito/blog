(ns site.web
  (:require [compojure.core :refer :all];; routing library
            [compojure.route :refer [resources]]
            [ring.middleware.defaults :refer :all];; HTTP server abstraction
            [org.httpkit.server :refer [run-server]] ; httpkit is a server
            [selmer.parser :refer [render-file]] ;; templates
            [markdown.core :as md];; markdown
            [clojure.java.io :as io]
            [clojure.string :as str]))

(defn parse-post-metadata [file]
  (md/md-to-html-string-with-meta (-> (str "posts/" file ".md") io/resource slurp)))


;; take all but the first one (the directory)
(def all-posts
  (let [directory (clojure.java.io/file "resources/posts/")
        files (for [file (file-seq directory)] (first (str/split (.getName file) #".md")))]
    (take 10 (drop 1 files))))

;; ROUTES
(defn home [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body    (render-file "templates/index.html" {:posts all-posts})})


(defn post [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body    (render-file "templates/post.html"
                         (parse-post-metadata (get (:params req) :slug)))})

(defroutes app
  (GET "/" [] home)
  (GET "/post/:slug" [slug] post)
  (resources "/"))
