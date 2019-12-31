package com.wx.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author wuxi
 * @date 2018年11月2日
 */
public class StrSubUtil {

	public static List<String> getSubUtil(String soap, String rgex) {
		List<String> list = new ArrayList<String>();
		Pattern pattern = Pattern.compile(rgex);
		Matcher m = pattern.matcher(soap);
		while (m.find()) {
			int i = 1;
			list.add(m.group(i));
			i++;
		}
		return list;
	}

	public static String getSubUtilSimple(String soap, String rgex) {
		Pattern pattern = Pattern.compile(rgex);
		Matcher m = pattern.matcher(soap);
		while (m.find()) {
			return m.group(1);
		}
		return "";
	}

	public static List<String> getTwoSubUtil(String soap, String rgex) {
		List<String> list = new ArrayList<String>();
		Pattern pattern = Pattern.compile(rgex);
		Matcher m = pattern.matcher(soap);
		while (m.find()) {
			int i = 1;
			list.add(m.group(i));
			list.add(m.group(i+1));
			i++;
		}
		return list;
	}

	public static void main(String[] args) {

		// 此处是中文输入的（）正则获取（）中内容
		String str = "BD/赛事数据（32）/master";
		String descriptions = StrSubUtil.getSubUtilSimple(str, "(?<=\\（)(\\S+)(?=\\）)");
		System.out.println(descriptions);

		String log = "{\"code\":200,\"message\":\"成功!\",\"result\":\"\",\"code\":200,\"message\":\"成功!!\",\"result\":\"\"}";
		String description = StrSubUtil.getSubUtil(log, "message\":\"(.*?)\",\"result").get(1);
		System.out.println(description);
		String description1 = StrSubUtil.getSubUtilSimple(log, "message\":\"(.*?)\",\"result");
		System.out.println(description1);

		String test = "{\"msg\":\"登录成功\",\"uid\":\"DAD3483647A94DBDB174C4C036CA8A80\",\"code\":\"1\"}";
		String description2 = StrSubUtil.getTwoSubUtil(test, "\"uid\":\"(.+?)\",\"code\":\"(.+?)\"}").get(0);
		String description3 = StrSubUtil.getTwoSubUtil(test, "\"uid\":\"(.+?)\",\"code\":\"(.+?)\"}").get(1);
		System.out.println(description2);
		System.out.println(description3);
	}

}