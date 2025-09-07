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

## 💡 MYSQL Database Connection:

```
mySQLDumpFile
```

## 📏 📐 Testing

Unit tests were created for the repositiory and service, to test out the functionalities of the methods used and to check for any vulnerabilities.

```
./mvnw clean test
```

## 🛋️ API Documentation

**Javadocs**

This use this file path to view the javadocs documentation:

```
interior-design-api/target/reports/apidocs/index.html

./mvnw javadoc:javadoc
```

**Swagger:**

When the API is running you can check the **Swagger UI** documentation through this link:

```
http://localhost:8080/swagger-ui-interior-design-planner.html
```

## 🏘️ API Endpoints

Th Interior Design Planner API provides different endpoints to help the designer manage their interior design projects with their clients.

The entity relationships between the three entities: Client, Project and Room will allow the interior designer to perform CRUD operations on each entity, as well as query data.

Here are some examples of the endpoints used in the API:

### 💁🏾‍♀️ Clients Endpoints

<hr>

**Creates a new Client**

- HTTP Method: POST

- Creates a new Client on the system. It should return their details such as their name, email, phone number, address and projects.

```
/clients
```

**Finds Client by Lastname**

- HTTP Method: GET

- Retrieves clients by last name. Returns a list of matching clients or an empty list.

```
/clients/lastName/{lastName}
```

</br>

### 🗂️ Project Endpoints

<hr>

**Find Projects by Status**

- HTTP Method: GET

- Retrieves all projects with the specified status. The statuses are Enums and are case-insensitive.

```
/projects/status/{status}
```

#### **Orders Projects by deadline**

- HTTP Method: GET

- Retrieves all projects ordered by their dueDate in ascending order.

```
/projects/deadlines
```

</br>

### 🛌 Rooms Endpoints

<hr>

**Find Rooms by Room Type**

- HTTP Method: GET

- Retrieves all rooms with the specified type. The type are Enums and are case-insensitive.

```
/rooms/type/{type}
```

**Reassigns Room with a different project**

- HTTP Method: PATCH

- Reassign a room to a different project, keeping the one to one relationship.

```
/rooms/{roomId}/projects/{projectId}
```

<br>

## ⚠️ Error Handling

The API uses standard HTTP status codes to indicate errors. Custom exceptions for each entity (e.g ClientNotFoundException) are thrown when a resource is missing or a request is invalid. Spring automatically returns the corresponding status code (e.g., 404 Not Found, 400 Bad Request) along with a descriptive error message.
