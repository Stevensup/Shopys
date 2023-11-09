package co.edu.unbosque.Exception;

@SuppressWarnings("serial")
public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(String message) {
        super(message);
    }
}

