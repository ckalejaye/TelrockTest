package com.api.test;
 
import java.util.Arrays; 

import org.junit.Assert; 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat; 
import static org.mockito.Mockito.verify;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory; 
 

import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then; 
import cucumber.api.java.en.But;
import cucumber.api.java.Before;
import cucumber.api.java.After;   
import cucumber.runtime.java.guice.ScenarioScoped;

import com.google.inject.Inject;
import com.smartbear.readyapi.client.model.Assertion;
import com.smartbear.readyapi.client.model.JsonPathContentAssertion;
import com.smartbear.readyapi.client.model.ResponseSLAAssertion;
import com.smartbear.readyapi.client.model.RestTestRequestStep;
import com.smartbear.readyapi.client.model.ValidHttpStatusCodesAssertion;
import com.smartbear.readyapi.client.model.XPathContainsAssertion; 
import com.smartbear.readyapi.client.teststeps.TestStepTypes;
import com.smartbear.readyapi.client.teststeps.TestSteps;
import com.smartbear.readyapi.testserver.cucumber.CucumberRecipeExecutor;



/**
 * 
 * @author Cynthia
 * This class create a cucumber Runner for API Testing
 */
@ScenarioScoped
public class APITest {
	 
	private CucumberRecipeExecutor executor;
	private RestTestRequestStep apiTestStep;
	private String RefNumber;
	private String PostalCode;
	private String DateOfBirth;
	private String MobileNumber;
	
	/**
	 * Constructor for method for API Test Class
	 */
	@Inject
	public APITest(CucumberRecipeExecutor executor){ 
		this.executor = executor;

		this.RefNumber = "";
		this.PostalCode = "";
		this.DateOfBirth = "";
		this.MobileNumber = "";
	}
	
	@Given("^MobileNumber: (.+)$")
    public void mobileNumber( String mobile ) throws Throwable {
        this.MobileNumber = mobile;
    }
	
	@Given("^Date of Birth: (.+)$")
    public void DOB( String dob ) throws Throwable {
        this.DateOfBirth = dob;
    }
	
	@Given("^Postal Code: (.+)$")
    public void postalCode( String code ) throws Throwable {
         this.PostalCode = code;
    }
	
	@Given("^Postal Code:$")
	public void postal_Code() throws Throwable { 
	    //throw new PendingException();
	}
	
	@Given("^Reference Number: (.+)$")
    public void refNumber( String refNum ) throws Throwable {
         this.RefNumber = refNum;
    }

	/**
	 * This method consume the API using the authentication parameters
	 */
	@When("^a request to an API is made$")
    public void aRequestToTheAPIToListFiles() throws Throwable {
		apiTestStep = new RestTestRequestStep();
		
		apiTestStep.setURI( "https://usdemo-uat.telrock.com/telrock- tas-war/rest/idv/process" );
		apiTestStep.setMethod( TestSteps.HttpMethod.PUT.name() );
		apiTestStep.setType(TestStepTypes.REST_REQUEST.getName());

		apiTestStep.setRequestBody("[{\"idvQuestionVO\":{\"id\":1},\"answer\":\""+this.MobileNumber+"\"},"
								+ "{\"idvQuestionVO\":{\"id\":2},\"answer\":\""+this.DateOfBirth+"\"},"
								+ "{\"idvQuestionVO\":{\"id\":3},\"answer\":\""+this.PostalCode+"\"},"
								+ "{\"idvQuestionVO\":{\"id\":4},\"answer\":\""+this.RefNumber+"\"}]"); 
		
        executor.setTestStep( apiTestStep );

	}
	
	/**
	 * This method process the failed login
	 */
	@Then("^failed Login should be returned$")
    public void testFailedAuth() throws Throwable { 
		/* Below is the setup assertion with success status code */
		ValidHttpStatusCodesAssertion httpStatusCodesAssertion = new ValidHttpStatusCodesAssertion();
        httpStatusCodesAssertion.setValidStatusCodes(Arrays.asList(401));
        httpStatusCodesAssertion.setType("Valid HTTP Status Codes" );
        
        /*status code assertion is added to the executor*/
		executor.setAssertions(Arrays.asList( (Assertion)httpStatusCodesAssertion));
    }
	
	/**
	 * This method process the error login
	 */
	@Then("^error Login should be returned$")
    public void testErrorAuth() throws Throwable { 
		ValidHttpStatusCodesAssertion httpStatusCodesAssertion = new ValidHttpStatusCodesAssertion();
        httpStatusCodesAssertion.setValidStatusCodes(Arrays.asList(400));
        httpStatusCodesAssertion.setType("Valid HTTP Status Codes" );
        
        /*status code assertion is added to the executor*/
		executor.setAssertions(Arrays.asList( (Assertion)httpStatusCodesAssertion));
    }
	
	/**
	 * This method process the successful login
	 */
	@Then("^successful Login should be returned$")
    public void testSuccessAuth() throws Throwable { 
		/* Below is the setup assertion with success status code */
		ValidHttpStatusCodesAssertion httpStatusCodesAssertion = new ValidHttpStatusCodesAssertion();
        httpStatusCodesAssertion.setValidStatusCodes(Arrays.asList(200));
        httpStatusCodesAssertion.setType("Valid HTTP Status Codes" );
        
        /*status code assertion is added to the executor*/
		executor.setAssertions(Arrays.asList( (Assertion)httpStatusCodesAssertion));
    }
	
}
