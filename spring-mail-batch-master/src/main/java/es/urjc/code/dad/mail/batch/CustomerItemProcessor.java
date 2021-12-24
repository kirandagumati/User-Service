package es.urjc.code.dad.mail.batch;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

import es.urjc.code.dad.mail.batch.model.Customer;

public class CustomerItemProcessor implements ItemProcessor<Customer, MimeMessage> {

	private static final Logger log = LoggerFactory.getLogger(CustomerItemProcessor.class);

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private VelocityEngine engine;
	private String sender;
	private String attachment;
	
	public CustomerItemProcessor(String sender, String attachment) {
		this.sender = sender;
		this.attachment = attachment;
	}

	@Override
	public MimeMessage process(Customer customer) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		Map<String, Object> model = new HashMap<>();
		model.put("id", customer.getCutomerId());
		model.put("firstname", customer.getCustomerFirstName());
		model.put("lastname", customer.getCustomerLastName());
		model.put("dateofbrith", customer.getDateOfBrith());
		helper.setFrom(sender);
		helper.setTo(customer.getEmail());
		helper.setCc(sender);
		helper.setSubject(VelocityEngineUtils.mergeTemplateIntoString(engine, "email-subject.vm", "UTF-8", model));
		helper.setText(VelocityEngineUtils.mergeTemplateIntoString(engine, "email-body.vm", "UTF-8", model));
		
		log.info("Preparing message for: " + customer.getEmail());
		
		FileSystemResource file = new FileSystemResource(attachment);
		helper.addAttachment(file.getFilename(), file);
		
		return message;
	}

	
}
