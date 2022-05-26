<template>
  <div class="navbar">
    <hamburger :is-active="sidebar.opened" class="hamburger-container" @toggleClick="toggleSideBar"/>

    <breadcrumb class="breadcrumb-container"/>

    <div class="right-menu">
      <el-dropdown class="avatar-container">

        <div class="avatar-wrapper">
          <div class="avatar">
            <el-avatar :src="avatar+'?imageView2/1/w/80/h/80'" :size="35"></el-avatar>
          </div>
          <strong>{{ name }}</strong>
        </div>

        <el-dropdown-menu slot="dropdown" class="user-dropdown">
          <a target="_blank" href="https://github.com/">
            <el-dropdown-item>Github</el-dropdown-item>
          </a>
          <a target="_blank" href="http://127.0.0.1/doc/">
            <el-dropdown-item>Docs</el-dropdown-item>
          </a>
          <el-dropdown-item divided @click.native="logout">
            <span style="display:block;">退  出</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>

    <div class="text-scroll-wrapper">
      <TextScroll :data-list="textScroll"/>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'

export default {
  components: {
    Breadcrumb,
    Hamburger
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar',
      'name'
    ])
  },
  data() {
    return {
      textScroll: ['1、计划2022-5-25日对系统进行升级！', '2、我是演示通知公告']
    }
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      await this.$store.dispatch('user/logout')
      this.$router.push(`/login?redirect=${this.$route.fullPath}`)
    }
  }
}
</script>

<style lang="scss" scoped>

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}

.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, .08);

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background .3s;
    -webkit-tap-highlight-color: transparent;

    &:hover {
      background: rgba(0, 0, 0, .025)
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .text-scroll-wrapper {
    margin-right: 700px;
    float: right;
    height: 100%;
    line-height: 50px;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

    &:focus {
      outline: none;
    }

    .avatar-container {
      cursor: pointer;
      height: 100%;
      margin-right: 10px;
      padding-left: 10px;

      .avatar-wrapper {
        position: relative;
        height: 100%;
        width: 100px;
        line-height: 50px;

        .avatar {
          position: absolute;
          line-height: 50px;
          text-align: center;
          margin-left: 50px;
          top: 5px;
        }
      }
    }

    .avatar-container:hover {
      background-color: rgba(0, 0, 0, 0.050);
    }
  }
}
</style>
