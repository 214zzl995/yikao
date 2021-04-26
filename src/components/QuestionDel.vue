<template>
      <div>
            <Modal v-model="modal_show" width="600" :loading="modal_loading">
                  <p slot="header" style="color:#f60;text-align:center">
                        <Icon type="ios-information-circle"></Icon>
                        <span>删除题目</span>
                  </p>
                  <div>
                        <Table class="table-css" height="400" :columns="columns" :data="data"></Table>
                  </div>
                  <div slot="footer">
                        <Button @click="cancel">返回</Button>
                        <Button @click="delPost" type="error">删除</Button>
                  </div>
            </Modal>
      </div>
</template>

<script>
import { CourseQuestionDelect } from "../js/api/Question";
import expandRow from "../components/table-expand";
export default {
  name: "QuestionDel",
  props: ["data", "delModal", "type"],
  components: { expandRow },
  computed: {},
  data() {
    return {
      modal_show: false,
      columns: [
        {
          type: "expand",
          width: 50,
          render: (h, params) => {
            return h(expandRow, {
              props: {
                row: params.row,
              },
            });
          },
        },
        {
          title: "编号",
          key: "id",
        },
        {
          title: "问题",
          key: "question",
        },
      ],
      CourseId: -1,
      modal_loading: false,
    };
  },
  methods: {
    cancel() {
      this.modal_show = !this.modal_show;
    },
    delPost() {
      this.modal_loading = true;
      let data = [];
      for (let i = 0; i < this.data.length; i++) {
        data.push(this.data[i].id);
      }
      CourseQuestionDelect(this.type, this.CourseId, data).then((res) => {
        this.$Notice.success({
          title: "删除成功",
          desc: "本次删除数据共" + res.length + "条",
        });
        this.modal_loading = false;
        this.modal_show = false;
        this.$emit("del_ok");
      });
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
    delModal() {
      this.modal_show = !this.modal_show;
      this.getKey;
    },
  },
};
</script>

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
</style>