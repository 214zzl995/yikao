<template>
      <div>
            <Upload v-if="type=='Course'" style="width: 600px" :on-success="success" :show-upload-list="false" :headers="headers" name="file" type="drag" :data="Obj" action="/upload/clientupimg/">
                  <div style="padding: 20px 0">
                        <Icon type="ios-cloud-upload" size="52" style="color: #3399ff"></Icon>
                        <p>点击上传 或拖动图片到此位置</p>
                  </div>
            </Upload>
            <div class="AvtarUp">
                  <Upload v-if="type=='Avatar'" ref="upload" :data="Obj" :before-upload="beforeUpload" :show-upload-list="false" :on-success="handleSuccess" :format="['jpg','jpeg','png']" :max-size="2048" :on-exceeded-size="handleMaxSize" type="drag" action="/upload/clientupimg/">
                        <div class="upl">
                              <Avatar shape="square" icon="ios-person" size="150" :src="img" />
                              <div class="upIco">
                                    <Icon type="ios-camera" size="20"></Icon>
                              </div>
                              
                        </div>
                  </Upload>
            </div>
      </div>
</template>

<script>
export default {
  name: "uploadPic",
  props: ["type", "img"],
  components: {},
  computed: {},
  data() {
    return {
      Obj: {
        email: "admin",
        pass: "admin",
      },
      headers: {
        "Access-Control-Allow-Origin": "*",
      },
      imgurl: "",
    };
  },
  methods: {
    success(response, file, fileList) {
      this.$emit("uploadImg", response.data[0].Imgurl);
    },
    handleFormatError(file) {
      this.$Notice.warning({
        title: "图片格式错误",
        desc: "图片名为 " + file.name + " 请上传jpg或png",
      });
    },
    handleMaxSize(file) {
      this.$Notice.warning({
        title: "图片过大",
        desc: "File  " + file.name + " 图片大小不能超过2M",
      });
    },
    handleSuccess(response, file, fileList) {
      this.$emit("uploadImg", response.data[0].Imgurl);
    },
    beforeUpload(file) {
      let promise = new Promise((resolve, reject) => {
        this.$nextTick(function () {
          let _this = this;
          let reader = new FileReader();

          reader.onload = function (e) {
            _this.$Modal.confirm({
              render: (h) => {
                return h("div", [
                  h(
                    "div",
                    {
                      class: { "modal-title": true },
                      style: {
                        display: "flex",
                        height: "42px",
                        alignItems: "center",
                      },
                    },
                    [
                      h("Icon", {
                        props: {
                          type: "ios-alert",
                        },
                        style: {
                          width: "28px",
                          height: "28px",
                          fontSize: "28px",
                          color: "#f90",
                        },
                      }),
                      h("span", {
                        style: {
                          marginLeft: "12px",
                          fontSize: "16px",
                          color: "#17233d",
                          fontWeight: 500,
                        },
                        domProps: {
                          innerHTML: " 提示: 需要上传此图片作为头像么？ ",
                        },
                      }),
                    ]
                  ),
                  h(
                    "div",
                    {
                      // 创建内容区 div 节点，设置样式
                      class: { "modal-content": true },
                      style: {},
                    },
                    [
                      h("img", {
                        attrs: {
                          src: e.target.result,
                          style:
                            "width: 352px;height: 200px;object-fit: cover;margin-top: 20px",
                        },
                      }),
                    ]
                  ),
                ]);
              },
              onOk: () => {
                resolve(true);
              },
              onCancel: () => {
                reject();
              },
            });
          };

          reader.readAsDataURL(file);
        });
      });
      return promise;
    },
  },
  mounted() {},
  watch: {},
};
</script>

<style lang="less" scoped>
.upl {
  position: relative;
  width: 150px;
  height: 150px;
}
.upIco {
  position: absolute;
  width: 100%;
  bottom: 0;
  display: flex;
  flex-direction: column-reverse;
  height: 50%;
  border-radius: 0 0 4px 4px;
  //background: rgba(190, 190, 190, 0.9);
  background: linear-gradient(
    to top,
    rgba(255, 255, 255, 0.5) 0%,
    rgba(255, 255, 255, 0.5) 50%,
    rgba(190, 190, 190, 0) 51%,
    rgba(190, 190, 190, 0) 100%
  );
  > * {
    margin-bottom: 5px;
  }
}

.UserImg {
  width: 500px;
  height: 200px;
  object-fit: cover;
}
</style>

<style lang="less">
.AvtarUp {
  width: 150px;
  margin: 0;
  .ivu-upload {
    .ivu-upload-drag {
      border: 0;
    }
  }
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

