package com.anno.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.activation.FileDataSource;
import javax.management.Query;

public class Test {
	public static void main(String[] args) {
		Filter f1 = new Filter();
		f1.setId(10);

		Filter f2 = new Filter();
		f2.setUserNmae("lynn");
		f2.setAge(20);

		Filter f3 = new Filter();
		f3.setEmail("liu@sina.com,zg@163.com,87777@qq.com");

		Filter2 f4 = new Filter2();
		f4.setName("计算机中心");
		f4.setAmount(10);
		String sql1 = query(f1);
		String sql2 = query(f2);
		String sql3 = query(f3);

		System.out.println(sql1);
		System.out.println(sql2);
		System.out.println(sql3);
		System.out.println(query(f4));

	}

	private static String query(Object f1) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		// 获取class
		Class class1 = f1.getClass();
		// 获取table名字
		boolean isExists = class1.isAnnotationPresent(Table.class);
		if (!isExists) {
			return null;
		}
		Table t = (Table) class1.getAnnotation(Table.class);
		String tableName = t.value();
		sb.append("SELECT *FROM ").append(tableName).append(" WHERE 1=1 ");
		// 遍历所有字段
		Field[] fArray = class1.getDeclaredFields();
		for (Field field : fArray) {
			boolean isFexists = field.isAnnotationPresent(Column.class);
			if (!isExists) {
				continue;
			}
			Column column = field.getAnnotation(Column.class);
			String columnName = column.value();
			String fieldName = field.getName();
			String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			Object fieldValue = null;
			try {
				Method getMethod = class1.getMethod(getMethodName);
				fieldValue = getMethod.invoke(f1);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			// 拼装sql
			if (fieldValue == null || fieldValue instanceof Integer && (Integer) fieldValue == 0) {
				continue;
			}
			sb.append(" and ").append(fieldName);
			if (fieldValue instanceof String) {
				if (((String) fieldValue).contains(",")) {
					String[] values = ((String) fieldValue).split(",");
					sb.append(" in( ");
					for (String v : values) {
						sb.append("'").append(v).append("'").append(",");
					}
					sb.deleteCharAt(sb.length() - 1);
					sb.append(")");
				} else {
					sb.append("=").append("'").append(fieldValue).append("'");
				}
			} else if (fieldValue instanceof Integer) {
				sb.append("=").append(fieldValue);
			}
		}
		return sb.toString();
	}
}
