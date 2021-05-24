/* The Other class extends the Desire class a new Other object
 * is created when one of the Desires in the file is Other.
 */
public class Other extends Desire {
    public Fraction desireStrength;
    public String descriptor;

    public Other (){
        super();

    }

    public Other (Fraction fract){
        super(fract);

    }
    public Other(Other toCopy){
        super(toCopy);
    }
    public double calculateSatisfaction(Desire other){
        double similarityScore;
        if(other instanceof Other){
            similarityScore = 1.0;
            return this.getDesireStrengthDouble() * similarityScore;
        }
        return 0.0;

    }
    public Other copy(){

        return new Other(this.getDesireStrength());
    }
    public String toString(){
        return "Twiddling my thumbs";

    }

}
