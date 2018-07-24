### session
```
在计算机中,尤其是在网络应用中,称为"会话控制".Session 对象存储特定用户会话所需的属性及配置信息,这样,
当用户在应用程序的 Web 页之间跳转时,存储在 Session 对象中的变量将不会丢失,而是在整个用户会话中一直存在下去.
当用户请求来自应用程序的 Web 页时,如果该用户还没有会话,则 Web 服务器将自动创建一个 Session 对象.
当会话过期或被放弃后,服务器将终止该会话.Session 对象最常见的一个用法就是存储用户的首选项.

服务器可以为每个用户浏览器创建一个会话对象(session对象),一个浏览器只能产生一个session,当新建一个窗口访问服务器时,
还是原来的那个session.session中默认保存的是当前用户的信息.因此,在需要保存其他用户数据时,我们可以自己给session添加属性.
session(会话)可以看为是一种标识,通过带session的请求,可以让服务器知道是谁在请求数据.
```

### session与cookie的区别和联系
1. session由服务器创建并保存在服务器上.在session创建好之后,会把sessionId放在cookie中返回(response)给客户端,返回的cookie保存在客户端.
2. 以后的每次HTTP请求都会带着sessionId,来跟踪用户的整个会话.
3. session的过期和超时与cookie的过期没有什么联系,都是可以分别进行设置的.但是当session或cookie中任意一方过期,那么用户就需要重新登录了.





#### spring security 下session的三种管理
1. session 超时
```

```