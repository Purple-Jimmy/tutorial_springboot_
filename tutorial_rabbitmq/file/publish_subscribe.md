### publish subscribe 发布订阅
一个信息给多个消费者,这种模式被称为"发布/订阅".   
不处理路由键,只需要简单的将队列绑定到交换机上,一个发送到交换机的消息都会被转发到与该交换机绑定的所有队列上. 

**生产者**
```
ConnectionFactory factory = new ConnectionFactory();
factory.setHost("36.111.193.248");
Connection connection = factory.newConnection();
Channel channel = connection.createChannel();
// 声明交换区:1.名字 2.类型 3.持久化
channel.exchangeDeclare(EXCHANGE_NAME, "fanout",false);
// 发送消息
String message = "service log.";
channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
LOGGER.info(" [x] Sent '" + message + "'");
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
channel.exchangeDeclare(EXCHANGE_NAME, "fanout",false);
// 声明临时队列
String queueName = channel.queueDeclare().getQueue();
// 绑定交换器和队列:不要设置routingKey
// 1.队列名 2.交换区名 3.routingKey
channel.queueBind(queueName, EXCHANGE_NAME,"");
// 创建队列消费者
final Consumer consumer = new DefaultConsumer(channel) {
    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                               byte[] body) throws IOException {
        String message = new String(body, "UTF-8");
        LOGGER.info(" queue {} Received '" + message + "'",queueName);
    }
};
channel.basicConsume(queueName, true, consumer);
```
结果:发送一条消息,开启3个消费者都收到消息.
```
2018-02-09 11:24:06.610 |-INFO  [pool-2-thread-4] com.rabbitmq.pubsub.SubReceiver [35] -|  queue amq.gen-tMflzEL5gwzK3jAqEGDVhg Received 'service log.'
--------------------------------------------------------------------------------------------------------------------------------------------------------
2018-02-09 11:24:06.637 |-INFO  [pool-2-thread-4] com.rabbitmq.pubsub.SubReceiver [35] -|  queue amq.gen-dvtFXiCSx5yUboPXTyETvg Received 'service log.'
--------------------------------------------------------------------------------------------------------------------------------------------------------
2018-02-09 11:24:06.610 |-INFO  [pool-2-thread-4] com.rabbitmq.pubsub.SubReceiver [35] -|  queue amq.gen-wgLk9c7uoZf076vT1btkxQ Received 'service log.'
```