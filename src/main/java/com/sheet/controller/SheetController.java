package com.sheet.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
import com.sheet.dao.SheetDao;
import com.sheet.dao.impl.SheetFireBaseDao;
import com.sheet.model.SheetModel;
import com.sheet.services.SheetService;

@Controller
/*@EnableScheduling*/
public class SheetController {
	@Autowired
	SheetService sheetService;

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

	public  String mKey;
	public  String mURL;

	/*
	 * public void getAndStore() { try { getSheetData(); } catch (Exception e) {
	 * // TODO Auto-generated catch block e.printStackTrace(); } }
	 */
	
	
	@RequestMapping(value = "/sheetDetails", method = RequestMethod.GET)
	public ModelAndView displaySheetDetails(Model model) {

		List<SheetModel> sheetinfo = sheetService.displayAllData();
		return new ModelAndView("display","sheetDetails",sheetinfo);

	}
	/*@Scheduled(fixedDelay=5000)*/
	@RequestMapping(value ="/addData", method = RequestMethod.GET)
	private ModelAndView getSheetData() throws Exception {
		int lId_Col = 1;
		System.out.println("schedule method");
		// Build a new authorized API client service.
		SheetModel sheetmodel = new SheetModel();
		Sheets service = getSheetsService();
		String lSpreadsheetId = "1CZPzCBwp3-SviyvmTvgsIcLotvAzuG_H5rFYzQ3aQFE";
		/* String range = "MyTestSheet!A1:AZ2000"; */
		String lRange = "Sheet1!A1:AZ2000";

		ValueRange response = service.spreadsheets().values().get(lSpreadsheetId, lRange).execute();
		List<List<Object>> lRows = response.getValues();
		/*
		 * adding data sql database
		 */
		for (int i = 1; i < lRows.size(); i++) {
			// System.out.println(rows.size());
			if (lRows != null && lRows.size() > 0) {

				
				// getting frst row
				List<Object> lRowHeader = lRows.get(i);
				// it returns particular row
				List<Object> lRow = lRows.get(i);
				/*SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy MM:hh:ss");
				Date date = sdf.parse((String) lRow.get(0));
				java.sql.Date sqldate=new Date(date.getTime());*/
 
				sheetmodel.setTimestamp((String)lRow.get(0));
				
				sheetmodel.setName((String) lRow.get(1));
				sheetmodel.setEmail((String) lRow.get(2));
				sheetmodel.setCompany((String) lRow.get(3));
        		Document doc = Jsoup.connect((String) lRow.get(4)). ignoreHttpErrors(true).userAgent("Mozilla/5.0").timeout(5000).get();
            	Element lLinks = doc.getElementsByClass("photoContainer").first();
                Element lImgTags = lLinks.getElementsByTag("img").get(0);
         	    String src = lImgTags.attr("src");
				sheetmodel.setFaceBookURL(src);
				sheetService.addSheetData(sheetmodel);
			}
		}
		
		/**
		 * adding data to firebase
		 */
		 if ( lRows != null && lRows.size() > 0 ) {
	        	
	        	//getting frst row
	        	List<Object> lRowHeader =  lRows.get(0);
	        	for (int lRowNo = 1;  lRowNo < lRows.size(); lRowNo++) {
	        		
	        		//it returns particular row
	        		List<Object> lRow =  lRows.get(lRowNo);
	        		Map<String, String> map = new HashMap<String, String>();
	        		
	        		//it returns column of that particular row
	        		for (int lColNo = 0; lColNo < lRow.size(); lColNo++) {
	        			map.put((String)lRowHeader.get(lColNo), (String)lRow.get( lColNo ));
	        			
	        		}
	        		// facebook profile picture url fetching
	        		mURL=map.get("FaceBookURL");
	        		System.out.println(mURL);
	        		mKey=map.get(  lRowHeader.get(lId_Col) );
	        		byte[] lImage = null;
	        		Document doc = Jsoup.connect(mURL). ignoreHttpErrors(true).userAgent("Mozilla/5.0").timeout(5000).get();
	            	Element lLinks = doc.getElementsByClass("photoContainer").first();
	                Element lImgTags = lLinks.getElementsByTag("img").get(0);
	         	    String src = lImgTags.attr("src");
	         	 //   System.out.println(src);
	        		lImage = IOUtils.toByteArray(src);
	        		
	        		// converting to base64 image
	        		String lImageFile = Base64.getEncoder().encodeToString(lImage);
	        		map.put("image", lImageFile);
	        		map.remove("Name");
	        		SheetFireBaseDao.saveDataToFireBase( mKey , map );
	        	}
	}
		 return new ModelAndView("display");
	}
}
