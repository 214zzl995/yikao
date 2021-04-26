<template>
      <div>
            <div class="NavBar">
                  <div class="link">
                        <div @click="go('index')">
                              <span>首页</span>
                        </div>
                        <div @click="go('myindex')">
                              <span>我的课程</span>
                        </div>
                        <div @click="go('home')">
                              <span>考试历史</span>
                        </div>
                        <div @click="go('WrongQuestion')">
                              <span>错题本</span>
                        </div>
                  </div>
                  <div class="search">
                        <Input v-model="searchValue" icon="md-search" placeholder="请输入课程、教师名称" search @on-search="search()" style="width: 300px" />
                  </div>
                  <div @click="userShow=!userShow">
                        <Badge :count="msg" type="primary">
                              <Avatar shape="square" icon="ios-person" :src="User.avatar" />
                        </Badge>
                  </div>
                  <div class="Placeholder"></div>
            </div>
            
            
            <Drawer title="" :closable="false" v-model="userShow">
                  <div class="userInfo">
                        <Avatar icon="ios-person" size="100" :src="User.avatar" />
                        <div>
                              <p class="drawer username">{{User.nikename}}</p>
                              <p class="drawer school">{{User.school}}</p>
                        </div>
                        <div>
                        </div>
                  </div>
                  <div class="loginOut">
                        <Divider />
                        
                        <Button @click="loginOut" type="error">用户退出</Button>
                        <router-link class="editUser" :to="{ name: 'UserDetails'}">更新用户数据</router-link>
                  </div>
            </Drawer>
            
            
            <Modal v-model="editModal" width="360" class-name="userModal" :mask-closable="false">
                  <div class="modelBody">
                        <div>
                              <upload-pic :type="'Avatar'" :img="User.avatar" />
                        </div>
                        <Input class="input" prefix="ios-contact" v-model="User.nikename" maxlength="10" />
                  </div>
                  <div slot="footer" class="aa">
                        
                  </div>
            </Modal>
            
            
      </div>
</template>

<script>
import UploadPic from "./uploadPic.vue";
export default {
  name: "NavigationBar",
  components: { UploadPic },
  computed: {
    alertCont() {
      return this.$websocket.getters.onEvent("ConnectByJson.C");
    },
  },
  data() {
    return {
      theme1: "light",
      msg: 0,
      userShow: false,
      User: this.$store.getters.userDetails,
      searchValue: "",
      editModal: false,
    };
  },
  methods: {
    loginOut() {
      this.$store.dispatch("LogOut");
      this.$store.dispatch("FedLogOut");
      this.$router.push("/login");
    },
    search() {
      this.$router.push({
        name: "CourseInquire",
        query: { value: this.searchValue },
      });
      this.searchValue = "";
    },
    wsMessage(data) {},
    wsError() {},
    requstWs() {},
    initWebsocket() {
      const UserId = this.User.userId == undefined ? "1" : this.User.userId;
      this.$websocket.dispatch(
        "WEBSOCKET_INIT",
        "ws://127.0.0.1:8080/msg/" + UserId + "/" + this.$store.getters.token
      );
    },
    go(name) {
      this.$router.push({ name: name });
    },
  },
  mounted() {
    this.initWebsocket();
  },
  watch: {
    alertCont: function (a, b) {
      if (a !== b && a) {
        console.log("触发事件：");
        console.log("a:" + JSON.stringify(a));
        console.log("b:" + JSON.stringify(b));
      }
    },
  },
  beforeDestroy() {
    //closeWebsocket();
  },
};
</script>

<style scoped lang="less">
@import "../css/main.less";
.NavBar {
  height: 60px;
  width: 100vw;
  background: white;
  position: fixed;
  top: 0;
  left: 0;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  z-index: 999;
  padding: 0 1vw;
  .Shadow();
  > .search {
    margin-right: 40px;
  }
}
.userInfo {
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  font-size: 20px;
  font-weight: 500;
}
.loginOut {
  position: absolute;
  bottom: 0;
  width: 90%;
  margin-bottom: 10px;
  display: flex;
  flex-wrap: wrap;
  flex-direction: row-reverse;
  justify-content: space-between;
  align-items: center;
}
.modelBody {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.input {
  width: 200px;
  border-bottom: #515a6e solid 1px;
  margin-top: 25px;
}
.username {
  margin-top: 20px;
}
.drawer {
  width: 100%;
  text-align: center;
}

.school {
  font-size: 15px;
}

.link {
  flex: 1;
  display: flex;
  flex-direction: row;
  > div {
    width: 100px;
    text-align: center;
    font-weight: 600;
    cursor: pointer;
    height: 60px;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  > div:hover {
    border-bottom: 2px solid #2d8cf0;
    color: #2d8cf0;
  }
}
</style>

<style lang="less">
.userModal {
  .ivu-modal {
    .ivu-modal-footer {
      border-top: 0px;
    }
  }
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
.input >>> .ivu-input {
  text-align: center;
}
</style>