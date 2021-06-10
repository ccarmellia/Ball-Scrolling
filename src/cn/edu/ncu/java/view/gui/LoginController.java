/**
 * Copyright (C), 2015-2019, 南昌大学软件学院1807班
 * FileName: MyController
 * Author:   肖海军
 * Date:     2019/10/31 21:04
 * Description: fersrfew
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package cn.edu.ncu.java.view.gui;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 〈一句话功能简述〉</br> 
 * 〈fersrfew 〉
 * @author 肖海军
 * @create 2019/10/31
 * @since 1.0.0
 **/
public class LoginController {// implements Initializable{//
    @FXML
    Button confirm;
    @FXML AnchorPane root;
    @FXML Button cancel;
    @FXML Button forget;
    @FXML
    PasswordField userPwd;
    @FXML
    TextField userAccount;
    @FXML
    Label Account;
    @FXML Label pwd;
    String name;
    String password;

   public void login(ActionEvent event) throws Exception {
       userAccount.setUserData("6109119099");//设置默认账号密码
       userPwd.setUserData("190321");
       confirm.setOnAction(event1 -> {

        name=userAccount.getText();
        password=userPwd.getText();
         // if(name.equals(userAccount.getText())&&password==Integer.valueOf(userPwd.getText())){
                if(name.equals(userAccount.getUserData())&&password.equals(userPwd.getUserData())){
              Stage primaryStage = (Stage) confirm.getScene().getWindow();//将登录按钮)与Main类中的primaryStage(新窗口)绑定 并执行close()
              primaryStage.close();//打开新的窗口 所以要关闭当前的窗口
              Index we = new Index();//新窗口类
              Stage stage = new Stage();
              // MainApplication.stage.close();
              //  (new MainApp()).start(new Stage());
              try {
                  we.start(stage);//打开新窗口
              } catch (Exception e) {
                  e.printStackTrace();
              }
          } else {
              FadeTransition fade=new FadeTransition();
              fade.setDuration(Duration.seconds(0.5));
              fade.setNode(root);
              fade.setFromValue(0);
              fade.setToValue(1);
              fade.play();
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("错误提示");
              alert.setHeaderText(null);
              alert.setContentText("用户名或密码错误,请重新输入！");
              alert.showAndWait();
              userAccount.clear();
              userPwd.clear();
          }
          });
   }
   public void forget(ActionEvent event)throws Exception{
       forget.setOnAction(event1 -> {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("密码");
           alert.setHeaderText("提示");
           userAccount.setUserData("6109119099");//设置默认账号密码
           userPwd.setUserData("190321");
           alert.setContentText("账户为："+userAccount.getUserData()+"密码为："+userPwd.getUserData());
           alert.showAndWait();
   });
   }
    public void close(ActionEvent event) throws Exception {
        cancel.setOnAction(event1 -> {
            userAccount.setText("");
            userPwd.setText("");
        });
    }


}

