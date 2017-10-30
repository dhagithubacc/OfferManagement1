# Offer Management

Offer Management is a web application developed for managing offers. 

## Getting Started

As of now the project is up and running in an embeded Tomcat Server in local system.This project generates application as OfferMgmt-1.4.6.RELEASE.war 
and microservice  as offers-0.0.1-SNAPSHOT.jar files.

### Prerequisites

Following softwares are used for developing this application.

IDE : STS-3.8.3-RELEASE
Build Tool : Maven(Embeded) v3.3.9
Server : Pivotal tc Server Developer Edition v3.2
Web Framework : Spring v4.3.8.RELEASE & Spring-boot v1.4.6.RELEASE
Database: Mysql Server v5.7
Database Visual Tool : MySQL Workbench 6.3 CE
JDK1.8 : Java
Swagger v2


### Installing

Step-1
Install JDK1.8

Step-2
Install IDE STS-3.8.3-RELEASE for development.

Step-3

Install database Mysql Server v5.7

Step-4

Install visual database tool MySQL Workbench 6.3 CE.

Step-5

Check whether visual database tool or database is integrated or not using id/pwd.

Step-6

Check whether application and micro services are integrated with database or not.

Step-7
Implement Swagger for offer microservices and check how it works.

Step-8
Export database dumps (offermgmt.sql and offer.sql)

Step-9
Import database dumps (offermgmt.sql and offer.sql) in new environment database.

Step-10
Download MyRegistryServ,Offermgmt and offer and execute as separate project.



## Running the tests

Using  Jenkins one can run Test Cases automatically.

### Break down into end to end tests

	#UserControllerTest.java
		1. testRegistrationSuccess() : This is for creating users and saving in database.
		2. testAccountCreatedSuccess() : This is to  confirm that account is created.
		3. testShowAdminSuccess() : This  test confirms admin page is working fine,it fetches required data from database.
		4. testLogoutSuccess() : This test is to confirm logout is working as expected.
	#OfferControllerTest.java
		1. testCreateOfferSuccess() : This is for creating offers.
		2. testdoCreateSuccess(): This test is for creating offer and saving in database.
		3. testShowOffersSuccess() : Test to fetch all created offers from database and display in offers page.
		4. testOfferCreatedSuccess() : This confirms that offer is created.
		5. testServiceDownSuccess() : This test to show offer micro service is down. 

	#OffersControllerTest.java
		1.  testCreatedOfferSuccess : To create offer in offer microservice.
		2.	testShowOffersSuccess :   To show created offers.

## Deployment

This project generates application as OfferMgmt-1.4.6.RELEASE.war and microservice  as offers-0.0.1-SNAPSHOT.jar files
which can be deployed in any web/app server.


## Versioning

For the versions available, see the [tags on this repository]

https://github.com/dhagithubacc/OfferMgmnt.git

https://github.com/dhagithubacc/MyRegistryServ.git

https://github.com/dhagithubacc/offers.git

## Authors

* **Dhanurdhara Swain** - *End to End of the project* 

## License

NA

