package uqbar.ejemplosBasicos.ui.wicket

import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form.Button
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.markup.html.link.Link
import org.apache.wicket.markup.html.panel.FeedbackPanel
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.request.mapper.parameter.PageParameters

import uqbar.ejemplosBasicos.domain.Conversor

class ConversorPage extends WebPage {
  val conversor = new Conversor

  /**
   * Constructor that is invoked when page is invoked without a session.
   */
  def this(parameters: PageParameters) = {
    this()
    val form = new Form[Conversor]("conversorForm", createModel)
    add(form)
    addFields(form)
    addActions(form)
  }

  def addActions(form: Form[Conversor]) {
    form.add(
      new Button("convertir") {
        override def onSubmit = conversor.convertir
      },

      new Link[Object]("linkVolver") {
        override def onClick = setResponsePage(classOf[HomePage])
      })
  }

  def addFields(form: Form[Conversor]) {
    form.add(
      createMillasField(form),
      new Label("resultado"),
      new FeedbackPanel("feedbackPanel"))
  }

  def createMillasField(form: Form[Conversor]) = new TextField[Double]("millas")

  def createModel = new CompoundPropertyModel[Conversor](conversor)

}
