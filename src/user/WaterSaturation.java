package user;

import appclasses.Petrophysics;
import java.util.Scanner;

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
      System.out.println(result);
   }
}
