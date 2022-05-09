package lab5.interf;

import java.io.*;

import lab5.exceptions.NullException;

public interface Executable {
    /**Выполняет команды интеррактивно.*/
    public void executeFromInput();
    /**
     * Выполнение скрипта по пути из переменной окружения.
     * @throws IOException при плохом файле или его имени.
     * @throws NullException при обращении к {@code null}.
     */
    public void executeFromEnv()
    throws IOException, NullException;
    /**
     * Выполнение скрипта указанному по пути.
     * @param filePath путь скрипта.
     * @throws IOException при плохом файле или его имени.
     * @throws NullException при обращении к {@code null}.
     */
    public void executeFrom(String filePath)
    throws IOException, NullException;
}
