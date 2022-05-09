package lab5.interf;

import java.io.IOException;

import lab5.exceptions.NullException;

/**Описывает основное поведения чтения квартир из файла.*/
public interface ReadableFlats {
    /**
     * Считывает данные коллекции из файла.
     * @param filePath путь файла.
     * @throws IOException при плохом файле или его пути.
     * @throws NullException при обращении к {@code null}.
     */
    void readFrom(String filePath)
    throws IOException, NullException;
    /**
     * Считывает данные коллекции из файла
     * по пути переменной окружения.
     * @throws IOException при плохом файле или его пути.
     * @throws NullException при обращении к {@code null}.
     */
    void read() throws IOException, NullException;
}
