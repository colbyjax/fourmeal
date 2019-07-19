# FourMeal API
Service designed to store meals along with their ingredients in an informal manner.  Tags are a first class citizen, 
essentially marking different items like meals, meal-items.

## Schema
**Meal - Primary Object**
* "id" - unique id for the meal
* "name" - friendly name of the meal
* "tags"[] - any number of tags can be added to mark for latter retrieval, sorting, filtering (kids, dinner, etc.)
* "extras"[] - extra things you need for this meal, but small and likely have (e.g. oregano, ketchup, parmesan cheese)
* "mealitem"[] -  similar to an ingredient, but more informal, you mark units as whatever you want 
             such as "box","pack", "jar", "oz"
  * attributes: "name", "quantity", "tags", "units"
 
            

## Dev Notes
* Spring Boot Application
* Maven used to build, run (pom.xml)
* Uses AWS SDK.  Primarily accessing DynamoDB

### AWS Info
* Using credentials at ~/.aws (Default Provider Chain)
  * https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html
  * https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/setup-credentials.html
* In Maven Run configuration, set the following environment variable for runner:
  `AWS_PROFILE=fourmeal-dynamo`
  