/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.shinnlove.presto.dao.hbase;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Spring集成Hbase。
 *
 * @author shinnlove.jinsheng
 * @version $Id: SpringHbaseDemo.java, v 0.1 2018-08-21 下午5:34 shinnlove.jinsheng Exp $$
 */
public class SpringHbaseDemo {

    /** Hbase注入的模板类 */
    private MyHbaseTemplate myHbaseTemplate;

    /**
     * 写数据
     *
     * @param tableName       表名(格式为："表空间：表名")
     * @param familyName      列族名
     * @param columnQualifier 列名
     * @param rowkey 行键(此处测试使用了string类型，实际可以任意类型拼接的byte[])
     * @param value
     * @param action
     * @return
     */
    public Boolean put(final String tableName, final String familyName,
                       final String columnQualifier, final String rowkey, final String value,
                       TableCallback<Boolean> action) {
        return myHbaseTemplate.execute(tableName, new TableCallback<Boolean>() {
            public Boolean doInTable(HTableInterface table) throws Throwable {
                boolean flag = false;
                try {
                    Put put = new Put(Bytes.toBytes(rowkey));
                    put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnQualifier),
                        Bytes.toBytes(value));
                    table.put(put);
                    flag = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return flag;
            }
        });
    }

    /**
     * 通过表名和key获取一行数据
     *
     * @param tableName
     * @param rowName
     * @return
     */
    public Map<String, Object> get(String tableName, String rowName) {
        return myHbaseTemplate.get(tableName, rowName, new RowMapper<Map<String, Object>>() {
            public Map<String, Object> mapRow(Result result, int rowNum) throws Exception {
                List<Cell> ceList = result.listCells();
                Map<String, Object> map = new HashMap<String, Object>();
                if (ceList != null && ceList.size() > 0) {
                    for (Cell cell : ceList) {
                        map.put(
                            Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(),
                                cell.getFamilyLength())
                                    + "_"
                                    + Bytes.toString(cell.getQualifierArray(),
                                        cell.getQualifierOffset(), cell.getQualifierLength()),
                            Bytes.toString(cell.getValueArray(), cell.getValueOffset(),
                                cell.getValueLength()));
                    }
                }
                return map;
            }
        });
    }

    /**
     * 通过表名  key 和 列族 和列 获取一个数据
     *
     * @param tableName
     * @param rowName
     * @param familyName
     * @param qualifier
     * @return (此处测试返回string类型，实际可以是根据的类型将byte[]转换出来的类型)
     */
    public String get(String tableName, String rowName, String familyName, String qualifier) {
        return myHbaseTemplate.get(tableName, rowName, familyName, qualifier,
            new RowMapper<String>() {
                public String mapRow(Result result, int rowNum) throws Exception {
                    List<Cell> ceList = result.listCells();
                    String res = "";
                    if (ceList != null && ceList.size() > 0) {
                        for (Cell cell : ceList) {
                            res = Bytes.toString(cell.getValueArray(), cell.getValueOffset(),
                                cell.getValueLength());
                        }
                    }
                    return res;
                }
            });
    }

    /**
     * 通过表名，开始行键和结束行键获取数据
     *
     * @param tableName
     * @param startRow
     * @param stopRow
     * @return
     */
    public List<Map<String, Object>> find(String tableName, String startRow, String stopRow) {
        Scan scan = new Scan();
        if (startRow == null) {
            startRow = "";
        }
        if (stopRow == null) {
            stopRow = "";
        }
        scan.setStartRow(Bytes.toBytes(startRow));
        scan.setStopRow(Bytes.toBytes(stopRow));
        /* PageFilter filter = new PageFilter(2);
         scan.setFilter(filter);*/
        return myHbaseTemplate.find(tableName, scan, new RowMapper<Map<String, Object>>() {
            public Map<String, Object> mapRow(Result result, int rowNum) throws Exception {

                List<Cell> ceList = result.listCells();
                Map<String, Object> map = new HashMap<String, Object>();
                Map<String, Map<String, Object>> returnMap = new HashMap<String, Map<String, Object>>();
                String row = "";
                if (ceList != null && ceList.size() > 0) {
                    for (Cell cell : ceList) {
                        row = Bytes.toString(cell.getRowArray(), cell.getRowOffset(),
                            cell.getRowLength());
                        String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(),
                            cell.getValueLength());
                        String family = Bytes.toString(cell.getFamilyArray(),
                            cell.getFamilyOffset(), cell.getFamilyLength());
                        String quali = Bytes.toString(cell.getQualifierArray(),
                            cell.getQualifierOffset(), cell.getQualifierLength());
                        map.put(family + "_" + quali, value);
                    }
                    map.put("row", row);
                }
                return map;
            }
        });
    }

    /**
     * Setter method for property myHbaseTemplate.
     *
     * @param myHbaseTemplate value to be assigned to property myHbaseTemplate
     */
    public void setMyHbaseTemplate(MyHbaseTemplate myHbaseTemplate) {
        this.myHbaseTemplate = myHbaseTemplate;
    }

}