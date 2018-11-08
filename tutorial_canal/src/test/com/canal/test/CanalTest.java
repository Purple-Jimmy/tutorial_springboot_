package com.canal.test;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry.*;
import com.alibaba.otter.canal.protocol.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * Created by Jimmy. 2018/5/29  14:58
 * insert into book(name) values('test');
 */
public class CanalTest {
    private static final Logger logger = LoggerFactory.getLogger(CanalTest.class);

    public static void main(String[] args) {
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("192.168.50.247",
                11111), "search", "canal", "canal");
        int batchSize = 1000;
        try {
            connector.connect();
            connector.subscribe();
            connector.rollback();
            while (true) {
                // 获取指定数量的数据
                Message message = connector.getWithoutAck(batchSize);
                long batchId = message.getId();
                int size = message.getEntries().size();
                if (batchId == -1 || size == 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    printEntry(message.getEntries());
                }
                // 提交确认
                connector.ack(batchId);
                //connector.rollback(batchId); // 处理失败, 回滚数据
            }
        } finally {
            connector.disconnect();
        }
    }


    /**
     * 打印记录
     * @param entrys 记录
     */
    private static void printEntry(List<Entry> entrys) {
        for (Entry entry : entrys) {
            System.out.println(entry.getEntryType());
            logger.info(""+entry.getEntryType());
            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                continue;
            }
            RowChange rowChage = null;
            String tableName = "";
            try {
                rowChage = RowChange.parseFrom(entry.getStoreValue());
                tableName = entry.getHeader().getTableName();
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                        e);
            }
            EventType eventType = rowChage.getEventType();
            logger.info(String.format("================> binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                    eventType));
            System.out.println(String.format("================> binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                    eventType));

           /* for (RowData rowData : rowChage.getRowDatasList()) {
                if (eventType == EventType.DELETE) {
                    redisDelete(rowData.getBeforeColumnsList(),tableName);
                } else if (eventType == EventType.INSERT) {
                    redisInsert(rowData.getAfterColumnsList(),tableName);
                } else {
                    logger.info("-------> before");
                    printColumn(rowData.getBeforeColumnsList(),tableName);
                    logger.info("-------> after");
                    redisUpdate(rowData.getAfterColumnsList(),tableName);
                }
            }*/
        }
    }

    /**
     * 打印列
     * @param columns 列对象
     * @param tableName 表命
     */
    private void printColumn( List<Column> columns,String tableName) {
        for (Column column : columns) {
            logger.info(tableName+column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
        }
    }

}
