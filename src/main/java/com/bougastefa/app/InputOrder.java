package com.bougastefa.app;

import java.lang.annotation.*;

//Annotating the method declaration in order to retain order.

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface InputOrder {
  int value();
}
