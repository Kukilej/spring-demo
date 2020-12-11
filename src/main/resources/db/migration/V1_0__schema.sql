CREATE TABLE IF NOT EXISTS college(
   college_name varchar(50) NOT NULL  PRIMARY KEY,
   state varchar(50) NOT NULL,
   enrollment integer CHECK (enrollment> 0)
);

CREATE TABLE IF NOT EXISTS student(
   id int NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
   student_name varchar(50) NOT NULL,
   gpa numeric(2,1) CHECK (gpa >= 0.0 and gpa <= 4.0),
   highschool_size smallint CHECK (highschool_size>0)
);

CREATE TABLE IF NOT EXISTS apply (
     id int NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
     student_id int NOT NULL,
     college_name varchar(50) NOT NULL,
     major varchar(50) NOT NULL,
     decision char(1) NOT NULL,
     FOREIGN KEY (student_id) REFERENCES student(id),
     FOREIGN KEY (college_name) REFERENCES college(college_name)
);

