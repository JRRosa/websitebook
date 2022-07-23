CREATE TABLE IF NOT EXISTS dbo.Author (
    id bigserial
    ,name VARCHAR(200) NOT NULL
    ,PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS dbo.Publisher (
     id bigserial
    ,name VARCHAR(200) NOT NULL
    ,PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS dbo.Book (
     id bigserial
    ,title VARCHAR(500) NOT NULL
    ,publication_year int
    ,publisher_id bigint NOT NULL REFERENCES dbo.publisher (id)
    ,book_genre_id smallint
    ,amazon_rating double precision
    ,kid_friendly_status smallint
    ,created_date timestamp not null default now()
    ,PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS dbo.Book_Author (
    id bigserial
    ,book_id bigint NOT NULL REFERENCES dbo.Book (id)
    ,author_id bigint NOT NULL REFERENCES dbo.Author (id)
    ,PRIMARY KEY (id)
    ,UNIQUE (book_id, author_id)
);

CREATE TABLE IF NOT EXISTS dbo.Actor (
    id bigserial
    ,name VARCHAR(200) NOT NULL
    ,PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS dbo.Director (
    id bigserial
    ,name VARCHAR(200) NOT NULL
    ,PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS dbo.Movie (
    id bigserial
    ,title VARCHAR(500) NOT NULL
    ,release_year integer
    ,movie_genre_id smallint
    ,imdb_rating double precision
    ,kid_friendly_status smallint
    ,created_date timestamp not null default now()
    ,PRIMARY KEY (id)
    ,UNIQUE (title, release_year)
);

CREATE TABLE IF NOT EXISTS dbo.Movie_Actor (
    id bigserial
    ,movie_id bigint NOT NULL REFERENCES dbo.Movie (id)
    ,actor_id bigint NOT NULL REFERENCES dbo.Actor (id)
    ,UNIQUE (movie_id, actor_id)
);

CREATE TABLE IF NOT EXISTS dbo.Movie_Director (
    id bigserial
    ,movie_id bigint NOT NULL REFERENCES dbo.Movie (id)
    ,director_id bigint NOT NULL REFERENCES dbo.Director (id)
    ,UNIQUE (movie_id, director_id)
);

CREATE TABLE IF NOT EXISTS dbo.WebLink (
    id bigserial
    ,title VARCHAR(500) NOT NULL
    ,url VARCHAR(250) NOT NULL
    ,host VARCHAR(250) NOT NULL
    ,kid_friendly_status smallint
    ,created_date timestamp not null default now()
    ,PRIMARY KEY (id)
    ,UNIQUE (url)
);

CREATE TABLE IF NOT EXISTS dbo.User (
    id bigserial
    ,email VARCHAR(200) NOT NULL
    ,password VARCHAR(200) NOT NULL
    ,first_name VARCHAR(200) NOT NULL
    ,last_name VARCHAR(200) NOT NULL
    ,gender_id smallint
    ,user_type_id smallint
    ,created_date timestamp not null default now()
    ,PRIMARY KEY (id)
    ,UNIQUE (email)
);

--
CREATE TABLE IF NOT EXISTS dbo.User_Book (
    id bigserial
    ,user_id bigint NOT NULL REFERENCES dbo.User (id)
    ,book_id bigint NOT NULL REFERENCES dbo.Book (id)
    ,PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS dbo.User_Movie (
    id bigserial
    ,user_id bigint NOT NULL REFERENCES dbo.User (id)
    ,movie_id bigint NOT NULL REFERENCES dbo.Movie (id)
    ,PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS dbo.User_WebLink (
    id bigserial
    ,user_id bigint NOT NULL REFERENCES dbo.User (id)
    ,web_link_id bigint NOT NULL REFERENCES dbo.WebLink (id)
    ,PRIMARY KEY (id)
);

--

ALTER TABLE dbo.Book 
ADD COLUMN kid_friendly_marked_by bigint NOT NULL REFERENCES dbo.user (id) AFTER kid_friendly_status
,ADD COLUMN shared_by bigint NOT NULL REFERENCES dbo.user (id) AFTER kid_friendly_marked_by;

ALTER TABLE dbo.Movie
ADD COLUMN kid_friendly_marked_by bigint NOT NULL REFERENCES dbo.user (id) AFTER kid_friendly_status;

ALTER TABLE dbo.WebLink
ADD COLUMN kid_friendly_marked_by bigint NOT NULL REFERENCES dbo.user (id) AFTER kid_friendly_status
,ADD COLUMN shared_by bigint NOT NULL REFERENCES dbo.user (id) AFTER kid_friendly_marked_by;


