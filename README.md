##软件展示地址http://8.137.95.225:8090/
- administrators' access
- **Username: `admin`
- **Password: `123`
- user with limited access
- **Username: `tom`
- **Password: `123`

# Dream Project

This project adopts an agile development model. Initially, the front-end uses Thymeleaf with a monolithic architecture. Later, it will be updated to a separated front-end and back-end architecture using Vue/CLI. The project is developed in modules. Currently, the user management module is under development, and a new branch `nusblog` has been established for the blog module.

## Project Description

- **Current Version**: Chinese. Please set your development software to UTF-8 encoding after downloading the code. An English version will be updated later.
- **Database File Path**: `dream/user/data/dream.sql`
- **Database Settings**:
    - Charset: `utf8mb4`
    - Collation: `utf8mb4_bin`
- **Local Database Configuration**:
    - Username: `root`
    - Password: `123456`
- **Startup Program**: `UserApplication.java`
    - Access URL: [http://localhost:8090](http://localhost:8090)
- **Admin Test Account**:
    - Username: `admin`
    - Password: `123`
- **Cloud Database Connection** (may be unstable outside China):
```js
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://8.137.95.225:3306/david-dream?serverTimezone=UTC&characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=123456
```
- **Server Deployment** (may be unstable outside China):
- Access URL: [http://8.137.95.225:8090](http://8.137.95.225:8090)
- Admin Test Account:
    - Username: `admin`
    - Password: `123`

## Branch Description

- **Default Branch**: `branch_he`, used for developing the management module.
- **Blog Module Branch**: `nusblog`, used for developing the blog module.

## Installation and Running

### Local Environment Setup

1. **Clone the Project**:
   git clone https://github.com/heshi0816/dream.git
2. **Switch to the Default Branch `branch_he`**:
```js
cd dream
git checkout branch_he
```
4. **Import the Database**:
- Import the database file from `dream/user/data/dreamE.sql` into your local MySQL database.
- Ensure the database charset and collation are set to `utf8mb4` and `utf8mb4_bin`.

4. **Configure Database Connection**:
- Modify the `application.properties` file to set up your local database connection information.

5. **Start the Project**:
- Use your IDE to run the `UserApplication.java` file.
- Access the URL: [http://localhost:8090](http://localhost:8090).

### Using Cloud Database

If you choose to use the cloud database, make sure to configure the following information in `application.properties`:
```js
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://8.137.95.225:3306/david-dream?serverTimezone=UTC&characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=123456
```
### Accessing Admin Panel

- Open your browser and visit [http://localhost:8090](http://localhost:8090) or [http://8.137.95.225:8090](http://8.137.95.225:8090) (if using the cloud server).
- Use the following test account to log in:
    - Username: `admin`
    - Password: `123`

## Development Branches

### User Management Module

The user management module's code is located in the default branch `branch_he`. All functionalities and modifications related to user management should be carried out in this branch.

### Blog Module

The blog module is being developed in the newly established branch `nusblog`. To develop the blog module, switch to this branch:
```js
git checkout nusblog
```

If you have any questions or need assistance, please contact the project maintainers.




Software demonstration URL: http://8.137.95.225:8090/
Test account: admin
Password: 123


# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.3.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.3.0/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#web)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

