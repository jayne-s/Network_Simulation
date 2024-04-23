public class LinkedEventList implements FutureEventList {

    Node head;
    Node prev;

    private int simTime;

    /**
     * Insert an Event into the list.
     * <br>
     * The FutureEventList maintains an ordering of Events based on arrival time.
     *
     * @param e an Event to insert into the list
     */


    public void printList(){
        Node curr = head;

        while(curr != null){
            System.out.println("TIMES: ");
            System.out.print( curr.data.getArrivalTime() + " ");
            curr = curr.next;
        }

        System.out.println();
    }

    public void sortList(){
        Node curr = head;
        Node currPtr = null;
        Event temp;

        while(curr != null){
            currPtr = curr.next;
            while(currPtr != null){
                if(curr.data.getArrivalTime() > currPtr.data.getArrivalTime()){
                    temp = curr.data;
                    curr.data = currPtr.data;
                    currPtr.data = temp;
                }
                currPtr = currPtr.next;
            }
            curr = curr.next;
        }

        // printList();
    }

    public void insert(Event e){ //FIXME
        //System.out.println("Event: " + e.toString());
        Node newNode = new Node(e);
        Node last = head;

        if(head == null){
            head = newNode;
            newNode.prev = null;
            //-- duration is the interval from host
            e.setInsertionTime(0);
            return;
        }

        while(last.next != null){
            last = last.next;
        }

        last.next = newNode;
        newNode.prev = last;
        e.setInsertionTime(0);
        sortList();
    }



    /**
     * Remove and return the Event at the front of the list.
     * <br>
     * The FutureEventList is sorted by arrival time, so the Event at the front of the list will be the one with the
     * smallest arrival time.
     *
     * @return the Event at the front of the list
     */
    public Event removeFirst(){
        Node n = head;
        head = head.next;
       // head.prev = null;
        simTime = n.data.getArrivalTime();
        //n.data.handle();
        // n.data.handle();
        //System.out.println(n.data.getArrivalTime());

        return n.data;
    }

    /**
     * Remove the Event e from the list, if it exists.
     *
     * @param e an Event to remove from the list
     * @return true if Event present in the list, false otherwise
     */
    public boolean remove(Event e){
        Node currNode = head;
        while(currNode != null){
            if(currNode.data == e && currNode.prev != null && currNode.next != null){
                currNode.data.cancel();
                currNode.next.prev = currNode.prev;
                currNode.prev.next = currNode.next;
                return true;
            } else {
                currNode = currNode.next;
            }
        }
        return false;
    }

    /**
     * Return the list size (number of Events in the list).
     *
     * @return the number of Events in the list
     */

    public int size(){
        int count = 0;
        Node currNode = head;

        while(currNode != null){
            count++;
            currNode = currNode.next;
        }
        return count;
    }

    /**
     * Return the list capacity (total number of Events the list can store before having to resize it).
     *
     * @return total number of Events the list can store before having to resize it
     */

    public int capacity(){
        return size();
    }

    /**
     * Return the current simulation time (arrival time of last Event)
     *
     * @return the current simulation time
     */
    public int getSimulationTime(){


        //System.out.println("CURR TIME: " + currNode.data.getInsertionTime());
        return simTime;
    }

}
