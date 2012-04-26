CLASSPATH = :./WEB-INF/lib/*
OUTPUT = ./WEB-INF/classes/
SRC = ./WEB-INF/src/controller/* \
      ./WEB-INF/src/model/* \
      ./WEB-INF/src/databean/* \
      ./WEB-INF/src/formbean/* \
			./WEB-INF/src/scripts/*

.PHONY: all database clean

all:
	javac -Werror -classpath $(CLASSPATH) -d $(OUTPUT) $(SRC)

database:
	mysql finalwebapp < ./WEB-INF/reset_static_tables.sql
	mysqlimport -v --local finalwebapp ./WEB-INF/tvshow.txt

clean:
	rm -rf ./WEB-INF/classes/*
