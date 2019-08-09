package com.synnex.connectwise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import connectwise.client.*;
import connectwise.client.auth.Authentication;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.nio.client.HttpAsyncClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.Future;

public class SynnexApiClient extends ApiClient {
    private Field[] fields = null;
    private Map<String, Field> fieldMap = null;

    @Override
    public <T> Response<T> invokeAPI(String path, String method, List<Pair> queryParams, Object body, byte[] binaryBody, Map<String, String> headerParams, Map<String, Object> formParams, String accept, String contentType, String[] authNames, TypeRef returnType) throws ApiException {
        Future<HttpResponse> response = this.getAPIResponse(path, method, queryParams, body, binaryBody, headerParams, formParams, accept, contentType, authNames);
        Response<T> wrappedResponse = new Response(response, returnType != null ? returnType.getType() : null, this);
        return wrappedResponse;
    }

    @Override
    public ApiClient setBasePath(String site, String companyName) {
        setSite(site);
        setCompanyName(companyName);
        setCodebase();
        String codebase = getProperty("codebase");
        String scheme = getProperty("scheme");
        String basePath = scheme + site + "/" + codebase + "apis/3.0";
        setProperty("basePath", basePath);
        return this;
    }

    private Future<HttpResponse> getAPIResponse(String path, String method, List<Pair> queryParams, Object body, byte[] binaryBody, Map<String, String> headerParams, Map<String, Object> formParams, String accept, String contentType, String[] authNames) throws ApiException {
        if (body != null && binaryBody != null) {
            throw new ApiException(500, "either body or binaryBody must be null");
        } else {
            boolean usingCookies = getBooleanProperty("usingCookies");

            if (!usingCookies) {
                this.updateParamsForAuth(authNames, queryParams, headerParams);
            }

            StringBuilder b = new StringBuilder();
            b.append("?");
            if (queryParams != null) {
                Iterator var12 = queryParams.iterator();

                while(var12.hasNext()) {
                    Pair queryParam = (Pair)var12.next();
                    if (!queryParam.getName().isEmpty()) {
                        b.append(this.escapeString(queryParam.getName()));
                        b.append("=");
                        b.append(this.escapeString(queryParam.getValue()));
                        b.append("&");
                    }
                }
            }

            String querystring = b.substring(0, b.length() - 1);
            HttpAsyncClientBuilder builder = HttpAsyncClientBuilder.create();
            BasicCookieStore cStore = new BasicCookieStore();
            if (this.getBasePath() == null || this.getBasePath().isEmpty()) {
                this.setBasePath();
            }

            String url;
            if (accept == null) {
                url = this.getBasePath() + path + querystring;
            } else {
                url = this.getBasePath() + path + querystring;
            }

            List<Header> headers = new ArrayList();
            headers.add(new BasicHeader("Accept", accept));
            boolean overrideSsl = getBooleanProperty("overrideSsl");
           if (overrideSsl) {
                headers.add(new BasicHeader("x-cw-overridessl", "true"));
            }

            Iterator keyIterrator = headerParams.keySet().iterator();

            String key;
            while(keyIterrator.hasNext()) {
                key = (String)keyIterrator.next();
                headers.add(new BasicHeader(key, headerParams.get(key)));
            }

            Map<String, String> defaultHeaderMap = getMap("defaultHeaderMap");

            keyIterrator = defaultHeaderMap.keySet().iterator();

            while(keyIterrator.hasNext()) {
                key = (String)keyIterrator.next();
                if (!headerParams.containsKey(key)) {
                    headers.add(new BasicHeader(key, defaultHeaderMap.get(key)));
                }
            }

            String encodedFormParams = null;
            MultipartEntityBuilder mp;
            if (contentType.startsWith("multipart/form-data")) {
                mp = MultipartEntityBuilder.create();
                Iterator formKeys = formParams.entrySet().iterator();

                while(formKeys.hasNext()) {
                    Map.Entry<String, Object> param = (Map.Entry)formKeys.next();
                    if (param.getValue() instanceof File) {
                        File file = (File)param.getValue();
                        mp.addTextBody(param.getKey(), file.getName(), ContentType.MULTIPART_FORM_DATA);
                        mp.addBinaryBody(param.getKey(), file);
                    } else {
                        mp.addTextBody(param.getKey(), this.parameterToString(param.getValue()), ContentType.MULTIPART_FORM_DATA);
                    }
                }

                body = mp.build();
            } else if (contentType.startsWith("application/x-www-form-urlencoded")) {
                encodedFormParams = this.getXWWWFormUrlencodedParams(formParams);
            } else if (binaryBody != null) {
                mp = MultipartEntityBuilder.create();
                mp.addBinaryBody("upFile", binaryBody);
                body = mp.build();
            }
            String clientId = getProperty("clientId");

            String site = getProperty("site");

            if (clientId != null && !clientId.isEmpty()) {
                cStore = new BasicCookieStore();
                BasicClientCookie c = new BasicClientCookie("clientId", clientId);
                c.setPath(site);
                cStore.addCookie(c);
            }

            builder = this.setCookieAuthenticationIfNeeded(builder, headers, cStore);
            CloseableHttpAsyncClient client = this.getClient(builder);
            client.start();
            ObjectMapper mapper = new ObjectMapper();

            Future future;

            method = method.toLowerCase();
            switch(method) {
                case "get":
                    HttpGet request = new HttpGet(url);
                    future = client.execute(request, (FutureCallback)null);
                    break;
                case "post":
                    HttpPost postRequest = new HttpPost(url);
                    StringEntity postEntity = null;

                    try {
                        if (encodedFormParams != null) {
                            postEntity = new StringEntity(mapper.writeValueAsString(encodedFormParams));
                        } else {
                            postEntity = new StringEntity(mapper.writeValueAsString(body), ContentType.APPLICATION_JSON);
                        }
                    } catch (UnsupportedEncodingException | JsonProcessingException e) {
                        throw new ApiException(e);
                    }

                    postRequest.setEntity(postEntity);
                    future = client.execute(postRequest, (FutureCallback)null);
                    break;
                case "put":
                    HttpPut putRequest = new HttpPut(url);

                    try {
                        StringEntity putEntity = new StringEntity(mapper.writeValueAsString(body), ContentType.APPLICATION_JSON);
                        putRequest.setEntity(putEntity);
                    } catch ( JsonProcessingException e) {
                        throw new ApiException(e);
                    }

                    future = client.execute(putRequest, (FutureCallback)null);
                    break;
                case "patch":
                    HttpPatch patchRequest = new HttpPatch(url);

                    try {
                        StringEntity patchEntity = new StringEntity(mapper.writeValueAsString(body), ContentType.APPLICATION_JSON);
                        patchRequest.setEntity(patchEntity);
                    } catch (JsonProcessingException e) {
                        throw new ApiException(e);
                    }

                    future = client.execute(patchRequest, (FutureCallback)null);
                    break;
                case "delete":
                    HttpDelete deleteRequest = new HttpDelete(url);
                    future = client.execute(deleteRequest, (FutureCallback)null);
                    break;
                default:
                    throw new ApiException(500, "unknown method type " + method);
            }


            return future;
        }
    }

    private String getXWWWFormUrlencodedParams(Map<String, Object> formParams) {
        StringBuilder formParamBuilder = new StringBuilder();
        Iterator formKeys = formParams.entrySet().iterator();

        while(formKeys.hasNext()) {
            Map.Entry<String, Object> param = (Map.Entry)formKeys.next();
            String valueStr = this.parameterToString(param.getValue());

            try {
                formParamBuilder.append(URLEncoder.encode(param.getKey(), "utf8")).append("=").append(URLEncoder.encode(valueStr, "utf8"));
                formParamBuilder.append("&");
            } catch (UnsupportedEncodingException var8) {
            }
        }

        String encodedFormParams = formParamBuilder.toString();
        if (encodedFormParams.endsWith("&")) {
            encodedFormParams = encodedFormParams.substring(0, encodedFormParams.length() - 1);
        }

        return encodedFormParams;
    }

    private HttpAsyncClientBuilder setCookieAuthenticationIfNeeded(HttpAsyncClientBuilder builder, List<Header> headers, BasicCookieStore cStore) throws ApiException {
        boolean overrideSsl = getBooleanProperty("overrideSsl");
        if (overrideSsl) {
            headers.add(new BasicHeader("x-cw-overridessl", "true"));
        }
        boolean usingCookies = getBooleanProperty("usingCookies");

        if (!usingCookies) {
            builder.setDefaultHeaders((Collection)headers);
            builder.setDefaultCookieStore(cStore);
            return builder;
        } else {
            this.setMemberHashIfNeeded();
            if (headers == null) {
                headers = new ArrayList();
            }

            headers.add(new BasicHeader("Authorization", null));
            headers.add(new BasicHeader("Content-Type", "application/json"));
            if (cStore == null) {
                cStore = new BasicCookieStore();
            }
            String username = getProperty("username");
            String site = getProperty("site");
            String companyName = getProperty("companyName");
            String memberHash = getProperty("memberHash");

            BasicClientCookie user = new BasicClientCookie("MemberID", username);
            user.setDomain(site);
            user.setPath("/");
            BasicClientCookie company = new BasicClientCookie("CompanyName", companyName);
            company.setPath("/");
            company.setDomain(site);
            BasicClientCookie member = new BasicClientCookie("MemberHash", memberHash);
            member.setPath("/");
            member.setDomain(site);
            cStore.addCookie(user);
            cStore.addCookie(company);
            cStore.addCookie(member);
            builder.setDefaultCookieStore(cStore);
            builder.setDefaultHeaders((Collection)headers);
            return builder;
        }
    }

    private CloseableHttpAsyncClient getClient(HttpAsyncClientBuilder builder) {
        if (!getHostMap().containsKey(this.getBasePath())) {
            CloseableHttpAsyncClient client = builder.build();
            client.start();
            if (this.isDebugging()) {
                System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
                System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
                System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire.header", "debug");
                System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "debug");
            }

            getHostMap().put(this.getBasePath(), client);
        }

        return getHostMap().get(this.getBasePath());
    }

    private void updateParamsForAuth(String[] authNames, List<Pair> queryParams, Map<String, String> headerParams) {
        int len = authNames.length;
        Map<String, Authentication> map = getAuthentications();

        for(int i = 0; i < len; ++i) {
            String authName = authNames[i];
            Authentication auth = map.get(authName);
            if (auth == null) {
                throw new RuntimeException("Authentication undefined: " + authName);
            }

            auth.applyToParams(queryParams, headerParams);
        }

    }

    private void setMemberHashIfNeeded() throws ApiException {
        String memberHash = getProperty("memberHash");
        String scheme = getProperty("scheme");
        String site = getProperty("site");
        String codebase = getProperty("codebase");
        String clientId = getProperty("clientId");
        String connectwiseAppId = getProperty("connectwiseAppId");
        String companyName = getProperty("companyName");
        String username = getProperty("username");
        String password = getProperty("password");

        if (memberHash == null || memberHash.isEmpty()) {
            this.setCodebase();
            String loginPath = scheme + site + "/" + codebase + "login/Login.aspx";
            HttpClientBuilder builder = HttpClientBuilder.create();
            if (clientId != null && !clientId.isEmpty()) {
                BasicCookieStore cStore = new BasicCookieStore();
                BasicClientCookie c = new BasicClientCookie("clientId", clientId);
                c.setPath(site);
                cStore.addCookie(c);
                builder.setDefaultCookieStore(cStore);
            }

            List<BasicNameValuePair> params = new ArrayList();
            params.add(new BasicNameValuePair("cw-app_id", connectwiseAppId));
            params.add(new BasicNameValuePair("CompanyName", companyName));
            params.add(new BasicNameValuePair("UserName", username));
            params.add(new BasicNameValuePair("Password", password));
            params.add(new BasicNameValuePair("Response", "json"));
            HttpPost post = new HttpPost(loginPath);

            try {
                post.setEntity(new UrlEncodedFormEntity(params));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            CloseableHttpClient client = builder.build();
            CloseableHttpResponse loginResponse = null;

            try {
                loginResponse = client.execute(post);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = null;
            InputStream body = null;

            try {
                body = loginResponse.getEntity().getContent();
                root = mapper.readTree(body);
                boolean successful = root.path("Success").asBoolean();
                if (!successful) {
                    throw new ApiException(401, "Invalid username and password");
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new ApiException(401, "CompanyName not found: " + companyName);
            }
        }

    }
    private void setCodebase() {
        String codebase = getProperty("codebase");
        String clientId = getProperty("clientId");
        String scheme = getProperty("scheme");
        String site = getProperty("site");
        String companyName = getProperty("companyName");
        if (codebase == null || codebase.isEmpty()) {
            try {
                HttpAsyncClientBuilder builder = HttpAsyncClientBuilder.create();
                List<Header> headers = new ArrayList();
                headers.add(new BasicHeader("Content-Type", "application/json"));
                builder.setDefaultHeaders(headers);
                String loginInfoPath = scheme + site + "/login/companyinfo/" + companyName;
                if (clientId != null && !clientId.isEmpty()) {
                    BasicCookieStore cStore = new BasicCookieStore();
                    BasicClientCookie c = new BasicClientCookie("clientId", clientId);
                    c.setPath(site);
                    cStore.addCookie(c);
                    builder.setDefaultCookieStore(cStore);
                }

                HttpAsyncClient client = this.getClient(builder);
                HttpResponse loginInfoResponse = (HttpResponse)client.execute(new HttpGet(loginInfoPath), (FutureCallback)null).get();
                String body= EntityUtils.toString(loginInfoResponse.getEntity());
                if (body.trim() == "null") {
                    throw new ApiException(404, "Company not found: " + companyName);
                }

                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(body);
                setCodebase(root.path("Codebase").asText("v4_6_release/"));
            } catch (Exception e) {
                e.printStackTrace();
                setCodebase("v4_6_release/");
            }

        }
    }

    private Field getField(String name){
        if(fields == null){
            fields = this.getClass().getSuperclass().getDeclaredFields();
            fieldMap = new HashMap<>(fields.length);
            for (Field field : fields) {
                field.setAccessible(true);
                fieldMap.put(field.getName(), field);
            }
        }

        return fieldMap.get(name);
    }

    private Object getObject(String name) throws IllegalAccessException {
        Field field = getField(name);
        if(field != null){
            return field.get(this);
        }
        return null;
    }

    private void setProperty(String name, String value) {
        Field field = getField(name);
        if(field != null){
            try {
                field.set(this, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private String getProperty(String name){
        String value = null;
        try {
            value = (String)getObject(name);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return value;
    }

    private boolean getBooleanProperty(String name){
        boolean value = false;

        try {
            value = (Boolean)getObject(name);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return value;
    }

    private Map<String, CloseableHttpAsyncClient> getHostMap(){
        Map<String, CloseableHttpAsyncClient> value = null;
        try {
            value = (Map<String, CloseableHttpAsyncClient>)getObject( "hostMap");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return value;
    }

    public Map<String, String> getMap(String name){
        Map<String, String> value = null;
        try {
            value = (Map<String, String>)getObject(name);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return value;
    }

    public void close(){
        Map<String, CloseableHttpAsyncClient> clientMap = getHostMap();
        if(!clientMap.isEmpty()){
            clientMap.forEach((key, client) -> {
                if(client != null){
                    try {
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            clientMap.clear();
        }
    }
}
