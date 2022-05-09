#!/bin/bash
cd ..

rm -R bin/
mkdir bin/
javac -d bin/ src/lab5/*/*.java
# documentation
rm -R docs/
mkdir docs/
javadoc -encoding UTF-8 -charset UTF-8  -d docs/ -cp bin/ \
src/lab5/*/*.java

cd run-make-scripts