
#### Deploy Elastic Beanstalk
aws cloudformation deploy --template-file ~/Dropbox/dev/cloudformation/rest-api-stack-set/templates/elastic-beanstalk.yml --stack-name fourmealEBStack --region us-east-2 --profile fourmeal-dynamo