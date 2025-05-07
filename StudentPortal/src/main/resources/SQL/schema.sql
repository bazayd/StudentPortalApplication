CREATE SCHEMA IF NOT EXISTS `student_portal`;

USE student_portal;

CREATE TABLE IF NOT EXISTS Students(
	StudentID INT NOT NULL UNIQUE AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Major VARCHAR(255) DEFAULT 'Undeclared',
    Hold Boolean DEFAULT FALSE,
    PRIMARY KEY (StudentID)
);
CREATE TABLE IF NOT EXISTS Professors(
	ProfessorID INT NOT NULL UNIQUE AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Department VARCHAR(255),
    PRIMARY KEY (ProfessorID)
);
CREATE TABLE IF NOT EXISTS Courses(
    CourseID INT NOT NULL UNIQUE AUTO_INCREMENT,
	CourseName VARCHAR(255) NOT NULL,
    CourseMajor VARCHAR(255) NOT NULL,
    CourseUnits SMALLINT,
    CourseTitle VARCHAR(255),
    CourseDescription VARCHAR(500),
    PRIMARY KEY (CourseID)
);
CREATE TABLE IF NOT EXISTS Sections(
	SectionID INT NOT NULL UNIQUE AUTO_INCREMENT,
    ProfessorID INT NOT NULL,
    CourseID INT NOT NULL,
    StartTime TIME,
    EndTime TIME,
    DaysOfWeek VARCHAR(255),
    PRIMARY KEY (SectionID)
);
CREATE TABLE IF NOT EXISTS Enrollment(
	SectionID INT NOT NULL,
	StudentID INT NOT NULL,
	Term VARCHAR(255),
    PRIMARY KEY (SectionID, StudentID)
);
CREATE TABLE IF NOT EXISTS Messages(
	MessageID INT NOT NULL UNIQUE AUTO_INCREMENT,
	StudentID INT NOT NULL,
    ProfessorID INT NOT NULL,
	MessageTitle VARCHAR(255) NOT NULL,
    MessageDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    MessageBody LONGTEXT,
    PRIMARY KEY (MessageID)
);
CREATE TABLE IF NOT EXISTS Grades(
	StudentID INT NOT NULL,
    SectionID INT NOT NULL,
    Grade CHAR(25) DEFAULT 'NC',
    Units SMALLINT NOT NULL,
    Completed Boolean NOT NULL DEFAULT FALSE,
    PRIMARY KEY (StudentID, SectionID)
);
CREATE TABLE IF NOT EXISTS Requisites(
	ThisCourseID INT NOT NULL,
    RequiresCourseID INT NOT NULL,
    PRIMARY KEY (ThisCourseID,RequiresCourseID)
);


-- Professor Data
INSERT INTO Professors (Name, Password, Department) 
VALUES 
('Dr. John Smith', 'asdf', 'Computer Science'),
('Dr. Emily Johnson', 'asdf', 'Mathematics');

-- Course Data
INSERT INTO Courses (CourseName, CourseMajor, CourseUnits, CourseTitle, CourseDescription) 
VALUES 
('Introduction to Computer Science', 'Computer Science', 3, 'CS 101 - Intro to CS', 'An introductory course to computer science and programming.'),
('Calculus I', 'Mathematics', 4, 'MATH 101 - Calculus I', 'An introduction to the concepts of calculus, including limits, derivatives, and integrals.'),
('Physics I', 'Physics', 4, 'PHYS 101 - Mechanics', 'Introduction to classical mechanics, including motion, forces, and energy.'),
('Introduction to Psychology', 'Psychology', 3, 'PSY 101 - Intro to Psychology', 'Basic principles of psychology, including human behavior, cognition, and emotion.'),
('Calculus II', 'Mathematics', 4, 'MATH 102 - Calculus II', 'Like Calculus I, but sillier.');

-- Requisite Data
INSERT INTO Requisites (ThisCourseID, RequiresCourseID) 
VALUES 
(5, 1);

-- Section Data
INSERT INTO Sections (CourseID, ProfessorID, StartTime, EndTime, DaysOfWeek) 
VALUES 
(1, 2, '09:00:00', '10:30:00', 'Monday, Wednesday, Friday'),
(1, 2, '11:00:00', '12:30:00', 'Tuesday, Thursday'),
(2, 1, '08:00:00', '09:30:00', 'Monday, Wednesday, Friday'),
(2, 1, '14:00:00', '15:30:00', 'Tuesday, Thursday'),
(3, 2, '10:00:00', '11:30:00', 'Monday, Wednesday, Friday'),
(3, 2, '13:00:00', '14:30:00', 'Tuesday, Thursday'),
(4, 1, '09:00:00', '10:30:00', 'Monday, Wednesday, Friday'),
(4, 2, '11:00:00', '12:30:00', 'Tuesday, Thursday'),
(5, 1, '11:00:00', '12:30:00', 'Tuesday, Thursday');

-- Student Data
INSERT INTO Students (Name, Password, Major)
VALUES
('Harold Lawrence', 'asdf', 'Computer Science');

-- Message Data
INSERT INTO Messages (StudentID, ProfessorID, MessageTitle, MessageBody)
VALUES
(1, 1, 'Hello Harold', 'It is nice to meet you Harold.'),
(1, 1, 'Hold On Your Account', 'Hello Harold, we have placed an advising hold on your account stopping you from registering for classes. Please schedule an appointment meeting with your advisior to clear the hold.');

-- Grades Data
INSERT INTO Grades (StudentID, SectionID, Grade, Units)
VALUES
(1, 1, 'A', 3),
(2, 2, 'C', 2),
(1, 3, 'B', 3),
(1, 4, 'A-', 3),
(2, 4, 'F', 3),
(2, 3, 'B', 3);