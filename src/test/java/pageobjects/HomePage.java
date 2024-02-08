package pageobjects;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.ExcelUtils;
import Utilities.Takescreenshot;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) throws IOException {
		super(driver);
	}
	
	//Locating Elements
	
	@FindBy(xpath="//div[@class='row qlc']//div[@class='col-lg-2']")
	public WebElement ZigWheelLogo;
	
	@FindBy(xpath="//div[@id='forum_login_title_lg']")
	public WebElement LoginBtn;
	
	@FindBy(xpath="//div[contains(@class,'googleSignIn')]")
	public WebElement GoogleSignIn;
	
	@FindBy(xpath="//input[@type='email']")
	public WebElement email_inputbox;
	
	@FindBy(xpath="//span[@class='VfPpkd-vQzf8d' and text()='Next']")
	public WebElement NextBtn;
	
	@FindBy(xpath="//div[@class='o6cuMc Jj6Lae']")
	public WebElement errormsg;
	
	@FindBy(xpath="//div[@class='col-lg-12 pl-0']/ul/li[7]")
	public WebElement UsedCars_Tab;
	
	@FindBy(xpath="//div[@class='h-dd-r']/ul/li/span")
	public List<WebElement> UsedCar_City;
	
	@FindBy(xpath="//div[@class='col-lg-12 pl-0']/ul/li[3]")
	public WebElement NewBikes_Tab;
	
	@FindBy(xpath="//ul[@class='h-d-nav fnt-16 ']/li[3]/ul/li[5]/span")
	public WebElement UpcomingBikes;
	
	@FindBy(xpath="div[@class='modal-body']/div[@id='alternate-login-close']")
	public WebElement loginpopup;
	
	public String PageTitle=driver.getTitle();
	
	public String  Homepage_windowid=driver.getWindowHandle();
	
	String xfile=System.getProperty("user.dir")+"\\TestData\\zig.xlsx";
	
	//Action methods
	WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(20));
	
	//Verifying the title of ZigWheels.com
	public void verify_title() {
		mywait.until(ExpectedConditions.visibilityOf(ZigWheelLogo));
		Assert.assertEquals(PageTitle,"New Cars & Bikes, Prices, News, Reviews, Buy & Sell Used Cars - ZigWheels.com","Page Title not verified");
	}
	
	//Handling the loginpopup
	public void handleloginpopup() {
		try {
			if(loginpopup.isDisplayed()) {
				loginpopup.click();
			}
		}catch(Exception e) {}
	}
	
	//Automating the login functionality and testing it with negetive inputs
	public  void Login() throws IOException, InterruptedException {
		Takescreenshot ts=new Takescreenshot(driver);
		highlightElement(LoginBtn);
		LoginBtn.click();
		ts.ScreenShot("ClickedOnLoginButton.png");
		highlightElement(GoogleSignIn);
		GoogleSignIn.click();
		Set<String> windowids=driver.getWindowHandles();
		for(String win:windowids) {
			if(!win.equalsIgnoreCase(Homepage_windowid)) {
		driver.switchTo().window(win);
			}
		}
		email_inputbox.sendKeys(p.getProperty("emailid"));
		ts.ScreenShot("GoogleSignInpopupWindow.png");
		NextBtn.click();
		Thread.sleep(2000);
		ts.ScreenShot("InvalidCredentials.png");
	}
	
	
	//Capturing the error message displayed on giving the negetive input value
	public void captureerrormessage() throws IOException {
		highlightElement(errormsg);
		System.out.println("Error Message---->"+errormsg.getText());
		ExcelUtils.setCellData(xfile,"Sheet3", 1, 0,errormsg.getText());
		
	}
	
	
	//Hovering on the Used Cars tab and clicking on Chennai
	public void UsedCarsCity() throws InterruptedException, IOException {
		Takescreenshot ts=new Takescreenshot(driver);
		Actions act=new Actions(driver);
		act.moveToElement(UsedCars_Tab).perform();
		for(WebElement e:UsedCar_City) {
			if(e.getText().equalsIgnoreCase(p.getProperty("city"))) {	
				Thread.sleep(2000);
				highlightElement(e);
				ts.ScreenShot("HoverOnUsedCarsClickOnChennai.png");
				e.click();
				break;
			}
		}
		
	}
	
	
	//Hovering on New Bikes and clicking on Upcoming bikes
	public void NewBikes() throws InterruptedException, IOException {
		Takescreenshot ts=new Takescreenshot(driver);
		Actions act=new Actions(driver);
		act.moveToElement(NewBikes_Tab).perform();;
		Thread.sleep(2000);
		highlightElement(UpcomingBikes);
		ts.ScreenShot("HoverOnNewBikesandclickonUpcomingBikes.png");
		UpcomingBikes.click();
	}

}
