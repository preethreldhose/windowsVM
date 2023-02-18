package e2eTestingPWC_AID;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.specification.RequestSpecification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import e2eTestingPWC_AID.utils.handleMySQL;
import e2eTestingPWC_AID.utils.readCSVviaBufReader;

import e2eTestingPWC_AID.pojo.elementsOfData;
import e2eTestingPWC_AID.pojo.parentData;

import org.apache.log4j.Logger;

public class e2eCodePWCAddtl {

	static Logger logger = Logger.getLogger(XmlClass.class); 
	
	
	public static String builderJsonName = null;
	public static ArrayList<String> collectionBuilderJsonName = new ArrayList<String>();
	public static Map<String, String> mapBuilderJsonName = new TreeMap<String, String>();
	public static ArrayList<String> newReq35TestIDs = new ArrayList<String>();
	
	
	
	public static Map<String, String> testRunIDLocalDBBuilder = new TreeMap<String, String>();
	
	public static Map<String, String> testRunIDLocalDBBuilderLatest = new TreeMap<String, String>();
	
	public static ArrayList<String> arListReturn = new ArrayList<String>();
	public static ArrayList<String> apiARListReturn = new ArrayList<String>();
	
	static String builderJsonLink = null;
	String builderJsonId = null;
	String builderJsonUserId = null;
	
	String builderJsonCreatedTime = null;
	String builderJsonEndedTime = null;
	
	String builderJsonlabelId = null;
	String builderJsonlabelName = null;
	String builderJsonSamples = null;
	String builderJsonavgResponseTime = null;
	
	String builderJsonavgThroughtPut = null;
	String builderJson90line = null;
	String builderJson95line = null;
	String builderJsonMinResponseTime = null;
	String builderJsonMaxResponseTime = null;
	String builderJsonavgBytes = null;
	String builderJsonErrorRate = null;
	
	String[] builderArry = new String[11];
	
	String test_RunID = null;
	TreeMap<String, String> tm = new TreeMap<String, String>();
	static TreeMap<String, String> tmGetFiles = new TreeMap<String, String>();
	TreeMap<String, String> tmGetTestStatictics = new TreeMap<String, String>();
	
	/////
	TreeMap<String, String> tmGetTestStaticticsMultiTest = new TreeMap<String, String>();
	/////
	
	TreeMap<String, String> tmRequestTestStatistics = new TreeMap<String, String>();
	static handleMySQL st = new handleMySQL();
	static readCSVviaBufReader readCSV = new readCSVviaBufReader();
	java.util.Properties config = new java.util.Properties();
	static ArrayList<String> aListFilesJmeter = new ArrayList<String>();
	static ArrayList<String> aListDirectoriesJmeter = new ArrayList<String>();
	static ArrayList<String> aListFilesLoadRunner = new ArrayList<String>();
	static ArrayList<String> aListDirectoriesLoadRunner = new ArrayList<String>();	
	static ArrayList<String> aListDirectoriesLoadRunnerFinal = new ArrayList<String>();
	static ArrayList<String> aListFilesBlazeMeter = new ArrayList<String>();
	static ArrayList<String> aListDirectoriesBlazeMeter = new ArrayList<String>();
	
	public static Properties prop;
	
	
	public void getFilesForTestID_Original_TestA() {
		
		logger.info("\n");
		logger.info("Test");
		logger.info("\n");
		
		//RequestSpecification httpRequest = RestAssured.given().auth().basic("2bd25672e30748acc49bf96e",
		//		"2b1e5d9d05374675540463331561bf4cc8cc690282933020c21c90ef65ed3f98818cfe65");
		
		RequestSpecification httpRequest = RestAssured.given().auth().basic("2bd25672e30748acc49bf96e",
				"8ba55e8f8d564024a7cb8b8841c7f4e9a5479d9f69334fc6cc0296d0be254659bcf8e446");		
		
		String[] allTestIDs = readCSV.findTestIDs();

		for (String testIDvalue : allTestIDs) {
			System.out.println("testIDvalue : " + testIDvalue);

			try {

				Response response = httpRequest.get("https://a.blazemeter.com/api/v4/tests/" + testIDvalue + "/files");
				ResponseBody body = response.body();

				JSONObject jsonObj = new JSONObject(response.prettyPrint());
				response.prettyPrint();
				String responseData = response.asString();
				int code = response.getStatusCode();

				JsonPath json = response.jsonPath();
				String builderJson = json.get("result").toString();
				builderJson = json.get("result.name[0]").toString();

				List values = json.getList("result");
				int sizeValue = values.size();
				System.out.println("Size of the Json array is as follows : " + sizeValue);

				for (int i = 0; i < sizeValue; i++) {
					System.out.println();
					builderJsonName = json.get("result.name[" + i + "]").toString();
					System.out.println("builderJsonName : " + builderJsonName);

					builderJsonLink = json.get("result.link[" + i + "]").toString();
					System.out.println("builderJsonLink : " + builderJsonLink);

					tmGetFiles.put(builderJsonName, builderJsonLink);
					System.out.println();

					// st.insertInToGetFilesForTestID(testIDvalue, tmGetFiles);

				}

				System.out.println("outside builderJsonName : " + builderJsonName);
				System.out.println("outside builderJsonLink : " + builderJsonLink);
				st.insertInToGetFilesForTestID(testIDvalue, tmGetFiles);
				st.selectFromGetFilesForTestID();

				try {
					AssertJUnit.assertEquals(code, 200);
				} catch (Exception e) {
					System.out.println("Exception of Test1 getFilesForTestID_TestA : " + e);
				}

			} catch (Exception e) {

				System.out.println("Exception : " + e);
				
			}

		}

	}

	
	public void getTestStaticticsForTestID() {
		
		//RequestSpecification httpRequest = RestAssured.given().auth().basic("2bd25672e30748acc49bf96e",
		//		"2b1e5d9d05374675540463331561bf4cc8cc690282933020c21c90ef65ed3f98818cfe65");
		
		RequestSpecification httpRequest = RestAssured.given().auth().basic("2bd25672e30748acc49bf96e",
				"8ba55e8f8d564024a7cb8b8841c7f4e9a5479d9f69334fc6cc0296d0be254659bcf8e446");

		try {
			
				Response response = httpRequest.get("https://a.blazemeter.com/api/v4/masters?testId=11007272");
				ResponseBody body = response.body();
		
				JSONObject jsonObj = new JSONObject(response.prettyPrint());
				response.prettyPrint();
				String responseData = response.asString();
				int code = response.getStatusCode();
		
				JsonPath json = response.jsonPath();
				String builderJson = json.get("result").toString();
		
				List values = json.getList("result");
				int sizeValue = values.size();
				System.out.println("Size of the Json array is as follows : " + sizeValue);
		
				for (int i = 0; i < sizeValue; i++) {
					System.out.println();
		
					builderJsonId = json.get("result.id[" + i + "]").toString();
					System.out.println("builderJsonId : " + builderJsonId);
		
					builderJsonName = json.get("result.name[" + i + "]").toString();
					System.out.println("builderJsonName : " + builderJsonName);
		
					builderJsonUserId = json.get("result.userId[" + i + "]").toString();
					System.out.println("builderJsonUserId : " + builderJsonUserId);
		
					tmGetTestStatictics.put(builderJsonName, builderJsonUserId);
		
					System.out.println();
		
					st.insertInToGetTestStaticticsForTestID(builderJsonId, tmGetTestStatictics);
		
				}
		
				st.selectFromGetFilesForTestID();
		
				try {
					AssertJUnit.assertEquals(code, 200);
				} catch (Exception e) {
					System.out.println("Exception of Test1 getTestStaticticsForTestID : " + e);
				}
				
		} catch(Exception e) {
			System.out.println("Exception : " + e);
		}

	}

	
	public void RequestTestStatisticsForTestID() {
		
		//RequestSpecification httpRequest = RestAssured.given().auth().basic("2bd25672e30748acc49bf96e",
		//		"2b1e5d9d05374675540463331561bf4cc8cc690282933020c21c90ef65ed3f98818cfe65");
		
		RequestSpecification httpRequest = RestAssured.given().auth().basic("2bd25672e30748acc49bf96e",
				"8ba55e8f8d564024a7cb8b8841c7f4e9a5479d9f69334fc6cc0296d0be254659bcf8e446");
		
		try {
				test_RunID = "62788451";
				Response response = httpRequest
						.get("https://a.blazemeter.com/api/v4/masters/62788451/reports/aggregatereport/data");
				ResponseBody body = response.body();
		
				JSONObject jsonObj = new JSONObject(response.prettyPrint());
				response.prettyPrint();
				String responseData = response.asString();
				int code = response.getStatusCode();
		
				JsonPath json = response.jsonPath();
				String builderJson = json.get("result").toString();
		
				List values = json.getList("result");
				int sizeValue = values.size();
				System.out.println("Size of the Json array is as follows : " + sizeValue);
		
				for (int i = 0; i < sizeValue; i++) {
					System.out.println();
		
					builderJsonlabelId = json.get("result.labelId[" + i + "]").toString();
					System.out.println("Final all builderJsonlabelId : " + builderJsonlabelId);
		
					builderJsonSamples = json.get("result.samples[" + i + "]").toString();
					System.out.println("Final all builderJsonSamples : " + builderJsonSamples);
		
					builderJsonavgResponseTime = json.get("result.avgResponseTime[" + i + "]").toString();
					System.out.println("Final all builderJsonUserId : " + builderJsonavgResponseTime);
		
					tmRequestTestStatistics.put(builderJsonSamples, builderJsonavgResponseTime);
		
					System.out.println();
				}
		
				st.insertInToRequestTestStatisticsForTestID(test_RunID, builderJsonlabelId, tmRequestTestStatistics);
				st.selectFromGetFilesForTestID();
		
				try {
		
					AssertJUnit.assertEquals(code, 200);
		
				} catch (Exception e) {
		
					System.out.println("Exception of Test1 getTestStaticticsForTestID : " + e);
		
				}
		} catch (Exception e) {
			System.out.println("Exception : " + e);
		}

	}

	
	public static void jmeterCSVTest() {

		try {
			readCSV.readDomainNTestIDs(
					"/Users/preetheldhose/Desktop/Bismillah/Projects/PWC/csvFiles/Domain_BusinessUnit_App_testids_mappingLatest.csv");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	
	public static void loadRunnerCSVTest() {

		try {
			readCSV.readDomainNTestIDsLoadR(
					"/Users/preetheldhose/Desktop/Bismillah/Projects/PWC/csvFiles/Domain_BusinessUnit_App_testids_mappingLatest.csv");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static void readFromDirectory() throws IOException {

		prop = new Properties();
		prop.load(new FileInputStream("./Configuration/configuration"));

		// Creating a File object for directory

		String moveFilename = null;
		String directoryPathAddtl = null;
		String directoryPathRenameAddtl = null;

		directoryPathAddtl = prop.getProperty("directoryPath");
		directoryPathRenameAddtl = prop.getProperty("directoryPathRename");

		File directoryPath = new File(directoryPathAddtl);
		String directoryPathRename = directoryPathRenameAddtl;

		// List of all files and directories
		File filesList[] = directoryPath.listFiles();
		System.out.println("List of files and directories in the specified directory:");
		for (File file : filesList) {
			// checkCsvFile = file.
			if (file.getName().contains(".csv")) {
				System.out.println("File name: " + file.getName());
				System.out.println("File path: " + file.getAbsolutePath());
				System.out.println("Size :" + file.getTotalSpace());
				System.out.println(" ");
				readCSV.readDomainNTestIDs(file.getAbsolutePath());
				moveFilename = directoryPathRename + file.getName() + "-old";
				System.out.println("moveFilename : " + moveFilename);

				try {

					String[] cliCommandList = { "cp", file.getAbsolutePath(), moveFilename };

					for (String CommandList : cliCommandList) {
						System.out.println("CommandList : " + CommandList);
					}
					cmdTest(cliCommandList);
				} catch (Exception e) {
					System.out.println("Exception : " + e);
				}

			} else {

				System.out.println("Not a csv file: " + file.getName());

			}

		}

	}

	public void runJmeter() throws FileNotFoundException, IOException, ParseException {

		prop = new Properties();
		prop.load(new FileInputStream("./Configuration/configuration"));
		
		String directoryPathValue = null;
		directoryPathValue = prop.getProperty("root_jmeter");
		
		File directoryPath = new File(directoryPathValue);

		String subDirectory = null;
		int level = 4;

		// List of all files and directories
		File filesList[] = directoryPath.listFiles();
		System.out.println("List of files and directories in the specified directory:");
		for (File file : filesList) {
			if (!file.getName().contains(".DS_Store")) {
				recursiveFinalJmeter(filesList, level);
			} else {
				System.out.println("There are no subdirectories");
			}
		}
		
		int sizeALFiles = aListFilesJmeter.size();
		for(int i = 0; i < sizeALFiles; i++) {
			System.out.println("Values are as follows for files Jmeter: " + aListFilesJmeter.get(i));
		}
		
		int sizeALDirectories = aListDirectoriesJmeter.size();
		for(int i = 0; i < sizeALDirectories; i++) {
			System.out.println("Values are as follows for directories Jmeter: " + aListDirectoriesJmeter.get(i));
		}
		

		String[] splitFilePathJmeter = null;
		String[] jmeterSplit1 = null;
		String[] jmeterSplit2 = null;
		String jmeterstartTime = null;
		String[] preConvertJmeterstartTime = new String[6];
		sizeALFiles = aListFilesJmeter.size();
		for(int i = 0; i < sizeALFiles; i++) {
			System.out.println("Values are as follows for files Jmeter: " + aListFilesJmeter.get(i));
			splitFilePathJmeter = aListFilesJmeter.get(i).split("/");
			jmeterSplit1 = splitFilePathJmeter[14].split("\\.");
			jmeterSplit2 = jmeterSplit1[0].split("_");
			jmeterstartTime = jmeterSplit2[1]+"_"+jmeterSplit2[2];
			
			//readCSV.parseJMeterFiles(aListDirectoriesJmeter, aListFilesJmeter.get(i),jmeterstartTime);
			
			System.out.println("jmeterstartTime : " + jmeterstartTime);
			preConvertJmeterstartTime[0] =  jmeterSplit2[1].substring(4,8);
			System.out.println("year : " + preConvertJmeterstartTime[0]);
			
			preConvertJmeterstartTime[1] =  jmeterSplit2[1].substring(0,2);
			System.out.println("month : " + preConvertJmeterstartTime[1]);
			
			preConvertJmeterstartTime[2] =  jmeterSplit2[1].substring(2,4);
			System.out.println("day : " + preConvertJmeterstartTime[2]);
			
			preConvertJmeterstartTime[3] =  jmeterSplit2[2].substring(0,2);
			System.out.println("hour : " + preConvertJmeterstartTime[3]);
			
			preConvertJmeterstartTime[4] =  jmeterSplit2[2].substring(2,4);
			System.out.println("minute : " + preConvertJmeterstartTime[4]);
			
			preConvertJmeterstartTime[5] =  jmeterSplit2[2].substring(4,6);
			System.out.println("second : " + preConvertJmeterstartTime[5]);
			
			//String myDate = "2022/07/15 18:10:45";
			
			String myDate = preConvertJmeterstartTime[0]+"/"+preConvertJmeterstartTime[1]+"/"+preConvertJmeterstartTime[2]+" "+preConvertJmeterstartTime[3]+":"+preConvertJmeterstartTime[4]+":"+preConvertJmeterstartTime[5];
			System.out.println("myDate : " + myDate);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date datey = sdf.parse(myDate);
			long millis = datey.getTime();
			System.out.println(millis);
			
			System.out.println("Before millis : " + millis);
			
			long longnum = millis/1000;
	        System.out.println(longnum);

	        // explicit type casting from long to int
	        int intnumJmeterstartTime = (int)longnum;
	        System.out.println(intnumJmeterstartTime);
				
			System.out.println("After jmeterstartTime : " + intnumJmeterstartTime);
			
			logger.info("Jmeter Section => Value of jmeterstartTime inside runJmeter method : " + intnumJmeterstartTime);
			
			//readCSV.parseJMeterFiles(aListDirectoriesJmeter, aListFilesJmeter.get(i),jmeterstartTime);
			readCSV.parseJMeterFiles(aListDirectoriesJmeter, aListFilesJmeter.get(i),intnumJmeterstartTime);
			
		}
		
		
		//aListFilesJmeter = null;
		//int flagValue = 1;
		int flagValue = 0;
		if(flagValue == 0) {
			System.out.println("aListFilesJmeter details : " + aListFilesJmeter);
			int size_aListFilesJmeter = aListFilesJmeter.size();
			for(int fileCount = 0; fileCount < size_aListFilesJmeter; fileCount++) {
				System.out.println("aListFilesJmeter values : " + aListFilesJmeter.get(fileCount));
				try {
					String[] cliCommandList = { "cp", aListFilesJmeter.get(fileCount), aListFilesJmeter.get(fileCount)+"_old" };
					for (String CommandList : cliCommandList) {
						System.out.println("CommandList : " + CommandList);
					}
					cmdTest(cliCommandList);
				} catch (Exception e) {
					System.out.println("Exception : " + e);
				}
			}
		}

	
		//aListDirectoriesJmeter = null;
		
	}
	
	
	public void runJmeterEnhanced() throws FileNotFoundException, IOException, ParseException {

		prop = new Properties();
		prop.load(new FileInputStream("./Configuration/configuration"));
		
		String directoryPathValue = null;
		directoryPathValue = prop.getProperty("root_jmeter");
		
		File directoryPath = new File(directoryPathValue);

		String subDirectory = null;
		int level = 4;

		// List of all files and directories
		File filesList[] = directoryPath.listFiles();
		System.out.println("List of files and directories in the specified directory:");
		for (File file : filesList) {
			if (!file.getName().contains(".DS_Store")) {
				recursiveFinalJmeter(filesList, level);
			} else {
				System.out.println("There are no subdirectories");
			}
		}
		
		int sizeALFiles = aListFilesJmeter.size();
		for(int i = 0; i < sizeALFiles; i++) {
			System.out.println("Values are as follows for files Jmeter: " + aListFilesJmeter.get(i));
		}
		
		int sizeALDirectories = aListDirectoriesJmeter.size();
		for(int i = 0; i < sizeALDirectories; i++) {
			System.out.println("Values are as follows for directories Jmeter: " + aListDirectoriesJmeter.get(i));
		}
		

		String[] splitFilePathJmeter = null;
		String[] jmeterSplit1 = null;
		String[] jmeterSplit2 = null;
		String jmeterstartTime = null;
		String[] preConvertJmeterstartTime = new String[6];
		sizeALFiles = aListFilesJmeter.size();
		for(int i = 0; i < sizeALFiles; i++) {
			System.out.println("Values are as follows for files Jmeter: " + aListFilesJmeter.get(i));
			splitFilePathJmeter = aListFilesJmeter.get(i).split("/");
			jmeterSplit1 = splitFilePathJmeter[14].split("\\.");
			jmeterSplit2 = jmeterSplit1[0].split("_");
			jmeterstartTime = jmeterSplit2[1]+"_"+jmeterSplit2[2];
			
			//readCSV.parseJMeterFiles(aListDirectoriesJmeter, aListFilesJmeter.get(i),jmeterstartTime);
			
			System.out.println("jmeterstartTime : " + jmeterstartTime);
			preConvertJmeterstartTime[0] =  jmeterSplit2[1].substring(4,8);
			System.out.println("year : " + preConvertJmeterstartTime[0]);
			
			preConvertJmeterstartTime[1] =  jmeterSplit2[1].substring(0,2);
			System.out.println("month : " + preConvertJmeterstartTime[1]);
			
			preConvertJmeterstartTime[2] =  jmeterSplit2[1].substring(2,4);
			System.out.println("day : " + preConvertJmeterstartTime[2]);
			
			preConvertJmeterstartTime[3] =  jmeterSplit2[2].substring(0,2);
			System.out.println("hour : " + preConvertJmeterstartTime[3]);
			
			preConvertJmeterstartTime[4] =  jmeterSplit2[2].substring(2,4);
			System.out.println("minute : " + preConvertJmeterstartTime[4]);
			
			preConvertJmeterstartTime[5] =  jmeterSplit2[2].substring(4,6);
			System.out.println("second : " + preConvertJmeterstartTime[5]);
			
			//String myDate = "2022/07/15 18:10:45";
			
			String myDate = preConvertJmeterstartTime[0]+"/"+preConvertJmeterstartTime[1]+"/"+preConvertJmeterstartTime[2]+" "+preConvertJmeterstartTime[3]+":"+preConvertJmeterstartTime[4]+":"+preConvertJmeterstartTime[5];
			System.out.println("myDate : " + myDate);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date datey = sdf.parse(myDate);
			long millis = datey.getTime();
			System.out.println(millis);
			
			System.out.println("Before millis : " + millis);
			
			long longnum = millis/1000;
	        System.out.println(longnum);

	        // explicit type casting from long to int
	        int intnumJmeterstartTime = (int)longnum;
	        System.out.println(intnumJmeterstartTime);
				
			System.out.println("After jmeterstartTime : " + intnumJmeterstartTime);
			
			logger.info("Jmeter Section => Value of jmeterstartTime inside runJmeter method : " + intnumJmeterstartTime);
			
			//readCSV.parseJMeterFiles(aListDirectoriesJmeter, aListFilesJmeter.get(i),jmeterstartTime);
			//readCSV.parseJMeterFiles(aListDirectoriesJmeter, aListFilesJmeter.get(i),intnumJmeterstartTime);
			
			readCSV.parseJMeterFilesEnhanced(aListDirectoriesJmeter, aListFilesJmeter.get(i),intnumJmeterstartTime);
			
		}
		
		
		//aListFilesJmeter = null;
		//int flagValue = 1;
		int flagValue = 0;
		if(flagValue == 0) {
			System.out.println("aListFilesJmeter details : " + aListFilesJmeter);
			int size_aListFilesJmeter = aListFilesJmeter.size();
			for(int fileCount = 0; fileCount < size_aListFilesJmeter; fileCount++) {
				System.out.println("aListFilesJmeter values : " + aListFilesJmeter.get(fileCount));
				try {
					String[] cliCommandList = { "cp", aListFilesJmeter.get(fileCount), aListFilesJmeter.get(fileCount)+"_old" };
					for (String CommandList : cliCommandList) {
						System.out.println("CommandList : " + CommandList);
					}
					cmdTest(cliCommandList);
				} catch (Exception e) {
					System.out.println("Exception : " + e);
				}
			}
		}

	
		//aListDirectoriesJmeter = null;
		
	}

	
	public void runLoadRunner() throws FileNotFoundException, IOException, ParseException {
		
		prop = new Properties();
		prop.load(new FileInputStream("./Configuration/configuration"));
		
		String directoryPathValue = null;
		directoryPathValue = prop.getProperty("root_loadrunner");
		
		File directoryPath = new File(directoryPathValue);

		String subDirectory = null;
		int level = 4;

		// List of all files and directories
		File filesList[] = directoryPath.listFiles();
		System.out.println("List of files and directories in the specified directory:");
		for (File file : filesList) {
			if (!file.getName().contains(".DS_Store")) {
				
				//recursiveFinalLoadRunner(filesList, level);
				
			} else {
				System.out.println("There are no subdirectories");
			}
		}
		
		
		int sizeALFiles = aListFilesLoadRunner.size();
		for(int i = 0; i < sizeALFiles; i++) {
			System.out.println("Values are as follows for files LoadRunner: " + aListFilesLoadRunner.get(i));
		}
		
		int sizeALDirectories = aListDirectoriesLoadRunner.size();
		for(int i = 0; i < sizeALDirectories; i++) {
			System.out.println("Values are as follows for directories LoadRunner: " + aListDirectoriesLoadRunner.get(i));
		}
		
		
		/////
		String[] loadRunnerSplit1 = null;
		String[] loadRunnerSplit2 = null;
		String[] loadRunnerSplit3 = null;
		String removeRunNo = null;
		String loadRunnerstartTime = null;
		sizeALFiles = aListFilesLoadRunner.size();
		for(int i = 0; i < sizeALFiles; i++) {
			System.out.println("Values are as follows for files LoadRunner: " + aListFilesLoadRunner.get(i));

			
			loadRunnerSplit1 = aListFilesLoadRunner.get(i).split("/");
			loadRunnerSplit2 = loadRunnerSplit1[14].split("\\.");
			System.out.println("loadRunnerSplit2 : " + loadRunnerSplit2[0]);
			loadRunnerSplit3 = loadRunnerSplit2[0].split("_");
			
			//int checker = 0;
			int checker = 1;
			
			
			if (checker == 0) {
				
				
				removeRunNo = loadRunnerSplit3[1];
				loadRunnerstartTime = removeRunNo.substring(0, removeRunNo.length() - 4);
				System.out.println("loadRunnerstartTime : " + loadRunnerstartTime);
				//readCSV.parseLoadRunnerFiles(aListDirectoriesLoadRunner, aListFilesLoadRunner.get(i), loadRunnerstartTime);
				
				String[] preConvertLoadRunnerstartTime = new String[6];
				String placeHolder = "070101";
				
				System.out.println("loadRunnerstartTime : " + loadRunnerstartTime);
				preConvertLoadRunnerstartTime[0] =  loadRunnerstartTime.substring(4,8);
				System.out.println("year : " + preConvertLoadRunnerstartTime[0]);
				
				preConvertLoadRunnerstartTime[1] =  loadRunnerstartTime.substring(0,2);
				System.out.println("month : " + preConvertLoadRunnerstartTime[1]);
				
				preConvertLoadRunnerstartTime[2] =  loadRunnerstartTime.substring(2,4);
				System.out.println("day : " + preConvertLoadRunnerstartTime[2]);
				
				preConvertLoadRunnerstartTime[3] =  placeHolder.substring(0,2);
				System.out.println("hour : " + preConvertLoadRunnerstartTime[3]);
				
				preConvertLoadRunnerstartTime[4] =  placeHolder.substring(2,4);
				System.out.println("minute : " + preConvertLoadRunnerstartTime[4]);
				
				preConvertLoadRunnerstartTime[5] =  placeHolder.substring(4,6);
				System.out.println("second : " + preConvertLoadRunnerstartTime[5]);
				
				//String myDate = "2022/07/15 18:10:45";
				String myDate = preConvertLoadRunnerstartTime[0]+"/"+preConvertLoadRunnerstartTime[1]+"/"+preConvertLoadRunnerstartTime[2]+" "+preConvertLoadRunnerstartTime[3]+":"+preConvertLoadRunnerstartTime[4]+":"+preConvertLoadRunnerstartTime[5];
				System.out.println("myDate : " + myDate);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date datey = sdf.parse(myDate);
				long millis = datey.getTime();
				System.out.println(millis);
				
				System.out.println("Before millis : " + millis);
				
				long longnumLoadRunner = millis/1000;
		        System.out.println(longnumLoadRunner);

		        // explicit type casting from long to int
		        int intnumLoadRunnerstartTime = (int)longnumLoadRunner;
		        System.out.println(intnumLoadRunnerstartTime);
					
				System.out.println("After LoadRunnerstartTime : " + intnumLoadRunnerstartTime);
				
				//readCSV.parseLoadRunnerFiles(aListDirectoriesLoadRunner, aListFilesLoadRunner.get(i), loadRunnerstartTime);
				//readCSV.parseLoadRunnerFilesEnhanced(aListDirectoriesLoadRunner, aListFilesLoadRunner.get(i), intnumLoadRunnerstartTime);
				
			} else if(checker == 1) {
				
				System.out.println("Do this now : ");
				System.out.println("loadRunnerSplit2 : " + loadRunnerSplit2[0]);
				loadRunnerSplit3 = loadRunnerSplit2[0].split("_");
				System.out.println("loadRunnerSplit3 : " + loadRunnerSplit3[0] + " " + loadRunnerSplit3[1] + " " + loadRunnerSplit3[2]);
				System.out.println("Do this now : ");
				
				String[] preConvertLoadRunnerstartTime = new String[6];
				
				String placeHolder = "070101";
				//yyyy/MM/dd HH:mm:ss
				
				System.out.println("loadRunnerstartTime : " + loadRunnerstartTime);
				preConvertLoadRunnerstartTime[0] =  loadRunnerSplit3[1].substring(4,8);
				System.out.println("year : " + preConvertLoadRunnerstartTime[0]);
				
				preConvertLoadRunnerstartTime[1] =  loadRunnerSplit3[1].substring(0,2);
				System.out.println("month : " + preConvertLoadRunnerstartTime[1]);
				
				preConvertLoadRunnerstartTime[2] =  loadRunnerSplit3[1].substring(2,4);
				System.out.println("day : " + preConvertLoadRunnerstartTime[2]);
				
				preConvertLoadRunnerstartTime[3] =  loadRunnerSplit3[2].substring(0,2);
				System.out.println("hour : " + preConvertLoadRunnerstartTime[3]);
				
				preConvertLoadRunnerstartTime[4] =  loadRunnerSplit3[2].substring(2,4);
				System.out.println("minute : " + preConvertLoadRunnerstartTime[4]);
				
				preConvertLoadRunnerstartTime[5] =  loadRunnerSplit3[2].substring(4,6);
				System.out.println("second : " + preConvertLoadRunnerstartTime[5]);
				
				//String myDate = "2022/07/15 18:10:45";
				String myDate = preConvertLoadRunnerstartTime[0]+"/"+preConvertLoadRunnerstartTime[1]+"/"+preConvertLoadRunnerstartTime[2]+" "+preConvertLoadRunnerstartTime[3]+":"+preConvertLoadRunnerstartTime[4]+":"+preConvertLoadRunnerstartTime[5];
				System.out.println("myDate : " + myDate);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date datey = sdf.parse(myDate);
				long millis = datey.getTime();
				System.out.println(millis);
				
				System.out.println("Before millis : " + millis);
				
				long longnumLoadRunner = millis/1000;
		        System.out.println(longnumLoadRunner);

		        // explicit type casting from long to int
		        int intnumLoadRunnerstartTime = (int)longnumLoadRunner;
		        System.out.println(intnumLoadRunnerstartTime);
					
				System.out.println("After LoadRunnerstartTime : " + intnumLoadRunnerstartTime);
				
				//readCSV.parseLoadRunnerFiles(aListDirectoriesLoadRunner, aListFilesLoadRunner.get(i), loadRunnerstartTime);
				readCSV.parseLoadRunnerFilesEnhanced(aListDirectoriesLoadRunner, aListFilesLoadRunner.get(i), intnumLoadRunnerstartTime);
				
			}

			
			
		}
		
		aListFilesLoadRunner = null;
		aListDirectoriesLoadRunner = null;
		
	}
	
	@Test
	public void runLoadRunnerEnhanced() throws FileNotFoundException, IOException, ParseException {
		
		prop = new Properties();
		prop.load(new FileInputStream("./Configuration/configuration"));
		
		String directoryPathValue = null;
		directoryPathValue = prop.getProperty("root_loadrunner");
		
		File directoryPath = new File(directoryPathValue);

		String subDirectory = null;
		int level = 4;

		// List of all files and directories
		File filesList[] = directoryPath.listFiles();
		System.out.println("List of files and directories in the specified directory:");
//		for (File file : filesList) {
//			if (!file.getName().contains(".DS_Store")) {				
//				System.out.println("filesList : " + filesList);
//				System.out.println("file : " + file);
//				recursiveFinalLoadRunner(filesList, level);
//				//recursiveFinalLoadRunner(file, level);			
//			} else {
//				System.out.println("There are no subdirectories");
//			}
//		} 
		
		recursiveFinalLoadRunner(filesList, level);
		
		int sizeALFiles = aListFilesLoadRunner.size();
		for(int i = 0; i < sizeALFiles; i++) {
			System.out.println("Values are as follows for files LoadRunner: " + aListFilesLoadRunner.get(i));
		}
		
		int sizeALDirectories = aListDirectoriesLoadRunner.size();
		for(int i = 0; i < sizeALDirectories; i++) {
			System.out.println("Values are as follows for directories LoadRunner: " + aListDirectoriesLoadRunner.get(i));
		}
		
		
		/////
		String[] loadRunnerSplit1 = null;
		String[] loadRunnerSplit2 = null;
		String[] loadRunnerSplit3 = null;
		String removeRunNo = null;
		String loadRunnerstartTime = null;
		sizeALFiles = aListFilesLoadRunner.size();
		for(int i = 0; i < sizeALFiles; i++) {
			System.out.println("Values are as follows for files LoadRunner: " + aListFilesLoadRunner.get(i));

			
					//loadRunnerSplit1 = aListFilesLoadRunner.get(i).split("/"); if non windows then this.
			
			loadRunnerSplit1 = aListFilesLoadRunner.get(i).split("\\\\");
			System.out.println("loadRunnerSplit1 : " + loadRunnerSplit1[14] );
			loadRunnerSplit2 = loadRunnerSplit1[14].split("\\.");
			System.out.println("loadRunnerSplit2 : " + loadRunnerSplit2[0]);
			loadRunnerSplit3 = loadRunnerSplit2[0].split("_");
			
			//int checker = 0;
			int checker = 1;
			
			
			if (checker == 0) {
				
				
				removeRunNo = loadRunnerSplit3[1];
				loadRunnerstartTime = removeRunNo.substring(0, removeRunNo.length() - 4);
				System.out.println("loadRunnerstartTime : " + loadRunnerstartTime);
				//readCSV.parseLoadRunnerFiles(aListDirectoriesLoadRunner, aListFilesLoadRunner.get(i), loadRunnerstartTime);
				
				String[] preConvertLoadRunnerstartTime = new String[6];
				String placeHolder = "070101";
				
				System.out.println("loadRunnerstartTime : " + loadRunnerstartTime);
				preConvertLoadRunnerstartTime[0] =  loadRunnerstartTime.substring(4,8);
				System.out.println("year : " + preConvertLoadRunnerstartTime[0]);
				
				preConvertLoadRunnerstartTime[1] =  loadRunnerstartTime.substring(0,2);
				System.out.println("month : " + preConvertLoadRunnerstartTime[1]);
				
				preConvertLoadRunnerstartTime[2] =  loadRunnerstartTime.substring(2,4);
				System.out.println("day : " + preConvertLoadRunnerstartTime[2]);
				
				preConvertLoadRunnerstartTime[3] =  placeHolder.substring(0,2);
				System.out.println("hour : " + preConvertLoadRunnerstartTime[3]);
				
				preConvertLoadRunnerstartTime[4] =  placeHolder.substring(2,4);
				System.out.println("minute : " + preConvertLoadRunnerstartTime[4]);
				
				preConvertLoadRunnerstartTime[5] =  placeHolder.substring(4,6);
				System.out.println("second : " + preConvertLoadRunnerstartTime[5]);
				
				//String myDate = "2022/07/15 18:10:45";
				String myDate = preConvertLoadRunnerstartTime[0]+"/"+preConvertLoadRunnerstartTime[1]+"/"+preConvertLoadRunnerstartTime[2]+" "+preConvertLoadRunnerstartTime[3]+":"+preConvertLoadRunnerstartTime[4]+":"+preConvertLoadRunnerstartTime[5];
				System.out.println("myDate : " + myDate);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date datey = sdf.parse(myDate);
				long millis = datey.getTime();
				System.out.println(millis);
				
				System.out.println("Before millis : " + millis);
				
				long longnumLoadRunner = millis/1000;
		        System.out.println(longnumLoadRunner);

		        // explicit type casting from long to int
		        int intnumLoadRunnerstartTime = (int)longnumLoadRunner;
		        System.out.println(intnumLoadRunnerstartTime);
					
				System.out.println("After LoadRunnerstartTime : " + intnumLoadRunnerstartTime);
				
				//readCSV.parseLoadRunnerFiles(aListDirectoriesLoadRunner, aListFilesLoadRunner.get(i), loadRunnerstartTime);
				//readCSV.parseLoadRunnerFiles(aListDirectoriesLoadRunner, aListFilesLoadRunner.get(i), intnumLoadRunnerstartTime);
				//readCSV.parseLoadRunnerFilesEnhanced(aListDirectoriesLoadRunner, aListFilesLoadRunner.get(i), intnumLoadRunnerstartTime);
				
			} else if(checker == 1) {
				
				System.out.println("Do this now : ");
				System.out.println("loadRunnerSplit2 : " + loadRunnerSplit2[0]);
				loadRunnerSplit3 = loadRunnerSplit2[0].split("_");
				System.out.println("loadRunnerSplit3 : " + loadRunnerSplit3[0] + " " + loadRunnerSplit3[1] + " " + loadRunnerSplit3[2]);
				System.out.println("Do this now : ");
				
				String[] preConvertLoadRunnerstartTime = new String[6];
				
				String placeHolder = "070101";
				//yyyy/MM/dd HH:mm:ss
				
				System.out.println("loadRunnerstartTime : " + loadRunnerstartTime);
				preConvertLoadRunnerstartTime[0] =  loadRunnerSplit3[1].substring(4,8);
				System.out.println("year : " + preConvertLoadRunnerstartTime[0]);
				
				preConvertLoadRunnerstartTime[1] =  loadRunnerSplit3[1].substring(0,2);
				System.out.println("month : " + preConvertLoadRunnerstartTime[1]);
				
				preConvertLoadRunnerstartTime[2] =  loadRunnerSplit3[1].substring(2,4);
				System.out.println("day : " + preConvertLoadRunnerstartTime[2]);
				
				preConvertLoadRunnerstartTime[3] =  loadRunnerSplit3[2].substring(0,2);
				System.out.println("hour : " + preConvertLoadRunnerstartTime[3]);
				
				preConvertLoadRunnerstartTime[4] =  loadRunnerSplit3[2].substring(2,4);
				System.out.println("minute : " + preConvertLoadRunnerstartTime[4]);
				
				preConvertLoadRunnerstartTime[5] =  loadRunnerSplit3[2].substring(4,6);
				System.out.println("second : " + preConvertLoadRunnerstartTime[5]);
				
				//String myDate = "2022/07/15 18:10:45";
				String myDate = preConvertLoadRunnerstartTime[0]+"/"+preConvertLoadRunnerstartTime[1]+"/"+preConvertLoadRunnerstartTime[2]+" "+preConvertLoadRunnerstartTime[3]+":"+preConvertLoadRunnerstartTime[4]+":"+preConvertLoadRunnerstartTime[5];
				System.out.println("myDate : " + myDate);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date datey = sdf.parse(myDate);
				long millis = datey.getTime();
				System.out.println(millis);
				
				System.out.println("Before millis : " + millis);
				
				long longnumLoadRunner = millis/1000;
		        System.out.println(longnumLoadRunner);

		        // explicit type casting from long to int
		        int intnumLoadRunnerstartTime = (int)longnumLoadRunner;
		        System.out.println(intnumLoadRunnerstartTime);
					
				System.out.println("After LoadRunnerstartTime : " + intnumLoadRunnerstartTime);
				
				//readCSV.parseLoadRunnerFiles(aListDirectoriesLoadRunner, aListFilesLoadRunner.get(i), loadRunnerstartTime);
				//readCSV.parseLoadRunnerFiles(aListDirectoriesLoadRunner, aListFilesLoadRunner.get(i), intnumLoadRunnerstartTime);
				
				//aListDirectoriesLoadRunnerFinal = null;
				String[] preAListDirectoriesLoadRunnerFinal = aListFilesLoadRunner.get(i).toString().split("\\\\");
				
				System.out.println("preAListDirectoriesLoadRunnerFinal : " + preAListDirectoriesLoadRunnerFinal[10]);
				System.out.println("preAListDirectoriesLoadRunnerFinal : " + preAListDirectoriesLoadRunnerFinal[11]);
				System.out.println("preAListDirectoriesLoadRunnerFinal : " + preAListDirectoriesLoadRunnerFinal[12]);
				System.out.println("preAListDirectoriesLoadRunnerFinal : " + preAListDirectoriesLoadRunnerFinal[13]);
				
				aListDirectoriesLoadRunnerFinal.clear();
				
				aListDirectoriesLoadRunnerFinal.add(preAListDirectoriesLoadRunnerFinal[10]);
				aListDirectoriesLoadRunnerFinal.add(preAListDirectoriesLoadRunnerFinal[11]);
				aListDirectoriesLoadRunnerFinal.add(preAListDirectoriesLoadRunnerFinal[12]);
				aListDirectoriesLoadRunnerFinal.add(preAListDirectoriesLoadRunnerFinal[13]);
				
				//System.out.println("print aListDirectoriesLoadRunner : " + aListDirectoriesLoadRunner);
				System.out.println("print aListDirectoriesLoadRunnerFinal : " + aListDirectoriesLoadRunnerFinal);
				System.out.println("print aListFilesLoadRunner.get(i) : " + aListFilesLoadRunner.get(i));
				System.out.println("print intnumLoadRunnerstartTime : " + intnumLoadRunnerstartTime);
				if( preAListDirectoriesLoadRunnerFinal[10].contains("Domain1")) {
					System.out.println("Domain is 2");
				}
				
				if( preAListDirectoriesLoadRunnerFinal[10].contains("Domain3")) {
					System.out.println("Domain is 3");
				}
				
				//readCSV.parseLoadRunnerFilesEnhanced(aListDirectoriesLoadRunner, aListFilesLoadRunner.get(i), intnumLoadRunnerstartTime);
				readCSV.parseLoadRunnerFilesEnhanced(aListDirectoriesLoadRunnerFinal, aListFilesLoadRunner.get(i), intnumLoadRunnerstartTime);

				
				
				
			}

			
			
		}
		
		aListFilesLoadRunner = null;
		aListDirectoriesLoadRunner = null;
		
	}
	
	@Test
	public void newTestAllValue() {
		System.out.println("newTestAllValue");
	}
	
	//@SuppressWarnings("null")
	//@Test
	public void runBlazeMeterEnhanced() throws FileNotFoundException, IOException {
		
		Map<String, String> storemapBuilderJsonName = new TreeMap<String, String>();
		String[] getblazemeterColumns = null;
		
		prop = new Properties();
		prop.load(new FileInputStream("./Configuration/configuration"));
		
		String directoryPathValue = null;
		directoryPathValue = prop.getProperty("root_blazemeter");
		
		File directoryPath = new File(directoryPathValue);

		String subDirectory = null;
		int level = 4;

		// List of all files and directories
		File filesList[] = directoryPath.listFiles();
		System.out.println("List of files and directories in the specified directory:");
		for (File file : filesList) {
			if (!file.getName().contains(".DS_Store")) {
				recursiveFinalBlazemeter(filesList, level);
			} else {
				System.out.println("There are no subdirectories");
			}
		}
		
		int sizeALFiles = aListFilesBlazeMeter.size();
		for(int i = 0; i < sizeALFiles; i++) {
			System.out.println("Values are as follows for files BlazeMeter: " + aListFilesBlazeMeter.get(i));
		}
		
		int sizeALDirectories = aListDirectoriesBlazeMeter.size();
		for(int i = 0; i < sizeALDirectories; i++) {
			System.out.println("Values are as follows for directories BlazeMeter: " + aListDirectoriesBlazeMeter.get(i));
		}
		
		String[] splitFilePathBlazemeter = null;
		String[] blazemeterSplit1 = null;
		String[] blazemeterSplit2 = null;
		String blazeMeterStartTime = null;
		sizeALFiles = aListFilesBlazeMeter.size();
		String os = System.getProperty("os.name");
	    System.out.println("Using System Property: " + os);
		for(int i = 0; i < sizeALFiles; i++) {
			System.out.println("Values are as follows for files Blazemeter: " + aListFilesBlazeMeter.get(i));
			if(os.contains("Windows")) {
				System.out.println("aListFilesBlazeMeter.get(i) : " + aListFilesBlazeMeter.get(i));
				splitFilePathBlazemeter = aListFilesBlazeMeter.get(i).split("\\\\");
			} else {
				splitFilePathBlazemeter = aListFilesBlazeMeter.get(i).split("/");
			}
			
			blazemeterSplit1 = splitFilePathBlazemeter[10].split("\\.");
			blazemeterSplit2 = blazemeterSplit1[0].split("_");
			System.out.println("blazemeterSplit2 : " + blazemeterSplit2);
			blazeMeterStartTime=blazemeterSplit2[4];
			
			String line = "";
			String splitBy = ",";
	
			int count = 0;
			int newReqCounter = 5;
			ArrayList<String> allTestRunnerID = null;
			String testTypeNewReq = null;
							
			try {
				//parsing a CSV file into BufferedReader class constructor  
				//BufferedReader br = new BufferedReader(new FileReader(blazemeterPath));
				
				BufferedReader br = new BufferedReader(new FileReader(aListFilesBlazeMeter.get(i)));
				while ((line = br.readLine()) != null) // returns a Boolean value
				{
					getblazemeterColumns = line.split(splitBy); // use comma as separator
					for (int y = 0; y < getblazemeterColumns.length; y++ ) {
						System.out.println("blazemeterColumn is as follows : " + getblazemeterColumns[y]);
					    count++;
					    System.out.println("Value of count is as follows : " + count);
					    //if(count > 6) {
						if(count > 35) {
							System.out.println("Value of y is as follows : " + y);
							System.out.println("getblazemeterColumns[y] is : " + getblazemeterColumns[y]);
							if( (y >= 4) && ( y < 36) ) {
								System.out.println("Values are as follows : " + getblazemeterColumns[y]);
								if(y != 34 ) {
									newReq35TestIDs.add(getblazemeterColumns[y]);
									System.out.println("newReq35TestIDs : " + newReq35TestIDs);
								} else {
									testTypeNewReq = getblazemeterColumns[y];
									System.out.println("testTypeNewReq : " + testTypeNewReq);
									newReqCounter = 100;
								}
								System.out.println("newReqCounter : " + newReqCounter);
								if( newReqCounter == 100) {
								   int newReq35TestIDSize = newReq35TestIDs.size();
								   System.out.println("newReq35TestIDSize values : " + newReq35TestIDSize);
								   System.out.println("Values of newReq35TestIDs are as follows : " + newReq35TestIDs);
								   System.out.println("check TreeMap start");
								   //Map<String, String> storemapBuilderJsonName = new TreeMap<String, String>();
								   System.out.println("Values of the storemapBuilderJsonName for the test ID : " + newReq35TestIDs);
								   System.out.println("check TreeMap end");
								   if(testTypeNewReq.contains("Y")) {
									  System.out.println("TestType [Y] value is as follows : " + testTypeNewReq);
									  System.out.println("newReq35TestIDs TestType [Y] value is as follows : " + newReq35TestIDs);
									  storemapBuilderJsonName = callTestStaticticsForTestIDMapviaMultiTest(newReq35TestIDs);
									  System.out.println("Checkthis : " + storemapBuilderJsonName);
								   } else {
									  System.out.println("TestType [N] value is as follows : " + testTypeNewReq);
									  System.out.println("newReq35TestIDs TestType [N] value is as follows : " + newReq35TestIDs);
									  storemapBuilderJsonName = callTestStaticticsForTestIDMap(newReq35TestIDs);
									  System.out.println("Checkthis : " + storemapBuilderJsonName);
								   }
								  for (Map.Entry<String, String> entry : storemapBuilderJsonName.entrySet()) {
								       String[] split_Created_Ended = new String[2];
								       String[] Pre_Ended_n_TestID_Split = new String[2];
									   System.out.println("Started");
									   System.out.println("[" + entry.getKey() + ", " + entry.getValue() + "]");
									   split_Created_Ended = entry.getValue().split("_");
									   System.out.println("Created : " + split_Created_Ended[0]);
									   System.out.println("Pre_Ended_n_TestID_Split : " + split_Created_Ended[1]);
									   Pre_Ended_n_TestID_Split = split_Created_Ended[1].split("---");
									   System.out.println("Ended : " + Pre_Ended_n_TestID_Split[0]);
									   System.out.println("TestID value : " + Pre_Ended_n_TestID_Split[1]);
									   System.out.println("Final all : ");
									   System.out.println("Values of runnerID : " + entry.getKey());
									   System.out.println();
									   System.out.println("About to call callTestStatisticsForTestID");
  									   System.out.println("Required fields : " + " " + entry.getKey() + " " + getblazemeterColumns + " " + split_Created_Ended[0] + " " + Pre_Ended_n_TestID_Split[0] + " " + Pre_Ended_n_TestID_Split[1]);
                                       //callTestStatisticsForTestIDAggEnhanced(entry.getKey(), getblazemeterColumns, split_Created_Ended[0], split_Created_Ended[1]);
  									   callTestStatisticsForTestIDAggEnhanced(entry.getKey(), getblazemeterColumns, split_Created_Ended[0], Pre_Ended_n_TestID_Split[0], Pre_Ended_n_TestID_Split[1]);
                                       System.out.println();
								  }
							           System.out.println("OutOftheLoop");
									   System.out.println("Checkthis again before clear : " + storemapBuilderJsonName);
									   storemapBuilderJsonName.clear();
									   System.out.println("Checkthis again after clear : " + storemapBuilderJsonName);
									   newReq35TestIDs.clear();
									   newReqCounter = 0;
	    							   System.out.println("Rectified values : ");
								       /////
								}
							} else {
							}
					    }
				    }
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		aListFilesBlazeMeter = null;
		aListDirectoriesBlazeMeter = null;
	}
	
	public static void callReplicaDBAPIJMeter(ArrayList<String> aDList, String[] getJmeterColumns, String baseFileTestID, int intnumJmeterstartTime) throws JsonProcessingException {

		int intjmeterstartTime = 0;
		String[] splitbaseFileTestID = new String[3];
		splitbaseFileTestID = baseFileTestID.split("_");
		int datebaseFileTestID = Integer.parseInt(splitbaseFileTestID[1]);
		int timebaseFileTestID = Integer.parseInt(splitbaseFileTestID[2]);
		System.out.println("datebaseFileTestID : " + datebaseFileTestID);
		System.out.println("timebaseFileTestID : " + timebaseFileTestID);
		
		String xbaseFileTestID = baseFileTestID.replaceAll("\"", "");
		
		
		for(int xc = 0; xc < aDList.size(); xc++) {
			System.out.println("Values of aDList : " + aDList.get(xc));
		}
		
		for(int xz = 0; xz < getJmeterColumns.length; xz++) {
			System.out.println("Values of getJmeterColumns : " + getJmeterColumns[xz]);
		}
		
		System.out.println("baseFileTestID : " + baseFileTestID);
		System.out.println("jmeterstartTime : " + intnumJmeterstartTime);
		
        logger.info("\n");
        logger.info("Jmeter Section => Value of jmeterstartTime inside replicaDB method 1st : " + intnumJmeterstartTime);
        logger.info("\n");
		

        
	
	    	String requestBody = "{\n"
	    			+ "            \"data\": [\n"
	    			+ "                {\n"
	    			+ "                    \"domain\": \"domain\",\n"
	    			+ "                    \"samples\": 77,\n"
	    			+ "                    \"maxResponseTime\": 33087,\n"
	    			+ "                    \"avgBytes\": 5.785,\n"
	    			+ "                    \"start_time\": 1653290975,\n"
	    			+ "                    \"end_time\": 1653290985,\n"
	    			+ "                    \"avgThroughput\": 0.021974885844748857,\n"
	    			+ "                    \"errorsRate\": 3.896103896103896,\n"
	    			+ "                    \"95line\": 5851,\n"
	    			+ "                    \"business_unit\": \"business_unit\",\n"
	    			+ "                    \"labelName\": \"OpenAM-Login\",\n"
	    			+ "                    \"application_name\": \"application_name\",\n"
	    			+ "                    \"minResponseTime\": 2444,\n"
	    			+ "                    \"test_id\": \"11007272\",\n"
	    			+ "                    \"test_run_id\": 62907194,\n"
	    			+ "                    \"avgResponseTime\": 4154.558441558442,\n"
	    			+ "                    \"90line\": 3479\n"
	    			+ "                }\n"
	    			+ "            ]\n"
	    			+ "}";
	    	

	    	
	    	elementsOfData objElementsOfData = new elementsOfData(
	    			"domain", 
	    			77, 
	    			33087, 
	    			5.785, 
	    			1653290975, 
	    			1653290985, 
	    			0.021974885844748857, 
	    			3.896103896103896, 
	    			5851, 
	    			"business_unit", 
	    			"OpenAM-Login", 
	    			"application_name", 
	    			2444, 
	    			11007272, 
	    			62907194, 
	    			4154.558441558442, 
	    			3479);
	    	
	    	System.out.println("objData : " + objElementsOfData.avgBytes);
	        System.out.println("Latest - starts");
	        
	        ObjectMapper objMap = new ObjectMapper();
	        String myObjElementsOfData = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(objElementsOfData);

	    	parentData finalData = new parentData(objElementsOfData);
         	String myFinalData = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(finalData);
	        
	        System.out.println("myFinalData : " + myFinalData);
	        
	        String newgetJmeterColumns = getJmeterColumns[9].replace('%', ' ');
	        System.out.println("newgetJmeterColumns  : " + newgetJmeterColumns+"uuu");
	        Float errorRate = Float.parseFloat(newgetJmeterColumns);
	        System.out.println("errorRate  : " + errorRate);
       
	        Map<String, Object> map = new LinkedHashMap<>();
	        map.put("data", Arrays.asList(new LinkedHashMap<String, Object>() {
	            {
	            	
	            	put("domain", aDList.get(0));
	            	put("samples", Integer.parseInt(getJmeterColumns[1]));
	            	put("maxResponseTime", Integer.parseInt(getJmeterColumns[8]));
	            	put("avgBytes", Float.parseFloat(getJmeterColumns[11]));
            		put("start_time", intnumJmeterstartTime);
            		put("end_time", intnumJmeterstartTime);
	            	put("avgThroughput", Float.parseFloat(getJmeterColumns[10]));
	            	put("errorsRate", errorRate);
	            	put("95line", Integer.parseInt(getJmeterColumns[5]));
	            	put("business_unit", aDList.get(1));
	            	put("labelName", getJmeterColumns[0]);
	            	put("application_name", aDList.get(2));
	            	put("minResponseTime", Integer.parseInt(getJmeterColumns[7])); //=> Int 
	            	put("test_id", aDList.get(3));
	            	put("test_run_id", baseFileTestID);
	            	put("avgResponseTime", Float.parseFloat(getJmeterColumns[2]));  //=> Float
	            	put("90line", Integer.parseInt(getJmeterColumns[4]));			//=> Int
	            	
	            }
	        }));
	        
	        String bestJSON = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(map);
	        System.out.println("bestJSON : " + bestJSON);
	        logger.info("Jmeter Section => This is what will be written to replicaDB : " + bestJSON);
	        System.out.println("");
	        
	        /*
	        
	        RestAssured.baseURI = "https://api-dev.headspin.io/v0/replica/loadtest";

	        Response response = given()
	                .header("Authorization", "Bearer 3088f16f91ee4931b195acfd46ac994d")
	                .and()
	                .body(bestJSON)
	                .when()
	                .post("/upload")
	                .then()
	                .extract().response();

			JSONObject jsonObj = new JSONObject(response.prettyPrint());
	        
	        */
	        
	        //Assert.assertEquals(200, response.statusCode());
	        
	        System.out.println("Latest - ends");
	        System.out.println("");
	        

		
	}
	
	public static void callReplicaDBAPILoadRunner(ArrayList<String> aDList, String[] getLoadRunnerColumns, String baseFileTestID, int loadRunnerstartTime) throws JsonProcessingException {
		
		for(int xc = 0; xc < aDList.size(); xc++) {
			System.out.println("Values of aDList : " + aDList.get(xc));
		}
		
		for(int xz = 0; xz < getLoadRunnerColumns.length; xz++) {
			System.out.println("Values of getLoadRunnerColumns : " + getLoadRunnerColumns[xz]);
		}
		
		System.out.println("baseFileTestID : " + baseFileTestID);
		System.out.println("loadRunnerstartTime : " + loadRunnerstartTime);
		
		
	    	String requestBody = "{\n"
	    			+ "            \"data\": [\n"
	    			+ "                {\n"
	    			+ "                    \"domain\": \"domain\",\n"
	    			+ "                    \"samples\": 77,\n"
	    			+ "                    \"maxResponseTime\": 33087,\n"
	    			+ "                    \"avgBytes\": 5.785,\n"
	    			+ "                    \"start_time\": 1653290975,\n"
	    			+ "                    \"end_time\": 1653290985,\n"
	    			+ "                    \"avgThroughput\": 0.021974885844748857,\n"
	    			+ "                    \"errorsRate\": 3.896103896103896,\n"
	    			+ "                    \"95line\": 5851,\n"
	    			+ "                    \"business_unit\": \"business_unit\",\n"
	    			+ "                    \"labelName\": \"OpenAM-Login\",\n"
	    			+ "                    \"application_name\": \"application_name\",\n"
	    			+ "                    \"minResponseTime\": 2444,\n"
	    			+ "                    \"test_id\": \"11007272\",\n"
	    			+ "                    \"test_run_id\": 62907194,\n"
	    			+ "                    \"avgResponseTime\": 4154.558441558442,\n"
	    			+ "                    \"90line\": 3479\n"
	    			+ "                }\n"
	    			+ "            ]\n"
	    			+ "}";
	    	

	    	
	    	elementsOfData objElementsOfData = new elementsOfData(
	    			"domain", 
	    			77, 
	    			33087, 
	    			5.785, 
	    			1653290975, 
	    			1653290985, 
	    			0.021974885844748857, 
	    			3.896103896103896, 
	    			5851, 
	    			"business_unit", 
	    			"OpenAM-Login", 
	    			"application_name", 
	    			2444, 
	    			11007272, 
	    			62907194, 
	    			4154.558441558442, 
	    			3479);
	    	
	    	System.out.println("objData : " + objElementsOfData.avgBytes);
	        System.out.println("Latest - starts");
	        
	        ObjectMapper objMap = new ObjectMapper();
	        String myObjElementsOfData = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(objElementsOfData);

	    	parentData finalData = new parentData(objElementsOfData);
         	String myFinalData = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(finalData);
	        
	        System.out.println("myFinalData : " + myFinalData);
       
	        float maxResponseTimeJSON = Float.parseFloat(getLoadRunnerColumns[3])*1000;
	        int maxResponseTimeJSONInt = (int) maxResponseTimeJSON;
	        
	        float minResponseTimeJSON = Float.parseFloat(getLoadRunnerColumns[1])*1000;
	        int minResponseTimeJSONInt = (int) minResponseTimeJSON;
	        
	        float avgResponseTime = Float.parseFloat(getLoadRunnerColumns[2])*1000;
	        
	        float flt90lineJSON = Float.parseFloat(getLoadRunnerColumns[5])*1000;
	        int flt90lineJSONInt = (int) flt90lineJSON;
	        
	        float avgBytes = 0.0f;
	        float avgThroughput = 0.0f;
	        float errorsRate = 0.0f;
	        int line95 = 0;
	        
	        Map<String, Object> map = new LinkedHashMap<>();
	        map.put("data", Arrays.asList(new LinkedHashMap<String, Object>() {
	            {

	            	put("domain", aDList.get(0));
	            	put("samples", Integer.parseInt(getLoadRunnerColumns[6]));
	            	put("maxResponseTime", maxResponseTimeJSONInt);
	            	//put("avgBytes", "");
	            	put("avgBytes", avgBytes);
	            	put("start_time", loadRunnerstartTime);
	            	put("end_time", loadRunnerstartTime);
	            	//put("avgThroughput", "");
	            	//put("avgThroughput", "");
	            	put("avgThroughput", avgThroughput);
	            	//put("errorsRate", "");
	            	put("errorsRate", errorsRate);
	            	//put("95line", "");
	            	put("95line", line95);
	            	put("business_unit", aDList.get(1));
	            	put("labelName", getLoadRunnerColumns[0]);
	            	put("application_name", aDList.get(2));
	            	put("minResponseTime", minResponseTimeJSONInt);
	            	put("test_id", aDList.get(3));
	            	put("test_run_id", baseFileTestID);
	            	put("avgResponseTime", Float.parseFloat(getLoadRunnerColumns[2])*1000);
	            	put("90line", flt90lineJSONInt);
	            	
	            	
	            }
	        }));
	        
	        String bestJSON = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(map);
	        System.out.println("bestJSON : " + bestJSON);
	        logger.info("loadRunner => This is what is passed to Replica DB : " + bestJSON);
	        System.out.println("Next");
  
	       
	        
//	        RestAssured.baseURI = "https://api-dev.headspin.io/v0/replica/loadtest";
//
//	        Response response = given()
//	                .header("Authorization", "Bearer 3088f16f91ee4931b195acfd46ac994d")
//	                .and()
//	                .body(bestJSON)
//	                .when()
//	                .post("/upload")
//	                .then()
//	                .extract().response();
//
//			JSONObject jsonObj = new JSONObject(response.prettyPrint());
	        
	  
	        //Assert.assertEquals(200, response.statusCode());
	        System.out.println("Latest - ends");
		
	}
	
	
	public static void callReplicaDBAPIBlazeMeter(String TestRunnerID, ArrayList strList, String[] builderArry, int intCreated, int intEnded) throws JsonProcessingException {
		
		for(int xc = 0; xc < strList.size(); xc++) {
			System.out.println("Values of strList : " + strList.get(xc));
		}
		
		for(int xz = 0; xz < builderArry.length; xz++) {
			System.out.println("Values of builderArry : " + builderArry[xz]);
		}
		

		
	    	String requestBody = "{\n"
	    			+ "            \"data\": [\n"
	    			+ "                {\n"
	    			+ "                    \"domain\": \"domain\",\n"
	    			+ "                    \"samples\": 77,\n"
	    			+ "                    \"maxResponseTime\": 33087,\n"
	    			+ "                    \"avgBytes\": 5.785,\n"
	    			+ "                    \"start_time\": 1653290975,\n"
	    			+ "                    \"end_time\": 1653290985,\n"
	    			+ "                    \"avgThroughput\": 0.021974885844748857,\n"
	    			+ "                    \"errorsRate\": 3.896103896103896,\n"
	    			+ "                    \"95line\": 5851,\n"
	    			+ "                    \"business_unit\": \"business_unit\",\n"
	    			+ "                    \"labelName\": \"OpenAM-Login\",\n"
	    			+ "                    \"application_name\": \"application_name\",\n"
	    			+ "                    \"minResponseTime\": 2444,\n"
	    			+ "                    \"test_id\": \"11007272\",\n"
	    			+ "                    \"test_run_id\": 62907194,\n"
	    			+ "                    \"avgResponseTime\": 4154.558441558442,\n"
	    			+ "                    \"90line\": 3479\n"
	    			+ "                }\n"
	    			+ "            ]\n"
	    			+ "}";
	    	

	    	
	    	elementsOfData objElementsOfData = new elementsOfData(
	    			"domain", 
	    			77, 
	    			33087, 
	    			5.785, 
	    			1653290975, 
	    			1653290985, 
	    			0.021974885844748857, 
	    			3.896103896103896, 
	    			5851, 
	    			"business_unit", 
	    			"OpenAM-Login", 
	    			"application_name", 
	    			2444, 
	    			11007272, 
	    			62907194, 
	    			4154.558441558442, 
	    			3479);
	    	
	    	System.out.println("objData : " + objElementsOfData.avgBytes);
	        System.out.println("Latest - starts");
	        
	        ObjectMapper objMap = new ObjectMapper();
	        String myObjElementsOfData = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(objElementsOfData);

	    	parentData finalData = new parentData(objElementsOfData);
         	String myFinalData = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(finalData);
	        
	        //System.out.println("myFinalData : " + myFinalData);
	        
	        String mpDomain  = strList.get(0).toString();
	        System.out.println("mpDomain : " + mpDomain);
	        String mpsamples = builderArry[2];
	        System.out.println("mpsamples : " + mpsamples);
	        String mpmaxResponseTime = builderArry[8];
	        System.out.println("mpmaxResponseTime : " + mpmaxResponseTime);
	        String mpavgBytes = builderArry[9];
	        System.out.println("mpavgBytes : " + mpavgBytes);
	        
	        int mpstart_time = intCreated;
	        System.out.println("mpstart_time : " + mpstart_time);
	        
	        int mpend_time = intEnded;
	        System.out.println("mpend_time : " + mpend_time);
	                  
	        String mpavgThroughput = builderArry[4];
	        System.out.println("mpavgThroughput : " + builderArry[4]);
	        String mperrorsRate = builderArry[10];
	        System.out.println("mperrorsRate : " + mperrorsRate);
	        String mp95line = builderArry[6];
	        System.out.println("mp95line : " + mp95line);
	        String mpbusiness_unit = strList.get(1).toString();
	        System.out.println("mpbusiness_unit : " + mpbusiness_unit);
	        String mplabelName = builderArry[1];
	        System.out.println("mplabelName " + mplabelName);
	        String mpapplication_name = strList.get(2).toString();
	        System.out.println("mpapplication_name : " + mpapplication_name);
	        String mpminResponseTime = builderArry[7];
	        System.out.println("mpminResponseTime : " + mpminResponseTime);
	        
	        String mptest_id = strList.get(3).toString();
	        System.out.println("mptest_id : " + mptest_id);
	        
	        String mptest_run_id = TestRunnerID;
	        System.out.println("mptest_run_id : " + mptest_run_id);
	        String mpavgResponseTime = builderArry[3];
	        System.out.println("mpavgResponseTime : " + mpavgResponseTime);
	        String mp90line = builderArry[5];
	        System.out.println("mp90line : " + mp90line);
	        
	        //if( (TestRunnerID.equals(62780107) && (builderArry[1].equals("XRP_10_00_BOARDPOSITIONS")))
	        		
	        Map<String, Object> map = new LinkedHashMap<>();
	        map.put("data", Arrays.asList(new LinkedHashMap<String, Object>() {
	            {
	            	put("domain", strList.get(0));
		            put("samples", Integer.parseInt(builderArry[2]));
	            	put("maxResponseTime", Integer.parseInt(builderArry[8]));
	            	put("avgBytes", Float.parseFloat(builderArry[9]));
		           	put("start_time", intCreated);
		           	put("end_time", intEnded);
	            	put("avgThroughput", Float.parseFloat(builderArry[4]));
	            	put("errorsRate", Float.parseFloat(builderArry[10]));
	            	put("95line", Integer.parseInt(builderArry[6]));
	            	put("business_unit", strList.get(1));
	            	put("labelName", builderArry[1]);
	            	put("application_name", strList.get(2));
	            	put("minResponseTime", Integer.parseInt(builderArry[7]));
	            	put("test_id", strList.get(3));
	            	put("test_run_id", Integer.parseInt(TestRunnerID));
	            	put("avgResponseTime", Float.parseFloat(builderArry[3]));
	            	put("90line", Integer.parseInt(builderArry[5]));
	            	
	            }
	        }));
	        
	        String bestJSON = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(map);
	        System.out.println("bestJSON : " + bestJSON);
	        logger.info("Blazemeter this is the value being passed to replicaDB : " + bestJSON);
	        System.out.println("Latest");
   
	        RestAssured.baseURI = "https://api-dev.headspin.io/v0/replica/loadtest";

	        Response response = given()
	                .header("Authorization", "Bearer 3088f16f91ee4931b195acfd46ac994d")
	                .and()
	                .body(bestJSON)
	                .when()
	                .post("/upload")
	                .then()
	                .extract().response();

			JSONObject jsonObj = new JSONObject(response.prettyPrint());
		
	        //Assert.assertEquals(200, response.statusCode());
        	        
	        System.out.println("Latest - ends");
	        
		
	}

		
	public static void callFilesForTestID(String testIDvalue) {
		
		
		//RequestSpecification httpRequest = RestAssured.given().auth().basic("2bd25672e30748acc49bf96e",
		//		"2b1e5d9d05374675540463331561bf4cc8cc690282933020c21c90ef65ed3f98818cfe65");
		
		RequestSpecification httpRequest = RestAssured.given().auth().basic("2bd25672e30748acc49bf96e",
				"8ba55e8f8d564024a7cb8b8841c7f4e9a5479d9f69334fc6cc0296d0be254659bcf8e446");
		

			try {
				Response response = httpRequest.get("https://a.blazemeter.com/api/v4/tests/" + testIDvalue + "/files");
				ResponseBody body = response.body();

				JSONObject jsonObj = new JSONObject(response.prettyPrint());
				response.prettyPrint();
				String responseData = response.asString();
				int code = response.getStatusCode();

				JsonPath json = response.jsonPath();
				String builderJson = json.get("result").toString();
				builderJson = json.get("result.name[0]").toString();

				List values = json.getList("result");
				int sizeValue = values.size();
				System.out.println("Size of the Json array is as follows : " + sizeValue);

				for (int i = 0; i < sizeValue; i++) {
					System.out.println();
					builderJsonName = json.get("result.name[" + i + "]").toString();
					System.out.println("builderJsonName : " + builderJsonName);

					builderJsonLink = json.get("result.link[" + i + "]").toString();
					System.out.println("builderJsonLink : " + builderJsonLink);

					tmGetFiles.put(builderJsonName, builderJsonLink);
					System.out.println();

					// st.insertInToGetFilesForTestID(testIDvalue, tmGetFiles);

				}

				System.out.println("outside builderJsonName : " + builderJsonName);
				System.out.println("outside builderJsonLink : " + builderJsonLink);
				st.insertInToGetFilesForTestID(testIDvalue, tmGetFiles);
				st.selectFromGetFilesForTestID();

				try {
					AssertJUnit.assertEquals(code, 200);
				} catch (Exception e) {
					System.out.println("Exception of Test1 getFilesForTestID_TestA : " + e);
				}
			} catch (Exception e) {
				System.out.println("Exception : " + e);
			}
	
		
	}
	
	
	public ArrayList<String> callTestStaticticsForTestID(String[] getblazemeterColumns) {
		
		//RequestSpecification httpRequest = RestAssured.given().auth().basic("2bd25672e30748acc49bf96e",
		//		"2b1e5d9d05374675540463331561bf4cc8cc690282933020c21c90ef65ed3f98818cfe65");

		RequestSpecification httpRequest = RestAssured.given().auth().basic("2bd25672e30748acc49bf96e",
				"8ba55e8f8d564024a7cb8b8841c7f4e9a5479d9f69334fc6cc0296d0be254659bcf8e446");
		
		
		String testID = getblazemeterColumns[4];
		
		Response response = httpRequest.get("https://a.blazemeter.com/api/v4/masters?testId="+ testID +"");
		
		ResponseBody body = response.body();

		JSONObject jsonObj = new JSONObject(response.prettyPrint());
		response.prettyPrint();
		String responseData = response.asString();
		int code = response.getStatusCode();

		JsonPath json = response.jsonPath();
		String builderJson = json.get("result").toString();

		List values = json.getList("result");
		int sizeValue = values.size();
		System.out.println("Size of the Json array is as follows : " + sizeValue);

		for (int i = 0; i < sizeValue; i++) {
			System.out.println();

			builderJsonId = json.get("result.id[" + i + "]").toString();
			System.out.println("builderJsonId : " + builderJsonId);
			collectionBuilderJsonName.add(builderJsonId.toString());

			System.out.println("builderJsonId.toString() : " + builderJsonId.toString() + "value of i : " + i);
					
			builderJsonName = json.get("result.name[" + i + "]").toString();
			System.out.println("builderJsonName : " + builderJsonName);

			builderJsonUserId = json.get("result.userId[" + i + "]").toString();
			System.out.println("builderJsonUserId : " + builderJsonUserId);
			
			builderJsonCreatedTime = json.get("result.created[" + i + "]").toString();
			System.out.println("builderJsonCreatedTime : " + builderJsonCreatedTime);
			
			builderJsonEndedTime = json.get("result.ended[" + i + "]").toString();
			System.out.println("builderJsonEndedTime : " + builderJsonEndedTime);

			tmGetTestStatictics.put(builderJsonName, builderJsonUserId);

			System.out.println();

			st.insertInToGetTestStaticticsForTestID(builderJsonId, tmGetTestStatictics);

		}

		st.selectFromGetFilesForTestID();

		try {
			AssertJUnit.assertEquals(code, 200);
		} catch (Exception e) {
			System.out.println("Exception of Test1 getTestStaticticsForTestID : " + e);
		}

		return collectionBuilderJsonName;
		
	}
	
	//public Map<String, String> callTestStaticticsForTestIDMap(String[] getblazemeterColumns) {
	
	public Map<String, String> callTestStaticticsForTestIDMap(ArrayList<String> getblazemeterColumns) {
		
		//RequestSpecification httpRequest = RestAssured.given().auth().basic("2bd25672e30748acc49bf96e",
		//		"2b1e5d9d05374675540463331561bf4cc8cc690282933020c21c90ef65ed3f98818cfe65");

//		RequestSpecification httpRequest = RestAssured.given().auth().basic("2bd25672e30748acc49bf96e",
//				"8da8a863a21a2101e8d1781148b0c2cabb8054c4abd9ec427b893d23c3cdd10d2ec4d6e1");
		
		RequestSpecification httpRequest = RestAssured.given().auth().basic("2bd25672e30748acc49bf96e",
				"8ba55e8f8d564024a7cb8b8841c7f4e9a5479d9f69334fc6cc0296d0be254659bcf8e446");
		
		System.out.println("getblazemeterColumns values : " + getblazemeterColumns);
		String testID = null;
		//int countReadI = 4;
		for(int readI = 0 ; readI < getblazemeterColumns.size(); readI++) {
			//if( (countReadI >= 4) && (countReadI <= 33)) {
				System.out.println("value of countReadI : " + readI);
				System.out.println("getblazemeterColumns : " + getblazemeterColumns.get(readI));
				testID = getblazemeterColumns.get(readI);
				System.out.println("testID-------getblazemeterColumns : " + testID);
				if(!testID.isEmpty()) {
					Response response = httpRequest.get("https://a.blazemeter.com/api/v4/masters?testId="+ testID +"");
					System.out.println("Newvalue : " + "https://a.blazemeter.com/api/v4/masters?testId="+ testID +"");
					System.out.println("Final...!");
					try {
							ResponseBody body = response.body();
							JSONObject jsonObj = new JSONObject(response.prettyPrint());
							response.prettyPrint();
							String responseData = response.asString();
							int code = response.getStatusCode();
							JsonPath json = response.jsonPath();
							String builderJson = json.get("result").toString();
							List values = json.getList("result");
							int sizeValue = values.size();
							System.out.println("Size of the Json array is as follows : " + sizeValue);
							for (int i = 0; i < sizeValue; i++) {
								System.out.println();
								builderJsonId = json.get("result.id[" + i + "]").toString();
								System.out.println("builderJsonId : " + builderJsonId);
								collectionBuilderJsonName.add(builderJsonId.toString());
								System.out.println("builderJsonId.toString() : " + builderJsonId.toString() + "value of i : " + i);
								builderJsonName = json.get("result.name[" + i + "]").toString();
								System.out.println("builderJsonName : " + builderJsonName);
								builderJsonUserId = json.get("result.userId[" + i + "]").toString();
								System.out.println("builderJsonUserId : " + builderJsonUserId);
								builderJsonCreatedTime = json.get("result.created[" + i + "]").toString();
								System.out.println("builderJsonCreatedTime : " + builderJsonCreatedTime);
								builderJsonEndedTime = json.get("result.ended[" + i + "]").toString();
								System.out.println("builderJsonEndedTime : " + builderJsonEndedTime);
								tmGetTestStatictics.put(builderJsonName, builderJsonUserId);
								System.out.println();
								st.insertInToGetTestStaticticsForTestID(builderJsonId, tmGetTestStatictics);
								String combined = null;
								combined = builderJsonCreatedTime + "_" + builderJsonEndedTime + "---" + testID;
								mapBuilderJsonName.put(builderJsonId.toString(), combined);	
							}
							st.selectFromGetFilesForTestID();
							try {
								AssertJUnit.assertEquals(code, 200);
							} catch (Exception e) {
								System.out.println("Exception of Test1 getTestStaticticsForTestID : " + e);
							}		
					} catch(Exception e) {
							System.out.println("Exception : " + e);
					}
				/////
				//}
			}
			//countReadI++;
		}
		return mapBuilderJsonName;
	}
	
	

	
	/////
	
	//public Map<String, String> callTestStaticticsForTestIDMapviaMultiTest(String[] getblazemeterColumns) {
	
	public Map<String, String> callTestStaticticsForTestIDMapviaMultiTest(ArrayList<String> getblazemeterColumns) {
		
		//RequestSpecification httpRequest = RestAssured.given().auth().basic("2bd25672e30748acc49bf96e",
		//		"2b1e5d9d05374675540463331561bf4cc8cc690282933020c21c90ef65ed3f98818cfe65");

		//RequestSpecification httpRequest = RestAssured.given().auth().basic("2bd25672e30748acc49bf96e",
		//		"8da8a863a21a2101e8d1781148b0c2cabb8054c4abd9ec427b893d23c3cdd10d2ec4d6e1");
		
		mapBuilderJsonName.clear();
		
		RequestSpecification httpRequest = RestAssured.given().auth().basic("2bd25672e30748acc49bf96e",
				"8ba55e8f8d564024a7cb8b8841c7f4e9a5479d9f69334fc6cc0296d0be254659bcf8e446");
		
		System.out.println("getblazemeterColumns values : " + getblazemeterColumns);
		String testID = null;

		for(int readMTi = 0; readMTi < getblazemeterColumns.size(); readMTi++) {
		    System.out.println("value of readMTi : " + readMTi);
		    System.out.println("getblazemeterColumns : " + getblazemeterColumns.get(readMTi));
		    testID = getblazemeterColumns.get(readMTi);
		    System.out.println("testID-------getblazemeterColumns : " + testID);
 		    if(!testID.isEmpty()) {
	    		/////
			    //Response response = httpRequest.get("https://a.blazemeter.com/api/v4/masters?testId="+ testID +"");
				//https://a.blazemeter.com/api/v4/multi-tests/10271190?populateTests=true&limit=50
				Response response = httpRequest.get("https://a.blazemeter.com/api/v4/multi-tests/"+ testID +"?populateTests=true&limit=50");
				System.out.println("Values are as follows : " + "https://a.blazemeter.com/api/v4/multi-tests/"+ testID +"?populateTests=true&limit=50");
				System.out.println("Final");
				try {
						ResponseBody body = response.body();
						JSONObject jsonObj = new JSONObject(response.prettyPrint());
						response.prettyPrint();
						String responseData = response.asString();
						int code = response.getStatusCode();
						JsonPath json = response.jsonPath();
						String builderJson = json.get("result").toString();
						System.out.println("New data builderJson : " + builderJson);
						System.out.println("Final : ");
						//List values = json.getList("result");
						List values = json.getList("result.masters");
						System.out.println("newdata");
						System.out.println("values are as follows : " + values);
						int sizeValue = values.size();
						System.out.println("Size of the Json array is as follows : " + sizeValue);
						//mapBuilderJsonName.clear();
						for (int i = 0; i < sizeValue; i++) {
							System.out.println();
							//a
							//$.result[0].id
							//builderJsonId = json.get("result.id[" + i + "]").toString();
							
							//$.result.masters[1].id
							builderJsonId = json.get("result.masters[" + i + "].id").toString();
							System.out.println("builderJsonId : " + builderJsonId);
							collectionBuilderJsonName.add(builderJsonId.toString());
							System.out.println("builderJsonId.toString() : " + builderJsonId.toString() + "value of i : " + i);
							
							//b
							//$.result[0].name
							//builderJsonName = json.get("result.name[" + i + "]").toString();
							
							//$.result.masters[*].name
							builderJsonName = json.get("result.masters[" + i + "].name").toString();
							System.out.println("builderJsonName : " + builderJsonName);
				
							//c
							//$.result[0].userId
							//builderJsonUserId = json.get("result.userId[" + i + "]").toString();
							
							//$.result.masters[*].userId
							builderJsonUserId = json.get("result.masters[" + i + "].userId").toString();
							System.out.println("builderJsonUserId : " + builderJsonUserId);
							
							//d
							//$.result[0].created
							//builderJsonCreatedTime = json.get("result.created[" + i + "]").toString();
							
							//$.result.masters[*].created
							builderJsonCreatedTime = json.get("result.masters[" + i + "].created").toString();
							System.out.println("builderJsonCreatedTime : " + builderJsonCreatedTime);
							
							//e
							//$.result[0].ended
							//builderJsonEndedTime = json.get("result.ended[" + i + "]").toString();
							
							//$.result.masters[*].ended
							builderJsonEndedTime = json.get("result.masters[" + i + "].ended").toString();
							System.out.println("builderJsonEndedTime : " + builderJsonEndedTime);
				
							tmGetTestStaticticsMultiTest.put(builderJsonName, builderJsonUserId);
							System.out.println();
							//st.insertInToGetTestStaticticsForTestID(builderJsonId, tmGetTestStaticticsMultiTest);
							String combined = null;
							combined = builderJsonCreatedTime + "_" + builderJsonEndedTime + "---" + testID;
							mapBuilderJsonName.put(builderJsonId.toString(), combined);
						}
						//st.selectFromGetFilesForTestID();
						try {
							AssertJUnit.assertEquals(code, 200);
						} catch (Exception e) {
							System.out.println("Exception of Test1 getTestStaticticsForTestID : " + e);
						}
				
				} catch(Exception e) {
					System.out.println("Exception : " + e);
				}
			    /////
		    }
         }
	  return mapBuilderJsonName;
	}
	
	
	/////
	
	
//	public void callTestStatisticsForTestIDAgg(String TestRunnerID, String[] getblazemeterColumns, String Created, String Ended) throws JsonProcessingException {
//		
//		//RequestSpecification httpRequest = RestAssured.given().auth().basic("2bd25672e30748acc49bf96e",
//		//		"2b1e5d9d05374675540463331561bf4cc8cc690282933020c21c90ef65ed3f98818cfe65");
//		
//		RequestSpecification httpRequest = RestAssured.given().auth().basic("2bd25672e30748acc49bf96e",
//				"8ba55e8f8d564024a7cb8b8841c7f4e9a5479d9f69334fc6cc0296d0be254659bcf8e446");
//		
//
//		System.out.println("**********TestRunnerID : " + TestRunnerID);
//		Response response = httpRequest.get("https://a.blazemeter.com/api/v4/masters/" + TestRunnerID + "/reports/aggregatereport/data");
//		
//		
//		try {
//		
//				ResponseBody body = response.body();
//		
//				JSONObject jsonObj = new JSONObject(response.prettyPrint());
//				response.prettyPrint();
//				String responseData = response.asString();
//				int code = response.getStatusCode();
//		
//				JsonPath json = response.jsonPath();
//				String builderJson = json.get("result").toString();
//				System.out.println("builderJson output is as follows : " + builderJson);
//		
//				List values = json.getList("result");
//				int sizeValue = values.size();
//				System.out.println("Size of the Json array is as follows : " + sizeValue);
//				int builderCounter = 0;
//				
//				for (int i = 0; i < sizeValue; i++) {
//					System.out.println();
//		
//					builderJsonlabelId = json.get("result.labelId[" + i + "]").toString();
//					System.out.println("Final all builderJsonlabelId : " + builderJsonlabelId);
//					builderArry[builderCounter] = builderJsonlabelId;
//					builderCounter++;
//					
//					builderJsonlabelName = json.get("result.labelName[" + i + "]").toString();
//					System.out.println("Final all builderJsonlabelName : " + builderJsonlabelName);
//					builderArry[builderCounter] = builderJsonlabelName;
//					builderCounter++;
//					
//					builderJsonSamples = json.get("result.samples[" + i + "]").toString();
//					System.out.println("Final all builderJsonSamples : " + builderJsonSamples);
//					builderArry[builderCounter] = builderJsonSamples;
//					builderCounter++;
//		
//					builderJsonavgResponseTime = json.get("result.avgResponseTime[" + i + "]").toString();
//					System.out.println("Final all builderJsonavgResponseTime : " + builderJsonavgResponseTime);
//					builderArry[builderCounter] = builderJsonavgResponseTime;
//					builderCounter++;
//					
//				
//					builderJsonavgThroughtPut = json.get("result.avgThroughput[" + i + "]").toString();
//					System.out.println("Final all builderJsonavgThroughtPut : " + builderJsonavgThroughtPut);
//					builderArry[builderCounter] = builderJsonavgThroughtPut;
//					builderCounter++;
//	
//					builderJson90line = json.get("result[" + i + "].90line").toString();
//					System.out.println("Final all builderJson90line : " + builderJson90line);
//					builderArry[builderCounter] = builderJson90line;
//					builderCounter++;
//
//					builderJson95line = json.get("result[" + i + "].95line").toString();
//					System.out.println("Final all builderJson95line : " + builderJson95line);
//					builderArry[builderCounter] = builderJson95line;
//					builderCounter++;
//					
//				
//					builderJsonMinResponseTime = json.get("result.minResponseTime[" + i + "]").toString();
//					System.out.println("Final all builderJsonMinResponseTime : " + builderJsonMinResponseTime);
//					builderArry[builderCounter] = builderJsonMinResponseTime;
//					builderCounter++;
//					
//				
//					builderJsonMaxResponseTime = json.get("result.maxResponseTime[" + i + "]").toString();
//					System.out.println("Final all builderJsonMaxResponseTime : " + builderJsonMaxResponseTime);
//					builderArry[builderCounter] = builderJsonMaxResponseTime;
//					builderCounter++;
//					
//					
//					builderJsonavgBytes = json.get("result.avgBytes[" + i + "]").toString();
//					System.out.println("Final all builderJsonavgBytes : " + builderJsonavgBytes);
//					builderArry[builderCounter] = builderJsonavgBytes;
//					builderCounter++;
//					
//					
//					builderJsonErrorRate = json.get("result.errorsRate[" + i + "]").toString();
//					System.out.println("Final all builderJsonErrorRate : " + builderJsonErrorRate);
//					builderArry[builderCounter] = builderJsonErrorRate;
//					builderCounter++;
//		
//					builderCounter =0;
//					
//					tmRequestTestStatistics.put(builderJsonSamples, builderJsonavgResponseTime);
//		
//					System.out.println();
//					
//					System.out.println("Output of builderArry : " + builderArry);
//					
//					for(int xyz = 0; xyz < builderArry.length; xyz++) {
//						System.out.println("Values are as follows : " + builderArry[xyz]);
//					}
//					
//					System.out.println();
//					
//			        ArrayList<String> strList = new ArrayList<String>(Arrays.asList(getblazemeterColumns));
//					
//			        System.out.println("Created time's value : " + Created);
//			        int intCreated = Integer.parseInt(Created);
//			        System.out.println("intCreated time's value : " + intCreated);
//			        logger.info("Blazemeter => this is the intCreated time being passed : " + intCreated);
//			        System.out.println("Ended time's value : " + Ended);
//			        int intEnded = Integer.parseInt(Ended);
//			        System.out.println("intEnded time's value : " + intEnded);
//			        logger.info("Blazemeter => this is the intEnded time being passed : " + intEnded);
//			        
//			        st.insertInToBlazeMeterTableAddtlLatestDebug(strList, builderArry, strList.get(3), TestRunnerID, intCreated, intEnded);
//			        callReplicaDBAPIBlazeMeter(TestRunnerID, strList, builderArry, intCreated, intEnded);
//					
//				}
//		
//				try {
//		
//					AssertJUnit.assertEquals(code, 200);
//		
//				} catch (Exception e) {
//		
//					System.out.println("Exception of Test1 getTestStaticticsForTestID : " + e);
//		
//				}
//				
//		} catch(Exception e) {
//			
//			System.out.println("Exception : " + e);
//			
//		}
//
//	}
	
	//String obtTestRunID = null;
	//String testID_Sql = null;
	//String testScenario_Sql = null;

	public void callTestStatisticsForTestIDAggEnhanced(String TestRunnerID, String[] getblazemeterColumns, String Created, String Ended, String Pre_Ended_n_TestID_Split) throws JsonProcessingException {
		
		//RequestSpecification httpRequest = RestAssured.given().auth().basic("2bd25672e30748acc49bf96e",
		//		"2b1e5d9d05374675540463331561bf4cc8cc690282933020c21c90ef65ed3f98818cfe65");
		
//		RequestSpecification httpRequest = RestAssured.given().auth().basic("2bd25672e30748acc49bf96e",
//				"8ba55e8f8d564024a7cb8b8841c7f4e9a5479d9f69334fc6cc0296d0be254659bcf8e446");
//		
//
//		System.out.println("**********TestRunnerID : " + TestRunnerID);
//		Response response = httpRequest.get("https://a.blazemeter.com/api/v4/masters/" + TestRunnerID + "/reports/aggregatereport/data");
		
		
		try {
			
				RequestSpecification httpRequest = RestAssured.given().auth().basic("2bd25672e30748acc49bf96e",
				"8ba55e8f8d564024a7cb8b8841c7f4e9a5479d9f69334fc6cc0296d0be254659bcf8e446");
		
	
				System.out.println("**********TestRunnerID : " + TestRunnerID);
				Response response = httpRequest.get("https://a.blazemeter.com/api/v4/masters/" + TestRunnerID + "/reports/aggregatereport/data");
		
				ResponseBody body = response.body();
		
				JSONObject jsonObj = new JSONObject(response.prettyPrint());
				response.prettyPrint();
				String responseData = response.asString();
				int code = response.getStatusCode();
		
				JsonPath json = response.jsonPath();
				String builderJson = json.get("result").toString();
				System.out.println("builderJson output is as follows : " + builderJson);
		
				List values = json.getList("result");
				int sizeValue = values.size();
				System.out.println("Size of the Json array is as follows : " + sizeValue);
				int builderCounter = 0;
				
				for (int i = 0; i < sizeValue; i++) {
					System.out.println();
		
					builderJsonlabelId = json.get("result.labelId[" + i + "]").toString();
					System.out.println("Final all builderJsonlabelId : " + builderJsonlabelId);
					builderArry[builderCounter] = builderJsonlabelId;
					builderCounter++;
					
					builderJsonlabelName = json.get("result.labelName[" + i + "]").toString();
					System.out.println("Final all builderJsonlabelName : " + builderJsonlabelName);
					builderArry[builderCounter] = builderJsonlabelName;
					builderCounter++;
					
					builderJsonSamples = json.get("result.samples[" + i + "]").toString();
					System.out.println("Final all builderJsonSamples : " + builderJsonSamples);
					builderArry[builderCounter] = builderJsonSamples;
					builderCounter++;
		
					builderJsonavgResponseTime = json.get("result.avgResponseTime[" + i + "]").toString();
					System.out.println("Final all builderJsonavgResponseTime : " + builderJsonavgResponseTime);
					builderArry[builderCounter] = builderJsonavgResponseTime;
					builderCounter++;
					
				
					builderJsonavgThroughtPut = json.get("result.avgThroughput[" + i + "]").toString();
					System.out.println("Final all builderJsonavgThroughtPut : " + builderJsonavgThroughtPut);
					builderArry[builderCounter] = builderJsonavgThroughtPut;
					builderCounter++;
	
					builderJson90line = json.get("result[" + i + "].90line").toString();
					System.out.println("Final all builderJson90line : " + builderJson90line);
					builderArry[builderCounter] = builderJson90line;
					builderCounter++;

					builderJson95line = json.get("result[" + i + "].95line").toString();
					System.out.println("Final all builderJson95line : " + builderJson95line);
					builderArry[builderCounter] = builderJson95line;
					builderCounter++;
					
				
					builderJsonMinResponseTime = json.get("result.minResponseTime[" + i + "]").toString();
					System.out.println("Final all builderJsonMinResponseTime : " + builderJsonMinResponseTime);
					builderArry[builderCounter] = builderJsonMinResponseTime;
					builderCounter++;
					
				
					builderJsonMaxResponseTime = json.get("result.maxResponseTime[" + i + "]").toString();
					System.out.println("Final all builderJsonMaxResponseTime : " + builderJsonMaxResponseTime);
					builderArry[builderCounter] = builderJsonMaxResponseTime;
					builderCounter++;
					
					
					builderJsonavgBytes = json.get("result.avgBytes[" + i + "]").toString();
					System.out.println("Final all builderJsonavgBytes : " + builderJsonavgBytes);
					builderArry[builderCounter] = builderJsonavgBytes;
					builderCounter++;
					
					
					builderJsonErrorRate = json.get("result.errorsRate[" + i + "]").toString();
					System.out.println("Final all builderJsonErrorRate : " + builderJsonErrorRate);
					builderArry[builderCounter] = builderJsonErrorRate;
					builderCounter++;
		
					builderCounter =0;
					
					tmRequestTestStatistics.put(builderJsonSamples, builderJsonavgResponseTime);
		
					System.out.println();
					
					System.out.println("Output of builderArry : " + builderArry);
					
					for(int xyz = 0; xyz < builderArry.length; xyz++) {
						System.out.println("Values are as follows : " + builderArry[xyz]);
					}
					
					System.out.println();
					
			        ArrayList<String> strList = new ArrayList<String>(Arrays.asList(getblazemeterColumns));
					
			        System.out.println("Created time's value : " + Created);
			        int intCreated = Integer.parseInt(Created);
			        System.out.println("intCreated time's value : " + intCreated);
			        logger.info("Blazemeter => this is the intCreated time being passed : " + intCreated);
			        System.out.println("Ended time's value : " + Ended);
			        int intEnded = Integer.parseInt(Ended);
			        System.out.println("intEnded time's value : " + intEnded);
			        logger.info("Blazemeter => this is the intEnded time being passed : " + intEnded);
			        
			        String testIDForCheckingSQl = strList.get(3);
			        String TestRunnerIDForCheckingSQl = TestRunnerID;
			        System.out.println("testIDForCheckingSQl & TestRunnerIDForCheckingSQl : " + testIDForCheckingSQl + " " + TestRunnerIDForCheckingSQl);
        
			        if (Pre_Ended_n_TestID_Split.contains("11369589")) {
			        	System.out.println("value of such is as follows : " + Pre_Ended_n_TestID_Split);
			        } 
			        
			        apiARListReturn = st.constructComparisionArrLt(strList, builderArry, strList.get(3), TestRunnerID, intCreated, intEnded, Pre_Ended_n_TestID_Split);
			        System.out.println("apiARListReturn output : " + apiARListReturn);
			        logger.info("apiARListReturn output : " + apiARListReturn);
			        int sizeOfAPIARListReturn = apiARListReturn.size();
			        			        
			        arListReturn = st.selectBlazeMeterTableAddtlLatestDebug(strList, builderArry, strList.get(3), TestRunnerID, intCreated, intEnded, Pre_Ended_n_TestID_Split);
			        System.out.println("arListReturn     output : " + arListReturn);
			        logger.info("arListReturn     output : " + arListReturn);
			        int sizeOfARListReturn = arListReturn.size();
			        
			        if( (sizeOfAPIARListReturn != 0) ) {
			        	
			        	if(sizeOfARListReturn == 0 ) {
			        		
				        	logger.info("Data insert : " + strList + " " + builderArry + " " + strList.get(3) + " " + TestRunnerID + " " + intCreated + " " + intEnded);
				        	st.insertInToBlazeMeterTableAddtlLatestDebug(strList, builderArry, strList.get(3), TestRunnerID, intCreated, intEnded, Pre_Ended_n_TestID_Split);

				        	logger.info("API insert : " + strList + " " + builderArry + " " + strList.get(3) + " " + TestRunnerID + " " + intCreated + " " + intEnded);
					        callReplicaDBAPIBlazeMeter(TestRunnerID, strList, builderArry, intCreated, intEnded);
					        
			        		
			        	} else if (sizeOfARListReturn != 0 ) {
			        		
				        	if( (		    apiARListReturn.get(0).equals(arListReturn.get(0))	)  
				        			&& (	apiARListReturn.get(1).equals(arListReturn.get(1))	) 
				        			&& (	apiARListReturn.get(2).equals(arListReturn.get(2))	)
				        			&& (	apiARListReturn.get(3).equals(arListReturn.get(3))	)
				        			&& (	apiARListReturn.get(4).equals(arListReturn.get(4))	)
				        			&& (	apiARListReturn.get(5).equals(arListReturn.get(5))	)
				        			&& (	apiARListReturn.get(6).equals(arListReturn.get(6))	)
				        			&& (	apiARListReturn.get(7).equals(arListReturn.get(7))	)
				        			&& (	apiARListReturn.get(8).equals(arListReturn.get(8))	)
				        			&& (	apiARListReturn.get(9).equals(arListReturn.get(9))	)
				        			&& (	apiARListReturn.get(10).equals(arListReturn.get(10))	)
				        			&& (	apiARListReturn.get(11).equals(arListReturn.get(11))	)
				        			&& (	apiARListReturn.get(12).equals(arListReturn.get(12))	)
				        			&& (	apiARListReturn.get(13).equals(arListReturn.get(13))	)
				        			&& (	apiARListReturn.get(14).equals(arListReturn.get(14))	)
				        			&& (	apiARListReturn.get(15).equals(arListReturn.get(15))	)
				        			&& (	apiARListReturn.get(16).equals(arListReturn.get(16))	)
				        			&& (	apiARListReturn.get(17).equals(arListReturn.get(17))	)
				        			&& (	apiARListReturn.get(18).equals(arListReturn.get(18))	)
				        			&& (	apiARListReturn.get(19).equals(arListReturn.get(19))	)
				        			
				        			) {
				        		
				        					System.out.println("");
				        					System.out.println("");
				        					System.out.println("");
				        					System.out.println("");
				        					System.out.println("");
				        					System.out.println("Data already exist refer to the logger info for more details!");
				        					logger.info("Data already exist refer to the logger info for more details!");
				        					System.out.println("");
				        					System.out.println("");
				        					System.out.println("");
				        					System.out.println("");
				        					System.out.println("");
				        					
								        	logger.info("Data insert : " + strList + " " + builderArry + " " + strList.get(3) + " " + TestRunnerID + " " + intCreated + " " + intEnded);
								        	st.insertInToBlazeMeterTableAddtlLatestMaintenanceDebug(strList, builderArry, strList.get(3), TestRunnerID, intCreated, intEnded, Pre_Ended_n_TestID_Split);
								        	
									        //st.insertInToBlazeMeterTableAddtlLatestDebugX(strList, builderArry, strList.get(3), TestRunnerID, intCreated, intEnded);
									        //st.insertInToBlazeMeterTableAddtlLatestDebugY(strList, builderArry, strList.get(3), TestRunnerID, intCreated, intEnded);
								        	//logger.info("API insert : " + strList + " " + builderArry + " " + strList.get(3) + " " + TestRunnerID + " " + intCreated + " " + intEnded);
									        //callReplicaDBAPIBlazeMeter(TestRunnerID, strList, builderArry, intCreated, intEnded);
				        		
				        	}
			        		
			        	} else {
			        		
				        	System.out.println("sizeOfARListReturn != 0 passed but api and ar list comparision failed");
				        	logger.info("sizeOfARListReturn != 0 passed but api and ar list comparision failed");
				        	
				        	logger.info("Data insert : " + strList + " " + builderArry + " " + strList.get(3) + " " + TestRunnerID + " " + intCreated + " " + intEnded);
				        	st.insertInToBlazeMeterTableAddtlLatestDebug(strList, builderArry, strList.get(3), TestRunnerID, intCreated, intEnded, Pre_Ended_n_TestID_Split);

				        	logger.info("API insert : " + strList + " " + builderArry + " " + strList.get(3) + " " + TestRunnerID + " " + intCreated + " " + intEnded);
					        callReplicaDBAPIBlazeMeter(TestRunnerID, strList, builderArry, intCreated, intEnded);
			        		
			        	}
			        	
			        	

			        	
			        } else {
			        	
			        	System.out.println("Skipping as api list is empty");
			        	logger.info("Skipping as api list is empty");
			        	
			        }
			        

					
				}
		
				try {
		
					AssertJUnit.assertEquals(code, 200);
		
				} catch (Exception e) {
		
					System.out.println("Exception of Test1 getTestStaticticsForTestID : " + e);
		
				}
				
		} catch(Exception e) {
			
			System.out.println("Exception : " + e);
			
		}

	}

	public static void recursivePrint(File[] arr, int level) {
		// for-each loop for main directory files
		for (File f : arr) {
			// tabs for internal levels
			for (int i = 0; i < level; i++)
				System.out.print("\t");

			if (f.isFile())
				System.out.println("These are files : " + f.getName());

			else if (f.isDirectory()) {
				System.out.println("These are directories : [" + f.getName() + "]");

				// recursion for sub-directories
				recursivePrint(f.listFiles(), level + 1);
			}
		}
	}
	
	public static void recursiveFind(File[] arr, int level) {
		// for-each loop for main directory files
		for (File f : arr) {
			// tabs for internal levels
			for (int i = 0; i < level; i++)
				System.out.print("\t");

			if ( ( f.isFile() ) ) {
				
				System.out.println("These are files : " + f.getName());
				
			} else if (f.isDirectory()) {
				
				System.out.println("These are directories : [" + f.getName() + "]");
				//recursion for sub-directories
				recursivePrint(f.listFiles(), level + 1);
				
			}
		}
	}
	
	public static void recursiveFinalJmeter(File[] arr, int level) {
		// for-each loop for main directory files
		for (File f : arr) {
			// tabs for internal levels
			for (int i = 0; i < level; i++)
				
				level = level++;
			
			if ( (f.isFile()) && (level == 4) && (!f.getName().contains(".DS_Store")) ) {
				
					aListFilesJmeter.add(f.getAbsolutePath());
				
			} else if (f.isDirectory()) {
				
				//System.out.println("These are directories (before) : [" + f.getName() + "]");
				aListDirectoriesJmeter.add(f.getName());
				
	
				// recursion for sub-directories
				recursiveFinalJmeter(f.listFiles(), level);
				

			}
		}
	}
	
	
	public static void recursiveFinalLoadRunner(File[] arr, int level) {
		//public static void recursiveFinalLoadRunner(File arr, int level) {
		// for-each loop for main directory files
		for (File f : arr) {
			// tabs for internal levels

			//for (int i = 0; i < level; i++)
				 //level = level++;
			
				if ( (f.isFile()) && (level == 4) && (!f.getName().contains(".DS_Store")) ) {
					
					System.out.println("These are files : " + f.getName());
					//aListFilesLoadRunner.add(f.getName());
					aListFilesLoadRunner.add(f.getAbsolutePath());
					
				} else if (f.isDirectory()) {
					
					System.out.println("These are directories (before) : [" + f.getName() + "]");
					aListDirectoriesLoadRunner.add(f.getName());
					
					// recursion for sub-directories
					recursiveFinalLoadRunner(f.listFiles(), level);
					//recursiveFinalLoadRunner(f, level);
					
				}
		 //}
	  }
   }
	
	public static void recursiveFinalBlazemeter(File[] arr, int level) {
		// for-each loop for main directory files
		for (File f : arr) {
			// tabs for internal levels
			for (int i = 0; i < level; i++)
				
				level = level++;
			
			if ( (f.isFile()) && (level == 4) && (!f.getName().contains(".DS_Store")) ) {
				
				System.out.println("These are files : " + f.getName());
				//aListFilesBlazeMeter.add(f.getName());
				aListFilesBlazeMeter.add(f.getAbsolutePath());
				
			} else if (f.isDirectory()) {
				//System.out.println("These are directories (before) : [" + f.getName() + "]");
				aListDirectoriesBlazeMeter.add(f.getName());
				
				
				// recursion for sub-directories
				recursiveFinalBlazemeter(f.listFiles(), level);

			}
		}
	}

	public static String getDirectoryName(String path) {

		String dirPath = null;
		File[] directories = new File(path).listFiles(File::isDirectory);
		dirPath = Arrays.toString(directories);
		System.out.println("Additional data within the newmethod " + dirPath);

		return dirPath;

	}

	public static void cmdTest(String[] command) throws Exception {

		StringBuilder output = new StringBuilder();
		ProcessBuilder builder = new ProcessBuilder(command);
		builder.redirectErrorStream(true);
		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while (true) {
			line = r.readLine();
			if (line == null) {
				break;
			}
			System.out.println("This is the line : " + line);
			output.append(line + "\n");
		}

		int exitVal = p.waitFor();
		if (exitVal == 0) {
			System.out.println("Success! Command Execution returned no errors");
			System.out.println(output);
			// System.exit(0);
		} else {
			System.out.println("Abnormal! Command Execution returned with errors");
			System.out.println(output);
			// System.exit(0);

		}

	}

}

