Create table customer(
	customerid int NOT NULL AUTO_INCREMENT, 
	name varchar(50),
	username varchar(50),
	password varchar(50),
	phonenumber int,
	age int default null, 
	PRIMARY KEY(customerid)
);