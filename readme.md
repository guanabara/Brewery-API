# Brewery-API 🍺
[![Build Status](https://travis-ci.com/FonsecaGoncalo/Brewery-API.svg?token=pVvRG83cyxhvyBNvooAM&branch=main)](https://travis-ci.com/FonsecaGoncalo/Brewery-API)

Brewery-API uses [BreweryDB](https://www.openbrewerydb.org/)  to obtain information about breweries. It allows to:

 - Get a Single Brewery by id
 - List Breweries 
 - Search Breweries
 
 You can check the [Swagger](https://brewery.gfonseca.io/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config) for more information about each endpoint.

Brewery-API uses the [OAuth 2.0 protocol](https://tools.ietf.org/html/rfc6749) (client credentials grant) for authentication and authorization. It's using [okta](https://developer.okta.com/docs/concepts/auth-servers/) as the authorization server.

The application is deployed using [AWS Elastic Beanstalk](https://aws.amazon.com/pt/elasticbeanstalk/) under the domain https://brewery.gfonseca.io.

## Tests
- Units tests
- Functional tests with [Wiremock](http://wiremock.org/)
## Running Local

### Prerequisites
 - Java 11
 - Maven
 - Docker

Build Jar
```sh
mvn clean intall
```
Build Docker Image
```sh
docker build . -t brewery-api
```

Run Application
```sh
docker run -p 8080:80 \
  -e CLIENT_ID=<CLIENT_ID> \
  -e CLIENT_SECRET=<CLIENT_SECRET> \
  brewery-api
```
## Authentication

To get an access token you need to make a request to the authorization server:
```sh
POST /oauth2/default/v1/token HTTP/1.1
Host: dev-8089486.okta.com
Accept: application/json
Content-Type: application/x-www-form-urlencoded
Authorization: Basic base64(<CLIENT_ID>:<CLIENT_SECRET>)

grant_type=client_credentials&scope=read
```
The authorization server will respond with the following payload:
```sh
{
    "token_type": "Bearer",
    "expires_in": 600,
    "access_token": <TOKEN>,
    "scope": "read"
}
```

Then you need to add an `Authorization` header with token to all requests to the Brewery-API

```sh
GET /breweries/530 HTTP/1.1
Host: brewery.gfonseca.io
Authorization: Bearer <TOKEN>
```

## Postman
To more easily test the api you can import the [postman collection](postman/Brewery.postman_collection.json) into postman and define the variables {{client_id}} and {{client_secret}}. This collection, after the `Get Token` request automatically adds the token to the other requests.



## License
[MIT](https://choosealicense.com/licenses/mit/)
