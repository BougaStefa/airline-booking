package com.bougastefa.app;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

// Utility class that handles writing data to file 
// Utilizing reflection and annotations in order to maintain the correct column order in the CSV file
public class CSVUtilities {
  // Flag for testing purposes. Stops files from being created during tests.
  private static boolean testMode = false;

  public static void setTestMode(boolean isTestMode) {
    testMode = isTestMode;
  }

  // Final export to CSV
  // Get's an array of all the getter methods of an object and calls them in that
  // order before writing them to file
  public static void exportToCsv(Object object, String filename) {
    if (object == null) {
      throw new IllegalArgumentException("The object is null");
    }
    if (testMode) {
      return;
    }
    try (FileWriter fw = new FileWriter(filename, true)) {
      // Retrieve and sort methods
      Method[] orderedMethods = getOrderedMethods(object);

      String data = Arrays.stream(orderedMethods).map(method -> invokeGetter(object, method))
          .collect(Collectors.joining(","));
      fw.write(data + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Sort methods in the right order based on annotation
  public static Method[] getOrderedMethods(Object object) {
    return Arrays.stream(object.getClass().getDeclaredMethods())
        .filter(method -> method.isAnnotationPresent(InputOrder.class))
        .sorted(Comparator.comparing(m -> m.getAnnotation(InputOrder.class)
            .value()))
        .toArray(Method[]::new);
  }

  // Get result of calling the getter and returns it as a string
  public static String invokeGetter(Object object, Method method) {
    try {
      Object value = method.invoke(object);
      return value != null ? value.toString() : "";
    } catch (Exception e) {
      return "";
    }
  }
}
