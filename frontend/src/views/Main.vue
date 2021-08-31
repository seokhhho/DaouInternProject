<template>
  <div>
    <!-- <img alt="Vue logo" src="../assets/logo.png" /> -->
    <!-- <HelloWorld/> -->
    <v-btn
      rounded
      color="primary"
      dark
      class="mainBtn"
      id="userApply"
    >
      회원등록
    </v-btn>
  </div>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from "@/components/HelloWorld.vue";

export default {
  name: "Main",
  components: {
    // HelloWorld,
  },
};
</script>

<style>
.mainBtn{
  height:40%;
}
#userApply {
  margin-top:20%;
  margin-right:20%;
  float : right;
  width : 30%
  }
</style>
