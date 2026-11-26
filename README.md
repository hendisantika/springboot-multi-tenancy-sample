# Spring Boot Multi-Tenancy Sample

[![Java CI with Maven](https://github.com/hendisantika/springboot-multi-tenancy-sample/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/springboot-multi-tenancy-sample/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.x-brightgreen?logo=springboot)
![License](https://img.shields.io/badge/License-MIT-blue)

This application demonstrates multi-tenancy in a Spring Boot app
using a discriminator field (`tenant_id`) with Hibernate filters.

## Tech Stack

| Component       | Version                          |
|-----------------|----------------------------------|
| Java            | 25 (Temurin / Corretto)          |
| Spring Boot     | 4.x                              |
| Hibernate ORM   | 7.x (managed by Spring Boot)     |
| Build tool      | Maven 3.9.x (via Maven Wrapper)  |
| Database        | PostgreSQL (runtime), H2 (tests) |
| Spring AOP      | `spring-boot-starter-aspectj`    |

## Requirements

* JDK 25
* PostgreSQL (for running the app)
* No local Maven needed — use the bundled `./mvnw`

## Usage

Create the database and run the app:

```sh-session
$ createdb spring-multi-tenancy
$ ./mvnw clean spring-boot:run
```

Database connection settings live in `src/main/resources/application.properties`.

From another terminal:

```sh-session
$ curl localhost:8080/employees -H "X-TenantID: tenant1"
[{"id":"16b5308b-6bb8-4a75-ae93-66dc71a0b981","firstName":"John","lastName":"Doe","tenantId":"tenant1"}]

$ curl localhost:8080/employees -H "X-TenantID: tenant3"
[]

$ curl localhost:8080/employees -H "X-TenantID: tenant3" -H "Content-Type: application/json" -X POST -d '{"firstName":"Kakashi","lastName":"Hatake"}'
{"id":"c77ad6bb-b2ad-47f7-b21c-3e50deb6a574","firstName":"Kakashi","lastName":"Hatake","tenantId":"tenant3"}

$ curl localhost:8080/employees -H "X-TenantID: tenant3"
[{"id":"c77ad6bb-b2ad-47f7-b21c-3e50deb6a574","firstName":"Kakashi","lastName":"Hatake","tenantId":"tenant3"}]
```

Requests without the `X-TenantID` header are rejected with `400 Bad Request`.

## Build & Test

```sh-session
$ ./mvnw clean verify
```

Tests run against an in-memory H2 database (`src/test/resources/application.properties`),
so no PostgreSQL instance is required for the build or CI.

## Detailed Description

* Uses a Hibernate filter to limit the query results based on tenant.
* Uses Hibernate interceptors to enforce tenant details during creating/updating entities.
* Uses Spring AOP (AspectJ) to set the filter parameters.
* Each request goes through a custom servlet filter (`TenantFilter`) which checks for the `X-TenantID` HTTP header and
  sets it in a `ThreadLocal` variable using the `TenantContext` class. If the header is not present, the request is
  rejected.
* The controller routes the request to the service class, and Spring AOP (`EmployeeServiceAspect`) intercepts the
  service call and enables the Hibernate tenant filter.
* All service methods have to be annotated with `@Transactional` for `EmployeeServiceAspect` to work.
* The filter approach only works for read queries; for write queries, Hibernate interceptors are needed.
* Entity classes should implement the `TenantSupport` interface for the entity interceptor to work.

## CI/CD & Dependency Updates

* **GitHub Actions** (`.github/workflows/maven.yml`) builds and tests the project on JDK 25 for every push and pull
  request to `master`, and submits the Maven dependency graph to GitHub.
* **Dependabot** (`.github/dependabot.yml`) checks Maven dependencies and GitHub Actions weekly (Monday 05:00
  Asia/Jakarta), groups related updates (Spring, database drivers, Lombok, Maven plugins, Actions) and uses
  conventional commit prefixes (`chore(deps)`, `ci(deps)`). Major Spring Boot upgrades are ignored and should be
  migrated manually.

## More Info

* https://hibernate.atlassian.net/browse/HHH-6054
* https://docs.hibernate.org/orm/7.0/userguide/html_single/#multitenacy
* https://docs.hibernate.org/orm/7.0/userguide/html_single/#pc-filter
* https://docs.hibernate.org/orm/7.0/userguide/html_single/#events
* https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-4.0-Migration-Guide

## Other Examples

* https://github.com/ramsrib/multi-tenant-app-demo
* https://github.com/spring-labs/tenancy-sample

## License

MIT
