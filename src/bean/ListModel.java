package bean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class ListModel {
	public static final String ADD_ELEMENT = "ADD_ELEMENT";
	public static final String REMOVE_ELEMENT = "REMOVE_ELEMENT";
	private List<User> mList;
	private PropertyChangeListener listener;

	public ListModel() {
		mList = new ArrayList<User>();
	}

	public void add(User u) {

		if (mList.add(u)) {
			firePropertyChange(new PropertyChangeEvent(this, "ADD_ELEMENT", null, u));

		}

	}

	public void remove(User u) {
		if (mList.remove(u)) {
			firePropertyChange(new PropertyChangeEvent(this, "REMOVE_ELEMENT", null, u));
		}
	}

	/**
	 * 把改变通知给监听器
	 */

	public void firePropertyChange(PropertyChangeEvent e) {
		if (listener != null)

			listener.propertyChange(e);

	}

	// 添加监听
	public void addPropertyChangeListener(PropertyChangeListener li) {
		this.listener = li;

	}

	// 移除监听
	public void removePropertyChangeListener(PropertyChangeListener li) {
		if (this.listener == li) {
			this.listener = null;

		}
	}

	public Object[] elements() {
		// TODO Auto-generated method stub
		if (mList != null)
			return mList.toArray();
		return null;
	}

}
