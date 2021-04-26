<template>
      <div class="PaperMain">
            <div class="MainPlaceholder"></div>
            <div class="Paper">
                  <div class="PaperDiv">
                        <div class="title">
                              <div>
                                    <p>所有试卷</p>
                              </div>
                              <Button @click="addPaperShow" type="text" v-show="this.$route.name=='courseTea'">新增试卷</Button>
                        </div>
                        <paper-item :type="type" v-for="(paperItem , index) in PaperList" :key="index" :paper="paperItem" />
                  </div>
                  <Modal v-model="addPaperModal" width="360">
                        <p slot="header" style="text-align:center">
                              <span>新增试卷</span>
                        </p>
                        <div>
                              <Form ref="paper" :model="Paper" :rules="paperLine" inline>
                                    <FormItem prop="paperName">
                                          <Input style="width:328px" v-model="Paper.paperName" clearable>
                                          <span slot="prepend">卷名</span>
                                          </Input>
                                    </FormItem>
                              </Form>
                        </div>
                        
                        <div slot="footer">
                              <Button :loading="Modalloading" @click="addPaper" type="primary">确定</Button>
                              <Button @click="addPaperModal=!addPaperModal" type="text">取消</Button>
                        </div>
                        
                  </Modal>
            </div>
      </div>
</template>

<script>
import { addPaper } from "../js/api/Paper";
import PaperItem from "../components/PaperItem.vue";
export default {
  name: "PaperList",
  props: ["PaperList", "type"],
  components: { PaperItem },
  computed: {},
  data() {
    return {
      addPaperModal: false,
      Modalloading: false,
      Paper: {},
      CourseId: -1,
      paperLine: {
        PaperName: [
          {
            required: true,
            message: "请输入卷名",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    addPaper() {
      this.Modalloading = true;
      this.Paper.courseId = this.CourseId;
      this.$refs["paper"].validate((valid) => {
        console.log(valid);
        if (valid) {
          addPaper(this.Paper).then((res) => {
            if (res.id != undefined) {
              this.$Message.success("添加试卷成功");
              this.$emit("addPaper", res);
              this.addPaperModal = false;
            }
          });
        }
        this.Modalloading = false;
      });
    },
    addPaperShow() {
      this.addPaperModal = !this.addPaperModal;
      this.Paper = {};
      this.$refs["paper"].resetFields();
    },
  },
  mounted() {
    let urlParams = this.$route.query;
    const { CourseId } = urlParams;
    this.CourseId = CourseId;
  },
  watch: {},
};
</script>

<style lang="less" scoped>
@import "../css/main.less";

.title {
  width: 100%;
  height: 30px;
  font-size: 20px;
  font-weight: 600;
  display: flex;
  justify-content: space-between;
}
.PaperDiv {
  align-content: flex-start;
}
</style>