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
        if(porosityUnrealistic(poro))
            throw new IllegalStateException("Porosity values unrealistic. Please modify and try again.");
        else
            return new WorkoverCandidate(number, footage, injs, poro);
    }

    public static WorkoverCandidate add(int number, int footage,
                                        int injs, double poro, double sw) {
        if(porosityUnrealistic(poro))
            throw new IllegalStateException("Porosity values unrealistic. Please modify and try again.");
        else if(waterSaturationUnrealistic(sw))
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


    private final double getScore() {
        double result = (injRank() * 0.25) +
                     (porosityRank() * 0.25) +
                     (netPayRank() * 0.2) +
                     (waterSaturationRank() * 0.3);
        return Math.round(result * 100) / 100.0;
    }

    private int injRank() {
        return adjacentInjectors;
    }

    private static boolean porosityUnrealistic(double porosity) {
        if (porosity > 0.3 || porosity < 0.0)
            return true;
        return false;
    }

    private static boolean waterSaturationUnrealistic(double waterSaturation) {
        if (waterSaturation > 1.0 || waterSaturation < 0.01)
            return true;
        return false;
    }

    private int porosityRank() {
        Map<Integer, List<Double>> ranges = new HashMap<>();
        ranges.put(1, List.of(0.0, 0.05));
        ranges.put(2, List.of(0.06, 0.1));
        ranges.put(3, List.of(0.11, 0.15));
        ranges.put(4, List.of(0.16, 0.2));
        ranges.put(5, List.of(0.21, 0.3));
        List<Double> range = ranges.values()
             .stream()
             .filter(list -> porosity <= list.get(1) &&
                  porosity >= list.get(0))
             .findAny()
             .orElse(new ArrayList<>());
        int result = 0;
        for (Integer score : ranges.keySet())
            if (ranges.get(score) == range)
                result = score;
        return result;
    }

    private int netPayRank() {
        Map<Integer, List<Integer>> ranges = new HashMap<>();
        ranges.put(1, List.of(0, 10));
        ranges.put(2, List.of(11, 20));
        ranges.put(3, List.of(21, 30));
        ranges.put(4, List.of(31, 40));
        ranges.put(5, List.of(41, 100));
        List<Integer> range = ranges.values()
             .stream()
             .filter(list -> netPay <= list.get(1) &&
                  netPay >= list.get(0))
             .findAny()
             .orElse(new ArrayList<>());
        int result = 0;
        for (Integer score : ranges.keySet())
            if (ranges.get(score) == range)
                result = score;
        return result;
    }

    private int waterSaturationRank() {
        Map<Integer, List<Double>> ranges = new HashMap<>();
        ranges.put(1, List.of(1.0, 0.8));
        ranges.put(2, List.of(0.79, 0.6));
        ranges.put(3, List.of(0.59, 0.4));
        ranges.put(4, List.of(0.39, 0.2));
        ranges.put(5, List.of(0.19, 0.0));
        List<Double> range = ranges.values()
             .stream()
             .filter(list -> waterSaturation <= list.get(1) &&
                  waterSaturation >= list.get(0))
             .findAny()
             .orElse(new ArrayList<>());
        int result = 0;
        for (Integer score : ranges.keySet())
            if (ranges.get(score) == range)
                result = score;
        return result;
    }  

    // Standard overrides of methods inherited from Object
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
