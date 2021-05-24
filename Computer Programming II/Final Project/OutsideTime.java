/* The OutsideTime class extends the Desire class a new OutsideTime object
 * is created when one of the Desires in the file is OutsideTime. This
 * is also the super class for HikingOrCamping and OtherOutside which
 * hold the same functionality except these also have a descriptor.
 */
public class OutsideTime extends Desire {
    public Fraction desireStrength;

    //No-arg that calls to the super setting desireStrength to 0
    public OutsideTime (){
        super();

    }

    /*Constructor that takes in a Fraction and sets
     *desireStrength to the Fraction
     */
    public OutsideTime (Fraction fract){
        super(fract);

    }

    /*Copy constructor that sets that makes a copy of
     *the OutsideTime object passed in.
     */
    public OutsideTime(OutsideTime toCopy){
        super(toCopy);
    }

    //Calculates the satisfaction of the desire
    public double calculateSatisfaction(Desire other){
        double similarityScore;
        if(other instanceof OutsideTime){
            similarityScore = 1.0;
            return this.getDesireStrengthDouble() * similarityScore;
        }
        return 0.0;

    }

    //Makes a copy of an OutsideTime object
    public OutsideTime copy(){
        return new OtherOutside(this.getDesireStrength());
    }

    //Returns a string
    public String toString(){
        return "Spending Sometime Outside";

    }
}
