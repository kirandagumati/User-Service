package es.urjc.code.dad.mail.batch.controller;
import java.util.Optional;

import es.urjc.code.dad.mail.batch.model.Customer;
import es.urjc.code.dad.mail.batch.model.Sim;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
	private Optional<Customer> customer;
    private Sim sim;
	
	}

