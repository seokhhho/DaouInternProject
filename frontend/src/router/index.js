import Vue from "vue";
import VueRouter from "vue-router";
import Main from "../views/Main.vue";
import Join from "../views/Join.vue";
import UserList from "../views/UserList.vue";
import BuyProduct from "../views/BuyProduct.vue";
import Pay from "../views/Pay.vue";
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Main",
    component: Main,
  },
  {
    path: "/join",
    name: "Join",
    component: Join,
  },
  {
    path: "/userList",
    name: "UserList",
    component: UserList,
  },
  {
    path: "/buyProduct",
    name: "BuyProduct",
    component: BuyProduct,
  },
  {
    path: "/pay",
    name: "Pay",
    component: Pay,
    props: true,
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
