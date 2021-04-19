package user;

import appclasses.WorkoverCandidate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PrioritizeWorkovers {
    public static void main(String[] args) {
        var in = new Scanner(System.in);
        List<WorkoverCandidate> wells = new ArrayList<>();
        do {
            System.out.print("Please enter well number: ");
            int no = in.nextInt();
            System.out.print("Enter the net pay, in feet: ");
            int ft = in.nextInt();
            System.out.print("Enter the number of adjacent injectors: ");
            int inj = in.nextInt();
            System.out.print("Enter the porosity, in decimal: ");
            double por = in.nextDouble();
            wells.add(WorkoverCandidate.add(no, ft, inj, por));
            System.out.print("Press q to quit or any other character to continue adding wells: ");
        } while (!in.next().matches("[Qq]"));

        Collections.sort(wells, WorkoverCandidate.COMPLETION_ORDER);
        System.out.println("Wells should be completed in the following order:");
        for (WorkoverCandidate well : wells)
            System.out.println(well.getWellNumber());
    }
}
