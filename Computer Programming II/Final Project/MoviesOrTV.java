public class MoviesOrTV extends ScreenTime {
    public String descriptor;

    public MoviesOrTV(){
        super();

    }

    public MoviesOrTV(Fraction desireStrength){
        super(desireStrength);
        this.descriptor = "";
    }

    public MoviesOrTV(Fraction desireStrength, String des){
        super(desireStrength);
        this.descriptor = des;

    }

    public MoviesOrTV(MoviesOrTV toCopy){
        super(toCopy.desireStrength);
        this.descriptor = toCopy.descriptor;

    }

    public double calculateSatisfaction(Desire other){
        double similarityScore;

        if(this.getClass() == other.getClass()){
           MoviesOrTV compare = new MoviesOrTV(other);
            if(this.descriptor.equals(compare.descriptor)){
                similarityScore = 1;
                return this.getDesireStrengthDouble() * similarityScore;
            }
            if(this.descriptor.equals("") || compare.descriptor.equals("")){
                similarityScore = 1;
                return this.getDesireStrengthDouble() * similarityScore;
            }
            if((!this.descriptor.equals("")) && (!this.descriptor.equals(compare.descriptor))){
                similarityScore = .65;
                return this.getDesireStrengthDouble() * similarityScore;
            }

        }
        if(this.getClass().getSuperclass() == other.getClass().getSuperclass()){
            similarityScore = .30;
            return this.getDesireStrengthDouble() * similarityScore;
        }
        return 0;

    }

    public MoviesOrTV copy(){

        return new MoviesOrTV(this.getDesireStrength());
    }

    public String toString(){
        if(this.descriptor.isEmpty()){
            return "Watching a Movie or TV";
        }
        return descriptor;

    }
}
