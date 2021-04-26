// table-expand.vue
<style scoped>
.expand-row {
  margin-bottom: 16px;
}
.expand-key {
  font-weight: 600;
}
</style>
<template>
      <div>
            <Row class="expand-row">
                  <Col v-if="row[item.key]!=''" span="24" v-for="item ,index in getKey" :key="item">
                  <span class="expand-key">{{item.name}}:</span>
                  <span class="expand-value">{{row[item.key]}}</span>
                  </Col>
                  
            </Row>
      </div>
</template>
<script>
export default {
  props: {
    row: Object,
  },
  computed: {
    getKey() {
      const modules = {
        answer: "答案",
        option1: "选项一",
        option2: "选项二",
        option3: "选项三",
        option4: "选项四",
        option5: "选项五",
        option6: "选项六",
      };
      let name = [];
      let da = Object.keys(this.row);
      for (let i in da) {
        if (modules[da[i]] != undefined) {
          let e = {
            key: da[i],
            name: modules[da[i]],
          };
          name.push(e);
        }
      }
      return name;
    },
  },
};
</script>