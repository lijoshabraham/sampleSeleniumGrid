package com.naveenautomation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.naveenautomation.TestBases.TestBase;
import com.naveenautomation.utility.Utility;

public class DownloadPage extends TestBase {
	public DownloadPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#content h2")
	WebElement downloadsHeading;

	public String getAccountDowloadsHeading() {
		return Utility.getTextFromWebelement(downloadsHeading);
	}

}
