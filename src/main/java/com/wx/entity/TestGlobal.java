/**
 * 
 */
package com.wx.entity;

import java.util.HashMap;
import java.util.Map;

/**
* @author wuxi
* @date 2018年11月2日
 */
public class TestGlobal {

	private String  protocol;
	
	private String  wmethod;
	
	private String  url;
	
	private String  body;
	
	private String  description;
	
	private Map<String, ?> Headers=new HashMap<>();
	
    private Map<String, ?> params = new HashMap<>();
    

	/**
	 * @return the protocol
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * @param protocol the protocol to set
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	/**
	 * @return the wmethod
	 */
	public String getWmethod() {
		return wmethod;
	}

	/**
	 * @param wmethod the wmethod to set
	 */
	public void setWmethod(String wmethod) {
		this.wmethod = wmethod;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return the params
	 */
	public Map<String, ?> getParams() {
		return params;
	}

	/**
	 * @param args the params to set
	 */
	public void setParams(Map<String, ?> args) {
		this.params = args;
	}

	/**
	 * @return the headers
	 */
	public Map<String, ?> getHeaders() {
		return Headers;
	}

	/**
	 * @param args the headers to set
	 */
	public void setHeaders(Map<String, ?> args) {	
		Headers = args;
	}
	
}
