# Customer/Data Resource API
This repository contains code related to the Customer API

## Branches overview

- **stage-02**: This branches contain code related to stage 2. It is a RESTful API that serves hard coded Customer data and supports these endpoints:
  - GET\api
  - GET\api\customers
  - GET\api\customers\{cust-id}

- **stage-03**: This branch has full CRUD operations implemented for the Customer API using HSQL database instead of hard coded data. 

- **stage-04**: This branch implements OAuth2 for the Customer. There is an added SecurityConfig.java file
-The customer can retrieve a JWT from the Account service, which generates the token (located in repository:  https://github.com/kelseymara/account-api). Once the token is obtained, the user can complete CRUD functions on Customer.
  
  
