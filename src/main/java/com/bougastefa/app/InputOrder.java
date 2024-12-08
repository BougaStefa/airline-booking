package com.bougastefa.app;

import java.lang.annotation.*;

//Annotating the method declaration in order to retain order.

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
// Annotation can only be used on methods and is retained at runtime for
// reflection
public @interface InputOrder {
  int value();
}
