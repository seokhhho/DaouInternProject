<template>
  <div class="main">
      <h3 style="margin-top:3% ; margin-left:3%">{{this.userName}}님 반갑습니다.
      <v-btn @click="moveToMain" style="float : right ; margin-right:5%">메인 페이지</v-btn>
    </h3>
    
    <div class="main-title" style="margin-right:24%">결제 내역</div>
    <div style="margin-top:4%;margin-left:0%;margin-right:2%;float:right;width:18%">
    <h2>내 보유금액</h2>
     <v-card
        tile
        style="margin-top:3%"
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


    
    <div class="payment-card">
    <div v-for="(item) in list"
            :key="item"
            class="product-card"
    >
    
     <v-card
      width="300"
      height="230"
    >
      <v-card-title>
       <div v-for="(p) in item.payedProducts"
            :key="p">
          {{p.productName}} : {{p.amount}}개 &nbsp;&nbsp;
        </div> 
      </v-card-title>
  
      <v-card-subtitle>
        <div>{{item.couponName}}</div>
        <div v-for="(u) in item.usedPoints"
            :key="u">
          {{u.pointName}} : {{u.usedMoney}}p &nbsp;&nbsp;
        </div> 
        <div>사용 적립금 : {{item.usedFund}}원 </div> 
        
       <div> PG 결제 금액: {{item.usedMoney}}원</div>
      <v-btn Primary  style="margin-top:5%" @click="refund(item.paymentId)">
        환불
      </v-btn>
      </v-card-subtitle>
    </v-card>
    </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "BuyProduct",
   data() {
        return {
          userName:'',
          userNumber:0,
          list :[],
          balance : [],
          balanceCoupons : [],
          balancePoints : [],
          balanceFund : 0,
        };
    },
    created(){
      this.userName = localStorage.getItem('userName');
      this.userNumber = localStorage.getItem('userNumber');
      this.getBalance();
    },
    mounted(){
      this.getList();
    },
  components: {
  
  },
  methods: {
    getBalance(){
      axios
          .get(`http://localhost:8088/user/balance/${this.userNumber}`)
          .then((response) => {
          this.balanceCoupons = response.data.coupons;
          this.balancePoints = response.data.points;
          this.balanceFund = response.data.fund;
          })
          .catch(function(error) {
              
            console.log(error);
          });
    },
      getList(){
        axios
        .get(`http://localhost:8088/order/list/${this.userNumber}`)
        .then((response) => {
          this.list = response.data;
        })
        .catch(function(error) {
          console.log(error);
        });
     },
     refund(paymentId){
       if(confirm("환불하시겠습니까?")){
        axios
          .get(`http://localhost:8088/order/refund/${paymentId}`)
          .then((response) => {
            this.balance = response.data;
            this.$router.push("/myBalance");
          })
          .catch(function(error) {
            console.log(error);
          });
       }
     },
     moveToMain(){
       this.$router.push("/");
     }

  }
};
</script>

<style>
.payment-card{
  margin-right: 20%;
}
</style>