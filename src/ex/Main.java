package ex;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {

	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; // 드라이버 ID
	public static final String WEB_DRIVER_PATH = "D:\\DEV\\JAVA\\SOURCE\\Selenium\\EXSelenium\\so\\chromedriver.exe"; // 드라이버
																														// 경로

	public static void main(String[] args) {

		System.out.println(System.getProperty("user.dir"));

		Path path = Paths.get(System.getProperty("user.dir"), "so/chromedriver.exe");

		// webDriver 경로 성정
		 System.setProperty("webdriver.chrome.driver",path.toString());

		// webDriver 경로 성정
//		try {
//			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		System.out.println(path.toString());

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");// 전체화면
		options.addArguments("--disable-popup-blocking");// 팝업무시
		options.addArguments("--disable-default-apps");// 기본앱 사용안함

		// webdriver 객체 생성
	    ChromeDriver driver=new ChromeDriver(options);
	    //driver.executeScript("window.open('https://naver.com/', '_blank');");
	    driver.executeScript("window.open('about:blank', '_blank');");
	    List<String> tabs = new ArrayList<>(driver.getWindowHandles());
	    
	    driver.switchTo().window(tabs.get(0));
	    driver.get("https://naver.com");
	    
	}

}
