package es.urjc.code.dad.mail.batch.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name="customer")
public class Customer {
	@Id
	private int cutomerId;
	@Column(name="customer_name")
	private String customerFirstName;
	@Column(name="customer_name")
	private String customerLastName;
	@Column(name="date_Of_Brith")
	private String dateOfBrith;

	@Column(name="customer_email")
	private String email;
	
	  @OneToMany(targetEntity = Sim.class,cascade =CascadeType.ALL )
	  @JoinColumn(name="customer_id",referencedColumnName = "customerId") private
	 List<Sim> sims;
	 
}
