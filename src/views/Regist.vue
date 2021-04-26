<template>
      <div class="main">
            <div class="LoginBody cardBody">
                  <Row style="height:100%">
                        <Col span="17">
                        <p class="title">注册</p>
                        <div class="inputDiv">
                              <Form ref="RegistForm" :model="User" :rules="RegistRules" :label-width="80" label-position="left">
                                    <FormItem label="用户名" prop="username" :error="usernameError">
                                          <Input v-model="User.username" placeholder="请输入用户名"></Input>
                                    </FormItem>
                                    <FormItem label="E-Mail" prop="email">
                                          <Input v-model="User.email" type="email" email placeholder="请输入邮箱"></Input>
                                    </FormItem>
                                    <FormItem label="电话" prop="phonenumber">
                                          <Input class="tel" v-model="User.phonenumber" type="number" placeholder="请输入电话号码"></Input>
                                    </FormItem>
                                    <FormItem label="密码" prop="password">
                                          <Input type="password" password placeholder="请输入密码" v-model="User.password" />
                                    </FormItem>
                                    <FormItem label="确认密码" prop="passwordCheck">
                                          <Input type="password" password placeholder="请重新输入密码" v-model="User.passwordCheck" />
                                    </FormItem>
                                    
                                    <FormItem label="身份" prop="userType">
                                          <RadioGroup v-model="User.userType">
                                                <Radio label="01">教师</Radio>
                                                <Radio label="02">学生</Radio>
                                          </RadioGroup>
                                    </FormItem>
                                    
                                    <div class="btnDiv">
                                          <Button @click="regist" size="large" type="primary">注册</Button>
                                          
                                    </div>
                                    <div style="margin-top:10px">
                                          <router-link :to="{ name: 'Login'}" style="float:right">已有账号？</router-link>
                                    </div>
                              </Form>
                        </div>
                        </Col>
                        <Col class="Animation" span="7">
                        <vue-particles style="height:100%" color="#292929" :particleOpacity="1" :particlesNumber="150" shapeType="edge" :particleSize="2" linesColor="#292929" :linesWidth="1" :lineLinked="true" :lineOpacity="0.8" :linesDistance="150" :moveSpeed="1" :hoverEffect="true" hoverMode="grab" :clickEffect="true" clickMode="remove"></vue-particles>
                        </Col>
                  </Row>
            </div>
            
      </div>
</template>

<script>
import { regist } from "../js/api/Login";
export default {
  name: "Regist",
  components: {},
  computed: {},
  data() {
    const validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        var reg = /^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)(?!([^(0-9a-zA-Z)])+$)^.{8,16}$/;
        console.log(reg.test(value));
        if (!reg.test(value)) {
          callback(new Error("密码须为8-16位,必须包含大小写英文字母和数字"));
        }
        if (this.User.passwordCheck !== "") {
          // 对第二个密码框单独验证
          this.$refs.RegistForm.validateField("passwordCheck");
        }
        callback();
      }
    };
    const validatePassCheck = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请重新输入密码"));
      } else if (value !== this.User.password) {
        callback(new Error("两次密码不同"));
      } else {
        callback();
      }
    };
    const validatePhoneNumber = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入手机号码"));
      } else {
        var reg = /^1([3589][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/;
        if (!reg.test(value)) {
          callback(new Error("手机号码格式错误"));
        } else {
          callback();
        }
      }
    };
    const validateEmail = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入邮箱"));
      } else {
        var reg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        if (!reg.test(value)) {
          callback(new Error("邮箱格式错误"));
        } else {
          callback();
        }
      }
    };
    const validateUserName = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入用户名"));
      } else {
        var reg = /^[a-zA-Z0-9_-]{4,16}$/;
        if (!reg.test(value)) {
          callback(new Error("用户名须为4到16位（字母，数字，下划线）"));
        } else {
          callback();
        }
      }
    };
    return {
      User: {
        username: "",
        userType: "",
        email: "",
        phonenumber: "",
        password: "",
        passwordCheck: "",
      },
      usernameError: "",
      emailError: "",
      phonenumberError: "",
      RegistRules: {
        username: [
          {
            required: true,
            validator: validateUserName,
            trigger: "blur",
          },
        ],
        email: [
          {
            required: true,
            validator: validateEmail,
            trigger: "blur",
          },
        ],
        phonenumber: [
          {
            required: true,
            validator: validatePhoneNumber,
            trigger: "blur",
          },
        ],
        password: [
          {
            required: true,
            validator: validatePass,
            trigger: "blur",
          },
        ],
        passwordCheck: [
          {
            required: true,
            validator: validatePassCheck,
            trigger: "blur",
          },
        ],
        userType: [
          {
            required: true,
            message: "请选择注册身份",
            trigger: "change",
          },
        ],
      },
    };
  },
  methods: {
    regist() {
      this.$refs["RegistForm"].validate((valid) => {
        if (valid) {
          let data = {};
          data = JSON.parse(JSON.stringify(this.User));
          delete data.passwordCheck;
          regist(data).then((res) => {
            if (res.userId != "") {
              this.$Modal.success({
                title: "注册成功",
                content: "<p>您的用户id为" + res.userId + "</p>",
                onOk: () => {
                  this.$router.push({ name: "Login" });
                },
              });
            } else {
              if (res.code == 5001) {
                this.usernameError = res.msg;
              } else if (res.code == 5002) {
                this.emailError = res.msg;
              } else {
                this.phonenumberError = res.msg;
              }
            }
          });
        }
      });
    },
  },
  mounted() {},
  watch: {},
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

.inputDiv {
  display: flex;
  flex-direction: column;
  margin-top: 6%;
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

.btnDiv {
  display: flex;
  justify-content: space-between;
  > * {
    width: 100%;
  }
}
</style>


<style>
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
}
input[type="number"] {
  -moz-appearance: textfield;
}
</style>
