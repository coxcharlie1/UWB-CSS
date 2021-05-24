/*Charlie Cox
 *
 *LinkedLists
 *
 *David Nixon
 *The List class acts as a LinkedList, adds Objects to the list in the form
 *of Nodes. This class consists of functions that manage the list such as
 *add, remove, indexOf, insert etc.
 */


import sun.security.krb5.internal.crypto.Des;

public class List<Object>{
    public Node head; //the start of the linked list
    public List() {

        head = null;
    }
    public List(Node newNode){
        this.head = newNode;
    }


    //The inner class for the list to handle when a new
    //list gets created
    private class Node {
        private Object data;
        private Node next;

        public Node(){

        }

        public Node(Object data){
            this.data = data;

        }
        public Node(Object newData, Node nextLink) {
           this.data = newData;
           this.next = nextLink;
        }
    }
    public Object getData(){
        return this.head.data;
    }
    public Node getNext(){
        return this.head.next;
    }

    public void append(Object obj){
        Node added = new Node(obj); //Creates a node of the object
        //If the list is empty adds it to the front
        if(head == null){
            head = added;
        }
        //Loops through the list until the end of the list
        else {
            Node current = head;

            while (current.next != null) {
                current = current.next;

            }
            //Adds the object to the end
            current.next = added;
        }

    }
    public Object get(int index){
        Node current = head;
        int count = 0;

        while(current != null){
            if(count == index){
                return current.data;
            }
            count++;
            current = current.next;
        }
        return null;
    }


    //The size method gets the size of the List
    public int size(){
        //if the list is empty
        if(head == null){
            return 0;
        }
        //Loops through all the elements and keeps
        //a count of all elements then returns the count
        int count = 0;
        Node current = head;
        while (current != null) {
            current = current.next;
            count++;
        }
        return count;

    }






}
