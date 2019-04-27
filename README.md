# QuizManagerWeb
Project for Advanced Java lecture on EPITA


Download the project files
Setup and open H2 database
Edit H2 database credentials under the file applicationContext.xml for 'quiz-rest-services' project
Run Maven project 'quiz-rest-services' on server (Apache Tomcat is recommended to avoid some problems)
Navigate to 'quiz-app' Angular project root from command line and execute 'npm install' to produce node_modules folder
Run command 'ng serve' in root folder to run the Angular project
Navigate to localhost:4200 from browser 
Chrome used with following arguments to allow app to run:
chrome.exe --user-data-dir="C:/Chrome dev session" --disable-web-security
