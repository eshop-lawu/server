package com.lawu.eshop.common.dto;

import java.math.BigDecimal;

/**
 * 商品运费
 */
public class FreightDTO {

    /**
     * 默认运费，多少件
     */
    private Integer defaultPieceNumber;
    /**
     * 默认运费，多少件内多少钱
     */
    private BigDecimal defaultPieceMoney;
    /**
     * 每加多少件
     */
    private Integer addPieceNumber;
    /**
     * 每加多少件，加多少钱
     */
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((addPieceMoney == null) ? 0 : addPieceMoney.hashCode());
        result = prime * result + ((addPieceNumber == null) ? 0 : addPieceNumber.hashCode());
        result = prime * result + ((defaultPieceMoney == null) ? 0 : defaultPieceMoney.hashCode());
        result = prime * result + ((defaultPieceNumber == null) ? 0 : defaultPieceNumber.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FreightDTO other = (FreightDTO) obj;
        if (addPieceMoney == null) {
            if (other.addPieceMoney != null)
                return false;
        } else if (!addPieceMoney.equals(other.addPieceMoney))
            return false;
        if (addPieceNumber == null) {
            if (other.addPieceNumber != null)
                return false;
        } else if (!addPieceNumber.equals(other.addPieceNumber))
            return false;
        if (defaultPieceMoney == null) {
            if (other.defaultPieceMoney != null)
                return false;
        } else if (!defaultPieceMoney.equals(other.defaultPieceMoney))
            return false;
        if (defaultPieceNumber == null) {
            if (other.defaultPieceNumber != null)
                return false;
        } else if (!defaultPieceNumber.equals(other.defaultPieceNumber))
            return false;
        return true;
    }
    
}
