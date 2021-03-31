package com.proedoc;

import java.io.FileNotFoundException;

import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

public class SfApi {

	public SObject queryObject(String sfId) {
		String username = System.getProperty("sf.username");
		String password = System.getProperty("sf.password");
		String authEndPoint = "https://login.salesforce.com/services/Soap/u/51/";

		PartnerConnection partnerConnection = null;

		try {
			ConnectorConfig config = new ConnectorConfig();
			config.setUsername(username);
			config.setPassword(password);

			config.setAuthEndpoint(authEndPoint);
			config.setTraceMessage(true);
			config.setPrettyPrintXml(true);

			partnerConnection = new PartnerConnection(config);

		} catch (ConnectionException ce) {
			ce.printStackTrace();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}

		try {
			return partnerConnection.query("SELECT Id FROM Opportunity WHERE Id = '" + sfId + "'").getRecords()[0];
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
		return null;
	}
}
