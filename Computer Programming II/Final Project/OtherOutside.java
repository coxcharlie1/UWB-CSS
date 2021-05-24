public class OtherOutside extends OutsideTime {
    public String descriptor;

    public OtherOutside(){
        super();

    }

    public OtherOutside(Fraction desireStrength){
        super(desireStrength);
        this.descriptor = "";
    }

    public OtherOutside(Fraction desireStrength, String des){
        super(desireStrength);
        this.descriptor = des;

    }

    public OtherOutside(OtherOutside toCopy){
        super(toCopy.desireStrength);
        this.descriptor = toCopy.descriptor;

    }

    public double calculateSatisfaction(Desire other){
        double similarityScore;

        if(this.getClass() == other.getClass()){
            OtherOutside compare = new OtherOutside(other);
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

    public OtherOutside copy(){
        return new OtherOutside(this.getDesireStrength());
    }

    public String toString(){
        if(this.descriptor.isEmpty()){
            return "Doing something outside";
        }
        return descriptor;

    }
}
