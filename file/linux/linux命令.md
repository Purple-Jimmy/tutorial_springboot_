### linux命令
cat /proc/cpuinfo| grep "physical id"| sort| uniq| wc -l

cat /proc/cpuinfo| grep "processor"| wc -l

命令参数：
-a：这个好像是全部数据的刷新时间周期，单位是秒，默认是300.
-i：进入网卡的流量图的显示比例最大值设置，默认10240 kBit/s.
-m：不显示流量图，只显示统计数据。
-o：出去网卡的流量图的显示比例最大值设置，默认10240 kBit/s.
-t：显示数据的刷新时间间隔，单位是毫秒，默认500。
-u：设置右边Curr、Avg、Min、Max的数据单位，默认是自动变的.注意大小写单位不同！
h|b|k|m|g h: auto, b: Bit/s, k: kBit/s, m: MBit/s etc.
H|B|K|M|G H: auto, B: Byte/s, K: kByte/s, M: MByte/s etc.
-U：设置右边Ttl的数据单位，默认是自动变的.注意大小写单位不同（与-u相同）！
Devices：自定义监控的网卡，默认是全部监控的，使用左右键切换。
如只监控eth0命令：# nload eth0
使用 $ nload eth0 ，可以查看第一网卡的流量情况，显示的是实时的流量图， $ nload -m 可以同时查看多个网卡的流量情况

# 总核数 = 物理CPU个数 X 每颗物理CPU的核数 # 总逻辑CPU数 = 物理CPU个数 X 每颗物理CPU的核数 X 超线程数
# 查看物理CPU个数 
cat /proc/cpuinfo| grep "physical id"| sort| uniq| wc -l
# 查看每个物理CPU中core的个数(即核数)
cat /proc/cpuinfo| grep "cpu cores"| uniq
# 查看逻辑CPU的个数 cat /proc/cpuinfo| grep "processor"| wc -l