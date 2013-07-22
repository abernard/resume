[:div {:class "technologies"}
 [:h3 "Technological Expertise"]
{{#.}}
[:div {:class "technology"}
 [:p {:class "technology-key"} "{{tech}}"]
 [:div {:class "technology-entries"}
  [:ul {:class "tags"} {{#entries}}
  [:li "{{.}}"] 
 {{/entries}}
 ]]]
{{/.}}
]
