package cn.edu.ncu.java.service;

import cn.edu.ncu.java.dao.*;
import cn.edu.ncu.java.entity.ShooterList;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class ShooterListService {
    private ShooterListDAO shooterListDAO;
    private JDBCAdapter jdbc;
    /**
     * @Description: 构造方法
     * @Name: ShooterListService
     * @Param:[]
     * @Return:
     */
    public ShooterListService() throws SQLException, IOException, BiffException {
        this.shooterListDAO = new ShooterListDAOImpl();
        this.jdbc = new JDBCAdapter();
    }


    /**
     * @Description: 被方法showAllShooterListForMale()、showAllShooterListForMale()和
     *               showAllShooterListForFemale()调用
     * @Name: showAllShooterList
     * @Param: [table]
     * @Return: java.util.ArrayList<cn.edu.ncu.java.entity.ShooterList>
     */
    private ArrayList<ShooterList> showAllShooterList(Vector table){
        ArrayList<ShooterList> data = new ArrayList<>();
        if(table!=null) {
            Iterator<Vector> it = table.iterator();
            while (it.hasNext()) {
                Vector row = it.next();
                String[] value = new String[row.size()];
                int i = 0;
                Iterator<Object> it2 = row.iterator();
                while (it2.hasNext()) {
                    value[i++] = it2.next() + "";
                }
                ShooterList shooterList = new ShooterList(value[0], value[1], value[2], value[3],
                        value[4], value[5], value[6]);
                //System.out.println(shooterList.toString());
                assert data != null;
                data.add(shooterList);
            }
        }else {
            data = null;
        }
        return data;
    }
}
