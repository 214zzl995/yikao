<template>
      <div @click="heart=!heart">
            <div class="stage">
                  <div class="heart" :class="{ 'is-active': heart }"></div>
                  
                  <div class="wishlist-heart-group">
                        <input name="product-333" id="product-333" data-product-id="333" type="checkbox" />
                        <label for="product-333">
                              <svg xmlns:dc="https://purl.org/dc/elements/1.1/" xmlns:cc="https://creativecommons.org/ns#" xmlns:rdf="https://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:svg="https://www.w3.org/2000/svg" xmlns="https://www.w3.org/2000/svg" xmlns:sodipodi="https://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd" xmlns:inkscape="https://www.inkscape.org/namespaces/inkscape" version="1.1" x="0px" y="0px" viewBox="0 0 100 100">
                                    <g transform="translate(0,-952.36218)">
                                          <path style="color:#000000;enable-background:accumulate;" d="m 34.166665,972.36218 c -11.41955,0 -19.16666,8.91891 -19.16666,20.27029 0,19.45943 15,27.56753 35,39.72973 20.00001,-12.1622 34.99999,-20.2703 34.99999,-39.72973 0,-11.35137 -7.7471,-20.27029 -19.16665,-20.27029 -7.35014,0 -13.39148,4.05405 -15.83334,6.48647 -2.44185,-2.43241 -8.48319,-6.48647 -15.83334,-6.48647 z" fill="transparent" id="heart-path" stroke="#F8332A" stroke-width="5" marker="none" visibility="visible" display="inline" overflow="visible" />
                                    </g>
                              </svg>
                        </label>
                  </div>
            </div>
      </div>
</template>

<script>
export default {
  name: "heart",
  components: {},
  computed: {},
  data() {
    return {
      heart: false,
    };
  },
  methods: {},
  mounted() {},
  watch: {},
};
</script>

<style>
.heart {
  width: 100px;
  height: 100px;
  background: url("https://cssanimation.rocks/images/posts/steps/heart.png")
    no-repeat;
  background-position: 0 0;
  cursor: pointer;
  -webkit-transition: background-position 1s steps(28);
  transition: background-position 1s steps(28);
  -webkit-transition-duration: 0s;
  transition-duration: 0s;
}
.heart.is-active {
  -webkit-transition-duration: 1s;
  transition-duration: 1s;
  background-position: -2800px 0;
}

.stage {
  position: fixed;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}
</style>

<style>
.wishlist-heart-group {
  display: inline-block;
  width: 40px;
  height: 40px;
  position: relative;
}
.wishlist-heart-group input[type="checkbox"] {
  position: absolute;
  left: -999999px;
  top: -999999px;
}
.wishlist-heart-group input[type="checkbox"] + label svg {
  -webkit-transform: rotate(0deg);
  transform: rotate(0deg);
}
.wishlist-heart-group input[type="checkbox"]:checked + label svg {
  -webkit-animation: wishlist-heart-wiggle 400ms 50ms forwards ease-in-out;
  animation: wishlist-heart-wiggle 400ms 50ms forwards ease-in-out;
}
.wishlist-heart-group input[type="checkbox"]:checked + label svg #heart-path {
  -webkit-animation: wishlist-heart-add 300ms forwards;
  animation: wishlist-heart-add 300ms forwards;
}
.wishlist-heart-group input[type="checkbox"] + label svg #heart-path {
  -webkit-transition: fill 200ms;
  transition: fill 200ms;
}
.wishlist-heart-group label {
  display: inline-block;
  width: 100%;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  justify-content: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  position: relative;
}
@-webkit-keyframes wishlist-heart-wiggle {
  0% {
    -webkit-transform: rotate(0deg);
    transform: rotate(0deg);
  }
  25% {
    -webkit-transform: rotate(-10deg);
    transform: rotate(-10deg);
  }
  75% {
    -webkit-transform: rotate(8deg);
    transform: rotate(8deg);
  }
  100% {
    -webkit-transform: rotate(0deg);
    transform: rotate(0deg);
  }
}
@keyframes wishlist-heart-wiggle {
  0% {
    -webkit-transform: rotate(0deg);
    transform: rotate(0deg);
  }
  25% {
    -webkit-transform: rotate(-10deg);
    transform: rotate(-10deg);
  }
  75% {
    -webkit-transform: rotate(8deg);
    transform: rotate(8deg);
  }
  100% {
    -webkit-transform: rotate(0deg);
    transform: rotate(0deg);
  }
}
@-webkit-keyframes wishlist-heart-add {
  0% {
    stroke-width: 5;
  }
  50% {
    stroke-width: 20;
  }
  100% {
    stroke-width: 5;
    fill: #f8332a;
  }
}
@keyframes wishlist-heart-add {
  0% {
    stroke-width: 5;
  }
  50% {
    stroke-width: 20;
  }
  100% {
    stroke-width: 5;
    fill: #f8332a;
  }
}
</style>