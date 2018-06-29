package com.lawu.eshop.order.srv;

import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.junit.Ignore;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lawu.eshop.mq.dto.ad.AdPointNotification;
import com.lawu.eshop.order.dto.foreign.ExpressInquiriesDetailDTO;

public class SerializableTest {

    @Ignore
    @Test
    public void serTest() {
        ExpressInquiriesDetailDTO dto = new ExpressInquiriesDetailDTO();
        dto.setReason("hahha");
        String json = JSONObject.toJSON(dto).toString();
        json = JSONObject.toJSONString(dto, SerializerFeature.WriteMapNullValue);
        System.out.println(json);
        FileOutputStream fs = null;
        ObjectOutputStream os = null;
        try {
            fs = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\dto.ser");
            os = new ObjectOutputStream(fs);
            os.writeObject(dto);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fs.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ObjectInputStream ois = null;
        ExpressInquiriesDetailDTO dto1 = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("C:\\Users\\Administrator\\Desktop\\dto.ser"));
            dto1 = (ExpressInquiriesDetailDTO) ois.readObject();
            json = JSONObject.toJSONString(dto1, SerializerFeature.WriteMapNullValue);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(json);

    }

    @Ignore
    @Test
    public void writeObject() {
        AdPointNotification bean = new AdPointNotification();
        
        // 输出文件
        try (FileOutputStream fs = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\bean.ser");
                ObjectOutputStream os = new ObjectOutputStream(fs)) {
            os.writeObject(bean);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // 输出字符串
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                FileOutputStream fs = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\bean1.ser")) {
            oos.writeObject(bean);
            String str = new String(Base64.encodeBase64(baos.toByteArray()), "UTF-8");
            fs.write(str.getBytes());
            fs.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void readObject() {
        // 从序列化文件中读取
        try (FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\Desktop\\bean.ser")) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            AdPointNotification bean = (AdPointNotification) ois.readObject();
            assertNotNull(bean);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // 从自定义文件中读取
        try (FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\Desktop\\bean1.ser")) {
            System.out.println("以字节为单位读取文件内容，一次读多个字节：");
            StringBuilder sb = new StringBuilder();
            // 一次读多个字节
            byte[] tempbytes = new byte[1024];
            int byteread = 0;
            // 读入多个字节到字节数组中，byteread为一次读入的字节数
            while ((byteread = fis.read(tempbytes)) != -1) {
                sb.append(new String(tempbytes, 0, byteread, "UTF-8"));
            }
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(Base64.decodeBase64(sb.toString().getBytes("UTF-8"))));
            AdPointNotification bean = (AdPointNotification) ois.readObject();
            assertNotNull(bean);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
