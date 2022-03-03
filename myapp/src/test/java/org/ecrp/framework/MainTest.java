package org.ecrp.framework;

import org.junit.After;
import org.junit.Before;

/**
 * description 测试类
 *
 * @author bicg
 * @date 2021-11-04 13:25
 */
public class MainTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

//    @Test
//    public void httpsTest() {
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        //HttpClients.createDefault();
//
//        for (int i=0; i<10; i++) {
//
//            String url = "https://www.cytx56.com/PLATFORM-MODULE-CMS/reportForm/access";
//            // String url = "https://www.cytx56.com/PLATFORM-MODULE-CMS/appVUpdate/getVersion";
//
//            HttpPost httpPost = new HttpPost(url);
//
//            // 拼接请求参数
//            Map<String, Object> requestMap = new HashMap<>();
//            requestMap.put("id", "首页");
//            requestMap.put("url","/");
//            requestMap.put("isPortal", true);
//            String jsonString = JSON.toJSONString(requestMap);
//            System.out.println("jsonString:" + jsonString);
//
//            // 拼接请求
//            StringEntity entity = new StringEntity(jsonString, "UTF-8");
//            httpPost.setEntity(entity);
//            httpPost.setHeader("Content-Type", "application/json;charset=utf8");
//
//            // 响应模型
//            CloseableHttpResponse response = null;
//            try {
//                // 由客户端执行(发送)Post请求
//                response = httpClient.execute(httpPost);
//
//                // 从响应模型中获取响应实体
//                HttpEntity responseEntity = response.getEntity();
//
//                System.out.println("响应状态为:" + response.getStatusLine());
//                if (responseEntity != null) {
//                    System.out.println("响应内容长度为:" + responseEntity.getContentLength());
//                    System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
//                }
//            } catch (ClientProtocolException e) {
//                e.printStackTrace();
//            } catch (ParseException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            try {
//                // 释放资源
//                if (response != null) {
//                    response.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        try {
//            // 释放资源
//            if (httpClient != null) {
//                httpClient.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

//    @Test
//    public void TYTest() {
//
//        // String url = "https://www.cytx56.com/PLATFORM-MODULE-CMS/reportForm/access";
//        String url = "https://tianye.ztyl100.com/PLATFORM-MODULE-CMS/tl/transport/plan";
//
//        for (int i=0; i<10; i++) {
//            // 拼接请求参数
//            Map<String, Object> requestMap = new HashMap<>();
//            requestMap.put("id", "首页");
//            requestMap.put("url","/");
//            requestMap.put("isPortal", true);
//            String jsonString = JSON.toJSONString(requestMap);
//            System.out.println("jsonString:" + jsonString);
//
//            String response = HttpRequest.post(url)
//                    .header(Header.AUTHORIZATION,"1m86v3b979xchi4p9xnimyc2qe1fjsm3")
//                    .header("appid","C000218")
//                    .body(jsonString)
//                    .execute().body();
//            System.out.println("result:" + response);
////            try {
////                Thread.sleep(20);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
//        }
//    }

}
