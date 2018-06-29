package com.lawu.eshop.order.srv.json;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.DateDeserializer;

public class JCDateDeserializer extends DateDeserializer {
	
    public static final JCDateDeserializer INSTANCE = new JCDateDeserializer();
    
    /**
     * 时间格式
     */
    private String pattern = "yyyy-MM-dd HH:mm";
    
    public JCDateDeserializer() {
    }
    
    public JCDateDeserializer(String pattern) {
    	this.pattern = pattern;
    }

	@SuppressWarnings("unchecked")
	@Override
	protected <T> T cast(DefaultJSONParser parser, Type clazz, Object fieldName, Object val){
        if (val == null) {
            return null;
        } else if (val instanceof String) {
            String strVal = (String) val;
            if (strVal.length() == 0) {
                return null;
            } else {
            	SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            	dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            	parser.setDateFomrat(dateFormat);
            	try {
					return (T) dateFormat.parse(strVal);
				} catch (ParseException e) {
					e.printStackTrace();
				}
            }
        }
        return super.cast(parser, clazz, fieldName, val);
    }
}