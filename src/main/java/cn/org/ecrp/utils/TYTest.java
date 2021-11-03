package cn.org.ecrp.utils;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * description <br>
 *
 * @author YourName
 * @date 2021-11-03 11:10
 */
public class TYTest {

    public void doGetAndPush() {

        // String url = "https://www.cytx56.com/PLATFORM-MODULE-CMS/reportForm/access";
        String url = "https://tianye.ztyl100.com/PLATFORM-MODULE-CMS/tl/transport/plan";

        for (int i=0; i<10; i++) {
            // 拼接请求参数
            Map<String, Object> requestMap = new HashMap<>();
            requestMap.put("id", "首页");
            requestMap.put("url","/");
            requestMap.put("isPortal", true);
            String jsonString = JSON.toJSONString(requestMap);
            System.out.println("jsonString:" + jsonString);

            String response = HttpRequest.post(url)
                    .header(Header.AUTHORIZATION,"1m86v3b979xchi4p9xnimyc2qe1fjsm3")
                    .header("appid","C000218")
                    .body(jsonString)
                    .execute().body();
            System.out.println("result:" + response);
//            try {
//                Thread.sleep(20);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

    }

}
