import {createRouter, createWebHistory} from 'vue-router'
import Home from "../view/home.vue"
import Login from "../view/login.vue";
import Register from "../view/register.vue";
import Reset from "../view/reset.vue";
import Help from "../view/home/help.vue";
import Welcome from "../view/home/welcome.vue";

const routes = [{
    path: "/",
    redirect: "/login"
}, {
    path: "/register",
    component: Register
}, {
    path: "/login",
    component: Login
}, {
    path: "/home",
    component: Home,
    children : [{
        path : "welcome",
        compoent: Welcome,
    }, {
        path:"help",
        component: Help,
    }]
}, {
    path: "/reset",
    component: Reset
}]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
