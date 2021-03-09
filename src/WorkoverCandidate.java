import java.util.*;
import static java.util.Comparator.*;

public class WorkoverCandidate {
    private final int wellNumber;
    private final int netPay;
    private final int adjacentInjectors;
    private final double porosity;

    private WorkoverCandidate(int number, int footage,
                              int injs, double poro) {
        wellNumber = number;
        netPay = footage;
        adjacentInjectors = injs;
        porosity = poro;
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
    public String toString() {
        return Integer.toString(wellNumber);
    }

    public static void main(String[] args) {
        List<WorkoverCandidate> wells = new ArrayList<>();
        WorkoverCandidate alpha = new WorkoverCandidate(1, 41, 2, 0.08);
        WorkoverCandidate beta  = new WorkoverCandidate(2, 49, 1, 0.05);
        wells.add(alpha);
        wells.add(beta);

        Collections.sort(wells, COMPLETION_ORDER);

        for(WorkoverCandidate well : wells)
            System.out.println(well.toString());
    }
}
