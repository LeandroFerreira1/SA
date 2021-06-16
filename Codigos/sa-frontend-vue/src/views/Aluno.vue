<template>
  <v-data-table
    :headers="headers"
    :items="lAluno"
    sort-by="id"
    class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar flat>
        <v-toolbar-title>Cadastro de Alunos</v-toolbar-title>
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
                        :rules="alunoRulesNomeEndereco"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="6">
                      <v-text-field
                        v-mask="{ mask: '###.###.###-##' }"
                        v-model="editedItem.cpf"
                        label="CPF"
                        outlined
                        required
                        :rules="alunoRulesCpf"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="6">
                      <v-text-field
                        v-mask="{ mask: '(##) ####-####' }"
                        v-model="editedItem.telefone"
                        label="Telefone"
                        outlined
                        required
                        :rules="alunoRulesTelefone"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="12">
                      <v-text-field
                        v-model="editedItem.endereco"
                        label="Endereço"
                        outlined
                        required
                        :counter="200"
                        :rules="alunoRulesNomeEndereco"
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
import AlunoService from "../service/domain/AlunoService";
import { mask } from "@titou10/v-mask";


const textos = {
  novo: "Novo Aluno",
  edicao: "Edição de Aluno",
  exclusao: "Deseja mesmo remover este Aluno?",
};

export default {
  directives: { mask },
  data: () => ({
    service: AlunoService.build(),
    dialog: false,
    dialogExcluir: false,
    valid: true,
    alunoRulesNomeEndereco: [
      (v) => !!v || "Preenchimento Necessário",
      (v) =>
        (v && v.length <= 200 && v.length >= 10) ||
        "O campo deve ter pelo menos 10 e no maximo 200 letras",
    ],
    alunoRulesCpf: [
      (v) => !!v || "Preenchimento Necessário",
      (v) =>
        (v && v.length <= 14 && v.length >= 14) ||
        "O campo deve ter 11 digitos",
    ],
    alunoRulesTelefone: [
      (v) => !!v || "Preenchimento Necessário",
      (v) =>
        (v && v.length <= 14 && v.length >= 14) ||
        "O campo deve ter 10 digitos",
    ],
    headers: [
      { text: "ID", value: "id" },
      { text: "Nome", align: "start", value: "nome" },
      { text: "CPF", value: "cpf" },
      { text: "Telefone", value: "telefone" },
      { text: "Endereço", value: "endereco" },
      { text: "Ações", align: "end", value: "actions", sortable: false },
    ],
    lAluno: [],
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
        this.lAluno = response.rows;
        return;
      }
      this.lAluno = [];
    },
    editItem(item) {
      this.editedIndex = this.lAluno.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },
    deleteItem(item) {
      this.editedIndex = this.lAluno.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialogExcluir = true;
    },
    deleteItemComfirm() {
      //   this.service
      //     .destroy(this.editedItem)
      //     .then(this.lAluno.splice(this.editedIndex, 1));
      this.lAluno.splice(this.editedIndex, 1);
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
        //     Object.assign(this.lAluno[this.editedIndex], this.editedItem)
        //   );
        Object.assign(this.lAluno[this.editedIndex], this.editedItem);
      } else {
        // this.service
        //   .create(this.editedItem)
        //   .then((response) => this.lAluno.push(response));
        //  this.lAluno.push(response)editedItem
        this.lAluno.push(this.editedItem);
      }
      this.close();
    },
  },
};
</script>