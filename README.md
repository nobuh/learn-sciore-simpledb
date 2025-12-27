This repo contains the combination of the SimpleDB_3.4.zip (within
[SimpleDB_3.4](./SimpleDB_3.4)) and hwassignments.zip (within
[hwassignments](./hwassignments)) from Sciore's website.

Build instructions:
https://gist.github.com/ankithooda/b0d624aec9b3ed2882713d59feba4b11.


build
-----

Create bin folder to store the compiled .class files

	cd SimpleDB_3.4
	mkdir -p bin/server
	mkdir -p bin/client

Compile Server

	javac -d bin/server simpledb/server/*

Compile the various Client programs

	javac -d bin/client simpleclient/SimpleIJ.java
	javac -d bin/client simpleclient/network/*
	javac -d bin/client simpleclient/embedded/*

Now the bin/server contains all the class files for server
and bin/client contains all the class files for running various sample clients

Run Server
----------

	cd bin/server/
	java simpledb.server.StartServer

Run Clients
-----------

Create some sample data by running the CreateStudentDB and StudentMajor clients

	cd bin/client
	java network.CreateStudentDB
	java network.StudentMajor

Run interactive client

	java SimpleIJ

it will ask for connection string

	Connect> 
	jdbc:simpledb://localhost

after providing the connection string, SQL> prompt appears, where one can type in the query.

	SQL> select sname, majorid from student
	      sname majorid
	-------------------
	        joe      10
	        amy      20
	        max      10
	        sue      20
	        bob      30
	        kim      20
	        art      30
	        pat      20
	        lee      10

How to insert
-------------

	insert into student (sname, majorid) values ('name', value)
	-- string value should be enclosed by ' not "
