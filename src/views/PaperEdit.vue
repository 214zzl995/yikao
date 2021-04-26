<template>
      <div>
            <div class="main_s">
                  <div class="mainBody">
                        <div class="MainPlaceholder"></div>
                        <Tabs class="Tab" :animated="animated">
                              <Spin style="z-index:999" size="large" fix v-if="spinShow"></Spin>
                              <TabPane label="单选题" icon="md-checkmark-circle">
                                    <div class="Anchor" id="anchor">
                                          <span v-for="(item,index) in PaperData.singleChoiceList" @click="test('#singleChoice_'+index)"> {{index+1}}</span>
                                    </div>
                                    <div class="qAt">
                                          <div class="question">
                                                
                                                <single-choice v-for="(item,index) in PaperData.singleChoiceList" :data="item" :key="index" :index="index" />
                                                
                                          </div>
                                          
                                          <div class="table">
                                                <question-table :type="'singleChoice'" :type1="'ques2'" :selectData="PaperData.singleChoiceList" @select="addQuestion(0, arguments)" />
                                          </div>
                                    </div>
                              </TabPane>
                              <TabPane label="多选题" icon="md-checkbox">
                                    <div class="Anchor" id="anchor">
                                          <span v-for="(item,index) in PaperData.multipleChoiceList" @click="test('#multipleChoice_'+index)"> {{index+1}}</span>
                                    </div>
                                    <div class="qAt">
                                          <div class="question">
                                                <multiple-choice v-for="(item,index) in PaperData.multipleChoiceList" :data="item" :key="index" :index="index" />
                                          </div>
                                          <div class="table">
                                                <question-table :type="'multipleChoice'" :type1="'ques2'" :selectData="PaperData.multipleChoiceList" @select="addQuestion(1, arguments)" />
                                          </div>
                                    </div>
                              </TabPane>
                              <TabPane label="判断题" icon="md-close-circle">
                                    <div class="Anchor" id="anchor">
                                          <span v-for="(item,index) in PaperData.judgmentList" @click="test('#judgment_'+index)"> {{index+1}}</span>
                                    </div>
                                    <div class="qAt">
                                          <div class="question">
                                                <judgment v-for="(item,index) in PaperData.judgmentList" :data="item" :key="index" :index="index" />
                                                
                                          </div>
                                          <div class="table">
                                                <question-table :type="'judgment'" :type1="'ques2'" :selectData="PaperData.judgmentList" @select="addQuestion(2, arguments)" />
                                          </div>
                                    </div>
                              </TabPane>
                              <TabPane label="填空题" icon="md-code-download">
                                    <div class="Anchor" id="anchor">
                                          <span v-for="(item,index) in PaperData.blanksList" @click="test('#blanks_'+index)"> {{index+1}}</span>
                                    </div>
                                    <div class="qAt">
                                          <div class="question">
                                                <blanks v-for="(item,index) in PaperData.blanksList" :data="item" :key="index" :index="index" />
                                          </div>
                                          <div class="table">
                                                <question-table :type="'blanks'" :type1="'ques2'" :selectData="PaperData.blanksList" @select="addQuestion(3, arguments)" />
                                          </div>
                                          
                                    </div>
                              </TabPane>
                              <TabPane label="简答题" icon="md-clipboard">
                                    <div class="Anchor" id="anchor">
                                          <span v-for="(item,index) in PaperData.briefList" @click="test('#brief_'+index)"> {{index+1}}</span>
                                    </div>
                                    <div class="qAt">
                                          <div class="question">
                                                <brief v-for="(item,index) in PaperData.briefList" :data="item" :key="index" :index="index" />
                                                
                                          </div>
                                          <div class="table">
                                                <question-table :type="'brief'" :type1="'ques2'" :selectData="PaperData.briefList" @select="addQuestion(4, arguments)" />
                                          </div>
                                    </div>
                              </TabPane>
                              
                              <TabPane label="预览" icon="ios-paper-outline">
                                    <div class="Paper" v-if="PaperData.paper!=null">
                                          <div class="Anchor" id="anchor">
                                                <span @click="test('#jmd')">
                                                      <Icon type="md-home" />
                                                </span>
                                                <span v-for="(item,index) in PaperData.singleChoiceList" @click="test('#paper_'+parseInt(index))">
                                                      {{index+1}}
                                                </span>
                                                <span v-for="(item,index) in PaperData.multipleChoiceList" @click="test('#paper_'+parseInt(PaperData.singleChoiceList.length+index))">
                                                      {{PaperData.singleChoiceList.length+index+1}}
                                                </span>
                                                <span v-for="(item,index) in PaperData.judgmentList" @click="test('#paper_'+parseInt(PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index))">
                                                      {{PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index+1}}
                                                </span>
                                                <span v-for="(item,index) in PaperData.blanksList" @click="test('#paper_'+parseInt(PaperData.judgmentList.length+PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index))">
                                                      {{PaperData.judgmentList.length+PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index+1}}
                                                </span>
                                                <span v-for="(item,index) in PaperData.briefList" @click="test('#paper_'+parseInt(PaperData.blanksList.length+PaperData.judgmentList.length+PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index))">
                                                      {{PaperData.blanksList.length+PaperData.judgmentList.length+PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index+1}}
                                                </span>
                                          </div>
                                          <div>
                                                <p id="jmd" class="paperTitle">{{PaperData.paper.paperName}}</p>
                                                <p class="paperProbably">考试时间：{{PaperData.paper.timeLimit}}分钟，总分：{{getTotal()}}</p>
                                          </div>
                                          <div class="questionDiv">
                                                
                                                <div v-show="PaperData.singleChoiceList.length!=0">
                                                      <p class="questionTitle">一、选择题</p>
                                                      <single-choice v-for="(item,index) in PaperData.singleChoiceList" :type="'paper'" :data="item" :key="index" :index="index" :fraction="PaperData.singleChoiceFraction/PaperData.singleChoiceList.length" />
                                                </div>
                                                <div v-show="PaperData.multipleChoiceList.length!=0">
                                                      <p class="questionTitle">二、多项选择</p>
                                                      <multiple-choice v-for="(item,index) in PaperData.multipleChoiceList" :type="'paper'" :data="item" :key="index" :index="PaperData.singleChoiceList.length+index" :fraction="PaperData.multipleChoiceFraction/PaperData.multipleChoiceList.length" />
                                                </div>
                                                <div v-show="PaperData.judgmentList.length!=0">
                                                      <p class="questionTitle">三、判断题</p>
                                                      <judgment v-for="(item,index) in PaperData.judgmentList" :type="'paper'" :data="item" :key="index" :index="PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index" :fraction="PaperData.judgmentFraction/PaperData.judgmentList.length" />
                                                </div>
                                                <div v-show="PaperData.blanksList.length!=0">
                                                      <p class="questionTitle">四、填空题</p>
                                                      <blanks v-for="(item,index) in PaperData.blanksList" :type="'paper'" :data="item" :key="index" :index="PaperData.judgmentList.length+PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index" :fraction="PaperData.blanksFraction/PaperData.blanksList.length" />
                                                </div>
                                                <div v-show="PaperData.briefList.length!=0">
                                                      <p class="questionTitle">五、简答题</p>
                                                      <brief v-for="(item,index) in PaperData.briefList" :type="'paper'" :data="item" :key="index" :index="PaperData.blanksList.length+PaperData.judgmentList.length+PaperData.multipleChoiceList.length+PaperData.singleChoiceList.length+index" :fraction="PaperData.briefFraction/PaperData.briefList.length" />
                                                </div>
                                          </div>
                                    </div>
                              </TabPane>
                              
                              <TabPane label="设置" icon="ios-cog-outline">
                                    <div class="PaperSetting">
                                          <Form ref="PaperData" :model="PaperData" label-position="left" :rules="ruleCustom" :label-width="150" v-if="PaperData.paper!=null">
                                                <FormItem prop="id" label="试卷编号">
                                                      <Input style="width: 200px" disabled maxlength="30" v-model="PaperData.paper.id"></Input>
                                                </FormItem>
                                                <FormItem prop="PapereTitle" label="试卷名称">
                                                      <Input style="width: 400px" maxlength="30" v-model="PaperData.paper.paperName" placeholder="编辑试卷名称"></Input>
                                                </FormItem>
                                                <FormItem prop="TimeLimit" label="考试时间(分钟)">
                                                      <InputNumber style="width: 400px" :max="999" :min="1" v-model="PaperData.paper.timeLimit"></InputNumber>
                                                </FormItem>
                                                <FormItem prop="TimeLimit" :label="'单项选择分值('+PaperData.singleChoiceList.length +'题)'">
                                                      <InputNumber style="width: 400px" :max="999" :min="0" v-model="PaperData.singleChoiceFraction" :disabled="PaperData.singleChoiceList.length==0"></InputNumber>
                                                      
                                                </FormItem>
                                                <FormItem prop="TimeLimit" :label="'多项选择分值('+PaperData.multipleChoiceList.length+'题)'">
                                                      <InputNumber style="width: 400px" :max="999" :min="0" v-model="PaperData.multipleChoiceFraction" :disabled="PaperData.multipleChoiceList.length==0"></InputNumber>
                                                      
                                                </FormItem>
                                                <FormItem prop="TimeLimit" :label="'判断题分值('+PaperData.judgmentList.length+'题)'">
                                                      <InputNumber style="width: 400px" :max="999" :min="0" v-model="PaperData.judgmentFraction" :disabled="PaperData.judgmentList.length==0"></InputNumber>
                                                      
                                                </FormItem>
                                                <FormItem prop="TimeLimit" :label="'填空题分值('+PaperData.blanksList.length+'题)'">
                                                      <InputNumber style="width: 400px" :max="999" :min="0" v-model="PaperData.blanksFraction" :disabled="PaperData.blanksList.length==0"></InputNumber>
                                                      
                                                </FormItem>
                                                <FormItem prop="TimeLimit" :label="'简答题分值('+ PaperData.briefList.length+'题)'">
                                                      <InputNumber style="width: 400px" :max="999" :min="0" v-model="PaperData.briefFraction" :disabled="PaperData.briefList.length==0"></InputNumber>
                                                      
                                                </FormItem>
                                                
                                                <FormItem style="">
                                                      <Button @click="updatePaper" size="large" type="primary">提交</Button>
                                                      <Button @click="$router.go(-1);" size="large" style="margin-left: 15px">取消</Button>
                                                </FormItem>
                                          </Form>
                                    </div>
                              </TabPane>
                        </Tabs>
                        
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
import QuestionTable from "../components/QuestionTable.vue";
import { getPaper, update } from "../js/api/Paper";
export default {
  name: "PaperEdit",
  components: {
    SingleChoice,
    MultipleChoice,
    Judgment,
    Blanks,
    Brief,
    QuestionTable,
  },
  computed: {},
  data() {
    return {
      PaperId: -1,
      PaperData: {},
      animated: false,
      scrollTop: 0,
      Single: [],
      Blanks: [],
      Brief: [],
      Judgment: [],
      Multiple: [],
      ruleCustom: {
        name: [
          {
            required: true,
            message: "The name cannot be empty",
            trigger: "blur",
          },
        ],
      },
      spinShow: true,
    };
  },
  methods: {
    getPapers() {
      getPaper(this.PaperId).then((res) => {
        if (res.paper.aging != null) {
          res.paper.aging = new Date(res.paper.aging);
        }
        this.PaperData = res;
        this.$forceUpdate();
        this.spinShow = false;
      });
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
    handleScroll() {
      var scrollTop =
        window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop;
      var anchor = document.getElementById("anchor");
      var a = scrollTop + 100;
    },
    addQuestion(index, a) {
      if (index == 0) {
        this.PaperData.singleChoiceList = a[0];
      } else if (index == 1) {
        this.PaperData.multipleChoiceList = a[0];
      } else if (index == 2) {
        this.PaperData.judgmentList = a[0];
      } else if (index == 3) {
        this.PaperData.blanksList = a[0];
      } else if (index == 4) {
        this.PaperData.briefList = a[0];
      }
    },
    unique(arr) {
      const res = new Map();
      return arr.filter((arr) => !res.has(arr.id) && res.set(arr.id, 1));
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
    getIndex() {
      this.index++;
      return this.index;
    },
    updatePaper() {
      let paperData = {
        blanks: [],
        brief: [],
        judgment: [],
        multipleChoice: [],
        singleChoice: [],
        blanksFraction: this.PaperData.blanksFraction,
        briefFraction: this.PaperData.briefFraction,
        judgmentFraction: this.PaperData.judgmentFraction,
        multipleChoiceFraction: this.PaperData.multipleChoiceFraction,
        singleChoiceFraction: this.PaperData.singleChoiceFraction,
      };

      for (let i = 0; i < this.PaperData.blanksList.length; i++) {
        paperData.blanks.push(this.PaperData.blanksList[i].id);
      }

      for (let i = 0; i < this.PaperData.briefList.length; i++) {
        paperData.brief.push(this.PaperData.briefList[i].id);
      }

      for (let i = 0; i < this.PaperData.judgmentList.length; i++) {
        paperData.judgment.push(this.PaperData.judgmentList[i].id);
      }

      for (let i = 0; i < this.PaperData.multipleChoiceList.length; i++) {
        paperData.multipleChoice.push(this.PaperData.multipleChoiceList[i].id);
      }

      for (let i = 0; i < this.PaperData.singleChoiceList.length; i++) {
        paperData.singleChoice.push(this.PaperData.singleChoiceList[i].id);
      }

      this.PaperData.paper.questionData = JSON.stringify(paperData);
      // this.PaperData.paper.agingEnd = new Date(
      //   this.PaperData.paper.agingEnd
      // ).format("yyyy-MM-dd HH:mm:ss");
      // this.PaperData.paper.agingBegin = new Date(
      //   this.PaperData.paper.agingBegin
      // ).format("yyyy-MM-dd HH:mm:ss");

      update(this.PaperId, this.PaperData.paper).then((res) => {
        this.$Message.success("数据更新成功");
      });
    },
    changeDate(date) {
      console.log(date);
      this.PaperData.paper.aging = date;
    },
  },
  mounted() {
    let urlParams = this.$route.query;
    const { PaperId } = urlParams;
    this.PaperId = PaperId;
    this.getPapers();
    //     window.addEventListener("scroll", this.handleScroll);
  },
  watch: {},
  beforeDestroy() {
    // 在组件生命周期结束的时候要销毁监听事件，否则其他页面也会使用这个监听事件
    //     window.removeEventListener("scroll", this.handleScroll);
  },
};
</script>

<style lang="less" scoped>
@import "../css/main.less";

.Tab {
  padding: 18px;
}
.question {
  display: flex;
  flex-direction: column;
  width: 50%;
}
.table {
  position: sticky;
  width: 50%;
  top: 170px;
  height: 530px;
}
.qAt {
  display: flex;
  flex-wrap: wrap;
  overflow: visible;
}

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

.paperProbably {
  margin-top: 10px;
  color: #808695;
}
.Paper {
  display: flex;
  flex-direction: column;
}
.questionDiv {
  width: 70%;
}
.questionTitle {
  font-size: 17px;
  font-weight: 600;
  margin: 20px 0;
  color: #17233d;
}

.PaperSetting {
  display: flex;
  justify-content: center;
  margin-top: 40px;
}

.questionNumber {
  margin-left: 10px;
  font-weight: 500;
}
</style>


<style scoped>
div >>> .ivu-tabs-tabpane {
  overflow: visible;
}
div >>> .ivu-tabs {
  overflow: visible;
}
</style>