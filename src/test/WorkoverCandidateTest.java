package test;

import static org.junit.jupiter.api.Assertions.*;

import appclasses.WorkoverCandidate;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorkoverCandidateTest {
   @Test
   public void testCreation() {
      WorkoverCandidate wc = WorkoverCandidate.add(1234, 25, 3, 0.1);
      assertAll(
           () -> assertEquals(1234, wc.getWellNumber()),
           () -> assertEquals(25, wc.getNetPay()),
           () -> assertEquals(3, wc.getAdjacent()),
           () -> assertEquals(0.1, wc.getPorosity(), 0.001));
   }

   @Test
   public void testCreateFail() {
      int no = 1234;
      int pay = 25;
      int inj = 3;
      double por = 0.31;
      assertThrows(IllegalStateException.class,
                   () -> WorkoverCandidate.add(no, pay, inj, por));
   }

   @Test
   public void testCreateFail2() {
      int no = 1234;
      int pay = 25;
      int inj = 3;
      double por = -1.0;
      assertThrows(IllegalStateException.class,
                   () -> WorkoverCandidate.add(no, pay, inj, por));
   }

   @Test
   public void testComparison() {
      List<WorkoverCandidate> list = new ArrayList<>();
      WorkoverCandidate one = WorkoverCandidate.add(1234, 25, 3, 0.1);
      WorkoverCandidate two = WorkoverCandidate.add(1235, 26, 3,0.1);
      WorkoverCandidate three = WorkoverCandidate.add(1236, 25, 3, 0.12);
      list.add(one);
      list.add(two);
      list.add(three);
      Collections.sort(list, WorkoverCandidate.COMPLETION_ORDER);
      assertAll(
           () -> assertEquals(one, list.get(2)),
           () -> assertEquals(two, list.get(0)),
           () -> assertEquals(three, list.get(1)));
   }

   @Test
   public void testOverrides() {
      WorkoverCandidate one = WorkoverCandidate.add(1234, 25, 3, 0.1);
      WorkoverCandidate two = WorkoverCandidate.add(1234, 25, 3, 0.1);
      assertAll(
           () -> assertTrue(one.equals(two)),
           () -> assertTrue(one.hashCode() == two.hashCode()),
           () -> assertEquals("1234", one.toString()));
   }
}