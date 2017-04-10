package com.yc.education.util;



import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;

public class HttpSender{
	//用来产生随机验证码的
	private static Random rand = new Random();
	
	private static String[] str = {"0","1","2","3","4","5","6","7","8","9"};
	
	

	public static void main(String[] args) {

		
    	//存放产生的随机数
    	String sms="";
    	//生成六位数的随机数
    	StringBuffer buf = new StringBuffer();
		for(int i=0;i<6;i++){
			buf.append(str[rand.nextInt(10)]);
		}
		sms= buf.toString();
		System.out.println( buf.toString());
		
		//session.setAttribute("sms", buf.toString());
		
		String url = "http://www.uoleem.com.cn/api/";// 应用地址
		String username = "xxltl";// 账号
		String pwd = "xxltl63811727";// 密码
		String mobile = "13772164740";// 手机号码，多个号码使用","分割
		String content = "【香榭丽涂料】校验码："+sms+",您正在注册香榭丽会员，请尽快操作!";// 短信内容

		try {
			String returnString = HttpSender.batchSend(url, username, pwd, mobile, content);
			System.out.println(returnString);
			// TODO 处理返回值,参见HTTP协议文档
		} catch (Exception e) {
			// TODO 处理异常
			e.printStackTrace();
		}
	}	
	
	public static String batchSend(String url, String username, String pwd, String mobile, String content) throws Exception {
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod();
		try {
			URI base = new URI(url, false);
			method.setURI(new URI(base, "uoleemApi", false));
			method.setQueryString(new NameValuePair[] { 
					new NameValuePair("username", username),
					new NameValuePair("pwd", pwd), 
					new NameValuePair("mobile", mobile),
					new NameValuePair("content", content)
				});
			int result = client.executeMethod(method);
			if (result == HttpStatus.SC_OK) {
				InputStream in = method.getResponseBodyAsStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = in.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
				}
				return URLEncoder.encode(baos.toString(), "UTF-8");
			} else {
				throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
			}
		} finally {
			method.releaseConnection();
		}

	}
}
