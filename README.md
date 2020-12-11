# spring-demo
Spring Boot CRUD demonstrating the use of PostgreSQL db running inside Docker container and Redis as cache (also running inside Docker container) .

To run Redis container: docker run --name my-redis-container -p 6379:6379 -d redis:6.0.9-alpine

To run Docker container: docker run --name my-postgres-container -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 -d postgres:13.1-alpine

Make sure that parameters from above commands align with application.properties file.


I added timeouts of 1sec to GET/{id} endpoints so difference to a call which goes to a database compared to cached content is visible to a human eye.

By calling GET in Postman for some id for the first time you can see time will be ~1sec, compared to a second call for same id which will be approximately 100x faster.


While initial idea was to get familiar with Redis and caching i plan to add following to the project:

1) Add basic security with JWT
2) Add testing and get familiar with Testcontainers library
3) Add some non-trivial SQL queries and get familiar with jOOQ library
4) Add Swagger
5) Pagination and JpaAuditing...
