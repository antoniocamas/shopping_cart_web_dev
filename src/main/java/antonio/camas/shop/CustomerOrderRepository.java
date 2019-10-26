package antonio.camas.shop;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

	CustomerOrder findByItemsId(long id);
	

}
