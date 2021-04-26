<template>
      <div>
            <div class="main_s">
                  <div>
                        <div class="course">
                              <course-bar :type="'Tea'" @select="getView" :CourseData="CourseData.course" :subscription="CourseData.subscription" />
                              <div class="zw2"></div>
                              <router-view @update="refresh" :type="'Tea'" @addPaper="addPaper" :data="CourseData.course" :PaperList="CourseData.paperList" :key="key" :name="view" />
                        </div>
                  </div>
            </div>
      </div>
</template>

<script>
import Bus from "../js/Bus";
import CourseBar from "../components/CourseBar.vue";
import { getCourse } from "../js/api/Course";
export default {
  name: "CourseTea",
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

    Bus.$on("delect", (myMsg) => {
      var newArr = this.CourseData.paperList.reduce((total, current) => {
        current.id !== myMsg.id && total.push(current);
        return total;
      }, []);
      this.CourseData.paperList = newArr;
    });
  },
  watch: {
    $route: function () {},
  },
};
</script>

<style lang="less" scoped>
@import "../css/main.less";
.course {
  min-height: 96vh;
  width: 100vw;
  display: flex;
  // flex-wrap: wrap;
}

.zw2 {
  min-width: 240px;
  height: 96vh;
}
</style>