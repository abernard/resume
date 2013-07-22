[:div {:class "contact"}
 [:div {:class "name-div"}
  [:h1 {:id "name"} "{{name}}"]
  [:div {:class "objective"} "{{objective}}"]]
 [:div {:class "contact-info"}
  [:p {:id "email"} [:a {:href "mailto:{{email}}"} "{{email}}"]]
  [:p {:id "location"} "{{location}}"]
  [:p {:id "phone"} "{{phone}}"]
  [:a {:id "github"
   :href "http://github.com/{{github}}"}
   [:p {:class "icon-github"}] GitHub]]
  ]

