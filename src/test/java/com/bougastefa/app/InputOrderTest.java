package com.bougastefa.app;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;

public class InputOrderTest {
  // Defining and annotating two test methods
  @InputOrder(1)
  public void methodOne() {
  }

  @InputOrder(2)
  public void methodTwo() {
  }

  @Test
  public void testInputOrderAnnotation() throws NoSuchMethodException {
    // Using reflection to get the methods from the class
    Method methodOne = InputOrderTest.class.getMethod("methodOne");
    Method methodTwo = InputOrderTest.class.getMethod("methodTwo");

    // Getting the annotatio from each of the methods
    InputOrder annotationOne = methodOne.getAnnotation(InputOrder.class);
    InputOrder annotationTwo = methodTwo.getAnnotation(InputOrder.class);

    // Ensuring annotations are present
    assertNotNull(annotationOne);
    assertNotNull(annotationTwo);

    // Ensuring annotations have the correct values
    assertEquals(1, annotationOne.value());
    assertEquals(2, annotationTwo.value());
  }
}
