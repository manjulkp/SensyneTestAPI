package steps;

import java.util.Map;

import org.testng.Assert;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import pojos.JsonBody;
import utility.Common;
import utility.RestAssuredHelper;

public class GetProductList  {

	RestAssuredHelper restAssuredExtension = new RestAssuredHelper();	
    ResponseOptions<Response> res;
	Common commonUtility = new Common();
	Map<String,String> uniquePath;
	JsonBody json ;
	
	@Given("I perform get operation {string} to get the list of product")
	public void i_perform_get_operation_to_get_the_list_of_product(String url_pattern) {
		
		res = (ResponseOptions<Response>) restAssuredExtension.GetOps(url_pattern).getBody();
		
	}

	@Then("the product in {int} as the price as {string}")
	public void the_product_is_as_the_price_as(int id, String price) {
		
		Assert.assertTrue(res.getBody().jsonPath().get("[0].id").equals(id));
		Assert.assertTrue(res.getBody().jsonPath().get("[0].price").equals(price));
	  
	}
	
	
}

