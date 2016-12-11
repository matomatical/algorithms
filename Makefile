# A simple makefile for command line compiling and testing of java classes
# based on my project set up
#
# Matt Farrugia

# java compiler and virtual machine
JCC = javac
JVM = java

# source, build, and jar directories
SRC = src
BIN = bin
JAR = jar

# test configuration (directory of tests, test runner class, test suite)
TESTD = test
RUNNER = org.junit.runner.JUnitCore
SUITE = TestSuite
# filter for test output: to remove stack traces from failed tests
FILTER = sed '/^	at/d'

# compilation flags
# the -g flag compiles with debugging information
# the -cp flag sets the class path
# the -d flad sets the output directory
JFLAGS = -g -Xlint:unchecked -cp "./$(SRC)/" -d $(BIN)
TFLAGS = -cp "./$(BIN)/:./$(TESTD):./$(TESTD)/jar/junit.jar:./$(TESTD)/jar/hamcrest.jar"

default: build

# Builds all the classes
build:
		mkdir -p $(BIN)
		$(JCC) $(JFLAGS) $(SRC)/*.java

# build project then JUnit tests
build-tests: build
	$(JCC) $(TFLAGS) $(TESTD)/$(SUITE).java

# Build and run JUnit tests
test: build-tests
	$(JVM) $(TFLAGS) $(RUNNER) $(SUITE) | $(FILTER) | more

# Build and run JUnit tests without filtering output, saving output to a file
# and opening that file
test-v: build-tests
	$(JVM) $(TFLAGS) $(RUNNER) $(SUITE) | more

# Bundle up the classes into a jar file TODO
# jar: build
# 	mkdir -p $(JAR)
# 	# jar command

# Removes all .class files, so that the next make rebuilds them
clean:
		rm -rf ./$(BIN)
		$(RM) ./$(TESTD)/*.class