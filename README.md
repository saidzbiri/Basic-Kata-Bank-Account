# Basic Kata Bank Account

This project was created with Spring Boot, use an embedded database (h2) and Swagger.

## Author

Said Z'BIRI

## Getting Started

To run this project, please follow those instructions

### Prerequises

Clone this repository:

```text
    git clone git@github.com:saidzbiri/Basic-Kata-Bank-Account.git
```

Then open the project with the IDE of your choice, Right click on the main class named Application, and then press "run Application".

To execute the unit tests alone:
```text
    mvn test
```


## Documentation

When the server is running you can access the API Swagger at this URL:

```text
    http://localhost:8080/swagger-ui.html
```

## API

With this API, you can register deposit and withdrawal operations on your bank account and print account statement.

When the API is running, an embedded Apache Tomcat Server will be running at :  

```text
    http://localhost:8080/
```  
First of all, you need to get a user token with your credentials (usernameOrEmail: szbiri, password: 2022)

With CURL :
```text
curl -X POST \
  http://localhost:8080/api/auth/signin \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{"usernameOrEmail":"szbiri", "password":"2022"} '
```  

Then you are able to make POST and GET request on the bank-account-api with an Authorization Bearer and the user token.

You can make GET Request at this URL in order to print account statement (testing account is 123):  

```text
    http://localhost:8080/accounts/123
```  

With CURL :  

```CURL
    curl -X GET \
      'http://localhost:8080/accounts/123' \
      -H "accept: */*" \
      -H 'Authorization: Bearer  eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjYzOTc1NTI0LCJleHAiOjE2NjQ1ODAzMjR9.IgKsGjT4zrN0V3Ko-VOBDGzhdPau8X8OmHMhrgcVGCeLSeBl1b61BtLFQo-qCirL9_Vp_ZVW1ZXb1u8dNC5yZQ'
``` 

You can make POST Request at this URL in order to make a deposit or a withdrawal (testing account is 123):  

```text
    http://localhost:8080/operations
```  

You can make GET Request at this URL in order to retrieve your operation history :  

```text
    http://localhost:8080/operations?accountNumber=123&page=0&size=10
```  



