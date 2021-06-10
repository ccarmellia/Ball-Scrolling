package cn.edu.ncu.java.dao;

import java.util.Vector;

public abstract class ScoreOfTeamDAOImpl implements ScoreOfTeamDAO{
    /**
     * @Description: 显示球队积分榜
     */
    @Override
    public Vector showAllScoreOfTeam(String selecting,JDBCAdapter jdbc) {
        Vector exist = jdbc.query(selecting);
        if(exist.size() == 0){
            System.out.println("查询信息不存在！");
            return null;
        }
        return exist;
    }
}
