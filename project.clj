(defproject resume "0.0.1"
            :descripton "Resume generator"
            :url "http://zig.io"
            :plugins [[lein-cljsbuild "0.3.1"]
                      [lein-npm "0.1.0"]]
            :dependencies [[org.clojure/clojure "1.5.1"]
                           [hiccups "0.2.0"]]
            :node-dependencies [[cheerio "0.12.0"]
                                [express "3.3.4"]
                                [mustache "0.7.2"]]
            :profiles {:dev {:cljsbuild {:builds [{:id "development"
                                                   :source-paths ["src/cljs" "views"]
                                                   :compiler {
                                                              :target :nodejs
                                                              :pretty-print true
                                                              :optimizations :simple
                                                              :output-to "target/development/resume.js"
                                                              }}]}}})

