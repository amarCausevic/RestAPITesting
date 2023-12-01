package org.booking.options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/org/booking/features",
    glue = "org/booking/specs",
    plugin = {"pretty", "html:target/html-reports"},
    stepNotifications = true
    /*tags = "createBooking"*/)
public class TestRunner {

}
