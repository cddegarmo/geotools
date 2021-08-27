package user;

import appclasses.Volumetrics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class RunVolumes {

   // Formatter serves to parse parameters from csv file
   private static class ParameterFormatter {
      private final ResourceBundle resource;
      private final Path dataFolder;

      private ParameterFormatter() {
         resource = ResourceBundle.getBundle("resources.config");
         Path current = Path.of("").toAbsolutePath();
         dataFolder = current.resolve(resource.getString("data.folder"));
      }
   }

   private static final ParameterFormatter pf = new ParameterFormatter();

   // Prohibit instantiation and subclassing
   private RunVolumes() {}

   private static List<Double> parseParameters(String text) {
      if (text == null)
         throw new IllegalArgumentException("Check csv file for proper contents.");
      List<Double> container = new ArrayList<>();
      String[] values = text.split(",");
      for (String value : values) {
         container.add(Double.parseDouble(value));
      }
      return container;
   }

   private static String loadParameters(Path file) {
      try {
         String content = Files.readString(file);
         return content;
      } catch (IOException e) {
         e.printStackTrace();
      }
      return null;
   }

   // User specifies number of runs to be conducted. Resulting output
   // can be pasted into spreadsheet for risk analysis
   public static void main(String[] args) {
      Path file = pf.dataFolder.resolve(
           pf.resource.getString("data.file"));
      List<Double> parameters = parseParameters(loadParameters(file));
      List<Double> results = new ArrayList<>();
      Scanner s = new Scanner(System.in);
      System.out.print("How many runs would you like to conduct? ");
      int runs = Integer.parseInt(s.nextLine());
      for (int i = 0; i < runs; i++) {
         double result = Volumetrics.oilInPlaceMCCast(parameters);
         results.add(result);
         System.out.printf("%,.2f%n", result);
      }
      Collections.sort(results);
      System.out.print("P50 value of results: ");
      System.out.printf("%,.2f%n",results.get(results.size() / 2));
   }
}
