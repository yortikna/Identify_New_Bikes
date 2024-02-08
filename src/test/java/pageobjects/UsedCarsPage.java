package pageobjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import Utilities.ExcelUtils;
import Utilities.Takescreenshot;

public class UsedCarsPage extends BasePage {

	public UsedCarsPage(WebDriver driver) throws IOException {
		super(driver);
	}
	//Locating elements
	
	@FindBy(xpath="//div[@class='gsc_thin_scroll']/ul/li/span/input")
	public List<WebElement> checkboxes;
	
	@FindBy(xpath="//div[@class='gsc_thin_scroll']/ul/li/label")
	public List<WebElement>popular_models;
	
	@FindBy(xpath="//div[@class='row qlc']//div[@class='col-lg-2']")
	public WebElement ZigWheelLogo;
	
	@FindBy(xpath="//div[@class='zm-cmn-colorBlack ml-30 mob-nonclick div-h3 mt-20 mb-10' and text()='Popular Models']")
	public WebElement PopularModels;
	
	String xfile=System.getProperty("user.dir")+"\\TestData\\zig.xlsx";
	//Action Methods
	//WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(20));
	
	//Navigate to homepage
	public void returntohomepage() {
		ZigWheelLogo.click();
	}
	
	
	//Displaying all the popular models in the console
	public void displaypopularmodels() throws InterruptedException, IOException {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		Takescreenshot ts=new Takescreenshot(driver);
		for(int i=0;i<popular_models.size();i++) {
			js.executeScript("arguments[0].click();",checkboxes.get(i));
			Thread.sleep(2000);
			System.out.println(popular_models.get(i).getText());
			ExcelUtils.setCellData(xfile,"Sheet2", i+1, 0,popular_models.get(i).getText());
		}
		js.executeScript("arguments[0].scrollIntoView();",PopularModels);
		Thread.sleep(2000);
		ts.ScreenShot("Popular_Models.png");
		System.out.println();
	}
}
