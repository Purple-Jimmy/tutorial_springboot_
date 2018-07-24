### @Async 异步调用
1. 注解@EnableAsync:开启异步调用
2. 线程池定义:注意 @Bean("taskExecutor") 定义线程池名字
```
@Component
public class ThreadPoolConfigurer {
    @Autowired
    private ThreadPoolParam threadPoolParam;

    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数:线程池创建时候初始化的线程数
        executor.setCorePoolSize(threadPoolParam.getCorePoolSize());
        //最大线程数:线程池最大的线程数,只有在缓冲队列满了之后才会申请超过核心线程数的线程
        executor.setMaxPoolSize(threadPoolParam.getMaxPoolSize());
        //缓冲队列:用来缓冲执行任务的队列
        executor.setQueueCapacity(threadPoolParam.getQueueCapacity());
        //允许线程的空闲时间60秒:当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
        executor.setKeepAliveSeconds(threadPoolParam.getKeepAliveSeconds());
        // executor.setAllowCoreThreadTimeOut(true);
        //线程池名的前缀
        executor.setThreadNamePrefix("MyExecutor-");
        //设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //用来设置线程池中任务的等待时间,如果超过这个时候还没有销毁就强制销毁,以确保应用最后能够被关闭,而不是阻塞住
        executor.setAwaitTerminationSeconds(60);
        // rejection-policy：当pool已经达到max size的时候,如何处理新任务
        // CALLER_RUNS:主线程直接执行该任务,执行完之后尝试添加下一个任务到线程池中,可以有效降低向线程池内添加任务的速度.就是同步执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
```
#### 异步线程池调用
1. 如何让异步调用的执行任务使用这个线程池中的资源来运行???:只需要在@Async注解中指定线程池名即可
```
@Component
public class ThreadTaskService {
    private Logger LOGGER = LoggerFactory.getLogger(ThreadTaskService.class);
    public static Random random = new Random();

    @Async("taskExecutor")
    public void doTaskOne() throws Exception {
        LOGGER.info("开始做任务一");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Thread.sleep(random.nextInt(10000));
        stopWatch.stop();
        LOGGER.info("完成任务一,耗时:" + stopWatch.getTotalTimeMillis() + "毫秒");
    }

    @Async("taskExecutor")
    public void doTaskTwo() throws Exception {
        LOGGER.info("开始做任务二");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Thread.sleep(random.nextInt(10000));
        stopWatch.stop();
        LOGGER.info("完成任务二,耗时:" + stopWatch.getTotalTimeMillis() + "毫秒");
    }

    @Async("taskExecutor")
    public void doTaskThree() throws Exception {
        LOGGER.info("开始做任务三");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Thread.sleep(random.nextInt(10000));
        stopWatch.stop();
        LOGGER.info("完成任务三,耗时:" + stopWatch.getTotalTimeMillis() + "毫秒");
    }
}
```
