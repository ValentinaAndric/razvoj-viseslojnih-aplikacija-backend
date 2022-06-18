package rva.ctrl;

import java.util.Collection;


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
import rva.model.Preduzece;
import rva.repository.Ipreduzece;


@RestController
@CrossOrigin
@Api(tags = {"CRUD operations on Preduzece table"})
public class PreduzeceController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired 
	private Ipreduzece preduzeceRepository;   
	
	@ApiOperation(value = "Method which returns all rows from Preduzece table")
	@GetMapping("/preduzece")
	public Collection <Preduzece> getAllPreduzece(){
		
		return preduzeceRepository.findAll();

	}
	
	@ApiOperation(value = "Method which returns Preduzece by ID")
	@GetMapping("/preduzece/{id}")
	public Preduzece getPreduzeceById(@PathVariable Integer id) {
		return preduzeceRepository.getById(id);
	}
	
	@ApiOperation(value = "Method which returns Preduzece by name")
	@GetMapping("/preduzece/naziv/{naziv}")
	public Collection<Preduzece> getPreduzeceByNaziv(@PathVariable String naziv) {
		
		return preduzeceRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@ApiOperation(value = "Method which posts Preduzece in database")
	@PostMapping("/preduzece")
	public ResponseEntity<Preduzece> createPreduzece (@RequestBody Preduzece preduzece){
		
		if(!preduzeceRepository.existsById(preduzece.getIdPreduzece())){
			
			Preduzece temp = preduzeceRepository.save(preduzece);
			
			return new ResponseEntity <Preduzece>(temp, HttpStatus.CREATED);
			}else {
				
				return new ResponseEntity <Preduzece>(HttpStatus.CONFLICT);
			}
			
		}

	@ApiOperation(value = "Method which updates Preduzece table")
	@PutMapping("/preduzece")
	public ResponseEntity<Preduzece> updatePreduzece (@RequestBody Preduzece preduzece){
		
		if(preduzeceRepository.existsById(preduzece.getIdPreduzece())) {
			preduzeceRepository.save(preduzece);
			return new ResponseEntity <Preduzece>(HttpStatus.OK);
		}else {
			
			return new ResponseEntity<Preduzece> (HttpStatus.NOT_FOUND);
		}
		
	}
	
	@ApiOperation(value = "Method which deletes Preduzece table")
	@DeleteMapping("/preduzece/{id}")
	public ResponseEntity<Preduzece> deletePreduzece (@PathVariable int id){
		
		if (preduzeceRepository.existsById(id)) {
		
			preduzeceRepository.deleteById(id);
			
			if(id == -100) {
			jdbcTemplate.execute("insert into \"preduzece\" values (-100, 'TestNaziv', 346744, 'TestSediste', 'TestOpis')");
			}
			
			return new ResponseEntity<Preduzece>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Preduzece>(HttpStatus.NOT_FOUND);
		}
		
	}
	
}
	
	
	
	
