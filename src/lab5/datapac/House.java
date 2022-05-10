package lab5.datapac;

import java.util.*;

import lab5.exceptions.BadInputException;

/**Класс дома квартиры.*/
public class House {
    /**Поле не может быть null.*/
    private String name;
    public String getName() { return name; }
    /**Значение поля должно быть больше 0.*/
    private long year;
    public long getYear() { return year; }
    /**Значение поля должно быть больше 0.*/
    private int numberOfLifts;
    public int getNumberOfLifts() { return numberOfLifts; }

    /**
     * Проверяет {@code argN}.
     * @param argN параметр имени дома.
     * @return {@code true} при
     * {@code argN != null && argN.length() > 0},
     * иначе {@code false}.
     */
    public static boolean isNameGood(String argN) {
        return argN != null && argN.length() > 0;
    }
    /**
     * Проверяет {@code argY}.
     * @param argY параметр года постройки дома.
     * @return {@code true} при {@code argY > 0},
     * иначе {@code false}.
     */
    public static boolean isYearGood(long argY) {
        return argY > 0;
    }
    /**
     * Проверяет {@code argNOL}.
     * @param argNOL параметр количества лифтов в дома.
     * @return {@code true} при {@code argNOL > 0},
     * иначе {@code false}.
     */
    public static boolean isNumberOfLiftsGood(int argNOL) {
        return argNOL > 0;
    }

    /**Инициализирует поля простыми значениями.*/
    public House() {
        name = "NoNamedHouse";
        year = 1900;
        numberOfLifts = 1;
    }
    /**
     * Инициализиурет поля с проверкой параметров.
     * @param n имя дома.
     * @param y год постройки.
     * @param nOL количество лифтов.
     * @throws BadInputException при плохих параметрах.
     */
    public House(String n, long y, int nOL) 
    throws BadInputException {
        if (!isNameGood(n) || !isYearGood(y) ||
            !isNumberOfLiftsGood(nOL)) {
            throw new BadInputException(
                "Ошибка в каком-то параметре House:" +
                "\n argName: " + n +
                "\n argYear: " + y +
                "\n argNOfL: " + nOL
            );
        }
        name = n;
        year = y;
        numberOfLifts = nOL;
    }

    /**
     * Переводит даные о доме в простой вид.
     * @return объект {@code String},
     * где каждое поле записано через пробел в единственной строке.
     */
    public String getSimpleHouse() {
        return name + "," +
               Long.valueOf(year).toString() + "," +
               Integer.valueOf(numberOfLifts).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null ||
            !o.getClass().equals(this.getClass())) {
            return false;
        }
        House otherHouse = (House) o;
        return  name.equals(otherHouse.getName()) &&
                year == otherHouse.getYear() &&
                numberOfLifts == otherHouse.getNumberOfLifts();
    }
    @Override
    public String toString() {
        return(
            "[name=" + name + ", year=" + year +
            ", numberOfLifts=" + numberOfLifts + "]"
        );
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, year, numberOfLifts);
    }
}