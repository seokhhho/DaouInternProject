<template>
  <div class="main">
    <!-- <img alt="Vue logo" src="../assets/logo.png" /> -->
    <!-- <HelloWorld/> -->
    <div class="main-title">결제 하기</div>
    <h2>name: {{ this.cart[0].productName}}</h2>
  </div>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from "@/components/HelloWorld.vue";

export default {
  name: "Pay",
  props:["cart"],
  components: {

  },
  created(){
      this.getProduct();
  },
  methods: {
    getProduct(){
        // console.log("하이" + this.cart);
    }
  }
};
</script>

<style>

</style>