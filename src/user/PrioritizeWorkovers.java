package user;

import appclasses.WorkoverCandidate;

import java.nio.file.Path;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.*;
import java.io.*;

public class PrioritizeWorkovers {

    private static class WorkoverFormatter {
        private final ResourceBundle resource;
        private final Path dataFolder;
        private final MessageFormat workoverFormat;

        private WorkoverFormatter() {
            resource = ResourceBundle.getBundle("resources.config");
            workoverFormat = new MessageFormat(resource.getString("workover.data"));
            Path current = Path.of("").toAbsolutePath();
            dataFolder = current.resolve(resource.getString("data.folder"));
        }
    }

    private static final WorkoverFormatter wf = new WorkoverFormatter();

    private static WorkoverCandidate parseCandidate(String text) {
        WorkoverCandidate wc = null;
        try {
            Object[] values = wf.workoverFormat.parse(text);
            int wellNumber = Integer.parseInt((String) values[0]);
            int netPay     = Integer.parseInt((String) values[1]);
            int injectors  = Integer.parseInt((String) values[2]);
            double porosity = Double.parseDouble((String) values[3]);
            wc = WorkoverCandidate.add(wellNumber, netPay, injectors, porosity);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            System.out.println("Check configuration of csv file of candidates!");
        }
        return wc;
    }

    private static List<WorkoverCandidate> getCandidates(Path file) {
        List<WorkoverCandidate> wcs = new ArrayList<>();
        try (var reader = new BufferedReader(new FileReader(String.valueOf(file)))) {
            String line;
            while ((line = reader.readLine()) != null)
                wcs.add(parseCandidate(line));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return wcs;
    }

    private static void inputFromFile() {
        Path file = wf.dataFolder.resolve(wf.resource.getString("workovers.file"));
        List<WorkoverCandidate> wells = getCandidates(file);
        Collections.sort(wells, WorkoverCandidate.WEIGHTED_ORDER);
        System.out.println("Wells should be completed in the following order: ");
        for (WorkoverCandidate well : wells) {
            System.out.println(well.getWellNumber());
        }
    }

    private static void userInput() {
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

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Do you want to load from file (1) or manually (2)? ");
        int choice = s.nextInt();
        if (choice == 1)
            inputFromFile();
        else if (choice == 2)
            userInput();
        else
            throw new IllegalArgumentException("Enter 1 or 2 for program to execute correctly.");
    }
}
