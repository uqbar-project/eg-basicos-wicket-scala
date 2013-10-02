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

import uqbar.ejemplosBasicos.domain.Calculadora

class CalculadoraPage extends WebPage {
  val calculadora = new Calculadora

  /**
   * Constructor that is invoked when page is invoked without a session.
   * @param parameters Page parameters
   */
  def this(parameters: PageParameters) {
    this()

    val form = new Form("calculadoraForm", createModel)
    add(form)
    addFields(form)
    addActions(form)
  }

  def addActions(form: Form[Calculadora]) = {
    form.add(
      new Button("sumar") {
        override def onSubmit = {
          // no necesitamos enviar el mensaje sumar
          //CalculadoraPage.this.calculadora.sumar()
        }
      },

      new Link[Object]("linkVolver") {
        override def onClick = setResponsePage(classOf[HomePage])
      })
  }

  def addFields(form: Form[Calculadora]) {
    form.add(
      new TextField("operando1"),
      new TextField("operando2"),
      new Label("resultado"),
      new FeedbackPanel("feedbackPanel"))
  }

  def createModel = new CompoundPropertyModel(this.calculadora)
}
