package e2eTestingPWC_AID.pojo;

public class elementsOfData {
	
	 // fields to store data
	 public String domain; //domain
     public int samples; //77
     public int maxResponseTime; //": 33087,
     public double avgBytes; //: 5.785,
     public int start_time;  //: 1653290975,
     public int end_time;  //: 1653290985,
     public double avgThroughput;	//: 0.021974885844748857,
     public double errorsRate;	//: 3.896103896103896,
     public int n95line;	//": 5851,
     public String business_unit;		//": "business_unit",
     public String labelName;		//": "OpenAM-Login",
     public String application_name; 	//": "application_name",
     public int minResponseTime;		//": 2444,
     public int test_id;		//": "11007272",
     public int test_run_id;		//": 62907194,
     public double avgResponseTime;		//": 4154.558441558442,
     public int n90line;		//": 3479
     
     // Create Data with different data
     public elementsOfData(
    		 String domain, 
    		 int samples, 
    		 int maxResponseTime, 
    		 double avgBytes, 
    		 int start_time, 
    		 int end_time, 
    		 double avgThroughput, 
    		 double errorsRate,
    		 int n95line, 
    		 String business_unit, 
    		 String labelName, 
    		 String application_name,
    		 int minResponseTime, 
    		 int test_id, 
    		 int test_run_id, 
    		 double avgResponseTime, 
    		 int n90line
    		 	 ) {

      this.domain = domain;
      this.samples = samples;
      this.maxResponseTime = maxResponseTime;
      this.avgBytes = avgBytes;
      this.start_time = start_time;
      this.end_time = end_time;
      this.avgThroughput = avgThroughput;
      this.errorsRate = errorsRate;
      this.n95line = n95line;
      this.business_unit = business_unit;
      this.labelName = labelName;
      this.application_name = application_name;
      this.minResponseTime = minResponseTime;
      this.test_id = test_id;
      this.test_run_id = test_run_id;
      this.avgResponseTime = avgResponseTime;
      this.n90line = n90line;

     }

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public int getSamples() {
		return samples;
	}

	public void setSamples(int samples) {
		this.samples = samples;
	}

	public int getMaxResponseTime() {
		return maxResponseTime;
	}

	public void setMaxResponseTime(int maxResponseTime) {
		this.maxResponseTime = maxResponseTime;
	}

	public double getAvgBytes() {
		return avgBytes;
	}

	public void setAvgBytes(double avgBytes) {
		this.avgBytes = avgBytes;
	}

	public int getStart_time() {
		return start_time;
	}

	public void setStart_time(int start_time) {
		this.start_time = start_time;
	}

	public int getEnd_time() {
		return end_time;
	}

	public void setEnd_time(int end_time) {
		this.end_time = end_time;
	}

	public double getAvgThroughput() {
		return avgThroughput;
	}

	public void setAvgThroughput(double avgThroughput) {
		this.avgThroughput = avgThroughput;
	}

	public double getErrorsRate() {
		return errorsRate;
	}

	public void setErrorsRate(double errorsRate) {
		this.errorsRate = errorsRate;
	}

	public int getN95line() {
		return n95line;
	}

	public void setN95line(int n95line) {
		this.n95line = n95line;
	}

	public String getBusiness_unit() {
		return business_unit;
	}

	public void setBusiness_unit(String business_unit) {
		this.business_unit = business_unit;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getApplication_name() {
		return application_name;
	}

	public void setApplication_name(String application_name) {
		this.application_name = application_name;
	}

	public int getMinResponseTime() {
		return minResponseTime;
	}

	public void setMinResponseTime(int minResponseTime) {
		this.minResponseTime = minResponseTime;
	}

	public int getTest_id() {
		return test_id;
	}

	public void setTest_id(int test_id) {
		this.test_id = test_id;
	}

	public int getTest_run_id() {
		return test_run_id;
	}

	public void setTest_run_id(int test_run_id) {
		this.test_run_id = test_run_id;
	}

	public double getAvgResponseTime() {
		return avgResponseTime;
	}

	public void setAvgResponseTime(double avgResponseTime) {
		this.avgResponseTime = avgResponseTime;
	}

	public int getN90line() {
		return n90line;
	}

	public void setN90line(int n90line) {
		this.n90line = n90line;
	}
     
}
