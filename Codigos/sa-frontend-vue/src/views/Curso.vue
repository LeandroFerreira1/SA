<template>
  <v-data-table
    :headers="headers"
    :items="lCurso"
    sort-by="id"
    class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar flat>
        <v-toolbar-title>Cadastro de Curso</v-toolbar-title>
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
                        :rules="cursoRulesNome"
                      ></v-text-field>
                    </v-col>              
                  
                    <v-col cols="12" sm="6" md="6">
                      <v-combobox
                        v-model="editedItem.tipo"
                        label="Tipo"
                        outlined
                        required
                        :rules="cursoRulesTipo"
                      ></v-combobox>
                    </v-col>

                    <v-col cols="12" sm="6" md="4">
                      <v-text-field
                        v-model="editedItem.cargaHoraria"
                        label="Carga Horaria"
                        outlined
                        required
                        :rules="cursoRulesCargaHoraria"
                      ></v-text-field>
                    </v-col>

                    <v-col cols="12" sm="6" md="2">
                      <v-text-field
                        v-model="editedItem.duracao"
                        label="Duração"
                        outlined
                        required
                        :rules="cursoRulesDuracao"
                      ></v-text-field>
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
    <template v-slot:no-data>
      <v-btn color="primary" @click="initialize">Resetar</v-btn>
    </template>
  </v-data-table>
</template>

<script>
import CursoService from "../service/domain/CursoService";
import { mask } from "@titou10/v-mask";

const textos = {
  novo: "Novo Curso",
  edicao: "Edição de Curso",
  exclusao: "Deseja mesmo remover este Curso?",
};

export default {
  directives: { mask },
  data: () => ({
    service: CursoService.build(),
    dialog: false,
    dialogExcluir: false,
    valid: true,
    cursoRulesNomeEndereco: [
      (v) => !!v || "Preenchimento Necessário",
      (v) =>
        (v && v.length <= 200 && v.length >= 10) ||
        "O campo deve ter pelo menos 10 e no maximo 200 letras",
    ],
    headers: [
      { text: "ID", value: "id" },
      { text: "Nome", align: "start", value: "nome" },
      { text: "Tipo", value: "tipo" },
      { text: "Carga Horária", value: "cargahoraria" },
      { text: "Duração", value: "duracao" },
      { text: "Ações", align: "end", value: "actions", sortable: false },
    ],
    lCurso: [],
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
        this.lCurso = response.rows;
        return;
      }
      this.lCurso = [];
    },
    editItem(item) {
      this.editedIndex = this.lCurso.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },
    deleteItem(item) {
      this.editedIndex = this.lCurso.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialogExcluir = true;
    },
    deleteItemComfirm() {
      //   this.service
      //     .destroy(this.editedItem)
      //     .then(this.lCurso.splice(this.editedIndex, 1));
      this.lCurso.splice(this.editedIndex, 1);
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
        //     Object.assign(this.lCurso[this.editedIndex], this.editedItem)
        //   );
        Object.assign(this.lCurso[this.editedIndex], this.editedItem);
      } else {
        // this.service
        //   .create(this.editedItem)
        //   .then((response) => this.lCurso.push(response));
        //  this.lCurso.push(response)editedItem
        this.lCurso.push(this.editedItem);
      }
      this.close();
    },
  },
};
</script>