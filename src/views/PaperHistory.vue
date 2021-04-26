<template>
      <div class="main_s">
            <div class="mainBody">
                  <div class="MainPlaceholder"></div>
                  <div class="Paper" v-if="PaperData.paper!=null">
                        <div class="Anchor" id="anchor">
                              <span @click="test('#jmd')">
                                    <Icon type="md-home" />
                              </span>
                              <span v-for="(item,index) in PaperData.singleChoiceList" @click="test('#paper_'+parseInt(index))" :class="{'no':item.answer !=myAnswer.singleChoice[item.id].answer}">
                                    {{index+1}}
                              </span>
                              <span v-for="(item,index) in PaperData.multipleChoiceList" @click="test('#paper_'+parseInt(PaperData.singleChoiceList.length+index))" :class="{'no':item.answer !=myAnswer.multipleChoice[item.id].answer}">
                                    {{PaperData.singleChoiceList.length+index+1}}
                              </span>
                              <span v-for="(item,index) in PaperData.judgmentList" @click="test('#paper_'+parseInt(PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index))" :class="{'no':item.answer !=myAnswer.judgment[item.id].answer}">
                                    {{PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index+1}}
                              </span>
                              <span v-for="(item,index) in PaperData.blanksList" @click="test('#paper_'+parseInt(PaperData.judgmentList.length+PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index))" :class="{'no':item.answer !=myAnswer.blanks[item.id].answer}">
                                    {{PaperData.judgmentList.length+PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index+1}}
                              </span>
                              <span v-for="(item,index) in PaperData.briefList" @click="test('#paper_'+parseInt(PaperData.blanksList.length+PaperData.judgmentList.length+PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index))" :class="{'no':item.answer !=myAnswer.brief[item.id].answer}">
                                    {{PaperData.blanksList.length+PaperData.judgmentList.length+PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index+1}}
                              </span>
                        </div>
                        <div>
                              <p id="jmd" class="paperTitle">{{PaperData.paper.paperName}}</p>
                              <p class="paperProbably">考试时间：{{PaperData.paper.timeLimit}}分钟，总分：{{getTotal()}}</p>
                              <p class="paperProbably">考试得分 ：{{fraction}}</p>
                        </div>
                        <div class="questionDiv">
                              
                              <div v-show="PaperData.singleChoiceList.length!=0">
                                    <p class="questionTitle">一、选择题</p>
                                    <single-choice v-for="(item,index) in PaperData.singleChoiceList" :type="'paper'" :data="item" :key="index" :index="index" :fraction="PaperData.singleChoiceFraction/PaperData.singleChoiceList.length" :Danswer="myAnswer.singleChoice[item.id]" />
                              </div>
                              <div v-show="PaperData.multipleChoiceList.length!=0">
                                    <p class="questionTitle">二、多项选择</p>
                                    <multiple-choice v-for="(item,index) in PaperData.multipleChoiceList" :type="'paper'" :data="item" :key="index" :index="PaperData.singleChoiceList.length+index" :fraction="PaperData.multipleChoiceFraction/PaperData.multipleChoiceList.length" :Danswer="myAnswer.multipleChoice[item.id]" />
                              </div>
                              <div v-show="PaperData.judgmentList.length!=0">
                                    <p class="questionTitle">三、判断题</p>
                                    <judgment v-for="(item,index) in PaperData.judgmentList" :type="'paper'" :data="item" :key="index" :index="PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index" :fraction="PaperData.judgmentFraction/PaperData.judgmentList.length" :Danswer="myAnswer.judgment[item.id]" />
                              </div>
                              <div v-show="PaperData.blanksList.length!=0">
                                    <p class="questionTitle">四、填空题</p>
                                    <blanks v-for="(item,index) in PaperData.blanksList" :type="'paper'" :data="item" :key="index" :index="PaperData.judgmentList.length+PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index" :fraction="PaperData.blanksFraction/PaperData.blanksList.length" :Danswer="myAnswer.blanks[item.id]" />
                              </div>
                              <div v-show="PaperData.briefList.length!=0">
                                    <p class="questionTitle">五、简答题</p>
                                    <brief v-for="(item,index) in PaperData.briefList" :type="'paper'" :data="item" :key="index" :index="PaperData.blanksList.length+PaperData.judgmentList.length+PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index" :fraction="PaperData.briefFraction/PaperData.briefList.length" :Danswer="myAnswer.brief[item.id]" />
                              </div>
                        </div>
                  </div>
            </div>
            
      </div>
</template>

<script>
import Blanks from "../components/Question/Blanks.vue";
import Brief from "../components/Question/Brief.vue";
import Judgment from "../components/Question/Judgment.vue";
import MultipleChoice from "../components/Question/MultipleChoice.vue";
import SingleChoice from "../components/Question/SingleChoice.vue";
import { getPaperHistoryData } from "../js/api/Paper";
export default {
  name: "PaperHistory",
  components: { SingleChoice, MultipleChoice, Judgment, Blanks, Brief },
  computed: {},
  data() {
    return {
      PaperData: {},
      historyId: this.$route.params.historyId,
      myAnswer: {},
      fraction: 0,
    };
  },
  methods: {
    getPapers() {
      getPaperHistoryData(this.historyId).then((res) => {
        this.myAnswer = JSON.parse(res.myAnswer.answer);
        this.PaperData = res.paperData;
        this.fraction = res.myAnswer.fraction;
      });
    },
    getTotal() {
      var data = this.PaperData;
      let b =
        data.blanksFraction +
        data.briefFraction +
        data.judgmentFraction +
        data.multipleChoiceFraction +
        data.singleChoiceFraction;
      return b;
    },
    test(name) {
      history.replaceState(null, null, name);
      var data = name.replace("#", "");
      console.log(document.getElementById(data).offsetTop);
      document.querySelector(name).scrollIntoView({
        block: "end",
        behavior: "smooth",
      });
    },
  },
  mounted() {
    this.getPapers();
  },
  watch: {},
};
</script>

<style scoped lang="less">
@import "../css/main.less";

.Anchor {
  position: sticky;
  width: 100%;
  top: 60px;
  display: flex;
  flex-wrap: wrap;
  background: white;
  z-index: 12;
  > span {
    display: block;
    font-size: 15px;
    padding: 3px;
    width: 30px;
    text-align: center;
    border: 1px solid #dcdee2;
    margin: 2px;
    border-radius: 4px;
    cursor: pointer;
  }
  > span:active {
    background-color: rgba(46, 103, 222, 0.13);
    box-shadow: 0 1px 6px rgb(0 0 0 / 20%);
  }
}
.paperTitle {
  font-size: 25px;
  font-weight: 600;
  text-align: center;
  color: #17233d;
}

.Paper {
  display: flex;
  flex-direction: column;
}

.questionTitle {
  font-size: 17px;
  font-weight: 600;
  margin: 20px 0;
  color: #17233d;
}

.yes {
  border: 1px solid #8ce6b0;
  background-color: #edfff3;
}
.no {
  border: 1px solid #ffb08f;
  background-color: #ffefe6;
}

.questionDiv {
  width: 50%;
}
</style>