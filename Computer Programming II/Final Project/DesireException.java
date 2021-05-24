//Calls to the Exception class whenever a DesireException is thrown
public class DesireException extends Exception {
    public DesireException(){
        super();
    }
    public DesireException(String message){
        super(message);
    }
}
