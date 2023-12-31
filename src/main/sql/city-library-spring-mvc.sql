CREATE TABLE Person (
    person_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    full_name VARCHAR(30) NOT NULL UNIQUE,
    birth_year INT NOT NULL
);

CREATE TABLE Book (
    book_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    author VARCHAR(30) NOT NULL,
    year INT NOT NULL,
    person_id INT REFERENCES Person(person_id) ON DELETE SET NULL
);