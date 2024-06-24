#JUNE 23th
服務器sql版本是5.7，不支持8.0的utf8mb4_0900_ai_ci 校对规则，
导致用8.0的sql文件创建的ebook表的id变为int而不是bigint，
从而导致装不下雪花算法产生的32为的id而不能新增加数据。改变的方方法是用mysql5.0支持的语法来创建ebook表。
可以使用 utf8mb4_unicode_ci 或 utf8mb4_general_ci，这些是在更早版本的 MySQL 中普遍支持的校对规则来创建ebook表。
 ```$xslt
    CREATE TABLE `ebook` (
       `id` bigint NOT NULL COMMENT 'id',
       `name` varchar(50) DEFAULT NULL COMMENT '名称',
       `category1_id` bigint DEFAULT NULL COMMENT '分类1',
       `category2_id` bigint DEFAULT NULL COMMENT '分类2',
       `description` varchar(200) DEFAULT NULL COMMENT '描述',
       `cover` varchar(200) DEFAULT NULL COMMENT '封面',
       `doc_count` int DEFAULT NULL COMMENT '文档数',
       `view_count` int DEFAULT NULL COMMENT '阅读数',
       `vote_count` int DEFAULT NULL COMMENT '点赞数',
       PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='电子书';

```
要把创建数据库的sql文件中的将 COLLATE=utf8mb4_0900_ai_ci 都更改为 COLLATE=utf8mb4_unicode_ci。
原all.sql未加特定校验规制，为了在sql5.0服务器正常使用，可以用gpt把all.sql加上COLLATE=utf8mb4_unicode_ci校验就可以了。
已经用这种方式把all.sql修改为all-gai-saq5-1.sql文件，经测试可用。
另一个的办法是把服务器的sql升级为8.0版本，这样保证其他表也不会出错。
## Project setup
```

## gpt出现问题，可以清除浏览器缓存在使用。
# 一.数据库服务器重启
## 数据库服务器ID=1dc4beefef0f
## 通过docker ps命令查出各种服务的ID，查已经停止的也可以加个附加代码。
## 重启命令：docker restart 1dc4beefef0f
## 检查确认状态 docker ps -a

## 二。重建数据库
服务器上数据库名：david-dream，用dream.sql文件。david-dreamE，用dreamE.sql文件
### 一定要检测服务器是否有防火墙封闭了端口！！！！！！
### 检查防火墙规则
firewall-cmd --list-all
### 开放8090端口
firewall-cmd --permanent --add-port=8090/tcp
firewall-cmd --reload
8.137.95.225

docker run -d --name mysql -p 3307:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql:5.7


docker run -d \
-p 80:80 \
nginx \
-v /docker/nginx/nginx.conf:/etc/nginx/nginx.conf \
-v /docker/nginx/conf.d:/etc/nginx/conf.d \
-v /docker/nginx/logs:/var/log/nginx \
nginx:1.19.4


jElxcSUj38+Bnh73T68lNs0DfBSit6U3whQlcGO2XwnI+Bo3g4xsiCIPg8PV/L0fQMis08iupNwhe2PzYLB9Xg==
##如果服务器上有端口不能打开 ，一定要检测服务器是否有防火墙封闭了端口！！！！！！但是可能很危险，最好不打开而换一个端口！！
# 检查防火墙规则
firewall-cmd --list-all
# 开放8090端口
firewall-cmd --permanent --add-port=8090/tcp
firewall-cmd --reload

## 一。git密钥
1.git的密钥是针对计算机的，可以用命令行获取本机的 git密钥，配置到github 的ssh就可以了。
2.在ideal中把github设置中已经建立好的连接设为 defoult就会自动推送到gethub上。第一次要先选分享到github，写一个仓库名就可以了。

## 二.
1.开发参考案列是d/wike/wiki/jiaowa，自己的项目是d/wike/wiki1
2.而d/dream/dream/是我目前的主管理项目，项目已经连接github0816，向github的主项目dream推送，也可以拉取旁枝nusblog到项目来结合为一个项目。
3.应该另建一个项目以nusblog 为主，拉取dream管理项目来合并后向github的另一个主项目nusblog来推送比较好！
4.所以用jiaowa项目推送到heshi0816的github，用于合并管理项目！需要再建一个旁枝把dream 管理项目拉取过来，用于合并！

## 三.既然securty的默认首页就是login页面，
就把login 页面和首页做成一体就可以了！这样免得做首页的跳转链接，方便快捷！！

#教训太深刻了：
##1.一定用和原版一样的 node.node for win版本安装，免去设置系统变量的问题！后面发现要用14的版本。
##2.不要安装nvm管理器，会搞乱你的npm
##3.只用官方源，其他都不要用!!!
##4.后端的反而都没有问题，无论spring 版本和java 版本！！


# 开发日记原版：

Spring Boot与vue基础知识初步学习：

确保充分利用Spring Boot的自动配置来简化开发流程。特别是启动器（Starters），要仔细选择适合项目需求的启动器，避免加载不必要的依赖。部署时要检查内置服务器配置，尤其是端口设置，以避免端口冲突。

整合Vue
集成Vue时，保持前后端分离的结构清晰，Vue的组件化开发应充分利用来提高代码的复用性。对于数据绑定，注意数据流的管理，避免出现状态不一致的问题。

数据库交互
在使用Spring Data JPA和MyBatis时，要注意实体与数据库的映射关系，确保CRUD操作的正确性。要特别注意懒加载可能导致的性能问题，及时配置和优化查询。

安全性与权限管理
在实现用户认证和授权时，使用Spring Security框架，记得配置细节要详尽，以确保安全性。角色和权限必须清晰定义，避免权限过大导致安全隐患。

开发工具与环境
使用IntelliJ IDEA时，利用其强大的Spring Boot支持进行高效开发。项目依赖通过Maven或Gradle管理时，定期更新和检查依赖版本，防止版本冲突。

错误处理与日志
日志使用SLF4J与Logback，要合理配置日志级别和输出格式，使其既能提供足够的信息，又不至于过多消耗资源。实现全局异常处理，减少用户因错误导致的不良体验。

测试与部署
在部署前进行全面的单元测试和集成测试，确保功能的正确性和稳定性。部署策略要明确，尤其是环境参数的配置，以保证在不同环境中应用的顺畅运行。

MyBatis的集成与应用
基础设置
MyBatis虽然提供了直接编写SQL的能力，但管理SQL语句时要注意其安全性和效率，尤其是在XML配置中。Mapper的结构要清晰，便于后期维护。

MyBatis与Spring Boot的整合
数据源配置要准确无误，任何小错误都可能导致大问题。在整合MyBatis时，确保所有Mapper都被正确扫描和加载。事务管理要利用Spring Boot的声明式事务注解，简化配置同时保证事务的一致性和完整性。

开发流程与技术点
在定义实体和Mapper时，要保证数据库设计与业务需求的对齐。服务层中调用Mapper时，注意事务的边界和业务逻辑的清晰性。单元测试不仅要测试逻辑正确性，也要注意性能问题。

高级特性与最佳实践
动态SQL的使用可以大幅提高查询的灵活性，但要小心其复杂性和维护难度。配置多环境时，每个环境的配置要明确，以避免部署错误。性能优化时，适时使用MyBatis的缓存机制，但要注意缓存的更新策略和失效条件。


开发日记：深入理解MyBatis与Spring Boot集成
2023年5月5日
今天主要集中在配置MyBatis与Spring Boot的整合。首先，我必须确保在application.properties中正确设置了数据源，这一步对于确保数据库操作的顺利进行至关重要。我记得在之前的项目中，曾因为配置错误浪费了不少时间调试，所以这次我特别谨慎地检查了数据库的URL、用户名和密码。此外，配置MyBatis的Mapper路径也很关键，确保Spring Boot能够扫描到所有的Mapper文件，从而正确地创建Bean。

2023年5月6日
接下来，我开始编写Mapper接口和对应的XML文件。这里有个小技巧：尽管MyBatis支持注解配置SQL语句，我还是倾向于使用XML方式。因为XML不仅可以将SQL语句和Java代码分离，还可以更灵活地编写动态SQL，这在处理复杂查询时特别有用。我通过定义一个简单的查询来测试配置是否成功，结果一切顺利！

2023年5月7日
今天的任务是处理事务管理。Spring Boot提供的声明式事务真是让人省心，只需一个@Transactional注解就可以自动管理事务。我在服务层的方法上添加了此注解，以确保数据库操作的原子性和一致性。过去我曾忽略过这一配置，导致数据不一致的问题，这次我在编码前就特别注意这一点。

2023年5月8日
对于性能优化，MyBatis的二级缓存是个不错的选择，但配置它需要谨慎。我记得以前因为缓存设置不当导致数据更新延迟问题。因此，我决定在这个阶段暂时不启用它，直到所有基本功能稳定运行后再考虑添加缓存来优化读取性能。

2023年5月9日
最后，我开始编写单元测试。测试是确保应用稳定性的关键步骤，特别是在集成了多个组件如Spring Boot和MyBatis的情况下。我使用Spring Boot的测试框架来模拟整个应用环境，确保Mapper和服务层的每个功能都能按预期工作。编写测试时，我发现一个之前未注意的SQL错误，幸好测试帮我捕捉到了这一问题。

Spring MVC 和 Thymeleaf 的整合注意事项

当使用Thymeleaf时，务必保证在Controller中正确配置视图名称，以确保Thymeleaf可以找到对应的模板。
必须记住，在定义数据模型时，使用addAttribute向模型添加数据，这些数据将在Thymeleaf模板中通过表达式访问，要保证键名正确，避免在模板中出现找不到属性的错误。
对于表单的提交，确保使用Thymeleaf的表单绑定正确，特别是对象的绑定，如th:object，这样可以有效减少数据处理中的错误。


Controller的返回行为

ModelAndView的使用和重定向的差别非常关键。使用ModelAndView可以直接返回视图和数据，而重定向则是用于操作完成后的页面跳转，这有助于避免表单重复提交的问题。
在进行页面重定向时，使用redirect:前缀，确保URL的正确性，以及重定向后的目标地址能正确接收必要的参数或者能正确表达操作结果。
JSON数据处理

在处理AJAX请求时，使用@ResponseBody或者RestController来确保响应为JSON格式，特别注意方法返回类型应与期望的JSON结构相匹配。
利用Spring Boot的自动配置优势，确保合理配置Jackson或Gson来进行JSON序列化和反序列化。在复杂对象转换中，检查每个字段是否都正确标注了序列化属性。
视图解析

Thymeleaf的配置需要特别注意缓存的设置，开发时可以关闭缓存以便于实时更新模板变动，但生产环境中应开启缓存以提升性能。
确保配置了合适的消息源和默认语言选项，以及如何在Thymeleaf模板中使用消息源。

使用Thymeleaf进行表单处理
在使用Thymeleaf处理表单时，注意th:action和th:object的使用，确保表单数据能正确绑定到后端模型。
对于表单验证反馈，要合理使用Thymeleaf的条件表达式来显示错误信息，提高用户体验。

展示添加页面：又一次处理这个showAdd的请求，每次都得提醒自己不要忘了如何在Controller里利用ModelAndView传数据。得记住，创建新的ModelAndView时，addObject是关键。今天又一次用到了重定向，避免了表单的重复提交问题。这种小技巧还挺管用的。

处理JSON交互：处理JSON输出时，记得要用@ResponseBody或者@RestController来标注方法。用Jackson序列化对象也成了常态，但每次写AJAX的时候还是得小心，确保后端正确响应JSON请求。

映射URL的小提示：更新代码时，我得再三核对是用@RequestMapping还是@GetMapping。应该多利用这些映射注解来简化URL处理，这对提高开发效率大有帮助。

Thymeleaf的使用：它处理循环和变量的方式，使得动态内容的展示变得简单多了。需要记得在模板中如何正确地引用数据，尤其是那些复杂的表达式。

回顾了一些关于Thymeleaf和Bootstrap的整合技术，真是觉得还有好多要学的。特别是Thymeleaf的语法部分，初看起来有点复杂，得记住怎么用Thymeleaf来处理表单和数据绑定。这些东西写的时候要特别小心，一不留神就容易出错。之前就在一个简单的表单提交上犯了错误，没有注意到数据绑定的属性名和后端的不匹配，结果调试了好久。

还有Bootstrap，它真的很方便在做响应式布局的时候。但是也不能完全依赖于它的样式，有时候需要根据项目的特定需求进行一些自定义的CSS调整。今天花了不少时间调整那些看起来“不和谐”的按钮和表格。


记住如何在Thymeleaf中处理表单和数据绑定至关重要，稍有不慎就容易出错。

Bootstrap在构建响应式布局时的确大有裨益。不过，不能过分依赖于它提供的样式。有时候，根据项目需求还是得手动调整些CSS来确保一切元素的外观符合设计规范。

使用Spring Boot的Actuator进行应用监控时，我要记得常查看'/health'和'/metrics'端点，这对于及时发现和解决生产环境中的问题非常关键。今后在配置监控时，要确保敏感端点的安全性，避免暴露过多的系统信息。

关于消息队列的集成，特别是RabbitMQ的使用，我必须确保消息生产者和消费者的配置正确，避免因配置错误导致消息丢失。要经常检查队列、交换器的绑定关系，并确保错误处理逻辑能正确执行。


在开发过程中，确保Web应用的安全性至关重要，尤其是在处理用户认证和授权时。通过深入研究Spring Security相关部分，我总结了一些关于如何具体实施页面访问权限以及处理权限错误的方法。

页面访问权限的具体实施
配置安全细节：

利用WebSecurityConfigurerAdapter来设置安全配置。这个适配器类允许我们定义哪些URL路径应该是安全的，哪些不应该。
通过.antMatchers("/admin/**").hasRole("ADMIN")确保只有拥有ADMIN角色的用户可以访问/admin路径下的所有资源。
使用.anyRequest().authenticated()确保所有其他请求都需要用户被认证。
自定义登录：

自定义登录页面通过.loginPage("/login")设置，这样可以提供一个更符合项目风格的登录界面。
登录成功后的重定向页面可以通过.defaultSuccessUrl("/homepage")设置，从而在用户成功登录后立即跳转到主页。
权限不正确的检测与处理
访问拒绝处理：

自定义访问拒绝页面可以通过.accessDeniedPage("/accessDenied")实现，这样当用户尝试访问未授权的页面时，系统会重定向到一个友好的错误页面。
更高级的处理方式是实现一个AccessDeniedHandler，在处理方法中可以根据具体的错误原因给出不同的响应策略或进行日志记录。
错误页面的展示：

在Controller中可以设置一个映射到/error的方法，用来展示不同的错误信息，例如用户角色不足以访问某资源。
使用Thymeleaf模板可以动态显示错误详情，增加用户体验。

前后端分离Security 整合JWT实现身份认证
Dream小组讨论：不同页面访问授权方式的比较。管理项目是设定放行页面，其他所有页面需要认证
authorizeRequests()
//放行静态资源
.requestMatchers("/default","/bootstrap/**", "/js/**","/map/**").permitAll()
.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
@Override
public <O extends FilterSecurityInterceptor> O postProcess(O o) {
o.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
o.setAccessDecisionManager(myAccessDecisionManager);
return o;
}
})
//所有请求
.anyRequest()
//需要经过验证
.authenticated()
.and()
.exceptionHandling().accessDeniedPage("/roleError")
而weblog项目恰恰相反，是设定授权限制的页面，其他全部放行，注意比较两者的不同。
protected void configure(HttpSecurity http) throws Exception {
http.authorizeHttpRequests()
.mvcMatchers("/admin/**").authenticated() // 认证所有以 /admin 为前缀的 URL 资源
.anyRequest().permitAll().and() // 其他都需要放行，无需认证

什么是 JWT？
JWT（JSON Web Token）是一种用于在不同应用之间安全传输信息的开放标准（RFC 7519）。它是一种基于 JSON 的轻量级令牌，由三部分组成：头部（Header）、载荷（Payload）和签名（Signature）。JWT 被广泛用于实现身份验证和授权，特别适用于前后端分离的应用程序。

小组讨论Spring Security如何与数据库用户和密码建立联系的：
UserDetailsService实现用户详情服务
什么是 UserDetailsService？
UserDetailsService 是 Spring Security 提供的接口，用于从应用程序的数据源（如数据库、LDAP、内存等）中加载用户信息。它是一个用于将用户详情加载到 Spring Security 的中心机制。UserDetailsService 主要负责两项工作：

加载用户信息： 从数据源中加载用户的用户名、密码和角色等信息。
创建 UserDetails 对象： 根据加载的用户信息，创建一个 Spring Security 所需的 UserDetails 对象，包含用户名、密码、角色和权限等。
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中查询
        UserDO userDO = userMapper.findByUsername(username);

        // 判断用户是否存在
        if (Objects.isNull(userDO)) {
            throw new UsernameNotFoundException("该用户不存在");
        }

        // authorities 用于指定角色，这里写死为 ADMIN 管理员
        return User.withUsername(userDO.getUsername())
                .password(userDO.getPassword())
                .authorities("ADMIN")
                .build();
    }
}


Spring Security 整合 JWT ：实现接口鉴权

Vue 整合 Axios 实现登录功能（解决跨域问题）
Axios 是一个流行的用于发起 HTTP 请求的 JavaScript 库。它可以在浏览器和 Node.js 环境中使用，提供了一种简洁且强大的方式来处理异步网络请求。在项目中整合 Axios 可以帮助你轻松地与后端服务器进行数据交互。
在开始使用 Axios 发起请求之前，需要创建一个 Axios 实例
解决跨域问题有多种办法，如果使用 vite，可以使用代理来绕过跨域问题。Vite 提供了一个配置项来设置代理。在项目根目录的 vite.config.js添加：
export default defineConfig({
server: {
proxy: {
'/api': {
target: 'http://localhost:8080',
changeOrigin: true,
rewrite: (path) => path.replace(/^\/api/, ''),
},
}
},
// 省略...
})

如何把 Token 存储到 Cookie 中：

VueUse 提供了一些常见功能的封装，其中就有对 Cookie 操作的相关封装。
import { useCookies } from '@vueuse/integrations/useCookies'

// 存储在 Cookie 中的 Token 的 key
const TOKEN_KEY = 'Authorization'
const cookie = useCookies()

// 获取 Token 值
export function getToken() {
return cookie.get(TOKEN_KEY)
}

// 设置 Token 到 Cookie 中
export function setToken(token) {
return cookie.set(TOKEN_KEY, token)
}

// 删除 Token
export function removeToken() {
return cookie.remove(TOKEN_KEY)
}
封装三个常用的和 Token 相关的方法，分别是获取 Token 值、设置 Token 值、删除 Token 值。
至少目前看来用VueUse来处理token还是很方便的。

路由守卫
vue-router 提供的导航守卫主要用来通过跳转或取消的方式守卫导航。这里有很多方式植入路由导航中：全局的，单个路由独享的，或者组件级的
通过全局路由守卫方式：
const router = createRouter({ ... })

// 全局路由前置守卫
router.beforeEach((to, from, next) => {
next()
})
通过使用 router.beforeEach 注册一个全局前置守卫，每个守卫默认接受两个参数：

to: 即将要进入的目标；
from: 当前导航正要离开的路由；
next : 可额外添加的参数，用于手动控制跳转哪个页面；
next() 必须设置，否则不会跳转目标路由。
确保 next() 在任何给定的导航守卫中都被严格调用一次。


也可以注册全局后置钩子，然而和守卫不同的是，这些钩子不会接受 next 函数也不会改变导航本身
router.afterEach((to, from) => {

})
后置守卫对于分析、更改页面标题、声明页面等辅助功能以及许多其他事情都很有用

在登陆页面使用全局路由前置守卫
// 全局路由前置守卫
router.beforeEach((to, from, next) => {
console.log('==> 全局路由前置守卫')

    // 若用户想访问后台（以 /admin 为前缀的路由）
    // 未登录，则强制跳转登录页
    let token = getToken()
    if (!token && to.path.startsWith('/admin')) {
        showMessage('请先登录', 'warning')
        next({ path: '/login' })
    } else {
        next()
    }

})

重复登录问题的处理：
在路由的前置守卫中，添加逻辑代码
// 全局路由前置守卫
router.beforeEach((to, from, next) => {
console.log('==> 全局路由前置守卫')

    // 展示页面加载 Loading
    showPageLoading()
    
    let token = getToken()

    if (!token && to.path.startsWith('/admin')) { 
        // 省略...
    } else if (token && to.path == '/login') {
        // 若用户已经登录，且重复访问登录页
        showMessage('请勿重复登录', 'warning')
        // 跳转后台首页
        next({ path: '/admin/index' })
    } else {
        next()
    }
})

鉴权不通过响参提示
RestAccessDeniedHandler 专门用于处理用户登录成功，但是访问的是受保护的资源时候的处理，一般为显示权限不够信息。

重点知识：
Spring Security 方法级注解的配置和使用。
编辑 WebSecurityConfig 配置类，添加 @EnableGlobalMethodSecurity 注解。
// 省略...
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
// 省略...
}
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)：这是一个 Spring Security 注解，用于启用方法级别的安全性设置。prePostEnabled = true 表示启用 @PreAuthorize 和 @PostAuthorize 注解，securedEnabled = true 表示启用 @Secured 注解。这意味着可以在方法级别使用这些注解来定义访问控制规则。
注意：还需要在全局异常捕获类中将 AccessDeniedException 手动抛出，否则就被它给吞了。这里需要统一交给 RestAccessDeniedHandler 去处理
在 TestController 控制器中，添加一个新的接口 /admin/update , 并对其添加 @PreAuthorize("hasRole('ROLE_ADMIN')") 注解，表示在调用接口之前进行鉴权，必须是拥有 ROLE_ADMIN 管理员角色的账号发来的请求，才允许正常执行，否则提示鉴权不通过提示。通过这种方法级的权限控制对特定角色的特定方法授权。非常重要！！
@PostMapping("/admin/update")
@ApiOperationLog(description = "测试更新接口")
@ApiOperation(value = "测试更新接口")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public Response testUpdate() {
log.info("更新成功...");
return Response.success();
}

}

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.3.0.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.3.0.RELEASE/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.0.RELEASE/reference/htmlsingle/index.html#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

testtest
