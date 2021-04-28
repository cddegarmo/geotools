package appclasses;

import java.util.*;
import static java.util.Comparator.*;

public class WorkoverCandidate {
    private final int wellNumber;
    private final int netPay;
    private final int adjacentInjectors;
    private final double porosity;

    // Force static factory, prohibit subclassing
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

    public int getWellNumber()  { return wellNumber;        }
    public int getNetPay()      { return netPay;            }
    public int getAdjacent()    { return adjacentInjectors; }
    public double getPorosity() { return porosity;          }

    // Comparator serves as main tool for sorting these candidates
    // Future versions may incorporate various sorting criteria
    public static final Comparator<WorkoverCandidate> COMPLETION_ORDER =
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
}
