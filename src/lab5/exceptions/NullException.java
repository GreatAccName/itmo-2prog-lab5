package lab5.exceptions;

/**Проверяемое исключение нулевого указателя.*/
public class NullException extends Exception {
    public NullException() {
        super("::::ОБРАЩЕНИЕ К null::::");
    }
    public NullException(String message) {
        super(message);
    }
}
