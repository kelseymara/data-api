# Customer/Data Resource API
This repository contains code related to the Customer API

## Branches overview

- **main** and **stage-02**: Both of these branches contain code related to stage 2. It is a RESTful API that serves hard coded Customer data and supports these endpoints:
  - GET\api
  - GET\api\customers
  - GET\api\customers\{cust-id}

- **stage-03**: This branch has full CRUD operations implemented for the Customer API. It can be ran standalone or by using docker.

- **stage-04**: There are a few branches for stage-04 based on my progress. The branch with the highest number at the end is the most updated.
  First working implementation (stage-04-2) involved following the Security Boot lab. - Customer accepts JWT token, which is produced by Account (branches attempt-2 and attempt-3) in Account repository https://github.com/kelseymara/account-api/tree/attempt-3.
  These were tested by using POST in account first, and then GET in Customer.
-  Stage-04-03 branch is the latest branch. So far it only has the change of an added password attribute to Customer (as of 8/21)

  
  
