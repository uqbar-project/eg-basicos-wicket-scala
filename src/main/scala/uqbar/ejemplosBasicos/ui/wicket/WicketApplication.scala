package uqbar.ejemplosBasicos.ui.wicket;

import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see uqbar.ejemplosBasicos.ui.wicket.Start#main(String[])
 */
class WicketApplication extends WebApplication {
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	override def getHomePage = classOf[HomePage]
}
