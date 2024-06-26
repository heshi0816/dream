import {createRouter, createWebHistory} from 'vue-router'
import Home from "../view/home.vue"
import Login from "../view/login.vue";

const routes = [{
    path: "/login",
    component: Login
}, {
    path: "/home",
    component: Home
}]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
