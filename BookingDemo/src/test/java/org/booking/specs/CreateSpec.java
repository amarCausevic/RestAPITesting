package org.booking.specs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.booking.actions.CreateDAO;
import org.booking.model.CreateResponseDTO;
import org.junit.Assert;

public class CreateSpec extends CreateDAO {

  private static CreateResponseDTO getCreateDetails;

  @Given("user sends a CREATE request to the endpoint")
  public static void requestCreateBooking() {
    Response response = createBooking();

    getCreateDetails = getCreateResponse(response);
    System.out.println(
        "This is from inside the response: " + getCreateDetails.getBooking().getDepositPaid());
  }

  @When("endpoint is reached, new booking is added to database")
  public static void validateResponseIsNotNull() {

  }

  @Then("validate correct values are added to database")
  public static void validateResponseData() {

  }

  @And("validate create status code {int}")
  public static void validateEndResponseStatus(int defaultStatusCode) {
    //Change this get value from response!
    Assert.assertEquals(defaultStatusCode, 200);
  }
}
