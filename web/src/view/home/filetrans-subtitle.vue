<template>
  <a-modal v-model:open="open" title="生成字幕" @ok="handleOk">
    <a-table :dataSource="subtitles"
             :columns="columns"
             :pagination="pagination"
             @change="handleTableChange"
             :loading="loading">
      <template #bodyCell="{ column, record }">

      </template>
    </a-table>
  </a-modal>
</template>
<script setup>
import { ref } from 'vue';
import {notification} from "ant-design-vue";
import axios from "axios";
const open = ref(false);
const filetrans = ref();
filetrans.value = {};
const showModal = (_filetrans) => {
  filetrans.value = _filetrans;
  handleQuery();
  open.value = true;
};
const handleOk = e => {
  console.log(e);
  open.value = false;
};

//----------------- 列表查询 -----------------
// 分页的三个属性名是固定的
const pagination = ref({
  total: 0,
  current: 1,
  pageSize: 5,
});
const loading = ref(false);
const subtitles = ref();
subtitles.value = [];

const columns = [{
  title: '开始时间',
  dataIndex: 'begin',
}, {
  title: '结束时间',
  dataIndex: 'end',
}, {
  title: '字幕',
  dataIndex: 'text',
}];

const handleQuery = (param) => {
  if (!param) {
    param = {
      page: 1,
      size: pagination.value.pageSize
    };
  }
  loading.value = true;
  subtitles.value = [];
  axios.get("/nls/web/filetrans-subtitle/query", {
    params: {
      page: param.page,
      size: param.size,
      filetransId: filetrans.value.id
    }
  }).then((response) => {
    loading.value = false;
    let data = response.data;
    if (data.success) {
      subtitles.value = data.content.list;
      // 设置分页控件的值
      pagination.value.current = param.page;
      pagination.value.total = data.content.total;
    } else {
      notification.error({description: data.message});
    }
  });
};

const handleTableChange = (pagination) => {
  // console.log("看看自带的分页参数都有啥：" + pagination);
  handleQuery({
    page: pagination.current,
    size: pagination.pageSize
  });
};

/**
 * 时间格式化，将"秒"格式化成"时:分:秒"
 * @param second
 * @returns {string}
 */
const formatSecond = (second) => {
  return Tool.formatSecond(second);
};

// 使用 defineExpose 向外暴露指定的数据和方法
defineExpose({
  showModal,
})
</script>
