public class MeatAndDairy extends Eating {
    public String descriptor;

    public MeatAndDairy(){
        super();

    }

    public MeatAndDairy(Fraction desireStrength){
        super(desireStrength);
        this.descriptor = "";
    }

    public MeatAndDairy(Fraction desireStrength, String des){
        super(desireStrength);
        this.descriptor = des;

    }

    public MeatAndDairy(MeatAndDairy toCopy){
        super(toCopy.desireStrength);
        this.descriptor = toCopy.descriptor;

    }
    //Returns this.desireStrength * similarity score
    public double calculateSatisfaction(Desire other){
        double similarityScore;

        if(this.getClass() == other.getClass()){
            MeatAndDairy compare = new MeatAndDairy(other);
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

    public MeatAndDairy copy(){

        return new MeatAndDairy(this.getDesireStrength());
    }

    public String toString(){
        if(this.descriptor.isEmpty()){
            return "Eating Meat and Dairy";
        }
        return descriptor;

    }
}
