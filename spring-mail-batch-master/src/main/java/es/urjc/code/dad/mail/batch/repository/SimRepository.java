package es.urjc.code.dad.mail.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.urjc.code.dad.mail.batch.model.Sim;

@Repository
public interface SimRepository extends JpaRepository<Sim, Integer> {

}
