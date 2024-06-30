<template>
    <div>
        <h1>Ebook Search</h1>
        <!-- 使用 :value 和 @input 来更新 searchQuery -->
        <input type="text" :value="searchQuery" @input="updateQuery($event)" placeholder="Enter ebook name">
        <button @click="fetchEbooks">Search</button>
        <ul>
            <li v-for="ebook in ebooks" :key="ebook.id">
                {{ ebook.name }} (Category ID: {{ ebook.categoryId2 }})
            </li>
        </ul>
    </div>
</template>

<script setup>
    import { ref } from 'vue';
    import axios from 'axios';

    const searchQuery = ref('');
    const ebooks = ref([]);

    // 更新 searchQuery 函数
    const updateQuery = (event) => {
        searchQuery.value = event.target.value;
        console.log("Updated search query:", searchQuery.value);  // 立即打印新的搜索词
    };

    const fetchEbooks = async () => {
        console.log("Attempting to search with query:", searchQuery.value);  // 确认搜索时使用的查询词
        if (!searchQuery.value.trim()) {
            console.log("Search query is empty");  // 如果查询为空，则不进行搜索
            ebooks.value = [];
            return;
        }
        try {
            const response = await axios.get('http://localhost:8880/ebook/list',
