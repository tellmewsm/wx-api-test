package com.wx.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * 读写配置文件
 * 打印出来自己需要的内容
 *
 */
public class GetProUtil {
	private String file;
	private Properties prop;

	/**
	 * 构造方法
	 * @throws Exception 
	 */
	public GetProUtil(String file) {
		this.file = file;
		this.prop = readProperties();
	}

	/**
	 * 读取资源文件,并处理中文乱码
	 * 
	 * @return
	 * @throws Exception 
	 */
	private Properties readProperties(){
		Properties properties = new Properties();
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
			BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
			properties.load(bf);
			inputStream.close(); // 关闭流
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		return properties;
	}

	/**
	 * 获取某项文本内容
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String getKey(String key){
		if (prop.containsKey(key)) {
			return prop.getProperty(key);
		} else {

			throw new RuntimeException("get key: " + key + " is not exist in " + file);
		}
	}

	/**
	 * 获取资源文件key及返回值
	 * @param key
	 * @param value
	 */
	public void setProp(String key, String value) {
		if (prop == null) {
			prop = new Properties();
		}
		try {
			OutputStream outputStream = new FileOutputStream(file);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
			prop.setProperty(key, value);
			prop.store(bw, key + " value");
			bw.close();
			outputStream.close();
		} catch (Exception e) {
			// TODO: handle exception

			throw new RuntimeException(e.getMessage());
		}

	}

	/**
	 * 获取某个配置文件里的所有配置信息
	 * @return
	 */
	public HashMap<String, String> getAllKeyValue() {
		HashMap<String, String> keyValus = new HashMap<String, String>();
		Iterator it = prop.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			keyValus.put(key.toString(), value.toString());
		}
		return keyValus;
	}

	public static void main(String[] args) throws Exception {
     
	}
}
