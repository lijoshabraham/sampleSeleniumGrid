package com.naveenautomation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

import com.naveenautomation.TestBases.TestBase;
import com.naveenautomation.utility.Utility;

public class HomePage extends TestBase {

	public HomePage() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(css = "#logo img")
	WebElement logo;

	@FindBy(css = "#search input")
	WebElement searchInputBox;

	@FindBy(css = "#search button")
	WebElement searchBtn;

	@FindBy(css = ".row #cart")
	WebElement cartBtn;

	@FindBy(css = ".row #cart .dropdown-menu p a:first-of-type")
	WebElement viewCartBtn;

	@FindBy(css = "#common-home>div:first-of-type")
	WebElement successAlert;

	@FindBy(css = "#content .row>div:first-of-type .button-group button:first-of-type")
	WebElement addTocartIcon;

	@FindBy(css = "#content .row>div:first-of-type .caption a")
	WebElement macBookBtn;

	@FindBy(css = ".row> div:first-of-type .list-unstyled li a")
	List<WebElement> informationWebElementList;

	@FindBy(css = ".row> div:nth-of-type(2) .list-unstyled li a")
	List<WebElement> customerServiceWebElementList;

	@FindBy(css = ".row> div:nth-of-type(3) .list-unstyled li a")
	List<WebElement> extrasWebElementList;

	public void clickAddToCartIcon() {
		Utility.clickOnElement(addTocartIcon);
	}


	private void clickCartBtn() {
		Utility.clickOnElement(cartBtn);
	}

	private void clickViewCartBtn() {
		Utility.clickOnElement(viewCartBtn);
	}


	public boolean verifyNaveenLogo() {
		logger.info("verifying logo is displayed");
		return logo.isDisplayed();
	}

	private void enterSearchData(String searchData) {
		Utility.sendText(searchInputBox, searchData);
	}

	private void clickSearch() {
		Utility.clickOnElement(searchBtn);
	}

	// Information List
	private <T> T clickInformationListItem(String item, Class<T> pageClass) {
		for (WebElement element : informationWebElementList) {
			if (element.getText().equalsIgnoreCase(item)) {
				element.click();
				return PageFactory.initElements(getDriver(), pageClass);
			}
		}
		throw new RuntimeException("Information Item not found: " + item);
	}

	

	// Customer Service List
	private <T> T clickCustomerServiceListItem(String item, Class<T> pageClass) {
		for (WebElement element : customerServiceWebElementList) {
			if (element.getText().equalsIgnoreCase(item)) {
				element.click();
				return PageFactory.initElements(getDriver(), pageClass);
			}
		}
		throw new RuntimeException("Customer Service Item not found: " + item);
	}

	

	// Extras List
	private <T> T clickExtrasListItem(String item, Class<T> pageClass) {
		for (WebElement element : extrasWebElementList) {
			if (element.getText().equalsIgnoreCase(item)) {
				element.click();
				return PageFactory.initElements(getDriver(), pageClass);
			}
		}
		throw new RuntimeException("Extras Item not found: " + item);
	}

	public BrandsPage clickBrandsItem(String itemToClick) {
		return clickExtrasListItem(itemToClick, BrandsPage.class);
	}

	

	public String getSuccessAlert() {

		return successAlert.getText();
	}

}
