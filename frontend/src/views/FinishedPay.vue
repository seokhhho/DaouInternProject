<template>
  <div class="main">
    <h3 style="margin-top:3% ; margin-left:3%">{{this.userName}}님 반갑습니다.
      <v-btn @click="moveToMain" style="float : right ; margin-right:5%">메인 페이지</v-btn>
    </h3>
    <div class="main-title">결제 완료</div>
    <!-- <h2>{{this.totalPrice}} ,asda {{this.balanceCoupons}}</h2> -->
    <div class="payedLinfo">
      <h2 style="text-align:center">결제내역</h2>
 <v-card
    class="mx-auto"
    tile
    style="margin-top:4%;"
  >
    <v-list-item>
      <v-list-item-content>
        <v-list-item-title>결제금액</v-list-item-title>
        <v-list-item-subtitle>
           총금액 : {{this.totalPrice}}원  => 할인쿠폰 적용 금액 : {{this.discountedPrice}}
          </v-list-item-subtitle>
      </v-list-item-content>
    </v-list-item>

    <v-list-item>
        <v-list-item-content>
          <v-list-item-title>사용쿠폰</v-list-item-title>
          <v-list-item-subtitle>
           사용쿠폰 : {{this.couponName}}
          </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

    <v-list-item two-line>
      <v-list-item-content>
        <v-list-item-title>사용포인트</v-list-item-title>
        <div v-for="item in usingPoints"
          :key="item.id">
          <v-list-item-subtitle>{{item.pointName}} : {{item.usingMoney}}점</v-list-item-subtitle>
        </div>
      </v-list-item-content>
    </v-list-item>

    <v-list-item three-line>
      <v-list-item-content>
        <v-list-item-title>사용적립금</v-list-item-title>
        <v-list-item-subtitle>
            {{this.usingFund}}원
          </v-list-item-subtitle>
      </v-list-item-content>
    </v-list-item>

      <v-list-item four-line>
        <v-list-item-content>
          <v-list-item-title>직접 결제 금액</v-list-item-title>
          <v-list-item-subtitle>
            {{this.pgPayMoney}}원
          </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

  </v-card>
        </div>

            <div class="balanceLinfo">
      <h2 style="text-align:center">잔액</h2>
 <v-card
    class="mx-auto"
    tile
    style="margin-top:4%;"
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

export default {
  name: "FinishedPay",
  props:["totalPrice","discountedPrice","couponId","couponName","usingPoints","usingFund","pgPayMoney","userNumber","products","balanceCoupons","balancePoints","balanceFund"],
  data() {
        return {
          userName :'',
        };
    },
  created(){
    this.userName = localStorage.getItem('userName');
  },
  components: {
    
  },
  methods: {
    moveToMain(){
      this.$router.push("/");
    },
  }
};
</script>

<style>
.payedLinfo{
  float: left;
  width:35%;
  margin-left: 9%;
  margin-top:3%;
}
.balanceLinfo{
  float: right;
  width:35%;
  margin-right: 9%;
  margin-top:3%;
}
</style>