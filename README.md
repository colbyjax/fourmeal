# TODO
- Add Unit Tests


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
* Two places where dynamo is configured:
  - FourMealService - Ensure `Regions.US_EAST_2` is set to correct region
  - domain/Meal - This is where the table itself is mapped - Ensure table name correct **CASE_SENSITIVE**
 
            

## Dev Notes
* Spring Boot Application
* Maven used to build, run (pom.xml)
  - mvn spring-boot:run // it uses maven spring plugin to build and execute
  - quick test: http://localhost:5000/ping
* Uses AWS SDK.  Primarily accessing DynamoDB
* To ssh bounce from public host (bastion) to private subnet must have ssh forwarding:
https://developer.github.com/v3/guides/using-ssh-agent-forwarding/
* Can use `scp` to copy from public to private host without any special routing

### AWS Info
* Using credentials at ~/.aws (Default Provider Chain)
  * https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html
  * https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/setup-credentials.html
* In Maven Run configuration, set the following environment variable for runner:
  `AWS_PROFILE=fourmeal-dynamo`

### Batch load data 
`aws dynamodb batch-write-item --request-items file://seed-dynamo.json`

### Setting up remote repo
Had to add the following commands (https://docs.aws.amazon.com/codecommit/latest/userguide/setting-up-https-unixes.html)
git config --global credential.UseHttpPath true
git config --global credential.helper '!aws --profile fourmeal-dynamo codecommit credential-helper $@'
git remote add origin https://git-codecommit.us-west-1.amazonaws.com/v1/repos/fourmeal
git push --set-upstream origin master
(When prompted in a push, choose Deny, otherwise reference URL above to fix Keychain -- Thanks Mac!)

### How deploy Fargate instance
* Need to ensure you have a clean maven build and package along with a new docker image
```
//Clean and package app
mvn clean
mvn package // should create a jar in target folder -- verify date
mvn spring-boot:run

//Cleanup docker containers
docker rm <container id>
docker images //lists images -- remove

//Build docker image
docker build -t colbyjax/fourmeal-app .
docker login
docker push colbyjax/fourmeal-app


```
### Docker Notes
* List containers `docker ps -a`
* List images `docker images`
* Build Image - `docker build -t colbyjax/fourmeal-app .`
* Run image to test it - docker run -p 5000:5000 --name fourmeal -d colbyjax/fourmeal-app
  - curl http://localhost:5000/ping -- Should return with pong
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
