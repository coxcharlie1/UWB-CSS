/* Charlie Cox
 *
 * David Nixon
 *
 * Final Project: What Should We Do Tonight?
 *
 * This is the Driver which takes in a two files in the form of Strings. The
 * first file is a list of people and their desires. The next file is a list
 * of options. The purpose of this program is to see which desires would most
 * satisfy the people in the list based on the options list.
 */

import java.io.*;
public class Driver {
    public static void main(String[] args)throws DesireException, FileNotFoundException {
        PersonList myFriends= new PersonList("MyFriends.txt");

        OptionList SaturdayOptions = new OptionList("options1.txt");

        SaturdayOptions.printRankedListOfWhichOptionsWouldMostSatisfy(myFriends);

    }
}
