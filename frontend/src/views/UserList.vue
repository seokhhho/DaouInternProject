<template>
  <div class="main">
    
      <v-btn @click="moveToMain" style="float : right ; margin-right:5%">메인 페이지</v-btn>
    
    <div class="main-title" style="margin-left:9%">회원 리스트</div>
    <div class ="user-table">
    <v-simple-table>
        <template v-slot:default>
        <thead>
            <tr>
            <!-- <th class="text-left">no</th> -->
            <th>아이디</th>
            <th>이름</th>
            <th></th>
            </tr>
        </thead>
        <tbody>
            <tr
            v-for="item in list"
            :key="item.id"
            @click="read(item.id)"
            >
            <td>{{ item.id }}</td>
            <td>{{ item.name }}</td>
            <td>
                <v-btn
          rounded
          color="primary"
          dark
          style="margin-left:10%;"
          @click="moveToBuyProduct(item)"
        >
          상품구매
        </v-btn>
        <v-btn
          rounded
          color="primary"
          dark
          style="margin-left:1%;"
          @click="moveToPayList(item)"
        >
          결제내역
        </v-btn>
        </td>
            </tr>
        </tbody>
        </template>
    </v-simple-table>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Join",
   data() {
        return {
        list: [],
        };
    },
    created(){
        this.getList();
    },
  components: {
    
  },
  methods: {
      getList(){
          axios
          .get(`http://localhost:8088/user/list`)
          .then((response) => {
            this.list = response.data;
            console.log("리스트" + this.list[0].id);
          })
          .catch(function(error) {
            console.log(error);
          });
      },
      moveToBuyProduct(item){
        localStorage.setItem('userNumber', item.userNumber);
        localStorage.setItem('userName', item.name);
        this.$router.push("/buyProduct");
      },
      moveToMain(){
      this.$router.push("/");
    },
    moveToPayList(item){
      localStorage.setItem('userNumber', item.userNumber);
        localStorage.setItem('userName', item.name);
      this.$router.push("/payList");
    }
  }
};
</script>

<style>
.user-table{
    /* text-align: center; */
    margin-left: 30%;
    margin-right: 30%;
    margin-top: 5%;
}
</style>