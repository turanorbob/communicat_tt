import Vue from 'vue';
import ElementUI from 'element-ui';
import App from './vue/App.vue';

Vue.use(ElementUI);

new Vue({
    el: '#app',
    render: h => h(App)
})