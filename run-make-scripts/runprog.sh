#!/bin/bash
cd ..

# export READ_FLATS="src/bad_read_flats.csv"
export READ_FLATS="src/read_flats.csv"
export WRITE_FLATS="src/write_flats.csv"
export EX_COMMANDS="src/execute_commands.csv"

java -cp bin/ lab5.mainpac.Main

unset READ_FLATS
unset WRITE_FLATS
unset EX_COMMANDS

cd run-make-scripts