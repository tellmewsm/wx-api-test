package com.wx.utils;


import com.wx.entity.TestCase;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wuxi
 * @date 2018年6月27日
 */
public class DbUtils {

	public static Connection getConn() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:13306/wx_springboot?useSSL=false&characterEncoding=utf8";
		String username = "root";
		String password = "root";
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static List<TestCase> casesAll(String sql, Connection conn) {

		List<TestCase> casesAll = new ArrayList<>();
		Statement pstmt;
		try {
			pstmt = conn.createStatement();
			ResultSet rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				TestCase cases = new TestCase();
                cases.setHeader(rs.getString("header"));
                cases.setParams(rs.getString("params"));
                cases.setUrl(rs.getString("url"));
                cases.setCorrelation(rs.getString("correlation"));
                cases.setCheck(rs.getString("check"));
                cases.setResult(rs.getString("result"));
                cases.setRun(rs.getBoolean("run"));
                cases.setType(rs.getString("type"));
				casesAll.add(cases);
			}
			pstmt.close();
			conn.close();
			return casesAll;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Test
	public void startTest(){

		String sql = "select * from TestCase";

		List<TestCase> cases = DbUtils.casesAll(sql, DbUtils.getConn());

		System.out.println(cases);

	}
}
