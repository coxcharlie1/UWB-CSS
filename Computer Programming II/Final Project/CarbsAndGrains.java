public class CarbsAndGrains extends Eating {
    public String descriptor;

    public CarbsAndGrains(){
        super();

    }

    public CarbsAndGrains(Fraction desireStrength){
        super(desireStrength);
        this.descriptor = "";
    }

    public CarbsAndGrains(Fraction desireStrength, String des){
        super(desireStrength);
        this.descriptor = des;

    }

    public CarbsAndGrains(CarbsAndGrains toCopy){
        super(toCopy.desireStrength);
        this.descriptor = toCopy.descriptor;

    }

    public double calculateSatisfaction(Desire other){
        double similarityScore;

        if(this.getClass() == other.getClass()){
            CarbsAndGrains compare = new CarbsAndGrains(other);
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

    public CarbsAndGrains copy(){
        return new CarbsAndGrains(this.getDesireStrength());
    }

    public String toString(){
        if(this.descriptor.isEmpty()){
            return "Eating Carbs and Grains";
        }
        return descriptor;

    }
}
