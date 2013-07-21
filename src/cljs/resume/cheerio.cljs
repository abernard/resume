(ns resume.cheerio
  (:use [cljs.nodejs :only [require]]
        [resume.util :only [invoke puts]]))

(def $ (require "cheerio"))

(defn load
  [s]
  (.load $ s)) 

; this is necessarily hacky due to the way that
; cheerio is designed...
(defn select
  ([obj] (js* "resume.cheerio.$(~{obj})"))
  ([obj selector] (js* "~{obj}(~{selector})")))

