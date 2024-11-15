package com.bougastefa.app;

import java.lang.annotation.*;

//Defines the order items are to appear in the CSV file

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface InputOrder {
  int value();
}
