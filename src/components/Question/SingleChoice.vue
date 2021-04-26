<template>
      <div class="questionCard" :class="{'cwQuestion':answer !=data.answer && status=='History'}" :id="type!='paper'?'singleChoice_'+index: 'paper_'+index" @mouseenter="conShow = true" @mouseleave="conShow = false">
            <p class="Size">
                  <span> {{index+1}}. </span>
                  <span class="Question">{{data.question}} </span>
                  <span class="fraction" v-show="fraction!=undefined">（本题分值{{fraction}}）</span>
            </p>
            <RadioGroup v-model="answer" class="radio" @on-change="change">
                  <Radio label="A" :class="{'select':answer == 'A' && status=='examination'}" :disabled="status!='examination'" class="radioItem" v-show="data.option1!='' && data.option1!=null">
                        <p>
                              A. {{data.option1}}
                        </p>
                  </Radio>
                  <Radio label="B" :class="{'select':answer == 'B' && status=='examination'}" :disabled="status!='examination'" class="radioItem" v-show="data.option2!='' && data.option2!=null">
                        <p>
                              B. {{data.option2}}
                        </p>
                  </Radio>
                  <Radio label="C" :class="{'select':answer == 'C' && status=='examination'}" :disabled="status!='examination'" class="radioItem" v-show="data.option3!='' && data.option3!=null">
                        <p>
                              C. {{data.option3}}
                        </p>
                  </Radio>
                  <Radio label="D" :class="{'select':answer == 'D' && status=='examination'}" :disabled="status!='examination'" class="radioItem" v-show="data.option4!='' && data.option4!=null">
                        <p>
                              D.{{data.option4}}
                        </p>
                  </Radio>
                  <Radio label="E" :class="{'select':answer == 'E' && status=='examination'}" :disabled="status!='examination'" class="radioItem" v-show="data.option5!='' && data.option5!=null">
                        <p>
                              E.{{data.option5}}
                        </p>
                  </Radio>
                  <Radio label="F" :class="{'select':answer == 'F' && status=='examination'}" :disabled="status!='examination'" class="radioItem" v-show="data.option6!='' && data.option6!=null">
                        <p>
                              F.{{data.option6}}
                        </p>
                  </Radio>
            </RadioGroup>
            
            <p v-if="status=='examination' || status=='History'" class="answer">
                  我的答案: {{answer}}
            </p>
            
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
  name: "SingleChoice",
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
        type: "singleChoice",
        id: this.data.id,
      };
      Bus.$emit("delect", data);
    },
    change() {
      const data = {
        method: "single",
        type: "singleChoice",
        index: this.index,
        topicId: this.data.id,
        answer: this.answer,
      };
      this.$emit("change", data);
    },
    addWrong() {
      const data = {
        userId: this.$store.getters.userDetails.userId,
        questionType: "singleChoice",
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
    Danswer() {
      if (this.Danswer != null) {
        console.log(this.Danswer);
      }
    },
  },
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

.answer {
  margin-top: 10px;
  font-weight: 600;
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

<style>
.ivu-radio {
  display: none;
}
</style>