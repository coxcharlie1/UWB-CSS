public class Internet extends ScreenTime {
    public String descriptor;

    public Internet(){
        super();

    }

    public Internet(Fraction desireStrength){
        super(desireStrength);
        this.descriptor = "";
    }

    public Internet(Fraction desireStrength, String des){
        super(desireStrength);
        this.descriptor = des;

    }

    public Internet(MoviesOrTV toCopy){
        super(toCopy.desireStrength);
        this.descriptor = toCopy.descriptor;

    }

    public double calculateSatisfaction(Desire other){
        double similarityScore;

        if(this.getClass() == other.getClass()){
            Internet compare = new Internet(other);
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

    public Internet copy(){

        return new Internet(this.getDesireStrength());
    }

    public String toString(){
        if(this.descriptor.isEmpty()){
            return "Looking at stuff on the Internet";
        }
        return descriptor;

    }
}
