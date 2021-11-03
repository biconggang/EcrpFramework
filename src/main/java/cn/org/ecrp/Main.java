package cn.org.ecrp;

import cn.org.ecrp.ui.dialog.FxAlerts;
import cn.org.ecrp.utils.Config;
import cn.org.ecrp.utils.ECSystemUtil;
import cn.org.ecrp.utils.StageUtils;
import cn.org.ecrp.utils.TYTest;
import com.alibaba.fastjson.JSON;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;



/**
 * JavaFX Main
 */
public class Main extends Application {

    public static final String LOGO_PATH = "/images/icon.jpg";

    public static ResourceBundle RESOURCE_BUNDLE;

    private static Scene scene;
    private static Stage stage;

    public static void main(String[] args) {

        // 初始化本地语言
        RESOURCE_BUNDLE = ECSystemUtil.initSystemLocal();

        // 添加外部jar包
        // ECSystemUtil.addJarByLibs();

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;

        // 初始化 JavaFX 全局设置
//        FxApp.init(primaryStage, LOGO_PATH);
//        FxApp.setupIcon(primaryStage);
//        FxApp.styleSheets.add(Main.class.getResource("/css/jfoenix-main.css").toExternalForm());
//
//        primaryStage.setResizable(true);
//        primaryStage.setTitle(RESOURCE_BUNDLE.getString("Title") + Config.EcrpVersions);
//        primaryStage.setOnCloseRequest(this::confirmExit);
//
//        if (Config.getBoolean(Config.Keys.NewLauncher, false)) {
//            loadNewUI(primaryStage);
//        } else {
//            // loadClassicUI(primaryStage);
//        }
//
//        StageUtils.loadPrimaryStageBound(primaryStage);
//        primaryStage.show();
//        StageUtils.updateStageStyle(primaryStage);

        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();

        TYTest test = new TYTest();
        test.doGetAndPush();

        // httpsTest();
    }

    private void httpsTest() {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //HttpClients.createDefault();

        for (int i=0; i<10; i++) {

            String url = "https://www.cytx56.com/PLATFORM-MODULE-CMS/reportForm/access";
            // String url = "https://www.cytx56.com/PLATFORM-MODULE-CMS/appVUpdate/getVersion";

            HttpPost httpPost = new HttpPost(url);

            // 拼接请求参数
            Map<String, Object> requestMap = new HashMap<>();
            requestMap.put("id", "首页");
            requestMap.put("url","/");
            requestMap.put("isPortal", true);
            String jsonString = JSON.toJSONString(requestMap);
            System.out.println("jsonString:" + jsonString);

            // 拼接请求
            StringEntity entity = new StringEntity(jsonString, "UTF-8");
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type", "application/json;charset=utf8");

            // 响应模型
            CloseableHttpResponse response = null;
            try {
                // 由客户端执行(发送)Post请求
                response = httpClient.execute(httpPost);

                // 从响应模型中获取响应实体
                HttpEntity responseEntity = response.getEntity();

                System.out.println("响应状态为:" + response.getStatusLine());
                if (responseEntity != null) {
                    System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                    System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                // 释放资源
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            // 释放资源
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void loadNewUI(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("/cn/org/ecrp/fxmlView/newui/main.fxml"));
        fxmlLoader.setResources(RESOURCE_BUNDLE);

        Parent root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root));
    }

//    private void loadClassicUI(Stage primaryStage) throws IOException {
//        FXMLLoader fXMLLoader = IndexController.getFXMLLoader();
//        Parent root = fXMLLoader.load();
//
//        JFXDecorator decorator = JavaFxViewUtil.getJFXDecorator(
//                primaryStage,
//                RESOURCE_BUNDLE.getString("Title") + Config.xJavaFxToolVersions,
//                LOGO_PATH,
//                root
//        );
//        decorator.setOnCloseButtonAction(() -> confirmExit(null));
//
//        Scene scene = JavaFxViewUtil.getJFXDecoratorScene(decorator);
//        primaryStage.setScene(scene);
//    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //
    //  事件处理
    ///////////////////////////////////////////////////////////////////////////////////////
    private void confirmExit(Event event) {
        if (Config.getBoolean(Config.Keys.ConfirmExit, true)) {
            if (FxAlerts.confirmYesNo("退出应用", "确定要退出吗？")) {
                doExit();
            } else if (event != null) {
                event.consume();
            }
        } else {
            doExit();
        }
    }

    private void doExit() {
        StageUtils.savePrimaryStageBound(stage);
        Platform.exit();
        System.exit(0);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //
    //  成员访问
    ///////////////////////////////////////////////////////////////////////////////////////
    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Main.stage = stage;
    }
}
