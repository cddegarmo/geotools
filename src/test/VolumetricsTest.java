package test;
import static appclasses.Volumetrics.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VolumetricsTest {
   @Test
   public void testOilInPlace() {
      assertEquals(18_404_535.45, oilInPlace(240, 87.4, 0.71,
                                             0.18, 1.13), 0.01);
      assertEquals(2_656_723.82, oilInPlace(620, 15.2, 0.43,
                                            0.12, 1.42), 0.01);
   }

   @Test
   public void testOilInPlaceZeroes() {
      assertEquals(0, oilInPlace(240, 0, 0.71,
                                             0.18, 1.13));
   }

   @Test
   public void testOilInPlaceNegatives() {
      assertThrows(IllegalArgumentException.class,
                   () -> oilInPlace(240, -87.4, 0.71,
                                             0.18, 1.13));
   }
}
