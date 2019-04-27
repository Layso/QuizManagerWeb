# QuizManagerWeb
Project for Advanced Java lecture on EPITA. This is the continuation lecture of Java Fundemantals. Project is similar to the first one but this time as a web service to understand basics of JEE. Learning Maven and Angular to build a web project was the aim of the course. Core java projects are continuation of course works, can be found here: https://github.com/thomasbroussard/2019s1adv

# Setting Up
There are some preconditions to be followed to run the program without problems


### Database
Program uses H2 database to store and fetch data

### applicationContext.xml
Edit H2 database credentials under the file applicationContext.xml for 'quiz-rest-services' project

### Servlet base URL
Default URL for servlet should be http://localhost:8080/quiz-rest-services/rest/quiz/. In case it is different (port or path change) it should be set from the main web page. (More instructions can be found on web page itself)

### node_modules generation
node_modules folder under the Angular project folder is not included in this repository. It can be generated with 'npm install' command

### Chrome Web Security
Chrome did not let me run my app smoothly. I needed to use following command to run Chrome in order to let app run: 'chrome.exe --user-data-dir="C:/Chrome dev session" --disable-web-security'

# Running
- Download the project files
- Setup and open H2 database
- applicationContext.xml should be ready at this point
- Run Maven project 'quiz-rest-services' on server (Apache Tomcat is recommended to avoid some problems)
- node_modules should be ready at this point
- Run command 'ng serve' in root folder to run the Angular project
- Navigate to localhost:4200 from browser
- All other instructions can be found on main web page for Angular project
