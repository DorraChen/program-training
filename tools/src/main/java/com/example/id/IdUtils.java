package com.example.id;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;

/**
 * @author dorra
 * @date 2022/1/5 13:36
 * @description id唯一生成(雪花算法id)
 */
@Slf4j
public class IdUtils {
    private volatile static IdUtils idUtils = null;

    public static IdUtils getIdUtils() {
        if (idUtils == null) {
            synchronized (IdUtils.class) {
                if (idUtils == null) {
                    idUtils = new IdUtils();
                }
            }
        }
        return idUtils;
    }

    // ==============================Fields===========================================
    /**
     * 开始时间截 (2021-09-23)
     */
    private final long twepoch = 1632326400000L;

    /**
     * 机器id所占的位数
     */
    private static final long workerIdBits = 5L;

    /**
     * 数据标识id所占的位数
     */
    private final long datacenterIdBits = 5L;

    /**
     * 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 支持的最大数据标识id，结果是31
     */
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /**
     * 序列在id中占的位数
     */
    private final long sequenceBits = 12L;

    /**
     * 机器ID向左移12位
     */
    private final long workerIdShift = sequenceBits;

    /**
     * 数据标识id向左移17位(12+5)
     */
    private final long datacenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间截向左移22位(5+5+12)
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 工作机器ID(0~31)
     */
    private static long workerId;

    /**
     * 数据中心ID(0~31)
     */
    private long datacenterId;

    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 1L;

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    /**
     * 获取Long
     *
     * @return
     */
    public Long getNextLongId() {
        IdVo idVo = this.nextId();
        return idVo.getNextLongId();
    }

    /**
     * 获取
     *
     * @return
     */
    public String getNextStringId() {
        IdVo idVo = this.nextId();
        return idVo.getNextStringId();
    }

    static {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            String ip = addr.getHostAddress().toString();
            workerId = Long.valueOf(ip.replace(".", "")) % 31;
            log.info("------------------------------------------");
            log.info("-----------------初始化workerId:%s--------------", workerId);
            log.info("------------------------------------------");
            if (workerId > maxWorkerId || workerId < 0) {
                throw new IllegalArgumentException(
                        String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
            }
        } catch (Exception e) {
            log.error("{result:%s}", "ip获取异常，唯一id无法生成，系统强制关闭", e);
            throw new RuntimeException("id create exception");
        }
    }

    // ==============================Constructors=====================================

    /**
     * 构造函数
     *
     * @param datacenterId 数据中心ID (0~31)
     */
    private IdUtils(long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0 || datacenterId > 9) {
            throw new IllegalArgumentException(
                    String.format("datacenter Id can't be greater than %d  or less than 0", 9));
        }
        this.datacenterId = datacenterId;
    }

    /**
     * 构造函数
     */
    private IdUtils() {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        this.datacenterId = 9;
    }

    // ==============================Methods==========================================

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return IdVo
     */
    public synchronized IdVo nextId() {
        long timestamp = timeGen();
        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        // 如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            // 毫秒内序列溢出
            if (sequence == 1) {
                // 阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        // 时间戳改变，毫秒内序列重置
        else {
            sequence = 1L;
        }

        // 上次生成ID的时间截
        lastTimestamp = timestamp;

        IdVo idVo = new IdVo();
        idVo.setNextLongId(((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift) | sequence);
        idVo.setNextStringId(new StringBuffer(DateUtil.fomartToNumber(timestamp)).append(datacenterId).append(workerId)
                .append(String.format("%04d", this.sequence)).toString());
        // 移位并通过或运算拼到一起组成64位的ID
        return idVo;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    // ==============================Test=============================================

    /**
     * 测试
     */
    public static void main(String[] args) {
        IdUtils idWorker = new IdUtils();
        for (int i = 0; i < 100; i++) {
            IdVo id = idWorker.nextId();
            System.out.println(id.getNextLongId() + "\t" + id.getNextStringId());
//            System.out.println(id.getNextLongId().toString().length());
//            System.out.println(id.getNextStringId());
        }
        /*
         * 使用方法:
             IdUtils idUtils = IdUtils.getIdUtils();
             Long id = idUtils.getNextLongId();
         */
    }
}
