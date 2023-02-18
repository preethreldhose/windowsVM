package e2eTestingPWC_AID.utils;

import java.io.BufferedReader;
import org.apache.log4j.Logger;
import org.testng.xml.XmlClass;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import e2eTestingPWC_AID.e2eCodePWCAddtl;

public class readCSVviaBufReader {
	
	static handleMySQL st = new handleMySQL();
	static e2eCodePWCAddtl obje2eCodePWCAddtl = new e2eCodePWCAddtl();
	static Logger logger = Logger.getLogger(XmlClass.class);
	
	public static ArrayList<String> selectJmeterARListResult = new ArrayList<String>();
	public static ArrayList<String> apiARJmeterListResult = new ArrayList<String>();
	
	public static ArrayList<String> selectLoadRunnerARListResult = new ArrayList<String>();
	public static ArrayList<String> apiARLoadrunnerListLoadResult = new ArrayList<String>();
	
	public static String[] findTestIDs() {
		String line = "";
		String splitBy = ",";
		String[] getTestIDs = null;

		
		try {
			//parsing a CSV file into BufferedReader class constructor  
			BufferedReader br = new BufferedReader(new FileReader("/Users/preetheldhose/eclipse-workspace/e2eTestingPWC_AID/csvDirectory/TestID.csv"));
			while ((line = br.readLine()) != null) // returns a Boolean value
			{
				getTestIDs = line.split(splitBy); // use comma as separator
				for(String testID : getTestIDs) {
					System.out.println("testID : " + testID);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return getTestIDs;
	}
	
	public static String[] readDomainNTestIDs(String path) {
		String line = "";
		String splitBy = ",";
		String[] DomainNTestIDs = null;
		int count = 0;
		String pathLatest = "/Users/preetheldhose/Desktop/Bismillah/Projects/PWC/csvFiles/JmeterStandAlone.csv";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while ((line = br.readLine()) != null) // returns a Boolean value
			{
				DomainNTestIDs = line.split(splitBy); // use comma as separator
				
				for (int i = 0; i < DomainNTestIDs.length; i++ ) {
					System.out.println("DomainNTestIDs is as follows : " + DomainNTestIDs[i]);
				}
				
				System.out.println("Final");
				if(count == 0) {
					count ++;
				} else {
					parseCSVWithFileNames(DomainNTestIDs, pathLatest);
				}
				
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return DomainNTestIDs;
	}
	
	public static String[] readDomainNTestIDsLoadR(String path) {
		String line = "";
		String splitBy = ",";
		String[] DomainNTestIDs = null;
		int count = 0;
		String pathLatest = "/Users/preetheldhose/Desktop/Bismillah/Projects/PWC/csvFiles/LoadRunnerStandAloneTest.csv";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while ((line = br.readLine()) != null) // returns a Boolean value
			{
				DomainNTestIDs = line.split(splitBy); // use comma as separator
				
				for (int i = 0; i < DomainNTestIDs.length; i++ ) {
					System.out.println("DomainNTestIDs is as follows : " + DomainNTestIDs[i]);
				}
				
				System.out.println("Final");
				if(count == 0) {
					count ++;
				} else {
					parseCSVWithFileNamesLoadR(DomainNTestIDs, pathLatest);
				}
				
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return DomainNTestIDs;
	}
	
	
	public static String[] readJmeterCSVFiles(String path) {
		String line = "";
		String splitBy = ",";
		String[] DomainNTestIDs = null;
		int count = 0;
		String pathLatest = "/Users/preetheldhose/Desktop/Bismillah/Projects/PWC/csvFiles/JmeterStandAlone.csv";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while ((line = br.readLine()) != null) // returns a Boolean value
			{
				DomainNTestIDs = line.split(splitBy); // use comma as separator
				
				for (int i = 0; i < DomainNTestIDs.length; i++ ) {
					System.out.println("DomainNTestIDs is as follows : " + DomainNTestIDs[i]);
				}
				
				System.out.println("Final");
				if(count == 0) {
					count ++;
				} else {
					parseCSVWithFileNames(DomainNTestIDs, pathLatest);
				}
				
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return DomainNTestIDs;
	}
	
	//LoadRunnerStandAloneTest.csv
	
	public static String[] parseCSVWithFileNames(String[] DomainNTestIDs, String path) {
		String line = "";
		String splitBy = ",";
		String[] getJmeterColumns = null;
				
		int count = 0;
				
		try {
			//parsing a CSV file into BufferedReader class constructor  
			BufferedReader br = new BufferedReader(new FileReader(path));
			while ((line = br.readLine()) != null) // returns a Boolean value
			{
				getJmeterColumns = line.split(splitBy); // use comma as separator
				for (int i = 0; i < getJmeterColumns.length; i++ ) {
					System.out.println("jmeterColumn is as follows : " + getJmeterColumns[i]);
				}
				
				System.out.println("Final");
				if(count == 0) {
					count ++;
				} else {
					st.insertInTojmeterTable(DomainNTestIDs, getJmeterColumns);
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return getJmeterColumns;
	}
	
	public static String[] parseCSVWithFileNamesLoadR(String[] DomainNTestIDs, String path) {
		String line = "";
		String splitBy = ",";
		String[] getLoadRunnerColumns = null;
				
		int count = 0;
				
		try {
			//parsing a CSV file into BufferedReader class constructor  
			BufferedReader br = new BufferedReader(new FileReader(path));
			while ((line = br.readLine()) != null) // returns a Boolean value
			{
				getLoadRunnerColumns = line.split(splitBy); // use comma as separator
				for (int i = 0; i < getLoadRunnerColumns.length; i++ ) {
					System.out.println("getLoadRunnerColumns is as follows : " + getLoadRunnerColumns[i]);
				}
				
				System.out.println("Final");
				if(count == 0) {
					count ++;
				} else {
					st.insertInToLoadRunnerTable(DomainNTestIDs, getLoadRunnerColumns);
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return getLoadRunnerColumns;
	}
	
	
	//public static String[] parseJMeterFiles(ArrayList<String> aDList, String jmeterPath, String jmeterstartTime) {
	public static String[] parseJMeterFiles(ArrayList<String> aDList, String jmeterPath, int intnumJmeterstartTime) {
		String line = "";
		String splitBy = ",";
		String[] getJmeterColumns = null;		
		int count = 0;
		
		String[] splitJmeterTableValues = jmeterPath.split("/"); // use comma as separator
		String fileNameTestIDAddtl = splitJmeterTableValues[14];
		System.out.println("Latest value of fileNameTestIDAddtl : " + fileNameTestIDAddtl);
		String[] arryBaseFileTestID = fileNameTestIDAddtl.split("\\.");
		String baseFileTestID = arryBaseFileTestID[0];
		System.out.println("Latest value of baseFileTestID : " + baseFileTestID);
				
		try {
			//parsing a CSV file into BufferedReader class constructor  
			BufferedReader br = new BufferedReader(new FileReader(jmeterPath));
			while ((line = br.readLine()) != null) // returns a Boolean value
			{
				getJmeterColumns = line.split(splitBy); // use comma as separator
				for (int i = 0; i < getJmeterColumns.length; i++ ) {
					System.out.println("jmeterColumn is as follows : " + getJmeterColumns[i]);
				}
				
				System.out.println("Final");
				if(count == 0) {
					count ++;
				} else {

					
					logger.info("\n");
					logger.info("Jmeter Section => Value of jmeterstartTime inside parsing method : " + intnumJmeterstartTime);
					logger.info("\n");
					
					st.insertInTojmeterTableAddtl(aDList, getJmeterColumns, baseFileTestID, intnumJmeterstartTime);
					obje2eCodePWCAddtl.callReplicaDBAPIJMeter(aDList, getJmeterColumns, baseFileTestID, intnumJmeterstartTime);
					
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return getJmeterColumns;
	}
	
	public static String[] parseJMeterFilesEnhanced(ArrayList<String> aDList, String jmeterPath, int intnumJmeterstartTime) {
		String line = "";
		String splitBy = ",";
		String[] getJmeterColumns = null;		
		int count = 0;
		
		String[] splitJmeterTableValues = jmeterPath.split("/"); // use comma as separator
		String fileNameTestIDAddtl = splitJmeterTableValues[14];
		System.out.println("Latest value of fileNameTestIDAddtl : " + fileNameTestIDAddtl);
		String[] arryBaseFileTestID = fileNameTestIDAddtl.split("\\.");
		String baseFileTestID = arryBaseFileTestID[0];
		System.out.println("Latest value of baseFileTestID : " + baseFileTestID);
				
		try {
			//parsing a CSV file into BufferedReader class constructor  
			BufferedReader br = new BufferedReader(new FileReader(jmeterPath));
			while ((line = br.readLine()) != null) // returns a Boolean value
			{
				getJmeterColumns = line.split(splitBy); // use comma as separator
				for (int i = 0; i < getJmeterColumns.length; i++ ) {
					System.out.println("jmeterColumn is as follows : " + getJmeterColumns[i]);
				}
				
				System.out.println("Final");
				if(count == 0) {
					count ++;
				} else {

					
					logger.info("\n");
					logger.info("Jmeter Section => Value of jmeterstartTime inside parsing method : " + intnumJmeterstartTime);
					logger.info("\n");
					
					//st.insertInTojmeterTableAddtl(aDList, getJmeterColumns, baseFileTestID, intnumJmeterstartTime);
					//obje2eCodePWCAddtl.callReplicaDBAPIJMeter(aDList, getJmeterColumns, baseFileTestID, intnumJmeterstartTime);
					
					selectJmeterARListResult = st.selectJmeterTableAddtlEnhanced(aDList, getJmeterColumns, baseFileTestID, intnumJmeterstartTime);
					System.out.println("selectJmeterARListResult : " + selectJmeterARListResult);
      				System.out.println("Done");
      				int countselectJmeterARListResult = selectJmeterARListResult.size();
					
      				apiARJmeterListResult = st.constructJmeterTableAddtlEnhanced(aDList, getJmeterColumns, baseFileTestID, intnumJmeterstartTime);
					System.out.println("apiARJmeterListResult : " + apiARJmeterListResult);
      				System.out.println("Done");
      				int countapiARJmeterListResult = apiARJmeterListResult.size();
      				
					//obje2eCodePWCAddtl.callReplicaDBAPIJMeter(aDList, getJmeterColumns, baseFileTestID, intnumJmeterstartTime);
      				
      				if( countapiARJmeterListResult != 0) {
       					if( countselectJmeterARListResult == 0) {
      						System.out.println("inserting into localDB for jmeter and adding rows to replica DB queue : " + aDList + " " + getJmeterColumns + " " + baseFileTestID + " " + intnumJmeterstartTime);
      						logger.info("inserting into localDB for jmeter and adding rows to replica DB queue : "  + aDList + " " + getJmeterColumns + " " + baseFileTestID + " " + intnumJmeterstartTime);
      						//st.insertInTojmeterTableAddtl(aDList, getJmeterColumns, baseFileTestID, intnumJmeterstartTime);
      						st.insertInTojmeterTableAddtlEnhanced(aDList, getJmeterColumns, baseFileTestID, intnumJmeterstartTime);
      						obje2eCodePWCAddtl.callReplicaDBAPIJMeter(aDList, getJmeterColumns, baseFileTestID, intnumJmeterstartTime);
       					} else if( countselectJmeterARListResult != 0 ) {
      						if( 	    apiARJmeterListResult.get(0).equals(selectJmeterARListResult.get(0))       
      								&&	apiARJmeterListResult.get(1).equals(selectJmeterARListResult.get(1))
      								&&	apiARJmeterListResult.get(2).equals(selectJmeterARListResult.get(2))
      								&&	apiARJmeterListResult.get(3).equals(selectJmeterARListResult.get(3))
      								&&	apiARJmeterListResult.get(4).equals(selectJmeterARListResult.get(4))
      								&&	apiARJmeterListResult.get(5).equals(selectJmeterARListResult.get(5))
      								&&	apiARJmeterListResult.get(6).equals(selectJmeterARListResult.get(6))
      								&&	apiARJmeterListResult.get(7).equals(selectJmeterARListResult.get(7))
      								&&	apiARJmeterListResult.get(8).equals(selectJmeterARListResult.get(8))
      								&&	apiARJmeterListResult.get(9).equals(selectJmeterARListResult.get(9))
      								&&	apiARJmeterListResult.get(10).equals(selectJmeterARListResult.get(10))
      								&&	apiARJmeterListResult.get(11).equals(selectJmeterARListResult.get(11))
      								&&	apiARJmeterListResult.get(12).equals(selectJmeterARListResult.get(12))
      								&&	apiARJmeterListResult.get(13).equals(selectJmeterARListResult.get(13))
      								&&	apiARJmeterListResult.get(14).equals(selectJmeterARListResult.get(14))
      								&&	apiARJmeterListResult.get(15).equals(selectJmeterARListResult.get(15))
      								&&	apiARJmeterListResult.get(16).equals(selectJmeterARListResult.get(16))
      								&&	apiARJmeterListResult.get(17).equals(selectJmeterARListResult.get(17))
      								&&	apiARJmeterListResult.get(18).equals(selectJmeterARListResult.get(18)) )	 {
          						System.out.println("inserting into matching rows localDB for jmeter : " + aDList + " " + getJmeterColumns + " " + baseFileTestID + " " + intnumJmeterstartTime);
          						logger.info("inserting into localDB for jmeter and adding rows to replica DB queue : "  + aDList + " " + getJmeterColumns + " " + baseFileTestID + " " + intnumJmeterstartTime);
      							st.insertInTojmeterTableAddtlMatchingRowsEnhanced(aDList, getJmeterColumns, baseFileTestID, intnumJmeterstartTime);
      						}
      						
      						
      					} else {
      						
      						System.out.println("inserting into localDB for jmeter and adding rows to replica DB queue : " + aDList + " " + getJmeterColumns + " " + baseFileTestID + " " + intnumJmeterstartTime);
      						logger.info("inserting into localDB for jmeter and adding rows to replica DB queue : "  + aDList + " " + getJmeterColumns + " " + baseFileTestID + " " + intnumJmeterstartTime);
      						//st.insertInTojmeterTableAddtl(aDList, getJmeterColumns, baseFileTestID, intnumJmeterstartTime);
      						st.insertInTojmeterTableAddtlEnhanced(aDList, getJmeterColumns, baseFileTestID, intnumJmeterstartTime);
      						obje2eCodePWCAddtl.callReplicaDBAPIJMeter(aDList, getJmeterColumns, baseFileTestID, intnumJmeterstartTime);
      						
      					}
      					
      				} else {
      					
			        	System.out.println("Skipping as api list is empty");
			        	logger.info("Skipping as api list is empty");
      					
      				}
					
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return getJmeterColumns;
	}
	
	public static String[] parseBlazeMeterFiles(String blazemeterPath) {
		String line = "";
		String splitBy = ",";
		String[] getblazemeterColumns = null;		
		int count = 0;
		
				
		try {
			//parsing a CSV file into BufferedReader class constructor  
			BufferedReader br = new BufferedReader(new FileReader(blazemeterPath));
			while ((line = br.readLine()) != null) // returns a Boolean value
			{
				getblazemeterColumns = line.split(splitBy); // use comma as separator
				for (int i = 0; i < getblazemeterColumns.length; i++ ) {
					System.out.println("blazemeterColumn is as follows : " + getblazemeterColumns[i]);
				}
				
				System.out.println("Final");
				if(count == 0) {
					count ++;
				} else {
					//st.insertInToblazemeterTable(aDList, getblazemeterColumns);
					//st.insertInToblazemeterTableAddtl(aDList, getblazemeterColumns, baseFileTestID, blazemeterstartTime);
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return getblazemeterColumns;
	}
	
	
	//public static String[] parseLoadRunnerFiles(ArrayList<String> aDList, String loadRunnerPath, String loadRunnerstartTime) {
	public static String[] parseLoadRunnerFiles(ArrayList<String> aDList, String loadRunnerPath, int loadRunnerstartTime) {
		String line = "";
		String splitBy = ",";
		String[] getLoadRunnerColumns = null;		
		int count = 0;
		
		String[] splitLoadRunnerTableValues = loadRunnerPath.split("/"); // use comma as separator
		String[] preFileNameTestIDAddtl = splitLoadRunnerTableValues[14].split("\\.");
		String fileNameTestIDAddtl = preFileNameTestIDAddtl[0];
		System.out.println("Latest value of fileNameTestIDAddtl : " + fileNameTestIDAddtl);
				
		try {
			//parsing a CSV file into BufferedReader class constructor  
			BufferedReader br = new BufferedReader(new FileReader(loadRunnerPath));
			while ((line = br.readLine()) != null) // returns a Boolean value
			{
				getLoadRunnerColumns = line.split(splitBy); // use comma as separator
				for (int i = 0; i < getLoadRunnerColumns.length; i++ ) {
					System.out.println("loadRunnerColumn is as follows : " + getLoadRunnerColumns[i]);
				}
				
				System.out.println("Final");
				if(count == 0) {
					count ++;
				} else {
					//st.insertInToloadRunnerTable(aDList, getLoadRunnerColumns);
					st.insertInToloadRunnerTableAddtl(aDList, getLoadRunnerColumns, fileNameTestIDAddtl, loadRunnerstartTime);
					obje2eCodePWCAddtl.callReplicaDBAPILoadRunner(aDList, getLoadRunnerColumns, fileNameTestIDAddtl, loadRunnerstartTime);
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return getLoadRunnerColumns;
	}
	
	public static String[] parseLoadRunnerFilesEnhanced(ArrayList<String> aDList, String loadRunnerPath, int loadRunnerstartTime) {
		String line = "";
		String splitBy = ",";
		String[] getLoadRunnerColumns = null;		
		int count = 0;
		
				//String[] splitLoadRunnerTableValues = loadRunnerPath.split("/"); // use comma as separator // if non - windows
		String[] splitLoadRunnerTableValues = loadRunnerPath.split("\\\\"); 
		String[] preFileNameTestIDAddtl = splitLoadRunnerTableValues[14].split("\\.");
		String fileNameTestIDAddtl = preFileNameTestIDAddtl[0];
		System.out.println("Latest value of fileNameTestIDAddtl : " + fileNameTestIDAddtl);
				
		try {
			//parsing a CSV file into BufferedReader class constructor  
			BufferedReader br = new BufferedReader(new FileReader(loadRunnerPath));
			while ((line = br.readLine()) != null) // returns a Boolean value
			{
				getLoadRunnerColumns = line.split(splitBy); // use comma as separator
				for (int i = 0; i < getLoadRunnerColumns.length; i++ ) {
					System.out.println("loadRunnerColumn is as follows : " + getLoadRunnerColumns[i]);
				}
				
				System.out.println("Final");
				if(count == 0) {
					count ++;
				} else {
					//st.insertInToloadRunnerTable(aDList, getLoadRunnerColumns);
					
					
					//st.insertInToloadRunnerTableAddtl(aDList, getLoadRunnerColumns, fileNameTestIDAddtl, loadRunnerstartTime);
					//obje2eCodePWCAddtl.callReplicaDBAPILoadRunner(aDList, getLoadRunnerColumns, fileNameTestIDAddtl, loadRunnerstartTime);
					
					//public static ArrayList<String> selectLoadRunnerARListResult = new ArrayList<String>();
					//public static ArrayList<String> apiARLoadrunnerListLoadResult = new ArrayList<String>();
					
					//st.insertInToloadRunnerTableAddtlEnhanced(aDList, getLoadRunnerColumns, fileNameTestIDAddtl, loadRunnerstartTime);
					
					selectLoadRunnerARListResult = st.selectInToloadRunnerTableAddtlEnhanced(aDList, getLoadRunnerColumns, fileNameTestIDAddtl, loadRunnerstartTime);
					System.out.println("selectLoadRunnerARListResult : " + selectLoadRunnerARListResult);
					System.out.println("Done");
					int countselectLoadRunnerARListResult = selectLoadRunnerARListResult.size();
					
					apiARLoadrunnerListLoadResult = st.apiARLoadRunnerTableAddtlEnhanced(aDList, getLoadRunnerColumns, fileNameTestIDAddtl, loadRunnerstartTime);
					System.out.println("apiARLoadrunnerListLoadResult : " + apiARLoadrunnerListLoadResult);
					System.out.println("Done");
					int countapiARLoadrunnerListLoadResult = apiARLoadrunnerListLoadResult.size();
					
					if( countapiARLoadrunnerListLoadResult != 0 ) {
						
						if( countselectLoadRunnerARListResult == 0 ) {
							
							System.out.println("inserting into localDB for LoadRunner and adding rows to replica DB queue : " + aDList + " " +aDList + " " + getLoadRunnerColumns + " " + fileNameTestIDAddtl + " " + loadRunnerstartTime);
      						logger.info("inserting into localDB for LoadRunner and adding rows to replica DB queue : " + aDList + " " +aDList + " " + getLoadRunnerColumns + " " + fileNameTestIDAddtl + " " + loadRunnerstartTime);
      						st.insertInToloadRunnerTableAddtlEnhanced(aDList, getLoadRunnerColumns, fileNameTestIDAddtl, loadRunnerstartTime);
      						obje2eCodePWCAddtl.callReplicaDBAPILoadRunner(aDList, getLoadRunnerColumns, fileNameTestIDAddtl, loadRunnerstartTime);
      						
						} else if( countselectLoadRunnerARListResult != 0 ) {
							
							if( 	      apiARLoadrunnerListLoadResult.get(0).equals( selectLoadRunnerARListResult.get(0))       
      								&&	  apiARLoadrunnerListLoadResult.get(1).equals( selectLoadRunnerARListResult.get(1))
      								&&	  apiARLoadrunnerListLoadResult.get(2).equals( selectLoadRunnerARListResult.get(2))
      								&&	  apiARLoadrunnerListLoadResult.get(3).equals( selectLoadRunnerARListResult.get(3))
      								&&	  apiARLoadrunnerListLoadResult.get(4).equals( selectLoadRunnerARListResult.get(4))
      								&&	  apiARLoadrunnerListLoadResult.get(5).equals( selectLoadRunnerARListResult.get(5))
      								&&	  apiARLoadrunnerListLoadResult.get(6).equals( selectLoadRunnerARListResult.get(6))
      								&&	  apiARLoadrunnerListLoadResult.get(7).equals( selectLoadRunnerARListResult.get(7))
      								&&	  apiARLoadrunnerListLoadResult.get(8).equals( selectLoadRunnerARListResult.get(8))
      								&&	  apiARLoadrunnerListLoadResult.get(9).equals( selectLoadRunnerARListResult.get(9))
      								&&	  apiARLoadrunnerListLoadResult.get(10).equals( selectLoadRunnerARListResult.get(10))
      								&&	  apiARLoadrunnerListLoadResult.get(11).equals( selectLoadRunnerARListResult.get(11))
      								&&	  apiARLoadrunnerListLoadResult.get(12).equals( selectLoadRunnerARListResult.get(12))
      								&&	  apiARLoadrunnerListLoadResult.get(13).equals( selectLoadRunnerARListResult.get(13))
      								&&	  apiARLoadrunnerListLoadResult.get(14).equals( selectLoadRunnerARListResult.get(14))
      								&&	  apiARLoadrunnerListLoadResult.get(15).equals( selectLoadRunnerARListResult.get(15))
      								&&	  apiARLoadrunnerListLoadResult.get(16).equals( selectLoadRunnerARListResult.get(16))
      								&&	  apiARLoadrunnerListLoadResult.get(17).equals( selectLoadRunnerARListResult.get(17))
      								&&	  apiARLoadrunnerListLoadResult.get(18).equals( selectLoadRunnerARListResult.get(18)) )	 {
								
								System.out.println("inserting into localDB for LoadRunner and adding rows to replica DB queue : " + aDList + " " +aDList + " " + getLoadRunnerColumns + " " + fileNameTestIDAddtl + " " + loadRunnerstartTime);
	      						logger.info("inserting into localDB for LoadRunner and adding rows to replica DB queue : " + aDList + " " +aDList + " " + getLoadRunnerColumns + " " + fileNameTestIDAddtl + " " + loadRunnerstartTime);
	      						st.insertInToloadRunnerTableAddtlEnhancedMatchedRows(aDList, getLoadRunnerColumns, fileNameTestIDAddtl, loadRunnerstartTime);
	      						
							}
							
						} else {
							
							System.out.println("inserting into localDB for LoadRunner and adding rows to replica DB queue : " + aDList + " " +aDList + " " + getLoadRunnerColumns + " " + fileNameTestIDAddtl + " " + loadRunnerstartTime);
      						logger.info("inserting into localDB for LoadRunner and adding rows to replica DB queue : " + aDList + " " +aDList + " " + getLoadRunnerColumns + " " + fileNameTestIDAddtl + " " + loadRunnerstartTime);
      						st.insertInToloadRunnerTableAddtlEnhanced(aDList, getLoadRunnerColumns, fileNameTestIDAddtl, loadRunnerstartTime);
      						obje2eCodePWCAddtl.callReplicaDBAPILoadRunner(aDList, getLoadRunnerColumns, fileNameTestIDAddtl, loadRunnerstartTime);
							
						}
						
					} else {
						
			        	System.out.println("Skipping as api list is empty");
			        	logger.info("Skipping as api list is empty");
						
					}
					
					
					
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return getLoadRunnerColumns;
	}
	
}
