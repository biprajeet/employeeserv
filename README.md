# employeeserv

## Application Overview
employeeserv is a spring boot rest application which would provide the CRUD operations for `Employee` resource.

There are three modules in this application
- employeeservApi - This module contains the interface.
	- `v1/schema/employee.json` defines the employee resource.
	- `jsonschema2pojo-maven-plugin` is being used to create `Employee POJO` from json file.
	- `EmployeeResource.java` is the interface for CRUD operations on `Employee` resource.
		- GET `/v1/bfs/employees/{id}` endpoint is defined to fetch the resource.
		- POST `/v1/bfs/employees/` endpoint is defined to create the resource.
- employeeservImplementation - This module contains the implementation for the rest endpoints.
	- `EmployeeResourceImpl.java` implements the `EmployeeResource` interface.
- employeeservFunctionalTests - This module would have the functional tests.Load Balancer 

## How to run the application
- Please have Maven version `3.3.3` & Java 8 on your system.
- Use command `mvn clean install` to build the project.
- Use command `mvn spring-boot:run` from `employeeservImplementation` folder to run the project.
- Use postman or curl to access `http://localhost:8080/v1/bfs/employees/1` GET endpoint. It will return an Employee resource.

## Work Done

Logging and Monitoring

- Logging is being done with help of log4j2 and logging is added in all methods with appropriate log level
- MDC added for tracing a particular incoming request in log lines. GET request is traced via ID and POST request is traced via Name of Employee
- PERF statements added for database calls to check database response time during Load Test
- Metrics added, can be enabled by configuring metric.enable=true. This will give the time taken in each method and can be used to find out time taking methods.

Project  

- Input Validation - EmployeeservApi contains validation in JSON which is used to validate incoming inputs
- Security & Authorization - The rest calls can be secured and authenticated at Load Balancer Level. (CA Certificates and JWT implementation on client side)
- v1 vs v2 Went ahead with modifying v1 as the assignment is not a breaking change. Older Clients will get more information when calling GET endpoint
- Rate Limiting can be handled at Load Balancer level
- Input Sanitization for security vulnerabilities can be handled at Load Balancer level. 

Code Quality

- Unit Testing for all methods with logic, code coverage at 85%
- SONAR issues removed
- Project is broken down into packages and no classes exceed 100 line length

Response

- 200 OK - If requested resource is returned correctly
- 201 CREATED - If post request was successful
- 404 NOT FOUND - If requested resource does not exist (example : `GET /v1/bfs/employees/500` and id 500 does not exist)
- 400 BAD REQUEST - If input is incorrect as per `v1/schema/employee.json` or validation has failed
- 500 INTERNAL SERVER ERROR - If any other error occurs


