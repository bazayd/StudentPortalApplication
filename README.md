# Student Portal Application 

## Project Overview 
The **Student Portal Application** is developed with the purpose of being a crucial tool for students 
and professors alike. The purpose was to have students be able to choose and register for their desired course
according to their specific major and plan. This tool would also allow professor tools such as viewing all
student and courses information while giving permissions to change and submit grades. 
The primary objective of the student portal application was to develop multiple subtools within the application
such as course registration, messagin features, and professor management. Other primary objectives included:
- Having fully functional and relevant database tables
- Structured pages and presentation for all features
- Cross referncing and joining tables with information for seamless data retrieval

We developed this application within a three-layer architecture that incldued Presentation, Logic, and Database layers.
For our tools, we utilized the Spring Boot Framework, Java, HTML, CSS, JavaScript, JDBC, MySQL, and Maven.

# Project Setup and Requirements

Before setting up the project, ensure that the following are installed in your sy
stem and in your 
Environmental variables within the PATH in System Variables:
- **Java Development Kit (JDK)**
-  **Maven** (Dependency Management)
-  **MySQL Server**

### Step 1: Clone the Repository 
git clone https://github.com/bazayd/StudentPortalApplication.git

### Step 2: Configure the Database
Once MySQL Server is installed and setup within your system, it would be to crucial to set up within your system.
Within the terminal start up your server as such:
``` mysql -u root -p ```

Then, copy the contents of the create_schema.sql file within the application to initialize the student portal
schema and all desired tables and insertions.

### Step 3: Build the Project
Once Maven is installed and configured in your system, you can head to the pom.xml file within the application and
right click -> Maven -> Sync Project

You can also run the comman in the terminal: ``` mvn clean install ```

### Step 4: Deploy the Application
The final step is to deploy and run the application, which only requires a simple navigation to the 
**StudentPortalApplication.java** file and running said file. 
Within a IDE like VSCode you can open the Command Palette (Ctrl + Shift + P) and select **Java: Run Main Class**

### Step 5: Running the Application
Within the application you can use the default credentials as following for Students and Professors in order to 
test functionality:
**StudentID: 1, Password: Password**
**ProfessorID:1, Password: Password**
