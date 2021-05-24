public class Electronic extends Music {
    public String descriptor;

    public Electronic(){
        super();

    }

    public Electronic(Fraction desireStrength){
        super(desireStrength);
        this.descriptor = "";
    }

    public Electronic(Fraction desireStrength, String des){
        super(desireStrength);
        this.descriptor = des;

    }

    public Electronic(Electronic toCopy){
        super(toCopy.desireStrength);
        this.descriptor = toCopy.descriptor;

    }

    public double calculateSatisfaction(Desire other){
        double similarityScore;

        if(this.getClass() == other.getClass()){
            Electronic compare = new Electronic(other);
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

    public Electronic copy(){

        return new Electronic(this.getDesireStrength());
    }

    public String toString(){
        if(this.descriptor.isEmpty()){
            return "Listing to Electronic Music";
        }
        return descriptor;


    }
}
