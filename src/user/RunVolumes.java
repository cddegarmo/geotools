package user;

import appclasses.Volumetrics;

import java.io.IOException;
import java.nio.file.Files;
import java.text.MessageFormat;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.*;

public class RunVolumes {

   // Formatter serves to parse parameters from csv file
   private static class ParameterFormatter {
      private final ResourceBundle resource;
      private final MessageFormat parameters;
      private final Path dataFolder;

      private ParameterFormatter() {
         resource = ResourceBundle.getBundle("resources.parameters");
         parameters = new MessageFormat(resource.getString("inputs"));
         Path current = Path.of("").toAbsolutePath();
         dataFolder = current.resolve(resource.getString("data.folder"));
      }
   }

   private static final ParameterFormatter pf = new ParameterFormatter();

   // Prohibit instantiation and subclassing
   private RunVolumes() {}

   private static List<Number> parseParameters(String text) {
      List<Number> container = new ArrayList<>();
      try {
         Object[] values = pf.parameters.parse(text);
         for (int i = 0; i < values.length; i++) {
            if (i == 2 || i == 3) {
               container.add(Integer.parseInt((String) values[i]));
               continue;
            }
            container.add(Double.parseDouble((String) values[i]));
         }
      } catch (ParseException e) {
         e.printStackTrace();
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
      List<Number> parameters = parseParameters(loadParameters(file));
      Scanner s = new Scanner(System.in);
      System.out.print("How many runs would you like to conduct? ");
      int runs = Integer.parseInt(s.nextLine());
      for (int i = 0; i < runs; i++) {
         double result = Volumetrics.oilInPlaceMCCast(parameters);
         System.out.printf("%,.2f%n", result);
      }
   }
}
