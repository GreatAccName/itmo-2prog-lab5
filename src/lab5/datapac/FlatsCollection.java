package lab5.datapac;

import java.time.LocalDate;
import java.util.*;

/**Класс с основной коллекцией программы.*/
public final class FlatsCollection {
    /**Тип коллекции: HashMap.*/
    private Map<String, Flat> flats;
    /**Дата создания коллекции.*/
    private LocalDate creationDate;

    public LocalDate getCreationDate() { return creationDate; }
    public Map<String, Flat> getFlats() { return flats; }
    
    /**Единственный конструктор, задающий все поля.*/
    public FlatsCollection() {
        flats = new HashMap<>();
        creationDate = LocalDate.now();
    }
    
    /**
     * Проверяет, подходит ли ключ для добавления в коллекцию.
     * @param k значение ключа.
     * @return {@code true} при {@code k != null && k.length() > 0}.
     */
    public static boolean isKeyGood(String k) {
        return k != null && k.length() > 0;
    }
}
