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


	}

//}
