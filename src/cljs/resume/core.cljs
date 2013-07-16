(ns resume.core
  (:require [cljs.reader :as reader]
            [resume.cheerio :as q]
            [hiccups.runtime :as edn])
  (:use [resume.util :only [slurp puts invoke]]
        [cljs.nodejs :only [require]]))

(def mu (require "mustache"))
(def express (require "express"))

(defn- load-skeleton
  "get empty skeleton as html"
  [filename]
  (let [str (slurp filename)
        edn (into [] (apply concat (reader/read-string str)))
        html (edn/render-html edn)]
    (q/load html)))

(defn- get-ids
  "retrieve list of content ids from html skeleton"
  [skeleton]
  (-> skeleton
    (q/select "div#resume")
    .children
    (.map (fn [i el] (.attr (q/select el) "id")))))

(defn- process-view
  "process edn->hiccup view transformer and render as html"
  [id data view]
  (let [filled (.render mu view (clj->js data))
        hiccup (reader/read-string filled)]
    (edn/render-html hiccup)))

(defn- safe-load
  [filename]
  (try
    (slurp filename)
    (catch js/Object e "")))

(defn- load-contents
  "fill in skeleton with generated html partials"
  [skeleton data-dir view-dir ]
  (let [ids (get-ids skeleton)]
    (doseq [id ids]
      (let [text (safe-load (str data-dir "/" id ".edn")) 
            data (reader/read-string text)
            view (safe-load (str view-dir "/" id ".mu")) 
            html (process-view id data view)]
        (-> skeleton 
          (q/select (str "div#" id))
          (.replaceWith html))))        
    (.html skeleton))) ; skeleton is now filled

(def ^:private resume
  (let [config (reader/read-string (slurp "config.edn"))
        {:keys [skeleton-file data-prefix view-prefix]} config
        skeleton (load-skeleton skeleton-file)]
    (load-contents skeleton data-prefix view-prefix)))

(defn- routes
  [app html]
  (.get app "/" (fn [req res]
                  (.setHeader res "Content-Type" "text/html")
                  (.setHeader res "Content-Length" (.-length html))
                  (.end res html)
                  )))
(defn- serve
  [html]
  (let [app (express)
        port 5000]
    (doto app
      (.use (.bodyParser express))
      (.use (.cookieParser express))
      (.use (.compress express))
      (.use (.methodOverride express))
      (.use (.-router app))
      (routes html))
    (.listen app port)))

(defn -main
  [& args]
  (serve resume))

(set! *main-cli-fn* -main)

