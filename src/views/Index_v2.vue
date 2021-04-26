<template>
      <div class="main_index">
            <div class="MainPlaceholder"></div>
            <div>
                  <Carousel class="carousel" autoplay :value="0" loop>
                        <CarouselItem v-for="(item,index) in caousel" :key="index">
                              <div class="demo-carousel">
                                    <img :src="item" alt="">
                              </div>
                        </CarouselItem>
                  </Carousel>
            </div>
            
            <div class="recommend">
                  <p class="title">推荐课程</p>
                  <div class="courseDiv">
                        <course-card v-for="(courseItem,index) in courseList" :CourseData="courseItem" :type="'card'" :index="index" :key="index" @delect="delect(index)" />
                  </div>
            </div>
            
      </div>
</template>

<script>
import CourseCard from "../components/CourseCard.vue";
import { getIndexCourse } from "../js/api/Course";
export default {
  name: "Index_v2",
  components: { CourseCard },
  computed: {},
  data() {
    return {
      courseList: [],
      caousel: [
        "http://static.basicedu.chaoxing.com/erya_new/87dfd2efdb59a8777814de86a459a110.jpg",
        "http://static.basicedu.chaoxing.com/erya_new/785976df6204ce2284094ed25ba3fff2.jpg",
        "http://static.basicedu.chaoxing.com/erya_new/b75c4417fd092ebe3ec72a6da69addd5.jpg",
      ],
    };
  },
  methods: {
    getIndexCourse() {
      getIndexCourse().then((res) => {
        this.courseList = res;
      });
    },
  },
  mounted() {
    const userD = this.$store.getters.userDetails;
    if (userD.avatar == null && userD.school == null && userD.qq == null) {
      this.$Modal.confirm({
        title: "提醒",
        content: "<p>您的用户信息未完善</p> <p>需要前往完善用户信息么 </p>",
        onOk: () => {
          this.$router.push({ name: "UserDetails" });
        },
        onCancel: () => {},
      });
    }
    this.getIndexCourse();
  },
  watch: {},
};
</script>

<style scoped lang="less">
@import "../css/main.less";

.demo-carousel {
  width: 100%;
  max-height: 100%;
  > img {
    max-width: 100vw;
    max-height: 100%;
  }
}

.carousel {
  max-width: 100%;
  max-height: 100%;
}

.recommend {
  width: 100%;
  padding: 10px;
  > .title {
    font-size: 50px;
    margin: 0 auto;
    font-weight: 600;
    text-align: center;
  }
}

.main_index {
  width: 100vw;
  height: 100vh;
  display: flex;
  flex-direction: column;
}
.courseDiv {
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
}
</style>