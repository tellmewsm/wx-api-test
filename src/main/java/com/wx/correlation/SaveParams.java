package com.wx.correlation;

import com.jayway.jsonpath.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author wuxi
 * @description JsonPath读取json格式文件后转成Map
 * @date 2020年1月3日
 */
public class SaveParams {

    private static Logger logger = LoggerFactory.getLogger(SaveParams.class);

    public static Map<String, Object> saveMap = new HashMap<String, Object>();

    public static Map<String, Object> saveMapbyJsonPath(String json, String save) {
        if (!save.equals("")) {
            Map<String, Object> map = StringToMap.covertStringToMp(save);
            if (map != null) {
                //遍历key value值
                for (Entry<String, Object> entry : map.entrySet()) {
                    //logger.info("[correlation]"+entry.getKey() + " ---  " + entry.getValue().toString());
                    String key = entry.getKey();
                    // 提取数组
                    if (key.endsWith("*")) {
                        List<Object> list = JsonPath.read(json, entry.getValue().toString());
                        String before = key.split("_")[0];
                        for (int i = 0; i < list.size(); i++) {
                            logger.info("[correlation]=" + before + "_g" + (i + 1) + "_________" + list.get(i));
                            saveMap.put(before + "_g" + (i + 1), list.get(i));
                        }
                    } else {
                        // 单个关联
                        saveMap.put(key, JsonPath.read(json, entry.getValue().toString()));
                    }
                }
            }
            logger.info("[correlation]=" + saveMap);
        }
        return saveMap;
    }

    public static Object get(String key) {
        return saveMap.get(key);
    }

}
