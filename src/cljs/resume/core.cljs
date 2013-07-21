(ns resume.core
  (:require [cljs.reader :as reader]
            [resume.cheerio :as q]
            [hiccups.runtime :as edn])
  (:use [resume.util :only [slurp puts invoke]]
        [cljs.nodejs :only [require]]))

;;
;; node.js requires
;; 
(def os (require "os"))
(def mu (require "mustache"))
(def express (require "express"))
(defn less [opts] ((require "less-middleware") opts))
(def path (require "path"))


;;
;; program configuration map
;;
(def config 
  (let [base (reader/read-string (slurp "config.edn"))
        {:keys [resource-prefix static-prefix]} base]
    (into base {:data-path (str resource-prefix "/data")
                :skel-path (str resource-prefix "/skel")
                :view-path (str resource-prefix "/view")
                :style-path (str resource-prefix "/less")})))

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
    (q/select "[id]")
    (.map (fn [i el] (.attr (q/select el) "id")))))

;(.map (fn [i el] (.attr (q/select el) "id"))))

(defn- process-view
  "process edn->hiccup view transformer and render as html"
  [id data view]
  (let [filled (.render mu view (clj->js data))
        hiccup (reader/read-string filled)]
    (edn/render-html hiccup)))

(defn- safe-load
  "loads contents of file as a string or empty string on failure"
  [filename]
  (try
    (slurp filename)
    (catch js/Object e "")))

(defn- html5
  "appends html5 header on an html document"
  [html]
  (str "<!doctype html>" html))

(defn- load-contents
  "fill in skeleton with generated html partials"
  [skeleton]
  (let [ids (get-ids skeleton)
        {:keys [data-path view-path]} config]
    (doseq [id ids]
      (let [text (safe-load (str data-path "/" id ".edn")) 
            data (reader/read-string text)
            view (safe-load (str view-path "/" id ".mu")) 
            html (process-view id data view)]
        (-> skeleton 
          (q/select (str "div#" id))
          (.replaceWith html))))        
    (html5 (.html skeleton)))) ; skeleton is now filled

(defn- get-path
  [rel]
  (.join path js/__dirname rel))

(def ^:private resume
  (let [{:keys [skeleton-file skel-path]} config
        skeleton-qualified (str skel-path "/" skeleton-file)
        skeleton (load-skeleton skeleton-qualified)]
    (load-contents skeleton)))

(defn- routes
  [app html]
  (doto app
    (.get "/" (fn [req res]
                (.setHeader res "Content-Type" "text/html")
                (.setHeader res "Content-Length" (.-length html))
                (.end res html)
                ))))

; this hack gets around the 'static' keyword being used as
; a function call.  clojurescript chokes with normal syntax.
(defn- express-static
  [path]
  (invoke "resume.core.express.static" path))

(defn- less-opts
  [dest-dir]
  (clj->js {
            :src (:style-path config)
            :paths [(str (:bootstrap-path config) "/less")]
            :dest (str dest-dir "/css")
            :prefix "/css"
            :compress true
            :debug true
            }))

(defn- serve
  "configure express web server and serve html page"
  [html]
  (let [app (express)
        port (:www-port config)
        tmp-dir (str (.tmpdir os) "resume")
        opts (less-opts tmp-dir)]
    (doto app
      (.use (.favicon express))
      (.use (.compress express))
      (.use (.logger express))
      (.use (.-router app))
      (.use (less opts))
      (.use (express-static tmp-dir))
      (routes html))
    (.listen app port)))

(defn -main
  [& args]
  (serve resume))

(set! *main-cli-fn* -main)

