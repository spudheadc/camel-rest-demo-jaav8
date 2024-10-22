[![Java](https://img.shields.io/badge/Java-%23ED8B00.svg?logo=openjdk&logoColor=white)](#)
# Camel REST Demo - JAVA 8

This is a sample project of a Apache Camel REST service. This example uses [REST DSL with contract-first OpenAPI](https://camel.apache.org/manual/rest-dsl-openapi.html) to demonstrate how we can do a low code example of a REST enpoint. Furthermore is domenstrates how with a camel choice component we can use the same api to expose 2 different implementations.

It also demonstrates the use of MapStruct to map between disparate beans.

This project build java 8 compatable source

## Installation

Download the project and run gradlew

```bash
./gradlew build
```

## Usage

The key classes are
- RestApi.java which is a CamelRouteBuilder
- ComplexMapper.java which is a MapStruct mapper
- IComplexService.java and ComplexServiceImpl.java which are examples of a complex implementation of the functionality
- ISimpleService.java and SimpleServiceImpl.java which are examples of a more simplistic implementation of the functionality
- swagger.yml which is an OpenApi 3.0 descripition of the api

This project also uses the org.openapi.generator plugin for Gradle to build the model classes for the project

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

[MIT](https://choosealicense.com/licenses/mit/)