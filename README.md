# inmar

1. Develop a REST API for representing Meta-Data with end points (GET, POST, PUT
,DELETE etc.). For example GET on /api/v1/location returns all location objects
a. /api/v1/location,
b. /api/v1/location/{location_id}/department
c. /api/v1/location/{location_id}/department/{department_id}/category
d. /api/v1/location/{location_id}/department/{department_id}/category/{category_id}/
subcategory
e. /api/v1/location/{location_id}/department/{department_id}/category/{category_id}/
subcategory/{subcategory_id}

2. Persist the data in your favorite DB - relational or non-relational (You are expected to
install, configure, populate the DB). You may feel free to qualify object
representations with additional attributes to enhance modeling (For e.g. Location
object attributes = locationid, location description)
3. Write a api endpoint that takes input meta-data and returns all the SKU rows in
the "Data" that matches with the input meta-data.
a. For example, for input meta-data (Perimeter, Bakery, Bakery Bread, Bagels)
, return the rows with SKUs 1 & 14 (The actual data given to you might
contain more rows)

4. Develop test scripts to verify the application functionality
5. Implement an authentication mechanism for the UI (username/password) and API
(Basic Auth) - Bonus points if you can demonstrate OpenIDConnect, JWT, OAuth2
6. Implement the Exception Handling for the above required Rest Apis in the springboot
framework Level.
7. Implement Data Initialisation for the below given data into Database. (Loading data into
db initially)

META DATA:
Location,Department,Category,SubCategory
Perimeter,Bakery,Bakery Bread,Bagels
Perimeter,Bakery,Bakery Bread,Baking or Breading Products

1. SKU Data

SKU,NAME,LOCATION,DEPARTMENT,CATEGORY,SUBCATEGORY
1,SKUDESC1,Perimeter,Bakery,Bakery Bread,Bagels
2,SKUDESC2,Perimeter,Deli and Foodservice,Self Service Deli Cold,Beverages
3,SKUDESC3,Perimeter,Floral,Bouquets and Cut Flowers,Bouquets and Cut Flowers
