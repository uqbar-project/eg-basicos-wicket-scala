package uqbar.ejemplosBasicos.ui.wicket;

import java.util.List
import org.apache.wicket.markup.html.list.ListView
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model

abstract class AbstractListView[T](
  id: String,
  model: IModel[_ <: List[_ <: T]] = null)
  extends ListView[T](id, model) {

  def this(id: String, list: List[T]) = this(id, Model.ofList(list))
  
  override def getListItemModel(listViewModel: IModel[_ <: List[T]], index: Int) =
    new CompoundPropertyModel(listViewModel.getObject.get(index))
}