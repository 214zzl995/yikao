<template>
      <div class="qTb">         
            <Table ref="selection" class="table-css" :loading="loading" :columns="columns" :data="data" max-height="530" disabled-hover @on-selection-change="onSelect" @on-select-cancel="onSelectCancel" @on-select-all-cancel="onSelectCancelAll">
                  <template slot-scope="{ row, index }" slot="action">
                        <div class="link-type">
                              <Button type="text" size="small" v-show="type1 == 'ques1'" style="margin-right: 5px" @click="edit(index)">修改</Button>
                              <Button type="text" size="small" @click="remove(index)">删除</Button>
                        </div>
                  </template>
            </Table>
            <question-edit :type="type" :editModal="editModal" :data="editData" @edit_ok="getData" :add="addStatus" />
            <question-del :type="type" :data="delData" :delModal="delModal" @del_ok="getData" />
            <div class="page">
                  <div class="edit">
                        <Button @click="removeall" v-show="selection.length!=0 && type1 == 'ques1'" type="error">删除所选</Button>
                        <Button type="primary" v-show="type1 == 'ques1'" @click="edit(-1)">新增题目</Button>
                  </div>
                  <Page :total="total" size="small" @on-change="changePage" @on-page-size-change="changePageSize" show-elevator show-sizer show-total />
            </div>
      </div>
</template>

<script>
import Bus from "@/js/Bus.js";
import {
  CourseQuestion,
  CourseQuestionNumber,
  WrongQuestionNumber,
  WrongQuestion,
  deleteWrongQuestion,
} from "../js/api/Question";
import QuestionDel from "./QuestionDel.vue";
import QuestionEdit from "./QuestionEdit.vue";
export default {
  name: "QuestionTable",
  props: ["type", "type1", "selectData"],
  components: { QuestionEdit, QuestionDel },
  computed: {
    columns() {
      if (this.type1 == "ques2") {
        return this.sel;
      } else if (this.type1 == "wrongQuestion") {
        this.removeLast();
      }
      if (this.type == "blanks") {
        return this.blanks;
      } else if (this.type == "brief") {
        return this.brief;
      } else if (this.type == "judgment") {
        return this.judgment;
      } else if (this.type == "multipleChoice") {
        return this.multipleChoice;
      } else if (this.type == "singleChoice") {
        return this.singleChoice;
      }
    },
  },
  data() {
    return {
      blanks: [
        {
          type: "selection",
          width: 60,
          align: "center",
        },
        { title: "题目编号", key: "id", width: 150 },
        { title: "问题", key: "question" },
        { title: "答案", key: "answer" },
        { title: "最近更新时间", key: "updatetime", width: 150 },
        {
          title: "操作",
          slot: "action",
          width: 150,
          align: "center",
        },
      ],
      brief: [
        {
          type: "selection",
          width: 60,
          align: "center",
        },
        { title: "题目编号", key: "id", width: 150 },
        { title: "问题", key: "question" },
        { title: "答案", key: "answer" },

        { title: "最近更新时间", key: "updatetime", width: 150 },
        {
          title: "操作",
          slot: "action",
          width: 150,
          align: "center",
        },
      ],
      judgment: [
        {
          type: "selection",
          width: 60,
          align: "center",
        },
        { title: "题目编号", key: "id", width: 150 },
        { title: "问题", key: "question" },
        { title: "答案", key: "answer" },

        { title: "最近更新时间", key: "updatetime", width: 150 },
        {
          title: "操作",
          slot: "action",
          width: 150,
          align: "center",
        },
      ],
      multipleChoice: [
        {
          type: "selection",
          width: 60,
          align: "center",
        },
        { title: "题目编号", key: "id", width: 100 },
        { title: "问题", key: "question", width: 150 },
        { title: "选项一", key: "option1" },
        { title: "选项二", key: "option2" },
        { title: "选项三", key: "option3" },
        { title: "选项四", key: "option4" },
        { title: "选项五", key: "option5" },
        { title: "选项六", key: "option6" },
        { title: "答案", key: "answer", width: 130 },

        { title: "最近更新时间", key: "updatetime", width: 150 },
        {
          title: "操作",
          slot: "action",
          width: 150,
          align: "center",
        },
      ],
      singleChoice: [
        {
          type: "selection",
          width: 60,
          align: "center",
        },
        { title: "题目编号", key: "id", width: 100 },
        { title: "问题", key: "question", width: 250 },
        { title: "选项一", key: "option1" },
        { title: "选项二", key: "option2" },
        { title: "选项三", key: "option3" },
        { title: "选项四", key: "option4" },
        { title: "选项五", key: "option5" },
        { title: "选项六", key: "option6" },
        { title: "答案", key: "answer" },

        { title: "最近更新时间", key: "updatetime", width: 150 },
        {
          title: "操作",
          slot: "action",
          width: 150,
          align: "center",
        },
      ],
      sel: [
        {
          type: "selection",
          width: 60,
          align: "center",
        },
        { title: "题目编号", key: "id", width: 100 },
        { title: "问题", key: "question" },
      ],
      data: [],
      loading: true,
      CourseId: -1,
      total: -1,
      pageSize: 10,
      page: 1,
      editModal: false,
      editData: {},
      delData: [],
      delModal: false,
      selection: [],
      addStatus: false,
      flag: 0,
    };
  },
  methods: {
    getCourseId() {
      let urlParams = this.$route.query;
      const { CourseId } = urlParams;
      this.CourseId = CourseId;
    },
    getData() {
      if (this.type1 == "wrongQuestion") {
        WrongQuestion(
          this.type,
          this.addDateRange({
            offset: this.pageSize * this.page - this.pageSize,
            limit: this.pageSize,
          })
        ).then((res) => {
          this.data = res;
          this.getSize();
          this.loading = false;
          this.checkA();
        });
      } else {
        CourseQuestion(
          this.type,
          this.CourseId,
          this.addDateRange({
            offset: this.pageSize * this.page - this.pageSize,
            limit: this.pageSize,
          })
        ).then((res) => {
          this.data = res;
          this.getSize();
          this.loading = false;
          this.checkA();
        });
      }
    },
    changePageSize(size) {
      this.pageSize = size;
      this.loading = true;
      this.getData();
    },
    changePage(size) {
      this.page = size;
      this.loading = true;
      this.getData();
    },
    getSize() {
      if (this.type1 == "wrongQuestion") {
        WrongQuestionNumber(this.type).then((res) => {
          this.total = res;
        });
      } else {
        CourseQuestionNumber(this.type, this.CourseId).then((res) => {
          this.total = res;
        });
      }
    },
    edit(index) {
      this.editData = {};
      if (index != -1) {
        this.addStatus = false;
        this.editData = JSON.parse(JSON.stringify(this.data[index]));
      } else {
        this.addStatus = true;
        this.editData.publics = true;
      }
      this.editModal = !this.editModal;
    },
    remove(index) {
      if (this.type1 == "wrongQuestion") {
        deleteWrongQuestion(this.data[index].id).then((res) => {
          this.$Message.success("删除成功");
          this.data.splice(index, 1);
        });
      } else {
        this.delData = [];
        this.delModal = !this.delModal;
        this.delData.push(this.data[index]);
      }
    },
    removeall() {
      this.delData = [];
      this.delModal = !this.delModal;
      this.delData = this.selection;
      this.$emit("select", this.selection);
    },
    selectionChange(selection) {
      this.selection.push(selection);
      this.unique(this.selection);
    },
    onSelectCancel(selection, row) {
      let b = row.id;
      var newArr = this.selection.reduce((total, current) => {
        current.id !== b && total.push(current);
        return total;
      }, []);
      this.selection = newArr;
      this.$emit("select", this.selection);
    },
    onSelectCancelAll() {
      for (let i = 0; i < this.data.length; i++) {
        let b = this.data[i].id;
        var newArr = this.selection.reduce((total, current) => {
          current.id !== b && total.push(current);
          return total;
        }, []);
        this.selection = newArr;
        this.$emit("select", this.selection);
      }
    },
    onSelect(selection) {
      this.selection.push.apply(this.selection, selection);
      this.selection = this.unique();
      this.$emit("select", this.selection);
    },
    removeLast() {
      this.blanks.splice(this.blanks.length - 1, 1);
      this.brief.splice(this.brief.length - 1, 1);
      this.judgment.splice(this.judgment.length - 1, 1);
      this.multipleChoice.splice(this.multipleChoice.length - 1, 1);
      this.singleChoice.splice(this.singleChoice.length - 1, 1);
    },
    unique() {
      const arr = this.selection;
      const res = new Map();
      return arr.filter((arr) => !res.has(arr.id) && res.set(arr.id, 1));
    },
    checkA() {
      for (let i = 0; i < this.data.length; i++) {
        for (let a = 0; a < this.selection.length; a++) {
          if (this.data[i].id == this.selection[a].id) {
            this.data[i]._checked = true;
          }
        }
      }
    },
    addSelectData() {
      this.selection = JSON.parse(JSON.stringify(this.selectData));
      if (this.flag == 0) {
        this.getData();
      }

      this.checkA();
    },
    delectCheck() {},
    onDelect() {
      Bus.$on("delect", (myMsg) => {
        if (this.type1 != "ques1" && this.type == myMsg.type) {
          for (let i = 0; i < this.data.length; i++) {
            if (this.data[i].id == myMsg.id) {
              this.$refs.selection.toggleSelect(i);
              return;
            }
          }
          for (let i = 0; i < this.selectData.length; i++) {
            if (this.selectData[i].id == myMsg.id) {
              this.selection.splice(i, 1);
              this.$emit("select", this.selection);
              return;
            }
          }
        }
      });
    },
  },
  mounted() {
    this.getCourseId();
    this.getData();
    this.getSize();
    this.onDelect();
  },
  watch: {
    type() {
      this.getCourseId();
      this.getData();
      this.getSize();
    },
    selectData() {
      if (this.selectData != undefined) {
        this.addSelectData();
        this.flag++;
      }
    },
  },
};
</script>

<style lang="less" scoped>
.qTb {
  width: 100%;
}

.page {
  margin-top: 10px;
  display: flex;
  flex-direction: row-reverse;
  justify-content: space-between;
}

.edit {
  > * {
    margin-left: 10px;
  }
}
</style>


<style scoped>
/* 滚动条 */
.table-css >>> .ivu-table-overflowY::-webkit-scrollbar {
  width: 7px;
  height: 10px;
}
.table-css >>> .ivu-table-overflowY::-webkit-scrollbar-thumb {
  border-radius: 5px;
  background: rgb(236, 236, 236);
}

.table-css >>> .ivu-table-overflowX::-webkit-scrollbar {
  width: 7px;
  height: 10px;
}
.table-css >>> .ivu-table-overflowX::-webkit-scrollbar-thumb {
  border-radius: 5px;
  background: rgb(236, 236, 236);
}

.link-type >>> .ivu-input:focus {
  outline: none;
  box-shadow: 0 0 0 2px transparent;
}

/* 穿透进iview的input框 */
.link-type >>> .ivu-input {
  height: 45px;
}

.ivu-table >>> .ivu-table-fixed::before {
  height: 0px;
}

.ivu-table >>> .ivu-table-fixed-right::before {
  height: 0px;
}
</style>