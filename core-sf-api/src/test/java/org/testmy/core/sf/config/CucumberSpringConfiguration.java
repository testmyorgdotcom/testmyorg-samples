package org.testmy.core.sf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testmy.core.sf.ITestDataManager;
import org.testmy.core.sf.TestDataManager;

@Configuration
public class CucumberSpringConfiguration {
  @Bean
  ITestDataManager dataManager(){
    return new TestDataManager();
  }
}