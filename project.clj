(defproject resume "0.1.0"
            :descripton "Resume generator"
            :url "http://zig.io"
            :plugins [[lein-cljsbuild "0.3.1"]
                      [lein-npm "0.1.0"]]
            :dependencies [[org.clojure/clojure "1.5.1"]
                           [hiccups "0.3.0"]]
            :node-dependencies [[cheerio "0.18.0"]
                                [express "4.11.2"]
                                [mustache "1.0.0"]
                                [bootstrap "3.3.2"]
                                [pdfkit "0.7.0"]
                                [less-middleware "1.0.4"]]
            :profiles {:dev {:cljsbuild {:builds [{:id "development"
                                                   :source-paths ["src/cljs"]
                                                   :compiler {
                                                              :target :nodejs
                                                              :pretty-print true
                                                              :optimizations :simple
                                                              :output-to "target/development/resume.js"
                                                              }}]}}})

