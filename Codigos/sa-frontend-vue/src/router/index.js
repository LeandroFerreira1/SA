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
    component: () => import(/* webpackChunkName: "login" */ '../views/Login.vue')
  },
  {
    path: '/administrador',
    name: 'Administrador',
    component: () => import(/* webpackChunkName: "administrador" */ '../views/Administrador.vue')
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
    component: () => import(/* webpackChunkName: "periodoLetivo" */ '../views/PeriodoLetivo.vue')
  },
  {
    path: '/turma',
    name: 'Turma',
    component: () => import(/* webpackChunkName: "turma" */ '../views/Turma.vue')
  },
  {
    path: '/avaliacao',
    name: 'Avaliacao',
    component: () => import(/* webpackChunkName: "avaliacao" */ '../views/Avaliacao.vue')
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
  {
    path: '/registroNotas',
    name: 'RegistroNotas',
    component: () => import(/* webpackChunkName: "registroNotas" */ '../views/RegistroNotas.vue')
  },
]

const router = new VueRouter({
  routes
})

export default router
