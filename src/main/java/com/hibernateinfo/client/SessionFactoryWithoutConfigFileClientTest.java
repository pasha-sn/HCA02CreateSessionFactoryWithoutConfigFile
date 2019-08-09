package com.hibernateinfo.client;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.hibernateinfo.util.HibernateUitl;

/**
 * @author Pasha
 *
 */
public class SessionFactoryWithoutConfigFileClientTest {

	public static void main(String[] args) 
	{		
		//Session object implements autoCloseable and -after JDK 1.7- every object implements 
		//autoCloseable can be used as private resources in try catch block
		//you Don't need to close session in finally block
		try (Session session = HibernateUitl.getSessionFactory().openSession()) {			
			//nativeQuery in oracle database
			String sql = "SELECT version FROM V$INSTANCE";

			String result = (String) session.createNativeQuery(sql).getSingleResult();
			System.out.println("Oracle Database Version is:::");
			System.out.println(result);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

}
