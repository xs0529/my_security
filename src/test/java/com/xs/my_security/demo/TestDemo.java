package com.xs.my_security.demo;

/**
 * <p>
 *
 * </p>
 *
 * @author XieShuang
 * @version v1.0
 * @since 2018-12-03
 */
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TestDemo {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://47.107.79.3:3306/caict_test?useUnicode=true&characterEncoding=utf8";
            String user = "root";
            String pass = "root";
            conn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void main(String[] args) throws SQLException {
        //获取数据库连接
        Connection conn = getConnection();
        String sql = "select * from ";
        //获取所有表名
        List<String> tableNames = getTableNames(conn);

        for (String tableName : tableNames) {
            getTableDetails(conn,tableName);
        }
        conn.close();
    }


    /**
     * 获取数据库下的所有表名
     */
    public static List<String> getTableNames(Connection conn) {
        List<String> tableNames = new ArrayList<>();
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData db = conn.getMetaData();
            //从元数据中获取到所有的表名
            rs = db.getTables(null, null, null, new String[] { "TABLE" });
            while(rs.next()) {
                tableNames.add(rs.getString(3));
            }
        } catch (SQLException e) {
            log.error("getTableNames failure", e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                log.error("close ResultSet failure", e);
            }
        }
        return tableNames;
    }

    public static void getTableDetails(Connection conn,String tableName1){
        String sql = "select * from " +tableName1;
        PreparedStatement stmt;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);
            ResultSetMetaData data = rs.getMetaData();
            for (int i = 1; i <= data.getColumnCount(); i++) {
// 获得所有列的数目及实际列数
                int columnCount = data.getColumnCount();
// 获得指定列的列名
                String columnName = data.getColumnName(i);
// 获得指定列的列值
                int columnType = data.getColumnType(i);
// 获得指定列的数据类型名
                String columnTypeName = data.getColumnTypeName(i);
// 所在的Catalog名字
                String catalogName = data.getCatalogName(i);
// 对应数据类型的类
                String columnClassName = data.getColumnClassName(i);
// 在数据库中类型的最大字符个数
                int columnDisplaySize = data.getColumnDisplaySize(i);
// 默认的列的标题
                String columnLabel = data.getColumnLabel(i);
// 获得列的模式
                String schemaName = data.getSchemaName(i);
// 某列类型的精确度(类型的长度)
                int precision = data.getPrecision(i);
// 小数点后的位数
                int scale = data.getScale(i);
// 获取某列对应的表名
                String tableName = data.getTableName(i);
// 是否自动递增
                boolean isAutoInctement = data.isAutoIncrement(i);
// 在数据库中是否为货币型
                boolean isCurrency = data.isCurrency(i);
// 是否为空
                int isNullable = data.isNullable(i);
// 是否为只读
                boolean isReadOnly = data.isReadOnly(i);
// 能否出现在where中
                boolean isSearchable = data.isSearchable(i);
                System.out.println(columnCount);
                System.out.println("获得列" + i + "的字段名称:" + columnName);
                System.out.println("获得列" + i + "的类型,返回SqlType中的编号:"+ columnType);
                System.out.println("获得列" + i + "的数据类型名:" + columnTypeName);
                System.out.println("获得列" + i + "所在的Catalog名字:"+ catalogName);
                System.out.println("获得列" + i + "对应数据类型的类:"+ columnClassName);
                System.out.println("获得列" + i + "在数据库中类型的最大字符个数:"+ columnDisplaySize);
                System.out.println("获得列" + i + "的默认的列的标题:" + columnLabel);
                System.out.println("获得列" + i + "的模式:" + schemaName);
                System.out.println("获得列" + i + "类型的精确度(类型的长度):" + precision);
                System.out.println("获得列" + i + "小数点后的位数:" + scale);
                System.out.println("获得列" + i + "对应的表名:" + tableName);
                System.out.println("获得列" + i + "是否自动递增:" + isAutoInctement);
                System.out.println("获得列" + i + "在数据库中是否为货币型:" + isCurrency);
                System.out.println("获得列" + i + "是否为空:" + isNullable);
                System.out.println("获得列" + i + "是否为只读:" + isReadOnly);
                System.out.println("获得列" + i + "能否出现在where中:"+ isSearchable);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
            } catch (SQLException e) {
                log.error("close ResultSet failure", e);
            }
        }
    }
}