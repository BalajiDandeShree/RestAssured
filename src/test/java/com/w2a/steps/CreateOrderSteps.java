package com.w2a.steps;

import com.w2a.base.BaseTest;
import com.w2a.pojo.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;

public class CreateOrderSteps {
    private CreateOrderRequest request;
    private Response response;
    static String accessToken;
     static int statusCode;
    static String orderID;

    @When("get the access token from paypal API")
    public void getAccessTokenForPayPal() {
        String clientID = System.getProperty("clientID");
        String secreteKey = System.getProperty("secreteKey");
        Response response = given().auth().preemptive()
                .basic(clientID, secreteKey)
                .param("grant_type", "client_credentials")
                .post("/v1/oauth2/token");

        //response.prettyPrint();
        System.out.println("Access Token " + response.jsonPath().get("access_token"));
        accessToken = response.jsonPath().get("access_token");
    }

    @Given("create order payload with")
    public void create_order_payload(DataTable table) {

        Map<String, String> data = table.asMap(String.class, String.class);

        // ---------- Amount ----------
        Money itemTotal = new Money();
        itemTotal.setCurrency_code(data.get("currency"));
        itemTotal.setValue(data.get("itemTotal"));

        Money shipping = new Money();
        shipping.setCurrency_code(data.get("currency"));
        shipping.setValue(data.get("shippingAmount"));

        Breakdown breakdown = new Breakdown();
        breakdown.setItem_total(itemTotal);
        breakdown.setShipping(shipping);

        Amount amount = new Amount();
        amount.setCurrency_code(data.get("currency"));
        amount.setValue(data.get("totalAmount"));
        amount.setBreakdown(breakdown);

        // ---------- Items (static example, can also be driven from Examples) ----------
        Item tshirt = new Item();
        tshirt.setName("T-Shirt");
        tshirt.setDescription("Super Fresh Shirt");
        tshirt.setSku("sku01");
        tshirt.setQuantity("1");
        tshirt.setCategory("PHYSICAL_GOODS");

        Money tshirtPrice = new Money();
        tshirtPrice.setCurrency_code(data.get("currency"));
        tshirtPrice.setValue("20.00");
        tshirt.setUnit_amount(tshirtPrice);

        Item shoes = new Item();
        shoes.setName("Shoes");
        shoes.setDescription("Running, Size 10.5");
        shoes.setSku("sku02");
        shoes.setQuantity("2");
        shoes.setCategory("PHYSICAL_GOODS");

        Money shoesPrice = new Money();
        shoesPrice.setCurrency_code(data.get("currency"));
        shoesPrice.setValue("100.00");
        shoes.setUnit_amount(shoesPrice);

        // ---------- Purchase Unit ----------
        PurchaseUnit purchaseUnit = new PurchaseUnit();
        purchaseUnit.setInvoice_id(data.get("invoiceId"));
        purchaseUnit.setAmount(amount);
        purchaseUnit.setItems(Arrays.asList(tshirt, shoes));

        // ---------- Experience Context ----------
        ExperienceContext context = new ExperienceContext();
        context.setPayment_method_preference(data.get("paymentMethodPreference"));
        context.setLanding_page(data.get("landingPage"));
        context.setShipping_preference(data.get("shippingPreference"));
        context.setUser_action(data.get("userAction"));
        context.setReturn_url(data.get("returnUrl"));
        context.setCancel_url(data.get("cancelUrl"));

        Paypal paypal = new Paypal();
        paypal.setExperience_context(context);

        PaymentSource paymentSource = new PaymentSource();
        paymentSource.setPaypal(paypal);

        // ---------- Final Request ----------
        request = new CreateOrderRequest();
        request.setIntent(data.get("intent"));
        request.setPayment_source(paymentSource);
        request.setPurchase_units(Collections.singletonList(purchaseUnit));
    }

    @When("user calls create order API")
    public void user_calls_create_order_api() {
        RestAssured.baseURI = "https://api-m.sandbox.paypal.com";
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(accessToken)
                .header("PayPal-Request-Id", "7b92603e-77ed-4896-8e78-5dea2050476a")
                .body(request)
                .when()
                .post("/v2/checkout/orders");
        statusCode = response.getStatusCode();
        System.out.println("created order ID is  : "+response.jsonPath().get("id").toString());
    }

    @Then("order should be created with status code {int}")
    public void verify_status_code(int statusCode1) {
        Assert.assertEquals(statusCode1, statusCode);
    }


    @And("user calls get order API using below Order ID")
    public void userCallGetOrderAPI(DataTable table) {
        Map<String, String> data = table.asMap(String.class, String.class);
        orderID = data.get("OrderID");
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(accessToken)
                .header("PayPal-Request-Id", "7b92603e-77ed-4896-8e78-5dea2050476a")
                .when()
                .get("https://api.sandbox.paypal.com/v2/checkout/orders/"+orderID);
        statusCode = response.getStatusCode();
    }

    @Then("user verify status code as {string}")
    public void userVerifyStatusCode(String statusCode1){
        int status = Integer.parseInt(statusCode1);
        Assert.assertEquals(status,statusCode);
    }
}
