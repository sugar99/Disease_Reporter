import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Patient from "@/views/Patient";
import Disease from "@/views/Disease";
import Case from "@/views/Case";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home,
        children: [
            {
                path: '/patient',
                name: Patient,
                component: Patient
            },
            {
                path: '/disease',
                name: Disease,
                component: Disease
            },
            {
                path: '/case',
                name: Case,
                component: Case
            }
        ]
    }
]

const router = new VueRouter({
    routes
})

export default router
