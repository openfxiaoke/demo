package com.facishare.open.third.utils;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.facishare.open.third.beans.HttpResponseMessageVO;
import com.facishare.open.third.exception.RequestParamaterException;
import com.facishare.open.third.exception.SYSException;
import com.google.gson.Gson;

/**
 * https  请求工具类
 * @author huanghp
 * @date 2015年8月28日
 */

public class HttpTookit {
	
	private static Logger logger = LoggerFactory.getLogger(HttpTookit.class);

	private static final CloseableHttpClient httpClient;
	
    public static final String CHARSET = "UTF-8";
 
    static {
    	//饱含模式实现 httpClient 单例
    	httpClient = createSSLClientDefault();
    }
    
    public static CloseableHttpClient createSSLClientDefault(){
        RequestConfig config = RequestConfig.custom().setConnectTimeout(600000).setSocketTimeout(150000).build();
        
    	try {
             SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                 //信任所有
                 public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                     return true;
                 }
             }).build();
             
             SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
             
             return HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultRequestConfig(config).build();
         } catch (Exception e) {
        	 logger.error("初始化 httpClient 对象异常", e);
         }
    	
         return HttpClients.createDefault();
    }
    
    /**
     * post + json 发送请求
     * @param url 
     * @param parameters
     * @return @see HttpResponseMessageVO
     * @throws SYSException 
     * @throws RequestParamaterException 
     */
	public static HttpResponseMessageVO sendPostByJson(String url, String parameters) throws SYSException, RequestParamaterException {
		if (StringUtils.isEmpty(url)) {
			logger.error("url参数为空！");
            throw new RequestParamaterException("", "url参数不能够为空！");
        }
		
		HttpResponseMessageVO httpResponseMessageVO = new HttpResponseMessageVO();
		
    	String result = "";
        StringEntity params;
        
		try {
			params = new StringEntity(parameters,"UTF-8");
		    HttpPost request = new HttpPost(url);
		    request.addHeader("Content-type", "application/json");  
            request.setEntity(params);  
            CloseableHttpResponse response = httpClient.execute(request);
            
            int statusCode= response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            
			if (entity != null) {
			    result = EntityUtils.toString(entity, "utf-8");
			    httpResponseMessageVO.setHttpCode("" + statusCode);
			    httpResponseMessageVO.setContent(result);
			}
		} catch (Exception e) {
			logger.error("发送请求异常", e);
			throw new SYSException(""+500,url+"  发送请求异常,请检查url、参数的合法性！");
		}
		
        return  httpResponseMessageVO;
    }
    
    public static void main(String[] args) throws Exception{
    	Gson gson = new Gson();
    	
    	String token ="testToken";
    	String nonce="9890d0eb-5aa9-4f45-9a28-0b05fdfb2588";
    	long timestamp = System.currentTimeMillis();
    	String content = "4FE34659FB17F8AEB8971177F9FF04B581B83A0A6E5F5271C1BA41BD112C9F3C21C8E3AF0978FEB57323B5A903997327D65F7C1D1FF6F4308B720F8E52248223CC6E693CC3029B4A9A4784C21D4A064956C883CE64DE09486F053957827D4B8F2D10AAABB9083B806B5F6507887BF8F9";
    	
    	String deStr = SigUtils.decodeAES("4FE34659FB17F8AEB8971177F9FF04B581B83A0A6E5F5271C1BA41BD112C9F3C21C8E3AF0978FEB57323B5A903997327D65F7C1D1FF6F4308B720F8E52248223CC6E693CC3029B4A9A4784C21D4A064956C883CE64DE09486F053957827D4B8F2D10AAABB9083B806B5F6507887BF8F9","x45sdf3sd1231231232xs");
    	
    	System.out.println(deStr);
    	
    	String sig = SigUtils.getSHA1(token, "" + timestamp, nonce, content);
    	
    	Map<String,String> paramMap = new HashMap<String,String>();
    	paramMap.put("nonce", nonce);
    	paramMap.put("timeStamp", "" + timestamp);
    	paramMap.put("content", content);
    	paramMap.put("sig", sig);
    	
    	String jsonStr = gson.toJson(paramMap);
    	
    	System.out.println(jsonStr);
    	HttpResponseMessageVO httpResponseMessage = sendPostByJson("http://localhost:8080/third-sys/parse/authorize", jsonStr);
    	System.out.println(httpResponseMessage.getContent());
    }
    
}
