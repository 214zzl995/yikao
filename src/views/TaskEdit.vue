<template>
      <div class="PaperMain">
            <div class="MainPlaceholder"></div>
            <div class="Paper">
                  <div class="PaperDiv">
                        <div class="title">
                              <div>
                                    <p>任务设置</p>
                              </div>
                              
                        </div>
                  </div>
            </div>
      </div>
</template>

<script>
export default {
  name: "TaskEdit",
  components: {},
  computed: {},
  data() {
    return {};
  },
  methods: {},
  mounted() {},
  watch: {},
};
</script>

<style lang="less" scoped>
@import "../css/main.less";

.title {
  width: 100%;
  height: 30px;
  font-size: 20px;
  font-weight: 600;
  display: flex;
  justify-content: space-between;
}
</style>