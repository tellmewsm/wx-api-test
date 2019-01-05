/**
 * 
 */
package com.wx.testcases;

import com.wx.description.WxParameters;
import com.wx.description.WxRequest;
import java.util.Map;
import com.jayway.restassured.response.Response;
/**
 * @author wuxi
 * @date 2018年11月2日
 */
public interface WxAutoTest {

	// json格式https
	@WxRequest(protocol = "https", wmethod = "post", description = "body")
	Response HttpsPostBody(@WxParameters("body") String body, @WxParameters("url") String url);

	// json格式http
	@WxRequest(protocol = "http", wmethod = "post", description = "body")
	Response HttpPostBody(@WxParameters("body") String body, @WxParameters("url") String url);

	// json格式https+header
	@WxRequest(protocol = "https", wmethod = "post", description = "body")
	Response HttpsPostBodyHeader(@WxParameters("url") String url,@WxParameters("body") String body,@WxParameters("header") Map<String, Object> header);

	// key=value格式https
	@WxRequest(protocol = "https", wmethod = "post", description = "parameters")
	Response HttpsPostPara(@WxParameters("parameters") Map<String, Object> parameters, @WxParameters("url") String url);

	// key=value格式http
	@WxRequest(protocol = "http", wmethod = "post", description = "parameters")
	Response HttpPostPara(@WxParameters("parameters") Map<String, Object> parameters, @WxParameters("url") String url);

}
