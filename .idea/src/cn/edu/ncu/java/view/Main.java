/**
 * Copyright (C), 2015-2019, 南昌大学软件学院1807班
 * FileName: Main1
 * Author:   肖海军
 * Date:     2019/10/31 21:05
 * Description: dxgfsed
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package cn.edu.ncu.java.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.sql.SQLException;


public class Main extends Application {


    public Main() throws SQLException, IOException, BiffException {
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        // Read file fxml and draw interface.
        Parent root = FXMLLoader.load(getClass().getResource("gui/images/MainWindow.fxml"));

        primaryStage.setTitle("保龄球比赛管理系统");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("gui/images/background.png")));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}