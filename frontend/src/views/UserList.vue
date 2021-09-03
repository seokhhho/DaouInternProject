<template>
  <div class="main">
    <!-- <img alt="Vue logo" src="../assets/logo.png" /> -->
    <!-- <HelloWorld/> -->
    <div class="main-title">회원 리스트</div>
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
          @click="moveToBuyProduct"
        >
          상품구매
        </v-btn>
        <v-btn
          rounded
          color="primary"
          dark
          style="margin-left:1%;"
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
      moveToBuyProduct(){
        this.$router.push("/buyProduct");
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