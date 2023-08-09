# Spring Boot Application with MySQL

This application demonstrates a simple Spring Boot application with a MySQL database backend.

## Features

- CRUD operations for data management.
- Uses MySQL as a persistent datastore.
- Accessible externally via an Ngrok tunnel.
- Swagger documentation for APIs.

## Prerequisites

- Java 11+
- Maven
- Ngrok

## Local Development

1. **Start MySQL**:
   Ensure you have MySQL running on your local machine.

2. **Build the Project**:
   Navigate to the project root and run:
   ```bash
   mvn clean install
   ```

3. **Run the Application**:
   ```bash
   java -jar target/assignment-0.0.1-SNAPSHOT.jar
   ```

4. **Expose your local server with Ngrok**:
   ```bash
   ngrok http 8080
   ```

## External Access

The application is accessible via an Ngrok tunnel. However, the Ngrok link will expire every 2 hours. I will update the link manually as needed.

**Current Link**: [https://f536-2405-201-c038-e8b2-79ba-1b4f-7082-3307.ngrok.io)

> **Note**: This link might be inactive if not updated. Please check back later if you face any accessibility issues.

## Swagger Documentation

To view the API documentation when running locally, navigate to:
```
http://localhost:8080/swagger-ui.html
```

For external access via Ngrok, replace `localhost:8080` with the Ngrok-generated URL.

## Contributing



## License

[MIT](https://choosealicense.com/licenses/mit/)
