<template>
      <div class="CourseBar">
            <Menu :width="'240px'" @on-select="select" :theme="'light'" :active-name="hash" v-show="type=='Stu'" v-if="CourseData">
                  <MenuGroup>
                        <div class="MainPlaceholder"></div>
                  </MenuGroup>
                  
                  <MenuGroup>
                        <div class="MainPlaceholder"></div>
                  </MenuGroup>
                  <MenuGroup>
                        <div class="courseDetails">
                              <img class="courseImg" :src="CourseData.courseImage" alt="">
                              <p class="title">{{CourseData.courseTitle}}</p>
                        </div>
                  </MenuGroup>
                  <MenuGroup title="">
                        <MenuItem name="Paper">
                        <div class="courseDetails item">
                              <Icon type="md-document" />
                              <span>试卷</span>
                        </div>
                        </MenuItem>
                        <!-- <MenuItem name="2">
                        <div class="courseDetails item">
                              <Icon type="md-briefcase" />
                              <span>任务</span>
                        </div>
                        </MenuItem> -->
                        <MenuItem name="CourseComment">
                        <div class="courseDetails item">
                              <Icon type="md-chatboxes" />
                              <span>评论</span>
                        </div>
                        </MenuItem>
                        <MenuItem name="UserList">
                        <div class="courseDetails item">
                              <Icon type="md-people" />
                              <span>用户</span>
                        </div>
                        </MenuItem>
                        <div class="join courseDetails item" v-show="!subscription">
                              <Button @click="addMyCourse" size="large" type="primary">加入课程</Button>
                        </div>
                  </MenuGroup>
            </Menu>
            
            <Menu :width="'240px'" @on-select="select" :theme="'light'" :active-name="hash" v-show="type=='Tea'" v-if="CourseData">
                  <MenuGroup>
                        <div class="MainPlaceholder"></div>
                  </MenuGroup>
                  
                  <MenuGroup>
                        <div class="MainPlaceholder"></div>
                  </MenuGroup>
                  <MenuGroup>
                        <div class="courseDetails">
                              <img class="courseImg" :src="CourseData.courseImage" alt="">
                              <p class="title">{{CourseData.courseTitle}}</p>
                        </div>
                  </MenuGroup>
                  <MenuGroup title="">
                        <MenuItem name="Paper">
                        <div class="courseDetails item">
                              <Icon type="md-document" />
                              <span>试卷设置</span>
                        </div>
                        </MenuItem>
                        <!-- <MenuItem name="TaskEdit">
                        <div class="courseDetails item">
                              <Icon type="md-briefcase" />
                              <span>任务设置</span>
                        </div>
                        </MenuItem> -->
                        <MenuItem name="CourseComment">
                        <div class="courseDetails item">
                              <Icon type="md-chatboxes" />
                              <span>课程评论</span>
                        </div>
                        </MenuItem>
                        <MenuItem name="UserList">
                        <div class="courseDetails item">
                              <Icon type="md-people" />
                              <span>用户列表</span>
                        </div>
                        </MenuItem>
                        <MenuItem name="QuestionList">
                        <div class="courseDetails item">
                              <Icon type="md-list-box" />
                              <span>课程题库</span>
                        </div>
                        </MenuItem>
                        
                        <MenuItem name="CourseSetting">
                        <div class="courseDetails item">
                              <Icon type="md-settings" />
                              <span>课程设置</span>
                        </div>
                        </MenuItem>
                  </MenuGroup>
            </Menu>
      </div>
</template>

<script>
import { addUserCourse } from "@/js/api/Course";
export default {
  name: "CourseBar",
  props: ["CourseData", "subscription", "type"],
  components: {},
  computed: {
    hash() {
      var hash = window.location.hash;
      if (hash == "") {
        return "Paper";
      } else {
        var reg = new RegExp("#");
        var a = hash.replace(reg, "");
        return a;
      }
    },
  },
  data() {
    return {
      CourseId: -1,
      path: this.$route.path,
    };
  },
  methods: {
    addMyCourse() {
      addUserCourse(this.CourseData).then((res) => {
        if (res.id != null) {
          this.$emit("add");
          this.$Message.success("加入课程成功");
        }
      });
    },
    test(name) {
      const query = this.$router.history.current.query;
    },
    reloadRouter(path) {
      this.$router.replace({
        path: "/redirect",
        query: {
          nextPath: path + "?CourseId" + this.CourseId,
        },
      });
    },
    select(name) {
      this.$emit("select", name);
      window.location.replace("#" + name);
    },
    getHash() {
      var hash = window.location.hash;
      if (hash == "") {
        this.$emit("select", "Paper");
        window.location.replace("#Paper");
      } else {
        var reg = new RegExp("#");
        var a = hash.replace(reg, "");
        this.$emit("select", a);
      }
    },
  },
  mounted() {
    let urlParams = this.$route.query;
    const { CourseId } = urlParams;
    this.CourseId = CourseId;
    this.getHash();
  },
  watch: {
    $route: function () {},
  },
};
</script>

<style lang="less" scoped>
.CourseBar {
  position: fixed;
  left: 0;
  top: 0px;
}
.title {
  text-align: center;
  width: 180px;
  font-size: 18px;
  font-weight: 600;
  margin-top: 20px;
}

.courseDetails {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-wrap: wrap;
}

.courseImg {
  width: 80%;
  height: 100%;

  border-radius: 5px;
}

.item {
  font-size: 15px;
  > * {
    margin: 8px 5px;
  }
}

.join {
  position: absolute;
  bottom: 20px;
  width: 100%;
}
</style>

<style scoped>
.CourseBar >>> .ivu-menu {
  height: 100vh;
}
</style>