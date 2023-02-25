package com.emp.sanitycheck.helpers;

import com.emp.sanitycheck.constants.EndPoints;
import com.emp.sanitycheck.models.SaleTransaction;
import com.emp.sanitycheck.models.VoidTransaction;
import com.emp.sanitycheck.utils.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class TransactionServiceHelper {

    private static String BASE_URL = ConfigManager.getInstance().getPropertyString("baseUrl");
    private static String PORT = ConfigManager.getInstance().getPropertyString("port");
    //private static String USER = ConfigManager.getInstance().getPropertyString("user");
    //private static String PASSWORD = ConfigManager.getInstance().getPropertyString("password");

    TransactionServiceHelper(){
        RestAssured.baseURI = BASE_URL;
        RestAssured.port = Integer.parseInt(PORT);
        //RestAssured.basic(USER, PASSWORD);
        RestAssured.useRelaxedHTTPSValidation();
    }

     Response createSaleTransaction(SaleTransaction values, String user, String password){
        RestAssured.basic(user, password);
        SaleTransaction saleTransaction = new SaleTransaction();

        saleTransaction.setTransactionType(values.getTransactionType());
        saleTransaction.setCardNumber(values.getCardNumber());
        saleTransaction.setCvv(values.getCvv());
        saleTransaction.setExpirationDate(values.getExpirationDate());
        saleTransaction.setAmount(values.getAmount());
        saleTransaction.setUsage(values.getUsage());
        saleTransaction.setCardHolder(values.getCardHolder());
        saleTransaction.setEmail(values.getEmail());
        saleTransaction.setAddress(values.getAddress());

        Response response = RestAssured.given()
                .contentType("application/json; charset=UTF-8")
                .when()
                .body(saleTransaction)
                .post(EndPoints.SALE_TRANSACTION)
                .andReturn();

        return response;
    }

    Response createVoidTransaction(String referenceId){
        //create post request
        VoidTransaction voidTransaction = new VoidTransaction();

        voidTransaction.setReferenceId(referenceId);
        voidTransaction.setTransactionType("void");


        Response response = RestAssured.given()
                .contentType("application/json; charset=UTF-8")
                .when()
                .body(voidTransaction)
                .post(EndPoints.VOID_TRANSACTION)
                .andReturn();

        return response;
    }

}
