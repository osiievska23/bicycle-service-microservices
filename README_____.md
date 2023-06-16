Tha Back-end L+ Report-Generation API

- [Overview](#Overview)
- [Running the Application LOCALLY(remote DB) without Docker](#running-the-application-locally--remote-db--without-docker)
- [Running integration tests with local postgres database](#running-integration-tests-with-local-postgres-database)
- [Deploying the API Gateway and other resources from local machine or from the feature branch to the existing or New Environments](#deploying-the-api-gateway-and-other-resources-from-local-machine-or-from-the-feature-branch-to-the-existing-or-new-environments)
- [Minimum Requirements for Pull Requests](#minimum-requirements-for-pull-requests)

Overview
=======================================================
"Good architecture allows the software to be changed during its lifetime with as little, constant effort as possible (and correspondingly predictable costs for the client)." 
According to Robert C. Martin’s book “Clean Architecture,”

[Documentation](https://sema4genomics.atlassian.net/wiki/spaces/LPLUSR/pages/3463708964/Report+Data+Collector+Component)

[c4 diagrams](readme/c4/Lplus-report-generation-service.drawio.html)

[c2 and c3 combined diagram](readme/c2-c3/Container-component-diagram.drawio.html)

# C2-C3 Container-component diagram

![C2-C3-Container-diagram.jpg](readme/c2-c3/Container-component-diagram.jpg)


Running the Application LOCALLY(remote DB) without Docker
=======================================================
1. Make sure that you have AWS credentials for 098xxx (dev) AWS account (or for different AWS account if needed with different profile name)
2. Connect to company VPN
3. Configure ssh tunnel between remote database and local port `3306`  
   ``sudo ssh -i lis-dev-keys.pem -N -L 3306:{remote-db-host}:3306 {ssh-proxy-user}@[ssh-proxy-host]``
or the connection to the remote DB can be configured via Intellij IDEA just adding the local port number
when trying to connect to remote DB it will create the tunnel automatically so need to run sudo ssh command with args. ![ssh_config.png](readme/ssh_config.png) 

4. Make sure that remote database is allowed at `localhost:3306`
5. Ensure environment variables have been set (with `export`) for the following:
   - `AWS_ENDPOINT=dynamodb.us-east-1.amazonaws.com`, 
   - `AWS_REGION=us-east-1`, 
   - `LIS_DB_PASSWORD=real password to the remote db`,
   - `LIS_DB_SECRET_NAME=` we can set it to empty string,
   - `LIS_DB_LIS_URL_TEST=jdbc:mariadb://localhost:3306/{db name here}`,
   - `LIS_DB_USERNAME=real db user name`
6. **Optional**. To allow generating graphical reports containing [CV](https://jasperreports.sourceforge.net/api/net/sf/jasperreports/components/package-summary.html) 
components, [PhantomJS](https://github.com/Medium/phantomjs#deciding-where-to-get-phantomjs) should be installed to allow execution of JavaScript in the background.
After installation, set path to the phantomjs executable file as `PHANTOMJS_EXECUTABLE_PATH` value.
7. Run application with spring profile `local` with  
   `./gradlew bootRun --args='--spring.profiles.active=local'`



Running integration tests with local postgres database
=======================================================
1. Clone and open [s4-lis-db-schema schema repository](https://github.com/sema4genomics/s4-lis-db-schema) (preferably on **dev** branch) and run gradle task **setupLocalDatabase**:
   `./gradlew setupLocalDatabase -PignoreMissingMigrations=true`. This step assumes that **docker** and **docker-compose** are installed locally and docker daemon is available(`docker ps` command works as expected).
2. After previous step is done, integration tests can be run as `./gradlew integrationTest`. Integration tests are located in `src/intTest/groovy/com/sema4/reporting/generation/it` directory.

For Windows users, sometimes exception occurs while running task `setupLocalItDatabase`:
`java.lang.IllegalStateException: Could not connect to Ryuk at localhost:49154`. This can be resolved by running `netcfg -d` in console and rebooting system.


Deploying the API Gateway and other resources from local machine or from the feature branch to the existing or New Environments
=======================================================
Use cases:
- To verify new added resources and API Gateway
- The service must be exposed to clients via API Gateway to the new environment.

1.
   - Ensure the `serverless.yml` contains the correct environment custom configs.
   - While in the project root directory, run the following commands:
      ```
      npm ci
      sls deploy --stage {stage_name} --aws-profile {aws_profile}
      ```
      where:
       - `aws_profile`: what matches the account/region you wish to deploy to
       - `stage_name`: the name of the environment
   - After successful CloudFormation, go to the API Gateway console. "Stages" >
      "{stage_name}" > "Stage Variables" > "Add Stage Variable".
      Create a variable with name `url` and a value equal to the Elastic Load Balancer DNS
      concatenated with the ingress path (should be "/report-generation")
       - Example: If the ELB DNS is `12345-67890.elb.us-east-1.amazonaws.com`,
         the value of `url` would be `12345-67890.elb.us-east-1.amazonaws.com/report-generation`
  
2.
    - Add new changes to the serverless file.
    - Add the branch name to the deployment script in the .gitlab-ci.yml file

    ![deploy_api_gateway_branch_name.png](readme/deploy_api_gateway_branch_name.png)
   
    - Commit and push changes to the remote branch. It will trigger the deployment of api gateway and resources job.
    - After testing remove your feature branch name from the deployment job.
    - Commit and push again to create a PR.


Minimum Requirements for Pull Requests
==================================
When a PR is ready for review, the minimum expectation is:
* ticket link: a link to the JIRA ticket associated with this work.
* description: typically the commit message + elaboration - why to add this commit to the project, what does it do and what is the purpose. 