public class Rock extends Music {
    public String descriptor;

    public Rock (){
        super();

    }

    public Rock(Fraction desireStrength){
        super(desireStrength);
        this.descriptor = "";
    }

    public Rock(Fraction desireStrength, String des){
        super(desireStrength);
        this.descriptor = des;

    }

    public Rock (Rock  toCopy){
        super(toCopy.desireStrength);
        this.descriptor = toCopy.descriptor;

    }

    public double calculateSatisfaction(Desire other){
        double similarityScore;

        if(this.getClass() == other.getClass()){
            Rock compare = new Rock(other);
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

    public Rock  copy(){
        return new Rock(this.getDesireStrength());
    }

    public String toString(){
        if(this.descriptor.isEmpty()){
            return "Listing to Rock";
        }
        return descriptor;

    }
}
