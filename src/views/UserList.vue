<template>
      <div class="PaperMain">
            <div class="MainPlaceholder"></div>
            <div class="Paper">
                  <div class="PaperDiv">
                        <div class="any">
                              <div class="title"><span>用户列表</span></div>
                              <div class="userList">
                                    <div class="header" v-show="this.$route.name=='courseTea'">
                                          <Checkbox v-model="selectAll">全选</Checkbox>
                                          <Button @click="exitStu" v-show="selectData.length!=0"> 移出课程</Button>
                                    </div>
                                    
                                    <user-card v-for="user,index in userList" :data="user" :select="selectAll" @selectChange="selectChange" :key="index" />
                              </div>
                        </div>
                  </div>
            </div>
      </div>
</template>

<script>
import { getCourseUser, exitStu } from "../js/api/Course";
import UserCard from "../components/UserCard.vue";
export default {
  name: "UserList",
  components: { UserCard },
  computed: {},
  data() {
    return {
      userList: [],
      selectData: [],
      selectAll: false,
      CourseId: -1,
    };
  },
  methods: {
    selectChange(boo, data) {
      console.log("aa");
      if (boo) {
        this.selectData.push(data);
      } else {
        this.selectData.splice(this.selectData.indexOf(data), 1);
      }
    },
    testData() {
      for (let i = 0; i < 20; i++) {
        let a = {
          id: i,
          username: "牛niuniu",
          avatar: "http://127.0.0.1:8088/admin/TOIMGa11500401105435N.jpg",
          status: true,
        };
        this.userList.push(a);
      }
    },
    getCourseU() {
      getCourseUser(this.CourseId).then((res) => {
        this.userList = res;
      });
    },
    exitStu() {
      this.$Modal.confirm({
        title: "提醒",
        content: "<p>确定要将这些用户移出你的课程么</p>",
        loading: true,
        onOk: () => {
          exitStu(this.selectData, this.CourseId).then((res) => {
            console.log(res);
            this.$Modal.remove();
            this.getCourseU();
            this.$Message.success("移出成功");
          });
        },
      });
    },
  },
  mounted() {
    let urlParams = this.$route.query;
    const { CourseId } = urlParams;
    this.CourseId = CourseId;
    this.getCourseU();
  },
  watch: {
    selectAll() {
      if (this.selectAll) {
        for (let i = 0; i < this.userList.length; i++) {
          this.selectData.push(this.userList[i].userDetails.userId);
        }
      } else {
        this.selectData = [];
      }
    },
  },
};
</script>

<style lang="less" scoped>
@import "../css/main.less";

.title {
  font-size: 20px;
  font-weight: 600;
  margin-bottom: 20px;
  width: 100%;
}
.userList {
  display: flex;
  flex-wrap: wrap;
  border-radius: 6px;

  > .header {
    height: 50px;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
  > div {
    width: 50vw;
    margin-top: 5px;
  }
}
</style>