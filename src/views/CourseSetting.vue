<template>
      <div class="PaperMain">
            <div class="MainPlaceholder"></div>
            <div class="Paper">
                  <div class="PaperDiv">
                        <Spin class="spin" fix size="large" v-if="loading"></Spin>
                        <div><span class="title">课程设置</span></div>
                        <div class="inputMain">
                              <Form ref="CourseData" :model="CourseData" :rules="ruleInline" label-position="left" :label-width="130" hide-required-mark>
                                    <FormItem prop="id" label="课程编号">
                                          <Input style="width: 200px" disabled maxlength="30" v-model="CourseData.id" placeholder="编辑课程名称"></Input>
                                    </FormItem>
                                    <FormItem prop="courseTitle" label="课程名称">
                                          <Input style="width: 400px" maxlength="30" v-model="CourseData.courseTitle" placeholder="编辑课程名称"></Input>
                                    </FormItem>
                                    <FormItem prop="teacherName" label="任课老师名称">
                                          <Input style="width: 400px" maxlength="30" v-model="CourseData.teacherName" placeholder="编辑课程名称"></Input>
                                    </FormItem>
                                    <FormItem prop="teacherName" label="课程状态">
                                          <Select v-model="CourseData.courseStatus" style="width: 400px" placeholder="Select your city">
                                                <Option :value="1">允许所有人加入</Option>
                                                <Option :value="2">不允许加入</Option>
                                          </Select>
                                    </FormItem>
                                    <FormItem label="是否允许用户私聊" prop="privates">
                                          <i-switch v-model="CourseData.privates">
                                                <span slot="open">开</span>
                                                <span slot="close">关</span>
                                          </i-switch>
                                    </FormItem>
                                    <FormItem label="课程简介" prop="privates">
                                          <Input style="width: 600px" v-model="CourseData.courseIntroduction" type="textarea" :autosize="{minRows: 2,maxRows: 6}" placeholder="Enter something..."></Input>
                                    </FormItem>
                                    
                                    <FormItem label="课程图片" prop="privates">
                                          <div class="uploadDiv">
                                                <upload-pic :type="'Course'" v-show="data.courseImage==CourseData.courseImage" @uploadImg="uploadImg" />
                                                <div @mouseenter="conShow = true" @mouseleave="conShow = false" v-show="data.courseImage!=CourseData.courseImage">
                                                      <img class="courseImage" :src="CourseData.courseImage" alt="">
                                                      <Button @click="exitImg" type="text" ghost icon="md-close">aaa</Button>
                                                </div>
                                          </div>
                                    </FormItem>
                                    
                                    <FormItem style="">
                                          <Button size="large" @click="sumbit" type="primary">提交</Button>
                                          <Button size="large" style="margin-left: 15px">取消</Button>
                                    </FormItem>
                              </Form>
                        </div>
                        
                  </div>
            </div>
      </div>
</template>

<script>
import { getCourse, updateCourse } from "@/js/api/Course";
import UploadPic from "../components/uploadPic.vue";
export default {
  name: "CourseSetting",
  props: ["data"],
  components: { UploadPic },
  computed: {},
  data() {
    return {
      CourseData: {},
      CourseId: -1,
      ruleInline: {
        courseTitle: [
          {
            required: true,
            message: "请输入课程名称",
            trigger: "blur",
          },
        ],
      },
      loading: false,
      conShow: false,
    };
  },
  methods: {
    getCourseData() {
      this.CourseData = JSON.parse(JSON.stringify(this.data));
    },
    getCourseId() {
      let urlParams = this.$route.query;
      const { CourseId } = urlParams;
      this.CourseId = CourseId;
    },
    sumbit() {
      this.loading = true;
      updateCourse(this.CourseId, this.CourseData)
        .then((res) => {
          this.$emit("update", res);
          this.loading = false;
          this.$Message.success("更新数据成功");
        })
        .catch((error) => {
          this.loading = false;
          this.$Message.error("更新数据失败");
        });
    },
    uploadImg(url) {
      this.CourseData.courseImage = url;
    },
    exitImg() {
      this.CourseData.courseImage = this.data.courseImage;
    },
  },
  mounted() {
    this.getCourseId();
    this.getCourseData();
  },
  watch: {},
};
</script>

<style lang="less" scoped>
@import "../css/main.less";

.PaperDiv {
  flex-direction: column;
}

.title {
  font-size: 20px;
  font-weight: 600;
}
.minTitle {
  font-size: 18px;
  margin-right: 100px;
}
.inputMain {
  margin-top: 30px;
}

.courseImage {
  height: 125px;

  object-fit: cover;
  border-radius: 5px;
}

.uploadDiv {
  display: flex;
  flex-wrap: nowrap;
}
.spin {
  border-radius: @radio;
}

.con {
  position: absolute;
  top: 0;
  right: 0;
  z-index: 100;
}
</style>

