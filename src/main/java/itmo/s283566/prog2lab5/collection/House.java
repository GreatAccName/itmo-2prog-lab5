package itmo.s283566.prog2lab5.collection;

import itmo.s283566.prog2lab5.collection.limits.HouseLimits;

import java.util.Objects;

/**Класс дома. Ограничения: {@link HouseLimits}. Входит в состав {@link FlatObjects}.
 */
public class House implements FlatPart {
    private String name;
    private long year;
    private int numberOfLifts;

    protected House(){}

    /**Единственный конструктор дома, задающий и проверяющий все поля.
     * Использует {@link #setHouse(String, long, int)}.
     * @param aName аргумент поля name.
     * @param aYear аргумент поля year.
     * @param aNumberOfLifts аргумент поля numberOfLifts.
     * @throws IllegalArgumentException в случае неудачной проверки полей.
     */
    public House(String aName, long aYear, int aNumberOfLifts)
            throws IllegalArgumentException {setHouse(aName, aYear, aNumberOfLifts);}

    public String getName() {return name;}

    public long getYear() {return year;}

    public int getNumberOfLifts() {return numberOfLifts;}

    /**Единственный сеттер дома, меняющий и проверяющий все поля
     * @param aName аргумент поля name.
     * @param aYear аргумент поля year.
     * @param aNumberOfLifts аргумент поля numberOfLifts.
     * @throws IllegalArgumentException в случае неудачной проверки полей.
     */
    public void setHouse(String aName, long aYear, int aNumberOfLifts)
            throws IllegalArgumentException {
        if (!HouseLimits.isNameGood(aName)) {
            throw new IllegalArgumentException(HouseLimits.getNameLimitInfo());
        }
        if (!HouseLimits.isYearGood(aYear)) {
            throw new IllegalArgumentException(HouseLimits.getYearLimitInfo());
        }
        if (!HouseLimits.isNumberOfLiftsGood(aNumberOfLifts)) {
            throw new IllegalArgumentException(HouseLimits.getNumberOfLiftsLimitInfo());
        }
        name = aName; year = aYear; numberOfLifts = aNumberOfLifts;
    }

    @Override
    public void show() {
        System.out.print("Дом:");
        System.out.print(" [name=" + name + "],");
        System.out.print(" [year=" + year + "],");
        System.out.println(" [numberOfLifts=" + numberOfLifts + "].");
    }

    @Override
    public String getSimple() {return name + "," + year + "," + numberOfLifts;}

    @Override
    public String toString() {return "[name=" + name + ",year=" + year + ",numberOfLifts=" + numberOfLifts + "]";}

    @Override
    public int hashCode() {return Objects.hash(name, year, numberOfLifts);} //авто упаковка
}
