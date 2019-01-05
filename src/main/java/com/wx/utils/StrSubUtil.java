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
		Pattern pattern = Pattern.compile(rgex);// 匹配的模式
		Matcher m = pattern.matcher(soap);
		while (m.find()) {
			int i = 1;
			list.add(m.group(i));
			i++;
		}
		return list;
	}

	public static String getSubUtilSimple(String soap, String rgex) {
		Pattern pattern = Pattern.compile(rgex);// 匹配的模式
		Matcher m = pattern.matcher(soap);
		while (m.find()) {
			return m.group(1);
		}
		return "";
	}

	public static void main(String[] args) {

		// 此处是中文输入的（）正则获取（）中内容
		String str = "BD/赛事数据（32）/master";
		Matcher mat = Pattern.compile("(?<=\\（)(\\S+)(?=\\）)").matcher(str);
		while (mat.find()) {
			System.out.println(mat.group());

		}
	}

	public static String description(String log) {
		String description = null;	
		try {
		if (StrSubUtil.getSubUtil(log, "~(.*?)~").size() > 1) {
			description = StrSubUtil.getSubUtil(log, "~(.*?)~").get(0)
					+ StrSubUtil.getSubUtil(log, "~(.*?)~").get(1);
		} else {
			description = StrSubUtil.getSubUtil(log, "~(.*?)~").get(0) + "条件下" + "异常";
		}
		}catch (Exception e) {
		}
		return description;
	}

}