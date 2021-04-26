<template>
      <div>
            <div class="main_s">
                  <div>
                        <div class="course">
                              <course-bar :type="'Stu'" @select="getView" :CourseData="CourseData.course" @add="addCourse" :subscription="CourseData.subscription" />
                              <div class="zw2"></div>
                              <router-view @update="refresh" :type="'Stu'" :data="CourseData.course" :PaperList="CourseData.paperList" :key="key" :name="view" />
                        </div>
                        
                  </div>
            </div>
      </div>
</template>

<script>
import CourseBar from "../components/CourseBar.vue";
import { getCourse } from "../js/api/Course";
export default {
  name: "Course",

  components: { CourseBar },
  computed: {
    key() {
      return this.$route.path;
    },
  },
  data() {
    return {
      CourseId: -1,
      CourseData: {},
      isRouterAlive: true,
      view: undefined,
    };
  },
  methods: {
    addCourse() {
      this.CourseData.subscription = true;
      this.$forceUpdate;
    },
    getView(name) {
      if (name == "Paper") {
        this.view = undefined;
      } else {
        this.view = name;
      }
    },
    reload() {
      this.isRouterAlive = false;
      this.$nextTick(() => {
        this.isRouterAlive = true;
      });
    },
    refresh(course) {
      this.CourseData.course = course;
      this.$forceUpdate;
    },
    addPaper(data) {
      this.CourseData.paperList.push(data);
    },
  },
  mounted() {
    let urlParams = this.$route.query;
    const { CourseId } = urlParams;
    this.CourseId = CourseId;
    getCourse(CourseId).then((res) => {
      this.CourseData = res;
    });
  },
  watch: {},
};
</script>

<style lang="less" scoped>
@import "../css/main.less";
.course {
  min-height: 96vh;
  width: 100vw;
  display: flex;
  flex-wrap: wrap;
}

.zw2 {
  width: 240px;
  height: 96vh;
}
</style>