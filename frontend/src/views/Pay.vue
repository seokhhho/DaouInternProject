<template>
  <div class="main">
    <h3 style="margin-top:3% ; margin-left:3%">{{this.userName}}님 반갑습니다.
      <v-btn @click="moveToMain" style="float : right ; margin-right:5%">메인 페이지</v-btn>
    </h3>
    
    <div class="main-title" style="margin-bottom:3%">결제 하기</div>
    <div class="switch-btn">
      <!-- {{selectedUsingPoints}} -->
    <v-btn @click="switchAutoPay">자동결제</v-btn>
    <v-btn @click="switchSelect">직접입력</v-btn>
    </div>
    <div v-if="isAutopay" class="auto-pay">
        <v-card
      class="mx-auto"
      max-width="400"
      tile
    >
        <v-list-item>
        <v-list-item-content>
          <v-list-item-title>결제금액</v-list-item-title>
          <v-list-item-subtitle>
           총금액 : {{this.totalPrice}}원  => 할인쿠폰 적용 금액 : {{this.discountedPrice}}원
          </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title>사용쿠폰</v-list-item-title>
          <v-list-item-subtitle>
           {{this.couponName}}
          </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
  
      <v-list-item two-line>
        <v-list-item-content>
          <v-list-item-title>사용포인트</v-list-item-title>
          <div v-for="item in usingPoints"
          :key="item.id">
          <v-list-item-subtitle>{{item.pointName}} : {{item.usingMoney}}p</v-list-item-subtitle>
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
    <div class="pay-btn">
    <v-btn @click="pay">결제하기</v-btn>
    </div>
    </div>
    <div v-else class="select-pay">
      <div class="dicounted-price-text">
      총 결제 금액 : {{totalPrice}}
      </div>
      <div>
      할인 쿠폰 <v-combobox clearable v-model="usingCoupon" 
      :items="canUsingCoupon" item-text="couponName" item-value="discountRate" return-object
      v-on:change="changeDiscountedPrice"
>         </v-combobox>
      </div>

      <div class="dicounted-price-text">
      할인 적용 결제 금액 : {{selectedDiscountedPrice}}
      </div>
      <div>
      <div  v-for="(item,idx) in balancePoints"
          :key="idx">
      {{item.pointName}}(잔여 : {{item.pointMoney}}p) <v-text-field
            type="number"
            v-model="selectedPoints[idx]"
            v-on:change="getTotal"
            />
      </div>
      </div>
<!-- :rules="pointsRule[idx].rule" -->
      적립금 (잔여 : {{balanceFund}}원)<v-text-field 
      type="number"
      v-model="selectedFund"
      v-on:change="getTotal"
      :rules="fundRule"/>

      PG 결제 <v-text-field 
      v-model="selectedPG"
      v-on:change="getTotal"
      type="number"/>
      <div style="margin-top:3%;">총액 : {{this.selectedTotal}}원
        <v-btn @click="selectedPay" class="select-pay-btn" style="margin-top:-1%">결제하기</v-btn>
        <!-- <div v-if="!canSelectedPay" class="pay-match-message">결제 금액을 맞춰 주세요.</div>
        <div v-if="!isValidFund" class="pay-match-message">적립금을 확인 해 주세요.</div> -->
      </div>


    </div>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Pay",
  props:["cart"],
  components: {

  },
  data() {
        return {
          form: [],
          totalPrice : 0,
          discountedPrice : 0,
          couponId : 0,
          couponName : '',
          discountRate : 0,
          usingPoints :[],
          usingFund : 0,
          pgPayMoney : 0,
          userName:'',
          userNumber:0,
          products:[],
          p:{},
          payForm:[],
          isAutopay : true,
          balanceCoupons:[],
          balancePoints:[],
          balanceFund:0,
          fundRule: [v => (!isNaN(parseFloat(v)) && v >= 0 && v <= this.balanceFund) || '적립금은 ' + this.balanceFund + '이하이어야 합니다.'],
          pointsRule : [],
          usingCoupon:[],
          canUsingCoupon:[],
          selectedDiscountedPrice : 0,
          hasSelectedCoupon : false,
          canSelectedPay : true,
          selectedPoints : [],
          selectedUsingPoints : [],
          selectedFund : 0,
          selectedPG : 0,
          selectedTotal : 0,
          isValidFund : true,
          isValidPoint : true,
        };
    },
  created(){
      this.userName = localStorage.getItem('userName');
      this.userNumber = localStorage.getItem('userNumber');
      for(var i  in this.cart){
         this.p = {"productId":this.cart[i].productId,"amount":this.cart[i].amount};
         this.products.push(this.p);
      }
    //   console.log(this.products);
     this.getAutoPayInfo();    
     //this.getBalance(); 
  },
  methods: {
    getAutoPayInfo(){
        this.form = {
          userNumber : this.userNumber,
          products : this.products
      };
        axios
        .put(`http://localhost:8088/order/autoPayInfo`,this.form)
        .then((response) => {
          this.totalPrice = response.data.totalPrice;
          this.discountedPrice = response.data.discountedPrice;
          this.couponId = response.data.couponId;
          this.couponName = response.data.couponName;
          this.discountRate = response.data.discountRate;
          this.usingPoints = response.data.usingPoints;
          this.usingFund = response.data.usingFund;
          this.pgPayMoney = response.data.pgPayMoney;
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    pay(){
      if(confirm("결제하시겠습니까?") == true){
        this.payForm = {
          totalPrice : this.totalPrice,
          discountedPrice : this.discountedPrice,
          couponId : this.couponId,
          usingPoints :this.usingPoints,
          usingFund : this.usingFund,
          pgPayMoney : this.pgPayMoney,
          userNumber : this.userNumber,
          products : this.products
        }
        axios
          .put(`http://localhost:8088/order/autoPay`,this.payForm)
          .then((response) => {
            this.$router.push({name:'FinishedPay' ,params : {totalPrice : this.totalPrice,
          discountedPrice : this.discountedPrice,
          couponId : this.couponId,
          couponName : this.couponName,
          usingPoints :this.usingPoints,
          usingFund : this.usingFund,
          pgPayMoney : this.pgPayMoney,
          userNumber : this.userNumber,
          products : this.products,
          balanceCoupons : response.data.coupons,
          balancePoints : response.data.points,
          balanceFund : response.data.fund,
          }});
          })
          .catch(function(error) {
              alert("에러");
            console.log(error);
          });
      }
    },

    switchAutoPay(){
      this.isAutopay = true;
    },
    switchSelect(){
      this.isAutopay = false;
      this.selectedDiscountedPrice = this.totalPrice;
       this.getBalance();
    },
    getBalance(){
       axios
          .get(`http://localhost:8088/user/balance/${this.userNumber}`)
          .then((response) => {
            this.balanceCoupons = response.data.coupons;
            for(var i in this.balanceCoupons){
              console.log("최소 가격 : " + this.balanceCoupons[i].minPrice);
              if(this.balanceCoupons[i].minPrice <= this.totalPrice)
                this.canUsingCoupon.push(this.balanceCoupons[i]);
            }
            this.balancePoints = response.data.points;

            this.balanceFund = response.data.fund;
            for(var idx in this.balancePoints){
              //alert(this.balancePoints[idx].pointMoney+"sdfds" + idx);
                this.pointsRule[idx] = 
                {
                  rule : [v => (!isNaN(parseFloat(v)) && v >= 0 && v <= this.balancePoints[idx].pointMoney) || '해당 포인트는 ' + this.balancePoints[idx].pointMoney + '이하이어야 합니다.'],
                  index : idx
                };
              
                //alert(this.pointsRule[idx].index);
            }
          })
          .catch(function(error) {
            alert("에러");
            console.log(error);
          });
    },
    changeDiscountedPrice(){
      console.log(this.usingCoupon+"sdfsdf");
      if(this.usingCoupon != null)
        this.selectedDiscountedPrice = this.totalPrice - (this.totalPrice / 100 * this.usingCoupon.discountRate);
      else
        this.selectedDiscountedPrice = this.totalPrice;
    },
    getTotal(){
      this.selectedTotal = 0;
      for(var index in this.selectedPoints){
        this.selectedTotal += parseInt(this.selectedPoints[index]);
      }
      this.selectedTotal += (parseInt(this.selectedFund) + parseInt(this.selectedPG));
      // if(this.selectedTotal == this.selectedDiscountedPrice) {
      //   this.canSelectedPay == true;
      // }else{
      //   this.canSelectedPay == false;
      // }

      
    },
    selectedPay(){
      this.isValidPoint = true;
      this.isValidFund = true;
      for(var idx in this.balancePoints){
        if(this.balancePoints[idx].pointMoney < this.selectedPoints[idx]){
          this.isValidPoint = false;
          alert("포인트를 확인 해 주세요.");
        }
      }
      if(this.isValidPoint){
        if(this.selectedFund > this.balanceFund){
          alert("적립금을 확인 해 주세요.");
          this.isValidFund = false;
        }else{
          if(this.selectedTotal == this.selectedDiscountedPrice){
            if(confirm("결제하시겠습니까?")){
              // this.selectedUsingPoints = this.usingPoints;
              for(var sIdx in this.balancePoints){
                this.selectedUsingPoints[sIdx] = {
                  usingMoney :this.selectedPoints[sIdx],
                  pointId : this.balancePoints[sIdx].pointId,
                  valid : this.balancePoints[sIdx].valid,
                  pointName : this.balancePoints[sIdx].pointName
                }
              }
              // alert(this.selectedUsingPoints[0].usingMoney);
              // alert(this.selectedUsingPoints[1].usingMoney);
              this.payForm = {
              totalPrice : this.totalPrice,
              discountedPrice : this.selectedDiscountedPrice,
              couponId : this.usingCoupon.couponId,
              usingPoints :this.selectedUsingPoints,
              usingFund : this.selectedFund,
              pgPayMoney : this.selectedPG,
              userNumber : this.userNumber,
              products : this.products
            }
            axios
              .put(`http://localhost:8088/order/autoPay`,this.payForm)
              .then((response) => {
                this.$router.push({name:'FinishedPay' ,params : {totalPrice : this.totalPrice,
              discountedPrice : this.selectedDiscountedPrice,
              couponId : this.usingCoupon.couponId,
              couponName : this.usingCoupon.couponName,
              usingPoints :this.selectedUsingPoints,
              usingFund : this.selectedFund,
              pgPayMoney : this.selectedPG,
              userNumber : this.userNumber,
              products : this.products,
              balanceCoupons : response.data.coupons,
              balancePoints : response.data.points,
              balanceFund : response.data.fund,
              }});
              })
              .catch(function(error) {
                  alert("에러");
                console.log(error);
              });
            }
          }else{
            alert("총액과 할인적용금액을 맞춰 주세요.");
            this.canSelectedPay = false;
          }
        }
      }
    },
    moveToMain(){
      this.$router.push("/");
    }
  }
};
</script>

<style>
.pay-btn{
    text-align: center;
    margin-top: 4%;
}
.switch-btn{
  float: right;
  margin-right: 25%;
}
.auto-pay{
  margin-top: 9%;
  margin-bottom: 10%;
}
.pay-match-message{
color: rgb(241, 0, 48);
}
.select-pay{
  font-weight: bold;
  margin-top: 10%;
  margin-left: 25%;
  margin-right: 25%;
  margin-bottom: 10%;
}
.dicounted-price-text{
  margin-top: 2%;
  margin-bottom: 3%;
  font-size: 1.5rem;
}
.select-pay-btn{
  float: right;
}
</style>