CLASSPATH = :./WEB-INF/lib/*
OUTPUT = ./WEB-INF/classes/
SRC = ./WEB-INF/src/*

all:
	javac -classpath $(CLASSPATH) -d $(OUTPUT) $(SRC)
