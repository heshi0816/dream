<template>
  <div>
    <a-button type="primary" @click="showModal">开始上传音频</a-button>
    <filetrans-upload ref="filetransUploadCom"></filetrans-upload>
  </div>
</template>
<script setup>
import FiletransUpload from "./filetrans-upload.vue.vue";
import {ref} from "vue";

const filetransUploadCom = ref();

const showModal = () => {
  filetransUploadCom.value.showModal();
};

</script>
