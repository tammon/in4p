# Manufacturing Data Collection Server

Industry 4.0 or the Industrial Internet is the notion of using every single bit that we learned in the IT World to improve productivity in the industrial sector. The MDC (Manufacturing Data Collection) is a server application that is able to collect any kind of data in the manufacturing process and save it into a fast and scalable database. The data can be accessed via an REST API or in a visualization in an HTML document.

## Background
This was initiated as a student research project of Tammo Schwindt at the [DHBW (Cooperative State University) in Mosbach](http://dhbw-mosbach.de). The project shows a first concept of big data collection and organizing a product file for tracking products during the manufacturing process. This data should be available to intenal systems for further usage and customers for detailed information about their product. The concept is orientated to the Industry 4.0 Lab at the DHBW Mosbach for the first concept and can be generalized in a later development.

## Technologies
The MDC-Server uses advanced cross-platform technologies to ensure a scalable architecture
* Java Container: Apache Tomcat
* Depedency Management: Maven
* Framework: Spring.io (for specific projects see pom.xml)
* Database: MongoDB
* Loggin: log4j
* Template Engine: thymeleaf