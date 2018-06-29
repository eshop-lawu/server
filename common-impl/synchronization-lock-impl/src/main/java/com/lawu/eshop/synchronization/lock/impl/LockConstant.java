package com.lawu.eshop.synchronization.lock.impl;

public class LockConstant {
	
	/**
	 * 隐藏构造函数
	 */
	private LockConstant() {
		throw new IllegalAccessError("Utility class");
	}
	
	/*******************************
	 * Model Name
	 *******************************/
    public enum LockModule {
    	/**
         * ad-srv模块
         */
    	LOCK_AD_SRV("AD_SRV"),
    	
        /**
         * mall-srv模块
         */
    	LOCK_MALL_SRV("MALL_SRV"),
    	
        /**
         * order-srv模块
         */
    	LOCK_ORDER_SRV("ORDER_SRV"),
    	
        /**
         * product-srv模块
         */
    	LOCK_PRODUCT_SRV("PRODUCT_SRV"),
    	
        /**
         * property-srv模块
         */
    	LOCK_PROPERTY_SRV("PROPERTY_SRV"),
    	
        /**
         * user-srv模块
         */
    	LOCK_USER_SRV("USER_SRV"),

		/**
		 * beh_analyze_srv模块
		 */
		LOCK_BEH_ANALYZE_SRV("BEH_ANALYZE_SRV");

    	
    	private String name;
    	
    	LockModule(String name){
    		this.name = name;
    	}

		public String getName() {
			return name;
		}

    }

    /******************************************
     * Lock Name
     ******************************************/
    /*******	ad-srv模块LockName		**********/
    
    /*******	mall-srv模块LockName		**********/
    
    /*******	order-srv模块LockName 	**********/
    /**
     * 创建抢购订单
     */
    public final static String CREATE_ACTIVITY_ORDER = "CREATE_ACTIVITY_ORDER";
    
    /**
     * 订单支付成功回调
     */
    public final static String ORDER_PAYMENT_SUCCESSFUL = "ORDER_PAYMENT_SUCCESSFUL";
    
    /*******	product-srv模块LockName	**********/
    
    /*******	property-srv模块LockName	**********/
    
    /*******	user-srv模块LockName		**********/
    public final static String CREATE_ORDER_BECOME_FANS = "CREATE_ORDER_BECOME_FANS";

	public final static String CREATE_ABNORMAL_RECORD = "CREATE_ABNORMAL_RECORD";

	public final static String QUERY_ABNORMAL_CACHE = "QUERY_ABNORMAL_CACHE";
}
