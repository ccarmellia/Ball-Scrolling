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
package cn.edu.ncu.java.view.match;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class Main extends Application {
    public Main() {
    }
    public void start(Stage primaryStage) throws Exception {

        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("image/match.fxml"));
        primaryStage.setTitle("保龄球比赛");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 1012.0, 600.0));
        //primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("image4/background.png")));
      BorderPane pane = new BorderPane();
        pane.getStylesheets().add(
              getClass().getResource("image/MainStyle.css").toExternalForm());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

