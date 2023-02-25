package com.emp.sanitycheck.helpers;

import com.emp.sanitycheck.models.SaleTransaction;
import com.emp.sanitycheck.models.TransactionResponseInfo;
import com.emp.sanitycheck.utils.ConfigManager;
import com.fasterxml.jackson.core.type.TypeReference;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;

import java.lang.reflect.Type;

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

    public TransactionResponseInfo getResponseInfo(Response response){
        Type type = new TypeReference<TransactionResponseInfo>(){}.getType();
        TransactionResponseInfo tri = response.as(type);
        return tri;
    }

    //Asserts
    public void verifyTransactionApproved(Response response){
        Assert.assertEquals(getResponseInfo(response).getStatus(), "approved");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }

    public void verifyTransactionUnProcessable(Response response){
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_UNPROCESSABLE_ENTITY);
    }

    public void verifyUserUnAuthorized(Response response){
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_UNAUTHORIZED);
    }
}
