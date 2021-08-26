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
              :items="lCurso"
              item-text="nome"
              label="Curso"
              default="SELECIONE"
              v-model="curso"
              outlined
              required
              item-value="id"
              :rules="registroAprovacaoRulesCurso">
          </v-select>

          <v-select
              :items="lTurma"
              item-text="nome"
              label="Turma"
              default="SELECIONE"
              v-model="turma"
              outlined
              required
              item-value="id"
              :rules="registroAprovacaoRulesTurma">
          </v-select>
            
          </v-row>
        </v-spacer>
      </v-toolbar>
    </v-container>

    <v-container>
      <v-data-table
        v-model="selected"
        :headers="headers"
        :items="alunos"
        :single-select="singleSelect"
        item-key=""
        show-select
        class="elevation-1">

        <template v-slot:item.status="{ item }">
        <v-select
          v-model="cSel"
          :items="item.status"
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

//const textos = {
  //novo: "Registrar Aprovação",
  //edicao: "Edição de Registro De Aprovação",
  //exclusao: "Deseja mesmo remover estes Registrso De Aprovação?",
//};

export default {
   data: () => ({
    //service: RegistroDeNotasService.build(),
    name: "lRegAprov",
    components: {},
    registroAprovacaoRulesCurso: [(v) => !!v || "Seleção Necessária"],
    registroAprovacaoRulesTurma: [(v) => !!v || "Seleção Necessária"],
      
      headers: [
        { text: "Matrícula", align: "start", value: "", width: "10%" },
        {
          text: "Aluno",
          
          value: "",
          width: "30%"
        },
        { text: "Faltas", align: "center", value: "", width: "10%" },
        { text: "Nota", align: "center", value: "", width: "10%" },
        { text: "Prova Final", align: "center", value: "", width: "10%" },
        { text: "Nota Final", align: "center", value: "", width: "10%" },
        { text: "Status", align: "center", value: "", width: "20%" },
      ],
      lCurso: [],
      lTurma: [],
      editedItem: {},
  }),
  created() {
    this.initialize();
  },
  methods: {
    initialize() {
      this.fetchRecordsCurso();
      this.fetchRecordsTurma();
    },
    fetchRecordsCurso() {
      serviceCurso.search({}).then(this.fetchRecordsSuccessCurso);
    },
    fetchRecordsSuccessCurso(response) {
      if (Array.isArray(response.rows)) {
        this.lCurso = response.rows;
        return;
      }
      this.lCurso = [];
    },
    fetchRecordsTurma() {
      serviceTurma.search({}).then(this.fetchRecordsSuccessTurma);
    },
    fetchRecordsSuccessTurma(response) {
      if (Array.isArray(response.curso)) {
        this.lTurma = response.curso;
        return;
      }
      this.lTurma = [];
    },
  },
};
</script>