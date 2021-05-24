
/**
 * Option.
 *
 * @author David Nixon
 * @version March 2019
 *
 */
public class Option implements Comparable<Option>
{

    private Desire whatToDo;
    public double AmountOfSatisfaction; // it's public, so no getters or setters required.

    /**
     * Constructor for objects of class Option
     * ---------------------------------------
     * This takes in a Desire d which must be maximally specific,
     * which means it must be a sub-class of Desire which has a
     * descriptor AND that descriptor must be non-empty.
     * If a non-maximally specific Desire is passed in, we will
     * substitute it for a Desire to listen to Elvis Presley
     */
    public Option(Desire d)
    {
        this.whatToDo = (Desire) d.copy();
        AmountOfSatisfaction = 0.0;

        // check if it is a maximally specific Desire:
        try {
            if (!maximallySpecific(d)) {
                System.out.println("PROBLEM! Someone tried to create an Option without a maximally specific desire.");
                throw new Exception("Uh-oh! I was just given a non-maximally specific desire!");
            }
        }
        catch (Exception e){
            // substitute a new specific desire:
            Fraction f = new Fraction(0,1);
            this.whatToDo = new Rock(f, "Elvis-Presley");

        }

    }

    /*
     * getDesire
     * ---------
     * This returns a deep copy of the Desire inside the Option.
     * PRE: none
     * POST: returns a Desire
     */
    public Desire getDesire(){
        return (Desire) whatToDo.copy();
    }

    /*
     * compareTo
     * ---------
     * This takes in another Option and it
     * returns 0 if they have the same AmountOfSatisfaction,
     * returns -1 if this.AmountOfSatisfaction is less than other.AmountOfSatisfaction
     * returns 1 is this.AmountOfSatisfaction is more than other.AmountOfSatisfaction.
     * PRE: other != null
     * POST: returns an int
     */
    public int compareTo(Option other){
        //  It compares based on AmountOfSatisfaction.
        if (this.AmountOfSatisfaction == other.AmountOfSatisfaction) return 0;
        else if (this.AmountOfSatisfaction < other.AmountOfSatisfaction) return -1;
        else return 1;
    }

    /*
     * toString
     * --------
     * This will return a String representing the Option and
     * how much Satisfaction it will bring if acted upon.
     * PRE: none
     * POST: returns a String.
     */
    public String toString(){
        String satisfaction = String.format("%.2f", this.AmountOfSatisfaction);
        return whatToDo.toString() + " will bring " + satisfaction + " units of satisfaction.";
    }


    /*
     * maximallySpecific
     * -----------------
     * This method checks whether a Desire d is maximally specific or not.
     * To be maximally specific, it needs to be a bottom-level Desire (like Sweets or Rock or Other)
     * that has a descriptor, AND that descriptor has to be non-empty.
     * PRE: none
     * POST: returns a boolean
     */
    public boolean maximallySpecific(Desire d){

        if (d.getClass().getName().equals("Eating") ||
                d.getClass().getName().equals("ScreenTime")||
                d.getClass().getName().equals("OutsideTime") ||
                d.getClass().getName().equals("Music") ) return false;

        else if (d.getClass().getName().equals("MeatAndDairy")) {
            MeatAndDairy x = (MeatAndDairy)d; // cast it so we can use it as this kind of object and check its descriptor
            if (x.descriptor.equals("")) return false;
            else return true;
        }
        else if (d.getClass().getName().equals("CarbsAndGrains")) {
            CarbsAndGrains x = (CarbsAndGrains)d; // cast it so we can use it as this kind of object and check its descriptor
            if (x.descriptor.equals("")) return false;
            else return true;
        }
        else if (d.getClass().getName().equals("FruitsAndVeggies")) {
            FruitsAndVeggies x = (FruitsAndVeggies)d; // cast it so we can use it as this kind of object and check its descriptor
            if (x.descriptor.equals("")) return false;
            else return true;
        }
        else if (d.getClass().getName().equals("Sweets")) {
            Sweets x = (Sweets)d; // cast it so we can use it as this kind of object and check its descriptor
            if (x.descriptor.equals("")) return false;
            else return true;
        }
        else if (d.getClass().getName().equals("HikingOrCamping")) {
            HikingOrCamping x = (HikingOrCamping)d; // cast it so we can use it as this kind of object and check its descriptor
            if (x.descriptor.equals("")) return false;
            else return true;
        }
        else if (d.getClass().getName().equals("Biking")) {
            Biking x = (Biking)d; // cast it so we can use it as this kind of object and check its descriptor
            if (x.descriptor.equals("")) return false;
            else return true;
        }
        else if (d.getClass().getName().equals("OtherOutside")) {
            OtherOutside x = (OtherOutside)d; // cast it so we can use it as this kind of object and check its descriptor
            if (x.descriptor.equals("")) return false;
            else return true;
        }
        else if (d.getClass().getName().equals("Rock")) {
            Rock x = (Rock)d; // cast it so we can use it as this kind of object and check its descriptor
            if (x.descriptor.equals("")) return false;
            else return true;
        }
        else if (d.getClass().getName().equals("Country")) {
            Country x = (Country)d; // cast it so we can use it as this kind of object and check its descriptor
            if (x.descriptor.equals("")) return false;
            else return true;
        }
        else if (d.getClass().getName().equals("RandB")) {
            RandB x = (RandB)d; // cast it so we can use it as this kind of object and check its descriptor
            if (x.descriptor.equals("")) return false;
            else return true;
        }
        else if (d.getClass().getName().equals("Electronic")) {
            Electronic x = (Electronic)d; // cast it so we can use it as this kind of object and check its descriptor
            if (x.descriptor.equals("")) return false;
            else return true;
        }
        else if (d.getClass().getName().equals("Classsical")) {
            Classical x = (Classical)d; // cast it so we can use it as this kind of object and check its descriptor
            if (x.descriptor.equals("")) return false;
            else return true;
        }
        else if (d.getClass().getName().equals("MoviesOrTV")) {
            MoviesOrTV x = (MoviesOrTV)d; // cast it so we can use it as this kind of object and check its descriptor
            if (x.descriptor.equals("")) return false;
            else return true;
        }
        else if (d.getClass().getName().equals("VideoGames")) {
            VideoGames x = (VideoGames)d; // cast it so we can use it as this kind of object and check its descriptor
            if (x.descriptor.equals("")) return false;
            else return true;
        }
        else if (d.getClass().getName().equals("Internet")) {
            Internet x = (Internet)d; // cast it so we can use it as this kind of object and check its descriptor
            if (x.descriptor.equals("")) return false;
            else return true;
        }
        else if (d.getClass().getName().equals("Other")) {
            Other x = (Other)d; // cast it so we can use it as this kind of object and check its descriptor
            if (x.descriptor.equals("")) return false;
            else return true;
        }

        else return true;

    }

} // end Option class
