import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CricketScore {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\t-otegbayo\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.cricbuzz.com/live-cricket-scorecard/36101/2nd-odi-sri-lanka-tour-of-bangladesh-2021");
		driver.manage().window().maximize();
		
		WebElement table = driver.findElement(By.cssSelector("div[class='cb-col cb-col-100 cb-ltst-wgt-hdr']"));
		int rowCount = table.findElements(By.cssSelector("div[class='cb-col cb-col-100 cb-scrd-itms'] div:nth-child(3)")).size();
		int sum = 0;
		for(int i = 0; i <rowCount-2; i++)
		{
			String value = table.findElements(By.cssSelector("div[class='cb-col cb-col-100 cb-scrd-itms'] div:nth-child(3)")).get(i).getText();
			int valueInteger = Integer.parseInt(value);
			sum = sum + valueInteger;
		}
		String extras = driver.findElement(By.xpath("//div[text() = 'Extras']/following-sibling::div")).getText();
		int extraValue = Integer.parseInt(extras);
		int sumValue = sum + extraValue;
		
		String total = driver.findElement(By.xpath("//div[text() = 'Total']/following-sibling::div")).getText();
		int totalValue = Integer.parseInt(total);
		if(sumValue == totalValue)
		{
			System.out.println("The total score is correct");
		}
		else
		{
			System.out.println("The total score is incorrect");
		}
		
		File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scr, new File("C:\\Users\\t-otegbayo\\Desktop\\Screenshot_Pass.png"));

	}

}
