## Chinese version software display address http://8.137.95.225:8090/
- administrators' access
- **Username: `admin`
- **Password: `123`


# Dream Project

This project adopts an agile development model. Initially, the front-end uses Thymeleaf with a monolithic architecture. Later, it will be updated to a separated front-end and back-end architecture using Vue/CLI. The project is developed in modules. Currently, the user management module is under development, and a new branch `nusblog` has been established for the blog module.

## Project Description

- **Current Version**: I have updated the version on github repository to be english version, but so far the version deployed on the cloud server is still chinese version. http://8.137.95.225:8090/
- **Database File Path**: `dream/user/data/dreamE.sql`
- **Database Settings**:
    - Charset: `utf8mb4`
    - Collation: `utf8mb4_bin`
- **Local Database Configuration**:
    - Database name: 'dream1'
    - Username: `root`
    - Password: `123456`
- **Startup Program**: `UserApplication.java`
    - Access URL: [http://localhost:8090](http://localhost:8090)
- **Admin Test Account**:
    - ** administrators' access
    - ** Username: `admin`
    - ** Password: `123`
    - ** user with limited access
    - ** Username: `tom`
    - ** Password: `123`
- **Cloud Database Connection(Chinese versioin)** (may be unstable outside China):
```js
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://8.137.95.225:3306/david-dream?serverTimezone=UTC&characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=123456
```

- **Cloud Database Connection(english versioin)** (may be unstable outside China):
```js
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://8.137.95.225:3306/david-dreamE?serverTimezone=UTC&characterEncoding=UTF-8
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

- **Cloud Database Connection(Chinese versioin)** (may be unstable outside China):
```js
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://8.137.95.225:3306/david-dream?serverTimezone=UTC&characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=123456
```
- **Cloud Database Connection(english versioin)** (may be unstable outside China):
```js
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://8.137.95.225:3306/david-dreamE?serverTimezone=UTC&characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=123456
```
### Accessing Admin Panel

- Open your browser and visit [http://localhost:8090](http://localhost:8090) or [http://8.137.95.225:8090](http://8.137.95.225:8090) (if using the cloud server).
- Use the following test account to log in:
    - Username: `admin`
    - Password: `123`
    - ** user with limited access
    - ** Username: `tom`
    - ** Password: `123`

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

## Motivation:

As an NUS student, I find that sourcing educational resources is a time-consuming and challenging process. This is particularly difficult when students struggle to comprehend lectures and need to find clearer slides and helpful past-year videos. Additionally, when searching for cheat sheets and past-year papers, resources are scarce because professors may not release many to avoid overburdening students. And it is also especially problematic for students in majors with heavy workloads who lack the time to create their own cheat sheets. Therefore, I aim to develop a website for NUS students, linked with NUSMODs, to facilitate the sharing of learning experiences and resources. Furthermore, studying alone in the university is a hard process, so the website has functions of creating a user's own studying community and sharing a user's own studying blog.

## User Stories:

 (medium) As a student, I want to easily find helpful documents on the website simply through the course code and course name, so that I can save up my time for my workload.
(medium) As a student, I want to easily evaluate the resource I searched through other people’s rating and comments so that I can easily distinguish the truly useful docs.
(high)As a student, I want to read through previous students’ blogs and personal diary. so that I can have a general evaluation of this course and I also hope my personal experience can aid other students.
(medium) As a student, I want to create my own studying community so that I don’t have to study alone anymore. 
(high) As a TA, I want to use this website to release the documents I think that are helpful in my own account. I also hope to make progress in my teaching technique through the rating and comments given by my students. So that I can better fulfill my duty of helping my students.
(high) As a student in NUS, I want to upload my notes on the website and dynamically modifying it whenever I want. And before I make sure my notes are completed I don’t want to expose them to the public. So that I can find a easier place to settle my notes and an envrionment for people who wants to correct them.

## Project Feature:

## 1 
Account Management: every student will have their own repositories, which will record all the materials they have posted. When they are posting materials, they can select them to be either notes, cheatsheets, or past-year videos etc...and when they are posting they can select a tag for the document they are posting which can be the key for searching. The posted materials can be setted to either private or public, or granting certain people the access right to visit. 

## 2 
Recommendation system: After students have visited the materials they can either vote “likes” or “dislikes”, the materials with most likes will be granted with higher recommending priority in the same relevance level.

## 3 
Comment system: The web will also have a comment system for users to leave comments on the materials they have readed in case any potential confusion. And if there’s any dispute user can also see the replication from other people. In case, any debate escalate into too fierce conflict we will also have a report function.

## 4 
Course-centralled resources learning warehouse: Through the course code, the student can find relevant lecture slides, videos, notes, and other relevant stuff easily. In the searching box, you can either put the name of the course or using the course code, and also add the exact name of  the kind of material you want. Then the web will guide you to the page of that course and recommend the material with the closest name and highest “likes” at the top.

## 5 
Multi-form resource platform: the website supports information in multimedia, like videos, or pdf document. But the videos can only be embedded by a link from other video platform, otherwise the cost will be too consuming.

## 6 
Course-centralled blog platform: The web is going to be mounted with a blog system for students who wanna share their studying experience. When the user want to create a new blog they can tag the blog with the name of the module and the course code. So that when students are searching the course name or code, the users’ blog will be correspondingly categorized in the blog block under the course name.

## 7 
Studying Community: The first user created the community will become the administrator of the community. And he is granted with the right to invite and delete other members of the community. The power of administrator can be distributed out by the main administrator exclusively, and can also be drew back by him. The studying community will also be tagged with the course code and name accordingly for searching purpose.

## 8 
Completed backend managing system: the backend admin can check the statistics from the database, like how many materials in each module and what’s the overall rating of a published material.

## 9 
deploy to the cloud for users to test and use: the web will be eventually deployed on a cloud server and we are super grateful for anybody who wants to use and test our web.
---

------

------
## Website running preview picture
---
![image](https://github.com/heshi0816/dream/blob/branch_he/appImage/dream-hompage.jpg)
---
![image](https://github.com/heshi0816/dream/blob/branch_he/appImage/dream-login.jpg)
---
![image](https://github.com/heshi0816/dream/blob/branch_he/appImage/dream-user.jpg)
---
![image](https://github.com/heshi0816/dream/blob/branch_he/appImage/dream-userAdd.jpg)
---
![image](https://github.com/heshi0816/dream/blob/branch_he/appImage/dream-faculty.jpg)
---
![image](https://github.com/heshi0816/dream/blob/branch_he/appImage/dream-systemManagement.jpg)
---
![image](https://github.com/heshi0816/dream/blob/branch_he/appImage/dream-roleManagement.jpg)
---
![image](https://github.com/heshi0816/dream/blob/branch_he/appImage/dream-roleAdd.jpg)
---
![image](https://github.com/heshi0816/dream/blob/branch_he/appImage/dream-accessManagement.jpg)
---

---

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

