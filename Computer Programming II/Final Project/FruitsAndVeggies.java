public class FruitsAndVeggies extends Eating {
    public String descriptor;

    public FruitsAndVeggies(){
        super();

    }

    public FruitsAndVeggies(Fraction desireStrength){
        super(desireStrength);
        this.descriptor = "";
    }

    public FruitsAndVeggies(Fraction desireStrength, String des){
        super(desireStrength);
        this.descriptor = des;

    }

    public FruitsAndVeggies(FruitsAndVeggies toCopy){
        super(toCopy.desireStrength);
        this.descriptor = toCopy.descriptor;

    }

    public double calculateSatisfaction(Desire other){
        double similarityScore;

        if(this.getClass() == other.getClass()){
            FruitsAndVeggies compare = new FruitsAndVeggies(other);
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

    public FruitsAndVeggies copy(){

        return new FruitsAndVeggies(this.getDesireStrength());
    }

    public String toString(){
        if(this.descriptor.isEmpty()){
            return "Eating Fruits and Veggies";
        }
        return descriptor;

    }

}
