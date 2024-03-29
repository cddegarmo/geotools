package appclasses;

import java.util.concurrent.ThreadLocalRandom;

public class MonteCarlo {

   // This method can be used as a tool to test for success or failure
   // of an event, returning 1 (for success) or 0.
   // Ideally this should be useful for evaluating the average result with
   // many tens or hundreds of runs.
   public static int runSpinner(int chance) {
      int lowestChance = 1;
      int highestChance = 99;
      if (chance < lowestChance || chance > highestChance)
         throw new IllegalArgumentException("Chance of success must be between 0 and 100, exclusive.");
      int probability = ThreadLocalRandom.current().nextInt(lowestChance, highestChance + 1);
      if (probability <= chance) {
         System.out.println("Success!");
         return 1;
      } else {
         System.out.println("Failure");
         return 0;
      }
   }

   // This group of methods serves to reduce verbosity in other parts of the program
   // by eliminating the need to type out the full method signature to generate a
   // thread-safe random number.

   public static double getRandDouble(double bound) {
      return ThreadLocalRandom.current().nextDouble(bound);
   }

   public static double getRandDouble(double lower, double bound) {
      return ThreadLocalRandom.current().nextDouble(lower, bound);
   }

   // Prohibit instantiation and subclassing
   private MonteCarlo() {}
}
