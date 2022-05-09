package lab5.exceptions;

/**Проверяемое исключение при плохом вводе данных.*/
public class BadInputException extends Exception {
    public BadInputException() {
        super("::::ПЛОХИЕ ВХОДНЫЕ ДАННЫЕ::::");
    }
    public BadInputException(String message) {
        super(message);
    }
}
