package com.canteen.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * User: lixy
 * Mail: admin@TomyTime.com
 * Date: 14-2-16 上午4:15
 */
public class Derby {

/**
 * The Embedded mode is limited by that we can't run simultaneously
 * two programs (two JVM instances) using a same database (databaseName is the same).
 * <p>
 * But we can instead use the NetworkServer mode to avoid this case,
 * it is to say the "Client/Server" mode.
 * In this mode, you have to first start the NetworkServer by this command :
 * <pre>
 * java org.apache.derby.drda.NetworkServerControl start [-h hostIP -p portNumber]
 * </pre>
 * @author HAN
 *
 */
    private Connection con = null;
    private String filePath = null;
    /**
     * This express loading driver is not necessary for Java 6 and later, JDBC 4.0 and later.
     * Because it can be added automatically by <code>DriverManager</code> when connecting to a database.
     */
    public void loadDriver(){
        //load the driver
        try {
            filePath = Derby.class.getResource("").getPath().substring(1);
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            System.out.println("The embedded driver is successfully loaded");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * create and connect a database
     * @param databaseName
     * @param user
     * @param password
     * @return a connection to the URL
     */
    public Connection createDatabaseAndGetConnection(String databaseName, String user, String password){
        //create and connect the database
        Properties props = new Properties();
        props.put("user",user);
        props.put("password",password);
        try {
            //create=true表示当数据库不存在时就创建它
            con = DriverManager.getConnection("jdbc:derby:" + databaseName + ";create=true", props);
            System.out.println("Connection is successfully established, it use an Embedded database");
        } catch ( SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return con;
    }

    /**
     * Shut down a specified database. But it doesn't matter that later we could also connect to another database.
     * @param databaseName
     */
    public void shutdownDatabase(String databaseName){
        try {
            DriverManager.getConnection("jdbc:derby:" + databaseName+";shutdown=true");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Database: "+databaseName+" shut down normally");
        }
    }

    /**
     * shut down all opened databases and close the Derby driver.
     * The effect is that after the execution of this method, we will not permitted to use Derby again in the rest of our program.
     * Or else, an exception of "can't find a suitable driver for [a database URL]" will be thrown.
     */
    public void shutdownAll(){
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("All databases shut down normally and Derby driver closed");
        }
    }

    /**
     * Just connect to a database desired by providing the appropriate parameters.
     * @param databaseName
     * @param user
     * @param password
     * @return
     */
    public Connection getConnection(String databaseName, String user, String password){
        try {
            con=DriverManager.getConnection("jdbc:derby:" + databaseName,user,password);
            System.out.println("Connection is sucessfully established, it use an Embedded database");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return con;
    }
}
