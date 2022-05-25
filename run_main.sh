export READ_FLATS="src/main/resources/read_flats.csv"
export WRITE_FLATS="src/main/resources/write_flats.csv"
export EXECUTE_COMMANDS="src/main/resources/execute_commands.txt"

java -cp build/classes/java/main/ itmo.s283566.prog2lab5.Main

unset READ_FLATS
unset WRITE_FLATS
unset EXECUTE_COMMANDS

# javadoc -link https://docs.oracle.com/javase/8/docs/api \
# -encoding UTF-8 -charset UTF-8 \
# -d docs/ -cp build/classes/java/main/ \
# src/main/java/itmo/s283566/prog2lab5/*.java \
# src/main/java/itmo/s283566/prog2lab5/*/*.java \
# src/main/java/itmo/s283566/prog2lab5/*/*/*.java
