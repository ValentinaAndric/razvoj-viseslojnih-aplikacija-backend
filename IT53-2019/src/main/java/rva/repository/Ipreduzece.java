package rva.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import rva.model.Preduzece;

public interface Ipreduzece extends JpaRepository<Preduzece, Integer> {

	Collection<Preduzece> findByNazivContainingIgnoreCase(String naziv);
}
