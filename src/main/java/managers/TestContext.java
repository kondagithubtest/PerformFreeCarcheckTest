package managers;

public class TestContext {

	private PageObjectManager pageObjectManager;
	private WebDriverManager webDriverManager;
	
	public TestContext() {
		webDriverManager = new WebDriverManager();
		pageObjectManager = new PageObjectManager(webDriverManager.getWebDriverInstance());
	}
	
	public PageObjectManager getPageObjectManager() {
		return pageObjectManager;
	}
	
	public WebDriverManager getWebDriverManager() {
		return webDriverManager;
	}	
}
