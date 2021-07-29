package baseTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;




public class BaseClass {
	
	public Properties LoadProperties() {
		
		
		
		String propertyFilePath = System.getProperty("user.dir")+
		        ".//src//test//java//Configuration//config.properties";
	
		
		try {
	
			Properties prop = new Properties();
	//		FileInputStream fis = new FileInputStream(".//src//test//java//Configuration//config.properties");
			FileInputStream fis = new FileInputStream(propertyFilePath);
		//	prop.load(fis);
			   prop.load(new FileInputStream(propertyFilePath));
		return prop ;
		}catch(Exception e) {
			System.out.println("File not founf for Config.propeties");
			return null;
		}
		
	}

}
