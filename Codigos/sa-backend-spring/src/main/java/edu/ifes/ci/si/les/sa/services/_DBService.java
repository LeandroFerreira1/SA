package edu.ifes.ci.si.les.sa.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.sa.model.Cidade;
import edu.ifes.ci.si.les.sa.model.Uf;
import edu.ifes.ci.si.les.sa.repositories.AdministradorRepository;
import edu.ifes.ci.si.les.sa.repositories.AlunoRepository;
import edu.ifes.ci.si.les.sa.repositories.AlunoTurmaRepository;
import edu.ifes.ci.si.les.sa.repositories.AtividadeAvaliativaRepository;
import edu.ifes.ci.si.les.sa.repositories.AulaRepository;
import edu.ifes.ci.si.les.sa.repositories.AvaliacaoAlunoRepository;
import edu.ifes.ci.si.les.sa.repositories.BairroRepository;
import edu.ifes.ci.si.les.sa.repositories.CidadeRepository;
import edu.ifes.ci.si.les.sa.repositories.CursoRepository;
import edu.ifes.ci.si.les.sa.repositories.DisciplinaRepository;
import edu.ifes.ci.si.les.sa.repositories.PeriodoLetivoRepository;
import edu.ifes.ci.si.les.sa.repositories.PresencaAlunoRepository;
import edu.ifes.ci.si.les.sa.repositories.ProfessorRepository;
import edu.ifes.ci.si.les.sa.repositories.TurmaRepository;
import edu.ifes.ci.si.les.sa.repositories.UfRepository;

@Service
public class _DBService {

	@Autowired
	private AdministradorRepository administradorRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private AlunoTurmaRepository alunoTurmaRepository;
	
	@Autowired
	private AtividadeAvaliativaRepository atividadeAvaliativaRepository;
	
	@Autowired
	private AulaRepository aulaRepository;
	
	@Autowired
	private AvaliacaoAlunoRepository avaliacaoAlunoRepository;
	
	@Autowired
	private BairroRepository bairroRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	@Autowired
	private PeriodoLetivoRepository periodoLetivoRepository;
	
	@Autowired
	private PresencaAlunoRepository presencaAlunoRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private TurmaRepository turmaRepository;
	
	@Autowired
	private UfRepository ufRepository;
	
	public void instantiateTestDatabase() throws ParseException, IOException {
		
	
		Uf uf1 = new Uf(null,"ES", "Espirito Santo");
		Uf uf2 = new Uf(null,"RJ", "Rio de Janeiro");
		ufRepository.saveAll(Arrays.asList(uf1,uf2));
		
		Cidade cidade1 = new Cidade(null, "Vitoria",uf1);
		Cidade cidade2 = new Cidade(null,"Osasco", uf2); 
		cidadeRepository.saveAll(Arrays.asList(cidade1,cidade2));
	
		
	}
}
