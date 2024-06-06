## 1.用git config --global user.name "heshi0816"
 git config --global user.email "your-email@example.com"修改本机git用户名，也是最终显示在GitHub上的用户名字。
而最终确定显示的用户名的是邮箱，因为你用于在github注册的邮箱已经与github的用户名挂钩了。
## 2.即使用exe安装的git,
也要手动把`D:\Program Files\Git\mingw64\bin`这个路径注册如环境变量的path才能使用。
因为默认注册的是`D:\Program Files\Git\bin`，而我们使用的是64位的。
## 3.heshi0816这个github账号是用hgh54@126.com邮箱注册的。
密码Swxxxxxxx！
## 4.ideal第一次连接github时用用户名和密码不能连接，
而是要用github生成的token号才能连接。这样本地git才能推送到这个token的GitHub账号。


#JUNE 5th:      
get to know the vue and vue cli. Using vue and node.js to build frontend project. Integrating ant design vue into the project for convenience.
router assigns different web page with a unique path and a unique identifying name
main.ts is the file that associates index.html and other self-designed page. e.g. 
```
createApp(webpage-file).mount(id-name)   
```          
 means to replace the div block with corresponding id in index.html with the webpage-file.
for pages with the same footer and header design, we can specifically design components for <footer> and <header>, with <router-view> in the mid showing different pages
```
<the-header></the-header>
<router-view/>
<the-footer></the-footer>
```

#JUNE 6th:
 learning axios, to send request to the frontend from the backend. 
 ```
axios.get("http://127.0.0.1:8880/ebook/list?name=Spring").then((response) => {console.log(response);}) 
 ```
 then write the value retrieved in the log
 in the setup(), you can also create a field using ref or reactive: e.g.
  ```
 const ebooks = ref(); const ebooks1 = reactive({books: []}); to create field.   In axios.request("url", data).then(attach value to the field).
 ```
 At last you can return 
 the field with value to use them in template
 Creating env.dev and env.prod files and record the port information in these 2 files.
 In main.ts you can set the baseUrl by
 ```
  axios.defaults.baseURL = process.env.VUE_APP_SERVER;
```
 process.env.VUE_APP_SERVER means the VUE_APP_SERVER attribute from the env file.
 Then axios.get("/ebook/list") defaultly setted the baseurl as the prefix of "/ebook/list"
 
 The interceptor can intercept request and response, you can use them for things like writting log.
 ```$xslt
axios.interceptors.request/response.use(....);
```
 
 ##June 6th
 AOP, Interceptor, Filter all have the same function of processing before and after receiving the request and repose
setting a Pointcut. 
```$xslt
    @Pointcut("execution(public * com.jiawa.*.controller..*Controller.*(..))")
    public void controllerPointcut() {}
```
use @doAround, @before, @after to do processing.

```
            <a-menu-item key="/">
                <router-link to="/">首页</router-link>
            </a-menu-item>
```
router-link to can redirect to a different page.

use a-table can create your own template for rendering
```            
               <a-table>
                   <template #cover="{ text: cover }">
                       <img v-if="cover" :src="cover" alt="avatar" />
                   </template>
                   <template v-slot:action="{ text, record }">
                       <a-space size="small">
                           <a-button type="primary">
                               编辑
                           </a-button>
                           <a-button type="danger">
                               删除
                           </a-button>
                       </a-space>
                   </template>
               </a-table>
```
@change attribute in a-table means changing page
 
 
 # web

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).
