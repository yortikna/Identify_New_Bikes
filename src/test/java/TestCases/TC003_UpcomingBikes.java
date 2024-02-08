package TestCases;

import java.io.IOException;

import org.testng.annotations.*;

import pageobjects.UpcomingBikesPage;
//@Listeners(Utilities.ExtendReportManager.class)
public class TC003_UpcomingBikes extends TC002_UsedCars {
	public UpcomingBikesPage up;
	@Test(priority=6,groups= {"Master","Regression"})
	void test7() throws IOException, InterruptedException {
		up=new UpcomingBikesPage(driver);
		up.filtermanufacturer();
		log.info("filtering the bikes by the brand");
	}
	@Test(priority=7,dependsOnMethods= {"test7"},groups= {"Master","Regression"})
	void test8() throws IOException, InterruptedException {
		up=new UpcomingBikesPage(driver);
		up.clickonviewmoreBtn();
		up.displaybikedetails();
		log.info("Displaying the bikes that are under 4 lakhs");
	}
	@Test(priority=8,groups= {"Master","Regression"})
	void test9() throws IOException {
		up=new UpcomingBikesPage(driver);
		up.returntohomepage();
		log.info("Navigating back to homepage");
	}

}
