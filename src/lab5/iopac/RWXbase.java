package lab5.iopac;

import java.io.*;
import java.util.*;

import lab5.datapac.*;
import lab5.exceptions.*;

/**Базовый класс для всех классов,
 * предназначенных для ввода/вывода/выполнения.
 * Также располагает некоторыми
 * полезными статическими методами
 * для обработки строк и работы с файлами.*/
public class RWXbase {
    /**Имя переменной окружения, содержащая путь.*/
    protected String envVarName = null;
    public String getEnvName() { return envVarName; }
    /**Путь файла из заданной переменной окружения.*/
    protected String envVarPath = null;
    public String getEnvVarPath() { return envVarPath; }
    /**Ссылается на изменяемую коллекцию.
     * Кроме всего прочего, поле упрощяет сигнатуру методов.*/
    protected FlatsCollection collLink;
    public FlatsCollection getCollLink() { return collLink; }
    /**
     * Инициализирует в конструкторе {@code envVarPath}.
     * @throws NullException при {@code envVarName == null}
     * или {@code envVarPath == null}.
     */
    protected void initializeEnvVarPath()
    throws NullException {
        if (envVarName == null) {
            throw new NullException("envVarName == null");
        }
        Map<String, String> envVars = System.getenv();
        Set<Map.Entry<String, String>> envSet = envVars.entrySet();
        for (Map.Entry<String, String> entry : envSet) {
            if (entry.getKey().equals(envVarName)) {
                envVarPath = entry.getValue();
                break;
            }
        }
        if (envVarPath == null) {
            throw new NullException("envVarPath == null");
        }
    }

    /**Запрещает вызов конструктора по умолчанию.*/
    protected RWXbase() {}
    /**
     * Инициализирует поле переданной ссылкой на коллекцию.
     * @param toLink ссылка на коллекцию.
     * @throws NullException
     * нельзя присваивать коллекции {@code null}.
     */
    public RWXbase(FlatsCollection toLink)
    throws NullException {
        if (toLink == null) {
            throw new NullException(
                "FlatsCollection toLink == null"
            );
        }
        collLink = toLink;
    }
    
    /**
     * Делит строку на ключ и значение.
     * Разделение по '{@code separator}'.
     * @param s необработанная строка.
     * @param separator разделитель.
     * @return массив из 2-ух элементов {@code [key, value]}.
     * @throws NullException если {@code s == null}.
     * @throws BadInputException если пустой ключ или разделитель не найден.
     */
    public static String[] makeKeyValFrom(String s, char separator)
    throws NullException, BadInputException {
        if (s == null) {
            throw new NullException(
                "Переданная строка s: s == null"
            );
        }
        s = s.trim();
        int i = 0;
        while (i < s.length() && s.charAt(i) != separator) { ++i; }
        if (i == s.length()) {
            throw new BadInputException(
                "В переданной строке \"" + s + "\" " +
                "нет разделителя '" + separator + "'"
            );
        }
        if (i == 0) { throw new BadInputException(); }
        String val = "";
        if (i != s.length() - 1) { val = s.substring(i + 1).trim(); }
        String[] sArr = new String[] {
            s.substring(0, i),
            val
        };
        return sArr;
    }
    /**
     * Создает массив элементов строки.
     * Разделение по запятой '{@code ,}'.
     * @param s входная строка.
     * @return массив элементов строки.
     * @throws NullException если {@code s == null}.
     */
    public static String[] makeArrFrom(String s)
    throws NullException {
        if (s == null) {
            throw new NullException(
                "Переданная строка s: s == null"
            );
        }
        s = s.trim();
        if (s.length() == 0) { return new String[0]; }
        return s.split("[,]");
    }

    /**
     * Открывает {@code FileWriter} для ввода данных.
     * Перезаписывает файл, т.е. прошлые данные стираются.
     * @param filePath путь к файлу.
     * @return объект {@code FileWriter}.
     * @throws IOException при плохом файле или его имени.
     * @throws NullException при {@code filePath==null}.
     */
    public static FileWriter openFileWriter(String filePath)
    throws IOException, NullException {
        if (filePath == null) {
            throw new NullException(
                "Переданная строка filePath: filePath == null"
            );
        }
        File file = new File(filePath);
        if (!file.canRead()) {
            throw new IOException(
                "\"" + filePath + "\" не может быть прочитан"
            );
        }
        return new FileWriter(file, false);
    }
    /**
     * Открывает {@code Scanner} для ввода данных.
     * Перезаписывает файл, т.е. прошлые данные стираются.
     * @param filePath путь к файлу.
     * @return объект {@code Scanner}.
     * @throws IOException при плохом файле или его имени.
     * @throws NullException при {@code filePath==null}.
     */
    public static Scanner openScanner(String filePath)
    throws IOException, NullException {
        if (filePath == null) {
            throw new NullException(
                "Переданная строка filePath: filePath == null"
            );
        }
        File file = new File(filePath);
        if (!file.canRead()) {
            throw new IOException(
                "\"" + filePath + "\" не может быть прочитан"
            );
        }
        return new Scanner(file);
    }
}
