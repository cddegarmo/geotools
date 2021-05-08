package appclasses;

import java.util.concurrent.ThreadLocalRandom;

public class MonteCarlo {
   public static final int getRandInt(int bound) {
      return ThreadLocalRandom.current().nextInt(bound);
   }

   public static final int getRandInt(int lower, int bound) {
      return ThreadLocalRandom.current().nextInt(lower, bound);
   }

   public static final double getRandDouble(double bound) {
      return ThreadLocalRandom.current().nextDouble(bound);
   }

   public static final double getRandDouble(double lower, double bound) {
      return ThreadLocalRandom.current().nextDouble(lower, bound);
   }

   private MonteCarlo() {}
}
