(ns resume.macros)

(defmacro at 
  [dom node fns]
  `(-> (~dom ~node)
     ~fns))

