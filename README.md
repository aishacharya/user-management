# User Management Project

This project is a Java-Spring Boot based user management system designed to handle user-related operations efficiently.

## Features
- This project is integrated with in-memory H2 database(can be replaced in production),Use of JPA repository helps us to adapt to different databases.
- Swagger docummentation is available at :http://localhost:8080/swagger-ui/index.html  after your application is running.
- APIs are provided for all basic CRUD operations along with an additional findByUsername method.
- Custom exception andling, API validations,Logs and docstrings ,Custom Error API responses and Unit test cases are integrated. 

## Prerequisites

- **Java Development Kit (JDK) 17**: Ensure that JDK 17 is installed on your system.
- **Apache Maven**: Required for building and managing the project dependencies.
- **Docker**: Needed if you plan to run the application within a Docker container.

## Setting Up the Project

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/aishacharya/user-management.git
   ```

2. **Navigate to the Project Directory**:

   ```bash
   cd user-management
   ```

3. **Build the Project with Maven**:

   ```bash
   mvn clean package
   ```

## Running the Application

### On Localhost

1. **Start the Application**:

   ```bash
   java -jar target/user-management-0.0.1-SNAPSHOT.jar
   ```

2. **Access the Application**:

   Open your browser and navigate to `http://localhost:8080` to interact with the application.

### Using Docker

1. **Build the Docker Image**:

   ```bash
   docker build -t user-management:latest .
   ```

2. **Run the Docker Container**:

   ```bash
   docker run -p 8080:8080 user-management:latest
   ```

3. **Access the Application**:

   Open your browser and navigate to `http://localhost:8080` to interact with the application.

## Testing the Application

1. **Run Tests with Maven**:

   ```bash
   mvn test
   ```

2. **View Test Reports**:

   After running the tests, reports will be generated in the `target/surefire-reports` directory.

## Additional Resources

- **Project Repository**: [GitHub - aishacharya/user-management](https://github.com/aishacharya/user-management)

By following these instructions, you should be able to set up, run, and test the User Management project both locally and within a Docker container. 
