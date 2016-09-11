/**
 * Class name : HtmlRequestMethods
 * 
 * This class define all possible form methods.
 * 
 * @author Ibrahim YENER
 * @date 2016/09/11
 * 
 */
package html.tags.statics;

public class HtmlRequestMethods
{
	/**
	 * Same as GET but returns only HTTP headers and no document body
	 */
	public static String	HTTP_METHOD_POST	= "post";
	/**
	 * Uploads a representation of the specified URI
	 */
	public static String	HTTP_METHOD_PUT		= "put";
	/**
	 * Deletes the specified resource
	 */
	public static String	HTTP_METHOD_DELETE	= "delete";
	/**
	 * Returns the HTTP methods that the server supports
	 */
	public static String	HTTP_METHOD_OPTIONS	= "options";
	/**
	 * Converts the request connection to a transparent TCP/IP tunnel
	 */
	public static String	HTTP_METHOD_CONNECT	= "connect";
}