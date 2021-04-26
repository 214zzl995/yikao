<template>
      <div class="User">
            
            <div class="userCard" @click="sing=!sing">
                  
                  <Checkbox class="checkBox" v-model="sing" v-show="this.$route.name=='courseTea'"></Checkbox>
                  <div style="margin-left:12px">
                        <Avatar icon="ios-person" :src="data.userDetails.avatar" size="45" />
                  </div>
                  
                  <span class="username">{{data.userDetails.nikename}}</span>
                  <div class="status">
                        <Poptip trigger="hover" v-model="visible" placement="right">
                              <Icon type="md-more" class="more" @click.stop="visible=!visible" />
                              <div class="api" slot="content">
                                    <user :data="data.userDetails" />
                              </div>
                        </Poptip>
                        <Badge :status="data.status ? 'success': 'error'" :text="data.status ? '在线': '离线'" />
                  </div>
            </div>
      </div>
</template>

<script>
import HelloWorld from "./HelloWorld.vue";
import MySign from "./MySign.vue";
import User from "./User.vue";
export default {
  name: "UserCard",
  props: ["data", "select"],
  components: { HelloWorld, User, MySign },
  computed: {},
  data() {
    return {
      sing: false,
      visible: false,
    };
  },
  methods: {},
  mounted() {},
  watch: {
    select() {
      if (this.select) {
        this.sing = true;
      } else {
        this.sing = false;
      }
    },
    sing() {
      this.$emit("selectChange", this.sing, this.data.userDetails.userId);
    },
  },
};
</script>

<style lang="less" scoped>
.userCard {
  width: 100%;
  height: 70px;
  border-radius: 5px 12px 12px 5px;
  display: flex;
  align-items: center;
  cursor: pointer;
}

.userCard:hover {
  box-shadow: 0 1px 6px rgb(0 0 0 / 20%);
  border-color: #eee;
}

.username {
  margin-left: 12px;
  font-size: 15px;
  font-weight: 600;
  display: inline-block;
  vertical-align: middle;
  line-height: 20px;
}
.status {
  flex: 1;
  display: flex;
  flex-direction: row-reverse;
}

.checkBox {
  margin-left: 15px;
}

.more {
  font-size: 20px;
  margin: 0 30px;
}
</style>

<style scoped>
.ivu-badge >>> .ivu-badge-status-text {
  font-size: 11px;
  margin-right: 6px;
  margin-bottom: 4px;
}

.ivu-badge >>> .ivu-badge-status-dot {
  width: 8px;
  height: 8px;
}
</style>

<style scoped>
.User >>> .ivu-poptip {
  width: 100%;
}
.ivu-poptip >>> .ivu-poptip-rel {
  width: 100%;
}
.status >>> .ivu-poptip {
  width: 70px;
}

.ivu-avatar >>> img {
  object-fit: cover;
}
</style>