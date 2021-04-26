<template>
      <div class="Sign">
            <div>
                  <span>教</span>
            </div>
      </div>
</template>

<script>
export default {
  name: "MySign",
  props: ["type"],
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
.Sign {
  width: 43px;
  height: 40px;
  position: absolute;
  top: -7px;
  left: 10px;
  background: url(../assets/Bookmarks.png) no-repeat center center;
  background-size: cover;
  z-index: 200;
  color: white;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: 600;
  > div {
    margin-right: 6px;
    margin-bottom: 2px;
  }
}
</style>