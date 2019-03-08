### exchange direct
**生产者**  
```
ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("36.111.193.248");
Connection connection = factory.newConnection();
Channel channel = connection.createChannel();
// 声明交换区:1.名字 2.类型 3.持久化
channel.exchangeDeclare(EXCHANGE_NAME, "direct",false);
// 发送消息
for(int i = 0; i < 10; i++)  {
    int rand = new Random().nextInt(3);
    String severity  = LOG_LEVEL_ARR[rand];
    String message = "log : [" +severity+ "]" + UUID.randomUUID().toString();
    // 发布消息至交换区
    channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
    LOGGER.info(" [x] Sent '" + message + "'");
}
// 关闭频道和连接
channel.close();
connection.close();
```

**消费者**  
```
ConnectionFactory factory = new ConnectionFactory();
factory.setHost("36.111.193.248");
Connection connection = factory.newConnection();
Channel channel = connection.createChannel();
// 声明交换区:1.名字 2.类型 3.持久化
channel.exchangeDeclare(EXCHANGE_NAME, "direct",false);
// 设置日志级别
int rand = new Random().nextInt(3);
String severity  = LOG_LEVEL_ARR[rand];
// 声明一个临时队列
String queueName = channel.queueDeclare().getQueue();
// 绑定交换器和队列并指定routingKey
// queueBind(String queue, String exchange, String routingKey)
channel.queueBind(queueName, EXCHANGE_NAME, severity);
// 创建队列消费者
final Consumer consumer = new DefaultConsumer(channel) {
    @Override
    public void handleDelivery(String consumerTag, Envelope envelope,
                               AMQP.BasicProperties properties, byte[] body) throws IOException {
        String message = new String(body, "UTF-8");
        LOGGER.info(" queue {} Received '" + message + "'",queueName);
    }
};
//
channel.basicConsume(queueName, true, consumer);
``` 
结果:随机发送10个消息,包括3个debug,3个error和4个info,同时开启4个消费者.1个监听info,一个监听debug,2个监听error.
```
2018-02-09 14:22:55.907 |-INFO  [pool-2-thread-4] com.rabbitmq.direct.DirectReceiver [41] -|  queue amq.gen-Avsoaq8z6kgIWvYeaCz32w Received 'log : [info]75389d8d-f6be-4bdb-be47-b141eac977c4'
2018-02-09 14:22:55.917 |-INFO  [pool-2-thread-4] com.rabbitmq.direct.DirectReceiver [41] -|  queue amq.gen-Avsoaq8z6kgIWvYeaCz32w Received 'log : [info]a8a2741b-5adf-445e-8b1e-3593918f902f'
2018-02-09 14:22:55.918 |-INFO  [pool-2-thread-4] com.rabbitmq.direct.DirectReceiver [41] -|  queue amq.gen-Avsoaq8z6kgIWvYeaCz32w Received 'log : [info]053c914a-67e8-4b19-babf-68db0187d54f'
2018-02-09 14:22:55.923 |-INFO  [pool-2-thread-4] com.rabbitmq.direct.DirectReceiver [41] -|  queue amq.gen-Avsoaq8z6kgIWvYeaCz32w Received 'log : [info]2f43daea-a071-4334-9121-b52412979e12'
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
2018-02-09 14:22:55.891 |-INFO  [pool-2-thread-4] com.rabbitmq.direct.DirectReceiver [41] -|  queue amq.gen--NSI6Mbi5UEVyYYtowq7Tg Received 'log : [error]33ae28dc-3539-4557-a565-8989eb598e1d'
2018-02-09 14:22:55.900 |-INFO  [pool-2-thread-4] com.rabbitmq.direct.DirectReceiver [41] -|  queue amq.gen--NSI6Mbi5UEVyYYtowq7Tg Received 'log : [error]bd038ea9-52dd-454f-8a99-5d60d9609e05'
2018-02-09 14:22:55.901 |-INFO  [pool-2-thread-4] com.rabbitmq.direct.DirectReceiver [41] -|  queue amq.gen--NSI6Mbi5UEVyYYtowq7Tg Received 'log : [error]2fbde5df-187c-4fdf-839a-6572e18c29db'
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
2018-02-09 14:22:55.842 |-INFO  [pool-2-thread-4] com.rabbitmq.direct.DirectReceiver [41] -|  queue amq.gen-hhoEz3tqbyEx9I-a1QaUpw Received 'log : [debug]b7b8b28c-dc37-4a98-a18b-65dd63b2df21'
2018-02-09 14:22:55.851 |-INFO  [pool-2-thread-4] com.rabbitmq.direct.DirectReceiver [41] -|  queue amq.gen-hhoEz3tqbyEx9I-a1QaUpw Received 'log : [debug]9891524c-0b80-4169-aa6a-7cee9dac8845'
2018-02-09 14:22:55.852 |-INFO  [pool-2-thread-4] com.rabbitmq.direct.DirectReceiver [41] -|  queue amq.gen-hhoEz3tqbyEx9I-a1QaUpw Received 'log : [debug]e24fae73-a7a9-4558-af4d-6a4971ecc83e'
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
2018-02-09 14:22:55.861 |-INFO  [pool-2-thread-4] com.rabbitmq.direct.DirectReceiver [41] -|  queue amq.gen-7KAFsRMhEb1Ubl1cEkE7MA Received 'log : [error]33ae28dc-3539-4557-a565-8989eb598e1d'
2018-02-09 14:22:55.870 |-INFO  [pool-2-thread-4] com.rabbitmq.direct.DirectReceiver [41] -|  queue amq.gen-7KAFsRMhEb1Ubl1cEkE7MA Received 'log : [error]bd038ea9-52dd-454f-8a99-5d60d9609e05'
2018-02-09 14:22:55.870 |-INFO  [pool-2-thread-4] com.rabbitmq.direct.DirectReceiver [41] -|  queue amq.gen-7KAFsRMhEb1Ubl1cEkE7MA Received 'log : [error]2fbde5df-187c-4fdf-839a-6572e18c29db'
```