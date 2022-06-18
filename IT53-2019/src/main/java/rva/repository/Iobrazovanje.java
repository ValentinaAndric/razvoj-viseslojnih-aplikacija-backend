package rva.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.model.Obrazovanje;

public interface Iobrazovanje extends JpaRepository<Obrazovanje, Integer>{
	
	Collection<Obrazovanje> findByNazivContainingIgnoreCase(String naziv);
}
