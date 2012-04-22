DROP TABLE IF EXISTS tvshow;

CREATE TABLE tvshow
(
	id		int not NULL auto_increment,
	description	varchar(1000) default NULL,
	imageId		int not NULL default 0,
	showName	varchar(255) default NULL,
	airingIds	tinyint not NULL default 0,
	reviewIds	tinyint not NULL default 0,

	primary key (id)
);

CREATE TABLE tvshow_array_airingids
(
	id		int not NULL,
	__pos__		int not NULL,
	airingIds	int not NULL default 0,

	primary key (id, __pos__)
);

CREATE TABLE tvshow_array_reviewids
(
	id		int not NULL,
	__pos__		int not NULL,
	reviewIds	int not NULL default 0,

	primary key (id, __pos__)
);
