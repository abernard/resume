(ns resume.core
  (:require [cljs.reader :as reader])
  (:use [resume.util :only [slurp puts]]
        [cljs.nodejs :only [require]]
        [dommy.core :only [html]]))

(def config (reader/read-string (slurp "config.edn")))

(defn- inject
  [dom imap]
  dom)

(defn- get-ids
  [skel]
  ["contact" "objective" "skills" "technologies" "experience" "education"])

(defn- load-skeleton
  [filename]
  (let [skel-str (slurp filename)] 
    (into [] (apply concat (reader/read-string skel-str)))))

(defn- load-partials
  [prefix ids]
  )

(def ^:private resume
  (let [{:keys [skeleton-file data-prefix]} config
        skeleton (load-skeleton skeleton-file) 
        partials (load-partials data-prefix (get-ids skeleton))]
    (inject skeleton partials)))

(defn- serve
  [html]
  (puts html))

(defn -main
  [& args]
  (serve (html resume))) 

(set! *main-cli-fn* -main)

