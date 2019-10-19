package antonio.camas.shop;

import java.lang.reflect.Array;
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

@Controller
public class IndexController {

	@Autowired
	private CustomerOrderRepository orderRepository;

	@Autowired
	private ItemRepository itemRepository;

	@PostConstruct
	public void init() {
		
		Item item1Or1 = new Item("Item1Or1");
		itemRepository.save(item1Or1);
		
		Item item1Or2 = new Item("Item1Or2");
		itemRepository.save(item1Or2);
		
		List<Item> itemsOrder1 = Arrays.asList(item1Or1, item1Or2);
		
		orderRepository.save(new CustomerOrder("Order1", itemsOrder1));
		orderRepository.save(new CustomerOrder("Order2"));
	}
	
	@RequestMapping("/")
	public String landingPage(Model model) {

		model.addAttribute("orders", orderRepository.findAll());

		return "main";
	}

//	@PostMapping("/anuncio/nuevo")
//	public String nuevoAnuncio(Model model, CustomerOrder anuncio) {
//
//		orderRepository.save(anuncio);
//
//		return "anuncio_guardado";
//
//	}
//
//	@GetMapping("/anuncio/{id}")
//	public String verAnuncio(Model model, @PathVariable long id) {
//
//		CustomerOrder anuncio = orderRepository.findById(id).get();
//
//		model.addAttribute("anuncio", anuncio);
//
//		return "ver_anuncio";
//	}
}