<template>
      <div class="main_s">
            <div class="mainBody">
                  <div class="MainPlaceholder"></div>
                  <div class="title">
                        <p><span>考试历史</span></p>
                  </div>
                  
                  <div class="PaperDiv">
                        <paper-item :type="'History'" v-for="(paperItem , index) in History" :key="index" :paper="paperItem" />
                  </div>
            </div>
      </div>
</template>

<script>
import PaperItem from "../components/PaperItem.vue";
import { getUserPaperHistory } from "../js/api/Paper";
export default {
  name: "Home",
  components: { PaperItem },
  computed: {},
  data() {
    return {
      History: [],
    };
  },
  methods: {
    getHistory() {
      getUserPaperHistory().then((res) => {
        this.History = res;
      });
    },
  },
  mounted() {
    this.getHistory();
  },
  watch: {},
};
</script>

<style lang="less" scoped>
@import "../css/main.less";
.title {
  font-size: 20px;
  font-weight: 600;
  width: 100%;
  height: 50px;
  margin-top: 10px;
}
.mainBody {
  padding: 15px;
}
.PaperDiv {
  box-shadow: 0 0 20px 0 rgba(175, 187, 204, 0);
}
</style>