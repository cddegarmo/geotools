package user;

import appclasses.Petrophysics;
import java.util.Scanner;

// Program run from command line to get a quick calculation of water saturation via
// Archie equation
public class WaterSaturation {
   public static void main(String[] args) {
      Scanner s = new Scanner(System.in);
      System.out.print("Enter the saturation exponent: ");
      double n = s.nextDouble();
      System.out.print("Enter the water resistivity: ");
      double rw = s.nextDouble();
      System.out.print("Enter the porosity in decimal: ");
      double phi = s.nextDouble();
      System.out.print("Enter the cementation factor: ");
      double m = s.nextDouble();
      System.out.print("Enter the formation resistivity: ");
      double rf = s.nextDouble();
      double result = Petrophysics.waterSaturation(n, rw, phi, m, rf);
      if (result < 0 || result > 1)
         System.out.println("Check parameters for validity. Result computed as negative or greater than one.");
      System.out.println(result);
   }
}
