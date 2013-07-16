[:div {:class "experience"}
{{#.}}
[:div {:class "job"}
 [:p {:class "entity"} "{{entity}}"]
 [:p {:class "range"} "{{begin}}-{{end}}"]
 [:div {:class "projects"}
 {{#projects}}
  [:p {:class "project"} "{{.}}"]
 {{/projects}}
 ]]
{{/.}}
]

