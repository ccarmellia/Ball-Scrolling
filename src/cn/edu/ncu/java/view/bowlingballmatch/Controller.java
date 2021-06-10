/**
 * Copyright (C), 2015-2019, 南昌大学软件学院1807班
 * FileName: Controller
 * Author:   肖海军
 * Date:     2019/11/10 20:59
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package cn.edu.ncu.java.view.bowlingballmatch;



import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.ArrayList;

public class Controller {
    @FXML
    private Button mBtnDelete;
    @FXML
    private TableView mTable;
    @FXML
    private TextField mTxtName;
    @FXML
    private TextField mTxtSex;
    @FXML
    private TextField mTxtAge;
    @FXML
    private TextField mTxtBirthday;

    @FXML
    private TableColumn<Bowlingballjudgement, Boolean> mColumnSelect;
    @FXML
    private TableColumn<Bowlingballjudgement, String> mColumnName;
    @FXML
    private TableColumn<Bowlingballjudgement, String> mColumnSex;
    @FXML
    private TableColumn<Bowlingballjudgement, String> mColumnAge;
    @FXML
    private TableColumn<Bowlingballjudgement, String> mColumnBirthday;

    private final ObservableList<Bowlingballjudgement> data = FXCollections.observableArrayList();

    public Controller() {

    }

    @FXML
    public void onDelete(ActionEvent event) {
        this.deleteStudents();
    }

    @FXML
    private void initialize() {
        this.mColumnSelect.setCellFactory(CellFactory.tableCheckBoxColumn(new Callback<Integer, ObservableValue<Boolean>>() {
            public ObservableValue<Boolean> call(Integer index) {
                final Bowlingballjudgement g = (Bowlingballjudgement) Controller.this.mTable.getItems().get(index);
                ObservableValue<Boolean> ret = new SimpleBooleanProperty(g, "selected", g.getSelected());
                ret.addListener(new ChangeListener<Boolean>() {
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        g.setSelected(newValue);
                    }
                });
                return ret;
            }
        }));
        Callback<TableColumn<Bowlingballjudgement, String>, TableCell<Bowlingballjudgement, String>> cellFactory = (p) -> {
            return new EditingCell3();
        };
        this.mColumnSelect.setCellValueFactory(new PropertyValueFactory("selected"));
        this.mColumnName.setCellValueFactory(new PropertyValueFactory("name"));
        this.mColumnName.setCellFactory(cellFactory);
        this.mColumnSex.setCellValueFactory(new PropertyValueFactory("sex"));
        this.mColumnSex.setCellFactory(cellFactory);
        this.mColumnSex.setOnEditCommit((t) -> {
            ((Bowlingballjudgement)t.getTableView().getItems().get(t.getTablePosition().getRow())).setSex((String)t.getNewValue());
        });
        this.mColumnAge.setCellValueFactory(new PropertyValueFactory("age"));
        this.mColumnAge.setCellFactory(cellFactory);
        this.mColumnBirthday.setCellValueFactory(new PropertyValueFactory("birthday"));
        this.mColumnBirthday.setCellFactory(cellFactory);

        this.mTable.setItems(this.data);
        this.mTable.setEditable(true);
    }

    @FXML
    public void onAdd(ActionEvent event) {
        if (this.mTxtName.getText() != null && this.mTxtSex.getText() != null&&this.mTxtAge.getText()!=null&&this.mTxtBirthday.getText()!=null) {
            this.data.add(new Bowlingballjudgement(this.mTxtName.getText(), this.mTxtSex.getText(),this.mTxtAge.getText(),this.mTxtBirthday.getText()));
            this.mTxtName.clear();
            this.mTxtSex.clear();
            this.mTxtAge.clear();
            this.mTxtBirthday.clear();
        }

    }

    public boolean deleteStudents() {
        new ArrayList();
        int size = this.data.size();
        if (size <= 0) {
            return false;
        } else {
            for(int i = size - 1; i >= 0; --i) {
                Bowlingballjudgement s = (Bowlingballjudgement)this.data.get(i);
                if (s.getSelected()) {
                    this.data.remove(s);
                }
            }

            return true;
        }
    }
}
