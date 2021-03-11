import java.util.*;
import static java.util.Comparator.*;

public class WorkoverCandidate {
    private final int wellNumber;
    private final int netPay;
    private final int adjacentInjectors;
    private final double porosity;

    // Prohibit instantiation outside of static factory
    private WorkoverCandidate(int number, int footage,
                              int injs, double poro) {
        wellNumber = number;
        netPay = footage;
        adjacentInjectors = injs;
        porosity = poro;
    }

    public static WorkoverCandidate add(int number, int footage,
                                        int injs, double poro) {
        if(poro > 0.3 || poro < 0.0)
            throw new IllegalStateException("Porosity values unrealistic. Please modify and try again.");
        else
            return new WorkoverCandidate(number, footage, injs, poro);
    }

    private int getNetPay()      { return netPay;            }
    private int getAdjacent()    { return adjacentInjectors; }
    private double getPorosity() { return porosity;          }

    private static final Comparator<WorkoverCandidate> COMPLETION_ORDER =
            comparingInt(WorkoverCandidate::getAdjacent)
            .thenComparingInt(WorkoverCandidate::getNetPay)
            .thenComparingDouble(WorkoverCandidate::getPorosity)
                    .reversed();

    @Override
    public boolean equals(Object o) {
        if(o == this)
            return true;
        if(!(o instanceof WorkoverCandidate))
            return false;
        WorkoverCandidate wc = (WorkoverCandidate) o;
        return wc.wellNumber == wellNumber && wc.netPay == netPay;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(wellNumber);
        result = 31 * result + Integer.hashCode(netPay);
        return result;
    }

    @Override
    public String toString() {
        return Integer.toString(wellNumber);
    }

    // Example client code
    public static void main(String[] args) {
        List<WorkoverCandidate> wells = new ArrayList<>();

        WorkoverCandidate one = new WorkoverCandidate(3262, 39, 4, 0.075);
        WorkoverCandidate two = new WorkoverCandidate(3263, 45, 4, 0.069);
        WorkoverCandidate three = new WorkoverCandidate(4236, 38, 2, 0.066);
        WorkoverCandidate four = new WorkoverCandidate(3271, 39, 3, 0.067);
        WorkoverCandidate five = new WorkoverCandidate(3256, 24, 4, 0.07);
        WorkoverCandidate six = new WorkoverCandidate(3317, 35, 3, 0.064);
        WorkoverCandidate seven = new WorkoverCandidate(3264, 33, 2, 0.062);
        WorkoverCandidate eight = new WorkoverCandidate(3226, 35, 2, 0.07);

        wells.add(one);
        wells.add(two);
        wells.add(three);
        wells.add(four);
        wells.add(five);
        wells.add(six);
        wells.add(seven);
        wells.add(eight);

        Collections.sort(wells, COMPLETION_ORDER);

        for(WorkoverCandidate well : wells)
            System.out.println(well.toString());
    }
}
