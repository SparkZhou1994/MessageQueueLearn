# 1 集群介绍
![图 1.1 集群整体架构](https://github.com/SparkZhou1994/MessageQueueLearn/blob/master/RabbitMQ_Cluster.png "集群整体架构")

如图1.1 所示，底层RabbitMQ集群中节点（Node）进行消息同步，采用多节点的方式实现高可用；中间HAProxy进行负载均衡，采用Keepalived双机热备的方式，实现高可用；终端分别为消息生产者和消息消费者。

## 1.1 物理架构介绍

世界上并没用完全高可用的服务，在物理资源和高可用的平衡中做出如下部署：

应用名|所在物理机的IP地址
:--:|:--:
负载均衡-HAProxy（主） | 192.168.4.155
负载均衡-HAProxy（备） | 192.168.4.156
RabbitMQ（节点1） | 192.168.4.150
RabbitMQ（节点2） | 192.168.4.151
RabbitMQ（节点3） | 192.168.4.152

除了Keepalived的配置在不同物理机上会有所不同，其他的相同应用对应的配置都是相同的。

## 1.2 软件介绍

软件名|版本
:--:|:--:
CentOS | release 6.7 (Final) X64
Erlang | 21.3.8.9-1.el6.x86_64
Rabbitmq-Server | 3.7.19-1.el6
Haproxy | 1.5.18-1.el6.x86_64
Keepalived | 1.2.13-5.el6_6.x86_64

# 2 集群部署
## 2.1 RabbitMQ集群的连通

已知RabbitMQ已经部署在各个服务器上（本示例的192.168.4.150，192.168.4.151，192.168.4.152），将单机应用进行集群化的步骤如下（以下步骤可切换root用户）：

1. 修改主机名，以便连接各主机  
`[root@node1 ~]# vi /etc/sysconfig/network`  
可将192.168.4.150的服务器取名node1，修改如下：  
`HOSTNAME=node1`  
同理将192.168.4.151，192.168.4.152改为node2和node3  
2. 修改host文件，以便局网内服务器间的访问，针对所有服务器（192.168.4.150，192.168.4.151，192.168.4.152，192.168.4.155，192.168.4.156）  
`[root@node1 ~]# vi /etc/hosts`  
添加所有node节点的ip地址映射关系，如下  
(```)
192.168.4.150 node1  
192.168.4.151 node2  
192.168.4.152 node3  
(```)
3. 启用RabbitMQ的插件  
`[root@node1 ~]# rabbitmq-plugins enable rabbitmq_management`  
4. 统一各个RabbitMQ节点的cookie，确保cookie的值相同，可通过复制的方式  
`[root@node1 ~]# scp /var/lib/rabbitmq/.erlang.cookie root@node2:/var/lib/rabbitmq`  
**确保此cookie文件权限为400**  
同理，也复制一份至node3（192.168.4.152）
5. 重启所有RabbitMQ应用（192.168.4.150，192.168.4.151，192.168.4.152）
(```)
[root@node1 ~]# rabbitmqctl stop  
[root@node1 ~]# rabbitmq-server –detached  
(```)
**--detached参数很重要**  
6. RabbitMQ集群中从节点添加主节点  
在本示例中（192.168.4.150不做操作）  
在192.168.4.151服务器上操作如下：
(```)
[root@node2 ~]# rabbitmqctl stop_app
[root@node2 ~]# rabbitmqctl reset  
[root@node2 ~]# rabbitmqctl join_cluster rabbit@node1  
[root@node2 ~]# rabbitmqctl start_app
(```)
同理，在192.168.4.152服务器上添加 rabbit@node2  
7. 查看节点是否成功添加  
在各个RabbitMQ节点上运行：  
`[root@node1 ~]# rabbitmqctl cluster_status`  
如果看到其他节点，即表示节点添加成功  
(```)
Cluster status of node rabbit@node1 ...
[{nodes,[{disc,[rabbit@node1,rabbit@node2,rabbit@node3]}]},  
 {running_nodes,[rabbit@node1]},  
 {cluster_name,<<"rabbit@localhost">>},   
 {partitions,[{rabbit@node1,[rabbit@node2,rabbit@node3]}]},  
 {alarms,[{rabbit@node1,[]}]}]
(```) 
也可通过RabbitMQ管理控制台页面进行查看，步骤如下：  
(```)
[root@node1 ~]# rabbitmqctl add_user Spark Spark  
[root@node1 ~]# rabbitmqctl set_user_tags Spark administrator  
[root@node1 ~]# rabbitmqctl set_permissions -p "/" Spark ".*" ".*" ".*"  
(```)
登录网址[RabbitMQ管理控制台](http://192.168.4.150:15672/)  
看到Nodess栏有自己配置的节点即可  
![图1.2 RabbitMQ 管理控制台](https://github.com/SparkZhou1994/MessageQueueLearn/blob/master/RabbitMQ_Management.png "RabbitMQ 管理控制台")

## 2.2 Haproxy安装配置
1. 安装Haproxy，同理在haproxy2（192.168.4.156）也进行安装  
`[root@haproxy1 /]# yum -y install haproxy`  
2. 备份配置文件  
`[root@haproxy1 /]# cp /etc/haproxy/haproxy.cfg /etc/haproxy/haproxy.cfg.bak`  
3. 修改配置文件  
`[root@haproxy1 /]# vi /etc/haproxy/haproxy.cfg`  
添加如下配置：
# 配置haproxy管理界面，可登录[Haproxy管理界面](http://192.168.4.150:8100/stats)
用户名/密码：admin/admin
(```)
listen private_monitoring
        bind 0.0.0.0:8100  
        mode http  
        option httplog  
        stats refresh 5s  
        stats uri /stats  
        stats realm Haproxy  
        stats auth admin:admin
(```)
### 配置RabbitMQ管理控制台页面，可登录[RabbitMQ 管理控制台](http://192.168.4.150:8102)  进行查看
(```)
listen rabbitmq_admin
        bind 0.0.0.0:8102  
        server node1 node1:15672  
        server node2 node2:15672  
        server node3 node3:15672
(```)
### 配置RabbitMQ应用服务，服务地址[RabbitMQ 服务地址](192.168.4.150:8101)
(```)
listen rabbitmq_cluster  
        bind 0.0.0.0:8101  
        mode    tcp  
        option  tcplog  
        balance roundrobin  
        timeout client  3h  
        timeout server  3h  
        server  node1  node1:5672  check  inter  5000  rise  2  fall  3  
        server  node2  node2:5672  check  inter  5000  rise  2  fall  3  
        server  node3  node3:5672  check  inter  5000  rise  2  fall  3
(```)
4. 关闭防火墙
(```)
[root@haproxy1 /]# service iptables stop
[root@haproxy1 /]# chkconfig iptables off
(```)
5. 启动Haproxy
`[root@haproxy1 /]# haproxy -f /etc/haproxy/haproxy.cfg`  
## 2.3 Keepalived安装配置  
1. 安装Keepalived   
`[root@haproxy1 /]# yum install -y keepalived`  
2. 备份配置文件  
`[root@haproxy1 /]# cp /etc/keepalived/keepalived.conf /etc/keepalived/keepalived.conf.bk`  
3. 修改配置文件  
`[root@haproxy1 /]# vi /etc/keepalived/keepalived.conf`  
对于haproyx1服务器（192.168.4.155），修改如下：
(```)
global_defs {
   router_id LVS_DEVEL01  
}  
vrrp_instance VI_1 {  
    state MASTER  
    interface eth0  
    virtual_router_id 51  
    priority 100  
    advert_int 1  
    authentication {  
        auth_type PASS  
        auth_pass 1111  
    }  
    virtual_ipaddress {  
        192.168.4.160  
    }  
}
(```)
注意Haproxy（主，192.168.4.155）的priority为100高于Haproxy（备，192.168.4.156），主备节点virtual_ipaddress配置的VIP要一致，router_id需要不同。  
`[root@haproxy2 /]# vi /etc/keepalived/keepalived.conf`  
对于haproyx2服务器（192.168.4.156），修改如下：
(```)
global_defs {
   router_id LVS_DEVEL02  
}  
vrrp_instance VI_1 {  
    state MASTER  
    interface eth0  
    virtual_router_id 51  
    priority 90  
    advert_int 1  
    authentication {  
        auth_type PASS  
        auth_pass 1111  
    }  
    virtual_ipaddress {  
        192.168.4.160   
    }  
}
(```)
4. 各个Haproxy节点启动Keepalived    
`[root@haproxy1 /]# service keepalived start`  
