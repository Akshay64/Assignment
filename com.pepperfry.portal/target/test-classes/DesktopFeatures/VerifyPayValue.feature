Feature: Feature to compare Pay Values

Scenario: To verify you pay value on cart and address are same
Given user browse to pepperfry live website and click on profile
When user login to site using email and password
And user search sofa which redirects to product list
And user clicks very first product from the list and product detail page gets open in new tab
And user selects 2 in the quantity dropdown and clicks on Buy Now button
And on cart page store the You Pay values
And user click on Place Order button then it will redirect to address page
Then store the You Pay value on address
And compare those with earlier stored You Pay value of cart
