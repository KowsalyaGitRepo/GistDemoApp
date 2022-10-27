# GistDemoApp
Listing out hot gists
- Write an executable mobile application
- List Gists by querying a GitHub by API provided below, see resources [1] and [2]
- Show items according to response data in that list
- Each item should contain at least following information:
• ID
• URL
• File name (CSV)
• Favourite icon show and change state (locally)
By response data, extract user to make another query for other Gists count by that user. if
item count greater than 5, insert one item below the original one, showing user and the
count of that user shared. (do not show if the count is lower than five)
- When picking an item, will navigate to a detail page.
- Detail page have information from item picked.
- Detail page have an button for user to change favourite state.

RESOURCES
[1] Gists listing: GET https://api.github.com/gists/public?since
[2] Gists by user: GET https://api.github.com/users/{USER_NAME}/gists?since
