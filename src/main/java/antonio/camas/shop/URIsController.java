package antonio.camas.shop;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class URIsController {

	@Autowired
	private CustomerOrderRepository orderRepository;

	@Autowired
	private ItemRepository itemRepository;

	@PostConstruct
	public void init() {
		
		Item item1Or1 = new Item("Apple");
		itemRepository.save(item1Or1);
		
		Item item1Or2 = new Item("Pear");
		itemRepository.save(item1Or2);
	
		Item item1Or3 = new Item("Chewing gum", true);
		itemRepository.save(item1Or3);
	
		
		List<Item> itemsOrder1 = Arrays.asList(item1Or1, item1Or2, item1Or3);
		
		orderRepository.save(new CustomerOrder("Order1", itemsOrder1));
		orderRepository.save(new CustomerOrder("Order2"));
	}
	
	@GetMapping("/")
	public String landingPage(Model model) {

		model.addAttribute("orders", orderRepository.findAll());

		return "index";
	}

	@GetMapping("/show_order/{id}")
	public String showOrder(Model model, @PathVariable long id) {

		CustomerOrder order = orderRepository.findById(id).get();

		model.addAttribute("order", order);

		return "order";
	}
	
	@GetMapping("/create_order")
	public String createOrder(@RequestParam String title, @RequestParam List<String> items) {
		
	
		List<Item> itemEntries = new ArrayList<Item>();
		for (String item : items) {
			Item entry = new Item(item);
			itemEntries.add(entry);
			itemRepository.save(entry);
//			System.out.println(item);
		}
		
		CustomerOrder order = new CustomerOrder(title, itemEntries);
		orderRepository.save(order);

		return "success_order.html";

	}

}