package ex;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {

	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; // ����̹� ID
	public static final String WEB_DRIVER_PATH = "D:\\DEV\\JAVA\\SOURCE\\Selenium\\EXSelenium\\so\\chromedriver.exe"; // ����̹�
																														// ���

	public static void main(String[] args) {

		System.out.println(System.getProperty("user.dir"));

		Path path = Paths.get(System.getProperty("user.dir"), "so/chromedriver.exe");

		// webDriver ��� ����
		 System.setProperty("webdriver.chrome.driver",path.toString());

		// webDriver ��� ����
//		try {
//			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		System.out.println(path.toString());

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");// ��üȭ��
		options.addArguments("--disable-popup-blocking");// �˾�����
		options.addArguments("--disable-default-apps");// �⺻�� ������

		// webdriver ��ü ����
	    ChromeDriver driver=new ChromeDriver(options);
	    //driver.executeScript("window.open('https://naver.com/', '_blank');");
	    driver.executeScript("window.open('about:blank', '_blank');");
	    List<String> tabs = new ArrayList<>(driver.getWindowHandles());
	    
	    driver.switchTo().window(tabs.get(0));
	    driver.get("https://naver.com");
	    
	}

}
