package cn.lonecloud.features.common.util.http;

import cn.lonecloud.features.common.util.GsonUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @author lonecloud
 * Http请求工具类
 */
@Slf4j
public class HttpClientUtil {

    /**
     * get请求
     * @param url url
     * @param param 参数
     * @return
     */
    public static String doGet(String url, Map<String, String> param) {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (log.isDebugEnabled()){
            log.debug(resultString);
        }
        return resultString;
    }

    /**
     * get请求返回json->Object
     * @param url
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T doGetJson(String url, Class<T> clazz){
        String s = doGet(url);
        return GsonUtils.parseObject(s, clazz);
    }

    /**
     * 不带参数的get请求
     * @param url
     * @return
     */
    public static String doGet(String url) {
        return doGet(url, null);
    }

    /**
     * 基础创建参数对象进行POST请求
     * @param url
     * @param paramList 如果需要创建<pre>BasicNameValuePair</pre>
     * @return
     */
    public static String doBasePost(String url,List<NameValuePair> paramList){
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (paramList != null) {
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, StandardCharsets.UTF_8);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (log.isDebugEnabled()){
            log.debug(resultString);
        }
        return resultString;
    }

    /**
     * 带参数的post请求
     * @param url url
     * @param param 参数
     * @return
     */
    public static String doPost(String url, Map<String, String> param) {
        List<NameValuePair> paramList= Lists.newArrayList();
        if (param!=null){
            for (String key:param.keySet()) {
                paramList.add(new BasicNameValuePair(key,param.get(key)));
            }
        }
        return doBasePost(url,paramList);
    }
    /**
     * 重新构造post请求
     * @param url url
     * @param object 请求参数对象
     * @param excludeStr 排除某个参数
     * @return
     */
    public static String doPost(String url,Object object,String... excludeStr){
        return doPost(url,buildParamMap(object));
    }
    /**
     * 获取数据集
     * @param object
     * @return
     */
    private static Map<String, String> buildParamMap(Object object) {
        Map<String,String > params= Maps.newHashMap();
        Class clazz=object.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for(Field field:declaredFields){
            field.setAccessible(true);
            Object fieldObj=null;
            try {
                 fieldObj = field.get(object);
            } catch (IllegalAccessException e) {
                log.debug("not found field {} by error",field.getName());
            }
            params.put(field.getName(),fieldObj!=null?fieldObj.toString():"");
        }
        return params;
    }

    /**
     * 不带参数的post请求
     * @param url
     * @return
     */
    public static String doPost(String url) {
        return doPost(url, null);
    }

    /**
     * 使用json数据的post请求
     * @param url
     * @param json
     * @return
     */
    public static String doPostJson(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (log.isDebugEnabled()){
            log.debug(resultString);
        }
        return resultString;
    }
}
