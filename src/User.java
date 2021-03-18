import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {
    public static void main(String[] args) {
        List<WorkoverCandidate> wells = new ArrayList<>();

        WorkoverCandidate one = WorkoverCandidate.add(3262, 39, 4, 0.075);
        WorkoverCandidate two = WorkoverCandidate.add(3263, 45, 4, 0.069);
        WorkoverCandidate three = WorkoverCandidate.add(4236, 38, 2, 0.066);
        WorkoverCandidate four = WorkoverCandidate.add(3271, 39, 3, 0.067);
        WorkoverCandidate five = WorkoverCandidate.add(3256, 24, 4, 0.07);
        WorkoverCandidate six = WorkoverCandidate.add(3317, 35, 3, 0.064);
        WorkoverCandidate seven = WorkoverCandidate.add(3264, 33, 2, 0.062);
        WorkoverCandidate eight = WorkoverCandidate.add(3226, 35, 2, 0.07);

        wells.add(one);
        wells.add(two);
        wells.add(three);
        wells.add(four);
        wells.add(five);
        wells.add(six);
        wells.add(seven);
        wells.add(eight);

        Collections.sort(wells, WorkoverCandidate.COMPLETION_ORDER);

        for(WorkoverCandidate well : wells)
            System.out.println(well.toString());
    }
}
