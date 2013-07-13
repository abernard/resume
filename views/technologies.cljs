(ns resume.technologies)

(defn view
  [xs]
  (map #((let [{:keys [tech entries]} %]
           [:div {:class "technology"}
            [:div {:class "tech-key"} tech]
            [:div {:class "tech-entries"} entries]])) xs)) 
