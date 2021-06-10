package cn.edu.ncu.java.dao;

import jxl.read.biff.BiffException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.apache.tools.ant.types.EnumeratedAttribute;
import java.io.*;
import java.sql.*;
import java.util.Vector;

public class JDBCAdapter {
    private String user;            // 数据库用户名
    private String password;        // 数据库密码
    private String localhost;       //主机名
    private String DatabaseName;    //数据库名
    private Connection conn = null; // 注册驱动
    private Statement statement = null;
    private ResultSet res = null;
    private Vector rows;
    private ExcelReadDAO excelReadDAO = new ExcelReadDAOImpl();

    private void getParams(){
        try {
            System.out.println();
            File file = new File(this.getClass().getClassLoader().getResource("cn/edu/ncu/java/otherfiles").getPath()+"databaseInit.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            user = br.readLine();
            password = br.readLine();
            localhost = br.readLine();
            DatabaseName = br.readLine();
            br.close();
            fr.close();
       } catch (FileNotFoundException e) {
            System.err.println("未找到文件！");
       } catch (IOException e) {
            System.err.println("读取文件数据错误！");
        }
    }

    public JDBCAdapter() throws SQLException, IOException, BiffException {
        getParams();
        if(!createDatebase()) connector();
        if (tableIsEmplty()) {

            excelReadDAO.ReadExcel(conn, new File(this.getClass().getClassLoader().getResource("cn/edu/ncu/java/otherfiles").getPath()+"player.xls"), "insert player values(?,?,?,?,?,?,?,?,?);");
            excelReadDAO.ReadExcel(conn, new File(this.getClass().getClassLoader().getResource("cn/edu/ncu/java/otherfiles").getPath()+"team.xls"), "insert team values(?,?,?,?);");
            excelReadDAO.ReadExcel(conn, new File(this.getClass().getClassLoader().getResource("cn/edu/ncu/java/otherfiles").getPath()+"schedule.xls"), "insert schedule values(?,?,?,?,?,?,?);");
        }
    }

    public void connector(){
        try {
            java.sql.Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            String url = "jdbc:mysql://localhost:" + localhost + "/" + DatabaseName +
                        "?serverTimezone=UTC";
            conn = DriverManager.getConnection(url, user, password);
            if(conn!=null) {System.out.println("sucess connect to mysql");}
            statement = conn.createStatement();
        } catch (SQLException ex2) {
            ex2.printStackTrace();
            System.err.println("连接字符串错误，或者语句对象错误！");
        }
    }

    public boolean tableIsEmplty(){
        Vector table1 = query("select * from schedule");
        Vector table2 = query("select * from player");
        Vector table3  = query("select * from team");
        return table1.isEmpty() & table2.isEmpty() & table3.isEmpty();
    }

    public boolean createDatebase() throws SQLException {
        boolean result = false;
        try{
            Driver driver = new com.mysql.cj.jdbc.Driver();//获取数据库连接
            DriverManager.registerDriver(driver);
            String url = "jdbc:mysql://localhost:3306/project_dev?serverTimezone=UTC";
            //String url = "jdbc:mysql://localhost_new:project_dev?serverTimezone=UTC";
            conn = DriverManager.getConnection(url, user, password);
            statement = conn.createStatement();
            if(conn!=null) {System.out.println("sucess connect to mysql");}
            Vector exist = query("SELECT count(*) TABLES, table_schema FROM information_schema.TABLES where table_schema = '"+DatabaseName+"' GROUP BY table_schema;");
            if(exist.size() == 0){
                exeSQLSript(url,";",this.getClass().getClassLoader().getResource("cn/edu/ncu/java/otherfiles").getPath()+"bowlingballsys.sql"); //执行脚本
                exeSQLSript(url,"$",this.getClass().getClassLoader().getResource("cn/edu/ncu/java/otherfiles").getPath()+"Trigger.sql");
                result = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }

    public void exeSQLSript(String url, String delimiter, String sqlAddress){
        SQLExec sqlExec = new SQLExec();
        String mysqlDriver = "com.mysql.cj.jdbc.Driver";
        sqlExec.setDriver(mysqlDriver);
        sqlExec.setUrl(url);
        sqlExec.setUserid(this.user);
        sqlExec.setPassword(this.password);
        sqlExec.setEncoding("UTF-8");
        sqlExec.setSrc(new File(sqlAddress));
        sqlExec.setOnerror((SQLExec.OnError)(EnumeratedAttribute.getInstance(SQLExec.OnError.class, "abort")));
        sqlExec.setPrint(true);
        sqlExec.setOutput(new File(this.getClass().getClassLoader().getResource("cn/edu/ncu/java/otherfiles").getPath()+"bowlingballsys.out"));
        sqlExec.setDelimiter(delimiter);
        sqlExec.setProject(new Project());
        sqlExec.execute();
    }

    public void closeConnection(){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }

    public void closeStatement(){
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
            statement = null;
        }
    }

    public void insert(String inserting){
        if(conn == null || statement == null){
            System.out.println("没有建立数据库连接！");
        }
        try{
            statement.executeUpdate(inserting);
            System.out.println("插入成功！");
        }catch(Exception ex){
            System.err.println(ex);
            System.out.println("数据插入错误！");
        }
    }

    public void delete(String deleting){
        if(conn == null || statement == null){
            System.out.println("没有建立数据库连接！");
        }
        try{
            statement.executeUpdate(deleting);
            System.out.println("删除成功！");
        }catch(Exception ex){
            System.out.println("数据删除错误！");
        }
    }

    public void update(String updating){
        if(conn == null || statement == null){
            System.out.println("没有建立数据库连接！");
        }
        try{
            statement.executeUpdate(updating);
            System.out.println("更新成功！");
        }catch(Exception ex){
            System.out.println("数据更新错误！");
        }
    }

    public Vector query(String selecting){
        if (conn==null || statement==null){
            System.out.println("没有建立数据库连接！");
            return null;
        }
        ResultSetMetaData meta; //元数据，即数据字典,通过它能够得到结果集有多少字段，
        int colNum;             //记录结果集的列数
        String colName[];       //记录每个字段的名称
        try{
            res = statement.executeQuery(selecting);
            meta = res.getMetaData();
            colNum = meta.getColumnCount();
            colName = new String[colNum];
            for (int i=0; i<colNum; i++){
                colName[i]=meta.getColumnLabel(i+1);
            }
            rows = new Vector();
            while(res.next()){                  //将返回的结果集存入rows二维数组中
                Vector oneRow = new Vector();
                for(int i=1; i<=colNum; i++){   //每一行各个单元格内容加入到oneRow数组中
                    oneRow.addElement
                            ((Object)res.getObject(i));
                }
                rows.addElement(oneRow);
            }
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("数据查询错误！");
        }
        return rows;
    }

    public  void closeResultSet() {
        if (res != null) {
            try {
                res.close();
                res=null;
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
    }

    public void close(){
        this.closeResultSet();
        this.closeStatement();
        this.closeConnection();
    }

    public void commit() throws SQLException{
        conn.commit();
    }

    public void rollback() throws SQLException{
        conn.rollback();
    }

    public void insert2(String inserting){
        if(conn == null || statement == null){
            System.out.println("没有建立数据库连接！");
        }
        try{
            statement.executeUpdate(inserting);
        }catch(Exception ex){
        }
    }

    public void update2(String updating){
        if(conn == null || statement == null){
            System.out.println("没有建立数据库连接！");
        }
        try{
            statement.executeUpdate(updating);
        }catch(Exception ex){
            System.out.println("数据修改错误！");
        }
    }

    public void delete2(String deleting){
        if(conn == null || statement == null){
            System.out.println("没有建立数据库连接！");
        }
        try{
            statement.executeUpdate(deleting);
        }catch(Exception ex){
            System.out.println("数据修改错误！");
        }
    }
}




