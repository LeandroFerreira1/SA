<template>
  <div>
    <v-container>
      <v-toolbar flat>
        <v-spacer>
          <v-toolbar-title><center>Listagem de Notas</center></v-toolbar-title>
        </v-spacer>
      </v-toolbar>
    </v-container>

    <v-container>
      <v-toolbar>
        <v-spacer>
          <br />
          <v-row>
            <v-col cols="12" sm="6" md="4">
              <v-combobox
                item-text="disciplina"
                label="Disciplina"
                clearable
                outlined
                required
              >
              </v-combobox>
            </v-col>
          </v-row>
        </v-spacer>
      </v-toolbar>
    </v-container>

    <v-container>
      <v-data-table
        :headers="headers"
        :items="codigos"
        class="elevation-1"
        :hide-default-footer="true"
      >
      </v-data-table>
    </v-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      codigos: [
        {
          codigo: "001",
          alunos: "Fulano",
          prova1: 30,
          prova2: 30,
        },

        {
          codigo: "006",
          alunos: "Ciclano",
          prova1: 60,
          prova2: 30,
        },

        {
          codigo: "002",
          alunos: "Beltrano",
          prova1: 40,
          prova2: 25,
        },
      ],
      headers: [
        {
          text: "Codigo",
          align: "start",
          value: "codigo",
          width: "45%",
        },
        { text: "Alunos", value: "alunos" },
        { text: "Prova 1", value: "prova1" },
        { text: "Prova 2", value: "prova2" },
      ],
    };
  },
};
</script>