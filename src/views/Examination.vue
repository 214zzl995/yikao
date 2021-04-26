<template>
      <div class="main">
            <Spin size="large" class="spin" fix v-if="spinShow">
                  <Icon type="ios-loading" size=18 class="demo-spin-icon-load"></Icon>
                  <div>
                        <p>正在交卷</p>
                        <p>您可以自由关闭此标签了</p>
                        <router-link class="editUser" :to="{ name: 'index'}">或点击此处回到首页</router-link>
                  </div>
            </Spin>
            <div class="header" v-if="PaperData.paper!= undefined">
                  <div class="information">
                        <div class="paperName">
                              {{PaperData.paper.paperName}}
                        </div>
                        <div class="other">
                              <span>
                                    考试时间:{{PaperData.paper.timeLimit}}分钟
                              </span>
                        </div>
                  </div>
                  
                  
            </div>
            <div class="examMain whole" v-show="type" v-if="PaperData.paper!= undefined">
                  <div class="MainPlaceholder"></div>
                  <div v-show="PaperData.singleChoiceList.length!=0">
                        <p class="questionTitle">
                              <Icon type="md-checkmark-circle" /> &nbsp;&nbsp;选择题
                        </p>
                        <single-choice v-for="(item,index) in PaperData.singleChoiceList" @change="change" :type="'paper'" :data="item" :key="index" :index="index" :fraction="PaperData.singleChoiceFraction/PaperData.singleChoiceList.length" :Danswer="CarryData.singleChoice[item.id]" />
                  </div>
                  <div v-show="PaperData.multipleChoiceList.length!=0">
                        <p class="questionTitle">
                              <Icon type="md-checkbox" /> &nbsp;&nbsp;多项选择
                        </p>
                        <multiple-choice v-for="(item,index) in PaperData.multipleChoiceList" @change="change" :type="'paper'" :data="item" :key="index" :index="PaperData.singleChoiceList.length+index" :fraction="PaperData.multipleChoiceFraction/PaperData.multipleChoiceList.length" :Danswer="CarryData.multipleChoice[item.id]" />
                  </div>
                  <div v-show="PaperData.judgmentList.length!=0">
                        <p class="questionTitle">
                              <Icon type="md-close-circle" /> &nbsp;&nbsp;判断题
                        </p>
                        <judgment v-for="(item,index) in PaperData.judgmentList" @change="change" :type="'paper'" :data="item" :key="index" :index="PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index" :fraction="PaperData.judgmentFraction/PaperData.judgmentList.length" :Danswer="CarryData.judgment[item.id]" />
                  </div>
                  <div v-show="PaperData.blanksList.length!=0">
                        <p class="questionTitle">
                              <Icon type="md-code-download" /> &nbsp;&nbsp;填空题
                        </p>
                        <blanks v-for="(item,index) in PaperData.blanksList" @change="change" :type="'paper'" :data="item" :key="index" :index="PaperData.judgmentList.length+PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index" :fraction="PaperData.blanksFraction/PaperData.blanksList.length" :Danswer="CarryData.blanks[item.id]" />
                  </div>
                  <div v-show="PaperData.briefList.length!=0">
                        <p class="questionTitle">
                              <Icon type="md-clipboard" /> &nbsp;&nbsp;简答题
                        </p>
                        <brief v-for="(item,index) in PaperData.briefList" @change="change" :type="'paper'" :data="item" :key="index" :index="PaperData.blanksList.length+PaperData.judgmentList.length+PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index" :fraction="PaperData.briefFraction/PaperData.briefList.length" :Danswer="CarryData.brief[item.id]" />
                  </div>
                  
            </div>
            <div class="examMain single" v-show="!type">
                  <div class="MainPlaceholder"></div>
                  
            </div>
            
            <div class="navigation">
                  <div class="time">
                        <span style="width:70px">
                              剩余时间
                        </span>
                        <span>
                              {{total.minute}}
                        </span>
                        <span>
                              分
                        </span>
                        <span style="width:20px">
                              {{total.second}}
                        </span>
                        <span>
                              秒
                        </span>
                        <div class="tool">
                              
                              <Icon type="md-expand" @click="screenfullIco" />
                              <Icon type="md-exit" />
                              <Icon type="md-shuffle" @click="type=!type" />
                        </div>
                  </div>
                  <div class="Anchor" id="anchor">
                        <span @click="test('#jmd')" class="anchorSpan">
                              <Icon type="md-home" />
                        </span>
                        <span v-for="(item,index) in PaperData.singleChoiceList" :class="{'select':CarryData.singleChoice[item.id] !=undefined}" class="anchorSpan" @click="test('#paper_'+parseInt(index))">
                              {{index+1}}
                        </span>
                        <span v-for="(item,index) in PaperData.multipleChoiceList" :class="{'select':CarryData.multipleChoice[item.id] !=undefined}" class="anchorSpan" @click="test('#paper_'+parseInt(PaperData.singleChoiceList.length+index))">
                              {{PaperData.singleChoiceList.length+index+1}}
                        </span>
                        <span v-for="(item,index) in PaperData.judgmentList" :class="{'select':CarryData.judgment[item.id] !=undefined}" class="anchorSpan" @click="test('#paper_'+parseInt(PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index))">
                              {{PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index+1}}
                        </span>
                        <span v-for="(item,index) in PaperData.blanksList" :class="{'select': blankCheck(CarryData.blanks[item.id])}" class="anchorSpan" @click="test('#paper_'+parseInt(PaperData.judgmentList.length+PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index))">
                              {{PaperData.judgmentList.length+PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index+1}}
                        </span>
                        <span v-for="(item,index) in PaperData.briefList" :class="{'select': blankCheck(CarryData.brief[item.id])}" class="anchorSpan" @click="test('#paper_'+parseInt(PaperData.blanksList.length+PaperData.judgmentList.length+PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index))">
                              {{PaperData.blanksList.length+PaperData.judgmentList.length+PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index+1}}
                        </span>
                  </div>
                  <div class="assignment">
                        <Button @click="assignment" type="primary">提交试卷</Button>
                  </div>
            </div>
      </div>
</template>

<script>
import screenfull from "screenfull";
import { getExamination, assignment } from "../js/api/Paper";
import SingleChoice from "../components/Question/SingleChoice.vue";
import MultipleChoice from "../components/Question/MultipleChoice.vue";
import Judgment from "../components/Question/Judgment.vue";
import Blanks from "../components/Question/Blanks.vue";
import Brief from "../components/Question/Brief.vue";
export default {
  name: "Examination",
  components: { SingleChoice, MultipleChoice, Judgment, Blanks, Brief },
  computed: {
    alertCont() {
      return this.$websocket.getters.onEvent("examination");
    },
    Countdown() {
      var timestamp = Date.parse(new Date());
      return timestamp;
    },
  },
  data() {
    return {
      paperId: this.$route.params.paperId,
      PaperData: {},
      CarryData: {
        singleChoice: {},
        multipleChoice: {},
        judgment: {},
        blanks: {},
        brief: {},
      },
      maturity: -1,
      type: true,
      total: {
        minute: 0,
        second: 0,
      },
      fleg: 0,
      spinShow: false,
    };
  },
  methods: {
    getPaper() {
      getExamination(this.paperId).then((res) => {
        this.PaperData = res;
      });
    },
    initWebsocket() {
      const UserId = this.$store.getters.userDetails.userId;
      this.$websocket.dispatch(
        "EXAMINATION_INIT",
        "ws://127.0.0.1:8080/Examination/" +
          UserId +
          "/" +
          this.paperId +
          "/" +
          this.$store.getters.token
      );
      this.getTotal();
    },
    getTotal() {
      var _this = this;
      var time = window.setInterval(function () {
        var now = new Date().getTime();
        var totalHs = (_this.maturity.getTime() - now) / 1000;
        var minute = parseInt(totalHs / 60);
        var second = parseInt(totalHs % 60);
        if (minute == 0 && second < 0) {
          _this.spinShow = true;
        }
        _this.total.minute = minute;
        _this.total.second = second;
      }, 1000);
    },
    screenfullIco() {
      screenfull.toggle();
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
    change(data) {
      console.log(data);
      const answer = {
        topicId: data.topicId,
        answer: data.answer,
      };
      this.CarryData[data.type][data.topicId] = answer;
      this.fleg++;
      const re = JSON.parse(JSON.stringify(this.alertCont));
      re.data = JSON.stringify(this.CarryData);
      this.$websocket.dispatch("EXAMINATION_SEND", re);
    },
    blankCheck(data) {
      if (data != undefined) {
        if (data.answer != "") {
          return true;
        }
      } else {
        return false;
      }
    },
    assignment() {
      this.$Modal.confirm({
        title: "提醒",
        content: "<p>确定要提前提交试卷么</p>",
        onOk: () => {
          const re = {
            maturity: new Date(this.alertCont.maturity),
            data: JSON.stringify(this.CarryData),
          };

          assignment(this.paperId, re).then((res) => {
            this.spinShow = true;
          });
        },
        onCancel: () => {},
      });
    },
  },
  mounted() {
    this.initWebsocket();
    this.getPaper();
  },
  watch: {
    alertCont: function (a, b) {
      if (a !== b && a) {
        this.CarryData = JSON.parse(a.data);
        this.maturity = new Date(a.maturity);
      }
    },
  },
  beforeRouteEnter(to, from, next) {
    next();
  },
};
</script>

<style scoped lang="less">
@import "../css/main.less";
.time {
  width: 15vw;
  border-bottom: 1px solid #dcdee2;
  width: 100%;
  padding: 5px;
  > span {
    display: inline-block;
    width: 30px;
    text-align: center;
    font-size: 15px;
    font-weight: 600;
  }
}

.header {
  position: fixed;
  padding: 10px;
  width: 100vw;
  top: 0;
  display: flex;
  justify-content: space-between;
  height: 60px;
  align-items: center;
  z-index: 99;
  .Shadow();
  .bakC();
}

.information {
  flex: 1;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.paperName {
  font-size: 22px;
  font-weight: 600;
  width: 50%;
  text-align: end;
}

.other {
  width: 50%;
  height: 100%;
  display: flex;
  align-items: center;
  margin-left: 12px;
  > span {
    font-size: 13px;
    font-weight: 500;
  }
}

.examMain {
  min-height: 96vh;
  width: 60vw;
  border-radius: @radio;
  padding: 20px;
  .Shadow;
  .bakC();
}

.navigation {
  width: 18vw;
  min-height: 60vh;
  position: fixed;
  right: 20px;
  border-radius: @radio;
  padding: 10px;
  top: 100px;
  .Shadow;
  .bakC();
}

.tool {
  font-size: 20px;
  > i {
    width: 30px;
  }
}

.Anchor {
  width: 100%;
  top: 60px;
  display: flex;
  flex-wrap: wrap;
  background: white;
  z-index: 12;
  > span:active {
    background-color: rgba(46, 103, 222, 0.13);
    box-shadow: 0 1px 6px rgb(0 0 0 / 20%);
  }
}

.anchorSpan {
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

.select {
  border: 1px solid #abdcff;
  background-color: #f0faff;
}

.questionTitle {
  font-size: 20px;
  font-weight: 600;
  margin: 20px 0;
  color: #17233d;
}

.assignment {
  position: absolute;
  bottom: 10px;
  width: 100%;
  display: flex;
  justify-content: center;
}
.spin {
  z-index: 999;
}
.demo-spin-icon-load {
  animation: ani-demo-spin 1s linear infinite;
}
@keyframes ani-demo-spin {
  from {
    transform: rotate(0deg);
  }
  50% {
    transform: rotate(180deg);
  }
  to {
    transform: rotate(360deg);
  }
}
</style>

<style scoped>
.main >>> .ivu-spin-fix {
  position: fixed;
}
</style>