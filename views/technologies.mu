[:div {:class "technologies"}
{{#.}}
[:span {:class "technology"}
 [:p {:class "technology-key"} "{{tech}}"]
 [:span {:class "technology-entries"}
 {{#entries}}
  [:p "{{.}}"] 
 {{/entries}}
 ]
]
{{/.}}
]
