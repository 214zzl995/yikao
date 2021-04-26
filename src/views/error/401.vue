<template>
      <div class="errPage-container">
            <div>
                  <div class="rol rol1">
                        <h1 class="text-jumbo text-ginormous">
                              401错误!
                        </h1>
                        <h2>您没有访问权限！</h2>
                        <h6>对不起，您没有访问权限，请不要进行非法操作！您可以选择</h6>
                        <ul class="list-unstyled">
                              <li class="link-type">
                                    <Button to="/" type="text">返回首页</Button>
                              </li>
                              <li class="link-type">
                                    <Button @click="back" type="text">返回</Button>
                              </li>
                        </ul>
                  </div>
                  
                  <div class="rol rol2">
                        <img :src="errGif" width="313" height="428" alt="Girl has dropped her ice cream.">
                  </div>
            </div>
            
      </div>
</template>

<script>
import errGif from "@/assets/401_images/401.gif";

export default {
  name: "Page401",
  data() {
    return {
      errGif: errGif + "?" + +new Date(),
    };
  },
  methods: {
    back() {
      if (this.$route.query.noGoBack) {
        this.$router.push({ path: "/" });
      } else {
        this.$router.go(-1);
      }
    },
  },
};
</script>

<style lang="less" scoped>
.errPage-container {
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  > div {
    display: flex;
    width: 46vw;
    justify-content: center;
    align-items: center;
    flex-wrap: wrap;
  }
}
.text-jumbo {
  font-size: 80px;
}

.rol {
  width: 50%;
}
.pan-back-btn {
  width: 100%;
}
.rol1 {
  > * {
    margin: 20px 0;
  }
}

.rol2 {
  display: flex;

  justify-content: center;
  align-items: center;
  flex-wrap: wrap;
}
</style>

<style scoped>
.link-type >>> .ivu-input:focus {
  outline: none;
  box-shadow: 0 0 0 2px transparent;
}

/* 穿透进iview的input框 */
.link-type >>> .ivu-input {
  height: 45px;
}
</style>
