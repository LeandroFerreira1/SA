package edu.ifes.ci.si.les.sa.services;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.sa.model.Aluno;
import edu.ifes.ci.si.les.sa.model.AtividadeAvaliativa;
import edu.ifes.ci.si.les.sa.model.Aula;
import edu.ifes.ci.si.les.sa.model.AvaliacaoAluno;
import edu.ifes.ci.si.les.sa.model.Bairro;
import edu.ifes.ci.si.les.sa.model.Cidade;
import edu.ifes.ci.si.les.sa.model.Curso;
import edu.ifes.ci.si.les.sa.model.Disciplina;
import edu.ifes.ci.si.les.sa.model.PeriodoLetivo;
import edu.ifes.ci.si.les.sa.model.Professor;
import edu.ifes.ci.si.les.sa.model.Turma;
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

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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
		Bairro bairro1 = new Bairro(null, "Jardim Camburi", uf1, cidade1);
		Bairro bairro2 = new Bairro(null, "Piratininga", uf3, cidade2);
		Bairro bairro3 = new Bairro(null, "Barra do Itapemirim", uf1, cidade3);
		bairroRepository.saveAll(Arrays.asList(bairro1, bairro2, bairro3));

		// CADASTRO AULA
		Aula aula1 = new Aula(null, "Introdução a Algoritmos", sdf.parse("2021-07-12"));
		Aula aula2 = new Aula(null, "Portugol para iniciantes", sdf.parse("2021-07-16"));
		Aula aula3 = new Aula(null, "Importancia do teste de mesa", sdf.parse("2021-07-18"));
		aulaRepository.saveAll(Arrays.asList(aula1, aula2, aula3));

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

		// CADASTRO ATIVIDADE AVALIATIVA (Avaliação)
		AtividadeAvaliativa atividadeAvaliativa1 = new AtividadeAvaliativa(null,
				"Avaliacao Listas Duplamente Encadeadas", "Avaliacao", sdf.parse("2021-02-16"), 25.0, disciplina4);
		AtividadeAvaliativa atividadeAvaliativa2 = new AtividadeAvaliativa(null, "Trabalho de HTML5 - Aspnet.core",
				"Trabalho", sdf.parse("2021-04-16"), 25.0, disciplina1);
		AtividadeAvaliativa atividadeAvaliativa3 = new AtividadeAvaliativa(null, "Avaliacao de Calculo III",
				"Avaliacao", sdf.parse("2021-05-16"), 25.0, disciplina2);
		AtividadeAvaliativa atividadeAvaliativa4 = new AtividadeAvaliativa(null, "Compilação de KERNEL LINUX",
				"Trabalho", sdf.parse("2021-06-16"), 25.0, disciplina3);
		atividadeAvaliativaRepository.saveAll(
				Arrays.asList(atividadeAvaliativa1, atividadeAvaliativa2, atividadeAvaliativa3, atividadeAvaliativa4));

		// CADASTRO PROFESSOR
		Professor professor1 = new Professor(null, "Mario da Silva", sdf.parse("1979-08-10"), "M", "147.2356.987-50",
				"Ruas dos Braos Loics", 21, 1, bairro1, "Mestre", "mm@gmail.com", "123456");
		Professor professor2 = new Professor(null, "Lorena Marcos", sdf.parse("1990-09-15"), "F", "000.748.962-71",
				"Ruas Marcos Fonseca", 52, 1, bairro2, "Mestre", "lorena@gmail.com", "123456");
		Professor professor3 = new Professor(null, "Sebastiao Santos", sdf.parse("1965-08-09"), "M", "951.232.741-32",
				"Avenida Sambaio Dutra", 70, 1, bairro3, "Doutor", "sebastiao@gmail.com", "123456");
		professorRepository.saveAll(Arrays.asList(professor1, professor2, professor3));

		// CADASTRO PERIODO LETIVO
		PeriodoLetivo periodoLetivo1 = new PeriodoLetivo(null, "2021/1", sdf.parse("2021-02-08"), sdf.parse("2021-06-01"), 280);
		PeriodoLetivo periodoLetivo2 = new PeriodoLetivo(null, "2021/2", sdf.parse("2021-07-31"), sdf.parse("2020-12-21"), 283);
		PeriodoLetivo periodoLetivo3 = new PeriodoLetivo(null, "2022/1", sdf.parse("2022-02-10"), sdf.parse("2022-06-03"), 275);
		periodoLetivoRepository.saveAll(Arrays.asList(periodoLetivo1, periodoLetivo2, periodoLetivo3));

		// CADASTRO TURMA
		Turma turma1 = new Turma(null, "ProgramacaoI - 2021.1", 20, sdf.parse("2021-10-12"), sdf.parse("2021-10-15"),
				periodoLetivo1, aula1, atividadeAvaliativa1, professor1, disciplina1);
		Turma turma2 = new Turma(null, "LogicaI - 2021.1", 20, sdf.parse("2021-10-12"), sdf.parse("2021-10-15"),
				periodoLetivo1, aula1, atividadeAvaliativa1, professor1, disciplina1);
		Turma turma3 = new Turma(null, "Calculo I - 2021.1", 20, sdf.parse("2021-10-12"), sdf.parse("2021-10-15"),
				periodoLetivo1, aula1, atividadeAvaliativa1, professor1, disciplina1);
		Turma turma4 = new Turma(null, "Redes I - 2021.1", 20, sdf.parse("2021-10-12"), sdf.parse("2021-10-15"),
				periodoLetivo1, aula1, atividadeAvaliativa1, professor1, disciplina1);
		Turma turma5 = new Turma(null, "Banco de Dados I - 2021.1", 20, sdf.parse("2021-10-12"),
				sdf.parse("2021-10-15"), periodoLetivo1, aula1, atividadeAvaliativa1, professor1, disciplina1);
		turmaRepository.saveAll(Arrays.asList(turma1, turma2, turma3, turma4, turma5));

		// CADASTRO ALUNO
		Aluno aluno1 = new Aluno(null, "Abrao Lincao", sdf.parse("1980-10-12"), "M", "154.145.025-96",
				"Rua Canoi Carpado", 10, 2, bairro1, "20161SI0030", "3987412ES", "abraol@email.cpm", "123456", curso1);
		Aluno aluno2 = new Aluno(null, "Jovelha Nova", sdf.parse("1982-04-13"), "F", "003.134.543-96",
				"Rua Tabata battat", 11, 3, bairro2, "20161MA0031", "8889542ES", "joana@email.cpm", "123456", curso2);
		Aluno aluno3 = new Aluno(null, "Linara Barg", sdf.parse("1989-12-14"), "F", "111.532.004-96",
				"Rua Macarena Barao", 12, 4, bairro3, "20161ME0032", "0005412ES", "magno@email.cpm", "123456", curso3);
		alunoRepository.saveAll(Arrays.asList(aluno1, aluno2, aluno3));
		
		// CADASTRO ADMINISTRADOR
	}
}
