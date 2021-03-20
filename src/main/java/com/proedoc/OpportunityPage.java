package com.proedoc;

import com.codeborne.selenide.WebDriverRunner;

public class OpportunityPage {

	public String getId() {
		final String recordUrl = WebDriverRunner.currentFrameUrl();
		return URLParser.parseUrl(recordUrl);
	}
}
