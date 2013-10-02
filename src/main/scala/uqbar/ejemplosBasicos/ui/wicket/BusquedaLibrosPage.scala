package uqbar.ejemplosBasicos.ui.wicket

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.form.AjaxButton
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.markup.html.link.Link
import org.apache.wicket.markup.html.list.ListItem
import org.apache.wicket.markup.html.list.PropertyListView
import org.apache.wicket.model.CompoundPropertyModel

import uqbar.ejemplosBasicos.applicationModel.BuscadorLibros
import uqbar.ejemplosBasicos.domain.Libro

class BusquedaLibrosPage extends WebPage {
  val buscador = new BuscadorLibros()
  val buscarForm = new Form[BuscadorLibros](
    "buscarLibrosForm",
    new CompoundPropertyModel[BuscadorLibros](buscador))
  addSearchFields(buscarForm)
  addResults(buscarForm)
  addActions(buscarForm)
  add(buscarForm)

  /**
   * Crea y agrega los campos con los que se hace la busqueda. Estos controles
   * se bindean automaticamente con las properties del modelo del form, que es
   * un objeto de tipo {@link ListadoSocios}.
   */
  def addSearchFields(buscarForm: Form[BuscadorLibros]) =
    buscarForm.add(new TextField[String]("tituloBusqueda"))

  def addResults(buscarForm: Form[BuscadorLibros]) =
    buscarForm.add(new PropertyListView[Libro]("resultado") {
      override def populateItem(item: ListItem[Libro]) =
        item.add(
          new Label("titulo"),
          new Label("autor"))
    })

  /**
   * Agrega los botones que representan acciones
   */
  def addActions(form: Form[BuscadorLibros]) = {
    form.add(
      new AjaxButton("buscar") {
        override def onSubmit(target: AjaxRequestTarget, form: Form[_]) = {
          buscador.buscar
          target add buscarForm
        }

        override def onError(target: AjaxRequestTarget, form: Form[_]) = {}
      },

      new Link[Object]("linkVolver") {
        override def onClick = setResponsePage(classOf[HomePage])
      })
  }
}
