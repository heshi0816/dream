<template>
  <filetrans-upload ref="filetransUploadCom"></filetrans-upload>

  <p>
    <a-space>
      <a-button type="primary" @click="showModal">开始上传音频</a-button>
      <a-button type="primary" @click="handleQuery()">刷新列表</a-button>
    </a-space>
  </p>
  <a-table :dataSource="filetranss"
           :columns="columns"
           :pagination="pagination"
           @change="handleTableChange"
           :loading="loading">
  </a-table>
</template>
<script setup>
import FiletransUpload from "./filetrans-upload.vue";
import {ref} from "vue";
import axios from "axios";
import {notification} from "ant-design-vue";

const filetransUploadCom = ref();

const showModal = () => {
  filetransUploadCom.value.showModal();
};

//----------------- 列表查询 -----------------
// 分页的三个属性名是固定的
const pagination = ref({
  total: 0,
  current: 1,
  pageSize: 10,
});
const loading = ref(false);
const filetranss = ref();
filetranss.value = [];

const columns = [{
  title: '名称',
  dataIndex: 'name',
}, {
  title: '状态',
  dataIndex: 'status',
}, {
  title: '语言',
  dataIndex: 'lang',
}, {
  title: '时长',
  dataIndex: 'second',
}];

const handleQuery = (param) => {
  if (!param) {
    param = {
      page: 1,
      size: pagination.value.pageSize
    };
  }
  loading.value = true;
  filetranss.value = [];
  axios.get("/nls/web/filetrans/query", {
    params: {
      page: param.page,
      size: param.size
    }
  }).then((response) => {
    loading.value = false;
    let data = response.data;
    if (data.success) {
      filetranss.value = data.content.list;
      // 设置分页控件的值
      pagination.value.current = param.page;
      pagination.value.total = data.content.total;
    } else {
      notification.error({description: data.message});
    }
  });
};

const handleTableChange = (pagination) => {
  console.log("看看自带的分页参数都有啥：" + pagination);
  handleQuery({
    page: pagination.current,
    size: pagination.pageSize
  });
};

handleQuery();
</script>
