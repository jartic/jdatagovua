package org.tos.jartic.api.jdatagovua.getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DefaultDataGetter implements DataGetterInterface {
	public static String DATAFORMAT_XML = "XML";
	public static String DATAFORMAT_JSON = "JSON";
	public static String DefaultUrl = "http://data.gov.ua/view-dataset/dataset";
	private String QUERY_SEPARATOR = "?";
	private String QUERY_SEPARATOR_AND = "&";

	private String getFullUrl(String datasetId, String datasetRevisonId, String format) {
		// TODO Auto-generated method stub) {
		//
		String lFormat = (format == null) ? ""
				: (format.equalsIgnoreCase(DefaultDataGetter.DATAFORMAT_JSON)) ? ".json" : "";
		//
		return DefaultUrl + lFormat + QUERY_SEPARATOR + "dataset-id=" + datasetId + ((datasetRevisonId == null) ? ""
				: ((datasetRevisonId.equals("") ? "" : QUERY_SEPARATOR_AND + "revison-id=" + datasetRevisonId)));
	}

	@Override
	public String getData(String datasetId, String datasetRevisonId, String format) {
		// TODO Auto-generated method stub
		StringBuilder resultHtml = new StringBuilder();
		String fullUrl = getFullUrl(datasetId, datasetRevisonId, format);
		System.out.println("Request url ... " + fullUrl);
		boolean redirect = false;
		//
		try {
			URL obj = new URL(fullUrl);
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			int status;
			status = conn.getResponseCode();
			if (status != HttpURLConnection.HTTP_OK) {
				if (status == HttpURLConnection.HTTP_MOVED_TEMP || status == HttpURLConnection.HTTP_MOVED_PERM
						|| status == HttpURLConnection.HTTP_SEE_OTHER)
					redirect = true;
			}
			//
			/**
			 * Redirect does not supported yet
			 */
			if (redirect) {
				return "";
			}
			//
			try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					resultHtml.append(inputLine);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		//
		return resultHtml.toString();
	}

}
