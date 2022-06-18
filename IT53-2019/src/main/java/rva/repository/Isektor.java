package rva.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.model.Sektor;



public interface Isektor extends JpaRepository<Sektor, Integer> {
	Collection<Sektor> findByNazivContainingIgnoreCase(String naziv);

}
