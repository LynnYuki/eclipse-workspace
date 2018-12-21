package com.ann.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class PersonAnn {
	public static void main(String[] args) {
		try {
			//��ȡ�������ע��
			Class class1 = Class.forName("com.ann.test.Child");
			boolean isExist = class1.isAnnotationPresent(Description.class);
			if (isExist) {
				Description description = (Description) class1.getAnnotation(Description.class);
				System.out.println(description.value());
			}
			//��ȡ����ע��
			Method []methods = class1.getMethods();
			for (Method method : methods) {
				boolean isExist2 = method.isAnnotationPresent(Description.class);
				if (isExist2) {
					Description description = (Description)method.getAnnotation(Description.class);
					System.out.println(description.value());
				}
			}
			//����һ�ַ�����ȡ
			for (Method method : methods) {
				Annotation[] annotation = method.getAnnotations();
				for (Annotation a : annotation) {
					if (a instanceof Description) {
						Description d = (Description)a;
						System.out.println(d.value());
					}	
					
				}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
