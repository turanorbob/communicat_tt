package com.example.util;

import lombok.extern.java.Log;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author legend <legendl@synnex.com>
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2019/5/27
 */
@Log
public class HttpUtil {

    public static Object get(String url) {
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;

        try {
            response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            Header header = entity.getContentType();
            if(header.getValue().startsWith(MediaType.TEXT_HTML_VALUE) || header.getValue().startsWith(MediaType.APPLICATION_JSON_VALUE)){
                StringWriter writer = new StringWriter();
                IOUtils.copy(entity.getContent(), writer, "utf-8");
                result = writer.toString();
            }
            EntityUtils.consume(entity);
        } catch (IOException e) {
            log.info(e.getMessage());
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                log.warning(e.getMessage());
            }
        }

        return result;
    }

    public static Object postFormData(String url, Map<String, String> params) {
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            if(!CollectionUtils.isEmpty(params)){
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                params.forEach((key,value) -> nvps.add(new BasicNameValuePair(key, value)));
                httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            }

            response = httpclient.execute(httpPost);

            HttpEntity entity = response.getEntity();
            Header header = entity.getContentType();
            if(header.getValue().startsWith(MediaType.APPLICATION_JSON_VALUE)){
                StringWriter writer = new StringWriter();
                IOUtils.copy(entity.getContent(), writer, "utf-8");
                result = writer.toString();
            }

            EntityUtils.consume(entity);
        } catch (IOException e) {
            log.warning(e.getMessage());
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                log.warning(e.getMessage());
            }
        }
        return result;
    }
}
