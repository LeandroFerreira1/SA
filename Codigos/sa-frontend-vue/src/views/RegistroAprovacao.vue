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

          <v-combobox
              item-text="curso"
              label="Curso"
              clearable
              outlined
              required>
          </v-combobox>

          <v-combobox
              item-text="turma"
              label="Turma"
              clearable
              outlined
              required >
          </v-combobox>
            
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
        item-key="aluno"
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
export default {
  data() {
    return {
      singleSelect: false,
      cSel: 'Cursando',
      selected: [],
      alunos: [
        {
          aluno: "Ana da Silva",
          matricula: "20181SI",
          faltas: 0,
          nota: 84,
          provaFinal: '-',
          notaFinal: 84,
          status: ['Cursando', 'Aprovado'],
        },
        {
          aluno: "Beatriz de Souza",
          matricula: "2020SI60",
          faltas: 2,
          nota: 90,
          provaFinal: '-',
          notaFinal: 90,
          status: ['Cursando', 'Aprovado'],
        },
        {
          aluno: "Carlos Pereira",
          matricula: "2019SI30",
          faltas: 8,
          nota: 88,
          provaFinal: '-',
          notaFinal: 88,
          status: ['Cursando', 'Aprovado'],
        },
        {
          aluno: "Danilo Silveira",
          matricula: "2017SI30",
          faltas: 4,
          nota: 86,
          provaFinal: '-',
          notaFinal: 86,
          status: ['Cursando', 'Aprovado'],
        },
      ],
      headers: [
        { text: "Matrícula", align: "start", value: "matricula", width: "10%" },
        {
          text: "Aluno",
          
          value: "aluno",
          width: "30%"
        },
        { text: "Faltas", align: "center", value: "faltas", width: "10%" },
        { text: "Nota", align: "center", value: "nota", width: "10%" },
        { text: "Prova Final", align: "center", value: "provaFinal", width: "10%" },
        { text: "Nota Final", align: "center", value: "notaFinal", width: "10%" },
        { text: "Status", align: "center", value: "status", width: "20%" },
      ],
    };
  },
};
</script>