package com.w2a.pojo;

public class Breakdown {
    private Money item_total;
    private Money shipping;

    public Money getItem_total() {
        return item_total;
    }

    public void setItem_total(Money item_total) {
        this.item_total = item_total;
    }

    public Money getShipping() {
        return shipping;
    }

    public void setShipping(Money shipping) {
        this.shipping = shipping;
    }
}
