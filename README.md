# Assignment 2: Instant Messenger
Created by Jenny Wang and Duncan Hall

This is the second assignment for Christine Bassem's Distributed Computing course at Wellesley College, Spring 2017.

## Get Started:

#### Intellij
Create an rmi registry in `out/production/ds-assignment2` with `$ rmiregistry` (Unix) or `start rmiregistry` (Windows). Run the `main` method in `server.java`, then `main` method in `client.java` for each client.

#### Command line
Compile the `.java` files found in `src/test`, start an rmi registry as above in the `src` directory, then execute `server.class` and `client.class`.

## Commands:
Each client must register a username with `register <name>`, and then can send messages to other clients with `send <recipient> <message>`. `get` provides a directory of current clients. `signout` unregisters the client's username. 