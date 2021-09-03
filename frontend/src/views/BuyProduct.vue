<template>
  <div class="main">
    <div class="main-title">상품 구매</div>
    <div class = "cards-dummy">
    <div v-for="(item,index) in list"
            :key="index"
            class="product-card"
    >
    
     <v-card
      class="mx-auto"
      max-width="344"
    >
      <v-img
        src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
        height="200px"
      ></v-img>
  
      <v-card-title>
       {{item.productName}}
      </v-card-title>
  
      <v-card-subtitle>
        가격 : {{item.price}}  재고 : {{item.stock}} &nbsp; 
      <v-btn Primary class="cart-plus-btn" @click="plusCart(item,index)">
        추가
      </v-btn>
         <!-- <v-btn
        class="mx-2"
        fab
        dark
        x-small
        color="primary"
        @click="plusAmount(index)"
      >
        <v-icon dark>
          mdi-plus
        </v-icon>
      </v-btn> -->
      <!-- <v-btn
        class="mx-2"
        fab
        dark
        x-small
        color="primary"
      >
        <v-icon dark>
          mdi-minus
        </v-icon>
      </v-btn> -->
      </v-card-subtitle>
    </v-card>
    </div>
    </div>
    <div class="cart">
      장바구니
      <div v-for="(item,index) in cart_list"
            :key="index"
      >
        {{item.productName}}
         <vue-numeric-input  v-model="item.amount" :min="1" :max="item.stock" :step="1"></vue-numeric-input>
        </div>
        <div>
          <v-btn @click="moveToPay">구입</v-btn>
        </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import VueNumericInput from 'vue-numeric-input'
export default {
  name: "BuyProduct",
   data() {
        return {
          list: [],
          cart_list:[],
          value : 1,
        };
    },
    created(){
      this.getList();
    },
  components: {
    VueNumericInput
  },
  methods: {
      getList(){
        axios
        .get(`http://localhost:8088/product/list`)
        .then((response) => {
          this.list = response.data;
          console.log("리스트" + this.list[0].productName);
          for(var i in this.list){
            this.list[i].amount = 0;
          }
        })
        .catch(function(error) {
          console.log(error);
        });
     },
     plusAmount(index){
       this.list[index].amount += 1;
       console.log(this.list[index].productName + this.list[index].amount)
     },
     plusCart(item,index){
       this.cart_list.push(item);
       this.cart_list[index].amount = 0;
      // console.log( this.cart_list[0].productName);
      // console.log( "sdf"+this.list[0].productName);

     },
     moveToPay(){
       this.$router.push({name:'Pay' ,params : {cart: this.cart_list}});
     }
  }
};
</script>

<style>
.product-card{
    /* text-align: center; */
    float : left;
    margin-left: 5%;
    margin-top: 2%;
    cursor:pointer ;
}
.cards-dummy{
  margin-top: 5%;
  margin-right: 20%;
}
.cart{
  float: right;
  background-color: blue;
}
.cart-plus-btn{
  margin-left: 30%;
  width: 15%;
  
}
</style>