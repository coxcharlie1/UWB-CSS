/* The ScreenTime class extends the Desire class a new ScreenTime object
 * is created when one of the Desires in the file is ScreenTime. This
 * is also the super class for Internet, VideoGames and MoviesOrTV which
 * hold the same functionality except these also have a descriptor.
 */
public class ScreenTime extends Desire {
    public Fraction desireStrength;

    //No-arg that calls to the super setting desireStrength to 0
    public ScreenTime(){

    }

    /*Constructor that takes in a Fraction and sets
     *desireStrength to the Fraction
     */
    public ScreenTime(Fraction fract){
        this.desireStrength = fract;

    }

    /*Copy constructor that sets that makes a copy of
     *the ScreenTime object passed in.
     */
    public ScreenTime(ScreenTime toCopy){
        super(toCopy);
    }

    //Calculates the satisfaction of the desire
    public double calculateSatisfaction(Desire other){
        double similarityScore;
        if(other instanceof ScreenTime){
            similarityScore = 1.0;
            return this.getDesireStrengthDouble() * similarityScore;
        }
        return 0.0;

    }
    //Makes a copy of an ScreenTime object
    public ScreenTime copy(){

        return new ScreenTime(this.getDesireStrength());
    }

    //Returns a String
    public String toString(){
        return "Looking at a Screen";

    }
}
