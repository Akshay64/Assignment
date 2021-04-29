Feature: Feature to test whether products are getting added to wishlist

Scenario: To verify products getting added to wishlist
Given user browse to pepperfry live website and click on profile
When user login to site using email and password
And hover on Furniture and click on Sofa Sets
And on redirected product listing page, click on first product
And it will open product in new tab/page, click on Add to wishlist and store the product name
And Go back to previous tab/page, look for second product in the list & click on Add to wishlist & store the product name
Then click on Wishlist on the top section
And Validate the product names in the Whislist
And Validate the count of the Wishlist and the number of products listed in the side panel