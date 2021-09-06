<template>
  <div class="main">
    
    <div class="main-title">다우 쇼핑</div>
    <div class="main-button">
      <v-btn
        rounded
        color="primary"
        dark
        id="userApply"
        @click="join"
      >
        회원등록
      </v-btn>
      <v-btn
        rounded
        color="primary"
        dark
        id="userList"
        @click="userList"
      >
        회원목록
      </v-btn>
      <v-btn
        rounded
        color="primary"
        dark
        id="myMoney"
      >
        내 보유금액
      </v-btn>
      <v-btn
        rounded
        color="primary"
        dark
        id="buyProduct"
        @click="buyProduct"
      >
        상품 구매
      </v-btn>
    </div>
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
  methods: {
    join(){
      this.$router.push("/join");
    },
    userList(){
      this.$router.push("/userList");
    },
    buyProduct(){
      this.$router.push("/buyProduct");
    }
  }
};
</script>

<style>
.main-title{
  text-align: center;
  margin-top: 5%;
  font-size: 3rem;

}
.main{
  height:100%;
}
.main-button{
  margin-top:5%;
  margin-bottom:20%;
  margin-left:10%;
  margin-right:10%;
  height:30%;
}
#userApply {
  /* margin-top:20%;
  margin-right:20%; */
  float : left;
  width : 48%;
  height:50%;
  
  }
#userList {
  /* margin-top:20%;
  margin-right:20%; */
  float : right;
  width : 48%;
  height:50%;
  }
  #myMoney {
  /* margin-top:20%;
  margin-right:20%; */
  float : left;
  width : 48%;
  height:50%;
  margin-top:1%;
  }
   #buyProduct {
  /* margin-top:20%;
  margin-right:20%; */
  float : right;
  width : 48%;
  margin-top:1%;
  height: 48%;
  }
</style>
