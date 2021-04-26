<template>
      <div class="userMain">
            <my-sign v-show="data.myCourse!=undefined" />
            <div class="userInfo">
                  
                  <Avatar icon="ios-person" :src="data.avatar" size="100" />
                  <p>{{data.nikename}}</p>
                  <p class="school">{{data.school}}</p>
                  <Divider />
                  <p class="email"><span>id:</span><span>{{data.userId}}</span></p>
                  <p class="email"><span>email:</span><span>{{data.email}}</span></p>
                  <p class="email"><span>tel:</span><span>{{data.phonenumber}}</span></p>
            </div>
      </div>
</template>

<script>
import MySign from "./MySign.vue";
export default {
  name: "User",
  props: ["data"],
  components: { MySign },
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
.userMain {
  width: 200px;
  height: 300px;
  display: flex;
  justify-content: center;
  position: relative;
}

.userInfo {
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 20px;
  font-weight: 500;
}

.school {
  font-size: 12px;
}
.email {
  font-size: 14px;
}
</style>

<style scoped>
.ivu-avatar >>> img {
  object-fit: cover;
}
</style>