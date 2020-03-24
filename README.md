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
  - mvn spring-boot:run // it uses maven spring plugin to build and execute
  - quick test: http://localhost:5000/test 
* Uses AWS SDK.  Primarily accessing DynamoDB

### AWS Info
* Using credentials at ~/.aws (Default Provider Chain)
  * https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html
  * https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/setup-credentials.html
* In Maven Run configuration, set the following environment variable for runner:
  `AWS_PROFILE=fourmeal-dynamo`

### Batch load data - NOT YET IN PLACE
`aws dynamodb batch-write-item --request-items file://ProductCatalog.json`

### Setting up remote repo
Had to add the following commands (https://docs.aws.amazon.com/codecommit/latest/userguide/setting-up-https-unixes.html)
git config --global credential.UseHttpPath true
git config --global credential.helper '!aws --profile fourmeal-dynamo codecommit credential-helper $@'
git remote add origin https://git-codecommit.us-west-1.amazonaws.com/v1/repos/fourmeal
git push --set-upstream origin master
(When prompted in a push, choose Deny, otherwise reference URL above to fix Keychain -- Thanks Mac!)

### Docker Notes
* Build Image - docker build -t fourmeal-app .
* Run image to test it - docker run -p 80:80 fourmeal-app
* Tag image - docker tag fourmeal-app 757095936153.dkr.ecr.us-west-1.amazonaws.com/fourmeal-repository
* Do an AWS logging via cli to ECR - aws ecr get-login --no-include-email --region us-west-1 --profile fourmeal-dynamo
  * Run output
* Push image - docker push 757095936153.dkr.ecr.us-west-1.amazonaws.com/fourmeal-repository

https://docs.aws.amazon.com/AmazonECR/latest/userguide/docker-basics.html#docker-basics-create-image
"repository": {
        "registryId": "757095936153",
        "repositoryName": "fourmeal-repository",
        "repositoryArn": "arn:aws:ecr:us-west-1:757095936153:repository/fourmeal-repository",
        "createdAt": 1564951406.0,
        "repositoryUri": "757095936153.dkr.ecr.us-west-1.amazonaws.com/fourmeal-repository"
    }
// Fourmeal 5000
 "repository": {
        "registryId": "757095936153",
        "repositoryName": "fourmeal-5000",
        "repositoryArn": "arn:aws:ecr:us-west-1:757095936153:repository/fourmeal-5000",
        "createdAt": 1565111530.0,
        "repositoryUri": "757095936153.dkr.ecr.us-west-1.amazonaws.com/fourmeal-5000"
    }
