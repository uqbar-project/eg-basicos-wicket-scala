package uqbar.ejemplosBasicos.ui.wicket

import org.apache.wicket.Component
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.markup.html.link.Link
import org.apache.wicket.markup.html.panel.FeedbackPanel
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.request.mapper.parameter.PageParameters

import uqbar.ejemplosBasicos.domain.Calculadora

class CalculadoraAjaxPage extends WebPage {
		val calculadora = new Calculadora
		
		// Components
	val labelResultado = new Label("resultado").setOutputMarkupId(true)
		    val feedbackPanel = new FeedbackPanel("feedbackPanel").setOutputMarkupId(true)

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters Page parameters
	 */
	def this(parameters: PageParameters) = {
		  this()
		val form = new Form("calculadoraAjaxForm", createModel)
		add(form)
		addFields(form)
		addActions(form)
	}

	def addActions(form: Form[Calculadora]) {
		form.add(new AjaxSubmitLink("sumar") {
			override def onSubmit(target: AjaxRequestTarget, form: Form[_]) = 
			  target add labelResultado 

			override def onError(target: AjaxRequestTarget, form: Form[_]) = 
			  target add feedbackPanel
		},

		new Link[Object]("linkVolver") {
			override def onClick = setResponsePage(classOf[HomePage])
		})
	}

	def addFields(form: Form[Calculadora]) = {
		form.add(
		    new TextField("operando1"),
		    new TextField("operando2"),
		    labelResultado,
		    feedbackPanel)
	}

	def createModel = new CompoundPropertyModel[Calculadora](calculadora)
}
