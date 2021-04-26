<template>
      <div class="questionCard" :class="{'cwQuestion':answer !=data.answer && status=='History'}" :id="type!='paper'?'blanks_'+index: 'paper_'+index" @mouseenter="conShow = true" @mouseleave="conShow = false">
            <p class="Size">
                  <span> {{index+1}}. </span>
                  
                  <span class="Question">{{data.question}} </span>
                  <span class="fraction" v-show="fraction!=undefined">（本题分值{{fraction}}）</span>
            </p>
            <p class="answer">
                  <input class="answerInput" v-model="answer" type="text" v-show="status=='examination'" v-bind:disabled="status=='PaperEdit'">
                  
            </p>
            
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
  name: "Blanks",
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
        type: "blanks",
        id: this.data.id,
      };
      Bus.$emit("delect", data);
    },
    change() {},
    addWrong() {
      const data = {
        userId: this.$store.getters.userDetails.userId,
        questionType: "blanks",
        questionId: this.data.id,
        answer: this.answer,
      };
      addWrongQuestion(data).then((res) => {
        this.$Message.success("加入错题集成功");
      });
    },
  },
  mounted() {},
  watch: {
    answer() {
      const data = {
        method: "single",
        type: "blanks",
        index: this.index,
        topicId: this.data.id,
        answer: this.answer,
      };
      this.$emit("change", data);
    },
  },
};
</script>

<style lang="less" scoped>
.questionCard {
  width: 100%;
  margin-top: 20px;
  border-radius: 7px;
  padding: 12px;
  padding-left: 30px;
  position: relative;
  z-index: 10;
}

.cwQuestion {
  border: #ff0000 dashed 1px;
}

.Size {
  font-size: 15px;
  font-weight: 700;
}

.answerInput {
  margin-top: 10px;
  width: 100%;
  font-size: 16px;
  border-top: 0;
  border-left: 0;
  border-right: 0;
  background: white;
  color: #515a6e;
  border-bottom: 2px solid #515a6e;
  outline: none;
}

.answerInput:focus {
  border-bottom: 2px solid #abdcff;
  box-shadow: 0 4px 10px -6px #abdcff;
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

.addWrong {
  width: 100%;
  display: flex;
  margin-top: 12px;
  flex-direction: column-reverse;
}
</style>

<style scoped>
</style>