public class Sweets extends Eating {
    public String descriptor;

    public Sweets(){
        super();

    }

    public Sweets(Fraction desireStrength){
        super(desireStrength);
        this.descriptor = "";
    }

    public Sweets(Fraction desireStrength, String des){
        super(desireStrength);
        this.descriptor = des;

    }

    public Sweets(Sweets toCopy){
        super(toCopy.desireStrength);
        this.descriptor = toCopy.descriptor;

    }

    public double calculateSatisfaction(Desire other){
        double similarityScore;

        if(this.getClass() == other.getClass()){
            Sweets compare = new Sweets(other);
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

    public Sweets copy(){

        return new Sweets(this.getDesireStrength());
    }

    public String toString(){
        if(this.descriptor.isEmpty()){
            return "Eating Sweets";
        }
        return descriptor;

    }
}
