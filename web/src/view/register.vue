<template>
  <div class="register">
    <a-row>
      <a-col class="main" :span="6" :offset="9">
        <div class="title">
          我要注册
        </div>
        <a-form
            :model="registerMember"
            name="basic"
            :wrapper-col="{ span: 24 }"
            @finish="register"
        >
          <a-form-item
              name="mobile" class="form-item"
              :rules="[{ required: true, message: '请输入手机号' }]"
          >
            <a-input v-model:value="registerMember.mobile" placeholder="手机号" size="large">
              <template #prefix>
                <MobileOutlined style="margin-left: 15px"/>
              </template>
            </a-input>
          </a-form-item>

          <a-form-item name="code" class="form-item">
            <a-input-search
                v-model:value="registerMember.code"
                placeholder="短信验证码"
                :enter-button="sendText"
                @search="sendRegisterSmsCode"
            >
              <template #prefix>
                <MessageOutlined style="margin-left: 15px"/>
              </template>
            </a-input-search>
          </a-form-item>

          <a-form-item
              name="password" class="form-item"
              :rules="[{ required: true, message: '请输入密码' }]"
          >
            <a-input-password v-model:value="registerMember.passwordOri" placeholder="密码" size="large">
              <template #prefix>
                <LockOutlined style="margin-left: 15px"/>
              </template>
            </a-input-password>
          </a-form-item>

          <a-form-item
              name="password" class="form-item"
              :rules="[{ required: true, message: '请输入确认密码' }]"
          >
            <a-input-password v-model:value="registerMember.passwordConfirm" placeholder="确认密码" size="large">
              <template #prefix>
                <CheckCircleOutlined style="margin-left: 15px"/>
              </template>
            </a-input-password>
          </a-form-item>

          <a-form-item class="form-item">
            <a-button type="primary" block html-type="submit" class="register-btn" size="large">
              注&nbsp;册
            </a-button>
          </a-form-item>
        </a-form>
        <p class="footer">
          <router-link to="/login"><ArrowLeftOutlined /> 返回登录</router-link>
        </p>
      </a-col>
    </a-row>
  </div>
</template>
<script setup>
import { ref } from 'vue';
const registerMember = ref({
  mobile: '',
  passwordOri: '',
  passwordConfirm: ''
});
const register = values => {
  console.log('开始注册:', values);
};
const sendRegisterSmsCode = () => {
  console.log('发送短信验证码:');
};
const sendText = ref("获取验证码");
</script>
