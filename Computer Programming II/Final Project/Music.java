/* The Music class extends the Desire class a new Music object
 * is created when one of the Desires in the file is Music. This
 * is also the super class for Country, Classical, RandB and Rock which
 * hold the same functionality except these also have a descriptor.
 */
public class Music extends Desire {
    public Fraction desireStrength;

    //No-arg that calls to the super setting desireStrength to 0
    public Music(){
        super();

    }
    /*Constructor that takes in a Fraction and sets
     *desireStrength to the Fraction
     */
    public Music(Fraction fract){
        super(new Fraction(fract.getNumerator(),fract.getDenominator()));

    }
    /*Copy constructor that sets that makes a copy of
     *the Music object passed in.
     */
    public Music(Music toCopy){
        super(toCopy);
    }

    //Calculates the satisfaction of the desire
    public double calculateSatisfaction(Desire other){
        double similarityScore;
        if(other instanceof Music){
            similarityScore = 1.0;
            return this.getDesireStrengthDouble() * similarityScore;
        }
        return 0.0;

    }
    //Makes a copy of an Music object
    public Music copy(){
        return new Music(this.getDesireStrength());
    }
    //Returns a string
    public String toString(){
        return "Listening to Music";

    }
}
