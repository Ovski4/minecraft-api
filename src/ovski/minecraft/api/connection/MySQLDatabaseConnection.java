package ovski.minecraft.api.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * StudConnection
 * Connect to the MySQL database and manage the requests
 * @author Caligone
 * @version 0.4
 */
public class MySQLDatabaseConnection
{
      /*Variables*/
      private static Connection connection = null;

      /**
       * getStudConnection method permit to get a StudConnection object
       * @param String url : Contains the url to the database
       * @param int port : Contains the port to the database
       * @param String url : Contains the database name
       * @param String user : Contains the name of the login
       * @param String password : Contains the password
       * @return StudConnection object
       */
      public static Connection getConnection()
      {
              
              if(MySQLDatabaseConnection.connection != null)
                  return MySQLDatabaseConnection.connection;
              try
              {
                  String url = YamlDatabaseManager.getHost();
                  String database = YamlDatabaseManager.getDatabaseName();
                  String user = YamlDatabaseManager.getUser();
                  String password = YamlDatabaseManager.getPassword();
                  int port = YamlDatabaseManager.getPort();
                  
                  Class.forName("com.mysql.jdbc.Driver");
                  System.out.println("Connection URL : jdbc:mysql://"+url+":"+port+"/"+database);
                  try
                  {
                    System.out.println("Connection a la base de donnée studmine");
                    MySQLDatabaseConnection.connection = DriverManager.getConnection("jdbc:mysql://"+url+":"+port+"/"+database, user, password);
                  }
                  catch (SQLException e)
                  {
                    e.printStackTrace();
                    MySQLDatabaseConnection.connection = null;
                  }
                  return MySQLDatabaseConnection.connection;
              }
              catch (ClassNotFoundException e)
              {
                e.printStackTrace();
                return null;
              }
              
      }

      /**
       * sendData method permit to update, insert or delete data to the database
       * @param String sql contains the SQL Request
       * @return the row count or -1 if error appends
       */
      public static int sendData(String sql)
      {
              try
              {
                      return MySQLDatabaseConnection.getConnection().prepareStatement(sql).executeUpdate(sql);
              }
              catch (SQLException e)
              {
                      e.printStackTrace();
                      return -1;
              }
      }

      /**
       * getData method permit get data from the database
       * @param String sql contains the SQL Request
       * @return ResultSet contains the data requested or null if error appends
       */
      public static ResultSet getData(String sql)
      {
              try
              {
                      return MySQLDatabaseConnection.getConnection().prepareStatement(sql).executeQuery(sql);
              }
              catch (SQLException e)
              {
                      e.printStackTrace();
                      return null;
              }
      }
}