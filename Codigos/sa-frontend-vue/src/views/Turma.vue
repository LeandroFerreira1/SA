<template>
  <v-data-table
    :headers="headers"
    :items="lTurma"
    sort-by="id"
    class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar flat>
        <v-toolbar-title>Cadastro de Turma</v-toolbar-title>
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
                        :rules="TurmaRulesNome"
                      ></v-text-field>
                    </v-col>

                    <v-col cols="12" sm="4" md="6">
                      <v-combobox
                        v-model="editedItem.professor"
                        label="Professor"
                        outlined
                        required
                        :rules="TurmaRulesProfessor"
                      ></v-combobox>
                    </v-col>
                    <v-col cols="12" sm="4" md="6">
                      <v-combobox
                        v-model="editedItem.curso"
                        label="Curso"
                        outlined
                        required
                        :rules="TurmaRulesCurso"
                      ></v-combobox>
                    </v-col>

                    <v-col cols="12" sm="4" md="6">
                      <v-combobox
                        v-model="editedItem.disciplina"
                        label="Disciplina"
                        outlined
                        required
                        :rules="TurmaRulesDisciplina"
                      ></v-combobox>
                    </v-col>

                    <v-col cols="12" sm="4" md="3">
                      <v-combobox
                        v-model="editedItem.periodoletivo"
                        label="Período Letivo"
                        outlined
                        required
                        :rules="TurmaRulesPeriodoLetivo"
                      ></v-combobox>
                    </v-col>
                    <v-col cols="12" sm="6" md="3">
                      <v-text-field
                        v-model="editedItem.vagas"
                        label="Vagas"
                        outlined
                        required
                        :rules="turmaRulesVagas"
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
                            :rules="modeloRulesDataInicio"
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
                            :rules="modeloRulesDataFim"
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
    <template v-slot:item.actions="{ item }">
      <v-icon small class="mr-2" @click="editItem(item)">mdi-pencil</v-icon>
      <v-icon small @click="deleteItem(item)">mdi-delete</v-icon>
    </template>
        <template v-slot:body.append>
      <tr>
        <td></td>
        <td>
          <v-combobox
            v-model="EscolherCurso"
            item-text="curso"
            :items="curso"
            label="Curso"
            clearable
          ></v-combobox>
        </td>
        <td>
          <v-combobox
            v-model="EscolherPLetivo"
            item-text="periodo"
            :items="periodos"
            label="P. Letivo"
            clearable
          ></v-combobox>
        </td>
        <td>
          <v-combobox
            v-model="EscolherProfessor"
            item-text="professor"
            :items="turmas"
            label="Professor"
            clearable
          ></v-combobox>
        </td>
        <td></td>
      </tr>
    </template>
    <template v-slot:no-data>
      <v-btn color="primary" @click="initialize">Alterar</v-btn>
    </template>
  </v-data-table>
</template>

<script>
import TurmaService from "../service/domain/TurmaService";
import { mask } from "@titou10/v-mask";

const textos = {
  novo: "Nova Turma",
  edicao: "Edição de Turma",
  exclusao: "Deseja mesmo remover esta Turma?",
};

export default {
  directives: { mask },
  data: () => ({
    service: TurmaService.build(),
    dialog: false,
    dialogExcluir: false,
    valid: true,

    headers: [
      { text: "ID", value: "id" },
      { text: "Nome", align: "start", value: "nome" },
      { text: "Professor", align: "start", value: "professor" },
      { text: "Disciplina", align: "start", value: "disciplina" },
      { text: "Período Letivo", align: "start", value: "periodoletivo" },
      { text: "Vagas", align: "start", value: "vagas" },
      { text: "Ações", align: "end", value: "actions", sortable: false },
    ],
    lTurma: [],
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
    // this.fetchRecords();
  },
  methods: {
    fetchRecords() {
      //this.service.search({}).then(this.fetchRecodsSuccess);
    },
    fetchRecodsSuccess(response) {
      if (Array.isArray(response.rows)) {
        this.lTurma = response.rows;
        return;
      }
      this.lTurma = [];
    },
    editItem(item) {
      this.editedIndex = this.lTurma.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },
    deleteItem(item) {
      this.editedIndex = this.lTurma.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialogExcluir = true;
    },
    deleteItemComfirm() {
      //   this.service
      //     .destroy(this.editedItem)
      //     .then(this.lTurma.splice(this.editedIndex, 1));
      this.lTurma.splice(this.editedIndex, 1);
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
        // this.service
        //   .update(this.editedItem)
        //   .then(
        //     Object.assign(this.lTurma[this.editedIndex], this.editedItem)
        //   );
        Object.assign(this.lTurma[this.editedIndex], this.editedItem);
      } else {
        // this.service
        //   .create(this.editedItem)
        //   .then((response) => this.lTurma.push(response));
        //  this.lTurma.push(response)editedItem
        this.lTurma.push(this.editedItem);
      }
      this.close();
    },
  },
};
</script>