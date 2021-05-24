/**Charlie Cox
 * 
 * CSS 110 
 * 
 * Purpose: This program allows the user to interact with a spreadsheet like program.
 * The user can choose from 8 different functions that the program can do.
 */
import java.util.Scanner;           //Import to use the Scanner class 
import java.text.DecimalFormat;     //Import to use the DecimalFormat class

/**
 * Spreadsheet Skeleton File
 * For use with the Spreadsheet HW in 161, this assignment has you build methods
 * that do work on 2-dimensional arrays and produce calculations similar to what
 * you would find in Excel. 
 *
 * Don't rename these methods, or change their input or output values - use this
 * template to get you started.  You are free to add additional methods and data
 * as you see fit.
 * 
 * Index's (col,row) or (x,y) (left > right, up > down)
 * [0,0][1,0]
 * [0,1][1,1]
 *
 */

public class Spreadsheet{

     //Declaring variables as static globals is usually bad design. 
     //Do *NOT* use this strategy in future assignments.
    public static int size = 2; //The size of the spreadsheet
    public static double[][] spreadsheet = new double[size][size]; //Initialization of the spreadsheet
    
    public static DecimalFormat spreadsheetFormat = new DecimalFormat("0.00"); //Declaration of how the input values will be formatted
    
    public static void main(String[] args) {
        //need some scanner data here
        Scanner console = new Scanner(System.in); //Scanner used for the user to input a function a data for the spreadsheet
        initializeSpreadsheet(spreadsheet, spreadsheetFormat); //Sets the spreadsheet to a default value
        printSpreadsheet(spreadsheetFormat,spreadsheet); //Prints out the updated spreadsheet
        //The option/ command that they choose(input,row sum, col sum...)
        int input; 
        
        
        /*This while loop executes until the user inputs the value 8 which will exit out of the program.
         * The console will prompt for an input option and a switch statment is executed based on the option 
         * inputted by the user.
         * 
         */
        while(true) {
           
            System.out.println("(1) Input (2) Row Sum (3) Col Sum (4) Row Ave (5) Col Ave (6) Sum (7) Average (8) Quit");
            //get the next operation from the user
            input = console.nextInt();
            //The number of possible input values and the corresponding cases
            switch(input) { 
                case 1: //Gets the x and y value of the sheet and the value the user wants to input
                    System.out.println("Enter X, Y, Value: ");
                    int x = console.nextInt();
                    int y = console.nextInt();
                    double value = console.nextDouble();
                    inputData(x,y,value,spreadsheet);
                    break;
                case 2: //Gets the sum of all the elements in the row
                    System.out.println("Enter Row: ");
                    int row = console.nextInt();
                    //Formats the sum to the spreadsheetFormat
                    System.out.println("Sum : " + spreadsheetFormat.format(sumRow(row, spreadsheet)));
                    break;                    
                case 3: //Gets the sum of all the elments in the column
                    System.out.println("Enter Column: ");
                    int col = console.nextInt();
                    System.out.println("Sum : " + spreadsheetFormat.format(sumCol(col, spreadsheet)));
                    break;                     
                case 4: //Calculates the average of the elements in the selected row
                     System.out.println("Enter Row: ");
                     row = console.nextInt();
                     System.out.println("Average : " + spreadsheetFormat.format(rowAve(row, spreadsheet)));
                     break;
                case 5: //Calculates the average of the elements in the selected column 
                     System.out.println("Enter Column: ");
                     col = console.nextInt();
                     System.out.println("Average : " + spreadsheetFormat.format(colAve(col, spreadsheet)));
                     break;
                case 6: //Calculates the sum of every element in the spreadsheet
                    System.out.println("Sum : " + spreadsheetFormat.format(sumTotal(spreadsheet)));
                    break;
                case 7: //Calculates the average of every element in the spreadsheet
                    System.out.println("Average : " + spreadsheetFormat.format(average(spreadsheet)));
                    break;
                case 8: //If the user wants to quit
                    System.out.println("Good bye!");
                    System.exit(0);
                    break;
                default:
                System.out.println("Unrecognized command. Please try again!");
            }
        }   
    }
    /*initializeSpreadsheet loops through the spread sheet and sets the starting spreadsheet indexes to 0.
     * 
     */
    public static void initializeSpreadsheet(double[][] inputSpread, DecimalFormat spreadsheetFormat){
            //todo: use a for loop to initialize the rows and columns of the sheet based on the size
         for(int y = 0; y < inputSpread.length; y++){
             for(int x = 0; x < inputSpread[y].length; x++){
                 inputSpread[y][x] = 0;
             }
          }
            
    }
    /*The inputData function takes in three values from the user, an x, y, and the value that the user wants to put in at those cordinate points
     * The value gets initialize by getting the value of the column(y) and then the values of the row (x). Then the printSpreadsheet function is called
     * to update the print out of the sheet
     * 
     */
    public static void inputData(int x, int y, double value, double[][] inputSpread){
            
            inputSpread[y][x] = value;
            printSpreadsheet(spreadsheetFormat,spreadsheet);
    } 
     /*The sumTotal method takes in the array as a parmeter and loops through all the current elements in the sheet and updates a counter created which
      * adds the current sum value to the value being looked at in the sheet then returns the total 
      */
    public static double sumTotal(double[][] inputSpread) {
        double sum = 0;
        
       
        for(int i = 0; i < inputSpread.length; i++){
             
                 sum += sumRow(i, inputSpread);
             
        }
        
        
        return sum;
    }
    /*The sumRow method takes in the targetRow value which is inicated by the user and the spreadsheet. This method loops through all the elements in the 
     * targetRow and adds that value to a sum variable and returns the total sum of the elements in that target row. 
     */
    public static double sumRow(int targetRow, double[][] inputSpread) {
       double sum = 0; //sum variable
        for(int element = 0; element < inputSpread[targetRow].length; element++){
            sum += inputSpread[targetRow][element]; //Calculation for updating the sum variable
        
       }
       
       return sum;
    }
    /*The sumCol method takes in the targetCol value which is inicated by the user and the spreadsheet. This method loops through all the elements in the 
     * targetCol and adds that value to a sum variable and returns the total sum of the elements in that target column. 
     */
    public static double sumCol(int targetCol, double[][] inputSpread) {
        double sum = 0; //sum variable
         for(int element = 0; element < inputSpread[targetCol].length; element++){
            sum += inputSpread[element][targetCol]; //Calculation for updating the sum variable
        
       }
        return sum;
    }
    /*The rowAve method takes in a targetRow varible which is the row indicated by the user as the row they would like the average of, and the spreadsheet.
     * A loop goes through each element in the target row to get the number of elements that may be in that row. A conditional statment also is 
     * initialized to check if the value in the sheet in not the default value which is zero and updates the total variable which the total number of elements in that row. 
     * After the loop gets the total number of inputed values in that row, the method returns the method call of sumRow to get the sum of the current row, divided by the 
     * total value that calculated (Total sum of elements in the row / Total number of element in the row)
     */
    public static double rowAve(int targetRow, double[][] inputSpread) {
        int total = 0;
        for(int numberOfElements = 0; numberOfElements < inputSpread[targetRow].length; numberOfElements++){
           if(inputSpread[targetRow][numberOfElements] != 0.00){
                total++;
           }
        }
        return sumRow(targetRow, inputSpread) / total;
    }
     /*The colAve method takes in a targetCol varible which is the column indicated by the user as the column they would like the average of, and the spreadsheet.
     * A loop goes through each element in the target column to get the number of elements that may be in that column. A conditional statment also is 
     * initialized to check if the value in the sheet in not the default value which is zero and updates the total variable which the total number of elements in that column. 
     * After the loop gets the total number of inputed values in that column, the method returns the method call of sumCol to get the sum of the current col, divided by the 
     * total value that calculated (Total sum of elements in the column / Total number of elements in the column)
     */
    public static double colAve(int targetCol, double[][] inputSpread) {
          int total = 0;
          for(int numberOfElements = 0; numberOfElements < inputSpread[targetCol].length; numberOfElements++){
            if(inputSpread[targetCol][numberOfElements] != 0.00){
                total++;
            }
        }
        return sumRow(targetCol, inputSpread) / total;
        
    }
    /*The average method takes in the spreadsheet and calculates the total average of all the elements in the spreadsheet by looping though every values in the rows and column and
     * checks if the value is not the default value zero, then updates a total varible which represents the total number of inputted elements in the spreadsheet.
     * After looping though the sheet to calculate the total varible, the method returns the method call of sumTotal which calculates the sum of the elements in the sheet, 
     * divided by the the total varible. (Sum of all inputted values in the sheet / the amount of input values in the sheet) 
     */
    public static double average(double[][] inputSpread) {
        int total = 0; 
        for(int y = 0; y < inputSpread.length; y++){
            for(int x = 0; x < inputSpread[y].length; x++){
                if(inputSpread[y][x] != 0.00){
                   total++;
                }
            }
        }
        return sumTotal(inputSpread) / total;
    }
    /*The printSpreadsheet method takes in the spreadsheetFormat argument and the spreadsheet and formats every value in the sheet according to the spreadsheetFormat.
     * It does this by looping through each value and assigns the value at the target index as the spreadsheetFormat and prints it out.
     */
    public static void printSpreadsheet(DecimalFormat spreadsheetFormat, double[][] inputSpread) {
        //todo: use printf to format your spreadsheet string to only have 2 digits to the right of the mantissa
        //i.e. only have 2 digits representing the fractional part of the number
        for(int y = 0; y < inputSpread.length; y++){
             for(int x = 0; x < inputSpread[y].length; x++){
                 System.out.print("|");
                 System.out.print(spreadsheetFormat.format(inputSpread[y][x]));
                 System.out.print("|");
             }
             System.out.println();
        }
    }
   
}
