### session共享
> 在集群环境中做session共享是必不可少的一步.试想当用户的请求经过nginx转发到A机器进行登录,然后下一步的操作转发到了B机器,
  这个时候用户信息是存储在A机器上的web容器中,B机器就识别不了这个用户,这个时候就需要做session共享了.




#### jwt 告别session https://blog.csdn.net/change_on/article/details/71191894
> 为什么要告别session?
  有这样一个场景:系统的数据量达到千万级,需要几台服务器部署,当一个用户在其中一台服务器登录后,用session保存其登录信息,
  其他服务器怎么知道该用户登录了?(单点登录),当然解决办法有,可以用spring-session.如果该系统同时为移动端服务呢?
  移动端通过url向后台要数据,如果用session,通过sessionId识别用户,万一sessionId被截获了,别人可以利用sessionId向后台要数据,
  就有安全隐患了.所以有必要跟session说拜拜了.服务端不需要存储任何用户的信息,用户的验证应该放在客户端,jwt就是这种方式！