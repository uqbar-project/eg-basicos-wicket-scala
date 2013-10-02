package uqbar.ejemplosBasicos.ui.wicket

import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.apache.wicket.validation.IErrorMessageSource
import org.apache.wicket.validation.IValidatable
import org.apache.wicket.validation.IValidationError
import org.apache.wicket.validation.IValidator
import org.apache.wicket.validation.ValidationError

import uqbar.ejemplosBasicos.domain.Conversor

/**
 * La idea es mostrar una validacion propia asociando un IValidator a un componente
 * @author gdecuzzi
 */
class ConversorPageValidandoPI(parameters: PageParameters) extends ConversorPage(parameters) {
  override def createMillasField(form: Form[Conversor]) = {
    val millas = super.createMillasField(form)
    millas.add(new IValidator[Double] {
      def validate(validatable: IValidatable[Double]) =
        if (validatable.getValue == 3.14)
          validatable.error(new ValidationError().setMessage("El 3.14 no es valido"))
    })
    millas
  }
}
