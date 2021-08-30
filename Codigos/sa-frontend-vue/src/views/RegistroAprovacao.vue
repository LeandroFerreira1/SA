<template>
  <div>
    <v-container>
      <v-toolbar flat>
        <v-spacer>
          <v-toolbar-title
            ><center>Registro de Aprovação</center></v-toolbar-title
          >
        </v-spacer>
      </v-toolbar>
    </v-container>

    <v-container>
      <v-toolbar>
        <v-spacer>
          <br />
          <v-row>

          <v-select
              :items="lCursoRegApr"
              item-text="nome"
              label="Curso"
              v-model="curso"
              item-value="id"
              outlined
              required
              @change="fetchRecordsTurma"
              :rules="registroAprovacaoRulesCurso">
          </v-select>

          <v-select
              :items="lTurmaRegApr"
              item-text="nome"
              label="Turma"
              v-model="turma"
              item-value="id"
              outlined
              required
              @change="fetchRecordsAlunoTurma"
              :rules="registroAprovacaoRulesTurma">
          </v-select>
            
          </v-row>
        </v-spacer>
      </v-toolbar>
    </v-container>

    <v-container>
      <v-data-table
        :headers="headers"
        :items="lAlunoTurmaRegApr"
        :single-select="singleSelect"
        item-key="alunoTurma"
        show-select
        class="elevation-1">

        <template v-slot:item.status="{ item }">
        <v-select
          v-model="item.status"
          :items="cSel"
      ></v-select>
      </template> 
      </v-data-table>
    </v-container>

    <v-container>
      <div class="d-flex justify-center mb-6">
        <div class="mr-2">
          <v-btn block color="success">Salvar</v-btn>
        </div>
        <div class="mr-2">
          <v-btn block color="primary">Limpar</v-btn>
        </div>
        <div class="mr-2">
          <v-btn block color="red">Cancelar</v-btn>
        </div>
      </div>
    </v-container>
  </div>
</template>

<script>
//import RegistroAprovacaoService from "../service/domain/RegistroAprovacaoService";

import CursoService from "../service/domain/CursoService";
const serviceCurso = CursoService.build();

import TurmaService from "../service/domain/TurmaService";
const serviceTurma = TurmaService.build();

import AlunoTurmaService from "../service/domain/AlunoTurmaService";
const serviceAlunoTurma = AlunoTurmaService.build();

//const textos = {
  //novo: "Registrar Aprovação",
  //edicao: "Edição de Registro De Aprovação",
  //exclusao: "Deseja mesmo remover estes Registrso De Aprovação?",
//};

export default {
   data: () => ({
    components: {},
    registroAprovacaoRulesCurso: [(v) => !!v || "Seleção Necessária"],
    registroAprovacaoRulesTurma: [(v) => !!v || "Seleção Necessária"],
    cSel: ['APROVADO', 'CURSANDO', 'REPROVADO'],
      headers: [
        { text: "Matrícula", align: "start", value: "aluno.matricula", width: "25%" },
        { text: "Aluno", value: "aluno.nome", width: "25%"
        },
        { text: "Faltas", align: "center", value: "presencaFinal", width: "15%" },
        { text: "Nota Final", align: "center", value: "notaFinal", width: "15%" },
        { text: "Status", align: "center", value: "status", width: "20%" },
      ],
      lCursoRegApr: [],
      lTurmaRegApr: [],
      lAlunoTurmaRegApr:[],
      lAlunoRegApr:[],
      i: 0,
  }),
  created() {
    this.initialize();
  },
  methods: {
    initialize() {
      this.fetchRecordsCurso();
    },
    fetchRecordsCurso() {
      serviceCurso.search({}).then(this.fetchRecordsSuccessCurso);
    },
    fetchRecordsSuccessCurso(response) {
      if (Array.isArray(response.rows)) {
        this.lCursoRegApr = response.rows;
        return;
      }
      this.lCursoRegApr = [];
    },
    fetchRecordsTurma() {
      serviceTurma.searchFiltro("findByCurso", this.curso).then(this.fetchRecordsSuccessTurma);
    },
    fetchRecordsSuccessTurma(response) {
      if (Array.isArray(response.rows)) {
        this.lTurmaRegApr = response.rows;
        return;
      }
      this.lTurmaRegApr = [];
    },
    fetchRecordsAlunoTurma() {
      serviceAlunoTurma.searchFiltro("findByTurma", this.turma).then(this.fetchRecordsSuccessAlunoTurma);
    },
    fetchRecordsSuccessAlunoTurma(response) {
      if (Array.isArray(response.rows)) {
        this.lAlunoTurmaRegApr = response.rows;
        return;
      }
      this.lAlunoTurmaRegApr = [];
    },
  },
};
</script>