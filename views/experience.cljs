(ns resume.views)

(defn- entry-helper
  [entry]
  (let [{:keys [entity begin end projects]} entry]
    [:div {:class "job"}
     [:p {:class "entity"} entity]
     [:p {:class "begin"} begin]
     [:p {:class "end"} end]
     (into [:p {:class "projects"}]
           (map #([:p {:class "project"} %]) projects))]))

(defn experience 
  [xs]
  (map entry-helper xs))

