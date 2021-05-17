package appclasses;

import java.util.concurrent.ThreadLocalRandom;

public class MonteCarlo {
   public static int runSpinner(int chance) {
      if (chance < 1 || chance > 99)
         throw new IllegalArgumentException("Chance of success must be between 0 and 100, exclusive.");
      int probability = ThreadLocalRandom.current().nextInt(1, 100);
      if (probability <= chance) {
         System.out.println("Success!");
         return 1;
      }
      else {
         System.out.println("Failure");
         return 0;
      }
   }

   public static int getRandInt(int bound) {
      return ThreadLocalRandom.current().nextInt(bound);
   }

   public static int getRandInt(int lower, int bound) {
      return ThreadLocalRandom.current().nextInt(lower, bound);
   }

   public static double getRandDouble(double bound) {
      return ThreadLocalRandom.current().nextDouble(bound);
   }

   public static double getRandDouble(double lower, double bound) {
      return ThreadLocalRandom.current().nextDouble(lower, bound);
   }

   private MonteCarlo() {}

   public static void main(String[] args) {
      runSpinner(90);
   }
}
