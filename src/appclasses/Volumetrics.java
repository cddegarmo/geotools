package appclasses;

import static appclasses.MonteCarlo.*;
import java.util.*;

public class Volumetrics {
    public static final int BBLS = 7758;  // barrels (42 gallon) per acre-foot
    public static final int MCF = 43560;  // cubic feet of gas per acre-foot

    // Prohibit instantiation
    private Volumetrics() {}

    public static double oilInPlace(double area, int thickness,
                                        double oilSaturation, double porosity,
                                        double volumeFactor) {

        double value = BBLS * area * thickness * oilSaturation * porosity;
        value /= volumeFactor;

        return Math.round(value * 100) / 100.0;
    }

    public static double oilInPlaceMC(double areaLow, double areaHigh, int thicknessLow, int thicknessHigh,
                                      double satLow, double satHigh, double porLow, double porHigh,
                                      double vfLow, double vfHigh) {
        double area = getRandDouble(areaLow, areaHigh);
        int thickness = getRandInt(thicknessLow, thicknessHigh + 1);
        double saturation = getRandDouble(satLow, satHigh);
        double porosity = getRandDouble(porLow, porHigh);
        double volumeFactor = getRandDouble(vfLow, vfHigh);
        return oilInPlace(area, thickness, saturation, porosity, volumeFactor);
    }

    public static double oilInPlaceMCCast(List<? extends Number> parameters) {
        double area = getRandDouble((Double) parameters.get(0), (Double) parameters.get(1));
        int thickness = getRandInt((Integer) parameters.get(2), (Integer) parameters.get(3));
        double saturation = getRandDouble((Double) parameters.get(4), (Double) parameters.get(5));
        double porosity = getRandDouble((Double) parameters.get(6), (Double) parameters.get(7));
        double volumeFactor = getRandDouble((Double) parameters.get(8), (Double) parameters.get(9));
        return oilInPlace(area, thickness, saturation, porosity, volumeFactor);
    }

    public static double gasInPlace(double area, int thickness,
                                    double gasSaturation, double porosity,
                                    double volumeFactor) {

        double value = MCF * area * thickness * gasSaturation * gasSaturation * porosity;
        value /= volumeFactor;

        return Math.round(value * 100) / 100.0;
    }

    public static double gasInPlaceMC(double areaLow, double areaHigh, int thicknessLow, int thicknessHigh,
                                      double satLow, double satHigh, double porLow, double porHigh,
                                      double vfLow, double vfHigh) {
        double area = getRandDouble(areaLow, areaHigh);
        int thickness = getRandInt(thicknessLow, thicknessHigh + 1);
        double saturation = getRandDouble(satLow, satHigh);
        double porosity = getRandDouble(porLow, porHigh);
        double volumeFactor = getRandDouble(vfLow, vfHigh);
        return gasInPlace(area, thickness, saturation, porosity, volumeFactor);
    }

    // Generally only used to evaluate potential for water disposal, or as an academic exercise
    public static double waterInPlace(double area, int thickness,
                                      double porosity) {

        double value = BBLS * area * thickness * porosity;

        return Math.round(value * 100) / 100.0;
    }

    // Normalize all production volumes to 1-mile lateral
    private static double getLengthFactor(int lateralLength) {
         return Math.round(lateralLength * 1_000) / 5_280.0;
    }

    // Rough calculation of remaining reserves in a conventional well
    private static int getRemainingVolume(int lastYearProduction) {
        return lastYearProduction * 7;
    }

    // Calculate EUR of a well by summing the cumulative production with remaining potential
    // normalized to lateral length
    public static int calculateEUR(int cumulativeProduction, int lastYearProduction,
                                   int lateralLength) {
        int totalProduction = cumulativeProduction + getRemainingVolume(lastYearProduction);
        return (int) (totalProduction / getLengthFactor(lateralLength));
    }
}
