package pageobjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Utilities.ExcelUtils;
import Utilities.Takescreenshot;

public class UpcomingBikesPage extends BasePage {

	public UpcomingBikesPage(WebDriver driver) throws IOException {
		super(driver);
	}
	
	//Finding elements
	@FindBy(xpath="//div[@class='row qlc']//div[@class='col-lg-2']")
	public WebElement ZigWheelLogo;
	
	@FindBy(xpath="//select[@id='makeId']")
	public WebElement Manufacturer_dropdown;
	
	@FindBy(xpath="//ul[@id='modelList']/li[contains(@class,'modelItem')]")
	public List<WebElement> Vehicle_Card;
	
	@FindBy(xpath="//div[@class='p-15 pt-10 mke-ryt rel']/a/strong")
	public List<WebElement> Bike_Names;
	
	@FindBy(xpath="//div[@class='p-15 pt-10 mke-ryt rel']/div[1]")
	public List<WebElement>Bike_Prices;
	
	@FindBy(xpath="//div[@class='p-15 pt-10 mke-ryt rel']/div[2]")
	public List<WebElement>Bike_Launchdate;
	
	@FindBy(xpath="//h1[@class='mt-0 pt-2 pull-left zm-cmn-colorBlack']")
	public WebElement filterheadingtext;
	
	@FindBy(xpath="//span[@class='zw-cmn-loadMore']")
	public WebElement viewmoreBtn;
	
	String xfile=System.getProperty("user.dir")+"\\TestData\\zig.xlsx";
	
	//Action Methods
	
	//Navigating back to homepage
	public void returntohomepage() {
		ZigWheelLogo.click();
	}
	
	
	//filtering the manufacturer dropdown by selecting "Honda" from the dropdown
	public void filtermanufacturer() throws InterruptedException, IOException {
		Select s=new Select(Manufacturer_dropdown);
		s.selectByVisibleText(ExcelUtils.getCellData(xfile,"Sheet1", 0, 1));
		highlightElement(Manufacturer_dropdown);
		Thread.sleep(2000);
		highlightElement(filterheadingtext);
		Assert.assertEquals(filterheadingtext.getText(),"Honda Upcoming Bikes in India");
		
	}
	
	//Scrolling down in the webpage and clicking on the viewmoreBtn
	public void clickonviewmoreBtn() throws InterruptedException, IOException {
		Takescreenshot ts=new Takescreenshot(driver);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1500);","");
		highlightElement(viewmoreBtn);
		js.executeScript("arguments[0].click();",viewmoreBtn);
		Thread.sleep(2000);
		ts.ScreenShot("UpcomingModelsforHonda.png");
	}
	
	
	//Sorting out the bike models that are less than 4Lakhs
	public void displaybikedetails() throws IOException {
		int j=3;
		for(int i=0;i<Bike_Names.size();i++) {
			Double prices=Double.parseDouble(Vehicle_Card.get(i).getAttribute("data-price"));
			
			if(prices<400000) {
				System.out.println(Bike_Names.get(i).getText());
				ExcelUtils.setCellData(xfile,"Sheet1", j, 0,Bike_Names.get(i).getText());
				System.out.println(Bike_Prices.get(i).getText());
				ExcelUtils.setCellData1(xfile,"Sheet1", j, 1,Bike_Prices.get(i).getText());
				System.out.println(Bike_Launchdate.get(i).getText());
				ExcelUtils.setCellData1(xfile,"Sheet1", j, 2,Bike_Launchdate.get(i).getText());
				System.out.println("-----------------------------------");
				System.out.println("-----------------------------------");
				j++;
			}else {
				continue;
			}
			
		}
		
	}

}
