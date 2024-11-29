package com.bougastefa.app;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CSVUtilities {

  // Final export to CSV
  public static void exportToCsv(Object object, String filename) {
    if (object == null) {
      throw new IllegalArgumentException("The object is null");
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

  // Get result of calling the getter
  public static String invokeGetter(Object object, Method method) {
    try {
      Object value = method.invoke(object);
      return value != null ? value.toString() : "";
    } catch (Exception e) {
      return "";
    }
  }
}
