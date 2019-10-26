package antonio.camas.shop;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

	List<Item> findByName(String newItem);

	//List<Item> findByName_CustomerOrderId(String name, Long id);

}
