/* The Eating class extends the Desire class a new Eating object
 * is created when one of the Desires in the file is eating.This
 * is also the super class for FruitsAndVeggies, MeatAndDairy, CarbsAndGrains
 * and Sweets which hold the same functionality except these also have a descriptor.
 */
public class Eating extends Desire {
    public Fraction desireStrength;

    //No-arg that calls to the super setting desireStrength to 0
    public Eating(){
        super();
    }
    /*Constructor that takes in a Fraction and sets
     *desireStrength to the Fraction
     */
    public Eating(Fraction fract){
        super(fract);

    }
    /*Copy constructor that sets that makes a copy of
     *the Eating object passed in.
     */
    public Eating(Eating toCopy){
        new Eating(toCopy.desireStrength);

    }
    //Calculates the satisfaction of the desire
    public double calculateSatisfaction(Desire other){
        double similarityScore;
        if(other instanceof Eating){
            similarityScore = 1.0;
            return this.getDesireStrengthDouble() * similarityScore;
        }
        return 0.0;

    }
    //Makes a copy of an Eating object
    public Eating copy(){
        return new Eating(this.getDesireStrength());
    }
    //Returns a string
    public String toString(){
        return "Eating Something";

    }
}
