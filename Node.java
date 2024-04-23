public class Node {
    Event data;
    Node prev;
    Node next;

    Node(Event item){
        data = item;
        next = null;
        prev = null;
    }


}
