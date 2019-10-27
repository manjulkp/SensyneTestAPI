$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/feature/GetProductList.feature");
formatter.feature({
  "name": "List of Product",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@AllProduct"
    }
  ]
});
formatter.scenario({
  "name": "Get list of products",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@AllProduct"
    }
  ]
});
formatter.step({
  "name": "I perform get operation \"/products\" to get the list of product",
  "keyword": "Given "
});
formatter.match({
  "location": "GetProductList.i_perform_get_operation_to_get_the_list_of_product(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the product in 1 as the price as \"9.25\"",
  "keyword": "Then "
});
formatter.match({
  "location": "GetProductList.the_product_is_as_the_price_as(int,String)"
});
formatter.result({
  "status": "passed"
});
});