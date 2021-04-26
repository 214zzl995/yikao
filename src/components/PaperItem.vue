<template>
      <div class="paper">
            <div class="paper" v-if="type=='Stu'" @click="examination">
                  <Icon class="paperIco" type="ios-paper-outline" />
                  <span>{{paper.paperName}}</span>
                  <p class="date">{{date}}</p>
            </div>
            
            <div class="paper TeaPaper" v-if="type=='Tea'">
                  <Icon class="paperIco" type="ios-paper-outline" />
                  <span>{{paper.paperName}}</span>
                  <p class="date">{{date}}</p>
                  <div class="set">
                        <Button @click="openEdit" type="primary">修改</Button>
                        <Button @click="Delete" type="error">删除</Button>
                  </div>
            </div>
            
            <div class="paper" v-if="type=='History'" @click="History">
                  <Icon class="paperIco" type="ios-paper-outline" />
                  <span>{{paper.paperName}}</span>
                  <p class="date">{{paper.date}}</p>
                  <p class="date">成绩：{{paper.fraction}}</p>
            </div>
      </div>
</template>

<script>
import { Delete, InitExamination } from "../js/api/Paper";
import Bus from "../js/Bus";
export default {
  name: "PaperItem",
  props: ["paper", "type"],
  components: {},
  computed: {},
  data() {
    return {
      date: this.dateFormat("YYYY-mm-dd", this.paper.createtime),
      CourseId: -1,
    };
  },
  methods: {
    dateFormat(fmt, timestamp) {
      var date = new Date(timestamp);
      let ret;
      const opt = {
        "Y+": date.getFullYear().toString(), // 年
        "m+": (date.getMonth() + 1).toString(), // 月
        "d+": date.getDate().toString(), // 日
        "H+": date.getHours().toString(), // 时
        "M+": date.getMinutes().toString(), // 分
        "S+": date.getSeconds().toString(), // 秒
        // 有其他格式化字符需求可以继续添加，必须转化成字符串
      };
      for (let k in opt) {
        ret = new RegExp("(" + k + ")").exec(fmt);
        if (ret) {
          fmt = fmt.replace(
            ret[1],
            ret[1].length == 1 ? opt[k] : opt[k].padStart(ret[1].length, "0")
          );
        }
      }
      return fmt;
    },
    openEdit() {
      this.$router.push({
        name: "PaperEdit",
        query: { PaperId: this.paper.id, CourseId: this.CourseId },
      });
    },
    Delete() {
      Delete(this.paper.id).then((res) => {
        console.log(res);
        if (res) {
          this.$Message.success("删除成功");
          Bus.$emit("delect", this.paper);
        } else {
          this.$Message.error("删除失败");
        }
      });
    },
    examination() {
      InitExamination(this.paper.id).then((res) => {
        const routeUrl = this.$router.resolve({
          name: "examination",
          params: { paperId: this.paper.id },
        });
        window.open(routeUrl.href, "_blank");
      });
    },
    History() {
      this.$router.push({
        name: "History",
        params: { historyId: this.paper.paperHistoryId },
      });
    },
  },
  mounted() {
    let urlParams = this.$route.query;
    const { CourseId } = urlParams;
    this.CourseId = CourseId;
  },
  watch: {},
};
</script>

<style lang="less" scoped>
@import "../css/main.less";
.paper {
  width: 170px;
  height: 220px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border-radius: 5px;
  margin: 0 30px;
  > span {
    font-size: 20px;
    font-weight: 600;
  }
}

.paper:hover {
  box-shadow: 0 0 20px 0 rgba(175, 187, 204, 0.5);
  border: @backgroundColor solid 1px;
  > span {
    color: #3a8bff;
  }
  > .set {
    display: block;
  }
}

.paperIco {
  font-size: 120px;
}

.set {
  position: relative;
  // top: -50%;
  display: none;
  > * {
    margin: 0 5px;
  }
}

.date {
  font-weight: 600;
}
</style>



<style scoped>
</style>