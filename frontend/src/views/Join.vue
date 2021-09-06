<template>
  <div class="main">
    <h3 style="margin-top:3% ; margin-left:3%">{{this.userName}}님 반갑습니다.
      <v-btn @click="moveToMain" style="float : right ; margin-right:5%">메인 페이지</v-btn>
    </h3>
    <div class="main-title">회원 등록</div>
    <div class="join-form">
        <v-form
      ref="form"
      v-model="valid"
      lazy-validation
    >
      <v-text-field
        v-model="name"
        label="Name"
        required
      ></v-text-field>
  
      <v-text-field
        v-model="id"
        label="Id"
        required
      ></v-text-field>
        </v-form>
        <div class="create-user">
          <v-btn
          rounded
          color="primary"
          dark
          id="create"
          @click="createUser"
        >
          등록
        </v-btn>
      </div>
</div>
  </div>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from "@/components/HelloWorld.vue";
import axios from "axios";
export default {
  name: "Join",
   data() {
    return {
      id: "",
      name: "",
      form: []
    };
  },
  components: {
    // HelloWorld,
  },
  methods: {
    createUser(){
      this.form = {
          id: this.id,
          name: this.name,
      };
      if(confirm("등록하시겠습니까?") == true){
        axios
          .post(`http://localhost:8088/user/join`, this.form)
          .then((response) => {
            alert("등록완료! 유저번호 : " + response.data);
            this.$router.push("/main");
          })
          .catch(function(error) {
            console.log(error);
          });
      }

    },
    moveToMain(){
      this.$router.push("/");
    },
  }
};
</script>

<style>
.join-form{
    margin-left: 20%;
    margin-right: 20%;
}
.create-user{
  text-align: center;
}
#create{
  width:30%;
  
}
</style>