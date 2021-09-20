package appclasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankUtility {

   private RankUtility() {}

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

   public static int netPayRank(int netPay) {
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

   public static int waterSaturationRank(double waterSaturation) {
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
}
