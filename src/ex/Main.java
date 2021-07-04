package ex;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
		System.setProperty("webdriver.chrome.driver", path.toString());

		// webDriver 경로 성정
//		try {
//			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

//		System.out.println(path.toString());

		// WebDriver 옵션 설정
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized"); // 전체화면으로 실행
		options.addArguments("--disable-popup-blocking"); // 팝업 무시
		options.addArguments("--disable-default-apps"); // 기본앱 사용안함
		// options.setHeadless(true);
		// WebDriver 객체 생성
		ChromeDriver driver = new ChromeDriver(options);
		// 빈 탭 생성
		// driver.executeScript("window.open('about:blank','_blank');");
		List<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		// 첫번째 탭으로 전환
		driver.switchTo().window(tabs.get(0));
		driver.get("https://www.naver.com");
		driver.get("https://unsplash.com/t/nature");

		File downloadsFolder = new File("downloads");

		if (downloadsFolder.exists() == false) {
			downloadsFolder.mkdir();
		}

		List<String> tabs1 = new ArrayList<>(driver.getWindowHandles());

		driver.switchTo().window(tabs1.get(0));
		driver.get("https://unsplash.com/t/nature");
		List<WebElement> imgElements = driver.findElements(By.cssSelector(
				"[data-test=\"photo-grid-multi-col-figure\"] img[data-test=\"photo-grid-multi-col-img\"]"));

		System.out.println(imgElements.size());
		for (WebElement imgElement : imgElements) {
			String src = imgElement.getAttribute("src");
			System.out.println(src);

			BufferedImage saveImage = null;

			try {
				saveImage = ImageIO.read(new URL(src));
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (saveImage != null) {
				try {

					String fileName = src.split("/")[3];
					fileName = fileName.split("\\?")[0];
					ImageIO.write(saveImage, "jpg", new File("downloads/" + fileName + ".jpg"));
					System.out.println(saveImage);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			System.out.println(src);
		}

	}

}