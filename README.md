Team Members & Task Distribution
| NO | Student Name | ID No | Assigned Tasks |
|----|--------------|-------|----------------|
| 1. | Aymen Aman | RU 0195/14 | REST API Development - Restaurant endpoints implementation |
| 2. | Dagmawit Endashaw | RU4711/14 | SOAP Service Development - Restaurant operations |
| 3. | Fethiya Jeylan | RU0206/14 | API Testing Suite - HTTP client tests creation |
| 4. | Saron wondwossen | RU0156/14 | Documentation & Analysis - REST vs SOAP comparison report |
| 5. | Suhayb Abdirahman | RU2316/14 | Service Deployment - Multi-port service setup & coordination |



 Restaurant Web Services Project
A comprehensive web services implementation featuring both REST and SOAP APIs for a restaurant management system.

 Project Overview
This project demonstrates the implementation of two different web service architectures:
- REST API - Modern RESTful endpoints using JSON
- SOAP Service - SOAP-based web services using XML
Both services provide complementary functionality for menu management, order processing, and restaurant operations.

 Features
REST API (Port 8090)
- GET /api/menu - Retrieve all menu items
- GET /api/menu/{id} - Get specific menu item by ID
- POST /api/orders - Create new orders with JSON payload

SOAP Service (Port 9999)
- getMenu - Get menu items in XML format
- placeOrder - Submit new orders via SOAP
- calculateTotal - Calculate order totals
- checkOrderStatus - Check order status

 Technologies Used
- Java - programming language
- HTTP Server - Built-in Java HTTP server
- JSON - Data format for REST API
- XML/SOAP - Protocol for SOAP services
- HTTP Client - API testing tool

Project Structure
RestaurantService.java  REST API implementation
SoapService.java  SOAP   service implementation
webservice-testing.http  Comprehensive test suite

API Testing
Both services will start simultaneously
REST API: http://localhost:8090
SOAP Service: http://localhost:9999

Get all menu items
GET http://localhost:8090/api/menu

Create new order
POST http://localhost:8090/api/orders
Content-Type: application/json

{
  "customerName": "AYELE",
  
  "items": [
  
{"itemId":1, "quantity": 2},
{"itemId":3, "quantity": 1}
]
}


Testing SOAP Endpoints
 Get menu via SOAP
POST http://localhost:9999/soap
Content-Type: text/xml
SOAPAction: "getMenu"

<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
   <soap:Body>
      <getMenuRequest/>
   </soap:Body>
</soap:Envelope>

REST vs SOAP Comparison

| Aspect | REST | SOAP |
|--------|------|------|
| Protocol | HTTP | HTTP, SMTP, etc. |
| Data Format | JSON | XML |
| Standards | REST principles | WS- standards |
| Performance | Faster | Slower (XML parsing) |
| Flexibility | High | Moderate |

Key Learnings
-Implementation of both REST and SOAP web services
-JSON and XML data format handling
-HTTP server programming in Java
-API testing 
-Service deployment 


