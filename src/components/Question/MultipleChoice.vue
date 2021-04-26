<template>
      <div class="questionCard" :class="{'cwQuestion':answer !=data.answer && status=='History'}" :id="type!='paper'?'multipleChoice_'+index: 'paper_'+index" @mouseenter="conShow = true" @mouseleave="conShow = false">
            <p class="Size">
                  <span> {{index+1}}. </span>
                  <span class="Question">{{data.question}} </span>
                  <span class="fraction" v-show="fraction!=undefined">（本题分值{{fraction}}）</span>
            </p>
            <CheckboxGroup v-model="answer" class="radio" @on-change="change">
                  <Checkbox label="A" :class="{'select':answer.indexOf('A')!= -1 && status=='examination' }" :disabled="status!='examination'" class="radioItem" v-show="data.option1!=null && data.option1!=''">
                        <p v-show="data.option1!=''">
                              A. {{data.option1}}
                        </p>
                  </Checkbox>
                  <Checkbox label="B" :class="{'select':answer.indexOf('B')!= -1 && status=='examination' }" :disabled="status!='examination'" class="radioItem" v-show="data.option2!=null && data.option2!=''">
                        <p v-show="data.option2!=''">
                              B. {{data.option2}}
                        </p>
                  </Checkbox>
                  <Checkbox label="C" :class="{'select':answer.indexOf('C')!= -1 && status=='examination' }" :disabled="status!='examination'" class="radioItem" v-show="data.option3!=null && data.option3!='' ">
                        <p v-show="data.option3!=''">
                              C. {{data.option3}}
                        </p>
                  </Checkbox>
                  <Checkbox label="D" :class="{'select':answer.indexOf('D')!= -1 && status=='examination' }" :disabled="status!='examination'" class="radioItem" v-show="data.option4!=null && data.option4!=''">
                        <p v-show="data.option4!=''">
                              D.{{data.option4}}
                        </p>
                  </Checkbox>
                  <Checkbox label="E" :class="{'select':answer.indexOf('E')!= -1 && status=='examination' }" :disabled="status!='examination'" class="radioItem" v-show="data.option5!=null && data.option5!=''">
                        <p v-show="data.option5!=''">
                              E.{{data.option5}}
                        </p>
                  </Checkbox>
                  <Checkbox label="F" :class="{'select':answer.indexOf('F')!= -1 && status=='examination' }" :disabled="status!='examination'" class="radioItem" v-show="data.option6!=null && data.option6!=''">
                        <p v-show="data.option6!=''">
                              F.{{data.option6}}
                        </p>
                  </Checkbox>
            </CheckboxGroup>
            
            <p v-if="status=='examination'|| status=='History'" class="answer">
                  我的答案:
                  <span v-for="item in answer">{{item}}</span>
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
  name: "MultipleChoice",
  props: ["data", "index", "fraction", "type", "Danswer"],
  components: {},
  computed: {},
  data() {
    return {
      status: this.$route.name,
      conShow: false,
      answer:
        this.Danswer == undefined || this.Danswer.answer == ""
          ? []
          : this.Danswer.answer,
    };
  },
  methods: {
    getDelect() {
      const data = {
        type: "multipleChoice",
        id: this.data.id,
      };
      Bus.$emit("delect", data);
    },
    change() {
      const data = {
        method: "single",
        type: "multipleChoice",
        index: this.index,
        topicId: this.data.id,
        answer: this.answer,
      };
      this.$emit("change", data);
    },
    addWrong() {
      const data = {
        userId: this.$store.getters.userDetails.userId,
        questionType: "multipleChoice",
        questionId: this.data.id,
        answer: JSON.stringify(this.answer),
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
.radioItem {
  padding: 7px;
  margin: 2px 0;
}
</style>

<style scoped>
.radioItem >>> .ivu-checkbox-inner {
  display: none;
}

.radioItem >>> .ivu-checkbox {
  display: none;
}
</style>

<style>
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
  margin-top: 30px;
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