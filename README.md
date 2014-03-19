# Jooq Cache Demo

This project aims to introduce some simple use-cases of how Jooq Cache Demo can be used.
Moreover its goal is also to show that Jooq Cache is highly customizable : it can be used with almost any framework (play, spring, struts etc.) 

It can also be used to quickly play with the code generation : just edit database_init.sql and run the PersistenceGenerator class.

## What does it do ?

### database_init.sql

Contains the code that will be run to initialize the database.
The database is run in memory and will be erased at each run.
Technically a H2 database is used.

### PersistenceGenerator

It is used to generate the DB layer from the database schema defined in database_init.sql

### DemoConnectionProvider

The connection provider used in DAOs to fetch connections.

### DaoModule

Define the DAO runtime configuration.

### App

Initialize the application (mostly the dependency injection layer).

### Demo

A demo class which use the App.

### MicroBenchmark

Some micro-benchmarks for Jooq Cache.

### UserServiceTest 

An example of a Unit Test.
It does not really depends on Jooq Cache but show how Unit Test should be written (as opposed to Integration Test).

### UserDaoTest

An example of an Integration Test to test a Jooq Cache DAO with a running database.

## Remarks

I like the dependency injection framework Guice, however I think that Spring can do more or less the same.
