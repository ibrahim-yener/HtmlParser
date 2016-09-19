package utils;

public class CleanComments
{
	/*
	 * String will be parsed
	 */
	private String stringToParse = "";
	
	public CleanComments(String htmlRawText)
	{
		this.stringToParse = htmlRawText;
	}
	
	public void cleanHtmlComments()
	{
		this.stringToParse = stringToParse.replaceAll("(?s)<!--.*?-->", "");
	}
	
	public void cleanJavaScriptComments()
	{
		
	}
	
	public void cleanCssComments()
	{
		
	}
	
	public String stringToParse()
	{
		return stringToParse;
	}
}
