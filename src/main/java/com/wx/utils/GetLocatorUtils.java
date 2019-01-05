package com.wx.utils;

/**
 * 工具类：读配置文件resource.config文件
 * 再依据分隔符进行过滤
 * +ProUtil
 */
@SuppressWarnings("unused")
public class GetLocatorUtils {
	
		GetProUtil locator;
		
		public GetLocatorUtils(String locatorFile){
			
		this.locator = new GetProUtil(locatorFile);
         
		}
	
	/**
	 * 获取元素属性对应数据值
	 * @param locatorType
	 * @return
	 * @throws Exception
	 */
	public String getLocalor(String by) {
		String locatorKeyValue = locator.getKey(by);
		
		// 将配置对象中的定位类型存到 locatorType 变量，将定位表达式的值存入到 locatorValue 变量
		String type = locatorKeyValue.split(">")[0];// name
		String value = locatorKeyValue.split(">")[1];// 登录
		
		// 输出 locatorType 变量值和locatorValue 变量值，验证是否赋值正确
		return value;
	}

}
