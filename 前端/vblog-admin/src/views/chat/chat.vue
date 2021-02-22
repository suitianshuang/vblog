<template>
  <div id="app" style="margin-left: 80px;">
    <div class="sidebar">
      <card></card>
      <list></list>
    </div>
    <div class="main">
      <message></message>
      <usertext></usertext>
    </div>
  </div>
</template>

<script>
import card from '../../components/chat/card.vue'
import list from '../../components/chat/list.vue'
import message from '../../components/chat/message.vue'
import usertext from '../../components/chat/usertext.vue'

export default {
  name: 'Chat',
  data () {
    return {
      
    }
  },
  mounted:function() {
    this.$store.dispatch('initData');
	// this.$store.dispatch('concent');
  },
  components:{
    card,
    list,
    message,
    usertext
  }
}
</script>

<style lang="scss" scoped>
#app {
	background: #ffffff;
  margin: 20px auto;
  width: 1000px;
  height: 600px;
  overflow: hidden;
  border-radius: 10px;
  .sidebar, .main {
    height: 100%;
  }
  .sidebar {
    float: left;
    color: #f4f4f4;
    background-color: #2e3238;
    width: 200px;
  }
  .main {
    position: relative;
    overflow: hidden;
    background-color: #f5f5f5 ;
  }
}
</style>
