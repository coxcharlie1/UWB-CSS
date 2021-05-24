/* The PersonList class holds an list of Persons and their Desires in
 * the form of an ArrayList.
 */
import java.io.*;
import java.util.*;
public class PersonList extends ArrayList<PersonList>{


    PersonList myPersonList;
    //Creates a new PersonList
    public PersonList(){
        super();
    }

    //Takes in the fileName then calls loadPeople
    public PersonList(String fileName)throws FileNotFoundException{
        this.myPersonList = new PersonList();
        loadPeople(fileName,myPersonList);

    }
    //Adds a person to the list
    public void add(Person p){
        super.insert(p, this.size);

    }

    //Gets an element at a specified index
    public Person getPerson(int index){

        return (Person) super.get(index);
    }

    //Creates a Desire based on the file input
    public Desire createDesire(String desire, String descript, Fraction strength) {
        //Mid Level Desire: Eating
        if (desire.equals("Eating")){
            return new Eating(strength);
        }
        //Bottom of Desire hierarchy for Eating
        else if(desire.equals("MeatAndDairy")){
            return new MeatAndDairy(strength, descript);
        }
        else if(desire.equals("CarbsAndGrains")){
            return new CarbsAndGrains(strength, descript);
        }
        else if(desire.equals("FruitsAndVeggies")){
            return new FruitsAndVeggies(strength, descript);
        }
        else if(desire.equals("Sweets")){
            return new Sweets(strength, descript);
        }
        //Mid level Desire: OutsideTime
        else if(desire.equals("OutsideTime")){
            return new OutsideTime(strength);
        }
        //Bottom of Desire hierarchy for OutsideTime
        else if(desire.equals("HikingOrCamping")){
            return new HikingOrCamping(strength, descript);
        }
        else if(desire.equals("Biking")){
            return new Biking(strength, descript);
        }
        else if(desire.equals("OtherOutside")){
            return new OtherOutside(strength, descript);
        }
        //Mid level Desire: Music
        else if(desire.equals("Music")){
            return new Music(strength);
        }
        //Bottom of Desire hierarchy for Music
        else if(desire.equals("Rock")){
            return new Rock(strength,descript);
        }
        else if(desire.equals("Country")){
            return new Country(strength, descript);
        }
        else if(desire.equals("RandB")){
            return new RandB(strength, descript);
        }
        else if(desire.equals("Electronic")){
            return new Electronic(strength, descript);
        }
        else if(desire.equals("Classical")){
            return new Classical(strength, descript);
        }
        //Mid level Desire: ScreenTime
        else if(desire.equals("ScreenTime")){
            return new ScreenTime(strength);
        }
        //Bottom of Desire hierarchy for ScreenTime
        else if(desire.equals("MoviesOrTV")){
            return new MoviesOrTV(strength, descript);
        }
        else if(desire.equals("VideoGames")){
            return new VideoGames(strength, descript);
        }
        else {
            return new Internet(strength, descript);
        }

    }
    /* Goes through the file and creates Desire objects based on the input
     * while adding those Desires to a Persons list
     */
    public void loadPeople(String filename, PersonList myPersonList)throws FileNotFoundException {
        Scanner fileReader = new Scanner(new File(filename));
        String s = "";
        int current = -1;
        boolean newPerson = false;
        while (fileReader.hasNext()) {
            s = fileReader.nextLine();
            String[] tokens = s.split(" ");

            if (tokens[0].equals("Name:")) {
                if (!newPerson) {
                    String personsName = tokens[1];
                    //Creates a new Person object that holds the name
                    Person currentPerson = new Person(personsName);
                    this.myPersonList.add(currentPerson);
                    current++;
                    newPerson = true;
                } else {
                    String personsName = tokens[0];
                    Person currentPerson = new Person(personsName);
                    this.myPersonList.add(currentPerson);
                    current++;
                    newPerson = true;

                }

            }
            //read in desires
            else { // it must be a desire...
                // parse the desire and add it to the Person:
                String desireName = tokens[0];
                String descriptor = tokens[1].replace("\"", "");
                String fractionString = tokens[2];
                // parse the fractionString, and make a fraction:
                String[] fractionTokens = fractionString.split("/");
                int numerator = Integer.parseInt(fractionTokens[0]);
                int denominator = Integer.parseInt(fractionTokens[1]);
                Fraction desireStrength = new Fraction(numerator, denominator); // or, if your Fraction
                // only has constructors for
                // ints, then convert these
                // Strings to ints first.
                Desire currentDesire = createDesire(desireName, descriptor, desireStrength);
                this.myPersonList.getPerson(current).addDesire(currentDesire);
            }
        } // end of the filereader.hasNext() while loop

        // We get here when we run out of text in the file,
        // so we need to load up the last person there was.
        // Unless of course, for some reason there were no people to add...

    }// end LoadPeopleListFromFile()
    //Returns the personList in the form of a String
    public String toString(){
        String retVal = "";
        for(int i = 0; i < myPersonList.size; i++){
            retVal += myPersonList.getPerson(i).toString() + "\n";

        }
        return retVal;
    }



}
