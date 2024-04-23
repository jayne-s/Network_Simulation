
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Integer> hostsNums = new ArrayList<>();
        ArrayList<Integer> distances = new ArrayList<>();
        ArrayList<Integer> sims = new ArrayList<>();
        ArrayList<SimpleHost> hosts = new ArrayList<>();
        File myObj = new File("simulation4.txt");
        Scanner readInput  = new Scanner(myObj);
        hostsNums.add(readInput.nextInt());

        int num = 0;
        while(num != -1){
            num = readInput.nextInt();
            if(num == -1){
               break;
            } else {
                hostsNums.add(num);
                num = readInput.nextInt();
                distances.add(num);
            }
        }

//        System.out.println("HOSTS NUMS:");
//        for(int i : hostsNums){
//            System.out.println(i);
//        }
//
//
//        System.out.println("DISTANCES:");
//        for(int j : distances){
//            System.out.println(j);
//        }


        LinkedEventList list = new LinkedEventList();
        //setHostParameter, addNeighbor, addNeighbor
        for(int host : hostsNums){
            SimpleHost tempHost = new SimpleHost();
            tempHost.setHostParameters(host, list);
            hosts.add(tempHost);
        }

//        System.out.println("HOSTS:");
//        for(SimpleHost h : hosts){
//            System.out.println(h.getHostAddress());
//        }

        for(int a = 1, b = 0; a < hosts.size(); a++, b++){
            hosts.get(0).addNeighbor(hosts.get(a), distances.get(b));
            hosts.get(a).addNeighbor(hosts.get(0), distances.get(b));
        }
//        hosts.get(0).addNeighbor(hosts.get(1), distances.getFirst());
//        hosts.get(1).addNeighbor(hosts.get(0), distances.getFirst());
//
//        hosts.get(0).addNeighbor(hosts.get(2), distances.get(1));
//        hosts.get(2).addNeighbor(hosts.get(0), distances.get(1));
//
        while(readInput.hasNext()){
            sims.add(readInput.nextInt());
        }

//
//        while(readInput.hasNext()){
//            for(int m = 0; m < 4; m++){
//                sims.add(readInput.nextInt());
//            }
//
//            int index = 0;
//            for(int c = 0; c < hosts.size(); c++){
//                if(Objects.equals(hostsNums.get(c), sims.get(0))){
//                    index = c;
//                    break;
//                }
//            }
//            System.out.println("SIMS:");
//            for(int k : sims){
//                System.out.println(k);
//            }
//     
//
//        }
        int index = 0;
        int count = 0;
        int indexEnd = 4;
        for(int w = 0; w < (sims.size()/4); w++){

            ArrayList<Integer> tempSim = new ArrayList<>();
            for (; index < indexEnd; index++) {
                tempSim.add(sims.get(index));
            }

//            System.out.println("TEMP SIM:");
//            for (int q : tempSim) {
//                System.out.println(q);
//            }
            SimpleHost src = hosts.get(hostsNums.indexOf(tempSim.get(0)));
            //retrieve host with that number -- tempSim(0) gets the number of the host
            //get index of host with that particular number
            src.sendPings(tempSim.get(1), tempSim.get(2), tempSim.get(3));
            //index++;


            indexEnd+=4;
            tempSim.clear();
        }


//        for (int index = 4; index < 8; index++) {
//            tempSim.add(sims.get(index));
//        }
//
//        System.out.println("TEMP SIM:");
//        for (int q : tempSim) {
//            System.out.println(q);
//        }
//        src = hosts.get(hostsNums.indexOf(tempSim.get(0)));
//        //retrieve host with that number -- tempSim(0) gets the number of the host
//        //get index of host with that particular number
//        src.sendPings(tempSim.get(1), tempSim.get(2), tempSim.get(3));
//
//        tempSim.clear();

        while(list.size() > 0){
            Event e = list.removeFirst();
            e.handle();
//                if(e.getId() == 1 || e.getId() == 3 || e.getId() == 5 || e.getId() == 7){
//                    e.cancel();
//                    count++;
//                }
//|| e.getId() == 3 || e.getId() == 5 || e.getId() == 7

        }




    }
}
