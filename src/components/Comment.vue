<template>
      <div class="comment">
            <div class="header">
                  <div>
                        <Avatar icon="ios-person" :src="data.avatar" />
                        <span class="name">{{data.nikeName}}</span>
                  </div>
                  <div class="like" @click="likesC">
                        <Icon type="md-thumbs-up" class="animated bounce" :class="{ 'ok': data.status ,'animate__animated animate__fast animate__tada' : clic}" />
                        <div class="likesNum">
                              <span>{{data.likes}}</span>
                        </div>
                  </div>
            </div>
            <div class="comm">
                  {{data.comment}}
            </div>
      </div>
</template>

<script>
import { likes } from "../js/api/Likes";
export default {
  name: "Comment",
  props: ["data"],
  components: {},
  computed: {},
  data() {
    return {
      clic: false,
    };
  },
  methods: {
    likesC() {
      this.clic = true;
      setTimeout(() => {
        this.clic = false;
      }, 1000);
      const likesdata = {
        likeType: 0,
        likeTypeId: this.data.id,
      };
      likes(likesdata).then((res) => {
        if (res == undefined) {
          this.$Message.warning("你已经点赞过这个评论咯");
        } else {
          this.$emit("like");
        }
      });
    },
  },
  mounted() {
    console.log(this.$store.getters.userDetails);
  },
  watch: {},
};
</script>

<style lang="less" scoped>
.comment {
  width: 100%;
  border-radius: 12px;
  padding: 12px;
  margin-top: 20px;
  box-shadow: 0 1px 6px rgb(0 0 0 / 20%);
  border-color: #eee;
}

.name {
  font-size: 15px;
  font-weight: 600;
  margin-left: 12px;
}

.header {
  display: flex;
  justify-content: space-between;
}

.like {
  font-size: 20px;
  margin-right: 20px;
  color: rgb(143, 143, 143);
  cursor: pointer;
  display: flex;
}

.likesNum {
  font-size: 15px;
  width: 30px;
  color: black;
  text-align: center;
  font-weight: 600;
  color: #808695;
}
.comm {
  background: white;
  padding: 12px;
  width: 95%;
  border-radius: 12px;
  margin-top: 10px;
  margin-left: 42px;
  font-weight: 800;
}

.ok {
  color: #ff6060;
}
</style>

<style scoped>
.User >>> .ivu-poptip {
  width: 100%;
}
.ivu-poptip >>> .ivu-poptip-rel {
  width: 100%;
}
.status >>> .ivu-poptip {
  width: 70px;
}

.ivu-avatar >>> img {
  object-fit: cover;
}
</style>