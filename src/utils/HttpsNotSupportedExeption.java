package utils;

public class HttpsNotSupportedExeption extends Exception
{
	private static final long	serialVersionUID	= 8395096769846681955L;

	public HttpsNotSupportedExeption(String message)
	{
		super(message);
	}
}