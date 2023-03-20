# This is a Sample SpringBoot Project

### Prerequisite
JAVA 17 or Higher Version

MySql 8.x Version 

SonarQube 9.9 Version

STS or IntelliJ Idea or Eclipse IDE

**Clone the project to specified folder and import it into Your Favourite IDE**

```bash
git clone https://github.com/flabdev/Spring-Boot-Starter.git
```

**Setting Up Local Properties File**

In the cloned repository, navigate to src/main/resources and copy the application.properties file 
Past the file in any location on your computer other than the project folder and name it application-local.properties 
add the mysql details for the project 

Change the datasourcename and datasourcePassword in the file to below values 

```bash

spring.datasource.url=@ConnectionURL 
spring.datasource.username=@UserName
spring.datasource.password=@Password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```
## Project execution 

Navigate to root folder and open the terminal and execute below command 

```bash
gradle clean build

```
It will generate JaCoco report as well as jar for our application 

jacoco report location build/jacocoHtml/index.html
jar file location build/libs/Spring-Boot-Starter-0.0.1-SNAPSHOT.jar

Execute the below command
```bash
java -jar build/libs/Spring-Boot-Starter-0.0.1-SNAPSHOT.jar --spring.profile.active=local --spring.config.location=@YourApplicationPropertiesLocation
```

App Will be run on port 9090 

Open the Postman and verify 

## [Integrate sonarqube into the springboot project](https://github.com/flabdev/Spring-Boot-Starter/wiki)

**We need JAVA 11 or 17 for sonarqube**

**To run sonarqube**

```bash
./gradlew sonar -D "sonar.projectKey=Spring-Boot-Starter" -D "sonar.host.url=http://localhost:9000" -D "sonar.login={token generated while integeration}"
```



















# Testcontainers SpringBoot
This quick starter will guide you to configure and use Testcontainers in a SpringBoot project.

## 1. Setup Environment
Make sure you have Java 8+ and a [compatible Docker environment](https://www.testcontainers.org/supported_docker_environment/) installed.
If you are going to use Maven or gradle build tool then make sure Java 17 or higher version is installed.
Make sure you have gradle 8 version
jacoco 0.8.8 version
sonarqube 3.5.0.2730 version
Make sure that you have install sonarqube locally in your system with version 9.9


For example:
```shell
$ java -version
openjdk version "17.0.4" 2022-07-19
OpenJDK Runtime Environment Temurin-17.0.4+8 (build 17.0.4+8)
OpenJDK 64-Bit Server VM Temurin-17.0.4+8 (build 17.0.4+8, mixed mode, sharing)

$ docker version
... 
Server: Docker Desktop 4.12.0 (85629)
 Engine:
  Version:          20.10.17
  API version:      1.41 (minimum version 1.12)
  Go version:       go1.17.11
...

To check java verion in  window use below command 
java -version
To check gradle version in window use below command 
gradle -v


```

## 2. Run Tests

Before Running the Project We must change the username and password for mysql in application.properties file 


```
spring.datasource.username=@yourname

spring.datasource.password=@Password
```

The sample project uses JUnit tests and Testcontainers to run them against actual databases running in containers.

Run the command to run the tests.
```shell
$ ./gradlew test //for Gradle
$ ./mvnw verify  //for Maven

To run Sonarqube Server locally in your window system 
go to your sonarqube location then go to bin folder then go to windows-x86-64 
open command prompt here and type 
StartSonar.bat
to start your sonar server
To check wheather your sonarqube is up and running 
go to your browser and typle below link (by default sonar server runs on 9000 port)
http://localhost:9000 

To build your jar and to create jacoco report run below command on cmd 
gradle clean build
To check yor jacoco report 
go to your project folder go to build folder go to jacocoHtml 
open index.html on browser

To push your code to sonarqube server for static code analysis 
type below command 
gradlew sonar -Dsonar.projectKey=@YourProjectName -Dsonar.host.url=http://localhost:9000 -Dsonar.login=@YourKeyToken

To check your code quality go to your browser
where sonarqube is running http://localhost:9000
go to your project and See the status of code




```

The tests should pass.

# Consumer Driven Contracts with Pact
This quick starter will guide you to configure and use Testcontainers in a SpringBoot project.

## 1. Setup Environment
Make sure you have Java 8+ and a [compatible Docker environment](https://www.testcontainers.org/supported_docker_environment/) installed.
If you are going to use Maven build tool then make sure Java 17+ is installed.

## 2. Generate the pact file
Use the below gradle command to verify the contracts
```shell
$ ./gradle test --tests PactConsumerJUnit5Test
```
Pact file should generated under path
```
/build/pacts/UserConsumer-UserServiceJUnit5.json
```

## 3. Verifying contracts
Use the below gradle command to verify the contracts
```shell
$ ./gradle pactVerify
```
The tests should pass.

## 4. Running a Pact Broker locally
The pact broker requires a postgres database to work, so use docker compose to spin up and configure containers.

## 5. Publishing Pact contracts
Use the below command to publish the generated contracts
```shell
$ ./gradle pactPublish
```
Go to a browser and visit http://localhost:9292/ to open the broker web UI.

## 6. Retrieving and verifying contracts
Use the below command to publish the generated contracts
```shell
$ ./gradle pactVerify
```
