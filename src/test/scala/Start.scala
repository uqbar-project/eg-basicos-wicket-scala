package uqbar.ejemplosBasicos.ui.wicket

import org.mortbay.jetty.Connector
import org.mortbay.jetty.Server
import org.mortbay.jetty.bio.SocketConnector
import org.mortbay.jetty.webapp.WebAppContext

object Start {

  def main(args: Array[String]) = {
    val server = new Server
    val connector = new SocketConnector

    // Set some timeout options to make debugging easier.
    connector.setMaxIdleTime(1000 * 60 * 60)
    connector.setSoLingerTime(-1)
    connector.setPort(8080)
    server.setConnectors(Array(connector))

    val bb = new WebAppContext
    bb.setServer(server)
    bb.setContextPath("/")
    bb.setWar("src/main/webapp")

    // START JMX SERVER
    // MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer
    // MBeanContainer mBeanContainer = new MBeanContainer(mBeanServer)
    // server.getContainer.addEventListener(mBeanContainer)
    // mBeanContainer.start

    server.addHandler(bb)

    try {
      println(">>> STARTING EMBEDDED JETTY SERVER, PRESS ANY KEY TO STOP")
      server.start
      System.in.read
      println(">>> STOPPING EMBEDDED JETTY SERVER")
      // while (System.in.available == 0) {
      //   Thread.sleep(5000)
      // }
      server.stop
      server.join
    } catch {
      case e: Exception =>
        e.printStackTrace
        System.exit(100)
    }
  }
}
