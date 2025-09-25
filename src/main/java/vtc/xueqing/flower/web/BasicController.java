/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package vtc.xueqing.flower.web;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Api(tags = "测试接口")  // 类级别注解
@Slf4j
@RestController("/test")
public class BasicController {

    // http://127.0.0.1:8080/hello?name=lisi
    @Operation(summary = "测试hello") // 改用 Springdoc 注解
    @GetMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        return "Hello " + name;
    }

    // http://127.0.0.1:8080/user
    @GetMapping("/user")
    @ResponseBody
    public User user() {
        log.info("我被调用了");

        // 模拟数据库查询
        User user = new User();
        user.setName("Simon");
        user.setAge(666);
        return user;
    }

    @GetMapping("/users")
    @ResponseBody
    public List<User> users() {
        log.info("获取测试用户数据/users");
        List<User> users = new ArrayList<>();

        // 生成多个不同特征的用户数据
        User user1 = new User();
        user1.setName("张三");
        user1.setAge(28);
        users.add(user1);

        User user2 = new User();
        user2.setName("李四");
        user2.setAge(35);
        users.add(user2);

        User user3 = new User();
        user3.setName("王五");
        user3.setAge(22);
        users.add(user3);

        return users;
    }

    // http://127.0.0.1:8080/save_user?name=newName&age=11
    @GetMapping("/save_user")
    @ResponseBody
    public String saveUser(User u) {
        return "user will save: name=" + u.getName() + ", age=" + u.getAge();
    }

    // http://127.0.0.1:8080/html
    @GetMapping("/html")
    public String html() {
        return "index.html";
    }

    @ModelAttribute
    public void parseUser(@RequestParam(name = "name", defaultValue = "unknown user") String name
            , @RequestParam(name = "age", defaultValue = "12") Integer age, User user) {
        user.setName("zhangsan");
        user.setAge(18);
    }
}
