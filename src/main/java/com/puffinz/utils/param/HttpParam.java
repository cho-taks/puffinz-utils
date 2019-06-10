package com.puffinz.utils.param;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.puffinz.utils.util.HttpUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class HttpParam {
	private Map<String, String> headers = new HashMap<String, String>();
	private Map<String, Object> params = new HashMap<String, Object>();
	private Map<String, File> files = new HashMap<String, File>();
	private String url;
	private String content_type = HttpUtil.CONTENTS_TYPE_FORM;
	private String accept;
	private int retry_cnt = HttpUtil.HTTP_RETRY_COUNT;
	private int connect_timeout = 1000;
	private int read_timeout = 1000;
}