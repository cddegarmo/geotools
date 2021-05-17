package test;

import appclasses.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MonteCarloTest {
   @Test
   public void testSpinSuccess() {
      assertEquals(1, MonteCarlo.runSpinner(99));
   }

   @Test
   public void testSpinFail() {
      assertEquals(0, MonteCarlo.runSpinner(1));
   }

   @Test
   public void testParameters() {
      assertThrows(IllegalArgumentException.class,
                   () -> MonteCarlo.runSpinner(100));
   }

   @Test
   public void testEventualP1() {
      int result = 0;
      while (result == 0)
         result = MonteCarlo.runSpinner(1);
   }
}