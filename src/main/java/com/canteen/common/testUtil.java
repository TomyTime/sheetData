package com.canteen.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * User: lixy
 * Mail: admin@TomyTime.com
 * Date: 14-2-16 上午4:23
 */
public class testUtil {
    public static void main(String[] args) {
//        String driver = "org.apache.derby.jdbc.EmbeddedDriver";//在derby.jar里面
        String dbName="canteenAccountDB";
//        String path = testUtil.class.getResource("").getPath().substring(1);
        String dbURL = "jdbc:derby:" + dbName + ";create=true";//create=true表示当数据库不存在时就创建它
        System.out.println(dbURL);
        try {
            Derby db = new Derby();
            db.loadDriver();
//            Connection conn = DriverManager.getConnection(dbURL);//启动嵌入式数据库
            Connection conn = db.getConnection(dbName, "", "");
            Statement st = conn.createStatement();
           //st.execute("delete from trade");//创建foo表
//            StringBuffer sb = new StringBuffer();
            /*sb.append("CREATE TABLE capacity")
                    .append("(")
                    .append("id varchar(32) NOT NULL PRIMARY KEY ,")
                    .append("amount varchar(8) NOT NULL, " )
                    .append("subtotal varchar(16) NOT NULL " )
                    .append(")");*/
            //System.out.println("sql = " + sb.toString());
            //st.execute(sb.toString());
           // st.executeUpdate("insert into t_user(id) values ('1')");//插入一条数据
            //ResultSet rs = st.executeQuery("select * from member");//读取刚插入的数据
            //st.executeUpdate("delete from goods");
            /*String sqlstr = "insert into GOODS(ID,NAME,MODEL,TYPE)" +
                    "values('8798CE1EE1C74A4493C0D1FBA8CD919D','大米','25Kg.袋','0');insert into GOODS(ID,NAME,MODEL,TYPE)" +
                    "values('FB8AB016DFC54D928C4BA496A6FF3D35','丹富粉','25Kg.袋','0');insert into GOODS(ID,NAME,MODEL,TYPE)" +
                    "values('799EEF66B43640F0A20B102D807998AD','食醋','25Kg.桶','0');insert into GOODS(ID,NAME,MODEL,TYPE)" +
                    "values('3F26910496F3458B847F88411B44FA20','特一粉','25Kg.袋','0');insert into GOODS(ID,NAME,MODEL,TYPE)" +
                    "values('47823DFD72D2462082E4038413341748','调和油','20L.箱','0');insert into GOODS(ID,NAME,MODEL,TYPE)" +
                    "values('EA91020D9685462BAF164FDE1447230D','五得利','25Kg.袋','0');";
            String[] sqlArray = sqlstr.split(";");
            for(int i = 0; i < sqlArray.length; i++){
                //st.executeUpdate(sqlArray[i]);
            }*/
            /*ResultSet rs = st.executeQuery("select p.id, p.price, p.subtotal, " +
                    "p.amount, p.daytime, p.username from" +
                            " (select row_number() over() as rownum,pi.*" +
                            " from purchase pi where pi.id='p' order by pi.daytime desc) as p where p.rownum <= 1");//读取刚插入的数据
            */ResultSet rs = st.executeQuery("select t.id, g.name||'_'||g.model as gid," +
                    "u.name as uid, t.price, t.subtotal, t.amount, t.daytime, t.logtime from trade t, " +
                    "goods g, member u where t.gid = g.id and t.uid = u.id order by t.logtime desc, t.daytime desc");
            while(rs.next()){
                String id = rs.getString(1);
                System.out.println("id = " + id);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
