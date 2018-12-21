### 使用Vuex


### State
用来存储属性，一些公共的数据（`一个页面改变该数据，别的页面引用的该数据也会发生改变`）
```js
state:{ 
    products: [
    {name: '鼠标', price: 120},
    {name: '键盘', price: 40},
    {name: '耳机', price: 60},
    {name: '显示屏', price: 80}
    ],
    user: {},
    token: null,
},
```

使用：<br>
1.在页面中引入
```js
import testStore from '../store/testStore'
```
2.在data的相应属性上调用
```js
    data () {
        return {
            products: testStore.state.products
        }
    },
```
3.页面上使用
```html
<span class="name">{{ product.name }}</span>
```

### Getters
类似Java的get方法，能够通过某种公式或者直接返回值
```js
getters:{
    saleProducts: (state) => {
        let saleProducts = state.products.map(
            products => {
                return{
                    name: products.name,
                    price: products.price / 2
                }
            }
        )
        return saleProducts;
    }
},
```
使用：<br>
1.在页面中引入<br>
2.在data的相应属性上调用
```js
data () {
        return {
            products: testStore.getters.saleProducts
        }
    },
```
3.页面上使用

### Mutations
类似method方法，保存着更改数据的回调函数`第一个参数是state(应该是定死的), 第二参数是payload, 也就是自定义的参数.`
```js
mutations: {
    minusPrice (state,payload) {
        let newPrice = state.products.forEach(
            products =>{
                products.price -= payload
            }
        )
    }
}
```
使用：<br>
1.通过commit调用在store中名为`minusPrice`的方法，`2`为自定义参数
```js
testStore.commit('minusPrice',2);
```
2.可以将它写成一个函数来调用
```html
<button @click="minusPrice">减价2美元</button>
```
```js
methods: {
    minusPrice(){
        testStore.commit('minusPrice',2);
    }
}
```
### Actions
Actions 类似于 mutations，不同在于：

- actions提交的是mutations而不是直接变更状态
- actions中可以包含异步操作, mutations中绝对不允许出现异步
- actions中的回调函数的第一个参数是context, 是一个与store实例具有相同属性和方法的对象
- 此时,我们在store中添加actions属性, 其中minusPriceAsync采用setTimeout来模拟异步操作,延迟2s执行 该方法用于异步改变我们刚才在mutaions中定义的minusPrice

`个人见解：action是一个调用mutations里面的方法的一个方法`

```js
actions:{
    minusPriceAsync(context, payload){
        setTimeout(
            ()=>{
                context.commit('minusPrice',payload);
            },2000
        )
    }
}
```
使用：<br>
1.通过dispatch调用在store中名为`minusPrice`的方法
```js
testStore.dispatch('minusPriceAsync',5);
```
2.可以将它写成一个函数来调用
```html
<button @click="minusPriceAsync">减价5美元</button>
```
```js
methods: {
    minusPriceAsync(){
        testStore.dispatch('minusPriceAsync',5);
    }
}
```