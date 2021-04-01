package com.sample.test.demo.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sample.test.demo.TestBase;
import com.sample.test.demo.constants.PaymentType;
import com.sample.test.demo.constants.PizzaToppings;
import com.sample.test.demo.constants.PizzaTypes;
import com.sample.test.demo.pageobjects.PizzaOrderPageObject;

import junit.framework.Assert;

public class ValidTests extends TestBase {

	PizzaOrderPageObject pizzaOrderPage;

	@Test(priority=1)
	public void validateCostPrice() {
		pizzaOrderPage = new PizzaOrderPageObject(driver);
		pizzaOrderPage.selectPizza1(PizzaTypes.SMALL_NOTOPPINGS);
		pizzaOrderPage.selectToppings1(PizzaToppings.ITALIANHAM);
		pizzaOrderPage.selectToppings2(PizzaToppings.MANGOS);
		pizzaOrderPage.setQuantity(10);
		double expectedCost = 10 * PizzaTypes.SMALL_NOTOPPINGS.getCost();
		Assert.assertEquals(String.valueOf(expectedCost), pizzaOrderPage.getCost());
	}

	@Test(priority=2,dataProvider = "testData")
	public void validPlaceOrder(PizzaTypes pizza1, PizzaToppings toppings1, PizzaToppings toppings2, int quantity,
			String email, String name, String phone, PaymentType paymentType) {
		pizzaOrderPage = new PizzaOrderPageObject(driver);
		pizzaOrderPage.selectPizza1(pizza1);
		pizzaOrderPage.selectToppings1(toppings1);
		pizzaOrderPage.selectToppings2(toppings2);
		pizzaOrderPage.setQuantity(quantity);
		String totalCost = pizzaOrderPage.getCost();
		pizzaOrderPage.setEmail(email);
		pizzaOrderPage.setName(name);
		pizzaOrderPage.setPhone(phone);
		if (paymentType.equals(PaymentType.CREDITCARD)) {
			pizzaOrderPage.enableCreditCardRadioButton();
		} else if (paymentType.equals(PaymentType.CASH)) {
			pizzaOrderPage.enableCashRadioButton();
		}
		pizzaOrderPage.submitPlaceOrder();
		String expectedPopText = "Thank you for your order! TOTAL: " + totalCost + " " + pizza1.getDisplayName();
		Assert.assertEquals(expectedPopText, pizzaOrderPage.getPopText());
		pizzaOrderPage.clickOnClosePopupDialog();
	}

	@Test(priority=3)
	public void validateResetButton() {
		pizzaOrderPage = new PizzaOrderPageObject(driver);
		pizzaOrderPage.selectPizza1(PizzaTypes.SMALL_NOTOPPINGS);
		pizzaOrderPage.selectToppings1(PizzaToppings.ITALIANHAM);
		pizzaOrderPage.selectToppings2(PizzaToppings.MANGOS);
		pizzaOrderPage.setQuantity(10);
		pizzaOrderPage.setName("testName");
		pizzaOrderPage.setEmail("test");
		pizzaOrderPage.setPhone("1234567");
		pizzaOrderPage.enableCreditCardRadioButton();
		pizzaOrderPage.clickOnReset();
		Assert.assertTrue("Unable to reset the field quantity", Integer.parseInt(pizzaOrderPage.getQuantity()) == 0);
		Assert.assertTrue("Unable to reset the field cost", Integer.parseInt(pizzaOrderPage.getCost()) == 0);
		Assert.assertTrue("Unable to reset the field cost", pizzaOrderPage.getName().isEmpty());
		Assert.assertTrue("Unable to reset the field cost", pizzaOrderPage.getEmail().isEmpty());
		Assert.assertTrue("Unable to reset the field cost", pizzaOrderPage.getPhone().isEmpty());
	}

	@DataProvider(name = "testData")
	public Object[][] testData() {
		return new Object[][] {
				{ PizzaTypes.SMALL_NOTOPPINGS, PizzaToppings.MANGOS, PizzaToppings.OLIVES, 1, "test@gmail.com",
						"testName", "12345678", PaymentType.CREDITCARD },
				{ PizzaTypes.SMALL_ONETOPPINGS, PizzaToppings.MUSHROOMS, PizzaToppings.ONIONS, 2, "test@gmail.com",
						"testName", "12345678", PaymentType.CREDITCARD },
				{ PizzaTypes.MEDIUM_TWOTOPPINGS, PizzaToppings.ITALIANHAM, PizzaToppings.PEPPERONI, 6, "test@gmail.com",
						"testName", "12345678", PaymentType.CREDITCARD },
				{ PizzaTypes.LARE_NOTOPPINGS, PizzaToppings.SALAMI, PizzaToppings.PROVOLNE, 7, "test@gmail.com",
						"testName", "12345678", PaymentType.CREDITCARD },
				{ PizzaTypes.LARGE_THREETOPPINGS, PizzaToppings.EXTRACHEESE, PizzaToppings.MOZZARELLA, 5,
						"test@gmail.com", "testName", "12345678", PaymentType.CREDITCARD },
				{ PizzaTypes.SMALL_NOTOPPINGS, PizzaToppings.OLIVES, PizzaToppings.MANGOS, 1, "test@gmail.com",
						"testName", "12345678", PaymentType.CASH },
				{ PizzaTypes.SMALL_ONETOPPINGS, PizzaToppings.ONIONS, PizzaToppings.MUSHROOMS, 3, "test@gmail.com",
						"testName", "12345678", PaymentType.CASH },
				{ PizzaTypes.MEDIUM_TWOTOPPINGS, PizzaToppings.PEPPERONI, PizzaToppings.ITALIANHAM, 6, "test@gmail.com",
						"testName", "12345678", PaymentType.CASH },
				{ PizzaTypes.LARE_NOTOPPINGS, PizzaToppings.PROVOLNE, PizzaToppings.SALAMI, 7, "test@gmail.com",
						"testName", "12345678", PaymentType.CASH },
				{ PizzaTypes.LARGE_THREETOPPINGS, PizzaToppings.MOZZARELLA, PizzaToppings.EXTRACHEESE, 5,
						"test@gmail.com", "testName", "12345678", PaymentType.CASH } };

	}

}
