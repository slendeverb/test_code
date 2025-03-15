package dao;

import bean.Record;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

// 用于访问record表
public class RecordDAO {
    protected DatabaseConnection databaseConnection=new DatabaseConnection();
    protected Connection con=null;
    protected PreparedStatement pst=null;
    protected ResultSet rs=null;
    protected String sql=null;
    protected String tableName = "record";

    // 查询历史记录
    public ResultSet getRecords(String username){
        con=databaseConnection.getConnection();
        sql="select * from "+tableName+" where username=? order by time desc";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    // 添加历史记录
    public void addRecord(Record r){
        con=databaseConnection.getConnection();
        sql="insert into "+tableName+" values(?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, UUID.randomUUID().toString()); // id使用UUID类生成
            pst.setString(2, r.getUsername());
            pst.setInt(3, r.getLp());
            pst.setInt(4, r.getMoney());
            pst.setTimestamp(5, r.getDate());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            databaseConnection.closeConnection(con,pst,rs);
        }
    }

    // 删除历史记录
    public void removeRecord(String username){
        con=databaseConnection.getConnection();
        sql="delete from "+tableName+" where username=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.execute();
        }catch(SQLException e) {
            e.printStackTrace();
        }finally{
            databaseConnection.closeConnection(con,pst,rs);
        }
    }
}
