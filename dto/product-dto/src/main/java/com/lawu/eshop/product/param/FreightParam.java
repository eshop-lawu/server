package com.lawu.eshop.product.param;

import io.swagger.annotations.ApiParam;

import java.math.BigDecimal;

/**
 * 商品运费
 */
public class FreightParam {

    @ApiParam(value = "默认运费，多少件", required = false)
    private Integer defaultPieceNumber;

    @ApiParam(value = "默认运费，多少件内多少钱", required = false)
    private BigDecimal defaultPieceMoney;

    @ApiParam(value = "每加多少件", required = false)
    private Integer addPieceNumber;

    @ApiParam(value = "每加多少件，加多少钱", required = false)
    private BigDecimal addPieceMoney;

    public Integer getDefaultPieceNumber() {
        return defaultPieceNumber;
    }

    public void setDefaultPieceNumber(Integer defaultPieceNumber) {
        this.defaultPieceNumber = defaultPieceNumber;
    }

    public BigDecimal getDefaultPieceMoney() {
        return defaultPieceMoney;
    }

    public void setDefaultPieceMoney(BigDecimal defaultPieceMoney) {
        this.defaultPieceMoney = defaultPieceMoney;
    }

    public Integer getAddPieceNumber() {
        return addPieceNumber;
    }

    public void setAddPieceNumber(Integer addPieceNumber) {
        this.addPieceNumber = addPieceNumber;
    }

    public BigDecimal getAddPieceMoney() {
        return addPieceMoney;
    }

    public void setAddPieceMoney(BigDecimal addPieceMoney) {
        this.addPieceMoney = addPieceMoney;
    }
}
