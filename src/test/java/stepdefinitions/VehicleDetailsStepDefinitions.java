package stepdefinitions;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import managers.CsvReader;
import managers.TestContext;
import managers.WebDriverManager;
import pageobjects.CarTaxCheckHomePage;
import pageobjects.VehicleDetailsPage;
import util.CommonUtility;

public class VehicleDetailsStepDefinitions {
	
	TestContext testContext;
	CarTaxCheckHomePage carTaxCheckHomePage;
	VehicleDetailsPage vehicleDetailsPage;

    List<String> inputVehicleRegNumbers;
    Map<String, CsvReader> listOfOuptVehicleDetails;
	
    public VehicleDetailsStepDefinitions(TestContext context){
    	testContext = context;
    	carTaxCheckHomePage = testContext.getPageObjectManager().getCarTaxCheckHomePage();
    	vehicleDetailsPage = testContext.getPageObjectManager().getVehicleDetailsPage();
    }
    
	@Given("^I have list of car registration numbers \"([^\"]*)\"$")
	public void iHaveListOfCarRegistrationNumbers(String filePath) throws Throwable {
        String fileContent = CommonUtility.readInputFileAsString(filePath);
        inputVehicleRegNumbers = CommonUtility.getDataOnRegx(fileContent);
	}

	@Given("^I have list of expected car details \"([^\"]*)\"$")
	public void iHaveListOfExpectedCarDetails(String outputFile) throws Throwable {
		 listOfOuptVehicleDetails = CommonUtility.getOutPutDataFromCSV(outputFile);
	}

	@When("^I check car details in the cartax check website$")
	public void iCheckCarDetailsInTheCartaxCheckWebsite() throws Throwable {
		carTaxCheckHomePage.openCarTaxWebSite();
	}

	@Then("^I verified actual car details with expected details$")
	public void iVerifiedActualCarDetailsWithExpectedDetails() throws Throwable {
		 compareActualWithExpectedDeatails(listOfOuptVehicleDetails);
	}

    private void compareActualWithExpectedDeatails(Map<String, CsvReader> map) throws Exception {

        for (String regNumbFromInputText : inputVehicleRegNumbers) {

        	CsvReader csvData = map.get(regNumbFromInputText);
            if (csvData == null) {
                System.out.println("No vehicle output data found for Vehicle Reg :  " + regNumbFromInputText);
            } else {
            	carTaxCheckHomePage.enterCarRegistrationNumber(regNumbFromInputText);
            	
                // Reading Vehicle details from OutPut Text
                String carRegNumbFromOutputText = csvData.getREGISTRATION();
                String carMake = csvData.getMAKE();
                String carModel = csvData.getMODEL();
                String carColor = csvData.getCOLOR();
                String carRegistrationYear = csvData.getYEAR();
                
                // Reading Vehicle details from web site
                String retrievedRegistrationNumberFromFullVehicleDetailsPage = vehicleDetailsPage.getVehicleRegistrationNumber();
                String retrieveMakeFromFullVehicleDetailsPage = vehicleDetailsPage.getCarMake();
                String retrieveModelFromFullVehicleDetailsPage = vehicleDetailsPage.getVehicleModel();
                String rertrieveColourFromFullVehicleDetailsPage = vehicleDetailsPage.getVehicleColour();
                String retrieveYearFromFullVehicleDetailsPage = vehicleDetailsPage.getVehicleYear();

                // Assert Registration Number in the full Vehicle Details Page
                Assert.assertEquals("Registration Number Verification", carRegNumbFromOutputText, retrievedRegistrationNumberFromFullVehicleDetailsPage);

                // Assert Car Make Value in the full Vehicle Details Page
                Assert.assertEquals("Make Verification", carMake, retrieveMakeFromFullVehicleDetailsPage);

                // Assert Car Model Value in the full Vehicle Details Page
                Assert.assertEquals("Model Verification", carModel, retrieveModelFromFullVehicleDetailsPage);

                // Assert Car Color Value in the full Vehicle Details Page
                Assert.assertEquals("Colour Verification", carColor, rertrieveColourFromFullVehicleDetailsPage);

                // Assert Car Year Value in the full Vehicle Details Page
                Assert.assertEquals("Year Verification", carRegistrationYear, retrieveYearFromFullVehicleDetailsPage);
            }
            if (regNumbFromInputText != null) carTaxCheckHomePage.openCarTaxWebSite();
        }
    }	

    @After
    public void afterScenario(Scenario scenario) {
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        testContext.getWebDriverManager().getWebDriverInstance().quit();

    }
}
