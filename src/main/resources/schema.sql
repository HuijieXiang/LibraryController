    DROP TABLE IF EXISTS CITY;  
    CREATE TABLE CITY (
    City_code INT AUTO_INCREMENT  PRIMARY KEY,  
    city_name VARCHAR(50) NOT NULL,  
    city_pincode INT(8) NOT NULL 
    );
    
--	DROP TABLE IF EXISTS BOOKS;  
--    CREATE TABLE BOOKS (
--    bookid INT AUTO_INCREMENT  PRIMARY KEY,  
--    bookname VARCHAR(100) NOT NULL, 
--    author   VARCHAR(50) NOT NULL,
--    price 	INT(8) NOT NULL 
--    );