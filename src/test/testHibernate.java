package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

import org.hibernate.Criteria;
import org.hibernate.LobHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.jfok.cfcmms.hibernate.system.attachment._AttachmentFileType;
import com.jfok.cfcmms.hibernate.system.attachment._AttachmentReduceMode;
import com.jfok.cfcmms.hibernate.system.authority._Role;
import com.jfok.cfcmms.hibernate.system.setting._Systeminfo;
import com.jfok.cfcmms.hibernate.system.setting._Systemset;
import com.jfok.cfcmms.hibernate.system.setting._User;

public class testHibernate {

	@Test
	public void test() throws IOException {

	Configuration config = new Configuration().configure("hibernate.cfg.xml");
	SessionFactory sessionFactory = config.buildSessionFactory();
	Session session = sessionFactory.openSession();
	session.beginTransaction();
	try {
		Criteria criteria = session.createCriteria(_User.class);

		for (Object mode : criteria.list().toArray()) {
		  _User user = (_User) mode;
		  System.out.println(JSON.toJSONString(user.getTf_userName()));
		  
		  for (_Role role : user.getTf_Roles()){
		    System.out.println(role.getTf_roleName());
		  }


		}



		session.getTransaction().commit();
	} finally {
		session.close();
	}
	}

}
