(defproject site "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main site.system
  :dependencies [[org.clojure/clojure "1.7.0"]
                 ;; HTTP
                 [compojure "1.1.8"]
                 [http-kit "2.1.16"]
                 [ring/ring-defaults "0.1.1"]
                 [com.stuartsierra/component "0.2.3"]
                 [reloaded.repl "0.1.0"]
                 ;; TEMPLATE
                 [selmer "0.9.5"]
                 [markdown-clj "0.9.82"]]
  :profiles {:dev {:plugins []
                   :dependencies []
                   :source-paths ["dev"]}})
