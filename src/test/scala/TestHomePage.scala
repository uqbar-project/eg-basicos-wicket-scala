package uqbar.ejemplosBasicos.ui.wicket

import org.apache.wicket.util.tester.WicketTester

import junit.framework.TestCase

/**
 * Simple test using the WicketTester
 */
class TestHomePage extends TestCase {
	val tester = new WicketTester(new WicketApplication)

	def testRenderMyPage = {
		//start and render the test page
		tester.startPage(classOf[HomePage])

		//assert rendered page class
		tester.assertRenderedPage(classOf[HomePage])

		//assert rendered label component
		tester.assertLabel("message", "If you see this message wicket is properly configured and running")
	}
}
