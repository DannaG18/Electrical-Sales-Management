# Electrical Sales Management System

## Overview
The **Electrical Sales Management System** is a Java-based application designed to streamline the operations of an electrical sales business. This application integrates user authentication, role management, and CRUD functionalities for key business entities, providing an efficient and user-friendly system for managing daily operations.

## Features

### 1. User Authentication and Role Management
- Secure login system for authenticating users.
- Role-based access control to manage permissions for different users.

### 2. CRUD Operations
- Create, Read, Update, and Delete functionalities for:
  - Addresses
  - Branches
  - Product Categories

### 3. Database Integration
- MySQL database integration for reliable data storage and retrieval.

### 4. Graphical User Interface
- Developed using Java Swing to provide an intuitive and responsive user experience.

## Technologies Used
- **Programming Language**: Java (JDK 17)
- **Framework**: Maven (for dependency management)
- **Database**: MySQL
- **UI Framework**: Java Swing

## Project Structure
```
Electrical Sales Management
|
|-- pom.xml                # Maven configuration file
|-- src
    |-- main
        |-- java
            |-- com.esms
                |-- Main.java                           # Entry point of the application
                |-- login
                    |-- infrastructure
                        |-- controller
                            |-- LoginController.java    # Handles login UI and logic
                    |-- application
                        |-- LoginAuthenticationUseCase # Business logic for login
                |-- address
                    |-- application                    # Use cases for address management
                    |-- domain                         # Entity and service classes for address
                    |-- infrastructure                 # Repository and adapter for address
                |-- branch
                    |-- application                    # Use cases for branch management
                    |-- domain                         # Entity and service classes for branch
                    |-- infrastructure                 # Repository and adapter for branch
                |-- category
                    |-- application                    # Use cases for category management
```

## How to Run

### Prerequisites
1. Install Java Development Kit (JDK 17).
2. Install Maven.
3. Install MySQL and set up the database using the provided schema (see `database/schema.sql`).

### Steps
1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```bash
   cd Electrical Sales Management
   ```
3. Build the project using Maven:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   java -jar target/electricalsalesmg-1.0-SNAPSHOT.jar
   ```

## Database Configuration
Update the `application.properties` file (or equivalent configuration) with your MySQL credentials:
```properties
db.url=jdbc:mysql://localhost:3306/electrical_sales
db.username=your-username
db.password=your-password
```

## Contribution Guidelines
1. Fork the repository and create a feature branch.
2. Follow proper naming conventions for commits and branches.
3. Submit a pull request for review.

## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Contact
For any inquiries or support, please contact:
- **Name**: Danna Alvarez
- **Email**: dg.alvarezr@gmail.com
- **LinkedIn**: [in/danna-alvarez-9794611a5](in/danna-alvarez-9794611a5)