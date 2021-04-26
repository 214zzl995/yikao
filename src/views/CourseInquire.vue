<template>
      <div class="main_s">
            <div class="mainBody">
                  <div class="MainPlaceholder"></div>
                  <div class="searchBody">
                        <Input autofocus size="large" class="search" v-model="searchValue" search enter-button="搜索" @on-search="getCourse" placeholder="" />
                  </div>
                  <div class="MainPlaceholder"></div>
                  <div class="CourseList">
                        <course-card v-for="(courseItem,index) in CourseList" :CourseData="courseItem" @add="addMyCourse(index)" :Subscribe="SubscribeList[index]" :type="'list'" :index="index" :key="index" />
                  </div>
                  
            </div>
      </div>
</template>

<script>
import merge from "webpack-merge";
import CourseCard from "../components/CourseCard.vue";
import { selectCourse, getUserCourse } from "../js/api/Course";
export default {
  name: "CourseInquire",
  components: { CourseCard },
  computed: {},
  data() {
    return {
      searchValue: "",
      CourseList: [],
      SubscribeList: [],
    };
  },
  methods: {
    test() {},
    getCourse() {
      let urlParams = this.$route.query;
      const { value } = urlParams;
      if (this.searchValue != "" && this.searchValue != undefined) {
        selectCourse(this.addDateRange({ courseTitle: this.searchValue })).then(
          (res) => {
            this.CourseList = res;
            this.$router.push({
              query: merge(this.$route.query, { value: this.searchValue }),
            });
          }
        );
      }
    },
    Subscribe() {
      var this_ = this;
      let myCourse = [];
      this.SubscribeList = [];
      getUserCourse()
        .then((res) => {
          myCourse = res;
        })
        .then(function () {
          for (let i = 0; i < this_.CourseList.length; i++) {
            var temp1 = this_.CourseList[i];
            var flag = false;
            for (let a = 0; a < myCourse.length; a++) {
              var temp2 = myCourse[a];
              if (this_.deepCompare(temp1, temp2)) {
                flag = true;
              }
            }
            this_.SubscribeList.push(flag);
          }
        });
    },
    addMyCourse(index) {
      this.SubscribeList[index] = true;
      this.$forceUpdate();
    },
  },
  mounted() {
    let urlParams = this.$route.query;
    const { value } = urlParams;
    this.searchValue = value;
    this.getCourse();
    this.Subscribe();
  },
  watch: {
    $route(to, from) {
      let urlParams = this.$route.query;
      const { value } = urlParams;
      this.searchValue = value;
      this.getCourse();
      this.Subscribe();
    },
  },
};
</script>

<style lang="less" scoped>
@import "../css/main.less";

.searchBody {
  display: flex;
  margin-top: 20px;
  justify-content: center;
  align-items: center;
}
.search {
  width: 600px;
  height: 45px;
}
</style>

<style scoped>
.search >>> .ivu-input:focus {
  outline: none;
  box-shadow: 0 0 0 2px transparent;
}

/* 穿透进iview的input框 */
.search >>> .ivu-input {
  height: 45px;
}
</style>

