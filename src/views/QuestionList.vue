<template>
      <div class="PaperMain">
            <div class="MainPlaceholder"></div>
            <div class="Paper">
                  <div class="PaperDiv">
                        <Tabs class="Tab" :animated="false">
                              <TabPane label="单选题" icon="md-checkmark-circle">
                                    <question-table :type="'singleChoice' " :type1="'ques1'" />
                              </TabPane>
                              <TabPane label="多选题" icon="md-checkbox">
                                    <question-table :type="'multipleChoice'" :type1="'ques1'" />
                              </TabPane>
                              <TabPane label="判断题" icon="md-close-circle">
                                    <question-table :type="'judgment'" :type1="'ques1'" />
                              </TabPane>
                              <TabPane label="填空题" icon="md-code-download">
                                    <question-table :type="'blanks'" :type1="'ques1'" />
                              </TabPane>
                              <TabPane label="简答题" icon="md-clipboard">
                                    <question-table :type="'brief'" :type1="'ques1'" />
                              </TabPane>
                        </Tabs>
                  </div>
            </div>
      </div>
</template>

<script>
import QuestionTable from "../components/QuestionTable.vue";
export default {
  name: "QuestionList",
  components: { QuestionTable },
  computed: {},
  data() {
    return {
      publicPath: process.env.BASE_URL,
    };
  },
  methods: {
    test() {},
  },
  mounted() {},
  watch: {},
};
</script>

<style lang="less" scoped>
@import "../css/main.less";

.Tab {
  width: 100%;
}
</style>