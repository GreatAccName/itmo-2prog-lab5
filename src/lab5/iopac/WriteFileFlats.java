package lab5.iopac;

import java.io.*;
import java.util.*;

import lab5.datapac.*;
import lab5.exceptions.NullException;
import lab5.interf.WritableFlats;

/**Класс для записи данных квартир в файл.*/
public class WriteFileFlats
extends RWXbase
implements WritableFlats {
    /**Запрещает вызов конструктора по умолчанию.*/
    protected WriteFileFlats() {}
    /**
     * Инициализирует поле переданной ссылкой на коллекцию.
     * @param toLink ссылка на коллекцию.
     * @throws NullException при {@code envVarName == null},
     * {@code envVarPath == null} или {@code toLink == null}.
     */
    public WriteFileFlats(FlatsCollection toLink)
    throws NullException {
        super(toLink);
        envVarName = "WRITE_FLATS";
        initializeEnvVarPath();
    }

    @Override
    public void writeTextTo(String filePath, ArrayList<String> text)
    throws IOException, NullException {
        FileWriter fw = openFileWriter(filePath);
        // шапка
        fw.write(
            "key,id,creationDate,FlatName,Area,NumberOfRooms,IsNew," +
            "Transport,View,CoordinateX,CoordinateY," +
            "HouseName,HouseYear,HouseNumberOfLifts\n"
        );
        for (String line : text) { fw.write(line + "\n"); }
        fw.close();
    }
    @Override
    public void write(ArrayList<String> text)
    throws IOException, NullException {
        writeTextTo(envVarPath, text);
    }
}
