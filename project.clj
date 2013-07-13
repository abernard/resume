(defproject resume "0.0.1"
            :descripton "Resume generator"
            :url "http://zig.io"
            :plugins [[lein-cljsbuild "0.3.1"]
                      [lein-npm "0.1.0"]]
            :dependencies [[org.clojure/clojure "1.5.1"]
                           [prismatic/dommy "0.1.2-SNAPSHOT"]]
            :node-dependencies [[jsdom "0.7.0"]]
            :cljsbuild {:builds [{:id "development"
                                  :source-paths ["src/cljs"]
                                  :compiler {
                                             :target :nodejs
                                             :pretty-print true
                                             :optimizations :simple
                                             :output-to "target/development/resume.js"
                                             }}
                                 {:id "production"
                                  :source-paths ["src/cljs"]
                                  :compiler {
                                             :target :nodejs
                                             :pretty-print false 
                                             :optimizations :advanced
                                             :output-to "target/production/resume.js"
                                             }}]})

