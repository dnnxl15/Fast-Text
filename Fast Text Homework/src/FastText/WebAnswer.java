package FastText;

import javax.servlet.*; 
import javax.servlet.http.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL; 

public class WebAnswer extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private String Url;
	private String Request;
	
	public WebAnswer()
	{
	}
	
	public String getRequest()
	{
		return this.Request;
	}
	
	public void setUrl(String pUrl)
	{
		this.Url = pUrl;
	}

	public void sendGet(String pUrl) throws Exception 
	{
		setUrl(pUrl);
		String url = this.Url; 
		URL direction = new URL(url); // URL object
		HttpURLConnection connection = (HttpURLConnection) direction.openConnection();
		connection.setRequestMethod("GET"); // Get request method.
		connection.setRequestProperty("User-Agent", "Mozilla/5.0"); // Request header
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer buffer = new StringBuffer();
		while ((inputLine = in.readLine()) != null) 
		{
			buffer.append(inputLine); // Add the code html.
		}
		in.close();
		this.Request = buffer.toString(); // Asign the answer to the atribute.
	}
}




