package lab5.iopac;

import java.io.*;
import java.util.*;

import lab5.datapac.Flat;
import lab5.datapac.FlatsCollection;
import lab5.exceptions.BadInputException;
import lab5.exceptions.NullException;

/**Класс с командами для выполнения.*/
public class Commands extends RWXbase {
    /**Запрещает вызов конструктора по умолчанию.*/
    protected Commands() {}
    /**
     * Инициализирует поле переданной ссылкой на коллекцию,
     * команды над которой будут выполняться.
     * @param toLink ссылка на коллекцию.
     * @throws NullException при {@code envVarName == null},
     * {@code envVarPath == null} или {@code toLink == null}.
     */
    public Commands(FlatsCollection toLink)
    throws NullException {
        super(toLink);
        envVarName = "EX_COMMANDS";
        initializeEnvVarPath();
    }

    /**Вывести справку по доступным командам.*/
    protected void help() {
        System.out.println(
            "Справка по доступным командам:\n" +
            " [help] : вывести справку по доступным командам.\n" +
            " [info] : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.).\n" +
            " [show] : вывести в стандартный поток вывода все элементы коллекции в строковом представлении.\n" +
            " [insert key {flat}] : добавить новый элемент с заданным ключом.\n" +
            " [update id {flat}] : обновить значение элемента коллекции, id которого равен заданному.\n" +
            " [remove_key key] : удалить элемент из коллекции по его ключу.\n" +
            " [clear] : очистить коллекцию.\n" +
            " [save] : сохранить коллекцию в файл.\n" +
            " [execute_script file_path] : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме (запрещена для вызова из скрипта).\n" +
            " [exit] : завершить программу (без сохранения в файл).\n" +
            " [remove_lower id] : удалить из коллекции все элементы, меньшие, чем заданный.\n" +
            " [replace_if_greater key {flat}] : заменить значение по ключу, если новое значение больше старого.\n" +
            " [replace_if_lower key {flat}] : заменить значение по ключу, если новое значение меньше старого.\n" +
            " [min_by_id] : вывести любой объект из коллекции, значение поля id которого является минимальным.\n" +
            " [filter_starts_with_name name] : вывести элементы, значение поля name которых начинается с заданной подстроки.\n" +
            " [print_descending] : вывести элементы коллекции в порядке убывания."
        );
        System.out.println(
            "Ограничения ввода данных квартиры:\n" +
            " Имя: не пустое, не более 1 слова\n" +
            " Площадь (float): больше 0\n" +
            " Кол-во комнат: больше 0\n" +
            " Ограничения ввода координат квартиры:\n" +
            "  x (double): 0 <= x <= 165\n" +
            "  y (int): 0 <= y <= 165\n" +
            " Ограничения ввода данных о доме квартиры:\n" +
            "  Имя: не пустое, не более 1 слова\n" +
            "  Год (long): больше 0\n" +
            "  Кол-во лифтов: больше 0\n" +
            "Ключ: не пустой, не более 1 слова"
        );
    }
    /**Вывести в стандартный поток вывода информацию о коллекции
     * (тип, дата инициализации, количество элементов и т.д.).*/
    protected void info() {
        Map<String, Flat> flats = collLink.getFlats();
        System.out.println(
            "Информация о коллекции:" +
            "\n Тип: " + flats.getClass() + 
            "\n Дата создания: " + collLink.getCreationDate() +
            "\n Количество элементов: " + flats.size()
        );
    }
    /**Вывести в стандартный поток вывода все элементы коллекции
     * в строковом представлении.*/
    protected void show() {
        Map<String, Flat> flats = collLink.getFlats();
        if (flats.isEmpty()) {
            System.out.println("Коллекция пустая.");
            return;
        }
        System.out.println("Все элементы коллекции (ключ: зачение):");
        flats.forEach((key, flat) -> {
            System.out.println(
                key + ": " + flat.getShowableFlat()
            );
        });
    }
    /**Добавить новый элемент с заданным ключом.
     * @param key ключ, по которому меняется элемент.
     * @param flat новые данные квартиры.*/
    protected void insert(String key, Flat flat) {
        Map<String, Flat> flats = collLink.getFlats();
        if (flats.containsKey(key)) {
            System.out.println("Ключ \"" + key + "\" уже добавлен.");
            return;
        }
        flats.put(key, flat);
        System.out.println("Квартира по ключу \"" +
            key + "\" успешно добавлена.");
    }
    /**Обновить значение элемента коллекции, id которого равен заданному.
     * @param id ID квартиры.
     * @param flat новые данные квартиры.*/
    protected void update(Integer id, Flat flat) {
        Map<String, Flat> flats = collLink.getFlats();
        if (flats.isEmpty()) {
            System.out.println(
                "Обновить по ID \"" + id + "\" " +
                "не получилось, т.к. коллекция пустая."
            );
            return;
        }
        Collection<Flat> flatsVals = flats.values();
        Iterator<Flat> iter = flatsVals.iterator();
        while (iter.hasNext()) {
            Flat iterFlat = iter.next();
            if (iterFlat.getId().equals(id)) {
                try {
                    iterFlat.setFlat(flat.getName(), flat.getArea(),
                              flat.getNumberOfRooms(), flat.getIsNew(),
                              flat.getTransport(), flat.getView(),
                              flat.getCoordinates(), flat.getHouse());
                } catch (BadInputException | NullException e) {
                    e.printStackTrace();
                }
                System.out.println("Изменения в квартиру " +
                    "по ID \"" + id + "\" внесены.");
                return;
            }
        }
        System.out.println("ID \"" + id + "\" не найден в коллекции.");
    }
    /**Удалить элемент из коллекции по его ключу.
     * @param key ключ элемента для удаления.*/
    protected void remove_key(String key) {
        Map<String, Flat> flats = collLink.getFlats();
        if (flats.containsKey(key)) {
            flats.remove(key);
            System.out.println("Квартира по ключу \"" +
                key + "\" успешно удалена.");
            return;
        }
        System.out.println("Нет ключа \"" +
            key + "\" в коллекции.");
    }
    /**Очистить коллекцию.*/
    protected void clear() {
        Map<String, Flat> flats = collLink.getFlats();
        flats.clear();
        System.out.println("Коллекция очищена.");
    }
    /**Сохранить коллекцию в файл.*/
    protected void save() {
        Map<String, Flat> flats = collLink.getFlats();
        if (flats.isEmpty()) {
            System.out.println(
                "Сохранить коллекцию в файл не удасться," +
                " т.к. коллекция пустая."
            );
            return;
        }
        WriteFileFlats writeFileFlats = null;
        try {
            writeFileFlats = new WriteFileFlats(collLink);
        } catch (NullException e) {
            e.printStackTrace();
        }
        
        ArrayList<String> text = new ArrayList<>();
        flats.forEach((k, v) -> {
            text.add(k + " " + v.getSimpleFlat());
        });
        
        try { writeFileFlats.write(text); }
        catch (IOException | NullException e) {
            e.printStackTrace();
        }
        System.out.println(
            "Данные коллекции успешно записаны в файл: \"" +
            writeFileFlats.getEnvVarPath() + "\"."
        );
    }
    /**Завершить программу (без сохранения в файл).*/
    protected void exit() {
        System.out.println("Завершение выполнения программы...");
    }
    /**Удалить из коллекции все элементы,
     * меньшие по сравнению компаратором,
     * чем переданный по ID.
     * @param id ID квартиры (подразумевается сравнение по {@code area}).
     * @param comparator компаратор полей квартир.*/
    protected void remove_lower(Integer id,
                                Comparator<Flat> comparator) {
        Map<String, Flat> flats = collLink.getFlats();
        if (flats.isEmpty()) {
            System.out.println("Коллекция пустая.");
            return;
        }
        Set<Map.Entry<String, Flat>> flatsSet = flats.entrySet();
        Iterator<Map.Entry<String, Flat>> iter = flatsSet.iterator();
        Flat flat = null;
        while (iter.hasNext()) {
            Flat iterFlat = iter.next().getValue();
            if (id.equals(iterFlat.getId())) { flat = iterFlat; }
        }
        if (flat == null) {
            System.out.println("В коллекции нет элемента " +
                "с ID \"" + id + "\".");
            return;
        }
        iter = flatsSet.iterator();
        while (iter.hasNext()) {
            Flat iterFlat = iter.next().getValue();
            if (comparator.compare(iterFlat, flat) < 0) { iter.remove(); }
        }
        System.out.println("Удаление элементов, меньших" +
            " заданного по ID \"" + id + "\", прошло успешно.");
    }
    /**Заменить значение по ключу, если новое значение
     * больше старого по сравнению компаратором.
     * @param key переданный ключ.
     * @param flat переданные данные квартиры
     * (подразумевается сравнение по {@code area}).
     * @param comparator компаратор полей квартир.*/
    protected void replace_if_greater(String key, Flat flat,
                                   Comparator<Flat> comparator) {
        Map<String, Flat> flats = collLink.getFlats();
        Flat keyFlat = flats.get(key);
        if (keyFlat == null) {
            System.out.println("Ключ \"" + key + "\" не найден.");
            return;
        }
        if (comparator.compare(flat, keyFlat) > 0) {
            flats.put(key, flat);
            System.out.println("БОльшие данные " +
                "заменены по ключу \"" + key + "\".");
            return;
        }
        System.out.println("Данные НЕ заменены по ключу \"" +
            key + "\", т.к. не оказались бОльшими.");
    }
    /**Заменить значение по ключу, если новое значение
     * меньше старого по сравнению компаратором.
     * @param key переданный ключ.
     * @param flat переданные данные квартиры
     * (подразумевается сравнение по {@code area}).
     * @param comparator компаратор полей квартир.*/
    protected void replace_if_lower(String key, Flat flat,
                                   Comparator<Flat> comparator) {
        Map<String, Flat> flats = collLink.getFlats();
        Flat keyFlat = flats.get(key);
        if (keyFlat == null) {
            System.out.println("Ключ \"" + key + "\" не найден.");
            return;
        }
        if (comparator.compare(flat, keyFlat) < 0) {
            flats.put(key, flat);
            System.out.println("Меньшие данные " +
                "заменены по ключу \"" + key + "\".");
            return;
        }
        System.out.println("Данные НЕ заменены по ключу \"" +
            key + "\", т.к. не оказались меньшими.");
    }
    /**Вывести любой объект из коллекции,
     * значение поля id которого является минимальным.*/
    protected void min_by_id() {
        Map<String, Flat> flats = collLink.getFlats();
        Iterator<Map.Entry<String, Flat>> iter = flats.entrySet().iterator();
        Flat iterFlat;
        if (iter.hasNext()) { iterFlat = iter.next().getValue(); }
        else { System.out.println("Пустая коллекция."); return; }
        while (iter.hasNext()) {
            Flat v = iter.next().getValue();
            if (iterFlat.getId().compareTo(v.getId()) > 0) { iterFlat = v; }
        }
        System.out.println("Квартира с минимальным ID: " +
            iterFlat.getShowableFlat());
    }
    /**Вывести элементы, значение поля name
     * которых начинается с заданной подстроки.
     * @param subName заданная подтрока имени.*/
    protected void filter_starts_with_name(String subName) {
        Map<String, Flat> flats = collLink.getFlats();
        Iterator<Map.Entry<String, Flat>> iter = flats.entrySet().iterator();
        ArrayList<Flat> subNamedFlats = new ArrayList<>();
        while (iter.hasNext()) {
            Flat iterFlat = iter.next().getValue();
            int lSub = subName.length();
            int lIter = iterFlat.getName().length();
            if (lIter >= lSub) {
                String subV = iterFlat.getName().substring(0, lSub);
                if (subV.equals(subName)) { subNamedFlats.add(iterFlat); }
            }
        }
        if (subNamedFlats.isEmpty()) {
            System.out.println("Нет квартир в коллекции, " +
                "имена которых начинаются на \"" + subName + "\".");
            return;
        }
        System.out.println("Квартиры в коллекции, " +
            "имена которых начинаются на \"" + subName + "\":");
        for (Flat flat : subNamedFlats) {
            System.out.println(flat.getShowableFlat());
        }
    }
    /**Вывести элементы коллекции в порядке убывания.*/
    protected void print_descending() {
        NavigableMap<String, Flat> flats = 
        (new TreeMap<String, Flat>(collLink.getFlats())).descendingMap();
        if (flats.size() == 0) {
            System.out.println(
                "Нельзя вывести элементы коллекции " +
                "в порядке убывания, т.к. коллекция пустая."
            );
            return;
        }
        System.out.println("Коллекция в обратном порядке:");
        flats.forEach((k, v) -> {
            System.out.println(k + ": " + v.getShowableFlat());
        });
    }
}
/*
help : вывести справку по доступным командам.
info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.).
show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении.
insert key {flat} : добавить новый элемент с заданным ключом.
update id {flat} : обновить значение элемента коллекции, id которого равен заданному.
remove_key key : удалить элемент из коллекции по его ключу.
clear : очистить коллекцию.
save : сохранить коллекцию в файл.
execute_script file_path : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме (запрещена для вызова из скрипта).
exit : завершить программу (без сохранения в файл).
remove_lower id : удалить из коллекции все элементы, меньшие, чем заданный.
replace_if_greater key {flat} : заменить значение по ключу, если новое значение больше старого.
replace_if_lower key {flat} : заменить значение по ключу, если новое значение меньше старого.
min_by_id : вывести любой объект из коллекции, значение поля id которого является минимальным.
filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки.
print_descending : вывести элементы коллекции в порядке убывания.
*/