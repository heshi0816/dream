<template>
    <a-layout>
        <a-layout-sider width="200" style="background: #fff">
            <a-menu
                    mode="inline"
                    :style="{ height: '100%', borderRight: 0 }"
                    @click="handleClick">
                <a-menu-item key="welcome">
                    <MailOutlined />
                    <span>welcome</span>
                </a-menu-item>
                <a-sub-menu v-for="item in level1" :key="item.id">
                    <template v-slot:title>
                        <span><user-outlined />{{item.name}}</span>
                    </template>
                    <a-menu-item v-for="child in item.children" :key="child.id">
                        <MailOutlined /><span>{{child.name}}</span>
                    </a-menu-item>
                </a-sub-menu>
            </a-menu>
        </a-layout-sider>
        <a-layout-content
                :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }">
            <div style="margin-bottom: 20px;">
                <input type="text" :value="searchText" @input="updateSearchQuery($event)" placeholder="Enter ebook name" style="width: 300px; margin-right: 10px;">
                <a-button type="primary" @click="handleSearch">Search</a-button>
            </div>
            <div class="welcome" v-show="isShowWelcome">
                <the-welcome></the-welcome>
            </div>
            <a-list v-show="!isShowWelcome" item-layout="vertical" size="large" :grid="{ gutter: 20, column: 3 }" :data-source="ebooks">
                <template #renderItem="{ item }">
                    <a-list-item :key="item.name">
                        <template #actions>
              <span>
                <component v-bind:is="'FileOutlined'" style="margin-right: 8px" />
                {{ item.docCount }}
              </span>
                            <span>
                <component v-bind:is="'UserOutlined'" style="margin-right: 8px" />
                {{ item.viewCount }}
              </span>
                            <span>
                <component v-bind:is="'LikeOutlined'" style="margin-right: 8px" />
                {{ item.voteCount }}
              </span>
                        </template>
                        <a-list-item-meta :description="item.description">
                            <template #title>
                                <router-link :to="'/doc?ebookId=' + item.id">
                                    {{ item.name }}
                                </router-link>
                            </template>
                            <template #avatar><a-avatar :src="item.cover"/></template>
                        </a-list-item-meta>
                    </a-list-item>
                </template>
            </a-list>
        </a-layout-content>
    </a-layout>
</template>

<script lang="ts">
    import { defineComponent, ref, onMounted } from 'vue';
    import axios from 'axios';
    import { message } from 'ant-design-vue';
    import {Tool} from "@/util/tool";
    import TheWelcome from '@/components/the-welcome.vue';

    export default defineComponent({
        name: 'Home',
        components: {
            TheWelcome
        },
        setup() {
            const ebooks = ref([]);
            const searchText = ref('');
            const level1 = ref();
            const isShowWelcome = ref(true);


            const updateSearchQuery = (event: any) => {
                searchText.value = event.target.value;
                console.log("Updated search query:", searchText.value);
            };

            const handleSearch = () => {
                console.log("Searching for:", searchText.value);
                axios.get("/ebook/list", {
                    params: {
                        name: searchText.value
                    }
                }).then((response) => {
                    console.log("Search results:", response.data);
                    if (response.data.success) {
                        ebooks.value = response.data.content.list;
                        isShowWelcome.value = false;
                    } else {
                        message.error(response.data.message);
                    }
                });
            };

            const handleQueryEbook = (categoryId: any) => {
                isShowWelcome.value = false;
                axios.get("/ebook/list", {
                    params: {
                        page: 1,
                        size: 1000,
                        categoryId2: categoryId
                    }
                }).then((response) => {
                    if (response.data.success) {
                        ebooks.value = response.data.content.list;
                    }
                });
            };

            const handleClick = (value: any) => {
                if (value.key === 'welcome') {
                    isShowWelcome.value = true;
                } else {
                    handleQueryEbook(value.key);
                }
            };



            onMounted(() => {
                axios.get("/category/all").then((response) => {
                    if (response.data.success) {
                        level1.value = [];
                        level1.value = Tool.array2Tree(response.data.content, 0);
                    } else {
                        message.error(response.data.message);
                    }
                });
            });

            return {
                ebooks,
                level1,
                isShowWelcome,
                handleSearch,
                handleClick,
                searchText,
                updateSearchQuery
            }
        }
    });
</script>

<style scoped>
    .ant-avatar {
        width: 50px;
        height: 50px;
        line-height: 50px;
        border-radius: 8%;
        margin: 5px 0;
    }
    input {
        padding: 8px;
    }
</style>

