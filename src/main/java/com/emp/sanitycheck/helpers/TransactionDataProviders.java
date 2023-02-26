package com.emp.sanitycheck.helpers;

import com.emp.sanitycheck.models.SaleTransaction;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;


public class TransactionDataProviders {

    @DataProvider(name="sale-transaction-values")
    public Iterator<Object[]> dpSaleTransaction(){

        ArrayList<Object[]> listSaleTransaction = new ArrayList<Object[]>();

        SaleTransaction[] arraySaleTransaction = new SaleTransaction[1];
        arraySaleTransaction[0] = new SaleTransaction();
        arraySaleTransaction[0].setTransactionType("sale");
        arraySaleTransaction[0].setCardNumber("4200000000000000");
        arraySaleTransaction[0].setCvv("123");
        arraySaleTransaction[0].setExpirationDate("06/2023");
        arraySaleTransaction[0].setAmount("500");
        arraySaleTransaction[0].setUsage("Coffeemaker");
        arraySaleTransaction[0].setCardHolder("Panda Panda");
        arraySaleTransaction[0].setEmail("panda@example.com");
        arraySaleTransaction[0].setAddress("Panda Street, China");

        listSaleTransaction.add(arraySaleTransaction);
        return listSaleTransaction.iterator();
    }

 }
