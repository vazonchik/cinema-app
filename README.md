# Cinema-Service
This is simple simulator of cinema service for reservation tickets, that supports registration, authentication and CRUD operations.
___

## Features:
* register or login as user
* create and find movies
* create and find available movie sessions
* creating shopping cart
* add tickets to shopping cart
* complete an order
---

## Project architecture:
The Cinema-Service is based on 3-layer architecture:
1. Controllers, which handle requests, call services and send responses
2. Services - there are all business logic
3. DAO, which handle CRUD operations to database

### Model structure
![img.png](img.png)
---

## Technologies used in project:
* Java 17
* MySql 8.0.29
* Hibernate 5.6.8
* Maven
* Spring

<h2>Instructions for launching the project:</h2>
<h4>To run this project locally, follow these steps:</h4>

1. You should install <a href="https://tomcat.apache.org/download-90.cgi">TomCat</a>, <a href="https://dev.mysql.com/downloads/installer/">MySQL</a>
and <a href="https://maven.apache.org/install.html">Maven</a>

2. Clone this project from GitHub
```bash
git clone https://github.com/vazonchik/cinema-app.git
```
3. Configure app.properties in resources folder: set db.user and db.password <br>
4. Run command
```bash
mvn build
```
5. Configure TomCat for this project and run it.