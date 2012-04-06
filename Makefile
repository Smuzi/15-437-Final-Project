CLASSPATH = :./WEB-INF/lib/*
OUTPUT = ./WEB-INF/classes/
SRC = ./WEB-INF/src/controller/* \
      ./WEB-INF/src/model/*

.PHONY: all database clean

all:
	javac -Werror -classpath $(CLASSPATH) -d $(OUTPUT) $(SRC)
database:
	mysql finalwebapp < ./WEB-INF/reset_tables.sql
	mysqlimport --local finalwebapp ./WEB-INF/tvshow.txt

clean:
	rm -rf ./WEB-INF/classes/*
