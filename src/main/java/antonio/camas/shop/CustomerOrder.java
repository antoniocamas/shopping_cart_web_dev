package antonio.camas.shop;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CustomerOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String title;
	
	//@OneToMany(cascade=CascadeType.ALL)
	@OneToMany
	private List<Item> items;
	
	public CustomerOrder() {

	}

	public CustomerOrder(String title) {
		super();
		this.title = title;
		this.items = new ArrayList<Item>();
	}

	public CustomerOrder(String title, List<Item> items) {
		super();
		this.title = title;
		this.items = items;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public void addItem(Item item) {
		this.items.add(item);
	}

	public void removeItem(Item item) {
		int index = this.items.indexOf(item);
		if(index>-1) {
			this.items.remove(index);
		}
	}
	
	@Override
	public String toString() {
		return "CustomerOrder [title=" + title +"]";
	}

}
