package appclasses;

import static appclasses.MonteCarlo.*;
import java.util.*;

public class Volumetrics {
    public static final int BBLS = 7758;       // barrels (42 gallon) per acre-foot
    public static final int MCF = 43_560;       // cubic feet of gas per acre-foot
    public static final double MILE = 5280.0;  // one mile in feet

    // Prohibit instantiation
    private Volumetrics() {}

    public static double oilInPlace(double area, double thickness,
                                        double oilSaturation, double porosity,
                                        double volumeFactor) {

        double value = BBLS * area * thickness * oilSaturation * porosity;
        if (value < 0 || volumeFactor < 0)
            throw new IllegalArgumentException("Negative parameters are invalid for this calculation");
        value /= volumeFactor;

        // Round off to two decimal places
        return Math.round(value * 100) / 100.0;
    }

    public static double oilInPlaceMC(double areaLow, double areaHigh, double thicknessLow, double thicknessHigh,
                                      double satLow, double satHigh, double porLow, double porHigh,
                                      double vfLow, double vfHigh) {
        double area = getRandDouble(areaLow, areaHigh);
        double thickness = getRandDouble(thicknessLow, thicknessHigh + 1);
        double saturation = getRandDouble(satLow, satHigh);
        double porosity = getRandDouble(porLow, porHigh);
        double volumeFactor = getRandDouble(vfLow, vfHigh);
        return oilInPlace(area, thickness, saturation, porosity, volumeFactor);
    }

    public static double oilInPlaceMCCast(List<Double> parameters) {
        double area = getRandDouble(parameters.get(0), parameters.get(1));
        double thickness = getRandDouble(parameters.get(2), parameters.get(3));
        double saturation = getRandDouble(parameters.get(4), parameters.get(5));
        double porosity = getRandDouble(parameters.get(6), parameters.get(7));
        double volumeFactor = getRandDouble(parameters.get(8), parameters.get(9));
        return oilInPlace(area, thickness, saturation, porosity, volumeFactor);
    }

    public static double gasInPlace(double area, double thickness,
                                    double gasSaturation, double porosity,
                                    double volumeFactor) {

        double value = MCF * area * thickness * gasSaturation * gasSaturation * porosity;
        if (value < 0 || volumeFactor < 0)
            throw new IllegalArgumentException("Negative parameters are invalid for this calculation");
        value /= volumeFactor;

        // Round off to two decimal places
        return Math.round(value * 100) / 100.0;
    }

    public static double gasInPlaceMC(double areaLow, double areaHigh, double thicknessLow, double thicknessHigh,
                                      double satLow, double satHigh, double porLow, double porHigh,
                                      double vfLow, double vfHigh) {
        double area = getRandDouble(areaLow, areaHigh);
        double thickness = getRandDouble(thicknessLow, thicknessHigh + 1);
        double saturation = getRandDouble(satLow, satHigh);
        double porosity = getRandDouble(porLow, porHigh);
        double volumeFactor = getRandDouble(vfLow, vfHigh);
        return gasInPlace(area, thickness, saturation, porosity, volumeFactor);
    }

    // Generally only used to evaluate potential for water disposal, or as an academic exercise
    public static double waterInPlace(double area, double thickness,
                                      double porosity) {

        double value = BBLS * area * thickness * porosity;
        if (value < 0)
            throw new IllegalArgumentException("Negative parameters are invalid for this calculation");

        return Math.round(value * 100) / 100.0;
    }

    // Normalize all production volumes to 1-mile lateral
    private static double getLengthFactor(int lateralLength) {
        double result = lateralLength / MILE;
        return Math.round(result * 1000) / 1000.0;
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
