package utilities;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class CommonMethods {
	
	
	public int brokenurl(String pageurl) throws IOException
	{
		HttpsURLConnection connect = (HttpsURLConnection) new URL(pageurl).openConnection();
		connect.setRequestMethod("GET");
		int Status = connect.getResponseCode();
		return Status;
	}

}
