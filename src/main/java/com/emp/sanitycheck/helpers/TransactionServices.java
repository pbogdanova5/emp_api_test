package com.emp.sanitycheck.helpers;

import com.emp.sanitycheck.models.SaleTransaction;
import com.emp.sanitycheck.utils.ConfigManager;
import io.restassured.response.Response;

public class TransactionServices {
    //params, asserts

    private static String USER = ConfigManager.getInstance().getPropertyString("user");
    private static String PASSWORD = ConfigManager.getInstance().getPropertyString("password");
    TransactionServiceHelper tsh = new TransactionServiceHelper();

    //Create transaction with valid credentials
    public Response createSaleTransaction(SaleTransaction saleTransactionValues){
        return tsh.createSaleTransaction(saleTransactionValues, USER, PASSWORD);
    }

    //Create transaction with certain invalid or valid credentials
    public Response createSaleTransaction(SaleTransaction saleTransactionValues, String userName, String password){
        return tsh.createSaleTransaction(saleTransactionValues, userName, password);
    }

    public Response createVoidTransaction(String referenceId){
        return tsh.createVoidTransaction(referenceId);
    }

    //Asserts
}
