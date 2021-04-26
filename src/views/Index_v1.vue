<template>
      <div class="main_s">
            <div class="mainBody">
                  <div class="MainPlaceholder"></div>
                  <div class="title">
                        <div class="header">
                              <p>我的课程 </p>
                              <Button v-show="role!='student'" @click="addCourseM = true" type="text">新增课程</Button>
                        </div>
                        <Divider />
                  </div>
                  <div class="courseDiv">
                        <course-card v-for="(courseItem,index) in courseList" :CourseData="courseItem" :type="'card'" :index="index" :key="index" @delect="delect(index)" />
                        <p class="NullCourse" v-show="NullCourse">您还没有添加课程，先去添加一门课程吧！
                        </p>
                  </div>
            </div>
            
            <Modal v-model="addCourseM" title="新建课程" @on-ok="addCourse" :loading="loading" width="800px">
                  <Form ref="CourseData" :model="course" :rules="ruleInline" label-position="left" :label-width="130" hide-required-mark>
                        <FormItem prop="courseTitle" label="课程名称">
                              <Input style="width: 400px" maxlength="30" v-model="course.courseTitle" placeholder=""></Input>
                        </FormItem>
                        <FormItem prop="teacherName" label="任课老师名称">
                              <Input style="width: 400px" maxlength="30" v-model="course.teacherName" placeholder=""></Input>
                        </FormItem>
                        <FormItem label="课程简介" prop="privates">
                              <Input style="width: 600px" v-model="course.courseIntroduction" type="textarea" :autosize="{minRows: 2,maxRows: 6}" placeholder=""></Input>
                        </FormItem>
                        <FormItem label="课程图片" prop="privates">
                              <div class="uploadDiv">
                                    <upload-pic :type="'Course'" v-show="picShow" @uploadImg="uploadImg" />
                                    <div @mouseenter="conShow = true" @mouseleave="conShow = false" v-show="!picShow">
                                          <img class="courseImage" :src="course.courseImage" alt="">
                                          <Button @click="picShow=true" type="text" icon="md-close"> 重新上传</Button>
                                    </div>
                              </div>
                        </FormItem>
                  </Form>
            </Modal>
      </div>
</template>

<script>
import { getUserCourse, addCourse } from "@/js/api/Course";
import CourseCard from "../components/CourseCard.vue";
import UploadPic from "../components/uploadPic.vue";
export default {
  name: "Index_v1",
  components: { CourseCard, UploadPic },
  computed: {},
  data() {
    return {
      courseList: [],
      NullCourse: false,
      role: this.$store.getters.roles[0],
      addCourseM: false,
      loading: false,
      course: {
        courseTitle: "",
        courseIntroduction: "",
        teacherName: "",
        courseImage: "",
      },
      ruleInline: {
        courseTitle: [
          {
            required: true,
            message: "请输入课程名称",
            trigger: "blur",
          },
        ],
      },
      picShow: true,
    };
  },
  methods: {
    getCourse() {
      getUserCourse().then((res) => {
        this.courseList = res;
        this.NoCourse();
      });
    },
    goto() {
      this.$router.push("/index");
    },
    delect(index) {
      this.courseList.splice(index, 1);
      this.NoCourse();
    },
    NoCourse() {
      if (this.courseList.length == 0) {
        this.NullCourse = true;
      }
    },
    test() {},
    addCourse() {
      this.loading = true;
      addCourse(this.course).then((res) => {
        this.courseList.push(res);
        this.$Message.success("课程添加成功");
        this.NullCourse = false;
      });
    },
    uploadImg(url) {
      this.course.courseImage = url;
      this.picShow = false;
    },
  },
  mounted() {
    this.getCourse();
  },
  watch: {},
};
</script>

<style scoped lang="less">
@import "../css/main.less";
.courseDiv {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  > .NullCourse {
    font-size: 20px;
    margin-left: 10px;
    font-weight: 600;
  }
}
.header {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
  padding: 0 20px;
  > p {
    font-size: 20px;
    margin-left: 10px;
    font-weight: 600;
  }
}

.uploadDiv {
  display: flex;
  flex-wrap: nowrap;
}

.courseImage {
  height: 125px;

  object-fit: cover;
  border-radius: 5px;
}
</style>