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
import rva.model.Obrazovanje;
import rva.repository.Iobrazovanje;

@RestController
@CrossOrigin
@Api(tags = {"CRUD operations on Obrazovanje table"})
public class ObrazovanjeController {
	
	
	@Autowired 
	private Iobrazovanje obrazovanjeRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "Method which returns all rows from Obrazovanje table")
	@GetMapping("/obrazovanje")
	public Collection <Obrazovanje> getAllObrazovanje(){
		
		return obrazovanjeRepository.findAll();
		
	}
	@ApiOperation(value = "Method which returns Obrazovanje by ID")
	@GetMapping("/obrazovanje/{id}")
	public Obrazovanje getObrazaovanjeById (@PathVariable Integer id) {
		return obrazovanjeRepository.getById(id);
	}
	
	@ApiOperation(value = "Method which returns Obrazovanje by name")
	@GetMapping("/obrazovanje/naziv/{naziv}")
	public Collection<Obrazovanje> getObrazovanjeByNaziv (@PathVariable String naziv){
		
		return obrazovanjeRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@ApiOperation(value = "Method which posts Obrazovanje in database")
	@PostMapping("/obrazovanje")
	public ResponseEntity<Obrazovanje> createObrazovanje(@RequestBody Obrazovanje obrazovanje){
		if(!obrazovanjeRepository.existsById(obrazovanje.getIdObrazovanje()))
		{
			Obrazovanje temp = obrazovanjeRepository.save(obrazovanje);
			return new ResponseEntity<Obrazovanje>(temp,HttpStatus.CREATED);
		}else {
			return new ResponseEntity<Obrazovanje> (HttpStatus.CONFLICT);
		}
		
	}
	
	@ApiOperation(value = "Method which updates Obrazovanje table")
	@PutMapping("/obrazovanje")
	public ResponseEntity<Obrazovanje> updateObrazovanje(@RequestBody Obrazovanje obrazovanje){
		
		if(obrazovanjeRepository.existsById(obrazovanje.getIdObrazovanje())) {
			obrazovanjeRepository.save(obrazovanje);
			return new ResponseEntity<Obrazovanje>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Obrazovanje> (HttpStatus.NOT_FOUND);
		}
		
	}
	
	@ApiOperation(value = "Method which deletes Obrazovanje table")
	@DeleteMapping("/obrazovanje/{id}")
	public ResponseEntity<Obrazovanje> deleteObrazovanje(@PathVariable int id ){
		
		if(obrazovanjeRepository.existsById(id)) {
			obrazovanjeRepository.deleteById(id);
			
			if (id==-100) {
			jdbcTemplate.execute("insert into \"obrazovanje\" values (-100, 'FTN', 'MAS', 'Great!')");
			}
		   return new ResponseEntity<Obrazovanje> (HttpStatus.OK);
		    
		}else {
			return new ResponseEntity<Obrazovanje> (HttpStatus.NOT_FOUND);
		}
		
	}
	
}
