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
CREATE TABLE IF NOT EXISTS Grades(
	StudentID INT NOT NULL,
    CourseID INT NOT NULL,
    Grade CHAR(25) DEFAULT 'NC',
    Completed Boolean NOT NULL DEFAULT FALSE,
    PRIMARY KEY (StudentID, CourseID)
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
CREATE TABLE IF NOT EXISTS Requisites(
	ThisCourseID INT NOT NULL,
    RequiresCourseID INT NOT NULL,
    PRIMARY KEY (ThisCourseID,RequiresCourseID)
);

-- Student Data
INSERT INTO Students (Name, Password, Major)
VALUES
('Harold Lawrence', 'Password', 'Computer Science'),
('Becky Thomas', 'Password', 'Math'),
('Harold Lawrence', 'Password', 'Mechanical Engineering'),
('James Smith', 'Password', 'Physics'),
('Maria Johnson', 'Password', 'History'),
('Robert Brown', 'Password', 'Buisness'),
('Emily Davis', 'Password', 'Computer Science'),
('Michael Miller', 'Password', 'Computer Science'),
('Sarah Wilson', 'Password', 'Math'),
('William Moore', 'Password', 'Physics'),
('First Last', 'Password', 'Major'),
('Jessica Taylor', 'Password', 'History'),
('David Anderson', 'Password', 'Buisness'),
('Elizabeth Thomas', 'Password', 'Computer Science'),
('Christopher Lee', 'Password', 'Mechanical Engineering');

-- Professor Data
INSERT INTO Professors (Name, Password, Department) 
VALUES 
('Dr. John Smith', 'Password', 'Computer Science'),
('Dr. Emily Johnson', 'Password', 'Mathematics'),
('Dr. Alison Caster', 'Password', 'Buisness'),
('Dr. Sarah Mitchell', 'Password', 'Physics'),
('Dr. William Brown', 'Password', 'History'),
('Dr. Robert Davis', 'Password', 'Mechanical Engineering'),
('Dr. Jessica White', 'Password', 'Computer Science'),
('Dr. David Lee', 'Password', 'Business'),
('Dr. Emily Taylor', 'Password', 'Mathematics'),
('Dr. Michael Clark', 'Password', 'Physics'),
('Dr. Elizabeth Moore', 'Password', 'History'),
('Dr. Christopher Anderson', 'Password', 'Business'),
('Dr. Maria Walker', 'Password', 'Computer Science'),
('Dr. James Harris', 'Password', 'Mechanical Engineering'),
('Dr. Becky Robinson', 'Password', 'Mathematics');

-- Course Data
INSERT INTO Courses (CourseName, CourseMajor, CourseUnits, CourseTitle, CourseDescription) 
VALUES 
('Calculus I', 'Mathematics', 4, 'MATH 101 - Calculus I', 'An introduction to the concepts of calculus, including limits, derivatives, and integrals.'),
('Calculus II', 'Mathematics', 4, 'MATH 102 - Calculus II', 'Covers integration, differential equations, sequences and series, and parametric equations and polar coordinates.'),
('Calculus III', 'Mathematics', 4, 'MATH 203 - Calculus III', 'Covers multivariable calculus, including partial derivatives, multiple integrals, and vector calculus.'),
('Introduction to Computer Science', 'Computer Science', 3, 'CS 101 - Intro to CS', 'An introductory course to computer science and programming.'),
('Data Structures and Algorithms', 'Computer Science', 3, 'CS 201 - Data Structures and Algorithms', 'Focus on data structures, algorithm analysis, and problem-solving strategies.'),
('Introduction to Artificial Intelligence', 'Computer Science', 3, 'CS 301 - Intro to AI', 'Introduction to AI techniques such as machine learning, search algorithms, and robotics.'),
('Physics I', 'Physics', 4, 'PHYS 101 - Mechanics', 'Introduction to classical mechanics, including motion, forces, and energy.'),
('Physics II', 'Physics', 4, 'PHYS 102 - Electricity and Magnetism', 'Introduction to electromagnetism, including electric fields, magnetic fields, and electromagnetic waves.'),
('Physics III', 'Physics', 4, 'PHYS 203 - Modern Physics', 'Covers topics in modern physics, including special relativity, quantum mechanics, and atomic structure.'),
('Thermodynamics', 'Physics', 4, 'PHYS 202 - Thermodynamics', 'Study of the principles of thermodynamics and their applications in various systems.'),
('Quantum Mechanics', 'Physics', 4, 'PHYS 201 - Quantum Mechanics', 'Study of quantum theory, including wave-particle duality and quantum states.'),
('Linear Algebra', 'Mathematics', 3, 'MATH 201 - Linear Algebra', 'Introduction to vectors, matrices, and linear transformations.'),
('Business Management', 'Business', 3, 'BUS 101 - Intro to Business Management', 'An overview of business principles, management practices, and organizational behavior.'),
('History of Modern Europe', 'History', 3, 'HIST 201 - Modern European History', 'Examination of European history from the Renaissance to the present.'),
('Mechanical Engineering Design', 'Mechanical Engineering', 4, 'ME 301 - Mechanical Engineering Design', 'Introduction to the design process, including CAD modeling and prototyping.'),
('Corporate Finance', 'Business', 3, 'BUS 202 - Corporate Finance', 'Introduction to the management of corporate financial resources and capital markets.'),
('American History', 'History', 3, 'HIST 301 - American History', 'Survey of American history from pre-colonial times to the present.'),
('Environmental Science', 'Physics', 3, 'PHYS 301 - Environmental Science', 'Study of environmental systems, energy resources, and sustainability practices.');

-- Requisite Data
INSERT INTO Requisites (ThisCourseID, RequiresCourseID) 
VALUES 
(3, 2),
(2, 1),
(5, 4),
(5, 2),
(6, 3),
(6, 5),
(8, 7),
(9, 8),
(10, 9),
(10, 2),
(11, 9),
(11, 3),
(12, 2),
(14, 17);

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
(5, 1, '11:00:00', '12:30:00', 'Tuesday, Thursday'),
(6, 3, '09:00:00', '10:30:00', 'Monday, Wednesday, Friday'),
(6, 4, '13:00:00', '14:30:00', 'Tuesday, Thursday'),
(7, 5, '08:00:00', '09:30:00', 'Monday, Wednesday, Friday'),
(7, 6, '11:00:00', '12:30:00', 'Tuesday, Thursday'),
(8, 7, '10:00:00', '11:30:00', 'Monday, Wednesday, Friday'),
(8, 8, '13:00:00', '14:30:00', 'Tuesday, Thursday'),
(9, 9, '09:00:00', '10:30:00', 'Monday, Wednesday, Friday'),
(9, 10, '14:00:00', '15:30:00', 'Tuesday, Thursday'),
(10, 11, '08:00:00', '09:30:00', 'Monday, Wednesday, Friday'),
(10, 12, '13:00:00', '14:30:00', 'Tuesday, Thursday'),
(11, 13, '09:00:00', '10:30:00', 'Monday, Wednesday, Friday'),
(11, 14, '13:00:00', '14:30:00', 'Tuesday, Thursday'),
(12, 13, '08:00:00', '09:30:00', 'Monday, Wednesday, Friday'),
(12, 14, '11:00:00', '12:30:00', 'Tuesday, Thursday'),
(13, 15, '10:00:00', '11:30:00', 'Monday, Wednesday, Friday'),
(13, 16, '13:00:00', '14:30:00', 'Tuesday, Thursday'),
(14, 17, '08:00:00', '09:30:00', 'Monday, Wednesday, Friday'),
(14, 18, '11:00:00', '12:30:00', 'Tuesday, Thursday'),
(15, 19, '10:00:00', '11:30:00', 'Monday, Wednesday, Friday'),
(15, 20, '13:00:00', '14:30:00', 'Tuesday, Thursday');

-- Message Data
INSERT INTO Messages (StudentID, ProfessorID, MessageTitle, MessageBody)
VALUES
(1, 1, 'Hello Harold', 'It is nice to meet you!'),
(1, 1, 'Hold On Your Account', 'Hello Harold, we have placed an advising hold on your account stopping you from registering for classes. Please schedule an appointment meeting with your advisior to clear the hold.'),
(2, 6, 'Welcome to Calculus II', 'Becky, welcome to Calculus II. I look forward to a great semester together!'),
(3, 9, 'Your Course Enrollment', 'Harold, you are enrolled in Mechanical Engineering this semester. Please ensure you are prepared for your courses.'),
(4, 12, 'Physics I - Lab Schedule', 'James, please be aware that your Physics I lab schedule is available. Let me know if you have any questions.'),
(5, 10, 'History Paper Due', 'Maria, just a reminder that your history paper is due next week. Please start working on it ahead of time.'),
(6, 8, 'Business Course Materials', 'Robert, the textbooks for your Business course have been uploaded to the course portal. Be sure to check them out.'),
(7, 14, 'Computer Science Office Hours', 'Emily, my office hours are available for any questions you may have regarding Computer Science. Please stop by!'),
(8, 7, 'CS 101 - Midterm Results', 'Michael, your results for the midterm exam in CS 101 are posted. Please check the portal for your grade.'),
(9, 5, 'Mathematics Tutoring', 'Sarah, we have tutoring available in the Mathematics department. Feel free to use this resource if needed!'),
(10, 13, 'Physics II Assignment', 'William, please ensure that you submit your Physics II assignment by Friday. Let me know if you need any help.'),
(11, 2, 'History Exam Preparation', 'First, as your History exam approaches, make sure to review the course material. Reach out if you need assistance.'),
(12, 4, 'Business Course Feedback', 'Jessica, I wanted to give you feedback on your performance in the Business course. You’re doing well! Keep it up.'),
(13, 11, 'Computer Science - Final Project', 'David, the final project for your Computer Science course will be assigned soon. Start preparing now!'),
(14, 3, 'Mechanical Engineering Design Meeting', 'Elizabeth, our next Mechanical Engineering design meeting is on Thursday. Please come prepared with your project updates.');