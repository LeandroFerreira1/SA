package edu.ifes.ci.si.les.sa.services;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;

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
import edu.ifes.ci.si.les.sa.repositories.TelefoneRepository;
import edu.ifes.ci.si.les.sa.repositories.TurmaRepository;
import edu.ifes.ci.si.les.sa.repositories.UfRepository;

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
	private TelefoneRepository telefoneRepository;
	@Autowired
	private TurmaRepository turmaRepository;
	@Autowired
	private UfRepository ufRepository;
	
	public void instantiateTestDatabase() throws ParseException, IOException {
		
		
	}
}
