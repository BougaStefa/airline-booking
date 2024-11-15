package com.bougastefa.app;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
      Utilities ut = new Utilities();
      Customer customer = new Customer("111","222","333","444","555","666");
      ut.exportCustomerToCsv(customer,"Customer.csv");
    }
}
