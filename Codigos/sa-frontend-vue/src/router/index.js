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
    path: '/aluno',
    name: 'Aluno',
    component: () => import(/* webpackChunkName: "aluno" */ '../views/Aluno.vue')
  },
  {
    path: '/professor',
    name: 'Professor',
    component: () => import(/* webpackChunkName: "professor" */ '../views/Professor.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
