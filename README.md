# Charity Manager

- [Charity Manager](#charity-manager)
  - [Prerequisites](#prerequisites)
  - [Access the code](#access-the-code)
  - [Run/debug code in IntelliJ](#rundebug-code-in-intellij)
  - [Deploy and run the code locally as docker instance](#deploy-and-run-the-code-locally-as-docker-instance)
  - [Code Conventions](#code-conventions)
    - [Solving tickets](#solving-tickets)

## Prerequisites

Follow the [./PREREQUISITES.md](./PREREQUISITES.md) instructions to configure a local virtual machine with Ubuntu, Docker, IntelliJ.


## Access the code

* Fork the code GitHub repository under your Organization
  * https://github.com/UNIBUC-PROD-ENGINEERING/service
* Clone the code repository:
  * git@github.com:YOUR_ORG_NAME/service.git


## Run/debug code in IntelliJ
* Build the code
    * IntelliJ will build it automatically
    * If you want to build it from command line and also run unit tests, run: ```./gradlew build```
* Create an IntelliJ run configuration for a Jar application
    * Add in the configuration the JAR path to the build folder `./build/libs/hello-0.0.1-SNAPSHOT.jar`
* Start the MongoDB container using docker compose
    * ```docker-compose up -d mongo```
* Run/debug your IntelliJ run configuration
* Open in your browser:
    * http://localhost:8080/hello-world
    * http://localhost:8080/info

## Deploy and run the code locally as docker instance

* Build the docker image of the hello world service
    * ```make build```
* Start all the containers
    * ```docker-compose up -d```

* Verify that all containers started, by running
  ```
  service git:(master) ✗  $ docker ps
  CONTAINER ID   IMAGE           COMMAND                  CREATED         STATUS         PORTS                      NAMES
  c1d05dddd3fe   mongo:5.0.2     "docker-entrypoint.s…"   6 seconds ago   Up 5 seconds   0.0.0.0:27017->27017/tcp   service_mongo_1
  e90bb406c139   hello-img       "java -jar /hello/li…"   6 seconds ago   Up 5 seconds   0.0.0.0:8080->8080/tcp     service_hello_1
  411475a7b596   mongo-express   "tini -- /docker-ent…"   6 seconds ago   Up 2 seconds   0.0.0.0:8090->8081/tcp     service_mongo-admin-ui_1
  ```
* Open in your browser:
    * http://localhost:8080/hello-world
    * http://localhost:8080/info
* You can access the MongoDB Admin UI at:
  * http://localhost:8090 

## Code Conventions

### Solving tickets

* a branch starting off from `main` should be created for each ticket from the [Project Board](https://github.com/orgs/PascalTeam/projects/1/views/1)
* the branch name should follow this pattern: `<type>/<ticketNumber>/<shortDescription>`
  where:
    * **type** can be one of the following: _fix_(bug), _feat_(feature), _docs_(documentation-related changes)
    * **ticketNumber**: the issues number associated with the ticket from the board
    * **shortDescription**: a short description of the ticket

  _Most of these ideas came from [this document](https://gist.github.com/brianclements/841ea7bffdb01346392c)._

* when ready, create a PR having `main` as a base branch and add reviewers