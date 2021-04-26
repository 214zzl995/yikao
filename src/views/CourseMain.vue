<template>
      <div>
            <router-view :key="key" name="a" />
      </div>
</template>

<script>
export default {
  name: "CourseMain",
  components: {},
  computed: {
    key() {
      return this.$route.path;
    },
  },
  data() {
    return {};
  },
  methods: {},
  mounted() {},
  watch: {},
};
</script>

<style>
</style>