package TestCases;

import java.io.IOException;

import org.testng.annotations.*;

import pageobjects.UsedCarsPage;

public class TC002_UsedCars extends TC001_HomePage {
	public UsedCarsPage up;
	@Test(priority=3,groups= {"Master","Regression"})
	void test5() throws IOException, InterruptedException {
		up=new UsedCarsPage(driver);
		up.displaypopularmodels();
		log.info("Displaying the popular models in the console");
	}

	
	@Test(priority=4,groups= {"Master","Regression"})
	void test6() throws IOException {
		up=new UsedCarsPage(driver);
		up.returntohomepage();
		log.info("Navigating back to homepage");
	}
}
