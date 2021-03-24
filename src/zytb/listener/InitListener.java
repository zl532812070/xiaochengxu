package zytb.listener;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import zytb.util.Util;

public class InitListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		String path = this.getClass().getResource("/score.properties")
				.getPath();
		Properties pro = new Properties();
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(path);
			pro.load(fileInputStream);
			Integer one_li = Integer.valueOf(pro.getProperty("one_score_li"));
			Integer one_wen = Integer.valueOf(pro.getProperty("one_score_wen"));
			Integer two_li = Integer.valueOf(pro.getProperty("two_score_li"));
			Integer two_wen = Integer.valueOf(pro.getProperty("two_score_wen"));
			Util.setScore(one_li, one_wen, two_li, two_wen);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fileInputStream = null;
			}
		}
	}

}
