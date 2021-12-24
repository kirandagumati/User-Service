package es.urjc.code.dad.mail.batch.service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.urjc.code.dad.mail.batch.controller.ResponseTemplateVO;
import es.urjc.code.dad.mail.batch.model.Customer;
import es.urjc.code.dad.mail.batch.model.Sim;
import es.urjc.code.dad.mail.batch.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	private RestTemplate restTemplate;

	public Customer saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}

	// TODO Auto-generated method stub
	public ResponseTemplateVO getCustomerWithSim(int customerId) {
		log.info("Inside getUserWithDepartment of UserService");
		ResponseTemplateVO vo = new ResponseTemplateVO();
		Optional<Customer> customer = customerRepository.findById(customerId);

		Sim sim =
				restTemplate.getForObject("http://Sim-SERVICE/sims/" + customer.get().getCutomerId()
						,Sim.class);

		vo.setCustomer(customer);
		vo.setSim(sim);

		return  vo;
	}
	//List of all customers
	public List<Customer> getAllCustomers(){
		return customerRepository.findAll();
	}
	public String[] getMailAfterSevenDay() {

		String date1 = DateTimeFormatter.ofPattern("yyyy.MM.dd").format(LocalDate.now());

		return customerRepository
				.findAll()
				.stream()
				.filter(i -> i.getDateOfBirth() == date1.plusDays(7))
				.map(e -> e.getEmailId()).collect(Collectors.toList()).toArray(String[]::new);
	}

}
