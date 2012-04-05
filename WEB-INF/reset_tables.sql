DROP TABLE IF EXISTS myuser;
DROP TABLE IF EXISTS tvshow;
DROP TABLE IF EXISTS airing;
DROP TABLE IF EXISTS network;
DROP TABLE IF EXISTS image;
DROP TABLE IF EXISTS review;

CREATE TABLE myuser
(
	id		int,
	username 	varchar(20),
	password	varchar(30),
	email		varchar(50),
	phoneNumber	varchar(15),
	timezone	varchar(20)
);

CREATE TABLE tvshow
(
	id		int,
	description	varchar(1000),
	imageId		int
);

CREATE TABLE airing
(
	id		int,
	showId		int,
	networkId	int,
	airTime		date
);

CREATE TABLE network
(
	id		int,
	name		varchar(20)
);

CREATE TABLE image
(
	id		int
);

CREATE TABLE review
(
	id		int,
	showId		int,
	userId		int,
	text		varchar(10000),
	rating		int
);
	
