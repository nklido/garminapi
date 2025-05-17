# Garmin Scraper API
A Spring boot service that scrapes Garmin’s web interface to provide a RESTful API for activity and user data. This project covers:

- **Authorization** via the [garmin-auth-api](https://github.com/your-org/garmin-auth-api).  
- **Weekly training logs** for the authenticated user and their connections.  
- **Activity list** retrieval for the authenticated user and connections.  
- **Connection list** management.  
- **Profile information** access.

## Prerequisites

- Java 17 or higher  
- Gradle 8.0 or higher  
- Internet access (to perform scraping and fetch dependencies)

## Build and run
```sh
   ./gradlew clean build
   ./gradlew bootRun
```

## Environment
Make sure you have [garmin-auth-api](https://github.com/your-org/garmin-auth-api) by default on ::5000

```shell
AUTH_API_URL=http://localhost:5000
```

## Testing

To run all tests 

```shell
./gradlew test
```

And for coverage report

```shell
./gradlew clean test jacocoTestReport
```