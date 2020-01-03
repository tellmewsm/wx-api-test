package com.wx.correlation;

import com.googlecode.aviator.AviatorEvaluator;
import com.jayway.jsonpath.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuxi
 * @description 检查点校验
 * 检查点如：$.code=200;$.message=成功!，JsonPath.read()获取真实值、检查点解析单个检查点值循环比较，失败返回false
 * @date 2020年1月3日
 */
public class CheckPoints {

    private static Logger logger = LoggerFactory.getLogger(CheckPoints.class);

    public static boolean checkByJsonPath(String json, String params) {

        Boolean result = true;

        if (params != null && !"".equals(params) && !"null".equals(params)) {
            String[] ps = params.split(";");
            for (String p : ps) {

                String[] values = p.split("=|>|<|>=|<=");
                p = p.replace(values[0], "data");
                Map<String, Object> env = new HashMap<String, Object>();

                //获取实际返回值，读取值
                Object value = JsonPath.read(json, values[0]);
                p = p.replace("=", "==");
                if (value instanceof String) {
                    p = p.replace(values[1], covertToAviatorString(values[1]));

                }
                env.put("data", value);
                result = (Boolean) AviatorEvaluator.execute(p, env);

                if (result == false) {
                    logger.info("[check]="+result);
                    return result;
                }
            }
        }
        logger.info("[check]="+result);
        return result;
    }

    private static String covertToAviatorString(String value) {
        return "'" + value + "'";
    }


    /**
     * AviatorEvaluator.execute 表达式 值比较
     *
     * @param args
     */
    public static void main(String args[]) {

        //日期比较
        Map env7 = new HashMap();
        final Date date = new Date();
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS").format(date);
        env7.put("date1", date);
        env7.put("dateStr", dateStr);
        Boolean result = (Boolean) AviatorEvaluator.execute("date1==dateStr", env7);
        System.out.println(result);
        result = (Boolean) AviatorEvaluator.execute("date1 < '2009-12-20 00:00:00:00' ", env7);
        System.out.println(result);
        result = (Boolean) AviatorEvaluator.execute("date1 < '2200-12-20 00:00:00:00' ", env7);
        System.out.println(result);
    }

}
