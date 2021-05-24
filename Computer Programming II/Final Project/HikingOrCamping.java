public class HikingOrCamping extends OutsideTime {
    public String descriptor;

    public HikingOrCamping(){
        super();

    }

    public HikingOrCamping(Fraction desireStrength){
        super(desireStrength);
        this.descriptor = "";
    }

    public HikingOrCamping(Fraction desireStrength, String des){
        super(desireStrength);
        this.descriptor = des;

    }

    public HikingOrCamping(HikingOrCamping toCopy){
        super(toCopy.desireStrength);
        this.descriptor = toCopy.descriptor;

    }

    public double calculateSatisfaction(Desire other){
        double similarityScore;

        if(this.getClass() == other.getClass()){
            HikingOrCamping compare = new HikingOrCamping(other);
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

    public HikingOrCamping copy(){

        return new HikingOrCamping(this.getDesireStrength());
    }

    public String toString(){
        if(this.descriptor.isEmpty()){
            return "Hiking or Camping Outside";
        }
        return descriptor;

    }
}
