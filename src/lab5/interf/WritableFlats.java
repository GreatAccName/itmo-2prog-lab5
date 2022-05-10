package lab5.interf;

import java.io.*;
import java.util.ArrayList;

import lab5.exceptions.NullException;

/**Описывает основное поведения записи квартир в файл.*/
public interface WritableFlats {
    /**
     * Записывает данные о коллекции в файл.
     * В первую строку записывает "шапку",
     * потом данные в каждой новой строке.
     * @param filePath путь к указанному файлу.
     * @param text записываемый построчно текст.
     * @throws IOException при плохом файле или его имени.
     * @throws NullException при обращении к {@code null}.
     */
    void writeTextTo(String filePath, ArrayList<String> text)
    throws IOException, NullException;
    /**
     * Записывает данные о коллекции в файл
     * по пути переменной окружения.
     * В первую строку записывает "шапку",
     * потом данные в каждой новой строке.
     * @param text записываемый построчно текст.
     * @throws IOException при плохом файле или его имени.
     * @throws NullException при обращении к {@code null}.
     */
    void write(ArrayList<String> text)
    throws IOException, NullException;
}
