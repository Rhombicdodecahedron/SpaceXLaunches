package spacexlaunches.main.exception;

public class RestException extends Exception {


    public RestException(String method, String msg) {
        super("ERROR REST in: " + method + " caused by : " + msg);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
