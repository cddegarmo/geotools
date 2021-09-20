package appclasses;

import java.util.*;
import static java.util.Comparator.*;

public class WorkoverCandidate {
    private final int wellNumber;
    private final int netPay;
    private final int adjacentInjectors;
    private final double porosity;
    private double waterSaturation;

    // Force static factory, prohibit subclassing
    private WorkoverCandidate(int number, int footage,
                              int injs, double poro, double sw) {
        wellNumber = number;
        netPay = footage;
        adjacentInjectors = injs;
        porosity = poro;
        waterSaturation = sw;
    }

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
        if (RankUtility.porosityUnrealistic(poro))
            throw new IllegalStateException("Porosity values unrealistic. Please modify and try again.");
        else
            return new WorkoverCandidate(number, footage, injs, poro);
    }

    public static WorkoverCandidate add(int number, int footage,
                                        int injs, double poro, double sw) {
        if (RankUtility.porosityUnrealistic(poro))
            throw new IllegalStateException("Porosity values unrealistic. Please modify and try again.");
        else if (RankUtility.waterSaturationUnrealistic(sw))
            throw new IllegalStateException("Water saturation value unrealistic. Please modify and try again");
        else
            return new WorkoverCandidate(number, footage, injs, poro, sw);
    }

    public int getWellNumber()         { return wellNumber;        }
    public int getNetPay()             { return netPay;            }
    public int getAdjacent()           { return adjacentInjectors; }
    public double getPorosity()        { return porosity;          }
    public double getWaterSaturation() { return waterSaturation;   }

    // Comparators serve as main tool for sorting these candidates
    // Comparator #1
    // Sort without water saturation influence, importance is placed heavily
    // on number of adjacent injectors
    public static final Comparator<WorkoverCandidate> COMPLETION_ORDER =
         comparingInt(WorkoverCandidate::getAdjacent)
              .thenComparingInt(WorkoverCandidate::getNetPay)
              .thenComparingDouble(WorkoverCandidate::getPorosity)
              .reversed();

    // Comparator #2
    // Use weighted sorting, incorporating all fields of class. Should be
    // modified on a field-by-field basis
    public static final Comparator<WorkoverCandidate> WEIGHTED_ORDER =
         comparingDouble(WorkoverCandidate::getScore)
              .reversed();


    private double getScore() {
        double quarterWeight = 0.25;
        double fifthWeight = 0.2;
        double thirtyWeight = 0.3;
        double result = (injRank() * quarterWeight) +
             (RankUtility.porosityRank(porosity) * quarterWeight) +
             (RankUtility.netPayRank(netPay) * fifthWeight) +
             (RankUtility.waterSaturationRank(waterSaturation) * thirtyWeight);
        return Math.round(result * 100) / 100.0;
    }

    private int injRank() {
        return adjacentInjectors;
    }

    // Standard overrides of methods inherited from Object
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof WorkoverCandidate))
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
