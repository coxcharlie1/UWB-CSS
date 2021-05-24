//The LinkedListException class handles the Exceptions thrown in the List class
public class LinkedListException extends Exception{
    public LinkedListException(){
        //passes in a string to the super
        super("Element Not Found");

    }
    public LinkedListException(String message){
        //Passes the parameter message in as a parameter
        super(message);


    }
}
