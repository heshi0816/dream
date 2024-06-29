<template>
  <div class="login">
    <a-row>
      <a-col class="main" :span="6" :offset="9">
        <div class="title">
          甲蛙智能语音
        </div>
        <a-form
            :model="loginMember"
            name="basic"
            :wrapper-col="{ span: 24 }"
            @finish="login"
        >
          <a-form-item
              name="mobile" class="form-item"
              :rules="[{ required: true, message: '请输入手机号' }]"
          >
            <a-input v-model:value="loginMember.mobile" placeholder="手机号" size="large">
              <template #prefix>
                <MobileOutlined style="margin-left: 15px"/>
              </template>
            </a-input>
          </a-form-item>

          <a-form-item
              name="password" class="form-item"
              :rules="[{ required: true, message: '请输入密码' }]"
          >
            <a-input-password v-model:value="loginMember.password" placeholder="密码" size="large">
              <template #prefix>
                <LockOutlined style="margin-left: 15px"/>
              </template>
            </a-input-password>
          </a-form-item>

          <a-form-item class="form-item">
            <a-button type="primary" block html-type="submit" class="login-btn" size="large">
              登&nbsp;录
            </a-button>
          </a-form-item>
        </a-form>
        <p class="footer">
          <router-link to="/register">我要注册</router-link>&nbsp;&nbsp;
          <router-link class="pull-right" to="/reset">忘记密码</router-link>
        </p>
      </a-col>
    </a-row>
  </div>
</template>
<script setup>
import { ref } from 'vue';
import axios from "axios";
import {message} from "ant-design-vue";
import {useRouter} from "vue-router";

let router = useRouter();

const loginMember = ref({
  mobile: '',
  password: ''
});
const login = values => {
  axios.post("/nls/web/member/login", {
    mobile: loginMember.value.mobile,
    password: hexMd5Key(loginMember.value.password),
  }).then(response => {
    let data = response.data;
    if (data.success) {
      message.success("登录成功！");
      router.push("/home");
    } else {
      message.error(data.message);
    }
  })
};
</script>
