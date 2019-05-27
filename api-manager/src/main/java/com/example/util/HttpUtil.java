package com.example.util;

import com.example.entity.Api;
import com.example.entity.ApiBody;
import com.example.entity.ApiHeaders;
import com.example.entity.ApiParams;
import lombok.extern.java.Log;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2019/5/27
 */
@Log
public class HttpUtil {

    public static Object call(Api api, List<ApiParams> params, List<ApiHeaders> headers, ApiBody apiBody){
        String url = api.getUrl();
        if(api.getMethod().equals(HttpMethod.GET.name())){
            Map<String, String> queryParams = null;
            if(!CollectionUtils.isEmpty(params)){
                queryParams = new HashMap<>(params.size());
                final Map<String, String> tempParams = queryParams;
                params.forEach(ele -> tempParams.put(ele.getKey(), ele.getValue()));
            }
            return get(url, queryParams);
        }
        else if(api.getMethod().equals(HttpMethod.POST.name())){
            Map<String, String> formParams = null;
            if(apiBody != null){
                if(apiBody.getType().equals(MediaType.APPLICATION_FORM_URLENCODED_VALUE)){
                    String body = apiBody.getContent();
                    String[] kvs = body.split("&");
                    for (String kv : kvs) {
                        String[] kvArr = kv.split("=");
                        formParams.put(kvArr[0], kvArr[1]);
                    }
                }
                return postFormData(url, formParams);
            }
        }
        return null;
    }

    public static Object get(String url, Map<String, String> queryParams) {
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();

        CloseableHttpResponse response = null;

        try {
            URIBuilder builder = new URIBuilder(url);
            if(!CollectionUtils.isEmpty(queryParams)){
                queryParams.forEach((key, value) -> builder.setParameter(key, value));
            }

            HttpGet httpGet = new HttpGet(builder.build());
            response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            Header header = entity.getContentType();
            if(header.getValue().startsWith(MediaType.TEXT_HTML_VALUE) || header.getValue().startsWith(MediaType.APPLICATION_JSON_VALUE)){
                StringWriter writer = new StringWriter();
                IOUtils.copy(entity.getContent(), writer, "utf-8");
                result = writer.toString();
            }
            EntityUtils.consume(entity);
        } catch (IOException | URISyntaxException e) {
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
