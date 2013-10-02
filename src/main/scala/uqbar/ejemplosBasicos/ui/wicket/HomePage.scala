package uqbar.ejemplosBasicos.ui.wicket

import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.link.Link
import org.apache.wicket.markup.html.list.ListItem
import org.apache.wicket.markup.html.list.PropertyListView
import org.apache.wicket.request.mapper.parameter.PageParameters
import scala.collection.JavaConversions._
import org.apache.wicket.Page
import scala.beans.BeanProperty

class MenuAction(@BeanProperty val linkName: String, val responsePage: Class[_ <: Page]) extends Serializable

class HomePage extends WebPage {
  /**
   * Constructor that is invoked when page is invoked without a session.
   */
  def this(parameters: PageParameters) = {
    this()

    // Workaround para salvar un crash del compilador de Scala.
    val opciones = this.opciones
    
    add(new PropertyListView[MenuAction]("links", opciones) {
      override def populateItem(item: ListItem[MenuAction]) = {
        val link = new Link[Object]("link") {
          override def onClick = setResponsePage(item.getModelObject.responsePage)
        }
        link add (new Label("linkName"))
        item add link
      }
    })
  }

  def horaActual = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date)

  def opciones = List(
    new MenuAction("Calculadora Común", classOf[CalculadoraPage]),
    new MenuAction("Calculadora (usando ajax)", classOf[CalculadoraAjaxPage]),
    new MenuAction("Búsqueda de libros", classOf[BusquedaLibrosPage]),
    new MenuAction("Conversor", classOf[ConversorPage]),
    new MenuAction("Conversor con validación manual", classOf[ConversorPageValidandoPI]))

}
