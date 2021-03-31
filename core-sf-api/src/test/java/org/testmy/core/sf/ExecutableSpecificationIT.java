package org.testmy.core.sf;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
  features = "src/test/resources",
  glue = "org.testmy.core.sf.glue",
  plugin = {
    "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"
  }
)
public class ExecutableSpecificationIT {
  
}
