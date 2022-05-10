package lab5.datapac;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import lab5.enums.Transport;
import lab5.enums.View;
import lab5.exceptions.BadInputException;
import lab5.exceptions.NullException;
import lab5.iopac.RWXbase;

/**Основной класс данных, о квартире.*/
public final class Flat implements Comparable<Flat> {
    /**Генератор ID: 1, 2, 3....*/
    private static int idGen = 1;
    public static int getIdGen() { return idGen; }
    /**Массив со всеми созданными квартирами.*/
    private static final ArrayList<Flat> allCreatedFlats;
    // статический блок инициализации
    static { allCreatedFlats = new ArrayList<>(); }
    public ArrayList<Flat> getAllCreatedFlats() {
        return allCreatedFlats;
    }

    /**Поле не может быть null.
     * Значение поля должно быть больше 0.
     * Значение этого поля должно быть уникальным.
     * Значение этого поля должно генерироваться автоматически.*/
    private Integer id;
    /**Поле не может быть null.
     * Значение этого поля должно генерироваться автоматически.*/
    private LocalDate creationDate;

    /**Поле не может быть null, Строка не может быть пустой.*/
    private String name;
    /**Значение поля должно быть больше 0.*/
    private float area;
    /**Значение поля должно быть больше 0.*/
    private int numberOfRooms;
    /**Поле не может быть null.*/
    private Boolean isNew;
    /**Поле не может быть null.*/
    private Transport transport;
    /**Поле не может быть null.*/
    private View view;
    /**Поле не может быть null.*/
    private Coordinates coordinates;
    /**Поле может быть null.*/
    private House house;

    // блок инициализации
    { id = idGen++; creationDate = LocalDate.now(); }
    
    // getters
    public Integer getId() { return id; }
    public LocalDate getCreationDate() { return creationDate; }
    public String getName() { return name; }
    public float getArea() { return area; }
    public int getNumberOfRooms() { return numberOfRooms; }
    public Boolean getIsNew() { return isNew; }
    public Transport getTransport() { return transport; }
    public View getView() { return view; }
    public Coordinates getCoordinates() { return coordinates; }
    public House getHouse() throws NullException {
        if (house == null) { throw new NullException(
            "house == null"
        ); }
        return house;
    }

    /**
     * Проверяет, хорош ли {@code idToCheck}.
     * @param idToCheck проверяемое ID.
     * @return {@code true} при {@code idToCheck > 0 && idToCheck < idGen}.
     */
    public static boolean isIdGood(Integer idToCheck) {
        return idToCheck > 0 && idToCheck < idGen;
    }
    /**
     * Проверяет {@code argN}.
     * @param argN параметр имени квартиры.
     * @return
     * {@code true} при {@code argN != null && argN.length() > 0},
     * иначе {@code false}.
     */
    public static boolean isNameGood(String argN) {
        return argN != null && argN.length() > 0;
    }
    /**
     * Проверяет {@code argA}.
     * @param argA параметр площади квартиры.
     * @return {@code true} при {@code argA > 0},
     * иначе {@code false}.
     */
    public static boolean isAreaGood(float argA) {
        return Float.compare(argA, 0F) > 0;
    }
    /**
     * Проверяет {@code argNOR}.
     * @param argNOR параметр количества комнат квартиры.
     * @return {@code true} при {@code argNOR > 0},
     * иначе {@code false}.
     */
    public static boolean isNumberOfRoomsGood(int argNOR) {
        return argNOR > 0;
    }
    /**
     * Проверяет {@code argIsNew}.
     * @param argIsNew параметр новизны квартиры.
     * @return {@code true} при {@code argIsNew != null},
     * иначе {@code false}.
     */
    public static boolean isIsNewGood(Boolean argIsNew) {
        return argIsNew != null;
    }
    /**
     * Проверяет {@code argT}.
     * @param argT параметр количества транспорта рядом с квартирой.
     * @return {@code true} при {@code argT != null},
     * иначе {@code false}.
     */
    public static boolean isTransportGood(Transport argT) {
        return argT != null;
    }
    /**
     * Проверяет {@code argV}.
     * @param argV параметр вида из окна квартиры.
     * @return {@code true} при {@code argV != null},
     * иначе {@code false}.
     */
    public static boolean isViewGood(View argV) {
        return argV != null;
    }
    /**
     * Проверяет {@code argC}.
     * @param argC параметр координат квартиры.
     * @return {@code true} при {@code argC != null},
     * иначе {@code false}.
     */
    public static boolean isCoordinatesGood(Coordinates argC) {
        return argC != null;
    }
    /**
     * Полная проверка параметров квартиры.
     * @param argN параметр имени.
     * @param argA параметр площади.
     * @param argNOR параметр количества комнат.
     * @param argIsN параметр новизны.
     * @param argT параметр количества транспорта.
     * @param argV параметр вида из окна.
     * @param argC параметр координат.
     * @return {@code true} при выполнении проверок
     * для всех параметров, иначе {@code false}.
     */
    public static boolean isAllGood(String argN, float argA,
                                    int argNOR, Boolean argIsN,
                                    Transport argT, View argV,
                                    Coordinates argC) {
        return isNameGood(argN) && isAreaGood(argA) &&
               isNumberOfRoomsGood(argNOR) && isIsNewGood(argIsN) &&
               isTransportGood(argT) && isViewGood(argV) &&
               isCoordinatesGood(argC);
    }

    /**Конструктор по умолчанию инициализирует поля
     * конкретными значениями и {@code house = null}.*/
    public Flat() {
        name = "NoNamedFlat";
        area = 0.1F;
        numberOfRooms = 1;
        isNew = false;
        transport = Transport.NONE;
        view = View.GOOD;
        coordinates = new Coordinates();
        house = null;
        // для учета созданных квартир
        allCreatedFlats.add(this);
    }
    /**
     * Инициализирует поля с проверкой параметров.
     * @param argN параметр имени.
     * @param argA параметр площади.
     * @param argNOR параметр количества комнат.
     * @param argIsN параметр новизны.
     * @param argT параметр количества транспорта.
     * @param argV параметр вида из окна.
     * @param argC параметр координат.
     * @param argH параметр дома.
     * @throws BadInputException при плохих параметрах.
     */
    public Flat(String argN, float argA, int argNOR,
                Boolean argIsN, Transport argT, View argV,
                Coordinates argC, House argH)
    throws BadInputException {
        if (!isAllGood(argN,argA,argNOR,argIsN,argT,argV,argC)) {
            throw new BadInputException(
                "Ошибка в каком-то параметре Flat:" +
                "\n argN: " + argN +
                "\n argA: " + argA +
                "\n argNOR: " + argNOR +
                "\n argIsN: " + argIsN +
                "\n argT: " + argT +
                "\n argV: " + argV +
                "\n argC: " + argC
            );
        }
        name = argN;
        area = argA;
        numberOfRooms = argNOR;
        isNew = argIsN;
        transport = argT;
        view = argV;
        coordinates = argC;
        house = argH;
        // для учета созданных квартир
        allCreatedFlats.add(this);
    }

    /**
     * Преобразует строку данных в {@code Flat}.
     * Каждый параметр должен отделяться друг от друга
     * только запятыми '{@code ,}'. Допустимое количество
     * элементов в строке: либо 8, либо 11. Строгий порядок
     * элементов: name, area, numberOfRooms, isNew, transport, view,
     * CoordinatesX, CoordinatesY,
     * HouseName, HouseYear, HouseNumbOfLifts.
     * @param line строка данных.
     * @return объект {@code Flat}.
     * @throws NullException при {@code line == null}.
     * @throws BadInputException при плохих входных данных.
     */
    public static Flat parseLine(String line)
    throws NullException, BadInputException {
        if (line == null) { throw new NullException(
            "Передана строка line: line == null"
        ); }
        // Количество элементов: 6 + 2 + 3
        String[] sArr = RWXbase.makeArrFrom(line);
        int l = sArr.length;
        if (l != 8 && l != 11) {
            throw new BadInputException(
                "Количество элементов L в переданной строке \"" +
                line + "\" недопустимо, т.е. (L != 8 && L != 11)."
            );
        }
        
        String argN = null;
        float argA = 0F;
        int argNOR = 0;
        Boolean argIsN = false;
        Transport argT = null;
        View argV = null;
        Coordinates argC = null;
        House argH = null;

        try {
            argN = sArr[0];
            argA = Float.valueOf(sArr[1]);
            argNOR = Integer.valueOf(sArr[2]);
            argIsN = Boolean.valueOf(sArr[3]);
        } catch (NumberFormatException e) {
            throw new BadInputException(
                "Ошибка в каком-то из элементов строки:" +
                "\n argA: " + sArr[1] +
                "\n argNOR: " + sArr[2] +
                "\n argIsN: " + sArr[3]
            );
        }

        if (!Transport.isInTransport(sArr[4]) ||
            !View.isInView(sArr[5])) {
            throw new BadInputException(
                "Ошибка в каком-то из элементов строки:" +
                "\n argT: " + sArr[4] +
                "\n argV: " + sArr[5]
            );
        }
        argT = Transport.valueOf(sArr[4]);
        argV = View.valueOf(sArr[5]);

        double x = 0D;
        int y = 0;
        try {
            x = Double.valueOf(sArr[6]);
            y = Integer.valueOf(sArr[7]);
        } catch (NumberFormatException e) {
            throw new BadInputException(
                "Ошибка в каком-то из элементов строки:" +
                "\n CoordinatesX: " + sArr[6] +
                "\n CoordinatesY: " + sArr[7]
            );
        }
        argC = new Coordinates(x, y);

        if (l == 11) {
            String houseN = sArr[8];
            long houseY = 0;
            int houseNOL = 0;
            try {
                houseY = Long.valueOf(sArr[9]);
                houseNOL = Integer.valueOf(sArr[10]);
            } catch (NumberFormatException e) {
                throw new BadInputException(
                    "Ошибка в каком-то из элементов строки:" +
                    "\n houseN: " + sArr[8] +
                    "\n houseY: " + sArr[9] +
                    "\n houseNOL: " + sArr[10]
                );
            }
            argH = new House(houseN, houseY, houseNOL);
        }

        Flat f = new Flat(argN, argA, argNOR, argIsN,
                          argT, argV, argC, argH);
        // для учета созданных квартир
        allCreatedFlats.add(f);
        return f;
    }

    /**
     * Создает элемент из данных, полученных из {@code System.in}.
     * @param sc {@code Scanner} входного потока.
     * @return получившийся объект {@code Flat}.
     * @throws BadInputException при плохих входных данных.
     */
    public static Flat flatFromInput(Scanner sc)
    throws BadInputException {
        String argN = null;
        float argA = 0F;
        int argNOR = 0;
        Boolean argIsN = false;
        Transport argT = null;
        View argV = null;
        Coordinates argC = null;
        House argH = null;

        System.out.println("Ввод данных о квартире.");
        String line;

        try {
            System.out.print(" Имя: ");
            argN = sc.nextLine();
            System.out.print(" Площадь (float): ");
            argA = Float.valueOf(sc.nextLine());
            System.out.print(" Кол-во комнат: ");
            argNOR = Integer.valueOf(sc.nextLine());
            System.out.print(" Новизна (true, что-либо): ");
            argIsN = Boolean.valueOf(sc.nextLine());
        } catch (NumberFormatException e) {
            throw new BadInputException();
        }

        System.out.print(" Кол-во транспорта " +
            "(FEW, NONE, NORMAL, ENOUGH): ");
        line = sc.nextLine();
        if (!Transport.isInTransport(line)) {
            throw new BadInputException();
        }
        argT = Transport.valueOf(line);

        System.out.print(" Вид из окна " +
            "(STREET, PARK, BAD, GOOD): ");
        line = sc.nextLine();
        if (!View.isInView(line)) {
            throw new BadInputException();
        }
        argV = View.valueOf(line);

        System.out.println(" Ввод координат квартиры.");
        double x = 0D;
        int y = 0;
        try {
            System.out.print("  x (double): ");
            x = Double.valueOf(sc.nextLine());
            System.out.print("  y (int): ");
            y = Integer.valueOf(sc.nextLine());
        } catch (NumberFormatException e) {
            throw new BadInputException();
        }
        argC = new Coordinates(x, y);

        System.out.println(
            " Ввод данных о доме квартиры " +
            "(пустая строка, если их нет)."
        );
        System.out.print("  Имя: ");
        line = sc.nextLine();
        if (line.length() != 0) {
            String houseN = line;
            long houseY = 0;
            int houseNOL = 0;
            try {
                System.out.print("  Год (long): ");
                houseY = Long.valueOf(sc.nextLine());
                System.out.print("  Кол-во лифтов: ");
                houseNOL = Integer.valueOf(sc.nextLine());
            } catch (NumberFormatException e) {
                throw new BadInputException();
            }
            argH = new House(houseN, houseY, houseNOL);
        }

        Flat f = new Flat(argN, argA, argNOR, argIsN,
                          argT, argV, argC, argH);
        // для учета созданных квартир
        allCreatedFlats.add(f);
        return f;
    }

    /**
     * Устанавливает поля квартиры, кроме ID и даты создания.
     * @param argN параметр имени.
     * @param argA параметр площади.
     * @param argNOR параметр количества комнат.
     * @param argIsN параметр новизны.
     * @param argT параметр количества транспорта.
     * @param argV параметр вида из окна.
     * @param argC параметр координат.
     * @param argH параметр дома.
     * @throws BadInputException при плохих параметрах.
     */
    public void setFlat(String argN, float argA, int argNOR,
                        Boolean argIsN, Transport argT, View argV,
                        Coordinates argC, House argH)
    throws BadInputException {
        if (!isAllGood(argN,argA,argNOR,argIsN,argT,argV,argC)) {
            throw new BadInputException(
                "Ошибка в каком-то параметре Flat:" +
                "\n argN: " + argN +
                "\n argA: " + argA +
                "\n argNOR: " + argNOR +
                "\n argIsN: " + argIsN +
                "\n argT: " + argT +
                "\n argV: " + argV +
                "\n argC: " + argC
            );
        }
        name = argN;
        area = argA;
        numberOfRooms = argNOR;
        isNew = argIsN;
        transport = argT;
        view = argV;
        coordinates = argC;
        house = argH;
    }

    /**
     * Переводит данные о квартире в удобный для чтения вид.
     * @return объект {@code String},
     * где каждое поле в новой строке подписано;
     * в конце объекта НЕТ "{@code \n}".
     */
    public String getShowableFlat() {
        String s =
            "Flat with ID: " + id +
            "\n creationDate: " + creationDate +
            "\n name: " + name +
            "\n area: " + area +
            "\n numberOfRooms: " + numberOfRooms +
            "\n isNew: " + isNew +
            "\n transport: " + transport +
            "\n view: " + view +
            "\n coordinates: " + coordinates;
        if (house == null) { s += "\n house: none"; }
        else { s += "\n house: " + house; }
        return s;
    }
    /**
     * Переводит даные о квартире в простой вид.
     * @return объект {@code String},
     * где каждое поле записано через пробел в единственной строке.
     */
    public String getSimpleFlat() {
        String s =
            id + "," +
            creationDate + "," +
            name + "," +
            area + "," +
            numberOfRooms + "," +
            isNew + "," +
            transport + "," +
            view + "," +
            coordinates.getSimpleCoordinates();
        if (house != null) { s += "," + house.getSimpleHouse(); }
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null ||
            !o.getClass().equals(this.getClass())) {
            return false;
        }
        Flat otherFlat = (Flat) o;
        try {
            return id.equals(otherFlat.getId()) &&
                   creationDate.equals(otherFlat.getCreationDate()) &&
                   name.equals(otherFlat.getName()) &&
                   ( Float.valueOf(area) ).equals(otherFlat.getArea()) &&
                   numberOfRooms == otherFlat.getNumberOfRooms() &&
                   isNew == getIsNew() &&
                   transport.equals(otherFlat.getTransport()) &&
                   view.equals(otherFlat.getView()) &&
                   coordinates.equals(otherFlat.getCoordinates()) &&
                   house.equals(otherFlat.getHouse());
        } catch (NullException e) { return false; }
    }
    @Override
    public String toString() {
        if (house == null) {
            return(
                "[id=" + id +
                ", creationDate=" + creationDate +
                ", name=" + name +
                ", area=" + area +
                ", numberOfRooms=" + numberOfRooms +
                ", isNew=" + isNew +
                ", transport=" + transport +
                ", view=" + view +
                ", coordinates=" + coordinates +
                ", house=none]"
            );
        }
        else {
            return(
                "[id=" + id +
                ", creationDate=" + creationDate +
                ", name=" + name +
                ", area=" + area +
                ", numberOfRooms=" + numberOfRooms +
                ", isNew=" + isNew +
                ", transport=" + transport +
                ", view=" + view +
                ", coordinates=" + coordinates +
                ", house=" + house + "]"
            );
        }
    }
    /**Сравнение по ID.
     * @param o другая квартира для сравнения.
     * @return {@code id.compareTo(o.id)}.
    */
    @Override
    public int compareTo(Flat o) {
        return id.compareTo(o.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id,creationDate,name,area,
                            numberOfRooms,isNew,transport,
                            view,coordinates,house);
    }
}
/*
Integer id;
LocalDate creationDate;
String name;
float area;
int numberOfRooms;
Boolean isNew;
Transport transport;
View view;
Coordinates coordinates;
House house;

String argN;
float argA;
int argNOR;
Boolean argIsN;
Transport argT;
View argV;
Coordinates argC;
House argH;

id,creationDate,name,area,numberOfRooms,isNew,transport,view,coordinates,house
*/
