# String Calculator

This is a simple GUI of a string calculator that uses a client-server architecture nd MVC pattern.
The program is designed to have multiple calculators running or your computer using a threadpool to handle the requests

## Requirements

- [Java SDK](https://www.oracle.com/java/technologies/javase-downloads.html) minimum Java 8.x.x

## How to Run

1. Start the server
In your terminal run the following:

```shell
javac server/controller/ServerController.java
java server/controller/ServerController
```
**IMPORTANT:** DO NOT CLOSE THIS TERMINAL!

2. Open up another terminal

3. In the other terminal run the following commands:

```shell
javac client/controller/StringController.java
java client/controller/StringController
```
And follow the instructions on the screen.

Enjoy! :smile:

**NOTE:** The server will ask if you would like to shut down every 5 minutes