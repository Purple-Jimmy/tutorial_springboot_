### remember me 记住密码功能,实现自动登录
"记住我"是持久登录认证,应用程序会记住会话用户的身份.基本上在登录时当你使用"记住我"功能支持,应用程序会将一个cookie在登录成功后发送到浏览器.
这个cookie将被存储在浏览器端,并继续在一定期限(由cookie生命过期时间定义),下一次当您尝试访问该应用程序浏览器将检测到的cookie(如果仍然有效),那么用户将会自动登录无需提供如用户ID/密码.

* 建表语句
```
CREATE TABLE persistent_logins (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
); 
```