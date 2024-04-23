public class Message extends Event {

    private String message;
    private Host nextHop;
    private int srcAddress;
    private int destAddress;
    private int dist;
    private int duration;

    public int interval;

    public Message(String message, int dest1, int host, int time){
        this.message = message;
        srcAddress = host;
        destAddress = dest1;
        duration = time;
    }

    /**
     * Sets the insertion time and arrival time for this Event.
     * <br>
     * It is assumed that any information needed to compute the arrival time from the insertion time is passed into
     * the Event's constructor (for example a duration).  This method should be called from within the FutureEventList's
     * insert method.
     *
     * @param currentTime the current simulation time
     */
    public void setInsertionTime(int currentTime) {
        insertionTime = currentTime;
        // arrivalTime = insertionTime + duration;
    }

    /**
     * Cancel the Event.
     * <br>
     * This occurs after the Event has been removed from the future event list, probably before the arrival time has
     * been reached.
     */
    public void cancel() {
        //BLANK
    }

    /**
     * Handle (or execute) the Event.
     * <br>
     * This occurs after the Event has been removed from the future event list, due to the arrival time being reached.
     * For example, if this Event represents a network message, then calling handle() will 'process' the message at the
     * destination host.  If the Event is a Timer, then this will execute whatever needs to be done upon timer expiry.
     */
    public void handle() {
        if(nextHop != null){
            nextHop.receive(this);
        }
    }

    public String getMessage() {
        return message;
    }

    public int getSrcAddress() {
        return srcAddress;
    }

    public int getDestAddress() {
        return destAddress;
    }


    public void setNextHop(Host dest, int distance) {
        nextHop = dest;
        //destAddress = getSrcAddress();
        dist = distance;
        arrivalTime = dist + insertionTime;
    }
}
