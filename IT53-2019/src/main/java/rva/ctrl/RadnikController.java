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
import rva.model.Obrazovanje;
import rva.model.Radnik;
import rva.model.Sektor;
import rva.repository.Iobrazovanje;
import rva.repository.Iradnik;
import rva.repository.Isektor;

@CrossOrigin
@RestController
@Api(tags = {"CRUD operations on Radnik table"})
public class RadnikController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private Iradnik radnikRepository;
	
	@Autowired
	private Isektor sektorRepository;
	
	@Autowired
	private Iobrazovanje obrazovanjeRepository;
	
	@ApiOperation(value = "Method which returns all rows from Radnik table")
	@GetMapping("/radnik")
	public Collection<Radnik> getAllRadnik (){
		return radnikRepository.findAll();
	}
	
	@ApiOperation(value = "Method which returns Radnik by ID")
	@GetMapping("/radnik/{id}")
	public Optional<Radnik> getRadnikById(@PathVariable Integer id) {
		
		return radnikRepository.findById(id);
	}
	
	@ApiOperation(value = "Method which returns Radnik by Sektor ID")
	@GetMapping("/radnik/sektor/{id}")
	public Collection<Radnik> getRadnikBySektor (@PathVariable Integer id){
		
		Optional<Sektor> temp = sektorRepository.findById(id);
		return radnikRepository.findBySektor(temp);
	}
	
	@ApiOperation(value = "Method which returns Radnik by Obrazovanje ID")
	@GetMapping("/radnik/obrazovanje/{id}")
	public Collection<Radnik> getRadnikByObrazovanje(@PathVariable Integer id){
		
	    Optional<Obrazovanje> temp = obrazovanjeRepository.findById(id);
		return radnikRepository.findByObrazovanje(temp);
	}
	
	@ApiOperation(value = "Method which posts Radnik in database")
	@PostMapping("/radnik")
    public ResponseEntity <Radnik>createRadnik (@RequestBody Radnik radnik){
    	
    	if(!radnikRepository.existsById(radnik.getIdRadnik())) {
    		
    		Radnik temp = radnikRepository.save(radnik);
    		return new ResponseEntity<Radnik>(temp, HttpStatus.CREATED);
    		
    	}else {
    		return new ResponseEntity<Radnik>(HttpStatus.CONFLICT);
    	}
    }
	
	@ApiOperation(value = "Method which updates Radnik table")
	@PutMapping("/radnik")
	public ResponseEntity <Radnik> updateRadnik (@RequestBody Radnik radnik){
    	
    	if(radnikRepository.existsById(radnik.getIdRadnik())) {
    		
    		radnikRepository.save(radnik);
    		return new ResponseEntity<Radnik>(HttpStatus.OK);
    		
    	}else {
    		return new ResponseEntity<Radnik>(HttpStatus.NOT_FOUND);
    	}
    }
	
	@ApiOperation(value = "Method which deletes Radnik table")
	@DeleteMapping("/radnik/{id}")
	public ResponseEntity<Radnik> deleteRadnik (@PathVariable int id){
		
		if(radnikRepository.existsById(id)) {
			radnikRepository.deleteById(id);
			if(id == -100) {
				jdbcTemplate.execute("insert into \"radnik\"values(-100,'TestIme', 'TestPrezime', 123, 8, 8)");
				}
			return new ResponseEntity<Radnik> (HttpStatus.OK);
		}else {
			return new ResponseEntity<Radnik> (HttpStatus.CONFLICT);
			
		}
	}
}
