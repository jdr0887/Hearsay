package org.renci.hearsay.dao;

public class HearsayDAOException extends Exception {

    private static final long serialVersionUID = -7851739902384642707L;

    public HearsayDAOException() {
        super();
    }

    public HearsayDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public HearsayDAOException(String message) {
        super(message);
    }

    public HearsayDAOException(Throwable cause) {
        super(cause);
    }

}
