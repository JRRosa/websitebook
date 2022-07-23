INSERT INTO 
    dbo.Author (name) 
VALUES 
     ('Henry David Thoreau')
    ,('Ralph Waldo Emerson')
    ,('Lillian Eichler Watson')
    ,('Eric Freeman')
    ,('Bert Bates')
    ,('Kathy Sierra')
    ,('Elisabeth Robson')
    ,('Joshua Bloch'); 

SELECT * FROM dbo.Author;

INSERT INTO 
    dbo.Publisher (name) 
VALUES 
    ('Wilder Publications')
    ,('Dover Publications')
    ,('Touchstone')
    ,('O''Reilly Media')
    ,('Prentice Hall');

SELECT * FROM dbo.Publisher;

INSERT INTO 
    dbo.Book (title, publication_year, publisher_id, book_genre_id, amazon_rating, kid_friendly_status) 
VALUES 
    ('Walden',1854, 1, 6, 4.3, 2)
    ,('Self-Reliance and Other Essays', 1993, 2, 6, 4.5, 2)
    ,('LightFrom Many Lamps', 1988, 3, 6, 5.0, 2)
    ,('HeadFirst Design Patterns', 2004, 4, 10, 4.5, 2)
    ,('Effective Java Programming Language Guide', 2007,5, 10, 4.9, 2);

SELECT * FROM dbo.Book;

INSERT INTO 
    dbo.Book_Author (book_id, author_id) 
VALUES
    (1, 1)
    ,(2, 2)
    ,(3, 3)
    ,(4, 4)
    ,(4, 5)
    ,(4, 6)
    ,(4,7)
    ,(5, 8);

SELECT * FROM dbo.Book_Author;

INSERT INTO 
    dbo.Actor (name) 
VALUES 
    ('Orson Welles')
    ,('Joseph Cotten')
    ,('Henry Fonda')
    ,('Jane Darwell')
    ,('Albert Cullum')
    ,('Kaley Cuoco')
    ,('Jim Parsons')
    ,('Takashi Shimura')
    ,('Minoru Chiaki');

SELECT * FROM dbo.Actor;

INSERT INTO 
    dbo.Director (name) 
VALUES 
    ('Orson Welles')
    ,('John Ford')
    ,('Leslie Sullivan')
    ,('Chuck Lorre')
    ,('Bill Prady')
    ,('Akira Kurosawa');

SELECT * FROM dbo.Director;

INSERT INTO 
    dbo.Movie (title ,release_year ,movie_genre_id, imdb_rating, kid_friendly_status) 
VALUES 
    ('Citizen Kane', 1941, 0, 8.5,2)
    ,('The Grapes of Wrath', 1940,0, 8.2, 2)
    ,('A Touch of Greatness', 2004, 24, 7.3, 2)
    ,('The Big Bang Theory', 2007, 20, 8.7, 2)
    ,('Ikiru', 1952, 25, 8.4, 2);

SELECT * FROM dbo.Movie;

INSERT INTO 
    dbo.Movie_Actor (movie_id, actor_id) 
VALUES
    (1, 1), (1, 2), (2, 3), (2, 4), (3, 5), (4, 6), (4,7), (5, 8), (5, 9);

SELECT * FROM dbo.Movie_Actor;

INSERT INTO 
    dbo.Movie_Director(movie_id, director_id)
VALUES 
    (1, 1), (2, 2), (3, 3), (4, 4), (4, 5), (5,6);

SELECT * FROM dbo.Movie_Director;

INSERT INTO 
    dbo.WebLink (title, url, host ,kid_friendly_status) 
VALUES 
    ('Use Final Liberally','http://www.javapractices.com/topic/TopicAction.do?Id=23', 'http://www.javapractices.com', 2)
    ,('How do I import a pre-existing Java project into Eclipse and get up and running?','https://stackoverflow.com/questions/142863/how-do-i-import-a-pre-existing-java-project-into-eclipse-and-get-up-and-running', 'http://stackoverflow.com', 2)
    ,('Interface vs Abstract Class','http://mindprod.com/jgloss/interfacevsabstract.html', 'http://mindprod.com', 2)
    ,('NIO tutorial by Greg Travis','http://cs.brown.edu/courses/cs161/papers/j-nio-ltr.pdf', 'http://cs.brown.edu', 2)
    ,('Virtual Hosting and Tomcat', 'http://tomcat.apache.org/tomcat-6.0-doc/virtual-hosting-howto.html', 'http://tomcat.apache.org', 2);

SELECT * FROM dbo.WebLink;

INSERT INTO 
    dbo.User (email, password, first_name,last_name, gender_id, user_type_id)
VALUES 
    ('user0@semanticsquare.com', 'test', 'John','M', 0, 0)
    ,('user1@semanticsquare.com', 'test', 'Sam','M', 0, 0)
    ,('user2@semanticsquare.com', 'test', 'Anita','M', 1, 1)
    ,('user3@semanticsquare.com', 'test', 'Sara','M', 1, 1)
    ,('user4@semanticsquare.com', 'test', 'Dheeru', 'M', 0, 2);
    
SELECT * FROM dbo.User;
