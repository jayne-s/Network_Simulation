import java.util.ArrayList;

public class SimpleHost extends Host{
    private Message message;
    private int dur;

    private int interval1;
    private int dest;
    private int RTT;
    private ArrayList<Integer> ids = new ArrayList<>();
   // private ArrayList<Timer> timers = new ArrayList<>();
    //private int startID;
    //private int totalTimeID;
    /**
     * This is called when a host receives a Message event from a neighboring host.
     *
     * @param msg the Message event received
     */
    protected void receive(Message msg){
        message = msg;
        switch (msg.getMessage()) {
            case "request":
                System.out.println("[" + (this.getCurrentTime()) + "ts] Host " + msg.getDestAddress() + ": Ping request from host " + msg.getSrcAddress());
                message = new Message("response", msg.getSrcAddress(), msg.getDestAddress(), getCurrentTime());
                message.setInsertionTime(getCurrentTime());
                sendToNeighbor(message);
                break;
            case "response":
                System.out.println("[" + this.getCurrentTime() + "ts] Host " + msg.getDestAddress() + ": Ping response from host " + msg.getSrcAddress() + " (RTT = " + (getCurrentTime() - RTT) + "ts)");
                break;
        }

        // newTimer(getCurrentTime());
    }


    /**
     * This is called after a Timer event expires, and enables you to write code to do something upon timer
     * expiry.  A timer expires when the duration set for the timer is reached.
     *
     * @param eventId the event id of the Timer event which expired
     */
    protected void timerExpired(int eventId) {
        if ((getCurrentTime() < dur) || (getCurrentTime() + interval1) < dur) {
            System.out.println("[" + (getCurrentTime()) + "ts] Host " + this.getHostAddress() + ": Sent ping to host " + dest);
            // System.out.println("FUNC CALLED: " + dest);
            message = new Message("request", dest, this.getHostAddress(), getCurrentTime());
            message.setInsertionTime(getCurrentTime());
           // System.out.println("NEW TIME: " + (interval1+getCurrentTime()));
            newTimer(interval1+getCurrentTime());
            sendToNeighbor(message);
            RTT = getCurrentTime();
            //timerCancelled(eventId);
        }
        if(ids.contains(eventId)){
            timerCancelled(eventId);
        }
    }

    /**
     * This is called after a Timer event is cancelled, and enables you to write code to do something upon timer
     * cancellation.
     *
     * @param eventId the event id of the Timer event which was cancelled
     */
    protected void timerCancelled(int eventId){

            System.out.println("[" + dur + "ts] Host " + getHostAddress() + ": Stopped sending pings");

    }

    public void sendPings(int dest_addr, int interval, int duration){
        dur = duration;
        dest = dest_addr;
        interval1 = interval;
        // System.out.println("DEST: " + dest);

        //newTimer-- returns int id -- pass as parameter
        //cancelTimer() is the only one that accepts timer ID and removes from the fel list
        //onTimerExpiry() is called

            newTimer(interval1); //interval of ping sending
            int durID = newTimer(duration); // total duration before stopping
            //System.out.println("DUR TIMER: " + durID);
            ids.add(durID);


    }

}


