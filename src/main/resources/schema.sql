    DROP TABLE IF EXISTS CITY;  
    CREATE TABLE CITY (
    City_code INT AUTO_INCREMENT  PRIMARY KEY,  
    city_name VARCHAR(50) NOT NULL,  
    city_pincode INT(8) NOT NULL 
    );