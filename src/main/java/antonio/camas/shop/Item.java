package antonio.camas.shop;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	private boolean crossed;

	public Item() {
	}

	public Item(String name) {
		super();
		this.name = name;
		this.crossed = false;
	}
	
	public Item(String name, boolean crossed) {
		super();
		this.name = name;
		this.crossed = crossed;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean getCrossed() {
		return crossed;
	}

	public void setCrossed(boolean crossed) {
		this.crossed = crossed;
	}

	@Override
	public String toString() {
		return this.name + (this.crossed ? "(X)":"");
	}

}
