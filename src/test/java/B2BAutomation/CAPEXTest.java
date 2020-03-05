package B2BAutomation;
import org.apache.commons.mail.EmailException;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.AfterClass;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.xml.xsom.impl.Ref.ContentType;

import resources.Constants;
import resources.DataProviderParameter;
import resources.TestBase;
import io.restassured.http.Header;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class CAPEXTest extends TestBase {
	
	String access_token="";
	
	String URI="https://api.swychover.io/sandbox";
	/*
	String clinet_id ="swych_b2b_capex";
	String accountid="9185384047158163";
	String ApiKey = "d2ae25t7197f11e793ae9r2e63269v5t";
	*/
	String orderId ="";
	
	@DataProviderParameter("FileName=" + Constants.testData + ";Sheet=Sheet1")
	@Test(priority=1,dataProviderClass = resources.ExcelFileDataProvider.class,dataProvider = "getDataFromExcelFile")

	 public void get_balance(String URI, String client_id,String accountid, String APIKey, String OcpApimSubscriptionKey ) 
	 {  
		//String accountid="9185384047158163";
		System.out.println("\nNow Getting Balance " );
		 System.out.println("access token used in get balance is " +access_token);
		Response response_get_balance=RestAssured.given().header("Authorization", access_token).
				header("clientId",client_id).
				header("ApiKey",APIKey).
				header("Ocp-Apim-Subscription-Key",OcpApimSubscriptionKey).
				get(URI+"/api/v1/b2b/"+accountid+"/balance");
		
		int statusCode_get_balance = response_get_balance.getStatusCode();
		 
		 String responsebody_get_balance= response_get_balance.getBody().asString();
		 System.out.println("The response from get balance is "+responsebody_get_balance);
		 System.out.println("The statuscode is "+statusCode_get_balance);
		 
		 JsonPath jsonPathEvaluator_get_balance = response_get_balance.jsonPath();
		 int status= jsonPathEvaluator_get_balance.get("statusCode");
		 //int status = Integer.parseInt(sstatus);
		 Assert.assertEquals(status, 16010600);
		
	 }
	
	@DataProviderParameter("FileName=" + Constants.testData + ";Sheet=Sheet1")
	@Test(priority=2,dataProviderClass = resources.ExcelFileDataProvider.class,dataProvider = "getDataFromExcelFile")

	 public void get_catalog(String URI, String client_id,String accountid, String APIKey, String OcpApimSubscriptionKey) 
	 {  
		//String accountid="9185384047158163";
		 System.out.println("\nNow Getting Catalog " );
		 System.out.println("access token used in get catalog is " +access_token);
		Response response_get_balance=RestAssured.given().header("Authorization", access_token).
				header("clientId",client_id).
				header("ApiKey",APIKey).
				header("Ocp-Apim-Subscription-Key",OcpApimSubscriptionKey).
				get(URI+"/api/v1/b2b/"+accountid+"/catalog");
		
		int statusCode_get_balance = response_get_balance.getStatusCode();
		 
		 String responsebody_get_balance= response_get_balance.getBody().asString();
		 System.out.println("The response from get catalog is "+responsebody_get_balance);
		 System.out.println("The statuscode is "+statusCode_get_balance);
		 
		 JsonPath jsonPathEvaluator_get_balance = response_get_balance.jsonPath();
		 int status= jsonPathEvaluator_get_balance.get("statusCode");
		 //int status = Integer.parseInt(sstatus);
		 Assert.assertEquals(status, 16010800);
		
			 
	 
 }
	
	 
	
	//@DataProvider(name = "Authentication")
	@DataProviderParameter("FileName=" + Constants.testData_auth + ";Sheet=Sheet1")
	@Test(priority=0,dataProviderClass = resources.ExcelFileDataProvider.class,dataProvider = "getDataFromExcelFile")
	
	//
	
	 // @BeforeTest
	 //public void POST_createsession() throws JSONException
	 public void POST_createsession(String URI, String client_id,String OcpApimSubscriptionKey,
	 String ContentType,String granttype,String client_secret,String resource) throws JSONException
    
	 {   
		
		 
	 // Specify the base URL to the RESTful web service
		 RestAssured.baseURI = URI;
	 
	 
	 //Header header = new Header("Content-Type", "application/x-www-form-urlencoded");
	 //Header header = new Header("Content-Type", ContentType);
	 
	 Response response = RestAssured.given().header("Content-Type", "application/x-www-form-urlencoded").
			 header("Ocp-Apim-Subscription-Key",OcpApimSubscriptionKey).
			 formParam("grant_type", granttype).
			 formParam("client_id", client_id).
			 formParam("client_secret",client_secret).request().
			 formParam("resource",resource).
			 post(URI+"/oauth2/token");
	 
	
     int statusCode = response.getStatusCode();
	 
	 String responsebody= response.getBody().asString();
	 System.out.println("The response from POST Authetication is "+responsebody);
	 System.out.println("The statuscode is "+statusCode);
	 
	 JsonPath jsonPathEvaluator = response.jsonPath();
	 access_token = jsonPathEvaluator.get("access_token");
	 System.out.println("access token Response " +access_token);
	 
	
	    //String token = response.body().jsonPath().get("token");
	    //System.out.println("The responsebody is "+responsebody);
	   
	    //String access_token= responsebody.substring(185,1352);
	   // int length = access_token.length();
	    //System.out.println("Lenght is"+length);
	    //System.out.println("The responsebody is "+access_token);
	    //return token;
	    
	    
	    //Accountid = 9185384047158163
	    //Clinet id =  swych_b2b_capex
	    //API Key = d2ae25t7197f11e793ae9r2e63269v5t
	 
	 
	 }
	 
	@DataProviderParameter("FileName=" + Constants.TestData_createorder + ";Sheet=Sheet1")
	@Test(priority=3,dataProviderClass = resources.ExcelFileDataProvider.class,dataProvider = "getDataFromExcelFile")

	 public void put_createorder(String client_id,String APIKey,String OcpApimSubscriptionKey,
			 String accountid,String brandId,String productId,String amount,String senderFirstName,String senderLastName,String senderEmail,
			 String senderPhone,String recipientEmail,String recipientPhoneNumber,
			 String notificationDeliveryMethod,String giftDeliveryMode,String swychable) throws JSONException 
	 {  
		
		 RestAssured.baseURI = URI;
		    RequestSpecification request = RestAssured.given();
		    System.out.println("\nNow Creating Order " );
		    System.out.println("access token used in get catalog is " +access_token);
		    request.header("Content-Type", "application/json").
		    header("Authorization",access_token).
		    header("clientId",client_id).
		    header("ApiKey",APIKey).
		    header("Ocp-Apim-Subscription-Key",OcpApimSubscriptionKey);
		    
		    JSONObject requestParams = new JSONObject();
		    
		    requestParams.put("accountId",accountid);
		    requestParams.put("brandId",brandId);
	    	requestParams.put("productId",productId);
	    	requestParams.put("amount",amount);
	    	
            
	    	 requestParams.put("senderFirstName",senderFirstName);
			    requestParams.put("senderLastName",senderLastName);
		    	requestParams.put("senderEmail",senderEmail);
		    	requestParams.put("senderPhone",senderPhone);
	    	
		    requestParams.put("recipientEmail",recipientEmail);
	    	requestParams.put("recipientPhoneNumber",recipientPhoneNumber);
	    	requestParams.put("notificationDeliveryMethod",notificationDeliveryMethod);
		    requestParams.put("giftDeliveryMode",giftDeliveryMode);
	    	requestParams.put("swychable",swychable);
	    	
	    
	    	request.body(requestParams.toString());
			 
			
			 Response response = request.put(URI+"/api/v1/b2b/order");
					 

			    int statusCode = response.getStatusCode();
				 String responsebody= response.getBody().asString();
				 System.out.println("The response from create Order is "+responsebody);
				 
				 JsonPath jsonPathEvaluator = response.jsonPath();
				 int status= jsonPathEvaluator.get("statusCode");
				 //int status = Integer.parseInt(sstatus);
				 Assert.assertEquals(status, 16010900);
				 orderId= jsonPathEvaluator.get("orderId");
				 System.out.println("The order id is " +orderId);
	 
 }
	@DataProviderParameter("FileName=" + Constants.testData + ";Sheet=Sheet1")
	@Test(priority=4,dataProviderClass = resources.ExcelFileDataProvider.class,dataProvider = "getDataFromExcelFile")
	public void post_confirmorder(String URI, String client_id,String accountid, String APIKey,
			String OcpApimSubscriptionKey) throws JSONException 
	 {  
		 
		 RestAssured.baseURI = URI;
		    RequestSpecification request = RestAssured.given();
		    System.out.println("\nNow Confirming Order " );
		    request.header("Content-Type", "application/json").
		    header("Authorization",access_token).
		    header("clientId",client_id).
		    header("ApiKey",APIKey).
		    header("Ocp-Apim-Subscription-Key",OcpApimSubscriptionKey);
		    
		    JSONObject requestParams = new JSONObject();
		    
		    requestParams.put("accountId",accountid);
		    requestParams.put("orderId",orderId);
	    	
	    	
			
	    	request.body(requestParams.toString());
			 
			
			 Response response = request.post(URI+"/api/v1/b2b/order");
					 

			    int statusCode = response.getStatusCode();
				 String responsebody= response.getBody().asString();
				 System.out.println("The response from create Order is "+responsebody);
				 
				 JsonPath jsonPathEvaluator = response.jsonPath();
				 int status= jsonPathEvaluator.get("statusCode");
				 //int status = Integer.parseInt(sstatus);
				 Assert.assertEquals(status, 16011000);
				 
	       
}
	@DataProviderParameter("FileName=" + Constants.testData_suggestedrate + ";Sheet=Sheet1")
	@Test(priority=6,dataProviderClass = resources.ExcelFileDataProvider.class,dataProvider = "getDataFromExcelFile")
	 public void post_suggestedrate(String URI, String client_id,String accountid, String APIKey,
				String OcpApimSubscriptionKey,String sourceCurrency,String targetCurrency) throws JSONException 
	 {  
		 
		 RestAssured.baseURI = URI;
		    RequestSpecification request = RestAssured.given();
		    System.out.println("\nNow Doing Suggested Rate " );
		    request.header("Content-Type", "application/json").
		    header("Authorization",access_token).
		    header("clientId",client_id).
		    header("ApiKey",APIKey).
		    header("Ocp-Apim-Subscription-Key",OcpApimSubscriptionKey);
		    
		    JSONObject requestParams = new JSONObject();
		    
		    requestParams.put("sourceCurrency", sourceCurrency);
		    requestParams.put("targetCurrency", targetCurrency);
	    	
	    	
			
	    	request.body(requestParams.toString());
			 
			
			 Response response = request.post(URI+"/api/v1/b2b/suggestedRate");
					 

			    int statusCode = response.getStatusCode();
				 String responsebody= response.getBody().asString();
				 System.out.println("The response from create Order is "+responsebody);
				 /*
				 JsonPath jsonPathEvaluator = response.jsonPath();
				 int status= jsonPathEvaluator.get("statusCode");
				 //int status = Integer.parseInt(sstatus);
				 Assert.assertEquals(status, 16011000);
				 */
	       
}
	
	@DataProviderParameter("FileName=" + Constants.testData + ";Sheet=Sheet1")
	@Test(priority=5,dataProviderClass = resources.ExcelFileDataProvider.class,dataProvider = "getDataFromExcelFile")

	 public void get_order_details(String URI, String client_id,String accountid, String APIKey, String OcpApimSubscriptionKey) 
	 {  
		//String accountid="9185384047158163";
		 System.out.println("\nNow Order Details " );
		 System.out.println("access token used in get order details is " +access_token);
		Response response_get_order_details=RestAssured.given().header("Authorization", access_token).
				header("clientId",client_id).
				header("ApiKey",APIKey).
				header("Ocp-Apim-Subscription-Key",OcpApimSubscriptionKey).
				get(URI+"/api/v1/b2b/"+accountid+"/order?orderId="+orderId);
		
		int statusCode_get_order_details = response_get_order_details.getStatusCode();
		 
		 String responsebody_get_order_details= response_get_order_details.getBody().asString();
		 System.out.println("The response from get order_details is "+responsebody_get_order_details);
		 System.out.println("The statuscode is "+statusCode_get_order_details);
		 
		 JsonPath jsonPathEvaluator_get_order_details = response_get_order_details.jsonPath();
		 int status= jsonPathEvaluator_get_order_details.get("statusCode");
		 //int status = Integer.parseInt(sstatus);
		 Assert.assertEquals(status, 16011200);
		
			 
	 
 }	
	/*
	@DataProviderParameter("FileName=" + Constants.testData_retrievehistory + ";Sheet=Sheet1")
	@Test(priority=6,dataProviderClass = resources.ExcelFileDataProvider.class,dataProvider = "getDataFromExcelFile")

	 public void get_retrieve_history(String URI, String client_id,String accountid, String APIKey, String OcpApimSubscriptionKey,
			 String startdate,String enddate) 
	 {  
		//String accountid="9185384047158163";
		 System.out.println("\nNow Retrieving History..." );
		 System.out.println("access token used in retrieve history is " +access_token);
		Response response_get_retrieve_history=RestAssured.given().header("Authorization", access_token).
				header("clientId",client_id).
				header("ApiKey",APIKey).
				header("Ocp-Apim-Subscription-Key",OcpApimSubscriptionKey).
				get(URI+"/api/v1/b2b/"+accountid+"/history?startDate="+startdate+"&endDate="+enddate+"[&pageNo][&pageSize]");
		
		int statusCode_get_retrieve_history = response_get_retrieve_history.getStatusCode();
		 
		 String responsebody_get_retrieve_history= response_get_retrieve_history.getBody().asString();
		 System.out.println("The response from retrieve history is "+responsebody_get_retrieve_history);
		 System.out.println("The statuscode is "+statusCode_get_retrieve_history);
		 
		 JsonPath jsonPathEvaluator_get_retrieve_history = response_get_retrieve_history.jsonPath();
		 int status= jsonPathEvaluator_get_retrieve_history.get("statusCode");
		 //int status = Integer.parseInt(sstatus);
		 Assert.assertEquals(status, 16011100);
		
			 
	 
 }	
 */
	
	 
	 }
	 /*
	 public void afterClass() throws EmailException {
		 email.sendemail();
		 System.out.println("Done");
	 }
	 */
	 
	

