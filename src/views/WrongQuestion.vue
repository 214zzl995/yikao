<template>
      <div class="main_s">
            <div class="mainBody">
                  <div class="MainPlaceholder"></div>
                  <Tabs class="Tab" :animated="false">
                        <TabPane label="单选题" icon="md-checkmark-circle">
                              <question-table :type="'singleChoice' " :type1="'wrongQuestion'" />
                        </TabPane>
                        <TabPane label="多选题" icon="md-checkbox">
                              <question-table :type="'multipleChoice'" :type1="'wrongQuestion'" />
                        </TabPane>
                        <TabPane label="判断题" icon="md-close-circle">
                              <question-table :type="'judgment'" :type1="'wrongQuestion'" />
                        </TabPane>
                        <TabPane label="填空题" icon="md-code-download">
                              <question-table :type="'blanks'" :type1="'wrongQuestion'" />
                        </TabPane>
                        <TabPane label="简答题" icon="md-clipboard">
                              <question-table :type="'brief'" :type1="'wrongQuestion'" />
                        </TabPane>
                  </Tabs>
            </div>
      </div>
</template>

<script>
import QuestionTable from "../components/QuestionTable.vue";
export default {
  name: "WrongQuestion",
  components: { QuestionTable },
  computed: {},
  data() {
    return {};
  },
  methods: {},
  mounted() {},
  watch: {},
};
</script>

<style scoped lang="less">
@import "../css/main.less";
.mainBody {
  padding: 20px;
}
</style>