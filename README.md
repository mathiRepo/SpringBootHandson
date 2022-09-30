# SpringBootHandson
This repo  has code when practised with udemy java guide
# Read Me First
The following was discovered as part of building this project:

* The original package name 'net.javaguides.springboot-rest-api' is invalid and this project uses 'net.javaguides.springbootrestapi' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.0-M5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.0-M5/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.0-M5/reference/htmlsingle/#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
********************************************Notes*****************************************
1)Difference Between @Pathvariable vs @RequestParam
@Pathvariable   : To bind path URI TEMPLATE
@RequestParam   : To bind query parameter to the function

![img.png](img.png)

@ResquestBody Annotation internally uses spring provided HttpMessageConverter to convert JSON to java Object

@PostMapping : Return status as 200 but You have to change to 201 by using @ResponseStatus(HttpStatus.Created)

Blogging while building Blog Application:
![img_1.png](img_1.png)
