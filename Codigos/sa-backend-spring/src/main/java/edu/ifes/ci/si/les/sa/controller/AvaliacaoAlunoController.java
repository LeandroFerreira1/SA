package edu.ifes.ci.si.les.sa.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.ifes.ci.si.les.sa.model.AvaliacaoAluno;
import edu.ifes.ci.si.les.sa.services.AvaliacaoAlunoService;
import edu.ifes.ci.si.les.sa.services.exceptions.ConstraintException;

@RestController
@RequestMapping(value = "/api/v1/avaliacao")
public class AvaliacaoAlunoController {

	@Autowired
	private AvaliacaoAlunoService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<AvaliacaoAluno>> findAll() {
		Collection<AvaliacaoAluno> collection = service.findAll();
		return ResponseEntity.ok().body(collection);
	}

	//@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public ResponseEntity<AvaliacaoAluno> find(@PathVariable Integer id) {
	//	AvaliacaoAluno obj = service.findById(id);
	//	return ResponseEntity.ok().body(obj);
	//}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<AvaliacaoAluno> insert(@Valid @RequestBody AvaliacaoAluno obj, BindingResult br) {
		if (br.hasErrors())
			throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		obj = service.insert(obj);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<AvaliacaoAluno> update(@Valid @RequestBody AvaliacaoAluno obj, BindingResult br) {
		if (br.hasErrors())
			throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		obj = service.update(obj);
		return ResponseEntity.ok().body(obj);
	}

	//@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	//public ResponseEntity<Void> delete(@PathVariable Integer id) {
	//	service.delete(id);
	//	return ResponseEntity.noContent().build();
	}

//}
