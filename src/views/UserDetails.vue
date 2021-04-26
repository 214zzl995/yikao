<template>
      <div class="main_s">
            <div class="mainBody">
                  <div class="MainPlaceholder"></div>
                  <div class="all">
                        <div class="inputAll">
                              <Form ref="EditForm" :model="EditUser" :rules="EditRules" :label-width="0" label-position="left">
                                    <div class="item AaN" style="padding:6px">
                                          <FormItem prop="avatar">
                                                <upload-pic :type="'Avatar'" :img="User.avatar" @uploadImg="editAvatar" />
                                          </FormItem>
                                          <div class="UaE" @mouseenter="enter('nikename')" @mouseleave="leave('nikename')">
                                                <p v-show="!editShow.nikename">{{User.nikename}}</p>
                                                <div class="edit">
                                                      <Icon type="md-create" v-show="show.nikename && !editShow.nikename" @click="edit('nikename')" />
                                                </div>
                                                <div class="editDiv" v-show="editShow.nikename">
                                                      <FormItem prop="nikename">
                                                            <Input v-model="EditUser.nikename" class="inputD" />
                                                            <Button @click="editShow.nikename=false" type="text">取消</Button>
                                                            <Button @click="editPost('nikename')" type="primary">保存</Button>
                                                      </FormItem>
                                                </div>
                                          </div>
                                    </div>
                                    <div class="item" @mouseenter="enter('username')" @mouseleave="leave('username')">
                                          <div>
                                                <span class="itemTitle">用户名:</span>
                                                <span v-show="!editShow.username" class="itemValue">{{User.username}}</span>
                                          </div>
                                          <div class="sEdit edit">
                                                <Icon type="md-create" v-show="show.username && !editShow.username" @click="edit('username')" />
                                          </div>
                                          <div class="input" v-show="editShow.username">
                                                <FormItem prop="username">
                                                      <Input v-model="EditUser.username" class="inputD" />
                                                      <Button @click="editShow.username=false" type="text">取消</Button>
                                                      <Button @click="editPost('username')" type="primary">保存</Button>
                                                </FormItem>
                                          </div>
                                    </div>
                                    <div class="item" @mouseenter="enter('email')" @mouseleave="leave('email')">
                                          <div>
                                                <span class="itemTitle">邮箱:</span>
                                                <span v-show="!editShow.email" class="itemValue">{{User.email}}</span>
                                          </div>
                                          <div class="sEdit edit">
                                                <Icon type="md-create" v-show="show.email && !editShow.email" @click="edit('email')" />
                                          </div>
                                          <div class="input" v-show="editShow.email">
                                                <FormItem prop="email">
                                                      <Input v-model="EditUser.email" class="inputD" />
                                                      <Button @click="editShow.email=false" type="text">取消</Button>
                                                      <Button @click="editPost('email')" type="primary">保存</Button>
                                                </FormItem>
                                          </div>
                                    </div>
                                    <div class="item" @mouseenter="enter('qq')" @mouseleave="leave('qq')">
                                          <div>
                                                <span class="itemTitle">QQ:</span>
                                                <span v-show="!editShow.qq" class="itemValue">{{User.qq}}</span>
                                          </div>
                                          <div class="sEdit edit">
                                                <Icon type="md-create" v-show="show.qq && !editShow.qq" @click="edit('qq')" />
                                          </div>
                                          <div class="input" v-show="editShow.qq">
                                                <FormItem prop="qq">
                                                      <Input v-model="EditUser.qq" class="inputD" />
                                                      <Button @click="editShow.qq=false" type="text">取消</Button>
                                                      <Button @click="editPost('qq')" type="primary">保存</Button>
                                                </FormItem>
                                          </div>
                                    </div>
                                    <div class="item" @mouseenter="enter('phonenumber')" @mouseleave="leave('phonenumber')">
                                          <div>
                                                <span class="itemTitle">电话:</span>
                                                <span v-show="!editShow.phonenumber" class="itemValue">{{User.phonenumber}}</span>
                                          </div>
                                          <div class="sEdit edit">
                                                <Icon type="md-create" v-show="show.phonenumber && !editShow.phonenumber" @click="edit('phonenumber')" />
                                          </div>
                                          <div class="input" v-show="editShow.phonenumber">
                                                <FormItem prop="phonenumber">
                                                      
                                                      <Input class="inputD tel" v-model="EditUser.phonenumber" type="number"></Input>
                                                      <Button @click="editShow.phonenumber=false" type="text">取消</Button>
                                                      <Button @click="editPost('phonenumber')" type="primary">保存</Button>
                                                </FormItem>
                                                
                                          </div>
                                    </div>
                                    <div class="item" @mouseenter="enter('school')" @mouseleave="leave('school')">
                                          <div>
                                                <span class="itemTitle">学校:</span>
                                                <span v-show="!editShow.school" class="itemValue">{{User.school}}</span>
                                          </div>
                                          <div class="sEdit edit">
                                                <Icon type="md-create" v-show="show.school && !editShow.school" @click="edit('school')" />
                                          </div>
                                          <div class="input" v-show="editShow.school">
                                                <FormItem prop="school">
                                                      <Input v-model="EditUser.school" class="inputD" />
                                                      <Button @click="editShow.school=false" type="text">取消</Button>
                                                      <Button @click="editPost('school')" type="primary">保存</Button>
                                                </FormItem>
                                          </div>
                                    </div>
                                    <div class="item" @mouseenter="enter('qualifications')" @mouseleave="leave('qualifications')">
                                          <div>
                                                <span class="itemTitle">教师资格:</span>
                                                <span v-show="!editShow.qualifications" class="itemValue">{{User.qualifications}}</span>
                                          </div>
                                          <div class="sEdit edit">
                                                <Icon type="md-create" v-show="show.qualifications && !editShow.qualifications" @click="edit('qualifications')" />
                                          </div>
                                          <div class="input" v-show="editShow.qualifications">
                                                <FormItem prop="qualifications">
                                                      <Input v-model="EditUser.qualifications" class="inputD" />
                                                      <Button @click="editShow.qualifications=false" type="text">取消</Button>
                                                      <Button @click="editPost('qualifications')" type="primary">保存</Button>
                                                </FormItem>
                                          </div>
                                    </div>
                                    <div class="item" @mouseenter="enter('password')" @mouseleave="leave('password')">
                                          <div>
                                                <span class="itemTitle">密码:</span>
                                                <span v-show="!editShow.password" class="itemValue">***************</span>
                                          </div>
                                          <div class="sEdit edit">
                                                <Icon type="md-create" v-show="show.password && !editShow.password" @click="edit('password')" />
                                          </div>
                                          <div class="input" v-show="editShow.password">
                                                <FormItem prop="password">
                                                      <Input type="password" password v-model="EditUser.password" class="inputD" />
                                                      <Button @click="editShow.password=false" type="text">取消</Button>
                                                      <Button @click="editPost('password')" type="primary">保存</Button>
                                                </FormItem>
                                          </div>
                                    </div>
                                    <div class="item" @mouseenter="enter('introduction')" @mouseleave="leave('introduction')">
                                          <div>
                                                <span class="itemTitle">自我介绍:</span>
                                                <span v-show="!editShow.introduction" class="ZWJS itemValue">{{User.introduction}}</span>
                                          </div>
                                          <div class="sEdit edit">
                                                <Icon type="md-create" v-show="show.introduction && !editShow.introduction" @click="edit('introduction')" />
                                          </div>
                                          <div class="input" v-show="editShow.introduction">
                                                <FormItem prop="introduction">
                                                      <Input v-model="EditUser.introduction" class="inputD" type="textarea" :rows="4" placeholder="让大家认识一下吧" />
                                                      <Button @click="editShow.introduction=false" type="text">取消</Button>
                                                      <Button @click="editPost('introduction')" type="primary">保存</Button>
                                                </FormItem>
                                          </div>
                                    </div>
                              </Form>
                        </div>
                  </div>
            </div>
      </div>
</template>

<script>
import { updateD, updateP } from "../js/api/Login";
import UploadPic from "../components/uploadPic.vue";
export default {
  name: "UserDetails",
  components: { UploadPic },
  computed: {},
  data() {
    const validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        var reg = /^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)(?!([^(0-9a-zA-Z)])+$)^.{8,16}$/;
        if (!reg.test(value)) {
          callback(new Error("密码须为8-16位,必须包含大小写英文字母和数字"));
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
      User: this.$store.getters.userDetails,
      show: {
        nikename: false,
        username: false,
        email: false,
        qq: false,
        phonenumber: false,
        school: false,
        qualifications: false,
        introduction: false,
        password: false,
      },
      editShow: {
        nikename: false,
        username: false,
        email: false,
        qq: false,
        phonenumber: false,
        school: false,
        qualifications: false,
        introduction: false,
        password: false,
      },
      input: "",
      EditRules: {
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
      EditUser: {
        id: null,
        password: null,
        avatar: null,
        username: null,
        nikename: null,
        school: null,
        qq: null,
        phonenumber: null,
        email: null,
        qualifications: null,
        introduction: null,
      },
    };
  },
  methods: {
    getUser() {
      this.user = this.$store.getters.userDetails;
    },
    enter(name) {
      this.show[name] = true;
    },
    leave(name) {
      this.show[name] = false;
    },
    edit(name) {
      this.$refs["EditForm"].resetFields();
      this.EditUser[name] = this.User[name];
      this.editShow[name] = true;
    },
    editPost(name) {
      this.$refs["EditForm"].validateField(name, (valid) => {
        // valid的为空则证明表单验证通过，否则不通过
        if (!valid) {
          console.log(name);
          if (name == "password") {
            const user = {
              password: this.EditUser.password,
            };
            updateP(user, this.User.userId).then((res) => {
              this.$Message.success("更新成功！");
              this.editShow[name] = false;
            });
          } else {
            const user = {
              id: this.User.id,
              avatar: null,
              username: null,
              nikename: null,
              school: null,
              qq: null,
              phonenumber: null,
              email: null,
              qualifications: null,
              introduction: null,
            };
            user[name] = this.EditUser[name];
            var type = this.$store.getters.roles[0];
            updateD(type, user, this.User.userId).then((res) => {
              this.$Message.success("更新成功！");
              this.$store.dispatch("GetInfo");
              this.User = res;
              this.editShow[name] = false;
            });
          }
        }
      });
    },
    editAvatar(src) {
      this.EditUser.avatar = src;
      this.editPost("avatar");
    },
  },
  mounted() {},
  watch: {},
};
</script>

<style scoped lang="less">
@import "../css/main.less";
.mainBody {
  background: none;
  box-shadow: 0 0 0 0;
}

.all {
  padding-top: 30px;
  display: flex;
  justify-content: center;
}

.inputAll {
  width: 65%;
  padding: 15px;
  display: flex;
  flex-direction: column;
  background: white;
  border-radius: 1rem;
  min-height: 96vh;
}
.AaN {
  display: flex;
  flex-direction: row;
  align-items: center;
  > div {
    > p {
      font-size: 30px;
      margin-left: 30px;
      font-weight: 600;
    }
  }
}

.UaE {
  display: flex;
  flex-direction: row;
  width: 100%;
}

.edit {
  color: #2d8cf0;
  font-size: 30px;
  margin-left: 10px;
}

.sEdit {
  font-size: 16px;
}

.editDiv {
  display: flex;
  flex-direction: row;
  > * {
    margin-left: 10px;
  }
}

.inputD {
  width: 400px;
}

.Bitem {
  padding: 10px 0;
}

.item {
  display: flex;
  flex-direction: row;
  border-bottom: 1px solid #dcdee2;
  padding: 30px 0;
}

.itemTitle {
  font-size: 16px;
  font-weight: 600;
  margin-left: 193px;
  width: 150px;
  display: inline-block;
}

.itemValue {
  font-size: 16px;
}

.input {
  font-size: 16px;
}

.ZWJS {
  display: inline-block;
  width: 580px;
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