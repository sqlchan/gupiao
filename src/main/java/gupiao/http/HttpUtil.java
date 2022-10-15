package gupiao.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

public class HttpUtil {
    public static void main(String[] args) throws IOException {
//        HttpClient httpClient = new HttpClient();
//        GetMethod getMethod = new GetMethod("https://flash-api.xuangubao.cn/api/pool/detail?pool_name=limit_up");
//
//        String result = "";
//        try {
//            int code = httpClient.executeMethod(getMethod);
//            if (code == 200){
//                result = getMethod.getResponseBodyAsString();
//                System.out.println("result:" + result);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.out.println(get("https://flash-api.xuangubao.cn/api/pool/detail?pool_name=super_stock"));
    }

    public static String get(String url) throws IOException {
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        String result = "";
        int code = httpClient.executeMethod(getMethod);
        if (code == 200){
            result = getMethod.getResponseBodyAsString();
            System.out.println("result:" + result);
        }
        return result;
    }

}
