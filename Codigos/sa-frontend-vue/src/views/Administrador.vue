<template>
  <v-container class="fill-height" fluid>
    <v-row align="center" justify="center">
      <v-col cols="12" sm="8" md="4">
        <v-card class="elevation-12">
          <v-toolbar>
            <v-spacer>
              <v-toolbar-title
                ><center>Cadastro de Administrador</center></v-toolbar-title
              >
            </v-spacer>
          </v-toolbar>
          <v-card-text>
            <v-form>
              <v-text-field
                label="Nome"
                name="nome"
                prepend-icon="mdi-account"
                type="text"
                outlined
              ></v-text-field>
              <v-text-field
                id="password"
                label="Password"
                name="password"
                prepend-icon="mdi-lock"
                type="password"
                outlined
              ></v-text-field>
              <v-text-field
                label="Email"
                name="email"
                prepend-icon="mdi-email"
                type="text"
                outlined
              ></v-text-field>
            </v-form>
          </v-card-text>
          <v-card-actions>
            <v-col cols="12" sm="12" md="6">
              <v-btn block color="primary">Cadastrar</v-btn>
            </v-col>
            <v-col cols="12" sm="12" md="6">
              <v-btn block color="primary">Cancelar</v-btn>
            </v-col>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>