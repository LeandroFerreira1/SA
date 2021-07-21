<template>
  <v-data-table
    :headers="headers"
    :items="lRegistroDeNotas"
    sort-by="id"
    class="elevation-1"
    :loading="carregando" 
    loading-text="Aguarde... Carregando"
  >
    <template v-slot:top>
      <v-toolbar flat>
        <v-toolbar-title>Registro de Notas</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-dialog v-model="dialog" max-width="800px">
          <template v-slot:activator="{ on, attrs }">
            <v-btn color="primary" dark class="mb-2" v-bind="attrs" v-on="on"
              >Novo Item</v-btn
            >
          </template>
          <v-card>
            <v-form ref="form" v-model="valid">
              <v-card-title>
                <span class="headline">{{ formTitle }}</span>
              </v-card-title>

              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12" sm="6" md="12">
                      <v-text-field
                        v-model="editedItem.nome"
                        label="Nome"
                        outlined
                        required
                        :counter="200"
                        :rules="registroDeNotasRulesNome"
                      ></v-text-field>
                    </v-col>

                    <v-col cols="12" sm="4" md="6">
                      <v-combobox
                        v-model="editedItem.professor"
                        label="Professor"
                        outlined
                        :items="lProfessor"
                        item-text="nome"
                        required
                        :rules="registroDeNotasRulesProfessor"
                      ></v-combobox>
                    </v-col>
                    <v-col cols="12" sm="4" md="6">
                      <v-combobox
                        v-model="editedItem.curso"
                        label="Curso"
                        item-text="nome"
                        outlined
                        :items="lCurso"
                        required
                        @change="filtrarDisciplinaPorCurso"
                        :rules="registroDeNotasRulesCurso"
                      ></v-combobox>
                    </v-col>

                    <v-col cols="12" sm="4" md="6">
                      <v-combobox
                        v-model="editedItem.disciplina"
                        label="Disciplina"
                        outlined
                        required
                        :items="lDisciplinaFiltrada"
                        item-text="nome"
                        :rules="registroDeNotasRulesDisciplina"
                      ></v-combobox>
                    </v-col>

                    <v-col cols="12" sm="4" md="3">
                      <v-combobox
                        v-model="editedItem.atividadeAvaliativa"
                        label="Período Letivo"
                        outlined
                        :items="lAtividadeAvaliativa"
                        required
                        item-text="nome"
                        :rules="registroDeNotasRulesAtividadeAvaliativa"
                      ></v-combobox>
                    </v-col>
                    <v-col cols="12" sm="6" md="3">
                      <v-text-field
                        v-model="editedItem.qtdVaga"
                        label="Vagas"
                        outlined
                        required
                        :rules="registroDeNotasRulesVagas"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="6">
                      <v-menu
                        ref="menuEntrada"
                        :close-on-content-click="false"
                        transition="scale-transition"
                        offset-y
                        min-width="290px"
                      >
                        <template v-slot:activator="{ on, attrs }">
                          <v-text-field
                            v-model="editedItem.dataInicio"
                            label="Data de Inicio"
                            readonly
                            v-bind="attrs"
                            v-on="on"
                            outlined
                            required
                           
                          ></v-text-field>
                        </template>
                        <v-date-picker
                          v-model="editedItem.dataInicio"
                          no-title
                          scrollable
                        >
                          <v-spacer></v-spacer>
                          <v-btn
                            text
                            color="primary"
                            @click="menuEntrada = false"
                            >Cancelar</v-btn
                          >
                          <v-btn
                            text
                            color="primary"
                            @click="$refs.menuEntrada.save(dataInicio)"
                            >OK</v-btn
                          >
                        </v-date-picker>
                      </v-menu>
                    </v-col>

                    <v-col cols="12" sm="6" md="6">
                      <v-menu
                        ref="menuEntrada"
                        :close-on-content-click="false"
                        transition="scale-transition"
                        offset-y
                        min-width="290px"
                      >
                        <template v-slot:activator="{ on, attrs }">
                          <v-text-field
                            v-model="editedItem.dataFim"
                            label="Data de Fim"
                            readonly
                            v-bind="attrs"
                            v-on="on"
                            outlined
                            required
                       
                          ></v-text-field>
                        </template>
                        <v-date-picker
                          v-model="editedItem.dataFim"
                          no-title
                          scrollable
                        >
                          <v-spacer></v-spacer>
                          <v-btn
                            text
                            color="primary"
                            @click="menuEntrada = false"
                            >Cancelar</v-btn
                          >
                          <v-btn
                            text
                            color="primary"
                            @click="$refs.menuEntrada.save(dataFim)"
                            >OK</v-btn
                          >
                        </v-date-picker>
                      </v-menu>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="close"
                  >Cancelar</v-btn
                >
                <v-btn
                  :disabled="!valid"
                  color="blue darken-1"
                  text
                  @click="save"
                  >Salvar</v-btn
                >
              </v-card-actions>
            </v-form>
          </v-card>
        </v-dialog>
        <v-dialog v-model="dialogExcluir" max-width="430px">
          <v-card>
            <v-card-title class="headline"
              >Deseja mesmo remover este Item?</v-card-title
            >
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="closeExcluir"
                >Cancelar</v-btn
              >
              <v-btn color="blue darken-1" text @click="deleteItemComfirm"
                >Sim</v-btn
              >
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>
    <template v-slot:[`item.actions`]="{ item }">
      <v-icon small class="mr-2" @click="editItem(item)">mdi-pencil</v-icon>
      <v-icon small @click="deleteItem(item)">mdi-delete</v-icon>
    </template>
   
    <template v-slot:no-data>
      <v-btn color="primary" @click="initialize">Alterar</v-btn>
    </template>
  </v-data-table>
</template>

<script>
import { mask } from "@titou10/v-mask";
import RegistroDeNotasService from "../service/domain/RegistroDeNotasService";
import ProfessorService from "../service/domain/ProfessorService";
import DisciplinaService from "../service/domain/DisciplinaService";
import CursoService from "../service/domain/CursoService";
import AtividadeAvaliativaService from "../service/domain/AtividadeAvaliativaService";
const serviceProfessor = ProfessorService.build();
const serviceDisciplina = DisciplinaService.build();
const serviceCurso = CursoService.build();
const serviceAtividadeAvaliativa = AtividadeAvaliativaService.build();

const textos = {
  novo: "Nova RegistroDeNotas",
  edicao: "Edição de RegistroDeNotas",
  exclusao: "Deseja mesmo remover esta RegistroDeNotas?",
};

export default {
  directives: { mask },
  data: () => ({
    service: RegistroDeNotasService.build(),
    dialog: false,
    dialogExcluir: false,
    valid: true,
    registroDeNotasRulesNome: [
      (v) => !!v || "Preenchimento Necessário",
      (v) =>
        (v && v.length <= 50 && v.length >= 3) ||
        "O campo deve ter pelo menos 3 e no maximo 50 letras",
    ],
    registroDeNotasRulesVagas: [
      (v) => !!v || "Preenchimento Necessário",
      (v) =>
        (v && v.length <= 20 && v.length >= 1) ||
        "O campo deve ter pelo menos 3 e no maximo 20 digitos",
    ],
    registroDeNotasRulesCurso: [(v) => !!v || "Seleção Necessária"],
    registroDeNotasRulesProfessor: [(v) => !!v || "Seleção Necessária"],
    registroDeNotasRulesDisciplina: [(v) => !!v || "Seleção Necessária"],
    registroDeNotasRulesAtividadeAvaliativa: [(v) => !!v || "Seleção Necessária"],
    headers: [
      { text: "ID", value: "id" },
      { text: "Aluno", align: "start", value: "aluno.nome" },
      { text: "Matricula", align: "start", value: "aluno.matricula" },
      { text: "Disciplina", align: "start", value: "disciplina.nome" },
      { text: "Avaliação", align: "start", value: "atividadeAvaliativa.nome" },
      { text: "Nota", align: "start", value: "nota" },
      { text: "Ações", align: "end", value: "actions", sortable: false },
    ],
    lRegistroDeNotas: [],
    lCurso: [],
    lDisciplina: [],
    lDisciplinaFiltrada: [],
    lAtividadeAvaliativa: [],
    editedIndex: -1,
    editedItem: {},
    defaultItem: {},
  }),
  computed: {
    formTitle() {
      return this.editedIndex === -1 ? textos.novo : textos.edicao;
    },
  },
  watch: {
    dialog(val) {
      val || this.close();
    },
    dialogExcluir(val) {
      val || this.closeExcluir();
    },
  },
  created() {
    this.initialize();
  },
  methods: {
    initialize() {
      this.fetchRecords();
      this.fetchRecordsProfessor();
      this.fetchRecordsCurso();
      this.fetchRecordsDisciplina();
      this.fetchRecordsAtividadeAvaliativa();
    },
    fetchRecords() {
      this.service.search({}).then(this.fetchRecodsSuccess);
    },
    fetchRecordsProfessor() {
      serviceProfessor.search({}).then(this.fetchRecodsSuccessProfessor);
    },
    fetchRecordsCurso() {
      serviceCurso.search({}).then(this.fetchRecodsSuccessCurso);
    },
    fetchRecordsDisciplina() {
      serviceDisciplina.search({}).then(this.fetchRecodsSuccessDisciplina);
    },
    fetchRecordsAtividadeAvaliativa() {
      serviceAtividadeAvaliativa
        .search({})
        .then(this.fetchRecodsSuccessAtividadeAvaliativa);
    },
    fetchRecodsSuccess(response) {
      if (Array.isArray(response.rows)) {
        this.lRegistroDeNotas = response.rows;
        return;
      }
      this.lRegistroDeNotas = [];
    },
    fetchRecodsSuccessProfessor(response) {
      if (Array.isArray(response.rows)) {
        this.lProfessor = response.rows;
        return;
      }
      this.lProfessor = [];
    },
    fetchRecodsSuccessCurso(response) {
      if (Array.isArray(response.rows)) {
        this.lCurso = response.rows;
        return;
      }
      this.lCurso = [];
    },
    fetchRecodsSuccessDisciplina(response) {
      if (Array.isArray(response.rows)) {
        this.lDisciplina = response.rows;
        return;
      }
      this.lDisciplina = [];
    },
    fetchRecodsSuccessAtividadeAvaliativa(response) {
      if (Array.isArray(response.rows)) {
        this.lAtividadeAvaliativa = response.rows;
        return;
      }
      this.lAtividadeAvaliativa = [];
    },

    filtrarDisciplinaPorCurso() {
      this.resetSelecaoDisciplina();
      this.lDisciplinaFiltrada = this.lDisciplina.filter(
        (disciplina) => disciplina.curso.id == this.editedItem.curso.id
      );
    },
    resetSelecaoDisciplina() {
      this.lDisciplinaFiltrada = [];
      this.editedItem.disciplina = null;
    },
    editItem(item) {
      this.editedIndex = this.lRegistroDeNotas.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },
    deleteItem(item) {
      this.editedIndex = this.lRegistroDeNotas.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialogExcluir = true;
    },
    deleteItemComfirm() {
      this.service
        .destroy(this.editedItem)
        .then(this.lRegistroDeNotas.splice(this.editedIndex, 1));
      this.lRegistroDeNotas.splice(this.editedIndex, 1);
      this.closeExcluir();
    },
    closeExcluir() {
      this.dialogExcluir = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },
    close() {
      this.dialog = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },
    save() {
      if (this.editedIndex > -1) {
        this.service
          .update(this.editedItem)
          .then(Object.assign(this.lRegistroDeNotas[this.editedIndex], this.editedItem));
      } else {
        this.service
          .create(this.editedItem)
          .then((response) => this.lRegistroDeNotas.push(response));
      }
      this.close();
    },
  },
};
</script>