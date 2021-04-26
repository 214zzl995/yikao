<template>
      <div class="main">
            <navigation-bar />
            
            <transition name="fade-transform" mode="out-in">
                  
                  <router-view :key="key" />
                  
            </transition>
            
            <div class="zw"></div>
      </div>
</template>

<script>
import NavigationBar from "@/components/NavigationBar";
export default {
  name: "Index",
  components: { NavigationBar },
  computed: {
    key() {
      return this.$route.path;
    },
  },
  data() {
    return {};
  },
  methods: {
    getInfo() {
      this.$store.dispatch("GetInfo").then((res) => console.log(res));
    },
  },
  mounted() {},
  watch: {},
  created() {},
};
</script>

<style scoped lang="less">
@import "../css/main.less";

.zw {
  width: 100vw;
  height: 4vh;
}
</style>