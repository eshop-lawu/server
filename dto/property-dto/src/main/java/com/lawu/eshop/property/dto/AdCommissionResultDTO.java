package com.lawu.eshop.property.dto;/**
 * Created by ${Yangqh} on 2017/11/15.
 */

import java.math.BigDecimal;

/**
 * <p> </p>
 *
 * @author yangqh
 * @date 2017/11/15 10:50
 */
public class AdCommissionResultDTO {
    private BigDecimal actureMoneyIn;
    private BigDecimal actureLoveIn;

    public BigDecimal getActureMoneyIn() {
        return actureMoneyIn;
    }

    public void setActureMoneyIn(BigDecimal actureMoneyIn) {
        this.actureMoneyIn = actureMoneyIn;
    }

    public BigDecimal getActureLoveIn() {
        return actureLoveIn;
    }

    public void setActureLoveIn(BigDecimal actureLoveIn) {
        this.actureLoveIn = actureLoveIn;
    }
}
