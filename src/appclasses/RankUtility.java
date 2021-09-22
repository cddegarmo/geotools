package appclasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankUtility {

   private RankUtility() {}

   private static <T extends Number & Comparable<T>> List<T> retrieveRange(Map<Integer, List<T>> ranges,
                                                                           T value) {
      List<T> range = ranges.values()
                            .stream()
                            .filter(list -> value.compareTo(list.get(1)) <= 0 &&
                                 value.compareTo(list.get(0)) >= 0)
                            .findAny()
                            .orElse(new ArrayList<>());
      return range;
   }

   public static boolean porosityUnrealistic(double porosity) {
      double lowerBound = 0.0;
      double upperBound = 0.3;
      if (porosity > upperBound || porosity < lowerBound)
         return true;
      return false;
   }

   public static boolean waterSaturationUnrealistic(double waterSaturation) {
      double lowerBound = 0.01;
      double upperBound = 1.0;
      if (waterSaturation > upperBound || waterSaturation < lowerBound)
         return true;
      return false;
   }

   public static int porosityRank(double porosity) {
      Map<Integer, List<Double>> ranges = new HashMap<>();
      ranges.put(1, List.of(0.0, 0.05));
      ranges.put(2, List.of(0.06, 0.1));
      ranges.put(3, List.of(0.11, 0.15));
      ranges.put(4, List.of(0.16, 0.2));
      ranges.put(5, List.of(0.21, 0.3));
      List<Double> range = retrieveRange(ranges, porosity);
      int result = 0;
      for (Integer score : ranges.keySet())
         if (ranges.get(score) == range)
            result = score;
      return result;
   }

   public static int netPayRank(int netPay) {
      Map<Integer, List<Integer>> ranges = new HashMap<>();
      ranges.put(1, List.of(0, 10));
      ranges.put(2, List.of(11, 20));
      ranges.put(3, List.of(21, 30));
      ranges.put(4, List.of(31, 40));
      ranges.put(5, List.of(41, 100));
      List<Integer> range = retrieveRange(ranges, netPay);
      int result = 0;
      for (Integer score : ranges.keySet())
         if (ranges.get(score) == range)
            result = score;
      return result;
   }

   public static int waterSaturationRank(double waterSaturation) {
      Map<Integer, List<Double>> ranges = new HashMap<>();
      ranges.put(1, List.of(0.8, 1.0));
      ranges.put(2, List.of(0.6, 0.79));
      ranges.put(3, List.of(0.4, 0.59));
      ranges.put(4, List.of(0.2, 0.39));
      ranges.put(5, List.of(0.0, 0.19));
      List<Double> range = retrieveRange(ranges, waterSaturation);
      int result = 0;
      for (Integer score : ranges.keySet())
         if (ranges.get(score) == range)
            result = score;
      return result;
   }
}
