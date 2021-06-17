import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import(/* webpackChunkName: "aluno" */ '../views/Login.vue')
  },
  {
    path: '/administrador',
    name: 'Administrador',
    component: () => import(/* webpackChunkName: "aluno" */ '../views/Administrador.vue')
  },
  {
    path: '/aluno',
    name: 'Aluno',
    component: () => import(/* webpackChunkName: "aluno" */ '../views/Aluno.vue')
  },
  {
    path: '/professor',
    name: 'Professor',
    component: () => import(/* webpackChunkName: "professor" */ '../views/Professor.vue')
  },
  {
    path: '/curso',
    name: 'Curso',
    component: () => import(/* webpackChunkName: "curso" */ '../views/Curso.vue')
  },
  {
    path: '/periodoLetivo',
    name: 'PeriodoLetivo',
    component: () => import(/* webpackChunkName: "curso" */ '../views/PeriodoLetivo.vue')
  },
  {
    path: '/turma',
    name: 'Turma',
    component: () => import(/* webpackChunkName: "curso" */ '../views/Turma.vue')
  },
  {
    path: '/avaliacao',
    name: 'Avaliacao',
    component: () => import(/* webpackChunkName: "curso" */ '../views/Avaliacao.vue')
  },
  {
    path: '/disciplina',
    name: 'Disciplina',
    component: () => import(/* webpackChunkName: "disciplina" */ '../views/Disciplina.vue')
  },
  {
    path: '/cidade',
    name: 'Cidade',
    component: () => import(/* webpackChunkName: "cidade" */ '../views/Cidade.vue')
  },
  {
    path: '/uf',
    name: 'UF',
    component: () => import(/* webpackChunkName: "uf" */ '../views/Uf.vue')
  },
  {
    path: '/bairro',
    name: 'Bairro',
    component: () => import(/* webpackChunkName: "bairro" */ '../views/Bairro.vue')
  },
]

const router = new VueRouter({
  routes
})

export default router
