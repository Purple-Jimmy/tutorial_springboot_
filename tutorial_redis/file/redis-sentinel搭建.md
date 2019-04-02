# redis-sentinel 搭建
## redis安装
redis-3.2.4.tar.gz版本为例
1. 创建文件夹redis-cluster,在此执行wget http://download.redis.io/releases/redis-3.2.4.tar.gz
2. 解压 tar -zxvf redis-3.2.4.tar.gz
3. 编译,安装
```
进入 cd redis-3.2.4
编译 make
安装 make install
```
4. 配置,修改redis.conf
```
daemonize yes 后台启动
bind127.0.0.1 注释掉使得可以远程访问
protected-mode no 设置为no
设置登录认证 redis.conf中的requirepass属性
```


## redis主从
6579 主  
6580 从  
在从的conf中配置,重启redis,通过info命令查看主从信息
```
slaveof ip 6579



[root@izuf64u1jmuzim8azzt99bz ~]# redis-cli -p 6579
127.0.0.1:6579> info
# Server
redis_version:3.2.4
redis_git_sha1:00000000
redis_git_dirty:0
redis_build_id:4e27bfb0ac79c74d
redis_mode:standalone
os:Linux 3.10.0-862.14.4.el7.x86_64 x86_64
arch_bits:64
multiplexing_api:epoll
gcc_version:4.8.5
process_id:12135
run_id:6d184673e1f775f0e3f7566828b7baf9e20fc8a7
tcp_port:6579
uptime_in_seconds:264
uptime_in_days:0
hz:10
lru_clock:10604680
executable:/root/redis-cluster/redis-3.2.4/src/redis-server
config_file:/root/redis-cluster/redis-3.2.4/redis.conf

# Clients
connected_clients:1
client_longest_output_list:0
client_biggest_input_buf:0
blocked_clients:0

# Memory
used_memory:1931840
used_memory_human:1.84M
used_memory_rss:9818112
used_memory_rss_human:9.36M
used_memory_peak:1931840
used_memory_peak_human:1.84M
total_system_memory:1927921664
total_system_memory_human:1.80G
used_memory_lua:37888
used_memory_lua_human:37.00K
maxmemory:0
maxmemory_human:0B
maxmemory_policy:noeviction
mem_fragmentation_ratio:5.08
mem_allocator:jemalloc-4.0.3

# Persistence
loading:0
rdb_changes_since_last_save:0
rdb_bgsave_in_progress:0
rdb_last_save_time:1554108312
rdb_last_bgsave_status:ok
rdb_last_bgsave_time_sec:0
rdb_current_bgsave_time_sec:-1
aof_enabled:0
aof_rewrite_in_progress:0
aof_rewrite_scheduled:0
aof_last_rewrite_time_sec:-1
aof_current_rewrite_time_sec:-1
aof_last_bgrewrite_status:ok
aof_last_write_status:ok

# Stats
total_connections_received:2
total_commands_processed:245
instantaneous_ops_per_sec:0
total_net_input_bytes:8656
total_net_output_bytes:5908420
instantaneous_input_kbps:0.02
instantaneous_output_kbps:0.00
rejected_connections:0
sync_full:1
sync_partial_ok:0
sync_partial_err:0
expired_keys:0
evicted_keys:0
keyspace_hits:0
keyspace_misses:0
pubsub_channels:0
pubsub_patterns:0
latest_fork_usec:723
migrate_cached_sockets:0

# Replication
role:master
connected_slaves:1
slave0:ip=127.0.0.1,port=6580,state=online,offset=337,lag=0
master_repl_offset:337
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:2
repl_backlog_histlen:336

# CPU
used_cpu_sys:0.10
used_cpu_user:0.09
used_cpu_sys_children:0.00
used_cpu_user_children:0.00

# Cluster
cluster_enabled:0
```


## redis哨兵
哨兵是一个单独的程序,所以需要单独部署.  
新建一个文件加redis-sentinel,如上安装一遍redis,然后在3个redis文件夹中redis/conf/sentinel.conf都增加如下内容
```
# 禁止保护模式
protected-mode no
port 26379
sentinel monitor master1 10.0.0.10 6379 2
sentinel down-after-milliseconds master1 30000   //哨兵程序每5秒检测一次Master是否正常
sentinel failover-timeout master1 900000
sentinel parallel-syncs master1 1
#sentinel auth-pass mymaster 123456　　#如果你的redis集群有密码
```

启动sentinel:
```
redis-sentinel程序
redis-sentinel /path/to/sentinel.conf

redis-server程序
redis-server /path/to/sentinel.conf --sentinel & //(&有这可以Ctrl +C退到命令行,没有这个就直接退出哨兵进程)




添加窗口
screen -S sentinel


在新窗口启动哨兵
/opt/redis/src/redis-sentinel /opt/redis/conf/sentinel.conf --protected-mode no
启动后即可看到前台输出信息。


后台挂起这个窗口请按：
Ctrl+a+d


下次返回观看这个窗口请输入
screen -r sentinel

```

验证sentinel:
```
[root@izuf64u1jmuzim8azzt99bz src]# redis-cli -p 26379
127.0.0.1:26379> info
# Server
redis_version:3.2.4
redis_git_sha1:00000000
redis_git_dirty:0
redis_build_id:4e27bfb0ac79c74d
redis_mode:sentinel
os:Linux 3.10.0-862.14.4.el7.x86_64 x86_64
arch_bits:64
multiplexing_api:epoll
gcc_version:4.8.5
process_id:5178
run_id:0e9418ad5de9b47770ca82fa00eba91544291f72
tcp_port:26379
uptime_in_seconds:6171
uptime_in_days:0
hz:10
lru_clock:10680563
executable:/root/redis-cluster/redis-3.2.4/src/redis-sentinel
config_file:/root/redis-cluster/redis-3.2.4/sentinel.conf

# Clients
connected_clients:3
client_longest_output_list:0
client_biggest_input_buf:0
blocked_clients:0

# CPU
used_cpu_sys:7.44
used_cpu_user:5.97
used_cpu_sys_children:0.00
used_cpu_user_children:0.00

# Stats
total_connections_received:3
total_commands_processed:17785
instantaneous_ops_per_sec:2
total_net_input_bytes:999185
total_net_output_bytes:106664
instantaneous_input_kbps:0.13
instantaneous_output_kbps:0.02
rejected_connections:0
sync_full:0
sync_partial_ok:0
sync_partial_err:0
expired_keys:0
evicted_keys:0
keyspace_hits:0
keyspace_misses:0
pubsub_channels:0
pubsub_patterns:0
latest_fork_usec:0
migrate_cached_sockets:0

# Sentinel
sentinel_masters:1
sentinel_tilt:0
sentinel_running_scripts:0
sentinel_scripts_queue_length:0
sentinel_simulate_failure_flags:0
master0:name=mymaster,status=ok,address=47.101.149.94:6579,slaves=2,sentinels=3
```

关闭sentinel:
```
pkill redis-server   //这个会关掉Redis服务器和Sentinel(哨兵)进程
kill 进程号           //可以关掉指定进程号的进程
```

## 命令
```
info replication
```

① INFO
    sentinel的基本状态信息

②SENTINEL masters
   列出所有被监视的主服务器，以及这些主服务器的当前状态

③ SENTINEL slaves
   列出给定主服务器的所有从服务器，以及这些从服务器的当前状态

④SENTINEL get-master-addr-by-name
    返回给定名字的主服务器的 IP 地址和端口号

⑤SENTINEL reset
    重置所有名字和给定模式 pattern 相匹配的主服务器。重置操作清除主服务器目前的所有状态， 包括正在执行中的故障转移， 并移除目前已经发现和关联的， 主服务器的所有从服务器和 Sentinel 。

⑥SENTINEL failover
   当主服务器失效时， 在不询问其他 Sentinel 意见的情况下， 强制开始一次自动故障迁移，但是它会给其他sentinel发送一个最新的配置，其他sentinel会根据这个配置进行更新
   
   
注意启动的顺序。首先是主机（192.168.11.128）的Redis服务进程，然后启动从机的服务进程，最后启动3个哨兵的服务进程


/root/redis-cluster/redis-slaver/redis-3.2.4/src/redis-sentinel /root/redis-cluster/redis-slaver/redis-3.2.4/sentinel.conf --protected-mode no