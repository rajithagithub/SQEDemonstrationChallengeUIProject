package com.sample.test.demo.tests;

import org.testng.annotations.Test;
import com.sample.test.demo.TestBase;
import com.sample.test.demo.constants.PizzaToppings;
import com.sample.test.demo.constants.PizzaTypes;
import com.sample.test.demo.pageobjects.PizzaOrderPageObject;

import junit.framework.Assert;

public class NegativeTests extends TestBase {

	PizzaOrderPageObject pizzaOrderPage;
	
	@Test()
    public void validateInputLengthOfQuantityField() {
       pizzaOrderPage=new PizzaOrderPageObject(driver);
       pizzaOrderPage.setQuantity(1000000);
       String actualQuantity = pizzaOrderPage.getQuantity();
       Assert.assertEquals(actualQuantity.length(), 5);      
    }
	
	@Test()
    public void validateInputLengthOfPhoneField() {
       pizzaOrderPage=new PizzaOrderPageObject(driver);
       pizzaOrderPage.setPhone("1234567123123123123132312");
       String actualPhoneNumber = pizzaOrderPage.getPhone();
       Assert.assertEquals(actualPhoneNumber.length(), 10);
    }
	
    @Test()
    public void validateRequiredFieldName() {
       pizzaOrderPage=new PizzaOrderPageObject(driver);
       pizzaOrderPage.selectPizza1(PizzaTypes.SMALL_NOTOPPINGS);
       pizzaOrderPage.selectToppings1(PizzaToppings.ITALIANHAM);
       pizzaOrderPage.selectToppings2(PizzaToppings.MANGOS);
       pizzaOrderPage.setQuantity(10);
       pizzaOrderPage.setEmail("test@gmail.com");
       pizzaOrderPage.setPhone("1234567");
       pizzaOrderPage.enableCreditCardRadioButton();
       pizzaOrderPage.submitPlaceOrder();
       String expectedPopText ="Missing name";
       String actualPopText =  pizzaOrderPage.getPopText();
       pizzaOrderPage.clickOnClosePopupDialog();
       Assert.assertEquals(expectedPopText,actualPopText);
    }
    
    @Test()
    public void validateRequiredFieldPhone() {
       pizzaOrderPage=new PizzaOrderPageObject(driver);
       pizzaOrderPage.selectPizza1(PizzaTypes.SMALL_NOTOPPINGS);
       pizzaOrderPage.selectToppings1(PizzaToppings.ITALIANHAM);
       pizzaOrderPage.selectToppings2(PizzaToppings.MANGOS);
       pizzaOrderPage.setQuantity(10);
       pizzaOrderPage.setName("testName");
       pizzaOrderPage.setEmail("test@gmail.com");
       pizzaOrderPage.enableCreditCardRadioButton();
       pizzaOrderPage.submitPlaceOrder();
       String expectedPopText ="Missing phone number";
       String actualPopText= pizzaOrderPage.getPopText();
       pizzaOrderPage.clickOnClosePopupDialog();
       Assert.assertEquals(expectedPopText,actualPopText );  
    }
    
    @Test()
    public void validatePlaceOrderWithZeroQuantity() {
       pizzaOrderPage=new PizzaOrderPageObject(driver);
       pizzaOrderPage.selectPizza1(PizzaTypes.SMALL_NOTOPPINGS);
       pizzaOrderPage.selectToppings1(PizzaToppings.ITALIANHAM);
       pizzaOrderPage.selectToppings2(PizzaToppings.MANGOS);
       pizzaOrderPage.setQuantity(0);
       pizzaOrderPage.setPhone("1234567");
       pizzaOrderPage.setName("testName");
       pizzaOrderPage.setEmail("test@gmail.com");
       pizzaOrderPage.enableCreditCardRadioButton();
       pizzaOrderPage.submitPlaceOrder();
       String expectedPopText ="Quantity must be greater than zero";
       String ActualPopText = pizzaOrderPage.getPopText();
       pizzaOrderPage.clickOnClosePopupDialog();
       Assert.assertEquals(expectedPopText,ActualPopText);
    }
    
    @Test()
    public void validatePlaceOrderWithNegativeQuantity() {
       pizzaOrderPage=new PizzaOrderPageObject(driver);
       pizzaOrderPage.selectPizza1(PizzaTypes.SMALL_NOTOPPINGS);
       pizzaOrderPage.selectToppings1(PizzaToppings.ITALIANHAM);
       pizzaOrderPage.selectToppings2(PizzaToppings.MANGOS);
       pizzaOrderPage.setQuantity(-10);
       pizzaOrderPage.setPhone("1234567");
       pizzaOrderPage.setName("testName");
       pizzaOrderPage.setEmail("test@gmail.com");
       pizzaOrderPage.enableCreditCardRadioButton();
       pizzaOrderPage.submitPlaceOrder();
       String expectedPopText ="Quantity must be greater than zero";
       String ActualPopText = pizzaOrderPage.getPopText();
       pizzaOrderPage.clickOnClosePopupDialog();
       Assert.assertEquals(expectedPopText,ActualPopText);
    }
    
    @Test()
    public void validateInvalidEmailPattern() {
       pizzaOrderPage=new PizzaOrderPageObject(driver);
       pizzaOrderPage.selectPizza1(PizzaTypes.SMALL_NOTOPPINGS);
       pizzaOrderPage.selectToppings1(PizzaToppings.ITALIANHAM);
       pizzaOrderPage.selectToppings2(PizzaToppings.MANGOS);
       pizzaOrderPage.setQuantity(10);
       pizzaOrderPage.setName("testName");
       pizzaOrderPage.setEmail("test");
       pizzaOrderPage.setPhone("1234567");
       pizzaOrderPage.enableCreditCardRadioButton();
       pizzaOrderPage.submitPlaceOrder();
       String expectedPopText ="Enter valid email address";
       String actualPopText =  pizzaOrderPage.getPopText();
       pizzaOrderPage.clickOnClosePopupDialog();
       Assert.assertEquals(expectedPopText,actualPopText);
    }
    
}
