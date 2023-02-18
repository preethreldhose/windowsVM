package e2eTestingPWC_AID.reference;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
 
public class selectMySQL {
  public static void main(String[] args) {
    Connection connection = null;
    Statement insertStmt = null;
    Statement selectStmt = null;
    try
    {
      Class.forName("com.mysql.jdbc.Driver");
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");
       
      /*
      insertStmt = connection.createStatement();
      insertStmt.execute("INSERT INTO getFilesForTestID (jsonName,jsonLink) VALUES ('1','Lokesh')");
      insertStmt.execute("INSERT INTO getFilesForTestID (jsonName,jsonLink) VALUES ('2','Kumar')");*/
       
      selectStmt = connection.createStatement();
      ResultSet rs = selectStmt.executeQuery("SELECT jsonName, jsonLink FROM getFilesForTestID");
      while(rs.next())
      {
    	  System.out.println(rs.getString(1) + " " + rs.getString(2));
        //System.out.println(rs.getString(1));  //First Column
        //System.out.println(rs.getString(2));  //Second Column
        //System.out.println(rs.getString(3));  //Third Column
        //System.out.println(rs.getString(4));  //Fourth Column
      }
    } 
    catch (Exception e) {
      e.printStackTrace();
    }finally {
      try {
        selectStmt.close();
        //insertStmt.close();
        connection.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
