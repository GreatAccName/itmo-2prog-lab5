#!/bin/bash
cd ..

rm -R bin/
mkdir bin/
javac -d bin/ src/lab5/*/*.java
cd bin/
echo "Manifest-Version: 1.0" > MANIFEST.mf
echo "Main-Class: lab5.mainpac.Main" >> MANIFEST.mf
jar cmf MANIFEST.mf lab5.jar */*/*.class
mv lab5.jar ..
rm MANIFEST.mf
cd ..

cd run-make-scripts
