<template>
      <div class="main">
            <div class="LoginBody cardBody">
                  <Row style="height:100%">
                        <Col span="17">
                        
                        <p class="title">登陆</p>
                        
                        <Form ref="loginForm" :model="loginUser" :rules="loginRules" inline>
                              <div class="inputDiv">
                                    <FormItem prop="username">
                                          <Input size="large" prefix="ios-contact" placeholder="用户名、邮箱、电话号码" v-model="loginUser.username" />
                                    </FormItem>
                                    <FormItem prop="password">
                                          <Input size="large" password type="password" prefix="ios-lock" placeholder="密码" v-model="loginUser.password" />
                                    </FormItem>
                                    <div class="codeDiv">
                                          <FormItem prop="code" class="code">
                                                <Input size="large" placeholder="验证码" v-model="loginUser.code" />
                                          </FormItem>
                                          <Button @click="getCodeBtn" type="primary" v-show="codeBotton" size="large">获取验证码</Button>
                                          
                                          <div v-show="!codeBotton">
                                                <Button type="primary" @click="getCodeBtn" v-show="codeTime=='重新获取'" size="large">重新获取</Button>
                                                <span v-show="codeTime!='重新获取'">{{codeTime}}</span>
                                                <img v-show="!codeBotton" :src="codeImg" />
                                          </div>
                                    </div>
                                    
                                    <Checkbox v-model="loginUser.rememberMe">记住密码</Checkbox>
                                    
                                    <Button @click="login" size="large" type="primary">登陆</Button>
                                    
                                    <div>
                                          <router-link :to="{ name: 'Regist'}" style="float:right">新用户注册</router-link>
                                    </div>
                                    
                              </div>
                              
                        </Form>
                        </Col>
                        <Col class="Animation" span="7">
                        <div style="height:100%;padding:10px">
                              <vue-particles style="height:100%" color="#292929" :particleOpacity="1" :particlesNumber="150" shapeType="edge" :particleSize="2" linesColor="#292929" :linesWidth="1" :lineLinked="true" :lineOpacity="0.8" :linesDistance="150" :moveSpeed="1" :hoverEffect="true" hoverMode="grab" :clickEffect="true" clickMode="remove"></vue-particles>
                        </div>
                        </Col>
                  </Row>
            </div>
            
      </div>
</template>

<script>
import { getCodeImg } from "@/js/api/Login";
import Cookies from "js-cookie";
import { encrypt, decrypt } from "../utils/jsencrypt";

export default {
  name: "Login",
  components: {},
  computed: {},
  data() {
    return {
      loginUser: {
        username: "",
        password: "",
        code: "",
        uuid: "",
        rememberMe: false,
      },
      codeImg: "",
      codeBotton: true,
      codeTime: 60,
      testObj: {},
      loading: false,
      redirect: undefined,
      loginRules: {
        username: [
          {
            required: true,
            message: "请输入用户名",
            trigger: "blur",
          },
        ],
        password: [
          {
            required: true,
            message: "请输入密码",
            trigger: "blur",
          },
        ],
        code: [
          {
            required: true,
            message: "请输入验证码",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    getHeight() {},
    getCode() {
      getCodeImg().then((res) => {
        this.codeImg = "data:image/gif;base64," + res.img;
        this.loginUser.uuid = res.uuid;
        this.countDown();
      });
    },
    getCodeBtn() {
      this.codeTime = 60;
      this.getCode();
      this.codeBotton = false;
    },
    countDown() {
      var this_ = this;
      var interval = setInterval(function () {
        if (this_.codeTime <= 0 || this_.codeTime == "重新获取") {
          this_.codeTime = "重新获取";
          clearInterval(interval);
        } else {
          this_.codeTime = this_.codeTime - 1;
        }
      }, 1000);
    },
    getCookie() {
      const username = Cookies.get("username");
      const password = Cookies.get("password");
      const rememberMe = Cookies.get("rememberMe");
      this.loginUser = {
        username: username === undefined ? this.loginUser.username : username,
        password:
          password === undefined ? this.loginUser.password : decrypt(password),
        rememberMe: rememberMe === undefined ? false : Boolean(rememberMe),
      };
    },
    login() {
      this.loading = true;
      this.$refs["loginForm"].validate((valid) => {
        if (valid) {
          if (this.loginUser.rememberMe) {
            Cookies.set("username", this.loginUser.username, { expires: 30 });
            Cookies.set("password", encrypt(this.loginUser.password), {
              expires: 30,
            });
            Cookies.set("rememberMe", this.loginUser.rememberMe, {
              expires: 30,
            });
          } else {
            Cookies.remove("username");
            Cookies.remove("password");
            Cookies.remove("rememberMe");
          }
          this.$store
            .dispatch("Login", this.loginUser)
            .then(() => {
              this.$router.push({ path: this.redirect || "/" }).catch(() => {});
            })
            .catch(() => {
              this.loading = false;
              this.getCode();
            });
        } else {
          return;
        }
      });
    },
  },
  mounted() {},
  watch: {
    codeTime() {},
    $route: {
      handler: function (route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true,
    },
  },
  created() {
    this.getHeight();
    this.getCookie();
  },
};
</script>

<style scoped lang="less">
@import "../css/main.less";

.main {
  background: url("../assets/background-1.png") no-repeat;
  background-size: cover;
}

.LoginBody {
  background: white;
  padding: 3rem;
  position: relative;
}
.title {
  font-size: 2rem;
  font-weight: 500;
}

.Animation {
  background: rgba(187, 214, 255, 0.2);
  padding: -30px;
  position: absolute;
  top: 0;
  right: 0;
  height: 100%;
  width: 30%;
  border-radius: 0 @radio @radio 0;
}
.inputDiv {
  display: flex;
  flex-direction: column;
  margin-top: 10%;
  > * {
    margin: auto;
    margin-left: 4rem;
    width: 70%;
    height: 50px;
    margin-bottom: 3%;
  }
  > .codeDiv {
    display: flex;
    justify-content: space-between;
    > .code {
      display: inline;
    }
    > div {
      width: 50%;
      display: flex;
      flex-direction: row-reverse;
      > * {
        margin-left: 2px;
      }
      > img {
        border: #dcdee2 solid 1px;
        height: 40px;
        width: 80px;
        border-radius: 4px;
      }
      > span {
        padding: 6px;
        font-size: 17px;
        text-align: center;
        font-weight: 600;
      }
    }
  }
}
</style>

<style scoped>
.codeDiv >>> .ivu-form-item .ivu-form-item-required .ivu-form-item-error {
  height: 404px;
}
</style>