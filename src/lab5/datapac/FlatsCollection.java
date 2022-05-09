package lab5.datapac;

import java.time.LocalDate;
import java.util.*;

/**Класс с основной коллекцией программы.*/
public final class FlatsCollection {
    /**Тип коллекции: HashMap.*/
    private Map<String, Flat> flats;
    /**Дата создания коллекции.*/
    private LocalDate creationDate;
    
    /**Единственный конструктор, задающий все поля.*/
    public FlatsCollection() {
        flats = new HashMap<>();
        creationDate = LocalDate.now();
    }

    public LocalDate getCreationDate() { return creationDate; }
    public Map<String, Flat> getFlats() { return flats; }
}
