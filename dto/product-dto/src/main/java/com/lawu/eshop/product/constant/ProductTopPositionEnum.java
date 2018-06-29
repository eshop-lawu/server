package com.lawu.eshop.product.constant;

public enum ProductTopPositionEnum {
	
	    SHOPPING_INTEGRAL_INDIANA((byte) 0x01,"积分夺宝"),    
	    SHOPPING_PRODUCT((byte) 0x02, "商品"),
	    ACTIVITY_PRODUCT((byte) 0x03, "抢购"),
	    LINK((byte) 0x04, "链接");
	
	    public Byte val;
	    private String name;

		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
	
		ProductTopPositionEnum(Byte val, String name) {
	        this.val = val;
	        this.name = name;
	    }
	
	    public static ProductTopPositionEnum getEnum(Byte val) {
	    	ProductTopPositionEnum[] values = ProductTopPositionEnum.values();
	        for (ProductTopPositionEnum object : values) {
	            if (object.val.equals(val)) {
	                return object;
	            }
	        }
	        return null;
	    }

}
