package com.bougastefa.app;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;
import java.nio.file.Paths;
import java.util.List;

public class CSVUtilitiesTest {

  // Exporting null object throws the appropriate error
  @Test
  public void testExportToCsv_NullObject() {
    assertThrows(IllegalArgumentException.class, () -> {
      CSVUtilities.exportToCsv(null, "testfile.csv");
    });
  }

  // Test for successfull export
  @Test
  public void testExportToCsv_ValidObject() throws IOException {
    String filename = "testfile.csv";
    TestObject testObject = new TestObject("value1", "value2");

    CSVUtilities.exportToCsv(testObject, filename);

    List<String> lines = Files.readAllLines(Paths.get(filename));
    assertTrue(lines.contains("value1,value2"));

    Files.delete(Paths.get(filename));
  }

  // Test class for the CSV export tests
  private static class TestObject {
    private String field1;
    private String field2;

    public TestObject(String field1, String field2) {
      this.field1 = field1;
      this.field2 = field2;
    }

    @InputOrder(1)
    public String getField1() {
      return field1;
    }

    @InputOrder(2)
    public String getField2() {
      return field2;
    }
  }

  // Test that the methods are correctly ordered based on annotation
  @Test
  public void testGetOrderedMethods() {
    TestObject testObject = new TestObject("field1", "field2");
    Method[] methods = CSVUtilities.getOrderedMethods(testObject);
    // Methods not null and in the right order checks
    assertNotNull(methods);
    assertArrayEquals(new String[] { "getField1", "getField2" },
        Stream.of(methods).map(Method::getName).toArray());
  }

  // Test that the getters are successfully invoked
  @Test
  public void testInvokeGetter_ValidMethod() throws NoSuchMethodException {
    TestObject testObject = new TestObject("value1", "value2");
    Method method = TestObject.class.getMethod("getField1");
    String result = CSVUtilities.invokeGetter(testObject, method);
    assertEquals("value1", result);
  }

  // Test getters returning null values
  @Test
  public void testInvokeGetter_NullValue() throws NoSuchMethodException {
    TestObject testObject = new TestObject(null, "value2");
    Method method = TestObject.class.getMethod("getField1");
    String result = CSVUtilities.invokeGetter(testObject, method);
    assertEquals("", result);
  }

  // Test non existing methods exception handling
  @Test
  public void testInvokeGetter_Exception() throws NoSuchMethodException {
    TestObject testObject = new TestObject("value1", "value2");
    assertThrows(NoSuchMethodException.class, () -> {
      Method method = TestObject.class.getMethod("nonExistentMethod");
      CSVUtilities.invokeGetter(testObject, method);
    });
  }

}
