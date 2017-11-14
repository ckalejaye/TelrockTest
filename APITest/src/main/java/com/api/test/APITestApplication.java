package com.api.test;

 
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber; 

@RunWith(Cucumber.class)
@CucumberOptions( format = {"pretty", "html:target/cucumber"},
	    features = {"Feature"})
public class APITestApplication { 
	
	public static void main(String[] args) { 
		
	}
}