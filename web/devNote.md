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

```$xslt
        PageHelper.startPage(1, 3);
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);
```
Pagehelper only works for the first selectbyExample code.
 
 #June 8th
 @MapperScan is needed for scanning all mapper
 
 enviroment file like env.dev has to be under src folder
 
 when git repository conflicts choose the later one click "Theirs"
 
 ```$xslt
PageHelper.startPage(req.getPage(), req.getSize());
```
add pagination function in the backend

in the request class use
```$xslt
    @NotNull(message = "【页码】不能为空")
    private int page;

    @NotNull(message = "【每页条数】不能为空")
    @Max(value = 1000, message = "【每页条数】不能超过1000")
```
to add rule on parameter 
```$xslt
    public CommonResp save(@Valid @RequestBody EbookSaveReq req) {
```
@Valid activate the rule in the controller @RequestBody changes the json file into req class
 
 
 #JUNE 9th
 change the data source parameter in a-table can change the list type
 ```$xslt
:data-source="level1"
:data-source="ebooks"
```
ebooks is a list, but level1 is the sublist with children parameter 
ended up having totally different list on the webpage in the frontend


```$xslt
            <a-form-item label="parent type">
                <a-select
                        v-model:value="category.parent"
                        ref="select"
                >
                    <a-select-option value="0">
                        no type
                    </a-select-option>
                    <a-select-option v-for="c in level1" :key="c.id" :value="c.id" :disabled="category.id === c.id">
                        {{c.name}}
                    </a-select-option>
                </a-select>
            </a-form-item>
```
constructing a drop-down menu v-for =.... is for loop iterating on "level1" fields, :disabled
keyword exclude some some options from being picked


# JUNE 10th

```$xslt
in admin-doc
        doc.value = {
          ebookId: route.query.ebookId
        };
in admin-ebook
<router-link :to="'/admin/doc?ebookId=' + record.id">
```

as long as the parameter name and the variable name are the same they value can be transferred

```            
const edit = (record: any) => {
                   modalVisible.value = true;
                   doc.value = Tool.copy(record);
   
                   // 不能选择当前节点及其所有子孙节点，作为父节点，会使树断开
                   treeSelectData.value = Tool.copy(level1.value);
                   setDisable(treeSelectData.value, record.id);
   
                   // 为选择树添加一个"无"
                   treeSelectData.value.unshift({id: 0, name: '无'});
                   setTimeout(function () {
                       editor.create();
                   }, 100);
               };
```
editor.create is completed too fast, faster than the rendering time of the modalVisible

# JUNE 11th
delete function testing, if the id of the row of object in the database is generated by snowflake algorithm
it can't be deleted by the delete function in mapper

```$xslt
    String content

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
```
when I add a new field "content" in Req class, getter and setter has to come alone otherwise the field 
is not activated and no error is reported hard to debug 

# JUNE 12th
```$xslt
<style scoped>
</style>
```
means the style is only going to work in this page

```$xslt
            const add = () => {
                // 清空富文本框
                editor.txt.html("");
                modalVisible.value = true;
                doc.value = {
                    ebookId: route.query.ebookId
                };


                if (level1.value.length === 0) {
                    treeSelectData.value = [{id: 0, name: '无'}];
                } else {
                    // 为选择树添加一个"无"
                    treeSelectData.value = Tool.copy(level1.value);
                    treeSelectData.value.unshift({id: 0, name: '无'});
                }
            };
``` 
treeSelectData.value.unshift({id: 0, name: '无'});, wouldn't work if treeSelectData.value is
empty, so we have to do an empty check.  
 
 
 #JUNE 13th
 long time on debugging the request should require the 32 byte password
 # web

 #JUNE 15th
 
 ```$xslt
            const login = () => {
                console.log("开始登录");
                loginModalLoading.value = true;
                loginUser.value.password = hexMd5(loginUser.value.password + KEY);
                axios.post('/user/login', loginUser.value).then((response) => {
                    loginModalLoading.value = false;
                    const data = response.data;
                    if (data.success) {
                        loginModalVisible.value = false;
                        message.success("登录成功！");

                        store.commit("setUser", user.value);
                    } else {
                        message.error(data.message);
                    }
                });
            };
    @PostMapping("/login")
    public CommonResp login(@Valid @RequestBody UserLoginReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp<UserLoginResp> resp = new CommonResp<>();
        UserLoginResp userLoginResp = userService.login(req);

        Long token = snowFlake.nextId();
        LOG.info("生成单点登录token：{}，并放入redis中", token);
        userLoginResp.setToken(token.toString());
        redisTemplate.opsForValue().set(token, JSONObject.toJSONString(userLoginResp), 3600 * 24, TimeUnit.SECONDS);

        resp.setContent(userLoginResp);
        return resp;
    }

``` 
  generating a random number by using snowflake algorithm, then create a token field(string) 
  in the request type. And set the token in the redis and also using the vuex to store the status of
  login in.
  
```$xslt
  store.commit("setUser", user.value);
```
storing the status by using the vuex

```$xslt
  {
    path: '/admin/category',
    name: 'AdminCategory',
    component: AdminCategory,
    meta: {
      loginRequire: true
    }
  }
```
in the router adding loginRequire to mark the pages that needs to be logined in to access

#JUNE 16th

the generated xml file can't be changed so in order to add increaseViewCount function we 
have to create a new mapper and write a new xml, the DocMapperCust.
```$xslt
    public void vote(Long id) {
        // docMapperCust.increaseVoteCount(id);
        // 远程IP+doc.id作为key，24小时内不能重复
        String ip = RequestContext.getRemoteAddr();
        if (redisUtil.validateRepeat("DOC_VOTE_" + id + "_" + ip, 3600 * 24)) {
            docMapperCust.increaseVoteCount(id);
        } else {
            throw new BusinessException(BusinessExceptionCode.VOTE_REPEAT);
        }
    }
```
a single ip address can only vote once, each address!!!!

# JUNE 17th
```$xslt
    @Scheduled(fixedRate = 1000)
    public void simple() throws InterruptedException {
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        String dateString = formatter.format(new Date());
        Thread.sleep(2000);
        LOG.info("每隔5秒钟执行一次： {}", dateString);
    }

    /**
     * 自定义cron表达式跑批
     * 只有等上一次执行完成，下一次才会在下一个时间点执行，错过就错过
     */
    @Scheduled(cron = "*/1 * * * * ?")
    public void cron() throws InterruptedException {
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss SSS");
        String dateString = formatter.format(new Date());
        Thread.sleep(1500);
        LOG.info("每隔1秒钟执行一次： {}", dateString);
    }
```
the @scheduled annotation works on a singe thread instead of parallel, so both of them
will wait till the other one finish running before themselves moving on.


# JUNE 18th

```$xslt
@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }
}
```
Jackson Config solves the snowflake precision problem which incurred that changes passed from 
backend to database can't reach the database

```$xslt
   websocket = new WebSocket("ws://127.0.0.1:8880"+ '/ws/' + token);
```
websocket problem in the footer stay unsolved still using a fixed ip address, the address
written in env.dev isn't working 

```
    @Async
    public void sendInfo(String message, String logId) {
        MDC.put("LOG_ID", logId);
        webSocketServer.sendInfo(message);
    }
```
for asyncronous running @Async annotation should be in a different service class to run
on different threads

```$xslt
    @Transactional
```
 a powerful annotation that prevents database get into an inconsistent state, whenever exceptions
 occurs the execution rolls back. It wouldn't work when the method is used by another method
 in the same service class


# JUNE 19th

```$xslt
  unique key `ebook_id_date_unique` (`ebook_id`, `date`)
```
unique keyword in database can promise there's only one record and keep get updated instead of
creating a new one for the same record

#JUNE 20th
 ```$xslt
    declare let echarts: any;
```
any external compnents that does not belong to vues needs to be declared first
  
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
