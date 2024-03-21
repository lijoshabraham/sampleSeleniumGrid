package com.naveenautomation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.naveenautomation.TestBases.TestBase;
import com.naveenautomation.utility.Utility;

public class BrandsPage extends TestBase {

	public BrandsPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#content h1")
	WebElement brandPageHeader;

	public String validateBrandPageHeader() {
		return Utility.getTextFromWebelement(brandPageHeader);
	}

}
