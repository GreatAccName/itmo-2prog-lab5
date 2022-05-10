package lab5.iopac;

import java.io.*;
import java.util.*;

import lab5.datapac.*;
import lab5.enums.*;
import lab5.exceptions.BadInputException;
import lab5.exceptions.NullException;
import lab5.interf.ReadableFlats;

/**Класс для чтения данных квартир из файла.*/
public class ReadFileFlats
extends RWXbase
implements ReadableFlats {
    /**
     * Удовлетворяет лень придумывать данные по квартирам.
     * @param amount количество квартир, которые лень придумывать.
     */
    public static void printFlatDataArr(int amount) {
        // шапка
        System.out.println(
            "key,FlatName,Area,NumberOfRooms,IsNew," +
            "Transport,View,CoordinateX,CoordinateY," +
            "HouseName,HouseYear,HouseNumberOfLifts"
        );
        for (int i = 1; i <= amount; ++i) {
            Transport[] tArr = Transport.values();
            View[] vArr = View.values();
            // данные
            System.out.print(
                "key" + i + "," +
                "FlatName" + i + "," +
                ((20 + i % 10) + 0.625) + "," +
                (2 + i % 4) + "," +
                (i % 2 == 0 ? true : false) + "," +
                tArr[i % tArr.length] + "," +
                vArr[i % vArr.length] + "," +
                ((i % 164) + 0.625) + "," + (i % 164)
            );
            if (Math.random() < 0.5) {
                System.out.println(
                    ",HouseName" + i + "," +
                    (1900 + i) + "," + (2 + i % 2)
                );
            }
            else { System.out.println(); }
        }
    }

    /**Запрещает вызов конструктора по умолчанию.*/
    protected ReadFileFlats() {}
    /**
     * Инициализирует поле переданной ссылкой на коллекцию.
     * @param toLink ссылка на коллекцию.
     * @throws NullException при {@code envVarName == null},
     * {@code envVarPath == null} или {@code toLink == null}.
     */
    public ReadFileFlats(FlatsCollection toLink)
    throws NullException {
        super(toLink);
        envVarName = "READ_FLATS";
        initializeEnvVarPath();
    }

    @Override
    public void readFrom(String filePath)
    throws IOException, NullException {
        Scanner sc = openScanner(filePath);
        ArrayList<String> text = new ArrayList<>();
        sc.nextLine(); // skip header
        while (sc.hasNextLine()) { text.add(sc.nextLine()); }
        int counter = 1;
        for (String line : text) {
            if (line.length() == 0) { ++counter; continue; }
            try {
                String[] keyVal = makeKeyValFrom(line, ',');
                Flat flat = Flat.parseLine(keyVal[1]);
                collLink.getFlats().put(keyVal[0], flat);
            } catch (BadInputException e) {
                e.printStackTrace();
                System.out.println(
                    // e.getMessage() +
                    "\tфайл: " + filePath +
                    "\n\tстрока: " + counter
                );
            }
            ++counter;
        }
        sc.close();
    }
    @Override
    public void read() throws IOException, NullException {
        readFrom(envVarPath);
    }
}
