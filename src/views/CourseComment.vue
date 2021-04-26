<template>
      <div class="PaperMain">
            <div class="MainPlaceholder"></div>
            <div class="Paper">
                  <div class="PaperDiv">
                        <div class="all">
                              <div class="title">
                                    <p><span>用户评论</span></p>
                              </div>
                              <div>
                                    <comment v-for="(item ,index) in CommentData" @like="likes(index)" :key="index" :data="item" />
                              </div>
                              
                              <div class="addcomment">
                                    <Input v-model="addComment.userComment" type="textarea" placeholder="说些什么吧" />
                                    <Button @click="addUserComment" type="primary" style="margin-top:10px">发送</Button>
                              </div>
                              
                              <div class="page">
                                    <Page :total="commentNumber" @on-change="changePage" @on-page-size-change="changePageSize" show-total />
                              </div>
                              
                        </div>
                        
                        
                  </div>
            </div>
      </div>
      
</template>

<script>
import Comment from "../components/Comment.vue";
import {
  getCourseCommentNumber,
  getCourseComment,
  addUserComment,
} from "../js/api/Comment";
export default {
  name: "CourseComment",
  components: { Comment },
  computed: {},
  data() {
    return {
      CourseId: -1,
      commentNumber: 0,
      pageSize: 10,
      page: 1,
      CommentData: [],
      addComment: {
        courseId: 0,
        userId: 0,
        userComment: "",
        likes: 0,
      },
    };
  },
  methods: {
    getNumber() {
      getCourseCommentNumber(this.CourseId).then((res) => {
        this.commentNumber = res;
      });
    },
    getComment() {
      getCourseComment(
        this.CourseId,
        this.addDateRange({
          offset: this.pageSize * this.page - this.pageSize,
          limit: this.pageSize,
        })
      ).then((res) => {
        this.CommentData = res;
      });
    },
    changePageSize(size) {
      this.pageSize = size;
      this.getComment();
    },
    changePage(size) {
      this.page = size;
      this.getComment();
    },
    likes(index) {
      this.CommentData[index].likes++;
      this.CommentData[index].status = !this.CommentData[index].status;
    },
    addUserComment() {
      if (this.addComment.userComment != "") {
        addUserComment(this.addComment).then((res) => {
          this.getNumber();
          this.getComment();
          this.$Message.success("评论成功");
        });
      } else {
        this.$Message.warning("您还未填写评论哦");
      }
    },
  },
  mounted() {
    let urlParams = this.$route.query;
    const { CourseId } = urlParams;
    this.CourseId = CourseId;
    this.addComment.courseId = CourseId;
    this.getNumber();
    this.getComment();
  },
  watch: {},
};
</script>

<style lang="less" scoped>
@import "../css/main.less";

.title {
  font-size: 20px;
  font-weight: 600;
  width: 100%;
  height: 50px;
}

.all {
  flex: 1;
  display: flex;
  flex-direction: column;
}
.page {
  align-self: flex-end;
  margin-top: 20px;
}

.addcomment {
  margin-top: 12px;
}
</style>