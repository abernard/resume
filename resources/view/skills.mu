[:div {:class "skills"}
 [:h3 "Job Skills"]
 {{#.}}
  [:span {:class "skill-entry"}
   [:p {:class "skill"} "{{skill}}"]
   [:p {:class "skill-description"} "{{description}}"]]
 {{/.}}
]

