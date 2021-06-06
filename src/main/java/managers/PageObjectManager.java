package managers;

import org.openqa.selenium.WebDriver;

import pageobjects.CarTaxCheckHomePage;
import pageobjects.VehicleDetailsPage;

public class PageObjectManager {

	private WebDriver webDriver = null;
	private CarTaxCheckHomePage carTaxCheckHomePage = null;
	private VehicleDetailsPage vehicleDetailsPage = null;
	
	public PageObjectManager(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public CarTaxCheckHomePage getCarTaxCheckHomePage() {
		return (carTaxCheckHomePage == null) ? carTaxCheckHomePage = new CarTaxCheckHomePage(webDriver) : carTaxCheckHomePage;
	}
	
	public VehicleDetailsPage getVehicleDetailsPage() {
		return (vehicleDetailsPage == null) ? vehicleDetailsPage = new VehicleDetailsPage(webDriver) : vehicleDetailsPage;
	}
}
