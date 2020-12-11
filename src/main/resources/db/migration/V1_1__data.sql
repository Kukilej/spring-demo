    insert into Student (student_name, gpa, highschool_size) values
        ('Amy', 3.9, 1000),
        ('Bob', 3.6, 1500),
        ('Craig', 3.5, 500),
        ('Doris', 3.9, 1000),
        ('Edward', 2.9, 2000),
        ('Fay', 3.8, 200),
        ('Gary', 3.4, 800),
        ('Irene', 3.9, 400),
        ('Helen', 3.7, 800),
        ('Jay', 2.9, 1500),
        ('Amy', 3.9, 1000),
        ('Craig', 3.4, 2000);

    insert into College (college_name, state, enrollment) values
        ('Stanford', 'CA', 15000),
        ('Berkeley', 'CA', 36000),
        ('MIT', 'MA', 10000),
        ('Cornell', 'NY', 21000);

    insert into Apply (student_id, college_name, major, decision) values
        (1, 'Stanford', 'CS', 'Y'),
        (1, 'Stanford', 'EE', 'N'),
        (1, 'Berkeley', 'CS', 'Y'),
        (1, 'Cornell', 'EE', 'Y'),
        (2, 'Berkeley', 'biology', 'N'),
        (3, 'MIT', 'bioengineering', 'Y'),
        (3, 'Cornell', 'bioengineering', 'N'),
        (3, 'Cornell', 'CS', 'Y'),
        (3, 'Cornell', 'EE', 'N'),
        (6, 'Stanford', 'history', 'Y'),
        (8, 'Stanford', 'CS', 'N'),
        (8, 'MIT', 'biology', 'Y'),
        (8, 'MIT', 'marine biology', 'N'),
        (9, 'Stanford', 'CS', 'Y'),
        (9, 'Berkeley', 'CS', 'Y'),
        (10, 'Stanford', 'history', 'Y'),
        (10, 'Cornell', 'history', 'N'),
        (10, 'Cornell', 'psychology', 'Y'),
        (12, 'MIT', 'CS', 'N');