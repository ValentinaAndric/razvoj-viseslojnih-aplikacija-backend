package rva.ctrl;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.model.Sektor;
import rva.repository.Isektor;

@CrossOrigin
@RestController
@Api(tags = {"CRUD operations on Sektor table"})
public class sektorController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
 
	@Autowired
	private Isektor sektorRepository;
	
	@ApiOperation(value = "Method which returns all rows from Sektor table")
	@GetMapping("/sektor")
	public Collection<Sektor> getAllSektor(){
		
		return sektorRepository.findAll();
	}
	
	@ApiOperation(value = "Method which returns Sektor by ID")
	@GetMapping("/sektor/{id}")
	public Optional<Sektor> getSektorById (@PathVariable Integer id) {
		
		return sektorRepository.findById(id);
	}
	
	@ApiOperation(value = "Method which returns Sektor by name")
	@GetMapping("/sektor/naziv/{naziv}")
	public Collection<Sektor> getSektorByNaziv (@PathVariable String naziv){
		return sektorRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@ApiOperation(value = "Method which posts Sektor in database")
	@PostMapping("/sektor")
	public ResponseEntity<Sektor> createSektor (@RequestBody Sektor sektor){
		if (!sektorRepository.existsById(sektor.getIdSektor())) {
			Sektor temp = sektorRepository.save(sektor);
			return new ResponseEntity<Sektor>(temp, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<Sektor>(HttpStatus.CONFLICT);
		}
	}
	
	@ApiOperation(value = "Method which updates Sektor table")
	@PutMapping("/sektor")
	public ResponseEntity<Sektor> updateSektor(@RequestBody Sektor sektor){
		if(sektorRepository.existsById(sektor.getIdSektor())) {
			sektorRepository.save(sektor);
			return new ResponseEntity<Sektor>(HttpStatus.OK);
		}else {
			
			return new ResponseEntity<Sektor>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "Method which deletes Sektor table")
	@DeleteMapping("/sektor/{id}")
	public ResponseEntity<Sektor> deleteSektor (@PathVariable int id){
		
		if(sektorRepository.existsById(id)) {
			jdbcTemplate.execute("delete from radnik where sektor = " + id);
			sektorRepository.deleteById(id);
			if(id == -100) {
				jdbcTemplate.execute("insert into \"sektor\" values (-100, 'Markenting', 'M', 8)");
				}
			return new ResponseEntity<Sektor>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Sektor>(HttpStatus.NOT_FOUND);
		}
	}
}
