/* The Desire class holds a desire a person may have and the desire
 * the strength of that desire.
 */
public abstract class Desire extends Fraction implements Comparable<Desire>{
    //Instance variable holding the desire strength per person
    protected Fraction desireStrength;

    //No arg constructor that sets the desireStrength to 0
    public Desire(){

        this.desireStrength = new Fraction(0,1);
    }
    //Constructor that takes in a Fraction and sets the desireStrength
    public Desire(Fraction newDesire){
        //Possibly add an exception for desires over 1
        this.desireStrength = newDesire;
    }
    //Constructor that takes in a Desire
    public Desire(Desire toCopy){
        this.desireStrength = toCopy.getDesireStrength();


    }
    //Returns the desireStrength
    public Fraction getDesireStrength(){

        return this.desireStrength;
    }
    //Returns the desire strength in the form of a double
    public double getDesireStrengthDouble(){
        return (double)this.getNumerator() / this.getDenominator();

    }
    //Returns the desire strength in the form of a Fraction
    public void setDesireStrength(Fraction newStrength){

        this.desireStrength = newStrength;
    }
    //Compares desires based on desireStrength
    //Could sort a list of desires by which things you want
    //The most
    public int compareTo(Desire other){
        if(this.desireStrength.equals(other.desireStrength)){
            return 0;
        }
        if(this.getDesireStrengthDouble() > other.getDesireStrengthDouble()){
            return 1;
        }

        return -1;
    }
    //Returns a copy of the Desire
    public Desire copy(){
        return new Desire(this) {
            @Override
            public double calculateSatisfaction(Desire other) {
                return 0;
            }
        };
    }
    /*We calculate satisfaction by multiplying this.desireStrength by a similarity score
     *which represents how similar the desires (this and other) are to each other.
     *In a case where this is a general desire like Eating, OutsideTime, Music, or ScreenTime,
     *(not a class derived from one of those, but just is one of these general classes itself),
     *that means the person has a very general desire. For instance, this person desires to eat,
     *but doesn't care what.  So if other is the same class or derived from this class
     *(use instanceOf to check this), then we'll say that the similarity = 1. Otherwise,
     *the similarity = 0.
     *
     */
    public abstract double calculateSatisfaction(Desire other);

    //Returns the Fraction toString()
    public String toString(){
        return super.toString();

    }



}
