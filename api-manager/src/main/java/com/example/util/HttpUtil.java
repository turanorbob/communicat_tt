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
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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

        Map<String, String> headerParams = null;
        if (!CollectionUtils.isEmpty(headers)) {
            headers.forEach(header -> {
                headerParams.put(header.getKey(), header.getValue());
            });
        }

        Map<String, String> queryParams = null;
        if (!CollectionUtils.isEmpty(params)) {
            queryParams = new HashMap<>(params.size());
            final Map<String, String> tempParams = queryParams;
            params.forEach(ele -> tempParams.put(ele.getKey(), ele.getValue()));
        }

        if (api.getMethod().equals(HttpMethod.GET.name())) {
            // about get form params, that is ?xx=xxx
            return get(url, headerParams, queryParams);
        } else if (api.getMethod().equals(HttpMethod.POST.name()) || api.getMethod().equals(HttpMethod.PUT.name())) {
            Map<String, String> formParams = null;
            String json = null;
            if(apiBody != null){
                if(apiBody.getType().equals(MediaType.APPLICATION_FORM_URLENCODED_VALUE)){
                    String body = apiBody.getContent();
                    String[] kvs = body.split("&");
                    for (String kv : kvs) {
                        String[] kvArr = kv.split("=");
                        formParams.put(kvArr[0], kvArr[1]);
                    }
                } else if (apiBody.getType().equals(MediaType.APPLICATION_JSON_VALUE)) {
                    json = apiBody.getContent();
                }
            }
            return postFormData(url, headerParams, queryParams, formParams, json);
        }
        return null;
    }

    public static Object get(String url, Map<String, String> headerParams, Map<String, String> queryParams) {
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();

        CloseableHttpResponse response = null;

        try {
            URIBuilder builder = new URIBuilder(url);
            if(!CollectionUtils.isEmpty(queryParams)){
                queryParams.forEach((key, value) -> builder.setParameter(key, value));
            }

            HttpGet httpGet = new HttpGet(builder.build());
            if (!CollectionUtils.isEmpty(headerParams)) {
                headerParams.forEach((key, value) -> httpGet.setHeader(key, value));
            }
            response = httpclient.execute(httpGet);
            result = processResponse(response);
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

    public static Object postFormData(String url, Map<String, String> headerParams, Map<String, String> queryParams, Map<String, String> formParams, String json) {
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();

        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            if (!CollectionUtils.isEmpty(queryParams)) {
                queryParams.forEach((key, value) -> builder.setParameter(key, value));
            }

            HttpPost httpPost = new HttpPost(builder.build());

            if (!CollectionUtils.isEmpty(formParams)) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                formParams.forEach((key, value) -> nvps.add(new BasicNameValuePair(key, value)));

                httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            }
            if (!CollectionUtils.isEmpty(headerParams)) {
                headerParams.forEach((key, value) -> {
                    httpPost.setHeader(key, value);
                });
            }
            if (!StringUtils.isEmpty(json)) {
                StringEntity stringEntity = new StringEntity(json, "utf-8");
                stringEntity.setContentEncoding("UTF-8");
                stringEntity.setContentType("application/json");
                httpPost.setEntity(stringEntity);
            }

            response = httpclient.execute(httpPost);

            result = processResponse(response);
        } catch (IOException | URISyntaxException e) {
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

    public static Object delete(String url) {
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();

        CloseableHttpResponse response = null;
        HttpDelete httpDelete = new HttpDelete(url);
        try {
            response = httpclient.execute(httpDelete);
            result = processResponse(response);
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

    private static String processResponse(CloseableHttpResponse response) throws IOException {
        String result = "";
        HttpEntity entity = response.getEntity();
        Header header = entity.getContentType();
        if (header.getValue().startsWith(MediaType.APPLICATION_JSON_VALUE)) {
            StringWriter writer = new StringWriter();
            IOUtils.copy(entity.getContent(), writer, "utf-8");
            result = writer.toString();
        }

        EntityUtils.consume(entity);
        return result;
    }
}
