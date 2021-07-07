package edu.ifes.ci.si.les.sa.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.sa.model.AvaliacaoAluno;
import edu.ifes.ci.si.les.sa.model.Bairro;
import edu.ifes.ci.si.les.sa.model.Cidade;
import edu.ifes.ci.si.les.sa.model.Curso;
import edu.ifes.ci.si.les.sa.model.Disciplina;
import edu.ifes.ci.si.les.sa.model.PeriodoLetivo;
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

		// CADASTRO UF
		Uf uf1 = new Uf(null, "ES", "Espirito Santo");
		Uf uf2 = new Uf(null, "RJ", "Rio de Janeiro");
		Uf uf3 = new Uf(null, "SP", "São Paulo");
		ufRepository.saveAll(Arrays.asList(uf1, uf2, uf3));

		// CADASTRO CIDADE
		Cidade cidade1 = new Cidade(null, "Vitória", uf1);
		Cidade cidade2 = new Cidade(null, "Osasco", uf2);
		Cidade cidade3 = new Cidade(null, "Marataízes", uf1);
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));

		// CADASTRO BAIRRO
		Bairro bairro1 = new Bairro(null, "Jardim Camburi", cidade1);
		Bairro bairro2 = new Bairro(null, "Piratininga", cidade2);
		Bairro bairro3 = new Bairro(null, "Barra do Itapemirim", cidade3);
		bairroRepository.saveAll(Arrays.asList(bairro1, bairro2, bairro3));

		// CADASTRO CURSO
		Curso curso1 = new Curso(null, "Sistemas de Informação", 2370, 4, "Graduação");
		Curso curso2 = new Curso(null, "Matemática", 2370, 4, "Graduação");
		Curso curso3 = new Curso(null, "Engenharia Mecânica", 2370, 4, "Graduação");
		Curso curso4 = new Curso(null, "Engenharia de Minas", 2370, 4, "Graduação");
		Curso curso5 = new Curso(null, "Ténico em Informática", 1200, 2, "Técnico");
		cursoRepository.saveAll(Arrays.asList(curso1, curso2, curso3, curso4, curso5));

		// CADASTRO DISCIPLINA
		Disciplina disciplina1 = new Disciplina(null, "Desenvolvimento WEB", 60, "DW", curso1);
		Disciplina disciplina2 = new Disciplina(null, "Cálculo 3", 90, "CALC3", curso2);
		Disciplina disciplina3 = new Disciplina(null, "Sistema Operacional", 60, "SO", curso1);
		Disciplina disciplina4 = new Disciplina(null, "Programação Orientada a Objetos 1", 60, "POO1", curso1);
		Disciplina disciplina5 = new Disciplina(null, "Fundamentos de Hardware", 30, "FH", curso5);
		disciplinaRepository.saveAll(Arrays.asList(disciplina1, disciplina2, disciplina3, disciplina4, disciplina5));

		// CADASTRO PROFESSOR

		// CADASTRO PERIODO LETIVO
		// PeriodoLetivo periodoLetivo1 = new PeriodoLetivo(null, "2021/1",
		// "2021-02-08","2020-10-10",208 );
		// Tudo ok, só constando um problema na linha acima com a sintax da inserção do
		/// tipo date
		// periodoLetivoRepository.saveAll(Arrays.asList(periodoLetivo1));

		// CADASTRO TURMA

		// CADASTRO AVALIACAO
		// AvaliacaoAluno avaliacaoAluno1 = new AvaliacaoAluno(null,);
		// avaliacaoAlunoRepository.saveAll(Arrays.asList(avaliacaoAluno1));

		// CADASTRO ADMINISTRADOR

		// CADASTRO ALUNO

	}
}
