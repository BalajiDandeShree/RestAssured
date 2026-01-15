package com.w2a.pojo;

public class ExperienceContext {
    private String payment_method_preference;
    private String landing_page;
    private String shipping_preference;
    private String user_action;
    private String return_url;
    private String cancel_url;

    public String getPayment_method_preference() {
        return payment_method_preference;
    }

    public void setPayment_method_preference(String payment_method_preference) {
        this.payment_method_preference = payment_method_preference;
    }

    public String getLanding_page() {
        return landing_page;
    }

    public void setLanding_page(String landing_page) {
        this.landing_page = landing_page;
    }

    public String getShipping_preference() {
        return shipping_preference;
    }

    public void setShipping_preference(String shipping_preference) {
        this.shipping_preference = shipping_preference;
    }

    public String getUser_action() {
        return user_action;
    }

    public void setUser_action(String user_action) {
        this.user_action = user_action;
    }

    public String getReturn_url() {
        return return_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

    public String getCancel_url() {
        return cancel_url;
    }

    public void setCancel_url(String cancel_url) {
        this.cancel_url = cancel_url;
    }
}
