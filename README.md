
# Nuts Subscriber (Java)

This project is a simple Java application that simulates a message subscription service. It generates test messages at intervals and stores them in a PostgreSQL database using JDBC.

## Features

- Simulates continuous message generation
- Saves each message to a PostgreSQL table
- Uses Maven for project management
- PostgreSQL runs via Docker
- Database table is created via schema.sql

## Technologies

- Java 17
- Maven
- PostgreSQL 15
- JDBC
- Docker & Docker Compose

## Database Setup

### 1. Start PostgreSQL with Docker

```bash
docker-compose up -d
```

This runs a PostgreSQL container with exposed port `5432`.

### 2. Create the `messages` table

```bash
docker cp schema.sql postgres_nuts:/schema.sql
docker exec -it postgres_nuts psql -U postgres -d nutsdb -f /schema.sql
```

If the database `nutsdb` does not exist, create it with:

```bash
docker exec -it postgres_nuts psql -U postgres -c "CREATE DATABASE nutsdb;"
```

##  Running the Application

From the project root:

```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.nuts.Main"
```

This starts the message simulation and stores messages into the database every second.

## Notes

- The database credentials and connection string are set in `DBConnection.java`.
- Ensure Docker is running and port `5432` is not blocked.
- If using a different container name, update `postgres_nuts` in your terminal commands.

## Files

- `schema.sql` – SQL script to create the `messages` table
- `docker-compose.yml` – PostgreSQL container setup
- `pom.xml` – Maven build configuration
- `src/main/java/com/nuts/*.java` – Application source code
