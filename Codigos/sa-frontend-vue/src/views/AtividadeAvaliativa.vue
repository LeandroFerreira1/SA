<template>
  <v-data-table
    :headers="headers"
    :items="lAtividadeAvaliativa"
    sort-by="id"
    class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar flat>
        <v-toolbar-title>Cadastro de Atividade Avaliativa (Avaliação)</v-toolbar-title>
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
                    <v-col cols="12" sm="4" md="6">
                      <v-combobox
                        v-model="editedItem.atividadeAvaliativa"
                        label="AtividadeAvaliativa"
                        outlined
                        required
                        :rules="AvaliacaoRulesAtividadeAvaliativa"
                      ></v-combobox>
                    </v-col>

                    <v-col cols="12" sm="6" md="6">
                      <v-text-field
                        v-model="editedItem.avaliacao"
                        label="Avaliação"
                        outlined
                        required
                        :rules="avaliacaoRulesAvaliacao"
                      ></v-text-field>
                    </v-col>

                    <v-col cols="12" sm="6" md="3">
                      <v-text-field
                        v-model="editedItem.tipo"
                        label="Tipo"
                        outlined
                        required
                        :rules="avaliacaoRulesTipo"
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
                            v-model="editedItem.dataavaliacao"
                            label="Data da Avaliação"
                            readonly
                            v-bind="attrs"
                            v-on="on"
                            outlined
                            required
                            :rules="modeloRulesDataAvaliacao"
                          ></v-text-field>
                        </template>
                        <v-date-picker
                          v-model="editedItem.dataavaliacao"
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
                            @click="$refs.menuEntrada.save(dataavaliacao)"
                            >OK</v-btn
                          >
                        </v-date-picker>
                      </v-menu>
                    </v-col>
                    <v-col cols="12" sm="6" md="3">
                      <v-text-field
                        v-model="editedItem.valor"
                        label="Valor"
                        outlined
                        required
                        :rules="avaliacaoRulesValor"
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
import AtividadeAvaliativaService from "../service/domain/AtividadeAvaliativaService";
const serviceAtividadeAvaliativa = AtividadeAvaliativaService.build();
import CursoService from "../service/domain/CursoService";
const serviceCurso = CursoService.build();
const textos = {
  novo: "Novo AtividadeAvaliativa",
  edicao: "Edição de AtividadeAvaliativa",
  exclusao: "Deseja mesmo remover este AtividadeAvaliativa?",
};
export default {
  name: "lAtividadeAvaliativa",
  components: {},
  data: () => ({
    dialog: false,
    dialogExcluir: false,
    valid: true,
    atividadeAvaliativaRulesCurso: [(v) => !!v || "Seleção Necessária"],
    atividadeAvaliativaRulesNome: [
      (v) => !!v || "Preenchimento Necessário",
      (v) =>
        (v && v.length <= 20 && v.length >= 3) ||
        "O campo deve ter pelo menos 3 e no maximo 20 letras",
    ],
    headers: [
      { text: "ID", value: "id" },
      { text: "Nome", align: "start", value: "nome" },
      { text: "Tipo", align: "start", value: "tipo" },
      { text: "Valor", value: "valor" },
      { text: "Disciplina", value: "disciplina.nome" },
      { text: "Ações", align: "end", value: "actions", sortable: false },
    ],
    lAtividadeAvaliativa: [],
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
    this.fetchRecords();
    this.fetchRecordsCurso();
  },
  methods: {
    fetchRecords() {
      serviceAtividadeAvaliativa.search({}).then(this.fetchRecodsSuccess);
    },
    fetchRecordsCurso() {
      serviceCurso.search({}).then(this.fetchRecodsSuccessCurso);
    },
    fetchRecodsSuccess(response) {
      if (Array.isArray(response.rows)) {
        this.lAtividadeAvaliativa = response.rows;
        return;
      }
      this.lAtividadeAvaliativa = [];
    },
    fetchRecodsSuccessCurso(response) {
      if (Array.isArray(response.rows)) {
        this.lCurso = response.rows;
        return;
      }
      this.lCurso = [];
    },

    editItem(item) {
      this.editedIndex = this.lAtividadeAvaliativa.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },
    deleteItem(item) {
      this.editedIndex = this.lAtividadeAvaliativa.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialogExcluir = true;
    },
    deleteItemComfirm() {
      //const index = this.lAtividadeAvaliativa.indexOf(this.editedItem);
      serviceAtividadeAvaliativa
        .destroy(this.editedItem)
        .then(this.lAtividadeAvaliativa.splice(this.editedIndex, 1));
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
        console.log(this.editedItem);
        serviceAtividadeAvaliativa
          .update(this.editedItem)
          .then(
            Object.assign(this.lAtividadeAvaliativa[this.editedIndex], this.editedItem)
          );
      } else {
        serviceAtividadeAvaliativa
          .create(this.editedItem)
          .then((response) => this.lAtividadeAvaliativa.push(response));
      }
      this.close();
    },
  },
};
</script>