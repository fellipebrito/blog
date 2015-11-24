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
  (let [post (md/md-to-html-string-with-meta (str/replace (-> (str "posts/" file ".md") io/resource slurp) "---\n" ""))
        metadata (get post :metadata)
        html (get post :html)
        date (str/split file #"-")]
    {:html html
     :date (str (first date)"-"(second date)"-"(get date 2))
     :title (first (get metadata :title))
     :subtitle (first (get metadata :subtitle))
     :author (first (get metadata :author))
     :permalink (first (get metadata :permalink))
     :short (first (get metadata :short))}))

(def post-files-list
  (let [directory (clojure.java.io/file "resources/posts/")
        files (for [file (file-seq directory)] (first (str/split (.getName file) #".md")))]
    (reverse (remove #(= % "posts") (remove #(= % ".DS_Store") files)))))

(def all-posts
  (map parse-post-metadata post-files-list))

(defn find-post [permalink]
  (first (filter (fn [post] (= (str "/" permalink "/") (get post :permalink)))
          (for [post all-posts] post))))

(find-post "prayforhumanity")
;; ROUTES
(defn home [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body    (render-file "templates/index.html" {:posts all-posts})})

(defn view [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body    (render-file "templates/post.html" (find-post (get (:params req) :slug)))})

(defroutes app
  (GET "/" [] home)
  (GET "/:slug" {{slug :slug} :params} view)
  (GET "/:slug/" {{slug :slug} :params} view)
  (resources "/"))
