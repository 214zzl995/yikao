<template>
      <div class="questionCard" :class="{'cwQuestion':answer !=data.answer && status=='History'}" :id="type!='paper'?'judgment_'+index : 'paper_'+index" @mouseenter="conShow = true" @mouseleave="conShow = false">
            <p class="Size">
                  <span> {{index+1}}. </span>
                  <span class="Question">{{data.question}} </span>
                  <span class="fraction" v-show="fraction!=undefined">（本题分值{{fraction}}）</span>
            </p>
            <div>
                  <RadioGroup class="radio" v-model="answer" @on-change="change">
                        <Radio :class="{'select':answer == 'true' && status=='examination'}" :disabled="status!='examination'" class="radioItem" label="true">
                              <Icon type="md-checkmark" />正确
                        </Radio>
                        <Radio :class="{'select':answer == 'false' && status=='examination'}" :disabled="status!='examination'" class="radioItem" label="false">
                              <Icon type="md-close" />错误
                        </Radio>
                  </RadioGroup>
            </div>
            <p v-if="status=='History'" class="answer">我的答案:{{answer}}</p>
            <p v-if="status!='examination'" class="answer">正确答案:{{data.answer}}</p>
            <div v-show="conShow && status=='PaperEdit'" class="con">
                  <Button @click="getDelect" icon="md-close"></Button>
            </div>
            <div class="addWrong" v-if="answer !=data.answer && status=='History'">
                  <Button @click="addWrong" type="primary" ghost>加入错题集</Button>
            </div>
      </div>
</template>

<script>
import Bus from "@/js/Bus.js";
import { addWrongQuestion } from "@/js/api/Question";
export default {
  name: "Judgment",
  props: ["data", "index", "fraction", "type", "Danswer"],
  components: {},
  computed: {},
  data() {
    return {
      status: this.$route.name,
      conShow: false,
      answer: this.Danswer == undefined ? "" : this.Danswer.answer,
    };
  },
  methods: {
    getDelect() {
      const data = {
        type: "judgment",
        id: this.data.id,
      };
      Bus.$emit("delect", data);
    },
    change() {
      const data = {
        method: "single",
        type: "judgment",
        index: this.index,
        topicId: this.data.id,
        answer: this.answer,
      };
      this.$emit("change", data);
    },
    addWrong() {
      const data = {
        userId: this.$store.getters.userDetails.userId,
        questionType: "judgment",
        questionId: this.data.id,
        answer: this.answer,
      };
      addWrongQuestion(data).then((res) => {
        this.$Message.success("加入错题集成功");
      });
    },
  },
  mounted() {},
  watch: {},
};
</script>

<style lang="less" scoped>
@import "@/css/Choice.less";
.questionCard {
  width: 100%;
  margin-top: 20px;
  border-radius: 7px;
  padding: 12px;
  padding-left: 30px;
}

.cwQuestion {
  border: #ff0000 dashed 1px;
}

.Size {
  font-size: 15px;
  font-weight: 700;
}

.con {
  position: absolute;
  top: 13px;
  left: -10px;
  z-index: 100;
}
.fraction {
  font-size: 14px;
  color: #808695;
  font-weight: 500;
}

.radioItem {
  padding: 7px;
  margin: 2px 0;
}

.addWrong {
  width: 100%;
  display: flex;
  margin-top: 12px;
  flex-direction: column-reverse;
}
</style>