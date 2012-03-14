package triangle

class InvalidInputException extends Exception {
    InvalidInputException(String msg) {
        super(msg)
    }

    InvalidInputException(String msg, Throwable ex) {
        super(msg, ex)
    }
}
