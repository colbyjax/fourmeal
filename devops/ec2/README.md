##Overview
This AWS methodology will provision EC2 instances to serve fourmeal API.  

### Quickstart
* Create Stack vpc-cloud-formation.yaml
* Create Key Pair for accessing EC2
  - Create Key Pair
  `ssh-keygen -t rsa -C "aws-api-key" -f ~/.ssh/aws-api-key`
  - Import key into EC2
  `aws ec2 import-key-pair --key-name "aws-api-key" --public-key-material fileb://./aws-api-key.pub`
  - Add to your ssh so you can ssh forward the keys through the bastion
  `ssh-add -K aws-api-key`
  - Ensure port forwarding enabled .ssh/config
  ```
  Host example.com
    ForwardAgent yes
  ```
* Create Stack ec2-cloud-formation.yaml
* Connect to your bastion via ssh
 - Bounce to your internal private subnet instances from the Bastion shell (ssh to internal ip)
 - Problems reference this: https://developer.github.com/v3/guides/using-ssh-agent-forwarding/


##Manual Steps (POC -- will be replaced by Cloud Formation)
1. Copy jar file and Amazon Coretto to S3
2. Create role for ec2
3. Spin up manual instance of EC2, sync the fourmeal folder on ec2 spinup, run jar (setup role permissions) - Checked Enable Cloudwatch
 - Must install java --using Amazon Coretto
 - Sync files from s3 in ec2 instance commands, install java and run jar (remember this executes as root, so directories are not 'home' dir)
 ```
#!/bin/bash
aws s3 sync s3://fourmeal-colbywar /home/ec2-user
tar -xzf /home/ec2-user/amazon-corretto-11-x64-linux-jdk.tar.gz -C /opt
export PATH=/opt/amazon-corretto-11.0.6.10.1-linux-x64/bin:$PATH
nohup java -jar /home/ec2-user/fourmeal-1.1.0.jar &
```
- Curl to test
`curl http://localhost:5000/test`

3. Hit Test port and ensure logging to CloudWatch (likely permissions)
- CPU will work out of box, but for application logs (log4j) to be uploaded, need the cloudwatch agent
https://docs.aws.amazon.com/AmazonCloudWatch/latest/monitoring/install-CloudWatch-Agent-commandline-fleet.html

4. Spin up Dynamodb - configure permissions for access from api

### Security Log
- Create 'fourmeal-api-application-user-role' as an application role to access dynamo, s3 and other resources
