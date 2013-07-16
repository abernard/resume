(ns resume.util)

(def ^:private require (js* "require"))
(def ^:private fs (require "fs"))

(defn ^:export slurp
  [filename]
  (str (.readFileSync fs filename)))

(defn ^:export puts
  [o]
  (console/log (str o)))

(defn ^:export invoke
  [fn-str & args]
  (apply (js/eval fn-str) args))

