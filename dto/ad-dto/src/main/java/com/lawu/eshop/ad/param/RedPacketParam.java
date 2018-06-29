package com.lawu.eshop.ad.param;

import java.math.BigDecimal;

import com.lawu.eshop.ad.constants.RedPacketPutWayEnum;

public class RedPacketParam {

   
   private RedPacketPutWayEnum putWayEnum;

   
   private Integer packageCount;

   
   private BigDecimal totlePoint;


   public RedPacketPutWayEnum getPutWayEnum() {
	  return putWayEnum;
   }


   public void setPutWayEnum(RedPacketPutWayEnum putWayEnum) {
	  this.putWayEnum = putWayEnum;
   }


   public Integer getPackageCount() {
	  return packageCount;
   }


  public void setPackageCount(Integer packageCount) {
	this.packageCount = packageCount;
  }


public BigDecimal getTotlePoint() {
	return totlePoint;
}


public void setTotlePoint(BigDecimal totlePoint) {
	this.totlePoint = totlePoint;
}
   
   

   

   
   
   

}
