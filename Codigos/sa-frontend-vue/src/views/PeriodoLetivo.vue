<template>
  <v-data-table
    :headers="headers"
    :items="lPeriodoLetivo"
    sort-by="id"
    class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar flat>
        <v-toolbar-title>Cadastro de Perído Letivo</v-toolbar-title>
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
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field
                        v-mask="{ mask: '####/#' }"
                        v-model="editedItem.PeriodoLetivo"
                        label="Perído Letivo"
                        outlined
                        required
                        :counter="5"
                        :rules="periodoletivoRulesPeriodoLetivo"
                      ></v-text-field>
                    </v-col>

                    <v-col cols="12" sm="6" md="4">
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

                    <v-col cols="12" sm="6" md="4">
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

                    <v-col cols="12" sm="6" md="4">
                      <v-text-field
                        v-model="editedItem.numerodiasletivos"
                        label="Nº de Dias Letivos"
                        outlined
                        required
                        :rules="periodoletivoRulesNumeroDiasLetivos"
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
import PeriodoLetivoService from "../service/domain/PeriodoLetivoService";
import { mask } from "@titou10/v-mask";

const textos = {
  novo: "Novo Perído Letivo",
  edicao: "Edição de Perído Letivo",
  exclusao: "Deseja mesmo remover este Perído Letivo?",
};

export default {
  directives: { mask },
  data: () => ({
    service: PeriodoLetivoService.build(),
    dialog: false,
    dialogExcluir: false,
    valid: true,

    headers: [
      { text: "ID", value: "id" },
      { text: "Período Letivo", align: "start", value: "periodoletivo" },
      { text: "Data Inicio", value: "datainicio" },
      { text: "Data Fim", value: "datafim" },
      { text: "Nº de Dias Letivos", value: "numerodiasletivos" },
      { text: "Ações", align: "end", value: "actions", sortable: false },
    ],
    lPeriodoLetivo: [],
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
        this.lPeriodoLetivo = response.rows;
        return;
      }
      this.lPeriodoLetivo = [];
    },
    editItem(item) {
      this.editedIndex = this.lPeriodoLetivo.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },
    deleteItem(item) {
      this.editedIndex = this.lPeriodoLetivo.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialogExcluir = true;
    },
    deleteItemComfirm() {
      //   this.service
      //     .destroy(this.editedItem)
      //     .then(this.lPeriodoLetivo.splice(this.editedIndex, 1));
      this.lPeriodoLetivo.splice(this.editedIndex, 1);
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
        //     Object.assign(this.lPeriodoLetivo[this.editedIndex], this.editedItem)
        //   );
        Object.assign(this.lPeriodoLetivo[this.editedIndex], this.editedItem);
      } else {
        // this.service
        //   .create(this.editedItem)
        //   .then((response) => this.lPeriodoLetivo.push(response));
        //  this.lPeriodoLetivo.push(response)editedItem
        this.lPeriodoLetivo.push(this.editedItem);
      }
      this.close();
    },
  },
};
</script>