/* The Person class holds an individuals Person along with a
 * List of their Desires
 */
public class Person extends List<Desire>{
    public String name;

    //A list of all the person's desires
    //Using the linked-list ArrayList
    public List<Desire> desireList;

    public Person(){

    }

    //Sets the name
    public Person(String theName){

        this.name = theName;
        desireList = new List<Desire>();
    }
    //Adds a desire to their List
    public void addDesire(Desire theDesire){

        this.desireList.append(theDesire);
    }

    //Returns their desireList in the form of a String
    public String toString(){
        String desires = "";
        List<Desire> theDesires = desireList;
        while(theDesires.head != null){
            desires += "\n" + "     " + theDesires.getData();
            theDesires.head = theDesires.getNext();

        }



        return name + desires;

    }
}
