/*The ArrayList class carries out the functions of an ArrayList except using
 *arrays. The ArraysList class can get the size of the list, check if the list
 *is empty, inserts a new element into the list while accounting for the size,
 *gets the element at an index, checks if one object ArrayList equals another
 *in size and elements and just in size, removes an element at an index, and
 *prints out the elements in the array.
 * 
 */
public class ArrayList<PersonList> {
    public int initialCapacity; //Starting size of the array
    public int size;
    //The initialisation of the array of Objects
    public Object[] data;
    //ArrayList default constructor
    public ArrayList(){
        this.size = 0;
        this.initialCapacity = 1;
        data = new Object[this.initialCapacity];
    }
    //ArrayList constructor if a size was passed in 
    public ArrayList(int size){
        this.size = 0;
        this.initialCapacity = 1;
        data = new Object[this.initialCapacity];
        
    }


    /*The size() methods returns the number of element in the array which
     *is the size
     */
    public int size(){

        return this.size;
    }
    /*The isEmpty method, checks if the array is empty based on the current
     * size of the list.
     */
    public boolean isEmpty(){
        if(this.size() == 0){
            return true;
        }
        return false;
    }

    /*The insert method puts in input or an element at a specified index by checking
     *if the index is within range of the number of elements in the array, or if the
     * array is already filled up so it can add the element to the end of the array.
     * If the initialCapacity has been reached the resizeList method is called to
     * resize the list. If the index is already occupied by a value that value along
     * with values after if any will be shifted over to the right then the element
     * will be inserted, if an element get inserted the size also increases.
     */
    public void insert(Object input, int index){
        //If the index is not in range

        if(index > 0 && this.size() == 0){
            throw new IndexOutOfBoundsException();
        }
        /*If the index is the first ever element
         *being inserted.
         */
        else if(this.size() == 0 && index == 0) {
            data[index] = input;
            this.size++;//Increments size
        }
        /*If the index is already occupied by an element
         *or if if it's the value right next to the last
         * element in the list.
         */

        else{
            if(this.size() == this.initialCapacity){
                //the new data list according to resizeList
                this.data = resizeList(this.size() + 1);

            }
            for (int i = this.size(); i > index; i--) {
                this.data[i] = this.data[i - 1]; //Shifts value
            }
            this.data[index] = input;//Inserts new value
            this.size++;//Increments size

        }


    }
    /*The resizeList method gets called in the insert method, which resizes
     *the list if the list has reached it's capacity. Returns the data list
     * with an increased size which is the previous size plus one.
     */
    public Object[] resizeList(int newSize){
        this.initialCapacity = newSize;
        Object[] newList = new Object[initialCapacity];
        for(int i = 0; i < this.size(); i++){
            newList[i] = this.data[i];
        }
        return newList; //Returns the new list

    }

    /*The get method returns the object that's at the index passed in, if that index
     *is not occupied by a value then -1 is returned
     */
    public Object get(int index){
        if(this.isEmpty()){
            return -1;
        }
        return data[index];
    }
    /*The indexOf method gets the index of an element passed in by looping through
     *all the elements in the list and checks if the element matches the input being
     * passed, if it is not found returns -1
     */
    public int indexOf(Person input){
        for(int i = 0; i < this.size; i++){
            if(data[i] == input){
                return i;//Returns index
            }
        }
        return -1;
    }

    /*The sameSize method checks if the size of the array being passed in equals the
     *size of the ArrayList being called returns true if it does returns false
     * if it doesn't.This method helps with the equals method.
     */   
    public boolean sameSize(ArrayList input){
        if(this.size() == input.size()){
            return true;
        }
        return false;
    }

    /*The equals checks if the ArrayList object being passed in matches the elements
     *and size of the ArrayList object being called. Calls sameSize first to see if
     * the size is the same then compares each element in the ArrayList, if they are
     * not the same elements or size, returns false returns true if they are.
     */
    public boolean equals(ArrayList input){
        if(sameSize(input)){
            for(int i = 0; i < this.size; i++){
                //Compares elements
                if(this.data[i] != input.get(i)){
                    return false;
                
                }
            }
            return true;
        }
        return false;
    }

    /*The remove method removes the Object in the ArrayList at the index being passed
     *in, by getting the object, storing it into a variable, setting the value to
     * null, decrementing the size then returning the value.
     */
    public Person remove(int index){
        if(this.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if(data[index] == null){
            throw new IndexOutOfBoundsException();
        }
        Object val = this.get(index);
        //Shifts the values after the one being removed one before
            for(int i = index; i <= this.size(); i++){
                if(i == (this.size() - 1)) {
                    data[i] = null;
                    this.size--;
                }
                else {
                    data[i] = data[i + 1];

                }

            }
            return (Person)val; //Returns the value


       
    }
    /*The toString() method returns all the objects in the
     *ArrayList in the form of a String.
     */
    public String toString() {
        //If the list is null
        if (this.size() == 0) {
            return "[]";
        } else {
            //The value being returned
            String str = "[";
            for (int i = 0; i < this.size(); i++) {
                if (i == this.size() - 1) {
                    str += data[i] + "]";

                }
                else {
                    str += data[i] + ", ";
                }

            }
            return str;//Returned value
        }

    }
}