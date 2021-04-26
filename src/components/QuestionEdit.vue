<template>
      <div>
            <Form ref="blanks" :model="data" :rules="ruleInline">
                  <Modal v-if="type=='blanks' && data!= undefined" v-model="modal_show" width="600" :loading="modal_loading">
                        <p slot="header">
                              <span v-if="!add">修改题目id为: {{data.id}} 的填空题</span>
                              <span v-if="add">新增题目 </span>
                        </p>
                        <div>
                              <FormItem prop="question">
                                    <Input type="text" v-model="data.question">
                                    <span slot="prepend">问题</span>
                                    </Input>
                              </FormItem>
                              <FormItem prop="answer">
                                    <Input type="text" v-model="data.answer">
                                    <span slot="prepend">答案</span>
                                    </Input>
                              </FormItem>
                              
                        </div>
                        <div slot="footer">
                              <Button @click="cancel('blanks')">返回</Button>
                              <Button @click="editPost" type="primary">提交</Button>
                              
                        </div>
                  </Modal>
            </Form>
            
            <Form ref="brief" :model="data" :rules="ruleInline">
                  <Modal v-if="type=='brief' && data!= undefined" v-model="modal_show" width="600" :loading="modal_loading">
                        <p slot="header">
                              <span v-if="!add">修改题目id为: {{data.id}} 的简答题</span>
                              <span v-if="add">新增题目 </span>
                        </p>
                        <div>
                              <FormItem prop="question">
                                    <Input type="text" v-model="data.question">
                                    <span slot="prepend">问题</span>
                                    </Input>
                              </FormItem>
                              
                              <FormItem prop="answer">
                                    <span>答案：</span>
                                    <Input type="textarea" v-model="data.answer">
                                    
                                    </Input>
                              </FormItem>
                              
                        </div>
                        <div slot="footer">
                              <Button @click="cancel('brief')">返回</Button>
                              <Button @click="editPost" type="primary">提交</Button>
                              
                        </div>
                  </Modal>
            </Form>
            
            <Form ref="judgment" :model="data" :rules="ruleInline">
                  <Modal v-if="type=='judgment' && data!= undefined" v-model="modal_show" width="600" :loading="modal_loading">
                        <p slot="header">
                              <span v-if="!add">修改题目id为: {{data.id}} 的判断题</span>
                              <span v-if="add">新增题目 </span>
                        </p>
                        <div>
                              <FormItem prop="question">
                                    <Input type="text" v-model="data.question">
                                    <span slot="prepend">问题</span>
                                    </Input>
                              </FormItem>
                              
                              <FormItem prop="answer">
                                    <RadioGroup v-model="data.answer">
                                          <Radio label="true">正确</Radio>
                                          <Radio label="false">错误</Radio>
                                    </RadioGroup>
                              </FormItem>
                              
                        </div>
                        <div slot="footer">
                              <Button @click="cancel('judgment')">返回</Button>
                              <Button @click="editPost" type="primary">提交</Button>
                              
                        </div>
                  </Modal>
            </Form>
            
            <Form ref="multipleChoice" :model="data" :rules="ruleInline">
                  <Modal v-if="type=='multipleChoice' && data!= undefined" v-model="modal_show" width="600" :loading="modal_loading">
                        <p slot="header">
                              <span v-if="!add">修改题目id为: {{data.id}} 的多选题</span>
                              <span v-if="add">新增题目 </span>
                        </p>
                        <div>
                              <FormItem prop="question">
                                    <Input type="text" v-model="data.question">
                                    <span slot="prepend">问题</span>
                                    </Input>
                              </FormItem>
                              
                              <CheckboxGroup v-model="multipleChoice" class="radio">
                                    <Checkbox label="A" class="radioItem">
                                          <Input type="text" v-model="data.option1">
                                          <span slot="prepend">选项A</span>
                                          </Input>
                                    </Checkbox>
                                    <Checkbox label="B" class="radioItem">
                                          <Input type="text" v-model="data.option2">
                                          <span slot="prepend">选项B</span>
                                          </Input>
                                    </Checkbox>
                                    <Checkbox label="C" class="radioItem">
                                          <Input type="text" v-model="data.option3">
                                          <span slot="prepend">选项C</span>
                                          </Input>
                                    </Checkbox>
                                    <Checkbox label="D" class="radioItem">
                                          <Input type="text" v-model="data.option4">
                                          <span slot="prepend">选项D</span>
                                          </Input>
                                    </Checkbox>
                                    <Checkbox label="E" class="radioItem">
                                          <Input type="text" v-model="data.option5">
                                          <span slot="prepend">选项E</span>
                                          </Input>
                                    </Checkbox>
                                    <Checkbox label="F" class="radioItem">
                                          <Input type="text" v-model="data.option6">
                                          <span slot="prepend">选项F</span>
                                          </Input>
                                    </Checkbox>
                              </CheckboxGroup>
                              
                              <p>
                                    答案: <span class="answer" v-for="item in multipleChoice">{{item}}</span>
                              </p>
                              
                        </div>
                        <div slot="footer">
                              <Button @click="cancel('multipleChoice')">返回</Button>
                              <Button @click="editPost" type="primary">提交</Button>
                        </div>
                  </Modal>
            </Form>
            
            <Form ref="singleChoice" :model="data" :rules="ruleInline">
                  <Modal v-if="type=='singleChoice' && data!= undefined" v-model="modal_show" width="600" :loading="modal_loading">
                        <p slot="header">
                              <span v-if="!add">修改题目id为: {{data.id}} 的单选题</span>
                              <span v-if="add">新增题目 </span>
                        </p>
                        <div>
                              <FormItem prop="question">
                                    <Input type="text" v-model="data.question">
                                    <span slot="prepend">问题</span>
                                    </Input>
                              </FormItem>
                              
                              <RadioGroup v-model="data.answer" class="radio">
                                    <Radio label="A" class="radioItem">
                                          <Input type="text" v-model="data.option1">
                                          <span slot="prepend">选项A</span>
                                          </Input>
                                    </Radio>
                                    <Radio label="B" class="radioItem">
                                          <Input type="text" v-model="data.option2">
                                          <span slot="prepend">选项B</span>
                                          </Input>
                                    </Radio>
                                    <Radio label="C" class="radioItem">
                                          <Input type="text" v-model="data.option3">
                                          <span slot="prepend">选项C</span>
                                          </Input>
                                    </Radio>
                                    <Radio label="D" class="radioItem">
                                          <Input type="text" v-model="data.option4">
                                          <span slot="prepend">选项D</span>
                                          </Input>
                                    </Radio>
                                    <Radio label="E" class="radioItem">
                                          <Input type="text" v-model="data.option5">
                                          <span slot="prepend">选项E</span>
                                          </Input>
                                    </Radio>
                                    <Radio label="F" class="radioItem">
                                          <Input type="text" v-model="data.option6">
                                          <span slot="prepend">选项F</span>
                                          </Input>
                                    </Radio>
                              </RadioGroup>
                              
                              <p>
                                    答案: <span class="answer">{{data.answer}}</span>
                              </p>
                              
                        </div>
                        <div slot="footer">
                              <Button @click="cancel('singleChoice')">返回</Button>
                              <Button @click="editPost" type="primary">提交</Button>
                        </div>
                  </Modal>
                  
            </Form>
      </div>
</template>

<script>
import { CourseQuestionUpdate, CourseQuestionInsert } from "../js/api/Question";
export default {
  name: "QuestionEdit",
  props: ["type", "data", "editModal", "add"],
  components: {},
  computed: {
    getKey() {
      const modules = {
        question: "问题",
        answer: "答案",
        option1: "选项一",
        option2: "选项二",
        option3: "选项三",
        option4: "选项四",
        option5: "选项五",
        option6: "选项六",
      };
      let name = [];
      let da = Object.keys(this.data);
      for (let i in da) {
        if (modules[da[i]] != undefined) {
          name.push(modules[da[i]]);
        }
      }
      return name;
    },
  },
  data() {
    return {
      modal_loading: false,
      modal_show: false,
      editDate: {},
      ruleInline: {
        question: [
          {
            required: true,
            message: "请输入问题",
            trigger: "blur",
          },
        ],
        answer: [
          {
            required: true,
            message: "请输入问题",
            trigger: "blur",
          },
        ],
      },
      CourseId: -1,
      addStatus: false,
      multipleChoice: [],
    };
  },
  methods: {
    cancel(name) {
      this.modal_show = !this.modal_show;
      this.$refs[name].resetFields();
    },
    editPost() {
      this.modal_loading = true;
      if (!this.add) {
        CourseQuestionUpdate(this.type, this.CourseId, this.data).then(
          (res) => {
            if (res != undefined) {
              this.$emit("edit_ok");
              this.$Message.success("更新数据成功");
              this.modal_loading = false;
              this.modal_show = false;
            }
          }
        );
      } else {
        if (this.type == "multipleChoice") {
          this.data.answer = JSON.stringify(this.multipleChoice);
        }
        CourseQuestionInsert(this.type, this.CourseId, this.data).then(
          (res) => {
            if (res != undefined) {
              this.$emit("edit_ok");
              this.$Message.success("插入数据成功");
              this.modal_loading = false;
              this.modal_show = false;
            }
          }
        );
      }
    },
    getCourseId() {
      let urlParams = this.$route.query;
      const { CourseId } = urlParams;
      this.CourseId = CourseId;
    },
  },
  mounted() {
    this.getCourseId();
  },
  watch: {
    editModal() {
      this.modal_show = !this.modal_show;
      this.getKey;
    },
  },
};
</script>

<style lang="less" scoped>
.radioItem {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin: 10px 0;
  > * {
    margin: 0 10px;
  }
}

.answer {
  text-align: 500;
}
.radio {
  width: 100%;
}
</style>