package com.lawu.eshop.ad.constants;

/**
 * 上传文件类型
 * @author zhangrc
 * @date 2017年9月11日
 */
public enum FileTypeEnum {
	
	/**
	 * 视频
	 */
	VIDEO((byte) 0x01),
	
	/**
	 * 图片
	 */
	IMG((byte) 0x02);
	
    private Byte val;
    
    FileTypeEnum(Byte val) {
        this.val = val;
    }
    
    public Byte getVal() {
		return val;
	}

	public static FileTypeEnum getEnum(Byte val) {
    	FileTypeEnum[] values = FileTypeEnum.values();
        for (FileTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
