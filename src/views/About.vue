<template>
      <div class="about">
            <h1>This is an about page</h1>
            <heart />
            <div class="animate__animated animate__fast animate__pulse">
                  zzzzzzzzzzz
            </div>
      </div>
</template>


<script>
import Heart from "../components/heart.vue";
export default {
  name: "About",
  components: {
    Heart,
  },
  computed: {},
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
