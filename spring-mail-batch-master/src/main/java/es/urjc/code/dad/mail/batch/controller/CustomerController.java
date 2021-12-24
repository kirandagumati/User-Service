package es.urjc.code.dad.mail.batch.controller;

import es.urjc.code.dad.mail.batch.model.Customer;
import es.urjc.code.dad.mail.batch.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@PostMapping("/createcustomer")
	public Customer createCustomer(@RequestBody Customer customer ) {
		return customerService.saveCustomer(customer);
	}
	@GetMapping("/id")
 public ResponseTemplateVO getCustomerById(@PathVariable("id") int  customerId) {
	 return customerService.getCustomerWithSim(customerId);
 }

	@GetMapping("/customers/export")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		response.setContentType("text/csv");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
		response.setHeader(headerKey, headerValue);

		List<Customer> listcustomers = customerService.getAllCustomers().subList();

		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		String[] csvHeader = {"User ID", "E-mail", "First Name","Last Name","DateOfBrith","SimNumber"};
		String[] nameMapping = {"id", "email", "firstName","lastName","dateOfBrith","simNumber"};

		csvWriter.writeHeader(csvHeader);

		for (Customer customer  : listcustomers) {
			csvWriter.write(customer, nameMapping);
		}

		csvWriter.close();

	}


}
