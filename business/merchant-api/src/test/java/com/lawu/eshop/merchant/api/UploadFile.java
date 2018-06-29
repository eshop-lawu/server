package com.lawu.eshop.merchant.api;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Categories.IncludeCategory;

public class UploadFile {
	
	String URL = "http://127.0.0.1:2222/discountPackage/test";
	String FILE_PATH = "C://Users//Administrator//Desktop//陈记烧鹅.jpg";
	String AUTHORIZATION = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJNRVJDSEFOVCIsImp0aSI6IkIwMDAxIiwic3ViIjoiMTg2OTY1MjM2NTgiLCJhdWQiOiIxIiwiaWF0IjoxNDk4NTQ2MzQ4fQ.ZFXd7Lo15V0KFLaZa_xfY92DFupNdLHLU6LkvzrftctU6DBavfcJI29CMKICFLtUACV5bh4VG-hO5PV4Uk-DsQ";
	
	@Ignore
	@Test
	public void uploadForHttpClient4(){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = null;
        File file = new File(FILE_PATH);
        try {
            HttpPost httpPost = new HttpPost(URL);
            httpPost.setHeader("authorization", AUTHORIZATION);
            //httpPost.setHeader("Content-Type", "multipart/form-data");
            MultipartEntityBuilder mEntityBuilder = MultipartEntityBuilder.create();
            mEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            mEntityBuilder.setContentType(ContentType.MULTIPART_FORM_DATA.withCharset(Charset.forName("UTF-8")));
            mEntityBuilder.setCharset(Charset.forName("UTF-8"));
            //mEntityBuilder.setContentType(ContentType.create("image/jpeg"));
            mEntityBuilder.addBinaryBody("file", file);
            mEntityBuilder.addTextBody("name", "蒋鑫俊");
            
            httpPost.setEntity(mEntityBuilder.build());
            response = httpclient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity resEntity = response.getEntity();
                result = EntityUtils.toString(resEntity);
                // 消耗掉response
                EntityUtils.consume(resEntity);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            HttpClientUtils.closeQuietly(httpclient);
            HttpClientUtils.closeQuietly(response);
        }
        System.out.println(result);
	}
}
