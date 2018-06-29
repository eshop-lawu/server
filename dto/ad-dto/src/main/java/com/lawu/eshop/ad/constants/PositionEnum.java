package com.lawu.eshop.ad.constants;

public enum PositionEnum {
	
	    POSITON_RECOMMEND((byte) 0x01, "人气推荐"),      //人气推荐
	    POSITON_SHOP_TOP((byte) 0x02, "趣乐购顶部广告"),     //要购物顶部广告
	    POSITON_SHOP_CHOOSE((byte) 0x03, "趣乐购今日推荐"),    //要购物今日推荐
		POSITON_SHOP_GOODS((byte) 0x04, "趣乐购精品"),    //要购物精品
		POSITON_AD_TOP((byte) 0x05, "看广告顶部广告"),   //看广告顶部广告
	    SHOPPING_BUY((byte) 0x06, "E店必购"), // E店必够
	    SHOPPING_GOODS((byte) 0x07, "特色好货"), // 特色好货
	    SHOPPING_BENEFIT((byte) 0x08, "实惠单品"), // 实惠单品
	    SHOPPING_HOT((byte) 0x09, "热门商品"),  //热门商品
	    AD_POSITION_TWO((byte) 0x0A, "三个视频广告位") ,
	    AD_POSITION_THREE((byte) 0x0B, "一个平面广告位") ,
	    AD_POSITION_FOUR((byte) 0x0C, "五个平面广告位") ,
	    SHOPPING_INTEGRAL_INDIANA((byte) 0x0D,"积分抽奖"),
	    GAME_TOP((byte) 0x0E,"游戏顶部广告位"),
	    ACTIVITY_PRODUCT((byte) 0x0F,"抢购活动"),
	    TYPE_ALL((byte) 0x10,"所有");
	
	    public Byte val;
	    private String name;

		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
	
		PositionEnum(Byte val, String name) {
	        this.val = val;
	        this.name = name;
	    }
	
	    public static PositionEnum getEnum(Byte val) {
	    	PositionEnum[] values = PositionEnum.values();
	        for (PositionEnum object : values) {
	            if (object.val.equals(val)) {
	                return object;
	            }
	        }
	        return null;
	    }

}
