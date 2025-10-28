This project demonstrates basic implementations of RESTful and SOAP-based web services in Java. It provides examples of building, deploying, and testing both types of services, focusing on restaurant-related operations such as fetching menu data and handling orders.

Project Structure
RestaurantService.java  RESTful web service implementation
SoapService.java       SOAP web service implementation
webservice-testing.http HTTP request file for testing endpoints
RestaurantService.class  Compiled REST service class
RestaurantService1.class  Inner class (handler or method)
RestaurantService2.class  Inner class (handler or method)
RestaurantService3.class Inner class (handler or method)

Features
 REST API: Provides endpoints for restaurant-related operations (such as fetching menu data or handling orders).  
 SOAP Service: Implements a simple SOAP web service for structured XML-based communication.  
HTTP Testing File: Includes `.http` file for easy testing using tools Postman. 

 How to Run
 For REST Service
1. The service should start and expose REST endpoints  which is http://localhost:8090/menu and others 
 Use the webservice-testing.http file or Postman to test API endpoints.

  For SOAP Service
1. Run it on postman on local host http://localhost:9999/soap
   
