package cn.edu.ncu.java.dao;

import java.util.Vector;

public class ShooterListDAOImpl implements ShooterListDAO{
    /**
     * @Description: 显示射手榜
     */
    @Override
    public Vector showAllShooterList(String selecting,JDBCAdapter jdbc) {
        Vector exist = jdbc.query(selecting);
        if(exist.size() == 0){
            System.out.println("查询信息不存在！");
            return null;
        }
        return exist;
    }

    @Override
    public Vector showAllShooterListForFemale(JDBCAdapter jdbc) {
        String selecting = "select * from shooterlist where teamname in(select distinct team from player where gender='女') order by ranking";
        return showAllShooterList(selecting,jdbc);
    }

    @Override
    public Vector showAllShooterListForMale(JDBCAdapter jdbc) {
        String selecting = "select * from shooterlist where teamname not in(select name from team where college=NULL) and teamname not in(select distinct team from player where gender='女') order by ranking";
        return showAllShooterList(selecting,jdbc);
    }

    @Override
    public Vector showAllShooterListForAdult(JDBCAdapter jdbc) {
        String selecting = "select * from shooterlist where teamname in(select name from team where college=NULL) order by ranking";
        return showAllShooterList(selecting,jdbc);
    }
}
