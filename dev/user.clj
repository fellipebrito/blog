(ns user
  (:require [reloaded.repl :refer [system reset stop]]
            [site.system]))

(reloaded.repl/set-init! #'site.system/create-system)

