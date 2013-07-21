[:div {:class "technologies"}
 [:h3 "Technologies"]
{{#.}}
[:span {:class "technology"}
 [:p {:class "technology-key"} "{{tech}}"]
 [:span {:class "technology-entries"}
  [:ul {:class "tags"} {{#entries}}
  [:li "{{.}}"] 
 {{/entries}}
 ]]]
{{/.}}
]
