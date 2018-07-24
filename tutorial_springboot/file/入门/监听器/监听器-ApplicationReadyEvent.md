### ApplicationReadyEvent 事件
该事件表示application应用初始化完成,可以准备接收请求.
```
@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent>, Ordered {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        System.out.println("############started");
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
```
在tomcat启动,spring mvc初始化完成之后触发,表示应用已经可以接收请求.