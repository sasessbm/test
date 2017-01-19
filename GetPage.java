package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GetPage {

	public static void main(String[] args) throws IOException {

            System.setProperty("http.proxyHost", "proxy.nagaokaut.ac.jp");
		        //System.setProperty("http.proxyPort", "8080");
		//		
		//		Document document = Jsoup.connect("http://www.google.co.jp").get();
		//        System.out.println(document.html());

		Connection con = null;
		try {
			// JDBCドライバのロード - JDBC4.0（JDK1.6）以降は不要
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// MySQLに接続
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb?useSSL=false", "root", "databasetest86");
			System.out.println("MySQLに接続できました。");
			
			Statement stm = con.createStatement();
			//System.out.println("test");
            //String sql = "SELECT * FROM student";
			
			/* データ挿入　*/
			//String sql = "insert into student values(1, 'taro', 1), (2, 'jiro', 2);";
			//String sql = "alter table student add old int";
			
			/* テーブル名変更　*/
			//String sql = "ALTER TABLE student2 RENAME student1;";
			
			/* データ更新　*/
			String sql = "update student1 set old = 20 where name = 'taro';";
			stm.executeUpdate(sql);
			System.out.println("test");
			//stm.close();
		    //con.close();
//			int result = stm.executeUpdate(sql);
//	        System.out.println("更新件数は" + result + "です。");
//            ResultSet rs = stm.executeQuery(sql);

//            while(rs.next()){
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                System.out.println("取得結果 -> " + id + ":" + name);
//            }
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			System.out.println("JDBCドライバのロードに失敗しました。");
		} catch (SQLException e) {
			System.out.println("MySQLに接続できませんでした。" + e);
		} finally {
			System.out.println("test");
			if (con != null) {
				try {
					con.close();
					System.out.println("MySQLのクローズに成功しました。");
				} catch (SQLException e) {
					System.out.println("MySQLのクローズに失敗しました。");
				}
			}
		}


	}

}
