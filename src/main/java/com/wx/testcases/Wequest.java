package com.wx.testcases;

import com.wx.description.WxParameters;
import com.wx.description.WxRequest;
import java.util.Map;
import com.wx.entity.ResponseResult;

/**
 * @author wuxi
 * @date 2018年11月2日
 */
public interface Wequest {

	// http——get
	@WxRequest(protocol = "http", wmethod = "get", description = "body")
	ResponseResult HttpGet(@WxParameters("url") String url);

	// https——get
	@WxRequest(protocol = "https", wmethod = "get", description = "body")
	ResponseResult HttpsGet(@WxParameters("url") String url);

	// json格式https
	@WxRequest(protocol = "https", wmethod = "post", description = "body")
	ResponseResult HttpsPostBody(@WxParameters("body") String body, @WxParameters("url") String url);

	// json格式http
	@WxRequest(protocol = "http", wmethod = "post", description = "body")
	ResponseResult HttpPostBody(@WxParameters("body") String body, @WxParameters("url") String url);

	// json格式https+header
	@WxRequest(protocol = "https", wmethod = "post", description = "body")
	ResponseResult HttpsPostBodyHeader(@WxParameters("body") String body,@WxParameters("url") String url,@WxParameters("header") Map<String, Object> header);

	// key=value格式https
	@WxRequest(protocol = "https", wmethod = "post", description = "parameters")
	ResponseResult HttpsPostPara(@WxParameters("parameters") Map<String, Object> parameters, @WxParameters("url") String url);

	@WxRequest(protocol = "https", wmethod = "post", description = "parameters")
	ResponseResult HttpsPostParaHeader(@WxParameters("parameters") Map<String, Object> parameters, @WxParameters("url") String url,@WxParameters("header") Map<String, Object> header);

	// key=value格式http
	@WxRequest(protocol = "http", wmethod = "post", description = "parameters")
	ResponseResult HttpPostPara(@WxParameters("parameters") Map<String, Object> parameters, @WxParameters("url") String url);

}
