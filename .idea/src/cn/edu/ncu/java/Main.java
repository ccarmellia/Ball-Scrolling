package cn.edu.ncu.java;


import cn.edu.ncu.java.dao.*;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.sql.SQLException;


public class Main {
    private static TeamDAO teamDAO = new TeamDAOImpl();
    private static PlayerDAO playerDAO = new PlayerDAOImpl();

    public static void main(String[] args) throws IOException, SQLException, BiffException {
        JDBCAdapter jdbc = new JDBCAdapter();
    }
}
