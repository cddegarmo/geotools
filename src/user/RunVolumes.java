package user;

import appclasses.*;

import java.util.concurrent.ThreadLocalRandom;

public class RunVolumes {
   public static void main(String[] args) {
      for (int i = 0; i < 30; i++) {
         double ooip = Volumetrics.oilInPlaceMC(240, 298,
                                                78, 92,
                                                0.53, 0.78,
                                                0.12, 0.18,
                                                1.09, 1.23);
         System.out.printf("%,.2f%n", ooip);
      }
   }
}
