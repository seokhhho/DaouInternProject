<template>
  <div class="main">
    <h3 style="margin-top:3% ; margin-left:3%">{{this.userName}}님 반갑습니다.
      <v-btn @click="moveToMain" style="float : right ; margin-right:5%">메인 페이지</v-btn>
    </h3>
    <div class="main-title">내 보유금액</div>

            <div style="margin-left:30%, margin-right:30%">
      <!-- <h2 style="text-align:center">잔액</h2> -->
    <v-card
        tile
        style="margin-top:4%;margin-left:30%;margin-right:30%"
    >
        <v-list-item>
        <v-list-item-content>
            <v-list-item-title>적립금</v-list-item-title>
            <v-list-item-subtitle>
            {{this.balanceFund}}원
            </v-list-item-subtitle>
        </v-list-item-content>
        </v-list-item>

        <v-list-item>
            <v-list-item-content>
            <v-list-item-title>할인쿠폰</v-list-item-title>
            <div v-for="item in balanceCoupons"
            :key="item.couponId">
            <v-list-item-subtitle>[{{item.couponName}} ] : {{item.discountRate}}%</v-list-item-subtitle>
            </div>
            </v-list-item-content>
        </v-list-item>

        <v-list-item two-line>
        <v-list-item-content>
            <v-list-item-title>포인트</v-list-item-title>
            <div v-for="item in balancePoints"
            :key="item.pointId">
            <v-list-item-subtitle>{{item.pointName}} : {{item.pointMoney}}p</v-list-item-subtitle>
            </div>
        </v-list-item-content>
        </v-list-item>
  </v-card>
  
        </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "MyBalance",
  data() {
        return {
          userName :'',
          userNumber : 0,
          balanceCoupons : [],
          balancePoints : [],
          balanceFund : 0,
        };
    },
  created(){
    this.userName = localStorage.getItem('userName');
    this.userNumber = localStorage.getItem('userNumber');
    this.getList();
  },
  components: {
    
  },
  methods: {
    moveToMain(){
      this.$router.push("/");
    },
    getList(){
      axios
          .get(`http://localhost:8088/user/balance/${this.userNumber}`)
          .then((response) => {
          this.balanceCoupons = response.data.coupons;
          this.balancePoints = response.data.points;
          this.balanceFund = response.data.fund;
          })
          .catch(function(error) {
              alert("에러");
            console.log(error);
          });
    }
  }
};
</script>

<style>

</style>