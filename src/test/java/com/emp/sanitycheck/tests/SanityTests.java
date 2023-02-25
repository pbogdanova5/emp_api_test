package com.emp.sanitycheck.tests;

import com.emp.sanitycheck.helpers.TransactionDataProviders;
import com.emp.sanitycheck.models.SaleTransaction;
import com.emp.sanitycheck.models.TransactionResponseInfo;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SanityTests extends TestBase{

    @Test(description = "Send a valid payment transaction request and expect an approved response",
            dataProvider = "sale-transaction-values",
            dataProviderClass = TransactionDataProviders.class,
            priority=1)
    public void testSaleTransaction(SaleTransaction stValues){
        //1 Send a valid payment transaction request
        Response response = transactionServices.createSaleTransaction(stValues);
        referenceID = transactionServices.getResponseInfo(response).getUniqueId();

        //2 Verify that transaction is successful and its status is "approved"
        transactionServices.verifyTransactionApproved(response);
    }

    @Test(description = "Send a valid void transaction request and expect an approved response",
            dependsOnMethods = "testSaleTransaction", priority = 2)
    public void testVoidTransaction(){
        //1 Send a valid void transaction request
        Response response = transactionServices.createVoidTransaction(referenceID);
        referenceID = transactionServices.getResponseInfo(response).getUniqueId();

        //2 Verify that transaction is successful and its status is "approved"
        transactionServices.verifyTransactionApproved(response);
    }

    @Test(description = "Send a void transaction pointing to an existing void transaction and expect 422",
            dependsOnMethods = "testVoidTransaction", priority = 3)
    public void testVoidTrnToVoidTrn(){
        //1 Send a void transaction pointing to an existing void transaction
        Response response = transactionServices.createVoidTransaction(referenceID);

        //2 Verify that transaction cannot be processed
        transactionServices.verifyTransactionUnProcessable(response);
    }

    @Test(description = "Send a valid payment transaction with an invalid authentication and expect 401",
            dataProvider = "sale-transaction-values",
            dataProviderClass = TransactionDataProviders.class,
            priority=4)
    public void testSaleTransactionWithInvalidAuth(SaleTransaction stValues){
        //1 Send a payment transaction with invalid credentials
        Response response = transactionServices.createSaleTransaction(stValues, "invalidUser", "pass");

        //2 Verify that user is unauthorized
        transactionServices.verifyUserUnAuthorized(response);
    }

    @Test(description="send a void transaction pointing to a non-existent payment transaction and expect 422",
            priority = 5)
    public void testVoidTrnToNonExistingTrn(){
        //1 Send a void transaction pointing to a non-existent payment transaction
        Response response = transactionServices.createVoidTransaction("nonexist123");

        //2 Verify that transaction cannot be processed
        transactionServices.verifyTransactionUnProcessable(response);
    }
}
