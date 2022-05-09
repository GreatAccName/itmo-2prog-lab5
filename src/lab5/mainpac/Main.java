package lab5.mainpac;

import java.io.IOException;

import lab5.datapac.*;
import lab5.exceptions.NullException;
import lab5.iopac.Executer;
import lab5.iopac.ReadFileFlats;

/**
 * Исполняющий программу класс.<br>
 * Ход выполнения:
 * <ol>
 * <li>Ввод данных квартир из файла.</li>
 * <li>Запуск интерактивного консольного ввода.</li>
 * </ol>
 */
public class Main {
    /**
     * Исполняющий метод всей программы.
     * @param args аргументы для передачи через командную строку.
     */
    public static void main(String[] args) {
        // ReadFileFlats.printFlatDataArr(16);
        FlatsCollection coll = new FlatsCollection();
        stage1DataInputFromFile(coll);
        stage2InteractiveConcole(coll);
    }
    /**
     * Стадия 1: ввод данных квартир из файла.
     * @param coll коллекция с данными о квартирах.
     */
    public static void stage1DataInputFromFile(FlatsCollection coll) {
        try {
            ReadFileFlats readFileFlats = new ReadFileFlats(coll);
            readFileFlats.read();
        }
        catch (IOException | NullException e) {
            e.printStackTrace();
            return;
        }
    }
    /**
     * Стадия 2: запуск интерактивного консольного ввода.
     * @param coll коллекция с данными о квартирах.
     */
    public static void stage2InteractiveConcole(FlatsCollection coll) {
        try {
            Executer executer = new Executer(coll);
            executer.executeFromInput();
        } catch (NullException e) {
            e.printStackTrace();
        }
    }
}
