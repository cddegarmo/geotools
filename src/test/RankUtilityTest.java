package test;

import appclasses.RankUtility;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RankUtilityTest {

   @Test
   public void testPorosityRanges() {
      assertTrue(RankUtility.porosityUnrealistic(0.31));
      assertTrue(RankUtility.porosityUnrealistic(-0.1));
   }

   @Test
   public void testSaturations() {
      assertTrue(RankUtility.waterSaturationUnrealistic(0.0));
      assertTrue(RankUtility.waterSaturationUnrealistic(-0.01));
      assertTrue(RankUtility.waterSaturationUnrealistic(1.01));
   }

   @Test
   public void testPorosityRanks() {
      assertEquals(3, RankUtility.porosityRank(0.11));
      assertEquals(3, RankUtility.porosityRank(0.15));
      assertEquals(3, RankUtility.porosityRank(0.13));
      assertEquals(2, RankUtility.porosityRank(0.06));
      assertEquals(2, RankUtility.porosityRank(0.1));
      assertEquals(2, RankUtility.porosityRank(0.08));
   }

   @Test
   public void testNetPayRanks() {
      assertEquals(1, RankUtility.netPayRank(0));
      assertEquals(1, RankUtility.netPayRank(10));
      assertEquals(1, RankUtility.netPayRank(8));
      assertEquals(5, RankUtility.netPayRank(41));
      assertEquals(5, RankUtility.netPayRank(100));
   }

   @Test
   public void testWaterSaturationRanks() {
      assertEquals(1, RankUtility.waterSaturationRank(0.8));
      assertEquals(2, RankUtility.waterSaturationRank(0.79));
      assertEquals(3, RankUtility.waterSaturationRank(0.59));
      assertEquals(4, RankUtility.waterSaturationRank(0.2));
      assertEquals(5, RankUtility.waterSaturationRank(0.0));
   }
}