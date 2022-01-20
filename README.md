# task-notebook-backend
## Demo backend CRUD application that simulates creating and deleting tasks via APIs in Spring Boot.

In order for application to properly function it requires PostgreSQL with a default username __postgres__ and a database named __notebook__. You may also create Docker container as a substitute for Postgres database with a command:
> docker run --name postgres-db-demo -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -e POSTGRES_DB=notebook -p 5432:5432 -d postgres:alpine

If table with name __tbl_task__ is not given, application will create its' own due to property __spring.jpa.hibernate.dll-auto__ being set to __update__.
Configurations are found in __resources__ folder under __application.properties__ file as well as command for table creation in __migration.sql__.
