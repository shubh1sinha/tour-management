create table tour(
	tourid int not null auto_increment,
	tourname varchar(50) default null,
	duration int default null,
	description varchar(100) default null,
	startdate date default null,
	price int default null,
	PRIMARY KEY(tourid)	
);


Create table booking(
	bookingid int NOT NULL AUTO_INCREMENT, 
	customerid int,
	tourid int,
	passengers int,
	price int,
	status boolean,
	PRIMARY KEY(bookingid)
);


Create table admin(
	username varchar(50),
	password varchar(50),
	PRIMARY KEY(username)
);