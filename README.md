Major purpose of application is enable to run online sale.  

Program was wrote using Spring(Spring MVC, Spring Security), Hibernate, JUnit, JSP. 
To working requires PostgreSQL and Apache Tomcat.

Before first start you may fill database with default values which are in defaultDatabaseValues.sql script. 
You only need to set property "spring.database.initialize" in dataAccess.properties to true. 
It is also possible to customize pagination mechanism.
You are able to set maximum items displayed on single page and maximum visible number of navigation pages.
These options are in application.properties.
All described files are under src/main/resources.

Video is showing this application in action:
https://youtu.be/7rcxus7pONI
