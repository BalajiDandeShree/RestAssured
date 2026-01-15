package com.w2a.runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
        features = "src/test/features",
        glue = "com.w2a",
        plugin = {
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
