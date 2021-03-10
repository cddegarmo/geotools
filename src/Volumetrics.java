import java.math.BigDecimal;
import java.math.RoundingMode;

public class Volumetrics {
    public static final int BBLS = 7_758;  // barrels (42 gallon) per acre-foot
    public static final int MCF = 43_560;  // cubic feet of gas per acre-foot

    private Volumetrics() {}

    public static double oilInPlace(double area, int thickness,
                                        double oilSaturation, double porosity,
                                        double volumeFactor) {

        double value = BBLS * area * thickness * oilSaturation * porosity;
        value /= volumeFactor;

        return Math.round(value * 100.0) / 100.0;
    }

    public static double gasInPlace(double area, int thickness,
                                    double gasSaturation, double porosity,
                                    double volumeFactor) {

        double value = MCF * area * thickness * gasSaturation * gasSaturation * porosity;
        value /= volumeFactor;

        return Math.round(value * 100.0) / 100.0;
    }
}
