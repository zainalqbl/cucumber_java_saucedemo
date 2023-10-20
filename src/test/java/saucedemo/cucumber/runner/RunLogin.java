package saucedemo.cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/saucedemo/cucumber/features",
        tags = "@all",
        glue = "saucedemo.cucumber.stepDef",
        plugin = {"pretty",
                "json:target/cucumber.json",
                "me.jvt.cucumber.report.PrettyReports:target/cucumber",
                "html:target/HMTL_report.html"},
        monochrome = true
)
public class RunLogin {

}
