# TOFFEE
TOFFEE is an open-source project for developing a traffic engineering optimization framework for extending the network resources through intelligent and automated techniques. It provides a set of tools and APIs for managing network traffic flows, optimizing network resource utilization, and improving the overall network performance.

## Table of Contents
Installation
Prerequisites
Downloading the source code
Building the project
Usage
Running the application
Configuring the application
Using the APIs
Contributing
License
Installation
Prerequisites
Before you start using TOFFEE, make sure you have the following software installed on your system:

## Java Development Kit (JDK) 8 or higher
Git
Gradle
Downloading the source code
To download the source code of TOFFEE, you can clone the repository from GitHub using the following command:

## Downloading the source code
Once you have downloaded the source code, you can build the project using Gradle. To build the project, navigate to the root directory of the project and run the following command:
``` git clone  https://github.com/abdallhfattah/TOFFEE.git```

## Building the project
```./gradlew build ```
This will build the project and generate a JAR file that you can use to run the application.

# Usage
## Running the application
To run the TOFFEE application, navigate to the root directory of the project and run the following command:
```java -jar build/libs/toffee.jar```

## Starting application
This will start the application, and you can access it by opening a web browser and navigating to here http://localhost:8080/ .

## Configuring the application
You can configure the TOFFEE application by editing the application.properties file, which is located in the src/main/resources directory. This file contains various configuration settings, such as the server port, database settings, and logging settings.

## Using the APIs
TOFFEE provides a set of RESTful APIs that you can use to manage network traffic flows and optimize network resource utilization. You can access the APIs by sending HTTP requests to the appropriate endpoints. The API documentation is available at http://localhost:8080/swagger-ui.html.

## License
TOFFEE is released under the MIT License. See the LICENSE file for more details.
