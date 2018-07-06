package first_swt;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.Viewer;

import bean.ListModel;

public class ListContentProvider implements IStructuredContentProvider, PropertyChangeListener {
	private ListViewer mViewer;
	private ListModel model;

	@Override
	public Object[] getElements(Object inputElement) { // 当viewer的iniput()方法调用时执行
		// TODO Auto-generated method stub
		 return model.elements();
//		return ((List) inputElement).toArray();
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {// 当viewer的iniput()方法调用时执行
		this.mViewer = (ListViewer) viewer;
		// 更新监听
		if (oldInput instanceof ListModel)
			((ListModel) oldInput).removePropertyChangeListener(this);
		if (newInput instanceof ListModel) {
			this.model = (ListModel) newInput;
			((ListModel) newInput).addPropertyChangeListener(this);
		}

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		if (ListModel.ADD_ELEMENT.equals(evt.getPropertyName())) {
			mViewer.add(evt.getNewValue());

		}
		if (ListModel.REMOVE_ELEMENT.equals(evt.getPropertyName())) {
			mViewer.remove(evt.getNewValue());
		}
	}

}
