public class CustomError extends Exception{
    public CustomError(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
