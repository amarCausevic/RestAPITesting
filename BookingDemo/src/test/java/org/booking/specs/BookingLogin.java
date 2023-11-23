package org.booking.specs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class BookingLogin {

  @Given("User calls sets initial api call prerequisites")
  public static void setsInitialPrerequisites() {
    System.out.println("Cmar 1");
  }

  @When("User sends correct api call payload")
  public static void login() {
    System.out.println("Cmar 2");
  }

  @Then("User receives Authorization token")
  public static void validateUserReceivesToken() {
    System.out.println("Cmar 3");
  }

  @And("User is successfully logged into Booking app")
  public static void validateUserIsSuccessfullyLoggedIn() {
    System.out.println("Cmar 4");
  }
}
