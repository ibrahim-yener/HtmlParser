package main_lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;

import utils.CannotBeNullException;
import utils.HttpsNotSupportedExeption;

public class HtmlParser
{
	/**
	 * String will be parsed
	 */
	private String	stringToParse	= "";

	/**
	 * Constructor for regular String
	 * <p>
	 * String must be HTML source code
	 * 
	 * @param str
	 * @throws CannotBeNullException
	 * 
	 */
	public HtmlParser(String str) throws CannotBeNullException
	{
		if (str.length() < 2)
		{
			throw new CannotBeNullException("Parsing string cannot be null.");
		}
		else
		{
			this.stringToParse = str;
		}

		System.out.println(this.stringToParse);
	}

	/**
	 * Constructor for Web page. Pass full URL to this constructor. Once URL
	 * passes, constructor connect {@link} and try to get source code.
	 * <p>
	 * This constructor will NOT work password protected and Login required
	 * pages.
	 * 
	 * @param url
	 *            MUST be HTTP protocol. For Secure connection, please use
	 *            HttpParse(URL url, boolean https)
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @throws CannotBeNullException
	 * @throws HttpsNotSupportedExeption
	 * 
	 */
	public HtmlParser(URL url) throws UnsupportedEncodingException, IOException, CannotBeNullException, HttpsNotSupportedExeption
	{
		String convert = url.toString();

		/**
		 * Check to see url empty or not
		 */
		if (convert.length() < 2)
		{
			throw new CannotBeNullException("Parsing string cannot be null.");
		}

		/**
		 * Check to see if user trying HTTPS protocol in here
		 */
		if (url.getProtocol().equals("https"))
		{
			throw new HttpsNotSupportedExeption("Currently, this protocol is not supported.");
		}

		this.stringToParse = getUrlSource(url).toString();
	}

	/**
	 * Constructor for Web page. Pass full URL to this constructor. Once URL
	 * passes, constructor connect {@link} and try to get source code.
	 * <p>
	 * This constructor will NOT work password protected and Login required
	 * pages.
	 * 
	 * @param url
	 *            MUST be HTTPS protocol. For normal connection, please use
	 *            HttpParse(URL url)
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @throws CannotBeNullException
	 * @throws HttpFormatException
	 * 
	 * 
	 *             public HtmlParser(URL url, boolean https) throws
	 *             CannotBeNullException, UnsupportedEncodingException,
	 *             IOException, HttpFormatException { String convert =
	 *             url.toString();
	 * 
	 *             /** Check to see url empty or not / if (convert.length() < 2)
	 *             { throw new
	 *             CannotBeNullException("Parsing string cannot be null."); }
	 * 
	 *             /** Check to see if user trying HTTP protocol in here / if
	 *             (url.getProtocol().equals("http")) { throw new
	 *             HttpFormatException("Protocol exception."); }
	 * 
	 *             //this.stringToParse = getUrlSource(url).toString();
	 * 
	 *             System.out.println(this.stringToParse); }
	 */

	/**
	 * Parameters must be ArrayList
	 * 
	 * @param tags
	 * @return ArrayList<String> extracted code
	 * @return Exception
	 * 
	 */
	public ArrayList<String> htmlTagToExtract(ArrayList<String> tags)
	{
		ArrayList<String> result = new ArrayList<String>();
		
		
		
		return result;
	}
	
	/**
	 * Quick access and extract only JavaScript code block(s) if find any.
	 * <p> 
	 * @param index -1 returns all Script. If you want specific script then type index number. 0 is first JavaScript code block
	 * 
	 * @return Specified or all JavaScript codes as StringBuilder if found. Returns null otherwise.
	 */
	public StringBuilder getOnlyJavaScript(int index)
	{
		StringBuilder scriptOnly = new StringBuilder();
		
		
		
		return scriptOnly;
	}
	
	/**
	 * Quick access and extract all HREF tags in given String or URL.
	 * <p>
	 * @param index -1 returns all HREF tags. If you want specified amount of tag send it as parameter.<br>
	 * For example: getOnlyUrl(10) return only 10 HREF tags from beginning of BODY.
	 * 
	 * @return Specified amount or all HREF tag as ArrayList or return null if can't find any.
	 */
	public ArrayList<String> getOnlyUrl(int index)
	{
		ArrayList<String> urls = new ArrayList<String>();
		
		
		return urls;
	}
	
	/**
	 * Quick access and extract all Images in given String or URL.
	 * <p>
	 * @param index -1 returns all IMAGE tags. If you want specified amount of tag send it as parameter.<br>
	 * For example: getOnlyImage(10) return only 10 IMAGE tags from beginning of BODY.
	 * 
	 * @return Specified amount or all IMAGE tag as ArrayList or return null if can't find any.
	 */
	public ArrayList<String> getOnlyImage(int index)
	{
		ArrayList<String> img = new ArrayList<String>();
		
		
		return img;
	}

	private StringBuilder getUrlSource(URL url) throws UnsupportedEncodingException, IOException
	{
		StringBuilder output = new StringBuilder();

		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

		String line = "";
		while ((line = reader.readLine()) != null)
		{
			output.append(line.trim());
		}

		reader.close();

		return output;
	}
}
