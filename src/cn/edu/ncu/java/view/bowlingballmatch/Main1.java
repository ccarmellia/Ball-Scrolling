/**
 * Copyright (C), 2015-2019, 南昌大学软件学院1807班
 * FileName: Main
 * Author:   肖海军
 * Date:     2019/11/10 20:57
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package cn.edu.ncu.java.view.bowlingballmatch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main1 extends Application {
    public Main1() {
    }
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("image2/judge.fxml"));
        primaryStage.setTitle("赛程信息");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 630.0, 655.0));
        BorderPane pane = new BorderPane();
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("image2/background.png")));
        pane.getStylesheets().add(
                getClass().getResource("image2/MainStyle.css").toExternalForm());

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

