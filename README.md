
# Spring Boot Reactive Mongo CRUD Application

This repository demonstrates a **Spring Boot Reactive** application with **MongoDB** for performing CRUD operations. It includes advanced features like **dynamic filtering**, **pagination**, **sorting**, and **data validation** for both user and address models.

---

## Features

### Core Features
1. **Reactive Programming**:
    - Built using **Spring WebFlux** for non-blocking, asynchronous operations.
    - Uses **ReactiveMongoRepository** and `ReactiveMongoTemplate` for database interactions.
2. **CRUD Operations**:
    - Create, Read, Update, and Delete users.
3. **Dynamic Search**:
    - Supports searching with **filters** provided in the request body.
4. **Pagination and Sorting**:
    - Customize page size, sorting fields, and order (ascending/descending).

---

## Technologies Used

- **Spring Boot**: Simplifies application setup and development.
- **Spring WebFlux**: Reactive web framework for asynchronous processing.
- **MongoDB**: NoSQL database for storing and managing user data.
- **Java 17**: Latest LTS version for enhanced language features.
- **Maven**: Build automation and dependency management.

---

## Project Structure

### Key Packages and Classes

#### 1. **Controller Layer**
Handles HTTP requests and responses:
- `UserController`: Exposes RESTful endpoints for managing users.

#### 2. **Service Layer**
Contains business logic:
- `UserService`: Handles CRUD operations, validation, and dynamic query building.

#### 3. **Repository Layer**
Interacts with MongoDB:
- `UserRepository`: Extends `ReactiveMongoRepository` and a custom repository for advanced queries.
- `CustomReactiveRepository`: Defines custom methods like dynamic search.
- `CustomReactiveRepositoryImpl`: Implements dynamic filtering, pagination, and sorting logic.

#### 4. **Model Layer**
Encapsulates database models and DTOs:
- `UserDto`: Data Transfer Object for user-related operations.
- `Address`: Represents user addresses with validation.
- `PagingAndSortingRequest`: Encapsulates pagination, sorting, and dynamic filters.

---

## API Endpoints

### User Management

#### 1. **Create a User**
- **Endpoint**: `POST /api/user`
- **Request Body**:
   ```json
   {
     "name": "John Doe",
     "email": "john.doe@example.com",
     "phone": "1234567890",
     "address": {
       "addressLine1": "123 Main St",
       "city": "Springfield",
       "state": "IL",
       "country": "USA",
       "zipCode": "62704"
     }
   }
   ```
- **Response**:
   ```json
   {
     "id": "123",
     "name": "John Doe",
     "email": "john.doe@example.com",
     "phone": "1234567890",
     "address": {
       "addressLine1": "123 Main St",
       "city": "Springfield",
       "state": "IL",
       "country": "USA",
       "zipCode": "62704"
     }
   }
   ```

#### 2. **Retrieve a User by ID**
- **Endpoint**: `GET /api/user/{id}`
- **Response**:
   ```json
   {
     "id": "123",
     "name": "John Doe",
     "email": "john.doe@example.com",
     "phone": "1234567890",
     "address": {
       "addressLine1": "123 Main St",
       "city": "Springfield",
       "state": "IL",
       "country": "USA",
       "zipCode": "62704"
     }
   }
   ```

#### 3. **Update a User**
- **Endpoint**: `PUT /api/user/{id}`
- **Request Body**: Similar to the create request body.
- **Response**: Updated user details.

#### 4. **Delete a User**
- **Endpoint**: `DELETE /api/user/{id}`
- **Response**: No content (`204`).

### Dynamic Search

#### **Search with Filters, Pagination, and Sorting**
- **Endpoint**: `POST /api/user/getAll`
- **Request Body**:
   ```json
   {
     "page": 1,
     "limit": 5,
     "sortBy": "name",
     "sortType": "asc",
     "fields": {
       "city": "Springfield",
       "state": "IL"
     }
   }
   ```
- **Response**:
   ```json
   {
     "content": [
       {
         "id": "123",
         "name": "John Doe",
         "email": "john.doe@example.com",
         "phone": "1234567890",
         "address": {
           "addressLine1": "123 Main St",
           "city": "Springfield",
           "state": "IL",
           "country": "USA",
           "zipCode": "62704"
         }
       }
     ],
     "totalPages": 1,
     "totalElements": 1,
     "currentPage": 1
   }
   ```

---

## Key DTOs and Models

### **UserDto**
- Fields:
    - `id` (String)
    - `name` (String)
    - `email` (String)
    - `phone` (String)
    - `address` (Address)

### **Address**
- Fields:
    - `addressLine1` (String) 
    - `addressLine2` (String)
    - `city` (String)
    - `state` (String)
    - `country` (String)
    - `zipCode` (String)

### **PagingAndSortingRequest**
- Fields:
    - `page` (Integer) - Starting page (0-based).
    - `limit` (Integer) - Number of items per page.
    - `sortBy` (String) - Field to sort by.
    - `sortType` (String) - Sort direction (`asc` or `desc`).
    - `fields` (Map<String, Object>) - Dynamic filters.

---

## Setup and Run

### Prerequisites
1. **MongoDB**: Install MongoDB locally or connect to a remote instance.
2. **Java 17**: Ensure Java 17+ is installed.
3. **Maven**: Ensure Maven is installed.

### Steps to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/nikamrahul76/spring-boot-reactive-mongo.git
   cd spring-boot-reactive-mongo
   ```
2. Update MongoDB connection details in `application.yml`:
   ```yaml
   spring:
     data:
       mongodb:
         uri: mongodb://localhost:27017/reactive-db
   ```
3. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. Access the application on `http://localhost:8080`.

---

## Contributing

Contributions are welcome! Feel free to fork the repository, make changes, and submit pull requests.

---

## Author

[**Rahul Nikam**](https://github.com/nikamrahul76)