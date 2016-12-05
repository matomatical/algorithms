# A simple makefile for compiling java classes Adapted from
# https://www.cs.swarthmore.edu/~newhall/unixhelp/howto_makefiles.html#java
#
# Matt Farrugia

# java compiler
JCC = javac

# source, build and test directories
SRC = src
BIN = bin
TST = test

# compilation flags
# the -g flag compiles with debugging information
# the -cp flag sets the class path
# the -d flad sets the output directory
JFLAGS = -g -cp "./src/" -d $(BIN)

default: build

# Builds all the classes
build:
		mkdir -p $(BIN)
		$(JCC) $(JFLAGS) $(SRC)/*.java
		echo "build complete"

# Build and run JUnit tests
test:


# Removes all .class files, so that the next make rebuilds them
clean: 
		$(RM) $(BIN)/**/*.class