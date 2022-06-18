package rva.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.model.Obrazovanje;
import rva.model.Radnik;
import rva.model.Sektor;

public interface Iradnik extends JpaRepository<Radnik, Integer>{

	
	Collection<Radnik> findBySektor (Optional<Sektor> temp);
	Collection <Radnik> findByObrazovanje(Optional<Obrazovanje> temp);
}
