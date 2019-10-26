package antonio.camas.shop;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

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
		
		orderRepository.save(new CustomerOrder("Fruits", itemsOrder1));
		orderRepository.save(new CustomerOrder("Drinks"));
	}
	
	@GetMapping("/")
	public String landingPage(Model model) {

		model.addAttribute("orders", orderRepository.findAll());

		return "index";
	}

	@GetMapping("/new_order_form")
	public String newOrderForm() {
		return "new_order";
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
		}
		
		CustomerOrder order = new CustomerOrder(title, itemEntries);
		orderRepository.save(order);

		return "success_order";

	}

	@PostMapping(value ="/create_order")
	public String createOrderPost(WebRequest request ) {
		String title = request.getParameter("title");
		String[] items = request.getParameterValues("items");
		
		List<Item> itemEntries = new ArrayList<Item>();
		for (String item : items) {
			Item entry = new Item(item);
			itemEntries.add(entry);
			itemRepository.save(entry);
		}
		
		
		CustomerOrder order = new CustomerOrder(title, itemEntries);
		orderRepository.save(order);
		//System.out.println(order);
		
		return "success_order";

	}

	@GetMapping("/delete_order/{id}")
	public String deleteOrder(@PathVariable long id) {

		CustomerOrder order = orderRepository.findById(id).get();
		List<Item> items = order.getItems();
		items.size(); //resolve the lazy query before deleting.
		orderRepository.delete(order);
		
		for (Item item : items) {
			itemRepository.delete(item);
		}
		
		return "success_order";
	}
	
	@GetMapping("/modify_order_form/{id}")
	public String modifyOrderForm(Model model, @PathVariable long id) {

		CustomerOrder order = orderRepository.findById(id).get();

		model.addAttribute("order", order);

		return "modify_order_form";
	}
	
	@PostMapping(value ="/modify_order")
	public String modifyrderPost(WebRequest request ) {
		long orderId = Long.parseLong(request.getParameter("orderId"));
		String[] newItemNames = request.getParameterValues("items");

		CustomerOrder order = orderRepository.findById(orderId).get();
		
		
		deleteItemToOrder(newItemNames, order);
		addItemsToOder(newItemNames, order);
		
		return "success_order";
	}

	private void deleteItemToOrder(String[] newItemNames, CustomerOrder order) {
		List<Item> oldItemsOrder = order.getItems();
		for (int i=0; i< oldItemsOrder.size(); i++)
		{
			Item oldItem = oldItemsOrder.get(i);
			if(!Arrays.asList(newItemNames).contains(oldItem.getName())) {
				order.removeItem(oldItem);
				orderRepository.save(order);
				itemRepository.delete(oldItem);
			};
		}
	}

	private void addItemsToOder(String[] newItemNames, CustomerOrder order) {
		for (String newItem : newItemNames) {
			if(isNewItem(newItem, order)) {		
				Item itemToAdd = new Item(newItem);
				itemRepository.save(itemToAdd);
				order.addItem(itemToAdd);
				orderRepository.save(order);
			}
		}
	}
	
	private boolean isNewItem(String itemName, CustomerOrder order) {
		List<Item> itemsInDBforName = itemRepository.findByName(itemName);
		if (itemsInDBforName.isEmpty()) {
			return true;
		}
		
		for (Item itemFound: itemsInDBforName) {
			CustomerOrder orderWithItem = orderRepository.findByItemsId(itemFound.getId());
			
			if(orderWithItem.getId() == order.getId()) {
				return false;
			}
		}

		return true;
	}
}