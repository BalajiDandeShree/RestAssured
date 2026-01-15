package com.w2a.pojo;
import java.util.List;
public class CreateOrderRequest {
    private String intent;
    private PaymentSource payment_source;
    private List<PurchaseUnit> purchase_units;

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public PaymentSource getPayment_source() {
        return payment_source;
    }

    public void setPayment_source(PaymentSource payment_source) {
        this.payment_source = payment_source;
    }

    public List<PurchaseUnit> getPurchase_units() {
        return purchase_units;
    }

    public void setPurchase_units(List<PurchaseUnit> purchase_units) {
        this.purchase_units = purchase_units;
    }
}
