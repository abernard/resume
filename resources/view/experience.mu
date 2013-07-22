[:div {:class "experience"}
 [:h3 "Work & Educational Achievements"]
{{#.}}
[:div {:class "job"}
 [:span {:class "job-key"}
  [:p {:class "entity"} "{{{entity}}}"]
  [:p {:class "timeframe"} "{{begin}}-{{end}}"]]
 [:div {:class "projects"}
 {{#projects}}
  [:p {:class "project"} "{{{.}}}"]
 {{/projects}}
 ]]
{{/.}}
]

