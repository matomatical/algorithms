# A simple makefile for compiling java classes Adapted from
# https://www.cs.swarthmore.edu/~newhall/unixhelp/howto_makefiles.html#java
#
# Matt Farrugia

# java compiler
JCC = javac

# java virtual machine
JVM = java

# source, build, jar, and test directories
SRC = src
BIN = bin

JAR = jar

TESTD = test
SUITE = TestSuite
RUNNER = org.junit.runner.JUnitCore

# compilation flags
# the -g flag compiles with debugging information
# the -cp flag sets the class path
# the -d flad sets the output directory
JFLAGS = -g -cp "./$(SRC)/" -d $(BIN)
TFLAGS = -cp "./$(BIN)/:./$(TESTD):./$(TESTD)/junit.jar:./$(TESTD)/hamcrest.jar"

default: build

# Builds all the classes
build:
		mkdir -p $(BIN)
		$(JCC) $(JFLAGS) $(SRC)/*.java

# Build and run JUnit tests
test: build
	$(JCC) $(TFLAGS) $(TESTD)/$(SUITE).java
	$(JVM) $(TFLAGS) $(RUNNER) $(SUITE)


# Bundle up the classes into a jar file
# jar: build
# 	mkdir -p $(JAR)
# 	# jar command

# Removes all .class files, so that the next make rebuilds them
clean: 
		rm -rf ./$(BIN)
		$(RM) ./$(TESTD)/*.class