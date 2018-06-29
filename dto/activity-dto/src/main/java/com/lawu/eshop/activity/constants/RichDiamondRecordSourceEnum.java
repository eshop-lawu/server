package com.lawu.eshop.activity.constants;

/** 
 * 
 * @author lihj
 * @date 2018年5月3日
 */
public enum RichDiamondRecordSourceEnum {
	DAYGET((byte)0x01,"日常领取");
	
	private Byte val;
	private String name;
	
	RichDiamondRecordSourceEnum(Byte val,String name){
		this.val=val;
		this.name=name;
	}
	
    public static RichDiamondRecordSourceEnum getEnum(Byte val) {
    	RichDiamondRecordSourceEnum[] values = RichDiamondRecordSourceEnum.values();
        for (RichDiamondRecordSourceEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

	public Byte getVal() {
		return val;
	}

	public void setVal(Byte val) {
		this.val = val;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
