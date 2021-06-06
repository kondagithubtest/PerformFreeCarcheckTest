package runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        snippets = SnippetType.CAMELCASE,
        tags = {"@CarDetailsCheck"},
        plugin={"pretty","junit:target/cucumber-junit-report.xml", "html:target/site/cucumber-pretty", "json:target/cucumber.json"},
        features = "src/test/resources/features/",
        glue = "stepdefinitions"
)
public class TestRunner {

}
