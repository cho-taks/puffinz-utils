package com.puffinz.utils.util;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.puffinz.utils.model.HttpResponse;
import com.puffinz.utils.param.HttpParam;

import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Slf4j
public class HttpUtil {

	public static final int HTTP_BAD_REQUEST = 400;
	public static final int HTTP_SYS_FAIL = 500;
	public static final String ENCODING_DEFAULT = "UTF-8";
	public final static String CONTENTS_TYPE_XML = "application/xml; charset=UTF-8";
	public final static String CONTENTS_TYPE_JSON = "application/json; charset=UTF-8";
	public final static String CONTENTS_TYPE_FORM = "application/x-www-form-urlencoded; charset=UTF-8";
	public final static String CONTENTS_TYPE_TEXT = "text/html; charset=utf-8";
	public final static String CONTENTS_TYPE_MULTIPART = "multipart/form-data";
		
		public final static int HTTP_RETRY_COUNT = 3; 
	
	public HttpResponse get(HttpParam param) {
		
		HttpResponse res = new HttpResponse();
		
		try {

			OkHttpClient client = new OkHttpClient().newBuilder()
					.connectTimeout(param.getConnect_timeout(), TimeUnit.MILLISECONDS)
					.readTimeout(param.getRead_timeout(), TimeUnit.MILLISECONDS)
					.build();
			
			if(ObjectUtil.isEmpty(param.getUrl())) {
				log.warn("URL is empty : {}", param.toString());
				res.setStatus(HTTP_BAD_REQUEST);
				return res;
			}
			
			Request.Builder rb = new Request.Builder();
			rb.addHeader("Content-Type", param.getContent_type());
			
			if(param.getHeaders().size() > 0) {
				Set<String> keys = param.getHeaders().keySet();
				Iterator<String> itr = keys.iterator();
				
				while(itr.hasNext()) {
					String key = itr.next();
					rb.addHeader(key, param.getHeaders().get(key));
				}
			}
			
			HttpUrl.Builder hb = HttpUrl.parse(param.getUrl()).newBuilder();
			if(param.getParams().size() > 0) {
				Set<String> keys = param.getParams().keySet();
				Iterator<String> itr = keys.iterator();
				
				while(itr.hasNext()) {
					String key = itr.next();
					hb.addQueryParameter(key, URLEncoder.encode((String) param.getParams().get(key), ENCODING_DEFAULT));
				}
			}
			rb.url(hb.build());
			
			Request request = rb.build();
			Response response = client.newCall(request).execute();
			res.setStatus(response.code());
			res.setRes(response.body().string());

		} catch(Exception e) {
			log.error("Exception : {}", e.getMessage(), e);
			res.setStatus(HTTP_SYS_FAIL);
		}
		
		return res;
	}
	
	public HttpResponse post(HttpParam param) {
		
		HttpResponse res = new HttpResponse();
		
		try {

			OkHttpClient client = new OkHttpClient().newBuilder()
					.connectTimeout(param.getConnect_timeout(), TimeUnit.MILLISECONDS)
					.readTimeout(param.getRead_timeout(), TimeUnit.MILLISECONDS)
					.build();
			
			if(ObjectUtil.isEmpty(param.getUrl())) {
				log.warn("URL is empty : {}", param.toString());
				res.setStatus(HTTP_BAD_REQUEST);
				return res;
			}
			
			Request.Builder rb = new Request.Builder();
			rb.url(param.getUrl());
			rb.addHeader("Content-Type", param.getContent_type());
			
			if(param.getHeaders().size() > 0) {
				Set<String> keys = param.getHeaders().keySet();
				Iterator<String> itr = keys.iterator();
				
				while(itr.hasNext()) {
					String key = itr.next();
					rb.addHeader(key, param.getHeaders().get(key));
				}
			}
			
			if(CONTENTS_TYPE_FORM.equals(param.getContent_type())) {
				if(param.getParams().size() > 0) {
					Set<String> keys = param.getParams().keySet();
					Iterator<String> itr = keys.iterator();
					
					FormBody.Builder fb = new FormBody.Builder();
					while(itr.hasNext()) {
						String key = itr.next();
						fb.add(key, (String) param.getParams().get(key));
					}
					rb.post(fb.build());
				}
				
			} else if(CONTENTS_TYPE_JSON.equals(param.getContent_type())) {
				if(param.getParams().size() > 0) {
					Gson gson = new Gson();
					String json = gson.toJson(param.getParams());
					rb.post(RequestBody.create(MediaType.parse(CONTENTS_TYPE_JSON), json));
				}
			} else if(CONTENTS_TYPE_MULTIPART.equals(param.getContent_type())) {
				boolean exist_param = false;
				MultipartBody.Builder mb = new MultipartBody.Builder();
				mb.setType(MultipartBody.FORM);
				if(param.getParams().size() > 0) {
					exist_param = true;
					Set<String> keys = param.getParams().keySet();
					Iterator<String> itr = keys.iterator();
					
					while(itr.hasNext()) {
						String key = itr.next();
						mb.addFormDataPart(key, (String) param.getParams().get(key));
					}
				}
				
				if(param.getFiles().size() > 0) {
					exist_param = true;
					Set<String> keys = param.getFiles().keySet();
					Iterator<String> itr = keys.iterator();
					
					while(itr.hasNext()) {
						String key = itr.next();
						mb.addFormDataPart(key, param.getFiles().get(key).getName(), 
								RequestBody.create(MediaType.parse("application/octet-stream"), 
										param.getFiles().get(key)));
					}
				}
				
				if(exist_param) {
					rb.post(mb.build());
				}
			}
			
			Request request = rb.build();
			Response response = client.newCall(request).execute();
			res.setStatus(response.code());
			res.setRes(response.body().toString());
			
			System.out.println("request : " + request.toString() + " | headers : "+request.headers());
		    System.out.println("Response : " + response.body().string());
			
		} catch(Exception e) {
			log.error("Exception : {}", e.getMessage(), e);
			res.setStatus(HTTP_SYS_FAIL);
		}
		
		return res;
	}
}
