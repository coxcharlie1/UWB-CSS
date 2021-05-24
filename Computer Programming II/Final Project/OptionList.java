import java.io.*;
import java.util.Scanner;

public class OptionList {
    public Option[] optionList;

    public OptionList(String fileName)throws DesireException{
        optionList = new Option[10];
        loadOptions(fileName,this.optionList);


    }
    public void loadOptions(String fileName, Option[] optionList)throws DesireException{
        Scanner options = null;
        try{
            options = new Scanner(new File(fileName));

        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }
        int index = -1;
        while(options.hasNextLine()){
            index++;
            String[] current = options.nextLine().split(" ");
            Desire theDesire = createDesire(current[0],current[1]);
            this.optionList[index] = new Option(theDesire);

        }

    }
    public Desire createDesire(String desire, String descript)throws DesireException{
         if(desire.equals("Eating")){
            return new OutsideTime(new Fraction(1,1));
        }
        //Bottom of Desire hierarchy for Eating
        else if(desire.equals("MeatAndDairy")){
            return new MeatAndDairy(new Fraction(1,1), descript);
        }
        else if(desire.equals("CarbsAndGrains")){
            return new CarbsAndGrains(new Fraction(1,1), descript);
        }
        else if(desire.equals("FruitsAndVeggies")){
            return new FruitsAndVeggies(new Fraction(1,1), descript);
        }
        else if(desire.equals("Sweets")){
            return new Sweets(new Fraction(1,1), descript);
        }
        //Mid level Desire: OutsideTime
        else if(desire.equals("OutsideTime")){
            return new OutsideTime(new Fraction(1,1));
        }
        //Bottom of Desire hierarchy for OutsideTime
        else if(desire.equals("HikingOrCamping")){
            return new HikingOrCamping(new Fraction(1,1), descript);
        }
        else if(desire.equals("Biking")){
            return new Biking(new Fraction(1,1), descript);
        }
        else if(desire.equals("OtherOutside")){
            return new OtherOutside(new Fraction(1,1), descript);
        }
        //Mid level Desire: Music
        else if(desire.equals("Music")){
            return new Music(new Fraction(1,1));
        }
        //Bottom of Desire hierarchy for Music
        else if(desire.equals("Rock")){
            return new Rock(new Fraction(1,1),descript);
        }
        else if(desire.equals("Country")){
            return new Country(new Fraction(1,1), descript);
        }
        else if(desire.equals("RandB")){
            return new RandB(new Fraction(1,1), descript);
        }
        else if(desire.equals("Electronic")){
            return new Electronic(new Fraction(1,1), descript);
        }
        else if(desire.equals("Classical")){
            return new Classical(new Fraction(1,1), descript);
        }
        //Mid level Desire: ScreenTime
        else if(desire.equals("ScreenTime")){
            return new ScreenTime(new Fraction(1,1));
        }
        //Bottom of Desire hierarchy for ScreenTime
        else if(desire.equals("MoviesOrTV")){
            return new MoviesOrTV(new Fraction(1,1), descript);
        }
        else if(desire.equals("VideoGames")){
            return new VideoGames(new Fraction(1,1), descript);
        }
        else if(desire.equals("Internet")){
            return new Internet(new Fraction(1,1), descript);
        }
        else{
            System.out.println("error");
        }
        throw new DesireException();
    }

    /*Takes in a PersonList: list of people and their own desires
     * 1)Goes through the array of Options, and for each Option on
     * this list, sets the AmountOfSatisfaction by going through each
     * of the desires of each of the Persons.
     *
     * 2) Calls the CalculateSatisfaction with that desire(giving
     * the Desire in the Option as the param)
     *
     * 3) Adds that amount to AmountOfSatisfaction
     *
     * 4) Sorts the array(Not a bubble sort)
     * Options with the highest AOS are at the front
     * use a helper method
     */
    public void printRankedListOfWhichOptionsWouldMostSatisfy(PersonList p){
        /*Goes through the list of options calculates the amountOfSatisfaction
         *based on the PersonList and each Persons desire in that list
         */
        for(int i = 0; i < this.optionList.length; i++){
            Desire theOption = this.optionList[i].getDesire();
            double amount = 0.0;
            for(int person = 0; person < p.size(); person++){
                Person newPerson = p.getPerson(person);
                int numberOfDesires = newPerson.desireList.size();
                for(int desires = 0; desires < numberOfDesires; desires++){
                    if(newPerson.desireList.get(desires).equals(theOption)){
                        amount += newPerson.desireList.get(desires).getDesireStrengthDouble();
                    }
                }


            }
            this.optionList[i].AmountOfSatisfaction = amount;

        }
        listThatWouldMostSatisfy(this.optionList);
        System.out.println(optionList.toString());


    }
    /*Takes in the Array options with their new AmountOfSatisfaction
     *calculated and sorts it using Insertion sort.
     */
    public void listThatWouldMostSatisfy(Option[] options){
        for(int i = 1; i < options.length; i++){
            double key = options[i].AmountOfSatisfaction;
            int j = i - 1;
            while(j >= 0 && options[j].AmountOfSatisfaction > key){
                options[j + 1] = options[j];
                j = j - 1;
            }
            options[j + 1].AmountOfSatisfaction = key;
        }


    }
    //Prints out the list of Options
    public String toString(){
        if(optionList.length == 0){
            return "";
        }
        else {
            String retVal = "";
            for (int i = 0; i < this.optionList.length - 1; i++) {
                retVal += this.optionList[i] + ", ";

            }
            retVal += this.optionList[this.optionList.length - 1];
            return retVal;
        }
    }
}
