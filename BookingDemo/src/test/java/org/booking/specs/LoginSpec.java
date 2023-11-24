package org.booking.specs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.response.Response;
import org.booking.actions.LoginDAO;
import org.booking.model.LoginResponseDTO;
import org.junit.Assert;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class LoginSpec extends LoginDAO {
  
  private static LoginResponseDTO responseDTO = new LoginResponseDTO();
  private static Response response;

  @Given("username {string} and password {string} user sends a request to the endpoint")
  public static LoginResponseDTO sendRequest(String username, String password) {
    response = login(username, password);
    responseDTO = getResponse(response);

    return responseDTO;
  }

  @When("endpoint is reached, response is send back to the user")
  public static void validateResponse() {
    Assert.assertNotNull(responseDTO);
  }

  @Then("validate response body has returned a sign in token")
  public static void validateResponseToken() {
    Assert.assertNotNull(responseDTO.getToken());
  }

  @And("validate status code {int}")
  public static void validateStatusCode(int statusCode) {
    assertStatusCode(response, statusCode);
  }

  @Then("validate response body has message {string}")
  public static void validateInvalidRequest(String message) {
    Assert.assertEquals(message, responseDTO.getReason());
  }
}
