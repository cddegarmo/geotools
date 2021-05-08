package appclasses;

import static appclasses.MonteCarlo.*;

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

    public static void main(String[] args) {
        double result = oilInPlace(300, 25, 0.7, 0.15, 1.12);
        System.out.println(result);
        System.out.printf("%,.2f", result);
    }
}
