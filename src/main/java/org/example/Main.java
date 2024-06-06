package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RadioButtonsTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testRadioButtonsSelection() {
        List<WebElement> radioButtons = driver.findElements(By.name("my-radio"));

        // Select each radio button and verify only one is selected
        for (int i = 0; i < radioButtons.size(); i++) {
            WebElement radioButton = radioButtons.get(i);
            radioButton.click();

            for (int j = 0; j < radioButtons.size(); j++) {
                WebElement rb = radioButtons.get(j);
                if (j == i) {
                    assertTrue(rb.isSelected(), "Radio button " + (j + 1) + " should be selected.");
                } else {
                    assertFalse(rb.isSelected(), "Radio button " + (j + 1) + " should not be selected.");
                }
            }
        }
    }
}
