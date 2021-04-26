<template>
      <div @mouseenter="conShow = true" @mouseleave="conShow = false">
            <div class="courseCard" v-if="type=='card'">
                  <my-sign v-show="jiao" />
                  <Card :padding="0">
                        <div class="courseMain" @click="main">
                              <img class="courseImg" :src="CourseData.courseImage" alt="">
                        </div>
                        <div v-show="conShow" class="con">
                              <Button @click="ExitCourse" type="text" ghost icon="md-close"></Button>
                        </div>
                  </Card>
                  <div class="nameDiv" @click="main">
                        <p class="name" :class="{'nameB':conShow == true}">{{CourseData.courseTitle}}</p>
                        <p>
                              {{CourseData.teacherName}}
                        </p>
                  </div>
            </div>
            <div v-if="type=='list'">
                  <Row type="flex" justify="center" class="courseList">
                        
                        <Col span="4">
                        <img class="courseImgList courseImg" :src="CourseData.courseImage" alt="">
                        </Col>
                        <Col span="11">
                        <p class="listTitle">
                              <span> {{CourseData.courseTitle}}</span>
                              <span class="Evaluation">
                                    <Icon style="color:#ff0000" type="md-thumbs-up" />{{CourseData.courseLike}}
                                    <Icon type="md-thumbs-down" />{{CourseData.stepStep}}
                              </span>
                        </p>
                        <p class="listTeacher">{{CourseData.teacherName}} </p>
                        <p>课程简介： {{CourseData.courseIntroduction}} </p>
                        </Col>
                        <Col span="2">
                        <Button v-if="!Subscribe" @click="addMyCourse" type="primary" size="large">加入课程</Button>
                        <Button v-if="Subscribe" type="primary" size="large" disabled>已加入该课程</Button>
                        </Col>
                  </Row>
                  
                  
            </div>
      </div>
</template>

<script>
import { exitUserCourse, addUserCourse } from "@/js/api/Course";
import MySign from "./MySign.vue";
export default {
  name: "CourseCard",
  props: ["CourseData", "index", "type", "Subscribe"],
  components: { MySign },
  computed: {},
  data() {
    return {
      conShow: false,
      jiao: this.CourseData.userId == this.$store.getters.userDetails.userId,
    };
  },
  methods: {
    main() {
      let routeUrl = null;
      if (this.jiao) {
        routeUrl = this.$router.resolve({
          name: "courseTea",
          query: { CourseId: this.CourseData.id },
        });
      } else {
        routeUrl = this.$router.resolve({
          name: "courseStu",
          query: { CourseId: this.CourseData.id },
        });
      }
      window.open(routeUrl.href, "_blank");
    },
    mainEnter() {
      this.conShow = true;
    },
    mainLeave() {
      this.conShow = false;
    },
    ExitCourse() {
      this.$Modal.confirm({
        title: "请问",
        content: "<p>确定要退出这门课程么</p>",
        okText: "确定",
        cancelText: "取消",
        onOk: () => {
          exitUserCourse(
            this.addDateRange({ courseId: this.CourseData.id })
          ).then((res) => {
            this.$emit("delect", this.index);
          });
        },
        onCancel: () => {},
      });
    },
    addMyCourse() {
      addUserCourse(this.CourseData).then((res) => {
        if (res.id != null) {
          this.$emit("add", this.index);
          this.$Message.success("加入课程成功");
        }
      });
    },
  },
  mounted() {},
  watch: {},
};
</script>

<style lang="less" scoped>
@import "../css/main.less";

.courseImg {
  width: 200px;
  height: 130px;
  object-fit: cover;
  flex: 1;
  border-radius: 5px;
}

.courseMain {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
}
.name {
  font-size: 18px;
  margin-top: 5px;
  font-weight: 600;
}

.courseCard {
  position: relative;
  cursor: pointer;
  > div {
    padding: 0;
  }
}
.nameDiv {
  margin-left: 5px;
  font-size: 13px;
}

.nameB {
  color: #3a8bff;
}

.con {
  position: absolute;
  top: 0;
  right: 0;
  z-index: 100;
}
.courseList {
  height: 140px;
  width: 100%;
  margin-top: 40px;
}

.courseImgList {
  height: 90%;
}

.listTitle {
  font-size: 30px;
  font-weight: 500;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.listTeacher {
  font-size: 16px;
  font-weight: 500;
}

.Evaluation {
  font-size: 20px;
  margin-right: 30px;
  > * {
    margin: 10px;
  }
}
</style>

<style>
</style>