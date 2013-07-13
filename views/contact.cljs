(ns resume.contact)

(def view
  [record]
  (let [{:keys [name email location phone github]} record]
    [:div {:class "contact"}
     [:p {:id "name"} name]
     [:p {:id "email"} email]
     [:p {:id "location"} location]
     [:p {:id "phone"} phone]
     [:p {:id "github"} github]])) 
