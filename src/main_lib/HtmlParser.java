package main_lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.CannotBeNullException;
import utils.CleanComments;
import utils.HttpsNotSupportedExeption;
import utils.RemoveComment;

public class HtmlParser
{
	private CleanComments	cleanComments;

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
			cleanComments = new CleanComments(str);
		}
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

		/*
		 * Check to see url empty or not
		 */
		if (convert.length() < 2)
		{
			throw new CannotBeNullException("Parsing string cannot be null.");
		}

		/*
		 * Check to see if user trying HTTPS protocol in here
		 */
		if (url.getProtocol().equals("https"))
		{
			throw new HttpsNotSupportedExeption("Currently, this protocol is not supported.");
		}

		cleanComments = new CleanComments(getUrlSource(url).toString());
	}

	/**
	 * If you want to exclude HTML comments call this method<br>
	 * and set parameter TRUE.
	 * <p>
	 * 
	 * @param remove
	 *            boolean value
	 * @return void
	 */
	public void removeHtmlComment(boolean remove)
	{
		// Remove HTML comments ONLY if user set it true
		if (remove == true)
		{
			RemoveComment.REMOVE_HTML_COMMENTS = remove;
		}
	}

	/**
	 * If you want to exclude CSS comments call this method<br>
	 * and set parameter TRUE.
	 * <p>
	 * 
	 * @param remove
	 *            boolean value
	 * @return void
	 */
	public void removeCssComment(boolean remove)
	{
		// Remove CSS comments ONLY if user set it true
		if (remove == true)
		{
			RemoveComment.REMOVE_CSS_COMMENTS = remove;
		}
	}

	/**
	 * If you want to exclude JavaScript comments call this method<br>
	 * and set parameter TRUE.
	 * <p>
	 * 
	 * @param remove
	 *            boolean value
	 * @return void
	 */
	public void removeJavaScriptComment(boolean remove)
	{
		// Remove JavaScript comments ONLY if user set it true
		if (remove == true)
		{
			RemoveComment.REMOVE_JAVASCRIPT_COMMENTS = remove;
		}
	}

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
	 * Quick access and extract only JavaScript code block(s) if find any.<br>
	 * This method does <b>NOT</b> return external JavaScript files.
	 * <p>
	 * 
	 * @param index
	 *            -1 returns all Script. If you want specific script then type
	 *            index number. 0 is first JavaScript code block
	 * 
	 * @return Specified or all JavaScript codes as StringBuilder if found.
	 *         Returns null otherwise.
	 */
	public StringBuilder getOnlyJavaScript(int index)
	{
		StringBuilder scriptOnly = new StringBuilder();

		return scriptOnly;
	}

	/**
	 * Quick access and extract only StyleSheet (CSS) block(s) if find any.<br>
	 * This method does <b>NOT</b> return external StyleSheet files.
	 * <p>
	 * 
	 * @return Returns all CSS styles as StringBuilder if found. Returns null
	 *         otherwise.
	 */
	public StringBuilder getOnlyStyleSheet()
	{
		StringBuilder styleOnly = new StringBuilder();

		return styleOnly;
	}

	/**
	 * Quick access and extract all HREF tags in given String or URL.
	 * <p>
	 * 
	 * @param index
	 *            -1 returns all HREF tags. If you want specified amount of tag
	 *            send it as parameter.<br>
	 *            For example: getOnlyUrl(10) return only 10 HREF tags from
	 *            beginning of BODY.
	 *            <p>
	 * @param isExternalOnly
	 *            If set this parameter to TRUE then it will extract only url
	 *            starts with HTTP or WWW. If set to FALSE then extract
	 *            everything within HREF.
	 *            <p>
	 * @return Specified amount or all HREF tag as ArrayList or return null if
	 *         can't find any.
	 */
	public ArrayList<String> getOnlyUrl(int index, boolean isExternalOnly)
	{
		// Clean up process
		cleanUp();

		ArrayList<String> urls = new ArrayList<String>();

		String urlReg = "";

		if (!isExternalOnly)
		{
			urlReg = "href=\"([^\"]*)\"";
		}
		else
		{
			urlReg = "\\(?\\b(http://|www[.])[-A-Za-z0-9+&amp;@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&amp;@#/%=~_()|]";
		}

		Pattern pattern = Pattern.compile(urlReg, Pattern.CASE_INSENSITIVE);
		Matcher match = pattern.matcher(cleanComments.stringToParse());

		while (match.find())
		{
			// img.add(match.group());
			System.out.println("LINK = " + match.group());
		}

		return urls;
	}

	/**
	 * Quick access and extract all Images in given String or URL.
	 * <p>
	 * 
	 * @param index
	 *            -1 returns all IMAGE tags. If you want specified amount of tag
	 *            send it as parameter.<br>
	 *            For example: getOnlyImage(10) return only 10 IMAGE tags from
	 *            beginning of BODY.
	 * 
	 * @return Specified amount or all IMAGE tag as ArrayList or return null if
	 *         can't find any.
	 */
	public ArrayList<String> getOnlyImage(int index)
	{
		// Clean up process
		cleanUp();

		ArrayList<String> img = new ArrayList<String>();

		String imgReg = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";

		Pattern pattern = Pattern.compile(imgReg, Pattern.CASE_INSENSITIVE);
		Matcher match = pattern.matcher(cleanComments.stringToParse());

		while (match.find())
		{
			img.add(match.group());
		}

		ArrayList<String> imgList = new ArrayList<String>();

		if (index > 0)
		{
			for (int i = 0; i < img.size(); i++)
			{
				if ((i + 1) <= index)
				{
					imgList.add(img.get(i));
				}
			}

			return imgList;
		}
		else
		{
			return img;
		}
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

	/*
	 * Let's clean unwanted comments from HTML code.
	 */
	private void cleanUp()
	{
		// If set true then clean HTML
		if (RemoveComment.REMOVE_HTML_COMMENTS)
		{
			cleanComments.cleanHtmlComments();
		}

		// If set true then clean CSS
		if (RemoveComment.REMOVE_CSS_COMMENTS)
		{
			cleanComments.cleanCssComments();
		}

		// If set true then clean JavaScript
		if (RemoveComment.REMOVE_JAVASCRIPT_COMMENTS)
		{
			cleanComments.cleanJavaScriptComments();
		}
	}
}
