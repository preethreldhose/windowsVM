package e2eTestingPWC_AID.utils;

import java.sql.Connection;
import org.apache.log4j.Logger;
import org.testng.xml.XmlClass;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import e2eTestingPWC_AID.utils.handleMySQL;

public class handleMySQL {

	static Logger logger = Logger.getLogger(XmlClass.class);

	public static Map<String, String> mapLocalDBBuilder = new TreeMap<String, String>();
	public static Map<String, String> mapLocalDBBuilderSelect = new TreeMap<String, String>();
	public static Map<String, String> mapLocalDBBuilderSelectDebug = new TreeMap<String, String>();
	public static ArrayList<String> arList = new ArrayList<String>();
	public static ArrayList<String> apiARList = new ArrayList<String>();
	
	public static ArrayList<String> selectJmeterARList = new ArrayList<String>();
	public static ArrayList<String> apiARJmeterListLoad = new ArrayList<String>();
	
	
	public static ArrayList<String> selectLoadRunnerARList = new ArrayList<String>();
	public static ArrayList<String> apiARLoadrunnerListLoad = new ArrayList<String>();

	

	public static void selectFromGetFilesForTestID() {
		Connection connection = null;
		Statement selectStmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");
			selectStmt = connection.createStatement();
			ResultSet rs = selectStmt.executeQuery("SELECT jsonName, jsonLink FROM getFilesForTestID");
			while (rs.next()) {
				System.out.println(rs.getString(1) + " " + rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				selectStmt.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void insertInToGetFilesForTestID(String builderJsonName, String builderJsonLink) {

		Connection connection = null;
		Statement insertStmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");
			insertStmt = connection.createStatement();
			System.out.println("outside builderJsonName : " + builderJsonName);
			System.out.println("outside builderJsonLink : " + builderJsonLink);
			insertStmt.execute("INSERT INTO getFilesForTestID (jsonName,jsonLink) VALUES ('" + builderJsonName + "','"
					+ builderJsonLink + "')");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				insertStmt.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void insertInToGetFilesForTestID(String testID, TreeMap<String, String> tm) {

		Connection connection = null;
		Statement insertStmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");
			insertStmt = connection.createStatement();
			// System.out.println("outside builderJsonName : " + finalJsonName);
			// System.out.println("outside builderJsonLink : " + finalJsonLink);
			// insertStmt.execute("INSERT INTO getFilesForTestID (jsonName,jsonLink) VALUES
			// ('" + builderJsonName + "','" + builderJsonLink + "')");

			for (Map.Entry m : tm.entrySet()) {

				System.out.println("All latest values inside DBconnect: " + testID + m.getKey() + " " + m.getValue());
				insertStmt.execute("INSERT INTO getFilesForTestID (testID, jsonName,jsonLink) VALUES ('" + testID
						+ "','" + m.getKey() + "','" + m.getValue() + "')");

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				insertStmt.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void insertInToRequestTestStatisticsForTestID(String test_RunID, String builderJsonlabelId,
			TreeMap<String, String> tm) {

		Connection connection = null;
		Statement insertStmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");
			insertStmt = connection.createStatement();
			// System.out.println("outside builderJsonName : " + finalJsonName);
			// System.out.println("outside builderJsonLink : " + finalJsonLink);
			// insertStmt.execute("INSERT INTO getFilesForTestID (jsonName,jsonLink) VALUES
			// ('" + builderJsonName + "','" + builderJsonLink + "')");

			for (Map.Entry m : tm.entrySet()) {

				System.out.println("All latest values : " + test_RunID + " " + builderJsonlabelId + " " + m.getKey()
						+ " " + m.getValue());
				insertStmt.execute(
						"INSERT INTO requestTestStatisticsForTestID (testRunId, jsonLabelId, jsonSamples, jsonAvgResponseTime) VALUES ('"
								+ test_RunID + "','" + builderJsonlabelId + "','" + m.getKey() + "','" + m.getValue()
								+ "')");
				System.out.println(
						"INSERT INTO requestTestStatisticsForTestID (testRunId, jsonLabelId, jsonSamples, jsonAvgResponseTime) VALUES ('"
								+ test_RunID + "','" + builderJsonlabelId + "','" + m.getKey() + "','" + m.getValue()
								+ "')");

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				insertStmt.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void insertInToGetTestStaticticsForTestID(String testIDAddtl, TreeMap<String, String> tm) {

		Connection connection = null;
		Statement insertStmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");
			insertStmt = connection.createStatement();
			// System.out.println("outside builderJsonName : " + finalJsonName);
			// System.out.println("outside builderJsonLink : " + finalJsonLink);
			// insertStmt.execute("INSERT INTO getFilesForTestID (jsonName,jsonLink) VALUES
			// ('" + builderJsonName + "','" + builderJsonLink + "')");

			for (Map.Entry m : tm.entrySet()) {

				System.out.println("All latest values : " + testIDAddtl + m.getKey() + " " + m.getValue());
				insertStmt.execute("INSERT INTO getTestStaticticsForTestID (id, jsonName,jsonUserId) VALUES ('"
						+ testIDAddtl + "','" + m.getKey() + "','" + m.getValue() + "')");
				System.out.println("INSERT INTO getTestStaticticsForTestID (id, jsonName,jsonUserId) VALUES ('"
						+ testIDAddtl + "','" + m.getKey() + "','" + m.getValue() + "')");

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				insertStmt.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void insertInTojmeterTable(String[] DomainNTestIDsAddtl, String[] jmeterTableValues) {

		Connection connection = null;
		Statement insertStmt = null;
		String testIDAddtl = "1234567890";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");
			insertStmt = connection.createStatement();

			// insertStmt.execute("INSERT INTO jmeterTableAddtl (Domain) VALUES (" +
			// DomainNTestIDsAddtl[0] + ")");
			// insertStmt.execute("INSERT INTO jmeterTableAddtl (Domain, BusinessUnit,
			// ApplicationName) VALUES
			// ('"+testIDAddtl+"','"+testIDAddtl+"','"+testIDAddtl+"')");
			// insertStmt.execute("INSERT INTO jmeterTableAddtl (Domain, BusinessUnit,
			// ApplicationName) VALUES
			// ('"+DomainNTestIDsAddtl[0]+"','"+DomainNTestIDsAddtl[1]+"','"+DomainNTestIDsAddtl[2]+"')");

			/*
			 * insertStmt.execute("INSERT INTO jmeterTableAddtl (" + "Domain,\n" +
			 * "BusinessUnit,\n" + "ApplicationName,\n" + "TestScenario,\n" + "TestID,\n" +
			 * "Label,\n" + "Samples,\n" + "Average,\n" + "Median,\n" +
			 * "90PercentileLine,\n" + "95PercentileLine,\n" + "99PercentileLine,\n" +
			 * "Min,\n" + "Max,\n" + "ErrorPercentage,\n" + "Throughput,\n" +
			 * "ReceivedKBsec,\n" + "SentKBsec) VALUES ('" +testIDAddtl+"','"
			 * +testIDAddtl+"','" +testIDAddtl+"','" +testIDAddtl+"','" +testIDAddtl+"','"
			 * +testIDAddtl+"','" +testIDAddtl+"','" +testIDAddtl+"','" +testIDAddtl+"','"
			 * +testIDAddtl+"','" +testIDAddtl+"','" +testIDAddtl+"','" +testIDAddtl+"','"
			 * +testIDAddtl+"','" +testIDAddtl+"','" +testIDAddtl+"','" +testIDAddtl+"','"
			 * +testIDAddtl+"')");
			 */

//	        insertStmt.execute("INSERT INTO jmeterTableAddtl ("
//	        		+ "Domain,\n"
//	        		+ "BusinessUnit,\n"
//	        		+ "ApplicationName,\n"
//	        		+ "TestScenario,\n"
//	        		+ "TestID,\n"
//	        		+ "Label,\n"
//	        		+ "Samples,\n"
//	        		+ "Average,\n"
//	        		+ "Median,\n"
//	        		+ "90PercentileLine,\n"
//	        		+ "95PercentileLine,\n"
//	        		+ "99PercentileLine,\n"
//	        		+ "Min,\n"
//	        		+ "Max,\n"
//	        		+ "ErrorPercentage,\n"
//	        		+ "Throughput,\n"
//	        		+ "ReceivedKBsec,\n"
//	        		+ "SentKBsec) VALUES ('"
//	        		+DomainNTestIDsAddtl[0]+"','"
//	        		+DomainNTestIDsAddtl[1]+"','"
//	        		+DomainNTestIDsAddtl[2]+"','"
//	        		+DomainNTestIDsAddtl[3]+"','"
//	        		+DomainNTestIDsAddtl[4]+"','"
//	        		+jmeterTableValues[0]+"','"
//	        		+jmeterTableValues[1]+"','"
//	        		+jmeterTableValues[2]+"','"
//	        		+jmeterTableValues[3]+"','"
//	        		+jmeterTableValues[4]+"','"
//	        		+jmeterTableValues[5]+"','"
//	        		+jmeterTableValues[6]+"','"
//	        		+jmeterTableValues[7]+"','"
//	        		+jmeterTableValues[8]+"','"
//	        		+jmeterTableValues[9]+"','"
//	        		+jmeterTableValues[10]+"','"
//	        		+jmeterTableValues[11]+"','"
//	        		+jmeterTableValues[12]+"')");

			System.out.println("Hello there");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				insertStmt.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void insertInToLoadRunnerTable(String[] DomainNTestIDsAddtl, String[] loadRTableValues) {

		Connection connection = null;
		Statement insertStmt = null;
		String testIDAddtl = "1234567890";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");
			insertStmt = connection.createStatement();

			// insertStmt.execute("INSERT INTO jmeterTableAddtl (Domain) VALUES (" +
			// DomainNTestIDsAddtl[0] + ")");
			// insertStmt.execute("INSERT INTO jmeterTableAddtl (Domain, BusinessUnit,
			// ApplicationName) VALUES
			// ('"+testIDAddtl+"','"+testIDAddtl+"','"+testIDAddtl+"')");
			// insertStmt.execute("INSERT INTO jmeterTableAddtl (Domain, BusinessUnit,
			// ApplicationName) VALUES
			// ('"+DomainNTestIDsAddtl[0]+"','"+DomainNTestIDsAddtl[1]+"','"+DomainNTestIDsAddtl[2]+"')");

			/*
			 * insertStmt.execute("INSERT INTO jmeterTableAddtl (" + "Domain,\n" +
			 * "BusinessUnit,\n" + "ApplicationName,\n" + "TestScenario,\n" + "TestID,\n" +
			 * "Label,\n" + "Samples,\n" + "Average,\n" + "Median,\n" +
			 * "90PercentileLine,\n" + "95PercentileLine,\n" + "99PercentileLine,\n" +
			 * "Min,\n" + "Max,\n" + "ErrorPercentage,\n" + "Throughput,\n" +
			 * "ReceivedKBsec,\n" + "SentKBsec) VALUES ('" +testIDAddtl+"','"
			 * +testIDAddtl+"','" +testIDAddtl+"','" +testIDAddtl+"','" +testIDAddtl+"','"
			 * +testIDAddtl+"','" +testIDAddtl+"','" +testIDAddtl+"','" +testIDAddtl+"','"
			 * +testIDAddtl+"','" +testIDAddtl+"','" +testIDAddtl+"','" +testIDAddtl+"','"
			 * +testIDAddtl+"','" +testIDAddtl+"','" +testIDAddtl+"','" +testIDAddtl+"','"
			 * +testIDAddtl+"')");
			 */

			insertStmt.execute("INSERT INTO loadRunnerTableAddtl (" + "Domain,\n" + "BusinessUnit,\n"
					+ "ApplicationName,\n" + "TestScenario,\n" + "TestID,\n" + "TransationName,\n" + "Minimum,\n"
					+ "Average,\n" + "Maximum,\n" + "StdDeviation,\n" + "90PercentileLine,\n" + "Pass,\n" + "Fail,\n"
					+ "Stop) VALUES ('" + DomainNTestIDsAddtl[0] + "','" + DomainNTestIDsAddtl[1] + "','"
					+ DomainNTestIDsAddtl[2] + "','" + DomainNTestIDsAddtl[3] + "','" + DomainNTestIDsAddtl[4] + "','"
					+ loadRTableValues[0] + "','" + loadRTableValues[1] + "','" + loadRTableValues[2] + "','"
					+ loadRTableValues[3] + "','" + loadRTableValues[4] + "','" + loadRTableValues[5] + "','"
					+ loadRTableValues[6] + "','" + loadRTableValues[7] + "','" + loadRTableValues[8] + "')");

			System.out.println("Hello there");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				insertStmt.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/// Addtl

	// public static void insertInTojmeterTableAddtl(ArrayList<String> aDList,
	// String[] jmeterTableValues, String testIDAddtl, String jmeterstartTime) {
	public static void insertInTojmeterTableAddtl(ArrayList<String> aDList, String[] jmeterTableValues,
			String testIDAddtl, int intnumJmeterstartTime) {

		Connection connection = null;
		Statement insertStmt = null;
		String str = null;

		// jmeterstartTime = jmeterstartTime/1000;

		logger.info("\n");
		logger.info("Jmeter Section => Value of jmeterstartTime inside insert method : " + intnumJmeterstartTime);
		logger.info("\n");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");
			insertStmt = connection.createStatement();

//	        insertStmt.execute("INSERT INTO perfDataTable ("
//	        		+ "Domain,\n"
//	        		+ "BusinessUnit,\n"
//	        		+ "ApplicationName,\n"
//	        		+ "TestScenario,\n"
//	        		+ "TestID,\n"
//	        		+ "Label,\n"
//	        		+ "Samples,\n"
//	        		+ "Average,\n"
//	        		+ "Median,\n"
//	        		+ "90PercentileLine,\n"
//	        		+ "95PercentileLine,\n"
//	        		+ "99PercentileLine,\n"
//	        		+ "Min,\n"
//	        		+ "Max,\n"
//	        		+ "ErrorPercentage,\n"
//	        		+ "Throughput,\n"
//	        		+ "ReceivedKBsec,\n"
//	        		+ "SentKBsec) VALUES ('"
//	        		+aDList.get(0)+"','"
//	        		+aDList.get(1)+"','"
//	        		+aDList.get(2)+"','"
//	        		+aDList.get(3)+"','"
//	        		+testIDAddtl+"','"
//	        		+jmeterTableValues[0]+"','"
//	        		+jmeterTableValues[1]+"','"
//	        		+jmeterTableValues[2]+"','"
//	        		+jmeterTableValues[3]+"','"
//	        		+jmeterTableValues[4]+"','"
//	        		+jmeterTableValues[5]+"','"
//	        		+jmeterTableValues[6]+"','"
//	        		+jmeterTableValues[7]+"','"
//	        		+jmeterTableValues[8]+"','"
//	        		+jmeterTableValues[9]+"','"
//	        		+jmeterTableValues[10]+"','"
//	        		+jmeterTableValues[11]+"','"
//	        		+jmeterTableValues[12]+"')");

			insertStmt.execute("INSERT INTO perfDataTableLatest (" + "TestID,\n" + "TestRunID,\n" + "startTime,\n"
					+ "endTime,\n" + "passed,\n" + "Domain,\n" + "ApplicationName,\n" + "BusinessUnit,\n"
					+ "labelName,\n" + "is_parent,\n" + "samples,\n" + "avgResponseTime,\n" + "avgThroughput,\n"
					+ "90line,\n" + "95line,\n" + "minResponseTime,\n" + "maxResponseTime,\n" + "avgBytes,\n"
					+ "errorsRate) VALUES ('" + aDList.get(3) + "','" + testIDAddtl + "','" + intnumJmeterstartTime
					+ "','" + intnumJmeterstartTime + "','" + str + "','" + aDList.get(0) + "','" + aDList.get(2)
					+ "','" + aDList.get(1) + "','" + jmeterTableValues[0] + "','" + str + "','" + jmeterTableValues[1]
					+ "','" + jmeterTableValues[2] + "','" + jmeterTableValues[10] + "','" + jmeterTableValues[4]
					+ "','" + jmeterTableValues[5] + "','" + jmeterTableValues[7] + "','" + jmeterTableValues[8] + "','"
					+ jmeterTableValues[11] + "','" + jmeterTableValues[9] + "')");

			System.out.println("Hello there");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				insertStmt.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public static void insertInTojmeterTableAddtlEnhanced(ArrayList<String> aDList, String[] jmeterTableValues, String testIDAddtl, int intnumJmeterstartTime) {

		Connection connection = null;
		Statement insertStmt = null;
		String str = null;

		// jmeterstartTime = jmeterstartTime/1000;

		logger.info("\n");
		logger.info("Jmeter Section => Value of jmeterstartTime inside insert method : " + intnumJmeterstartTime);
		logger.info("\n");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");
			insertStmt = connection.createStatement();

			insertStmt.execute("INSERT INTO perfDataTableLatestJmeterDebug (" + "TestID,\n" + "TestRunID,\n" + "startTime,\n"
					+ "endTime,\n" + "passed,\n" + "Domain,\n" + "ApplicationName,\n" + "BusinessUnit,\n"
					+ "labelName,\n" + "is_parent,\n" + "samples,\n" + "avgResponseTime,\n" + "avgThroughput,\n"
					+ "90line,\n" + "95line,\n" + "minResponseTime,\n" + "maxResponseTime,\n" + "avgBytes,\n"
					+ "errorsRate) VALUES ('" + aDList.get(3) + "','" + testIDAddtl + "','" + intnumJmeterstartTime
					+ "','" + intnumJmeterstartTime + "','" + str + "','" + aDList.get(0) + "','" + aDList.get(2)
					+ "','" + aDList.get(1) + "','" + jmeterTableValues[0] + "','" + str + "','" + jmeterTableValues[1]
					+ "','" + jmeterTableValues[2] + "','" + jmeterTableValues[10] + "','" + jmeterTableValues[4]
					+ "','" + jmeterTableValues[5] + "','" + jmeterTableValues[7] + "','" + jmeterTableValues[8] + "','"
					+ jmeterTableValues[11] + "','" + jmeterTableValues[9] + "')");

			System.out.println("Hello there");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				insertStmt.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public static void insertInTojmeterTableAddtlMatchingRowsEnhanced(ArrayList<String> aDList, String[] jmeterTableValues, String testIDAddtl, int intnumJmeterstartTime) {

		Connection connection = null;
		Statement insertStmt = null;
		String str = null;

		// jmeterstartTime = jmeterstartTime/1000;

		logger.info("\n");
		logger.info("Jmeter Section => Value of jmeterstartTime inside insert method : " + intnumJmeterstartTime);
		logger.info("\n");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");
			insertStmt = connection.createStatement();

			insertStmt.execute("INSERT INTO perfDataTableLatestJmeterDebugMatchingRows (" + "TestID,\n" + "TestRunID,\n" + "startTime,\n"
					+ "endTime,\n" + "passed,\n" + "Domain,\n" + "ApplicationName,\n" + "BusinessUnit,\n"
					+ "labelName,\n" + "is_parent,\n" + "samples,\n" + "avgResponseTime,\n" + "avgThroughput,\n"
					+ "90line,\n" + "95line,\n" + "minResponseTime,\n" + "maxResponseTime,\n" + "avgBytes,\n"
					+ "errorsRate) VALUES ('" + aDList.get(3) + "','" + testIDAddtl + "','" + intnumJmeterstartTime
					+ "','" + intnumJmeterstartTime + "','" + str + "','" + aDList.get(0) + "','" + aDList.get(2)
					+ "','" + aDList.get(1) + "','" + jmeterTableValues[0] + "','" + str + "','" + jmeterTableValues[1]
					+ "','" + jmeterTableValues[2] + "','" + jmeterTableValues[10] + "','" + jmeterTableValues[4]
					+ "','" + jmeterTableValues[5] + "','" + jmeterTableValues[7] + "','" + jmeterTableValues[8] + "','"
					+ jmeterTableValues[11] + "','" + jmeterTableValues[9] + "')");

			System.out.println("Hello there");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				insertStmt.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public static ArrayList<String> selectJmeterTableAddtlEnhanced(ArrayList<String> aDList, String[] jmeterTableValues, String testIDAddtl, int intnumJmeterstartTime) {

		Connection connection = null;
		Statement selectStmt = null;
		String str = null;

		// jmeterstartTime = jmeterstartTime/1000;

		logger.info("\n");
		logger.info("Jmeter Section => Value of jmeterstartTime inside insert method : " + intnumJmeterstartTime);
		logger.info("\n");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");
			selectStmt = connection.createStatement();
			
			String jmeterQuery1 = "SELECT TestID, TestRunID, startTime, endTime, passed, Domain, ApplicationName, BusinessUnit,";
			String jmeterQuery2 = "labelName, is_parent, samples, avgResponseTime, avgThroughput, 90line, 95line, minResponseTime,";
			String jmeterQuery3 = "maxResponseTime, avgBytes, errorsRate from perfDataTableLatestJmeterDebug where TestID = ";
			String jmeterQuery4 = "\"" +  aDList.get(3) + "\"";
			String jmeterQuery5 = " and TestRunID = ";
			String jmeterQuery6 = "\"" +  testIDAddtl + "\"";
			String jmeterQuery7 = " and startTime = ";
			String jmeterQuery8 = "\"" +  intnumJmeterstartTime + "\"";
			String jmeterQuery9 = " and endTime = ";
			String jmeterQuery10 = "\"" +  intnumJmeterstartTime + "\"";
			
			String jmeterQuery9a = " and passed = ";
			String jmeterQuery10a = "\"" +  str + "\"";
			
			
			String jmeterQuery11 = " and Domain = ";
			String jmeterQuery12 = "\"" +  aDList.get(0) + "\"";
			
			String jmeterQuery13 = " and ApplicationName = ";
			String jmeterQuery14 = "\"" +  aDList.get(2) + "\"";
			
			String jmeterQuery15 = " and BusinessUnit = ";
			String jmeterQuery16 = "\"" +  aDList.get(1) + "\"";
			
			String jmeterQuery17 = " and labelName = ";
			String jmeterQuery18 = "\"" +  jmeterTableValues[0] + "\"";
			
			String jmeterQuery19 = " and is_parent = ";
			String jmeterQuery20 = "\"" +  str + "\"";
			
			String jmeterQuery21 = " and samples = ";
			String jmeterQuery22 = "\"" +  jmeterTableValues[1] + "\"";
			
			String jmeterQuery23 = " and avgResponseTime = ";
			String jmeterQuery24 = "\"" +  jmeterTableValues[2] + "\"";
			
			String jmeterQuery25 = " and avgThroughput = ";
			String jmeterQuery26 = "\"" +  jmeterTableValues[10] + "\"";
			
			String jmeterQuery27 = " and 90line = ";
			String jmeterQuery28 = "\"" +  jmeterTableValues[4] + "\"";
			
			String jmeterQuery29 = " and 95line = ";
			String jmeterQuery30 = "\"" +  jmeterTableValues[5] + "\"";
			
			String jmeterQuery31 = " and minResponseTime = ";
			String jmeterQuery32 = "\"" +  jmeterTableValues[7] + "\"";
			
			String jmeterQuery33 = " and maxResponseTime = ";
			String jmeterQuery34 = "\"" +  jmeterTableValues[8] + "\"";
			
			String jmeterQuery35 = " and avgBytes = ";
			String jmeterQuery36 = "\"" +  jmeterTableValues[11] + "\"";
			
			String jmeterQuery37 = " and errorsRate = ";
			String jmeterQuery38 = "\"" +  jmeterTableValues[9] + "\"";
			
			String jmeterQueryFinal1 = jmeterQuery1 + jmeterQuery2 + jmeterQuery3 + jmeterQuery4 + jmeterQuery5 + jmeterQuery6 + jmeterQuery7 + jmeterQuery8 + jmeterQuery9;
			String jmeterQueryFinal2 = jmeterQuery10 + jmeterQuery9a + jmeterQuery10a + jmeterQuery11 + jmeterQuery12 + jmeterQuery13 + jmeterQuery14 + jmeterQuery15 + jmeterQuery16 + jmeterQuery17;
			String jmeterQueryFinal3 = jmeterQuery18 + jmeterQuery19 + jmeterQuery20 + jmeterQuery21 + jmeterQuery22 + jmeterQuery23 + jmeterQuery24 + jmeterQuery25;
			String jmeterQueryFinal4 = jmeterQuery26 + jmeterQuery27 + jmeterQuery28 + jmeterQuery29 + jmeterQuery30 + jmeterQuery31 + jmeterQuery32 + jmeterQuery33;
			String jmeterQueryFinal5 = jmeterQuery34 + jmeterQuery35 + jmeterQuery36 + jmeterQuery37 + jmeterQuery38;
			
			String jmeterQueryFinal = jmeterQueryFinal1 + jmeterQueryFinal2 + jmeterQueryFinal3 + jmeterQueryFinal4 + jmeterQueryFinal5;
			
			System.out.println("jmeterQueryFinal : " + jmeterQueryFinal);
			System.out.println("Done");

			ResultSet results = selectStmt.executeQuery(jmeterQueryFinal);
			ResultSetMetaData rsmd = results.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			
			selectJmeterARList.clear();
			//System.out.println("arList is : " + arList);
			//System.out.println("First");
			int checkCount = 0;
			
		    int columnCount = results.getMetaData().getColumnCount();
		    while((results.next()) && (checkCount == 0)) {
		    	   for (int i = 1; i <= columnCount; i++) {
		    		   selectJmeterARList.add(results.getString(i));
		           }
		    	   
		    	   checkCount++;
		    	   
		    }

			System.out.println("Hello there");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				selectStmt.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return selectJmeterARList;

	}
	
	public static ArrayList<String> constructJmeterTableAddtlEnhanced(ArrayList<String> aDList, String[] jmeterTableValues, String testIDAddtl, int intnumJmeterstartTime) {

		// apiARJmeterListLoad
		
	    String nullValue = "null";
	    //System.out.println("Value of nullValue is as follows : " + nullValue);
	    
	    apiARJmeterListLoad.clear();
	

		try {
		
		apiARJmeterListLoad.add(aDList.get(3));
		apiARJmeterListLoad.add(testIDAddtl);
		apiARJmeterListLoad.add(String.valueOf(intnumJmeterstartTime));
		apiARJmeterListLoad.add(String.valueOf(intnumJmeterstartTime));
		apiARJmeterListLoad.add("" +  nullValue + "");
		apiARJmeterListLoad.add(aDList.get(0));
		apiARJmeterListLoad.add(aDList.get(2));
		apiARJmeterListLoad.add(aDList.get(1));
		apiARJmeterListLoad.add(jmeterTableValues[0]);
		apiARJmeterListLoad.add("" +  nullValue + "");
		apiARJmeterListLoad.add(jmeterTableValues[1]);
		apiARJmeterListLoad.add(jmeterTableValues[2]);
		apiARJmeterListLoad.add(jmeterTableValues[10]);
		apiARJmeterListLoad.add(jmeterTableValues[4]);
		apiARJmeterListLoad.add(jmeterTableValues[5]);
		apiARJmeterListLoad.add(jmeterTableValues[7]);
		apiARJmeterListLoad.add(jmeterTableValues[8]);
		apiARJmeterListLoad.add(jmeterTableValues[11]);
		apiARJmeterListLoad.add(jmeterTableValues[9]);
		
		System.out.println("output of apiARJmeterListLoad : " + apiARJmeterListLoad);
		System.out.println("Done");


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return apiARJmeterListLoad;

	}
	
	
	public static void insertInTojmeterTableAddtlEnhancedMatchingRows(ArrayList<String> aDList, String[] jmeterTableValues, String testIDAddtl, int intnumJmeterstartTime) {

		Connection connection = null;
		Statement insertStmt = null;
		String str = null;

		// jmeterstartTime = jmeterstartTime/1000;

		logger.info("\n");
		logger.info("Jmeter Section => Value of jmeterstartTime inside insert method : " + intnumJmeterstartTime);
		logger.info("\n");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");
			insertStmt = connection.createStatement();

			insertStmt.execute("INSERT INTO perfDataTableLatestJmeterDebugMatchingRows (" + "TestID,\n" + "TestRunID,\n" + "startTime,\n"
					+ "endTime,\n" + "passed,\n" + "Domain,\n" + "ApplicationName,\n" + "BusinessUnit,\n"
					+ "labelName,\n" + "is_parent,\n" + "samples,\n" + "avgResponseTime,\n" + "avgThroughput,\n"
					+ "90line,\n" + "95line,\n" + "minResponseTime,\n" + "maxResponseTime,\n" + "avgBytes,\n"
					+ "errorsRate) VALUES ('" + aDList.get(3) + "','" + testIDAddtl + "','" + intnumJmeterstartTime
					+ "','" + intnumJmeterstartTime + "','" + str + "','" + aDList.get(0) + "','" + aDList.get(2)
					+ "','" + aDList.get(1) + "','" + jmeterTableValues[0] + "','" + str + "','" + jmeterTableValues[1]
					+ "','" + jmeterTableValues[2] + "','" + jmeterTableValues[10] + "','" + jmeterTableValues[4]
					+ "','" + jmeterTableValues[5] + "','" + jmeterTableValues[7] + "','" + jmeterTableValues[8] + "','"
					+ jmeterTableValues[11] + "','" + jmeterTableValues[9] + "')");

			System.out.println("Hello there");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				insertStmt.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// public static void insertInToloadRunnerTableAddtl(ArrayList<String> aDList,
	// String[] loadRunnerTableValues, String testIDAddtl, String
	// loadRunnerstartTime) {
	public static void insertInToloadRunnerTableAddtl(ArrayList<String> aDList, String[] loadRunnerTableValues,
			String testIDAddtl, int loadRunnerstartTime) {

		Connection connection = null;
		Statement insertStmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");
			insertStmt = connection.createStatement();
			String str = "null";

//	        insertStmt.execute("INSERT INTO perfDataTable ("
//	        		+ "Domain,\n"
//	        		+ "BusinessUnit,\n"
//	        		+ "ApplicationName,\n"
//	        		+ "TestScenario,\n"
//	        		+ "TestID,\n"
//	        		+ "Label,\n"
//	        		+ "Samples,\n"
//	        		+ "Average,\n"
//	        		+ "Median,\n"
//	        		+ "90PercentileLine,\n"
//	        		+ "95PercentileLine,\n"
//	        		+ "99PercentileLine,\n"
//	        		+ "Min,\n"
//	        		+ "Max,\n"
//	        		+ "ErrorPercentage,\n"
//	        		+ "Throughput,\n"
//	        		+ "ReceivedKBsec,\n"
//	        		+ "SentKBsec) VALUES ('"
//	        		+aDList.get(0)+"','"
//	        		+aDList.get(1)+"','"
//	        		+aDList.get(2)+"','"
//	        		+aDList.get(3)+"','"
//	        		+testIDAddtl+"','"
//	        		+loadRunnerTableValues[0]+"','"
//	        		+loadRunnerTableValues[1]+"','"
//	        		+loadRunnerTableValues[2]+"','"
//	        		+loadRunnerTableValues[3]+"','"
//	        		+loadRunnerTableValues[4]+"','"
//	        		+loadRunnerTableValues[5]+"','"
//	        		+loadRunnerTableValues[6]+"','"
//	        		+loadRunnerTableValues[7]+"','"
//	        		+loadRunnerTableValues[8]+"','"
//	        		+loadRunnerTableValues[9]+"','"
//	        		+loadRunnerTableValues[10]+"','"
//	        		+loadRunnerTableValues[11]+"','"
//	        		+loadRunnerTableValues[12]+"')");

			float avgResponseTime = Float.parseFloat(loadRunnerTableValues[2]) * 1000;
			float ninethLine = Float.parseFloat(loadRunnerTableValues[5]) * 1000;
			float minReponse = Float.parseFloat(loadRunnerTableValues[1]) * 1000;
			float maxResponse = Float.parseFloat(loadRunnerTableValues[3]) * 1000;

			logger.info("loadRunnerstartTime - that is being passed from within insert : " + loadRunnerstartTime);

			insertStmt.execute("INSERT INTO perfDataTableLatest (" + "TestID,\n" + "TestRunID,\n" + "startTime,\n"
					+ "endTime,\n" + "passed,\n" + "Domain,\n" + "ApplicationName,\n" + "BusinessUnit,\n"
					+ "labelName,\n" + "is_parent,\n" + "samples,\n" + "avgResponseTime,\n" + "avgThroughput,\n"
					+ "90line,\n" + "95line,\n" + "minResponseTime,\n" + "maxResponseTime,\n" + "avgBytes,\n"
					+ "errorsRate) VALUES ('" + aDList.get(3) + "','" + testIDAddtl + "','" + loadRunnerstartTime
					+ "','"
					// +str+"','"
					+ loadRunnerstartTime + "','" + loadRunnerTableValues[6] + "','" + aDList.get(0) + "','"
					+ aDList.get(2) + "','" + aDList.get(1) + "','" + loadRunnerTableValues[0] + "','" + str + "','"
					+ loadRunnerTableValues[6] + "','"
					// +loadRunnerTableValues[2]+"','"
					+ avgResponseTime + "','" + str + "','"
					// +loadRunnerTableValues[5]+"','"
					+ ninethLine + "','" + str + "','"
					// +loadRunnerTableValues[1]+"','"
					+ minReponse + "','"
					// +loadRunnerTableValues[3]+"','"
					+ maxResponse + "','" + str + "','" + str + "')");

			System.out.println("Hello there");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				insertStmt.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	
	public static void insertInToloadRunnerTableAddtlEnhanced(ArrayList<String> aDList, String[] loadRunnerTableValues,
			String testIDAddtl, int loadRunnerstartTime) {

		if(aDList.get(0).contains("Domain2")) {
			System.out.println("Domain2 within insertInToloadRunnerTableAddtlEnhanced");
		}
		
		if(aDList.get(0).contains("Domain3")) {
			System.out.println("Domain3 within insertInToloadRunnerTableAddtlEnhanced");
		}
		Connection connection = null;
		Statement insertStmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");
			insertStmt = connection.createStatement();
			String str = "null";

			float avgResponseTime = Float.parseFloat(loadRunnerTableValues[2]) * 1000;
			float ninethLine = Float.parseFloat(loadRunnerTableValues[5]) * 1000;
			float minReponse = Float.parseFloat(loadRunnerTableValues[1]) * 1000;
			float maxResponse = Float.parseFloat(loadRunnerTableValues[3]) * 1000;

			logger.info("loadRunnerstartTime - that is being passed from within insert : " + loadRunnerstartTime);

			insertStmt.execute("INSERT INTO perfDataTableLatestLoadRunnerDebug (" + "TestID,\n" + "TestRunID,\n" + "startTime,\n"
					+ "endTime,\n" + "passed,\n" + "Domain,\n" + "ApplicationName,\n" + "BusinessUnit,\n"
					+ "labelName,\n" + "is_parent,\n" + "samples,\n" + "avgResponseTime,\n" + "avgThroughput,\n"
					+ "90line,\n" + "95line,\n" + "minResponseTime,\n" + "maxResponseTime,\n" + "avgBytes,\n"
					+ "errorsRate) VALUES ('" + aDList.get(3) + "','" + testIDAddtl + "','" + loadRunnerstartTime
					+ "','"
					// +str+"','"
					+ loadRunnerstartTime + "','" + loadRunnerTableValues[6] + "','" + aDList.get(0) + "','"
					+ aDList.get(2) + "','" + aDList.get(1) + "','" + loadRunnerTableValues[0] + "','" + str + "','"
					+ loadRunnerTableValues[6] + "','"
					// +loadRunnerTableValues[2]+"','"
					+ avgResponseTime + "','" + str + "','"
					// +loadRunnerTableValues[5]+"','"
					+ ninethLine + "','" + str + "','"
					// +loadRunnerTableValues[1]+"','"
					+ minReponse + "','"
					// +loadRunnerTableValues[3]+"','"
					+ maxResponse + "','" + str + "','" + str + "')");

			System.out.println("Hello there");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				insertStmt.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	
	public static void insertInToloadRunnerTableAddtlEnhancedMatchedRows(ArrayList<String> aDList, String[] loadRunnerTableValues,
			String testIDAddtl, int loadRunnerstartTime) {
		
		if(aDList.get(0).contains("Domain2")) {
			System.out.println("insert domain 2");
		}
		
		if(aDList.get(0).contains("Domain3")) {
			System.out.println("insert domain 3");
		}

		Connection connection = null;
		Statement insertStmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");
			insertStmt = connection.createStatement();
			String str = "null";

			float avgResponseTime = Float.parseFloat(loadRunnerTableValues[2]) * 1000;
			float ninethLine = Float.parseFloat(loadRunnerTableValues[5]) * 1000;
			float minReponse = Float.parseFloat(loadRunnerTableValues[1]) * 1000;
			float maxResponse = Float.parseFloat(loadRunnerTableValues[3]) * 1000;

			logger.info("loadRunnerstartTime - that is being passed from within insert : " + loadRunnerstartTime);

			insertStmt.execute("INSERT INTO perfDataTableLatestLoadRunnerDebugMatchingRows (" + "TestID,\n" + "TestRunID,\n" + "startTime,\n"
					+ "endTime,\n" + "passed,\n" + "Domain,\n" + "ApplicationName,\n" + "BusinessUnit,\n"
					+ "labelName,\n" + "is_parent,\n" + "samples,\n" + "avgResponseTime,\n" + "avgThroughput,\n"
					+ "90line,\n" + "95line,\n" + "minResponseTime,\n" + "maxResponseTime,\n" + "avgBytes,\n"
					+ "errorsRate) VALUES ('" + aDList.get(3) + "','" + testIDAddtl + "','" + loadRunnerstartTime
					+ "','"
					// +str+"','"
					+ loadRunnerstartTime + "','" + loadRunnerTableValues[6] + "','" + aDList.get(0) + "','"
					+ aDList.get(2) + "','" + aDList.get(1) + "','" + loadRunnerTableValues[0] + "','" + str + "','"
					+ loadRunnerTableValues[6] + "','"
					// +loadRunnerTableValues[2]+"','"
					+ avgResponseTime + "','" + str + "','"
					// +loadRunnerTableValues[5]+"','"
					+ ninethLine + "','" + str + "','"
					// +loadRunnerTableValues[1]+"','"
					+ minReponse + "','"
					// +loadRunnerTableValues[3]+"','"
					+ maxResponse + "','" + str + "','" + str + "')");

			System.out.println("Hello there");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				insertStmt.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	
	public static ArrayList<String> selectInToloadRunnerTableAddtlEnhanced(ArrayList<String> aDList, String[] loadRunnerTableValues,String testIDAddtl, int loadRunnerstartTime) {

		Connection connection = null;
		Statement selectStmt = null;
		
		selectLoadRunnerARList.clear();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");
			selectStmt = connection.createStatement();
			String str = "null";

			float avgResponseTime = Float.parseFloat(loadRunnerTableValues[2]) * 1000;
			float ninethLine = Float.parseFloat(loadRunnerTableValues[5]) * 1000;
			float minReponse = Float.parseFloat(loadRunnerTableValues[1]) * 1000;
			float maxResponse = Float.parseFloat(loadRunnerTableValues[3]) * 1000;

			logger.info("loadRunnerstartTime - that is being passed from within insert : " + loadRunnerstartTime);
			
			
//			String loadRunnerQuery1 = "SELECT TestID, TestRunID, startTime, endTime, passed, Domain, ApplicationName, BusinessUnit,labelName, is_parent, samples, avgResponseTime, ";
//			String loadRunnerQuery2 = "avgThroughput, 90line, 95line, minResponseTime,maxResponseTime, avgBytes, errorsRate from perfDataTableLatestJmeterDebug where TestID = " ;
//			String loadRunnerQuery3 = "\"" +  aDList.get(3) + "\"";
			
			
			String loadRunnerQuery1 = "SELECT TestID, TestRunID, startTime, endTime, passed, Domain, ApplicationName, BusinessUnit,";
			String loadRunnerQuery2 = "labelName, is_parent, samples, avgResponseTime, avgThroughput, 90line, 95line, minResponseTime,";
			String loadRunnerQuery3 = "maxResponseTime, avgBytes, errorsRate from perfDataTableLatestLoadRunnerDebug where TestID = ";
			String loadRunnerQuery4 = "\"" +  aDList.get(3) + "\"";
			String loadRunnerQuery5 = " and TestRunID = ";
			String loadRunnerQuery6 = "\"" +  testIDAddtl + "\"";
			String loadRunnerQuery7 = " and startTime = ";
			String loadRunnerQuery8 = "\"" +  loadRunnerstartTime + "\"";
			String loadRunnerQuery9 = " and endTime = ";
			String loadRunnerQuery10 = "\"" +  loadRunnerstartTime + "\"";
			
			String loadRunnerQuery9a = " and passed = ";
			String loadRunnerQuery10a = "\"" +  loadRunnerTableValues[6] + "\"";
			
			
			String loadRunnerQuery11 = " and Domain = ";
			String loadRunnerQuery12 = "\"" +  aDList.get(0) + "\"";
			
			String loadRunnerQuery13 = " and ApplicationName = ";
			String loadRunnerQuery14 = "\"" +  aDList.get(2) + "\"";
			
			String loadRunnerQuery15 = " and BusinessUnit = ";
			String loadRunnerQuery16 = "\"" +  aDList.get(1) + "\"";
			
			String loadRunnerQuery17 = " and labelName = ";
			String loadRunnerQuery18 = "\"" +  loadRunnerTableValues[0] + "\"";
			
			String loadRunnerQuery19 = " and is_parent = ";
			String loadRunnerQuery20 = "\"" +  str + "\"";
			
			String loadRunnerQuery21 = " and samples = ";
			String loadRunnerQuery22 = "\"" +  loadRunnerTableValues[6] + "\"";
			
			String loadRunnerQuery23 = " and avgResponseTime = ";
			String loadRunnerQuery24 = "\"" +  avgResponseTime + "\"";
			
			String loadRunnerQuery25 = " and avgThroughput = ";
			String loadRunnerQuery26 = "\"" +  str + "\"";
			
			String loadRunnerQuery27 = " and 90line = ";
			String loadRunnerQuery28 = "\"" +  ninethLine + "\"";
			
			String loadRunnerQuery29 = " and 95line = ";
			String loadRunnerQuery30 = "\"" +  str + "\"";
			
			String loadRunnerQuery31 = " and minResponseTime = ";
			String loadRunnerQuery32 = "\"" +  minReponse + "\"";
			
			String loadRunnerQuery33 = " and maxResponseTime = ";
			String loadRunnerQuery34 = "\"" +  maxResponse + "\"";
			
			String loadRunnerQuery35 = " and avgBytes = ";
			String loadRunnerQuery36 = "\"" +  str + "\"";
			
			String loadRunnerQuery37 = " and errorsRate = ";
			String loadRunnerQuery38 = "\"" +  str + "\"";
			
			String loadRunnerQueryFinal1 = loadRunnerQuery1 + loadRunnerQuery2 + loadRunnerQuery3 + loadRunnerQuery4 + loadRunnerQuery5 + loadRunnerQuery6 + loadRunnerQuery7 + loadRunnerQuery8 + loadRunnerQuery9;
			String loadRunnerQueryFinal2 = loadRunnerQuery10 + loadRunnerQuery9a + loadRunnerQuery10a + loadRunnerQuery11 + loadRunnerQuery12 + loadRunnerQuery13 + loadRunnerQuery14 + loadRunnerQuery15 + loadRunnerQuery16 + loadRunnerQuery17;
			String loadRunnerQueryFinal3 = loadRunnerQuery18 + loadRunnerQuery19 + loadRunnerQuery20 + loadRunnerQuery21 + loadRunnerQuery22 + loadRunnerQuery23 + loadRunnerQuery24 + loadRunnerQuery25;
			String loadRunnerQueryFinal4 = loadRunnerQuery26 + loadRunnerQuery27 + loadRunnerQuery28 + loadRunnerQuery29 + loadRunnerQuery30 + loadRunnerQuery31 + loadRunnerQuery32 + loadRunnerQuery33;
			String loadRunnerQueryFinal5 = loadRunnerQuery34 + loadRunnerQuery35 + loadRunnerQuery36 + loadRunnerQuery37 + loadRunnerQuery38;
			
			String loadRunnerQueryFinal = loadRunnerQueryFinal1 + loadRunnerQueryFinal2 + loadRunnerQueryFinal3 + loadRunnerQueryFinal4 + loadRunnerQueryFinal5;
			
			System.out.println("loadRunnerQueryFinal : " + loadRunnerQueryFinal);
			System.out.println("Done");
			

			ResultSet results = selectStmt.executeQuery(loadRunnerQueryFinal);
			ResultSetMetaData rsmd = results.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			
			int checkCount = 0;
		    int columnCount = results.getMetaData().getColumnCount();
		    while((results.next()) && (checkCount == 0)) {
		    	   for (int i = 1; i <= columnCount; i++) {
		    		   selectLoadRunnerARList.add(results.getString(i));
		           }
		    	   
		    	   checkCount++;
		    	   
		    }

			System.out.println("Hello there");
			
			

			System.out.println("Hello there");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				selectStmt.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return selectLoadRunnerARList;

	}

	public static ArrayList<String> apiARLoadRunnerTableAddtlEnhanced(ArrayList<String> aDList, String[] loadRunnerTableValues,String testIDAddtl, int loadRunnerstartTime) {

		String nullValue = "null";
		apiARLoadrunnerListLoad.clear();
		float avgResponseTime = Float.parseFloat(loadRunnerTableValues[2]) * 1000;
		float ninethLine = Float.parseFloat(loadRunnerTableValues[5]) * 1000;
		float minReponse = Float.parseFloat(loadRunnerTableValues[1]) * 1000;
		float maxResponse = Float.parseFloat(loadRunnerTableValues[3]) * 1000;
		
		try {
			
			apiARLoadrunnerListLoad.add(aDList.get(3));
			apiARLoadrunnerListLoad.add(testIDAddtl);
			apiARLoadrunnerListLoad.add(String.valueOf(loadRunnerstartTime));
			apiARLoadrunnerListLoad.add(String.valueOf(loadRunnerstartTime));
			apiARLoadrunnerListLoad.add(loadRunnerTableValues[6]);
			apiARLoadrunnerListLoad.add(aDList.get(0));
			apiARLoadrunnerListLoad.add(aDList.get(2));
			apiARLoadrunnerListLoad.add(aDList.get(1));
			apiARLoadrunnerListLoad.add(loadRunnerTableValues[0]);
			apiARLoadrunnerListLoad.add("" +  nullValue + "");
			apiARLoadrunnerListLoad.add(loadRunnerTableValues[6]);
			apiARLoadrunnerListLoad.add(String.valueOf(avgResponseTime));
			apiARLoadrunnerListLoad.add("" +  nullValue + "");
			apiARLoadrunnerListLoad.add(String.valueOf(ninethLine));
			apiARLoadrunnerListLoad.add("" +  nullValue + "");
			apiARLoadrunnerListLoad.add(String.valueOf(minReponse));
			apiARLoadrunnerListLoad.add(String.valueOf(maxResponse));
			apiARLoadrunnerListLoad.add("" +  nullValue + "");
			apiARLoadrunnerListLoad.add("" +  nullValue + "");
			


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return apiARLoadrunnerListLoad;

	}

	
	public static void insertInToBlazeMeterTableAddtl(ArrayList<String> aDList, String[] blazeMeterValues,
			String testIDAddtl, String TestRunnerID, int intCreated, int intEnded) {

		Connection connection = null;
		Statement insertStmt = null;
		String str = null;

		System.out.println("Start");
		System.out.println("Domain : " + aDList.get(0) + " BusinessUnit : " + aDList.get(1) + " ApplicationName : "
				+ aDList.get(2) + " TestScenario : " + aDList.get(3) + " TestID : " + aDList.get(4));
		System.out.println("End");

		for (int xcv = 0; xcv < blazeMeterValues.length; xcv++) {
			System.out.println("Start blazeMeterValues");
			System.out.println("Whole answer : " + blazeMeterValues[xcv]);
			System.out.println("End blazeMeterValues");
		}

		System.out.println("Done blazeMeterValues");

		logger.info("Blazemeter intCreated time : " + intCreated);
		logger.info("Blazemeter intEnded time : " + intEnded);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");
			insertStmt = connection.createStatement();

			insertStmt.execute("INSERT INTO perfDataTableLatest (" + "TestID,\n" + "TestRunID,\n" + "startTime,\n"
					+ "endTime,\n" + "passed,\n" + "Domain,\n" + "ApplicationName,\n" + "BusinessUnit,\n"
					+ "labelName,\n" + "is_parent,\n" + "samples,\n" + "avgResponseTime,\n" + "avgThroughput,\n"
					+ "90line,\n" + "95line,\n" + "minResponseTime,\n" + "maxResponseTime,\n" + "avgBytes,\n"
					+ "errorsRate) VALUES ('" + testIDAddtl + "','" + TestRunnerID + "','"
					// +str+"','"
					+ intCreated + "','"
					// +str+"','"
					+ intEnded + "','" + str + "','" + aDList.get(0) + "','" + aDList.get(2) + "','" + aDList.get(1)
					+ "','" + blazeMeterValues[1] + "','" + str + "','" + blazeMeterValues[2] + "','"
					+ blazeMeterValues[3] + "','" + blazeMeterValues[4] + "','" + blazeMeterValues[5] + "','"
					+ blazeMeterValues[6] + "','" + blazeMeterValues[7] + "','" + blazeMeterValues[8] + "','"
					+ blazeMeterValues[9] + "','" + blazeMeterValues[10] + "')");

			System.out.println("Hello there");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				insertStmt.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static Map<String, String> selectBlazeMeterTableAddtl() {

		Connection connection = null;
		Statement selectStmt = null;
		String maxTestRunID = null;
		String combinedRow = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");
			selectStmt = connection.createStatement();

			ResultSet rsA = selectStmt.executeQuery("select max(TestRunID) from perfDataTableLatestAddtlDebug");
			while (rsA.next()) {
				System.out.println("select max(TestRunID) : " + rsA.getString(1));
				maxTestRunID = rsA.getString(1);

			}

		// 61694752
		// select DISTINCT TestID, TestRunID, TestScenario from
		// perfDataTableLatestAddtlDebug where TestRunID =
			
		ResultSet rs = selectStmt.executeQuery(
				"select DISTINCT TestID, TestRunID, TestScenario from perfDataTableLatestAddtlDebug where TestRunID = \""
						+ maxTestRunID + "\"");
		while (rs.next()) {
			System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
			combinedRow = rs.getString(1) + "_" + rs.getString(3);
			mapLocalDBBuilder.put(rs.getString(2), combinedRow);

		}
		
	}catch(

	Exception e)
	{
			e.printStackTrace();
		}finally
	{
		try {
			selectStmt.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	return mapLocalDBBuilder;

	}
	
	
	public static Map<String, String> selectBlazeMeterTableAddtlwithValues(String TestID, String TestRunnerID) {

		Connection connection = null;
		Statement selectStmt = null;
		String maxTestRunID = null;
		String combinedRow = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");
			selectStmt = connection.createStatement();

			
			String sqlQueryLat1 = "select DISTINCT TestID, TestRunID, TestScenario from perfDataTableLatestAddtlDebug where TestRunID =";
			String sqlQueryLat2 = "\"" +  TestRunnerID + "\"";
			String sqlQueryLat3 = " and TestID = ";
			String sqlQueryLat4 = "\"" +  TestID + "\"";
			String sqlQueryLatFinal = sqlQueryLat1 + sqlQueryLat2 + sqlQueryLat3 + sqlQueryLat4;
			//ResultSet rs = selectStmt.executeQuery("select DISTINCT TestID, TestRunID, TestScenario from perfDataTableLatestAddtlDebug where TestRunID = \""+ maxTestRunID + "\"");
			System.out.println("Query : " + sqlQueryLatFinal);
			ResultSet rs = selectStmt.executeQuery(sqlQueryLatFinal);
			
			while (rs.next()) {
			System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
			combinedRow = rs.getString(1) + "_" + rs.getString(3);
			System.out.println("Before rs.getString(2) : " + rs.getString(2));
			System.out.println("Before combinedRow : " + combinedRow);
			System.out.println("Before mapLocalDBBuilderSelect : " + mapLocalDBBuilderSelect);
			mapLocalDBBuilderSelect.clear();
			mapLocalDBBuilderSelect.put(rs.getString(2), combinedRow);
			System.out.println("After mapLocalDBBuilderSelect : " + mapLocalDBBuilderSelect);
			System.out.println("After combinedRow : " + combinedRow);

		}
		
	}catch(

	Exception e)
	{
			e.printStackTrace();
		}finally
	{
		try {
			selectStmt.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	return mapLocalDBBuilderSelect;

	}

	public static void insertInToBlazeMeterTableAddtlLatest(ArrayList<String> aDList, String[] blazeMeterValues, String testIDAddtl, String TestRunnerID, int intCreated, int intEnded) {
		
	    Connection connection = null;
	    Statement insertStmt = null;
	    String str = null;
	    
	    System.out.println("Start");
	    System.out.println("Domain : " + aDList.get(0) + " BusinessUnit : " + aDList.get(1) + " ApplicationName : " + aDList.get(2) + " TestScenario : " + aDList.get(3) + " TestID : " + aDList.get(4));
	    System.out.println("End");
	    
	    for(int xcv = 0; xcv < blazeMeterValues.length; xcv++) {
	    	System.out.println("Start blazeMeterValues");
	    	System.out.println("Whole answer : " + blazeMeterValues[xcv]);
	    	System.out.println("End blazeMeterValues");
	    }
	    
	    System.out.println("Done blazeMeterValues");
	    
	    logger.info("Blazemeter intCreated time : " + intCreated);
	    logger.info("Blazemeter intEnded time : " + intEnded);
	    		
	    try
	    {
	      	Class.forName("com.mysql.jdbc.Driver");
	      	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");  	      	      
	        insertStmt = connection.createStatement();        
 	        	        
	        insertStmt.execute("INSERT INTO perfDataTableLatestAddtl ("
	        		+ "TestID,\n"
	        		+ "TestRunID,\n"
	        		+ "startTime,\n"
	        		+ "endTime,\n"
	        		+ "passed,\n"
	        		+ "Domain,\n"
	        		+ "ApplicationName,\n"
	        		+ "BusinessUnit,\n"
	        		+ "labelName,\n"
	        		+ "is_parent,\n"
	        		+ "samples,\n"
	        		+ "avgResponseTime,\n"
	        		+ "avgThroughput,\n"
	        		+ "90line,\n"
	        		+ "95line,\n"
	        		+ "minResponseTime,\n"
	        		+ "maxResponseTime,\n"
	        		+ "avgBytes,\n"
					+ "errorsRate,\n"
					+ "TestScenario) VALUES ('"
	        		+testIDAddtl+"','"
	        		+TestRunnerID+"','"
	        		//+str+"','"
	        		+intCreated+"','"
					//+str+"','"
					+intEnded+"','"
	        		+str+"','"
	        		+aDList.get(0)+"','"
	        		+aDList.get(2)+"','"
	        		+aDList.get(1)+"','"
	        		+blazeMeterValues[1]+"','"
					+str+"','"
	        		+blazeMeterValues[2]+"','"
	        		+blazeMeterValues[3]+"','"
					+blazeMeterValues[4]+"','"
	        		+blazeMeterValues[5]+"','"
					+blazeMeterValues[6]+"','"
	        		+blazeMeterValues[7]+"','"
	        		+blazeMeterValues[8]+"','"
					+blazeMeterValues[9]+"','"
					+blazeMeterValues[10]+"','"
					+aDList.get(4)+"')");
	        	
	        	System.out.println("Hello there");
	        
	    } 
	    catch (Exception e) {
	      e.printStackTrace();
	    }finally {
	      try {
	        insertStmt.close();
	        connection.close();
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	    }
		
	}

	public static void insertInToBlazeMeterTableAddtlLatestDebug(ArrayList<String> aDList, String[] blazeMeterValues, String testIDAddtl, String TestRunnerID, int intCreated, int intEnded, String Pre_Ended_n_TestID_Split) {
		
	    Connection connection = null;
	    Statement insertStmt = null;
	    String str = null;
	    
	    System.out.println("Start");
	    System.out.println("Domain : " + aDList.get(0) + " BusinessUnit : " + aDList.get(1) + " ApplicationName : " + aDList.get(2) + " TestScenario : " + aDList.get(3) + " TestID : " + aDList.get(4));
	    System.out.println("End");
	    
	    for(int xcv = 0; xcv < blazeMeterValues.length; xcv++) {
	    	System.out.println("Start blazeMeterValues");
	    	System.out.println("Whole answer : " + blazeMeterValues[xcv]);
	    	System.out.println("End blazeMeterValues");
	    }
	    
	    System.out.println("Done blazeMeterValues");
	    
	    logger.info("Blazemeter intCreated time : " + intCreated);
	    logger.info("Blazemeter intEnded time : " + intEnded);
	    
	    
	    
	    		
	    try
	    {
	      	Class.forName("com.mysql.jdbc.Driver");
	      	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");  	      	      
	        insertStmt = connection.createStatement();        
 	        	        
	        insertStmt.execute("INSERT INTO perfDataTableLatestAddtlDebug ("
	        		+ "TestID,\n"
	        		+ "TestRunID,\n"
	        		+ "startTime,\n"
	        		+ "endTime,\n"
	        		+ "passed,\n"
	        		+ "Domain,\n"
	        		+ "ApplicationName,\n"
	        		+ "BusinessUnit,\n"
	        		+ "labelName,\n"
	        		+ "is_parent,\n"
	        		+ "samples,\n"
	        		+ "avgResponseTime,\n"
	        		+ "avgThroughput,\n"
	        		+ "90line,\n"
	        		+ "95line,\n"
	        		+ "minResponseTime,\n"
	        		+ "maxResponseTime,\n"
	        		+ "avgBytes,\n"
					+ "errorsRate,\n"
					+ "TestScenario) VALUES ('"
	        		+testIDAddtl+"','"
	        		+TestRunnerID+"','"
	        		//+str+"','"
	        		+intCreated+"','"
					//+str+"','"
					+intEnded+"','"
	        		+str+"','"
	        		+aDList.get(0)+"','"
	        		+aDList.get(2)+"','"
	        		+aDList.get(1)+"','"
	        		+blazeMeterValues[1]+"','"
					+str+"','"
	        		+blazeMeterValues[2]+"','"
	        		+blazeMeterValues[3]+"','"
					+blazeMeterValues[4]+"','"
	        		+blazeMeterValues[5]+"','"
					+blazeMeterValues[6]+"','"
	        		+blazeMeterValues[7]+"','"
	        		+blazeMeterValues[8]+"','"
					+blazeMeterValues[9]+"','"
					+blazeMeterValues[10]+"','"
					//+aDList.get(4)+"')");
					+Pre_Ended_n_TestID_Split+"')");
	        	
	        	System.out.println("Hello there");
	        
	    } 
	    catch (Exception e) {
	      e.printStackTrace();
	    }finally {
	      try {
	        insertStmt.close();
	        connection.close();
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	    }
		
	}
	
	public static void insertInToBlazeMeterTableAddtlLatestMaintenanceDebug(ArrayList<String> aDList, String[] blazeMeterValues, String testIDAddtl, String TestRunnerID, int intCreated, int intEnded, String Pre_Ended_n_TestID_Split) {
		
	    Connection connection = null;
	    Statement insertStmt = null;
	    String str = null;
	    
	    System.out.println("Start");
	    System.out.println("Domain : " + aDList.get(0) + " BusinessUnit : " + aDList.get(1) + " ApplicationName : " + aDList.get(2) + " TestScenario : " + aDList.get(3) + " TestID : " + aDList.get(4));
	    System.out.println("End");
	    
	    for(int xcv = 0; xcv < blazeMeterValues.length; xcv++) {
	    	System.out.println("Start blazeMeterValues");
	    	System.out.println("Whole answer : " + blazeMeterValues[xcv]);
	    	System.out.println("End blazeMeterValues");
	    }
	    
	    System.out.println("Done blazeMeterValues");
	    
	    logger.info("Blazemeter intCreated time : " + intCreated);
	    logger.info("Blazemeter intEnded time : " + intEnded);
	    		
	    try
	    {
	      	Class.forName("com.mysql.jdbc.Driver");
	      	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");  	      	      
	        insertStmt = connection.createStatement();        
 	        	        
	        insertStmt.execute("INSERT INTO perfDataTableLatestMatchingRows ("
	        		+ "TestID,\n"
	        		+ "TestRunID,\n"
	        		+ "startTime,\n"
	        		+ "endTime,\n"
	        		+ "passed,\n"
	        		+ "Domain,\n"
	        		+ "ApplicationName,\n"
	        		+ "BusinessUnit,\n"
	        		+ "labelName,\n"
	        		+ "is_parent,\n"
	        		+ "samples,\n"
	        		+ "avgResponseTime,\n"
	        		+ "avgThroughput,\n"
	        		+ "90line,\n"
	        		+ "95line,\n"
	        		+ "minResponseTime,\n"
	        		+ "maxResponseTime,\n"
	        		+ "avgBytes,\n"
					+ "errorsRate,\n"
					+ "TestScenario) VALUES ('"
	        		+testIDAddtl+"','"
	        		+TestRunnerID+"','"
	        		//+str+"','"
	        		+intCreated+"','"
					//+str+"','"
					+intEnded+"','"
	        		+str+"','"
	        		+aDList.get(0)+"','"
	        		+aDList.get(2)+"','"
	        		+aDList.get(1)+"','"
	        		+blazeMeterValues[1]+"','"
					+str+"','"
	        		+blazeMeterValues[2]+"','"
	        		+blazeMeterValues[3]+"','"
					+blazeMeterValues[4]+"','"
	        		+blazeMeterValues[5]+"','"
					+blazeMeterValues[6]+"','"
	        		+blazeMeterValues[7]+"','"
	        		+blazeMeterValues[8]+"','"
					+blazeMeterValues[9]+"','"
					+blazeMeterValues[10]+"','"
					//+aDList.get(4)+"')");
					+Pre_Ended_n_TestID_Split+"')");
	        
			    if ( (intCreated == 1652186930) && (intEnded == 1652192704) ) {
			    	
			    	System.out.println("YES");
			    	
			    } else {
		    	
		    	System.out.println("NO");
		    	
		    }
	        
	        	System.out.println("Hello there");
	        
	    } 
	    catch (Exception e) {
	      e.printStackTrace();
	    }finally {
	      try {
	        insertStmt.close();
	        connection.close();
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	    }
		
	}
	
	public static ArrayList<String> selectBlazeMeterTableAddtlLatestDebug(ArrayList<String> aDList, String[] blazeMeterValues, String testIDAddtl, String TestRunnerID, int intCreated, int intEnded, String Pre_Ended_n_TestID_Split) {
		
	    Connection connection = null;
	    Statement selectStmt = null;
	    String str = null;
	    
	    //System.out.println("Start");
	    //System.out.println("Domain : " + aDList.get(0) + " BusinessUnit : " + aDList.get(1) + " ApplicationName : " + aDList.get(2) + " TestScenario : " + aDList.get(3) + " TestID : " + aDList.get(4));
	    //System.out.println("End");
	    
	    for(int xcv = 0; xcv < blazeMeterValues.length; xcv++) {
	    	//System.out.println("Start blazeMeterValues");
	    	//System.out.println("Whole answer : " + blazeMeterValues[xcv]);
	    	//System.out.println("End blazeMeterValues");
	    }
	    
	    //System.out.println("Done blazeMeterValues");
	    
	    logger.info("Blazemeter intCreated time : " + intCreated);
	    logger.info("Blazemeter intEnded time : " + intEnded);
	    
	    String nullValue = "'null'";
	    //System.out.println("Value of nullValue is as follows : " + nullValue);
	    		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");
			selectStmt = connection.createStatement();
			
			String curQueryLat1 = "select TestID, TestRunID, startTime, endTime, passed, Domain, ApplicationName, BusinessUnit, ";
			String curQueryLat2 = "labelName, is_parent, samples, avgResponseTime, avgThroughput, 90line, 95line, minResponseTime, maxResponseTime, avgBytes, ";
			
			String curQueryLat3 = "errorsRate, TestScenario from perfDataTableLatestAddtlDebug where TestID = ";
			String curQueryLat4 = "\"" +  testIDAddtl + "\"";
			
			String curQueryLat5 = " and TestRunID = ";
			String curQueryLat6 = "\"" +  TestRunnerID + "\"";
			
			String curQueryLat7 = " and startTime = ";
			String curQueryLat8 = "\"" +  intCreated + "\"";
			
			String curQueryLat9 = " and endTime = ";
			String curQueryLat10 = "\"" +  intEnded + "\"";
			
			String curQueryLat11 = " and passed = ";
			String curQueryLat12 = "" +  nullValue + "";
			
			String curQueryLat13 = " and Domain = ";
			String curQueryLat14 = "\"" +  aDList.get(0) + "\"";
			
			String curQueryLat15 = " and ApplicationName = ";
			String curQueryLat16 = "\"" +  aDList.get(2) + "\"";
			
			String curQueryLat17 = " and BusinessUnit = ";
			String curQueryLat18 = "\"" +  aDList.get(1) + "\"";
			
			String curQueryLat19 = " and labelName = ";
			String curQueryLat20 = "\"" +  blazeMeterValues[1] + "\"";
			
			String curQueryLat21 = " and is_parent = ";
			String curQueryLat22 = "" +  nullValue + "";
			
			String curQueryLat23 = " and samples = ";
			String curQueryLat24 = "\"" +  blazeMeterValues[2] + "\"";
			
			String curQueryLat25 = " and avgResponseTime = ";
			String curQueryLat26 = "\"" +  blazeMeterValues[3] + "\"";
			
			String curQueryLat27 = " and avgThroughput = ";
			String curQueryLat28 = "\"" +  blazeMeterValues[4] + "\"";
			
			String curQueryLat29 = " and 90line = ";
			String curQueryLat30 = "\"" +  blazeMeterValues[5] + "\"";
			
			String curQueryLat31 = " and 95line = ";
			String curQueryLat32 = "\"" +  blazeMeterValues[6] + "\"";
			
			String curQueryLat33 = " and minResponseTime = ";
			String curQueryLat34 = "\"" +  blazeMeterValues[7] + "\"";
			
			String curQueryLat35 = " and maxResponseTime = ";
			String curQueryLat36 = "\"" +  blazeMeterValues[8] + "\"";
			
			String curQueryLat37 = " and avgBytes = ";
			String curQueryLat38 = "\"" +  blazeMeterValues[9] + "\"";
			
			String curQueryLat39 = " and errorsRate = ";
			String curQueryLat40 = "\"" +  blazeMeterValues[10] + "\"";
			
			String curQueryLat41 = " and TestScenario = ";
			String curQueryLat42 = "\"" +  Pre_Ended_n_TestID_Split + "\"";
			
			
			String curQueryLatFinal1 = curQueryLat1 + curQueryLat2 + curQueryLat3 + curQueryLat4 + curQueryLat5 + curQueryLat6 + curQueryLat7 + curQueryLat8;
			String curQueryLatFinal2 = curQueryLat9 + curQueryLat10 + curQueryLat11 + curQueryLat12 + curQueryLat13 + curQueryLat14 + curQueryLat15 + curQueryLat16;
			String curQueryLatFinal3 = curQueryLat17 + curQueryLat18 + curQueryLat19 + curQueryLat20 + curQueryLat21 + curQueryLat22 + curQueryLat23 + curQueryLat24;
			String curQueryLatFinal4 = curQueryLat25 + curQueryLat26 + curQueryLat27 + curQueryLat28 + curQueryLat29 + curQueryLat30 + curQueryLat31 + curQueryLat32;
			String curQueryLatFinal5 = curQueryLat33 + curQueryLat34 + curQueryLat35 + curQueryLat36 + curQueryLat37 + curQueryLat38 + curQueryLat39 + curQueryLat40;
			String curQueryLatFinal6 = curQueryLat41 + curQueryLat42;
			
			String curQueryLatFinal = curQueryLatFinal1 + curQueryLatFinal2 + curQueryLatFinal3 + curQueryLatFinal4 + curQueryLatFinal5 + curQueryLatFinal6;
			
			//System.out.println("curQueryLatFinal : " + curQueryLatFinal);
			
			ResultSet results = selectStmt.executeQuery(curQueryLatFinal);
			ResultSetMetaData rsmd = results.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			
			arList.clear();
			//System.out.println("arList is : " + arList);
			//System.out.println("First");
			int checkCount = 0;
			
			ArrayList<String> rows = new ArrayList<String>();
		    int columnCount = results.getMetaData().getColumnCount();
		    while((results.next()) && (checkCount == 0)) {
		    	   for (int i = 1; i <= columnCount; i++) {
		    		   arList.add(results.getString(i));
		           }
		    	   
		    	   checkCount++;
		    	   
		    }
			
			//System.out.println("arList is : " + arList);
			//System.out.println("Second");

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				selectStmt.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//return mapLocalDBBuilderSelectDebug;
	    return arList;
	    
		
	}
	
	
	public static ArrayList<String> constructComparisionArrLt(ArrayList<String> aDList, String[] blazeMeterValues, String testIDAddtl, String TestRunnerID, int intCreated, int intEnded, String Pre_Ended_n_TestID_Split) {
		
    
	    String nullValue = "null";
	    //System.out.println("Value of nullValue is as follows : " + nullValue);
	    
	    apiARList.clear();
	    		
		try {

			apiARList.add(testIDAddtl);
			apiARList.add(TestRunnerID);
			apiARList.add(String.valueOf(intCreated));
			apiARList.add(String.valueOf(intEnded));
			apiARList.add("" +  nullValue + "");
			apiARList.add(aDList.get(0));
			apiARList.add(aDList.get(2));
			apiARList.add(aDList.get(1));
			apiARList.add(blazeMeterValues[1]);
			apiARList.add("" +  nullValue + "");
			apiARList.add(blazeMeterValues[2]);
			apiARList.add(blazeMeterValues[3]);
			apiARList.add(blazeMeterValues[4]);
			apiARList.add(blazeMeterValues[5]);
			apiARList.add(blazeMeterValues[6]);
			apiARList.add(blazeMeterValues[7]);
			apiARList.add(blazeMeterValues[8]);
			apiARList.add(blazeMeterValues[9]);
			apiARList.add(blazeMeterValues[10]);
			//apiARList.add(aDList.get(4));
			apiARList.add(Pre_Ended_n_TestID_Split);
			
			System.out.println("output of apiARList : " + apiARList);
			System.out.println("Done");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//return mapLocalDBBuilderSelectDebug;
	    return apiARList;
	    
		
	}
	
	
	
	public static void insertInToBlazeMeterMatchingTable(String testID, String TestRunnerID, String TestScenario) {
		
	    Connection connection = null;
	    Statement insertStmt = null;
	    String str = null;
    
	    		
	    try
	    {
	      	Class.forName("com.mysql.jdbc.Driver");
	      	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");  	      	      
	        insertStmt = connection.createStatement();        
 	        	        
	        insertStmt.execute("INSERT INTO perfDataTableLatestMatchingRows ("
	        		+ "TestID,\n"
	        		+ "TestRunID,\n"
					+ "TestScenario) VALUES ('"
	        		+testID+"','"
	        		+TestRunnerID+"','"
					+TestScenario+"')");
	        	
	        	System.out.println("Hello there");
	        
	    } 
	    catch (Exception e) {
	      e.printStackTrace();
	    }finally {
	      try {
	        insertStmt.close();
	        connection.close();
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	    }
		
	}

	public static void insertInToBlazeMeterTableAddtlLatestDebugX(ArrayList<String> aDList, String[] blazeMeterValues, String testIDAddtl, String TestRunnerID, int intCreated, int intEnded) {
		
	    Connection connection = null;
	    Statement insertStmt = null;
	    String str = null;
	    
	    System.out.println("Start");
	    System.out.println("Domain : " + aDList.get(0) + " BusinessUnit : " + aDList.get(1) + " ApplicationName : " + aDList.get(2) + " TestScenario : " + aDList.get(3) + " TestID : " + aDList.get(4));
	    System.out.println("End");
	    
	    for(int xcv = 0; xcv < blazeMeterValues.length; xcv++) {
	    	System.out.println("Start blazeMeterValues");
	    	System.out.println("Whole answer : " + blazeMeterValues[xcv]);
	    	System.out.println("End blazeMeterValues");
	    }
	    
	    System.out.println("Done blazeMeterValues");
	    
	    logger.info("Blazemeter intCreated time : " + intCreated);
	    logger.info("Blazemeter intEnded time : " + intEnded);
	    		
	    try
	    {
	      	Class.forName("com.mysql.jdbc.Driver");
	      	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");  	      	      
	        insertStmt = connection.createStatement();        
 	        	        
	        insertStmt.execute("INSERT INTO perfDataTableLatestAddtlDebugX ("
	        		+ "TestID,\n"
	        		+ "TestRunID,\n"
	        		+ "startTime,\n"
	        		+ "endTime,\n"
	        		+ "passed,\n"
	        		+ "Domain,\n"
	        		+ "ApplicationName,\n"
	        		+ "BusinessUnit,\n"
	        		+ "labelName,\n"
	        		+ "is_parent,\n"
	        		+ "samples,\n"
	        		+ "avgResponseTime,\n"
	        		+ "avgThroughput,\n"
	        		+ "90line,\n"
	        		+ "95line,\n"
	        		+ "minResponseTime,\n"
	        		+ "maxResponseTime,\n"
	        		+ "avgBytes,\n"
					+ "errorsRate,\n"
					+ "TestScenario) VALUES ('"
	        		+testIDAddtl+"','"
	        		+TestRunnerID+"','"
	        		//+str+"','"
	        		+intCreated+"','"
					//+str+"','"
					+intEnded+"','"
	        		+str+"','"
	        		+aDList.get(0)+"','"
	        		+aDList.get(2)+"','"
	        		+aDList.get(1)+"','"
	        		+blazeMeterValues[1]+"','"
					+str+"','"
	        		+blazeMeterValues[2]+"','"
	        		+blazeMeterValues[3]+"','"
					+blazeMeterValues[4]+"','"
	        		+blazeMeterValues[5]+"','"
					+blazeMeterValues[6]+"','"
	        		+blazeMeterValues[7]+"','"
	        		+blazeMeterValues[8]+"','"
					+blazeMeterValues[9]+"','"
					+blazeMeterValues[10]+"','"
					+aDList.get(4)+"')");
	        	
	        	System.out.println("Hello there");
	        
	    } 
	    catch (Exception e) {
	      e.printStackTrace();
	    }finally {
	      try {
	        insertStmt.close();
	        connection.close();
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	    }
		
	}

	public static void insertInToBlazeMeterTableAddtlLatestDebugY(ArrayList<String> aDList, String[] blazeMeterValues, String testIDAddtl, String TestRunnerID, int intCreated, int intEnded) {
		
	    Connection connection = null;
	    Statement insertStmt = null;
	    String str = null;
	    
	    System.out.println("Start");
	    System.out.println("Domain : " + aDList.get(0) + " BusinessUnit : " + aDList.get(1) + " ApplicationName : " + aDList.get(2) + " TestScenario : " + aDList.get(3) + " TestID : " + aDList.get(4));
	    System.out.println("End");
	    
	    for(int xcv = 0; xcv < blazeMeterValues.length; xcv++) {
	    	System.out.println("Start blazeMeterValues");
	    	System.out.println("Whole answer : " + blazeMeterValues[xcv]);
	    	System.out.println("End blazeMeterValues");
	    }
	    
	    System.out.println("Done blazeMeterValues");
	    
	    logger.info("Blazemeter intCreated time : " + intCreated);
	    logger.info("Blazemeter intEnded time : " + intEnded);
	    		
	    try
	    {
	      	Class.forName("com.mysql.jdbc.Driver");
	      	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testData", "root", "newType#123");  	      	      
	        insertStmt = connection.createStatement();        
 	        	        
	        insertStmt.execute("INSERT INTO perfDataTableLatestAddtlDebugY ("
	        		+ "TestID,\n"
	        		+ "TestRunID,\n"
	        		+ "startTime,\n"
	        		+ "endTime,\n"
	        		+ "passed,\n"
	        		+ "Domain,\n"
	        		+ "ApplicationName,\n"
	        		+ "BusinessUnit,\n"
	        		+ "labelName,\n"
	        		+ "is_parent,\n"
	        		+ "samples,\n"
	        		+ "avgResponseTime,\n"
	        		+ "avgThroughput,\n"
	        		+ "90line,\n"
	        		+ "95line,\n"
	        		+ "minResponseTime,\n"
	        		+ "maxResponseTime,\n"
	        		+ "avgBytes,\n"
					+ "errorsRate,\n"
					+ "TestScenario) VALUES ('"
	        		+testIDAddtl+"','"
	        		+TestRunnerID+"','"
	        		//+str+"','"
	        		+intCreated+"','"
					//+str+"','"
					+intEnded+"','"
	        		+str+"','"
	        		+aDList.get(0)+"','"
	        		+aDList.get(2)+"','"
	        		+aDList.get(1)+"','"
	        		+blazeMeterValues[1]+"','"
					+str+"','"
	        		+blazeMeterValues[2]+"','"
	        		+blazeMeterValues[3]+"','"
					+blazeMeterValues[4]+"','"
	        		+blazeMeterValues[5]+"','"
					+blazeMeterValues[6]+"','"
	        		+blazeMeterValues[7]+"','"
	        		+blazeMeterValues[8]+"','"
					+blazeMeterValues[9]+"','"
					+blazeMeterValues[10]+"','"
					+aDList.get(4)+"')");
	        	
	        	System.out.println("Hello there");
	        
	    } 
	    catch (Exception e) {
	      e.printStackTrace();
	    }finally {
	      try {
	        insertStmt.close();
	        connection.close();
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	    }
		
	}

}
