package com.emp.sanitycheck.tests;

import com.emp.sanitycheck.helpers.TransactionServices;
import org.testng.annotations.BeforeClass;

public class TestBase {
    String referenceID;
    TransactionServices transactionServices;
    @BeforeClass
    public void init(){
        transactionServices = new TransactionServices();
    }
}
