public class Classical extends Music {
    public String descriptor;

    public Classical(){
        super();

    }

    public Classical(Fraction desireStrength){
        super(desireStrength);
        this.descriptor = "";
    }

    public Classical(Fraction desireStrength, String des){
        super(desireStrength);
        this.descriptor = des;

    }

    public Classical(Classical toCopy){
        super(toCopy.desireStrength);
        this.descriptor = toCopy.descriptor;

    }

    public double calculateSatisfaction(Desire other){
        double similarityScore;

        if(this.getClass() == other.getClass()){
            Classical compare = new Classical(other);
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

    public Classical copy(){
        return new Classical(this.getDesireStrength());
    }

    public String toString(){
        if(this.descriptor.isEmpty()){
            return "Listing to Classical Music";
        }
        return descriptor;

    }
}
