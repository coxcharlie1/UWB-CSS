import java.util.*;
/*The Fraction class represents the ratio of two numbers the numerator and
 *denominator of the Fraction being passed. This class stores the fraction in
 * reduced form as an object, which is used for comparing fractions in the
 * counterArrayList the fraction being passed gets compared by the equals
 * method which takes in the fractions that are in the list.
 */
public class Fraction{
    private int numerator; //Instance variable for numerator
    private int denominator; //Instance variable for denominator
    private Fraction theFraction;

    
    public Fraction(){ //Constructor for Fraction that has no arguments

    }

    //Constructor for Fraction that takes in the numerator and denominator
    public Fraction(int numerator, int denominator){
        setNumerator(numerator); //Sets the numerator
        setDenominator(denominator); //Sets the denominator
        //Calls the reduce method for the set numerator and denominator
        reduce(numerator,denominator);
    }
    public int getNumerator(){ //Returns the current numerator
        return numerator;
    }
    public void setNumerator(int numerator){ //Sets the current numerator
        this.numerator = numerator;
    }
    public int getDenominator(){ //Returns the current denominator
        return denominator; 
    }
    public void setDenominator(int denominator){ //Sets the current denominator
       this.denominator = denominator;
    }
    /*The gcdReduce(numerator,denominator) method returns the value of the
     *greatest common denominator based on the denominator of the fraction.
     */
    public int gcdReduce(int numerator, int denominator){ 
        int commonDenom; //The greatest common denominator value
        //If the denominator is zero from the denominator being modded
        if(denominator == 0){
            commonDenom = numerator; //Sets the common denominator to the numerator
            return commonDenom;
        }
        /*sets the common denominator to values from reduce and the remainder of
         *denominator divided by the numerator And returns the common denominator.
         */
        commonDenom = gcdReduce(denominator, numerator % denominator); 
        return commonDenom;
    }

    /*The reduce(numerator,denominator) takes in the current numerator and
     *denominator sets the commonDenom value to what is being returned in gcdReduce
     * then divides the numerator by the greatest common denominator
     */
    public void reduce(int numerator, int denominator){
        //gets set based on the numerator and denominator
        int commonDenom = gcdReduce(numerator,denominator);
        //divides the numerator by the commonDenom value
        this.numerator = numerator/commonDenom;
        //divides the denominator by the commonDenom value
        this.denominator = denominator/commonDenom;
    }
    /*The equals(compare) method takes in a Fraction object and compares it's
     *numerator and denominator the set numerator and denominator that's currently
     * currently set, this method is used for the compareAndImplement method in the
     * FractionCounter class
     */
    public boolean equals(Fraction compare){
        /*Does cross multiplication and sees if the values match and if
         *they do they are the same fraction.
         */
        if(numerator * compare.getDenominator() == compare.getNumerator() * denominator){
            //theFraction.reduce(compare.getNumerator(),compare.getDenominator());

            return true;
        }
        return false;
    }
    public String toString(){ //Returns the String of the numerator and denominator
        return numerator + "/" + denominator;
    }
}