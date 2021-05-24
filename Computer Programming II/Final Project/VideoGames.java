public class VideoGames extends ScreenTime {
    public String descriptor;

    public VideoGames(){
        super();

    }

    public VideoGames(Fraction desireStrength){
        super(desireStrength);
        this.descriptor = "";
    }

    public VideoGames(Fraction desireStrength, String des){
        super(desireStrength);
        this.descriptor = des;

    }

    public VideoGames(MoviesOrTV toCopy){
        super(toCopy.desireStrength);
        this.descriptor = toCopy.descriptor;

    }

    public double calculateSatisfaction(Desire other){
        double similarityScore;

        if(this.getClass() == other.getClass()){
            VideoGames compare = new VideoGames(other);
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

    public VideoGames copy(){
        return new VideoGames(this.getDesireStrength());
    }

    public String toString(){
        if(this.descriptor.isEmpty()){
            return "Playing VideoGames";
        }
        return descriptor;

    }
}
