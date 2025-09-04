# Interior Design Planner API 🏚️🏠

A lightweight planner that helps **Interior designers** to organise projects from planning to completion for their clients. The REST API allows the designers to track clients details, projects, tasks, timelines and room specifications.

## 📝 Main Features

- **Client Management** - CRUD functionalities to access client details.
- **Project Management** - CRUD functionalities to help manage projects with a budget, deadlines, status and meeting links.
- **Room Management** - CRUD functionalities to define rooms with dimensions, room type and design changes and checklists.
- **Status Tracking** - Use of enums to track project stages i.e PLANNING, COMPLETED, ARCHIVED...
- **Audit Logging** - Automatically notes the dates when projects are completed and when clients details, projects and rooms are created or updated.

## 🛠️ Tech Stack

**Languages**: <object>![Java](https://img.shields.io/badge/Java-007396?style=flat-square&logo=java&logoColor=white)</object>

**Frameworks**: ![Springboot](https://img.shields.io/badge/springboot-6DB33F?style=flat-square&logo=springboot&logoColor=white) ![Hibernate](https://img.shields.io/badge/hibernate-59666C?style=flat-square&logo=hibernate&logoColor=white)

**Build Tool**: ![Maven](https://img.shields.io/badge/apachemaven-C71A36?style=flat-square&logo=apachemaven&logoColor=white)

**Database**: ![mySQL](https://img.shields.io/badge/mysql-4479A1?style=flat-square&logo=mysql&logoColor=white)

**Testing**: ![Junit5](https://img.shields.io/badge/junit5-25A162?style=flat-square&logo=junit5&logoColor=white) ![Mockito](https://img.shields.io/badge/mockito-green?style=flat-square&labelColor=green)

**Version Control**: ![Git](https://img.shields.io/badge/Git-F05032?style=flat-square&logo=git&logoColor=white) ![GitHub](https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=github&logoColor=white)

**API Testing**: ![Postman](https://img.shields.io/badge/postman-FF6C37?style=flat-square&logo=postman&logoColor=white)

## 🧱 Prerequisites

1. Install [Java 17+](https://www.java.com/en/)
2. Install [Maven](https://maven.apache.org/) with your IDE
3. Use an IDE - [Visual Studio Code](https://code.visualstudio.com/) or (IntelliJ IDEA, Eclipse)
4. Set up a [MySQL](https://www.mysql.com/) Database (and configure username/password in application.properties)
5. [Git](https://git-scm.com/) installed
6. Use [Postman](https://www.postman.com/) to test REST API endpoints

## 👷🏿‍♀️ Getting Started

1. Clone the repository

```
 git clone https://github.com/Vicko657/interior-design-api.git
```

2. Go to the project directory

```javascript
cd interior-design-api
```

3. Build the project using Maven

```
./mvnw clean install
```

4.  Run the application

```
./mvnw spring-boot:run
```

## 📏 📐 Run the tests

```
./mvnw clean test
```

## 🛋️ API Documentation

When the API is running you can check the **Swagger UI** documentation through this link:

```
http://localhost:8080/swagger-ui-interior-design-planner.html
```

## 🏘️ API Endpoints
