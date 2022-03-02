package org.ecrp;

import org.ecrp.controller.index.IndexController;
import org.ecrp.ui.base.FxApp;
import org.ecrp.ui.dialog.FxAlerts;
import org.ecrp.ui.util.JavaFxViewUtil;
import org.ecrp.utils.Config;
import org.ecrp.utils.ECSystemUtil;
import org.ecrp.utils.StageUtils;
import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
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
        FxApp.init(primaryStage, LOGO_PATH);
        FxApp.setupIcon(primaryStage);
        FxApp.styleSheets.add(Main.class.getResource("/css/jfoenix-main.css").toExternalForm());

        primaryStage.setResizable(true);
        primaryStage.setTitle(RESOURCE_BUNDLE.getString("Title") + Config.EcrpVersions);
        primaryStage.setOnCloseRequest(this::confirmExit);

        if (Config.getBoolean(Config.Keys.NewLauncher, false)) {
            loadNewUI(primaryStage);
        } else {
            loadClassicUI(primaryStage);
        }

        StageUtils.loadPrimaryStageBound(primaryStage);
        primaryStage.show();
        StageUtils.updateStageStyle(primaryStage);

//        scene = new Scene(loadFXML("primary"), 640, 480);
//        stage.setScene(scene);
//        stage.show();

    }

    private void loadNewUI(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("/org/ecrp/fxmlView/newui/main.fxml"));
        fxmlLoader.setResources(RESOURCE_BUNDLE);

        Parent root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root));
    }

    private void loadClassicUI(Stage primaryStage) throws IOException {
        FXMLLoader fXMLLoader = IndexController.getFXMLLoader();
        Parent root = fXMLLoader.load();

        JFXDecorator decorator = JavaFxViewUtil.getJFXDecorator(
                primaryStage,
                RESOURCE_BUNDLE.getString("Title") + Config.EcrpVersions,
                LOGO_PATH,
                root
        );
        decorator.setOnCloseButtonAction(() -> confirmExit(null));

        Scene scene = JavaFxViewUtil.getJFXDecoratorScene(decorator);
        primaryStage.setScene(scene);
    }

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
