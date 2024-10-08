# Customer/Data Resource API
This repository contains code related to the Customer API

## Branches overview

- **stage-02**: This branches contain code related to stage 2. It is a RESTful API that serves hard coded Customer data and supports these endpoints:
  - GET\api
  - GET\api\customers
  - GET\api\customers\{cust-id}

- **stage-03**: This branch has full CRUD operations implemented for the Customer API using HSQL database instead of hard coded data.
- **stage-03-for-react**: For the most part, this branch has the same features as stage 3 branch. However, I changed some of the APIs for it to work with my react project. You can run both the backend and frontend to see how they work together. The react project is here: https://github.com/kelseymara/front-end-app 

- **stage-04**: This branch implements OAuth2 for the Customer. There is an added SecurityConfig.java file
-The customer can retrieve a JWT from the Account service, which generates the token (located in repository:  https://github.com/kelseymara/account-api). Once the token is obtained, the user can complete CRUD functions on Customer.
  
  
## Running the Code
- Execute "./gradlew" in the project directory to retrieve dependencies
- Execute "./gradlew bootRun" to compile and run the app in standalone more
