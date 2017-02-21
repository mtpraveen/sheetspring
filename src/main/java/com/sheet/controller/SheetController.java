package com.sheet.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.web.util.RedirectUrlBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.gson.Gson;
import com.sheet.dao.impl.FireBaseDao;
import com.sheet.dao.impl.SheetFireBaseDao;
import com.sheet.model.SheetModel;

import com.sheet.services.SheetService;

@Controller
//@EnableScheduling
public class SheetController {
	@Autowired
	SheetService mSheetService;
	
	@Autowired
	PubImpl mPublisher;

	/** Application name. */
	private static final String APPLICATION_NAME = "Google Sheets API Java";

	/** Directory to store user credentials for this application. */
	private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"),
			".credentials/sheets.googleapis.com-java");

	/** Global instance of the {@link FileDataStoreFactory}. */
	private static FileDataStoreFactory DATA_STORE_FACTORY;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private static HttpTransport HTTP_TRANSPORT;

	/**
	 * Global instance of the scopes required by this class.
	 *
	 * If modifying these scopes, delete your previously saved credentials at
	 * ~/.credentials/sheets.googleapis.com-java
	 */
	private static final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS_READONLY);
	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Creates an authorized Credential object.
	 * 
	 * @return an authorized Credential object.
	 * @throws Exception
	 */
	public static Credential authorize() throws Exception {
		// Load client secrets.
		InputStream in = new FileInputStream("/home/bridgeit/client_secret.json");

		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES).setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline").build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
		System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
		return credential;
	}

	/**
	 * Build and return an authorized Sheets API client service.
	 * 
	 * @return an authorized Sheets API client service
	 * @throws Exception
	 */
	public static Sheets getSheetsService() throws Exception {
		Credential credential = authorize();
		return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME)
				.build();
	}

	public String mKey;
	public String mURL;

	/**
	 * get the all details from database based on email.
	 * 
	 * @return jsp page.
	 * @param String
	 */
	@RequestMapping(value = "/sheetList", method = RequestMethod.GET)
	public ModelAndView displaySheetList(@RequestParam("email") String email) {

		List<SheetModel> sheetinfo = mSheetService.displayAllDetails(email);
		return new ModelAndView("enggDetails", "sheetinfo", sheetinfo);

	}

	/**
	 * get the all details from database.
	 * 
	 * @return display jsp page.
	 *
	 */

	@RequestMapping(value = "/sheetDetails", method = RequestMethod.GET)
	public ModelAndView displaySheetDetails(Model model) {

		List<SheetModel> sheetinfo = mSheetService.displayAllData();
	//	System.out.println(sheetinfo.get(1));
		model.addAttribute("name","DHM");
		return new ModelAndView("display", "sheetDetails", sheetinfo);

	}

	@RequestMapping(value = "/sendmessage", method = RequestMethod.GET)
	public ModelAndView readSheetData() throws Exception {

		mPublisher.sendMessage();
		return new ModelAndView("success");

	}
	@RequestMapping(value = "/getSuggestion", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody void getSuggestion(HttpServletResponse response, HttpServletRequest request) {

		String term = request.getParameter("term");
		System.out.println("term" + term);

		response.setContentType("application/json");
		try {

			String searchList = new Gson().toJson(mSheetService.getSuggestion(term));

			System.out.println("search List::-" + searchList);
			response.getWriter().write(searchList);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	@RequestMapping(value="getSearchNameData",method=RequestMethod.POST)
	public ModelAndView getSearchNameData(@RequestParam("name") String pName){
		List<SheetModel> searchData = mSheetService.getSearchNameData(pName);
		return new ModelAndView("searchDetails", "searchData", searchData);
	}
	/**
	 * get the all data from sheet and store it in a database.
	 * 
	 * @return display jsp page.
	 *
	 */
	@Scheduled(fixedDelay = 5000)
	@RequestMapping(value = "/addData", method = RequestMethod.GET)
	public ModelAndView getSheetData() throws Exception {
		int lId_Col = 0;
		System.out.println("schedule method");
		// Build a new authorized API client service.
		SheetModel sheetmodel = new SheetModel();
		Sheets service = getSheetsService();
		String lSpreadsheetId = "1CZPzCBwp3-SviyvmTvgsIcLotvAzuG_H5rFYzQ3aQFE";
		/* String range = "MyTestSheet!A1:AZ2000"; */
		String lRange = "Sheet1!A1:AZ2000";

		ValueRange response = service.spreadsheets().values().get(lSpreadsheetId, lRange).execute();
		List<List<Object>> lRows = response.getValues();
		/* List<Object> lRowHeader = lRows.get(lId_Col); */
		/*
		 * adding data sql database
		 */
		for (int i = 1; i < lRows.size(); i++) {
			// System.out.println(rows.size());
			if (lRows != null && lRows.size() > 0) {

				// getting frst row
				// it returns particular row
				List<Object> lRow = lRows.get(i);
				String timeStamp = (String) lRow.get(0);
				SheetModel sheetModel = mSheetService.timeStampExist(timeStamp);
				if (sheetModel == null) {
					sheetmodel.setTimestamp((String) lRow.get(0));
					sheetmodel.setName((String) lRow.get(1));
					sheetmodel.setEmail((String) lRow.get(2));
					sheetmodel.setCompany((String) lRow.get(3));
					sheetmodel.setProfilePicture((String) lRow.get(4));
					sheetmodel.setHiringCity((String) lRow.get(5));
					sheetmodel.setCompanyLocation((String) lRow.get(6));
					sheetmodel.setEnggStatus((String) lRow.get(7));
					sheetmodel.setDomain((String) lRow.get(8));
					mSheetService.addSheetData(sheetmodel);
				} else {
					continue;
				}
			}
		}
		return new ModelAndView("display");

	}

}
