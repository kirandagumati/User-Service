package es.urjc.code.dad.mail.batch.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Data
@Table(name="customer")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Sim {
	private int simId;
	@Column(name="sim_name")
	private String simName;
	@Column(name="sim_number")
	private String simNumber;
	@Column(name="sim_network")
	private String simNetwork;
}
