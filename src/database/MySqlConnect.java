
package database;
import java.sql.*;

public class MySqlConnect {
    
    private String pass;
    Connection con = null;
    Statement stmt;
    ResultSet rs;
 
    public void Connect(){ 
    try
	{
            Class.forName("com.mysql.jdbc.Driver"); 
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reminderAppDB?autoReconnect=true&useSSL=false", "root","Racsdgr897!");
            stmt = con.createStatement();
	}
	catch(SQLException e1)
	{ 
            e1.printStackTrace();
        }
        catch(ClassNotFoundException e2){
            e2.printStackTrace();
        }
    }
    
    public Statement getStmt(){
        return stmt;
    }
    
    public boolean get_username(String username, String password){
        
        String sql = "select * from login where userName='" + username + "';";
        try{
            rs = stmt.executeQuery(sql);
            if(rs.next()){
            pass = rs.getString("password"); }
            if( !password.equals(pass)){
                return false;
            }
            else{
                return true;
            }
        }
        catch(SQLException e3){
            e3.printStackTrace();
        }
        return false;
    }
    
    public boolean register(String username, String password){
        
        String sql = "select * from login where userName='" + username + "';";
        try{
                rs = stmt.executeQuery(sql);
                if(!rs.next()){
                    String sql2 = "insert into login values ('"+username+"', '"+password+"')";
                    stmt.executeUpdate(sql2);
                    return true;
                }
                else{
                    return false;
                }
            }
            catch(SQLException e1){
                e1.printStackTrace();
            }
        return false;       
    }
    
    public boolean deleteReminder(String remID, String date, String time, String desc){
        
        String sql = "delete from Reminder where remID='" + remID +"';";
        try{
            stmt.executeUpdate(sql);
            return true;
        }
        catch(SQLException e1){
                e1.printStackTrace();
        }
        return false; 
    }
    
    public boolean updateReminder(String remID, String date, String time, String desc){
        
        String sql = "update Reminder set timeSet='" +time+ "', dateSet='" +date+ "', description='" +desc+ "' where RemId='" +remID+ "';";
        try{
            stmt.executeUpdate(sql);
            return true;
        }
        catch(SQLException e1){
                e1.printStackTrace();
        }
        return false; 
    }
    
    public boolean addReminder(String username, String time, String desc, String date){
        
        String sql =  "insert into Reminder values (NULL, '"+username+"', '"+time+"', '"+date+"', '"+desc+"')";
        try{
            stmt.executeUpdate(sql);
            return true;
        }
         catch(SQLException e1){
                e1.printStackTrace();
        }
        return false;    
    }
    
    public int getRowNumber(String user_name){
        
        String sql = "select count(*) as 'count' from Reminder where userName='"+user_name+"';";
        try{
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                int noOfRows = rs.getInt("count");
                return noOfRows;
            }
        }
        catch(SQLException e1){
                e1.printStackTrace();
        }
        return 0; 
    }
}
    

