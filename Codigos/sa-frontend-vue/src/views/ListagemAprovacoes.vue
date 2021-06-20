<template>
  <div>
    <v-container>
      <v-toolbar flat>
        <v-spacer>
          <v-toolbar-title><center>Listagem de Aprovação</center></v-toolbar-title>
        </v-spacer>
      </v-toolbar>
    </v-container>

    <v-container>
      <v-toolbar>
        <v-spacer>
          <br />
          <v-row>
            <v-combobox
              item-text="curso"
              label="Curso"
              clearable
              outlined
              required>
            </v-combobox>

            <v-combobox
              item-text="turma"
              clearable
              outlined
              label="Turma"
              required>
            </v-combobox>
            <div style="position: relative; top: 13%; left:2px;" class="d-flex justify-center mb-6">
                <div>
                    <v-btn block color="primary">Listar</v-btn>
                </div>
            </div>
          </v-row>
        </v-spacer>
      </v-toolbar>
    </v-container>

    <div > 
        <div style="width: 55%; position:relative; float: left; left:70px;">
            <v-container>
                <v-data-table
                    :headers="headers"
                    :items="alunos"
                    class="elevation-1"
                    :hide-default-footer="true">
                </v-data-table>
            </v-container>
        </div>
        <div style="width: 35%; position:relative; float: left; left:62px;">
            <v-container>
                <v-data-table
                    :headers="headers2"
                    :items="dados"
                    class="elevation-1"
                    :hide-default-footer="true">
                </v-data-table>
            </v-container>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      alunos: [
        {
          matricula: "20161si001",
          aluno: "Ana da Silva",
          status: 'Aprovado'
        },

        {
          matricula: "20161si002",
          aluno: "Beatriz de Souza",
          status: 'Reprovado'
        },

        {
          matricula: "20161si003",
          aluno: "Carlos Pereira",
          status: 'Aprovado'
        },
        {
          matricula: "20161si004",
          aluno: "Danilo Silveira",
          status: 'Repr. Falta'
        },
      ],
      headers: [
        {
          text: "Matricula",
          align: "start",
          value: "matricula",
          width: "20%",
        },
        { text: "Alunos", value: "aluno", width: "30%" },
        { text: "Status", value: "status", width: "20%" },
      ],
      dados: [
          {
          status: 'Aprovado',
          qtdAlunos: 2
        },

        {
          status: 'Aprovado Conselho',
          qtdAlunos: 0
        },

        {
          status: 'Cursando',
          qtdAlunos: 0
        },
        {
          status: 'Prova Final',
          qtdAlunos: 0
        },

        {
          status: 'Reprovado',
          qtdAlunos: 1
        },

        {
          status: 'Reprovado Por Falta',
          qtdAlunos: 1
        },
      ],
      headers2: [
        { text: "Status", align: "start", value: "status", width: "30%"},
        { text: "Qtd Alunos", value: "qtdAlunos", width: "10%" },
      ],
    };
  },
};
</script>