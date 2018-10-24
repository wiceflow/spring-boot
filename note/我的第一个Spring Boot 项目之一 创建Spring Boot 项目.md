## 我的第一个Spring Boot 项目之一 创建Spring Boot 项目  

因为笔者常用的编译器是 IDEA 所以接下来有关于 Spring Boot 的文章都是以 IDEA 为编译器所编写的。  

在创建 Spring Boot 之前，我们先来了解一下 Spring Boot 。可以说 Spring Boot 的设计目的是用来简化新 Spring 应用的初始搭建以及开发过程。而且 Spring Boot 内嵌了 Tomcat，无需部署 war 包了。  



> Spring Boot 并不是对 Spring 功能上的增强，而是提供一种快读使用 Spring 的方法。    



### 在 IDEA 中创建 Spring Boot 项目  

我们先点击 File -> New Project 会出现这么一个界面  

<img src = "/img/create-1.png">

虽然说 Spring Boot 也属于一个 Maven 项目，不过在 IDEA 中，并不直接创建 Maven 项目（**这和之前创建的 Spring 项目有很大区别**）  

选择了 Spring Initializr 之后，我们只需要选择 JDK 版本就好了。因为我项目里用到的是 JDK1.8，所以推荐大家保持和我一样的。  

点了 Next 之后  

<img src = "/img/create-2.png">

这里重点讲解一下打包方式。  

以往我们的打包方式是达成 war 包，然后放在 Tomcat 服务器中就可以跑了。这里因为 Spring Boot 集成了一个 Http 服务 所以我们选择的打包方式 Jar。  

在最后一栏我们可以看到 Package 这个属性，这是代表刚开始生成了包名目录，一般包名是全小写字母且不应该出现多个单词，如果我不修改这里的话，默认会变成 `com.wiceflow.springboot` 。  

点击 Next 后  

<img src = "/img/create-3.png">

因为我们创建的是 Web 项目，所以勾选 Web。

注意一下，Spring Boot 版本选择中会出现多个，我这里使用的是2.0.6，不同版本之间可能会有些特性不一样。  

点击 Next 后，就是最后一步了，选择自己的项目保存路径与填写项目名就 OK 。  

创建好的 Spring Boot 项目  

<img src = "/img/create-4.png">

我们会刚我们选定的包下看到一个带 Main 方法的类 `Application` ，这就是我们的启动类。  

这个类里面也是只有一个简单的 Main 方法  

```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

当我们运行这个 `Main` 方法的时候，我们的 Spring Boot 就启动起来了。  

> 后续会专门出文章探讨 Spring Boot 的启动原理  

### 测试我们搭建的 Spring Boot 项目 

我们编写一个 `HelloController` 类，里面写上一个方法，直接附上代码  

```java
/**
 * @author BF
 * @date 2018/10/17
 */
@RestController
public class HelloController {

    @RequestMapping("hello")
    public final AjaxResult getHelloWord(){
        String str = "Hello Word";
        return AjaxResult.okDataResponse(str);
    }
}
//  这里面的 AjaxResult 是我自定义的一个工具类，在最后面会放出来，大家也可以直接返回 String 类型
```

保存后，我们运行一个一开始自动生成的 `Appliaction` ，然后去浏览器输入 `http://localhost:8080/hello`  

<img src="/img/result-1.png">

运行成功~  

我们来看一下我整个项目的结构  

<img src="/img/result-2.png">

### 注意事项  

在创建 Spring Boot 后，系统默认帮我们生成的 Application 类的路径是在默认的包下的，这时候我们以这个包为根目录包，接下来所有的 Java 类 或者包都不能创建在 `Application` 类的上级，否则 Spring Boot 是启动失败的。    

> 可以创建在 Controller 的包中噢



### AjaxResult 类

奉上 `AjaxResult` 类代码  

```java
/**
 * 封装返回前台的数据
 *
 * @author BF
 */
public class AjaxResult {
    private Integer status;
    private Object data;
    private String message;

    private AjaxResult(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        if (data == null) {
            data = "";
        }
        this.data = data;
    }

    /**
     * 不设置就是默认成功了
     *
     * @return
     */
    public static AjaxResult okResponse() {
        return new AjaxResult(200, "success", null);
    }

    public static AjaxResult okResponse(String message) {
        return new AjaxResult(200, message, null);
    }

    /**
     * 只设置返回数据
     *
     * @param data 返回数据
     * @return
     */
    public static AjaxResult okResponse(Object data) {
        return new AjaxResult(200, "success", data);
    }

    /**
     * 解决data数据放在message里面的错误
     * @param data
     * @return
     */
    public static AjaxResult okDataResponse(Object data) {
        return new AjaxResult(200, "success", data);
    }

    /**
     * 若有数据返回，则message必须修改
     *
     * @param message 返回信息
     * @param data    返回数据
     * @return
     */
    public static AjaxResult okResponse(String message, Object data) {
        return new AjaxResult(200, message, data);
    }

    /**
     * 出错相应类型，提示错误信息
     *
     * @param message 错误信息
     * @return
     */
    public static AjaxResult errorResponse(String message) {
        return new AjaxResult(500, message, null);
    }
    
    /**
     * 参数不合法异常响应
     *
     * @param message 错误信息
     * @return
     */
    public static AjaxResult forbiddenResponse(String message) {
        return new AjaxResult(403, message, null);
    }
    
    /**
     * 找不到信息
     *
     * @param message 提示信息
     * @return
     */
    public static AjaxResult notData(String message) {
        return new AjaxResult(404, message, null);
    }

    /**
     * 自定义返回类型
     *
     * @param status  状态码
     * @param message 返回信息
     * @param data    返回数据
     * @return
     */
    public static AjaxResult customResponse(int status, String message, Object data) {
        return new AjaxResult(status, message, data);
    }
    /**
     * 自定义返回类型
     *
     * @param status  状态码
     * @param message 返回信息
     * @return
     */
    public static AjaxResult customResponse(int status, String message) {
        return new AjaxResult(status, message,null );
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AjaxResult{" +
                "status=" + status +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
```

