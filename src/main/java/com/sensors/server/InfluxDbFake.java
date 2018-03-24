package com.sensors.server;

import lombok.extern.slf4j.Slf4j;
import org.influxdb.InfluxDB;
import org.influxdb.dto.*;

import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Slf4j
public class InfluxDbFake implements InfluxDB {

    @Override
    public InfluxDB setLogLevel(LogLevel logLevel) {
        throw new IllegalStateException("Not supported");
    }

    @Override
    public InfluxDB enableGzip() {
        throw new IllegalStateException("Not supported");
    }

    @Override
    public InfluxDB disableGzip() {
        throw new IllegalStateException("Not supported");
    }

    @Override
    public boolean isGzipEnabled() {
        throw new IllegalStateException("Not supported");
    }

    @Override
    public InfluxDB enableBatch(int actions, int flushDuration, TimeUnit flushDurationTimeUnit) {
        throw new IllegalStateException("Not supported");
    }

    @Override
    public InfluxDB enableBatch(int actions, int flushDuration, TimeUnit flushDurationTimeUnit, ThreadFactory threadFactory) {
        throw new IllegalStateException("Not supported");
    }

    @Override
    public InfluxDB enableBatch(int actions, int flushDuration, TimeUnit flushDurationTimeUnit, ThreadFactory threadFactory, BiConsumer<Iterable<Point>, Throwable> exceptionHandler) {
        throw new IllegalStateException("Not supported");
    }

    @Override
    public void disableBatch() {
        throw new IllegalStateException("Not supported");
    }

    @Override
    public boolean isBatchEnabled() {
        throw new IllegalStateException("Not supported");
    }

    @Override
    public Pong ping() {
        throw new IllegalStateException("Not supported");
    }

    @Override
    public String version() {
        throw new IllegalStateException("Not supported");
    }

    @Override
    public void write(Point point) {
        log.info("Fake write point: {}", point.toString());
    }

    @Override
    public void write(String records) {
        log.info("Fake write records: {}", records);
    }

    @Override
    public void write(List<String> records) {
        log.info("Fake write records: {}", records.toString());
    }

    @Override
    public void write(String database, String retentionPolicy, Point point) {
        log.info("Fake write {}", point.toString());
    }

    @Override
    public void write(int udpPort, Point point) {
        log.info("Fake write point: {}", point.toString());
    }

    @Override
    public void write(BatchPoints batchPoints) {
        log.info("Fake write point: {}", batchPoints.toString());
    }

    @Override
    public void write(String database, String retentionPolicy, ConsistencyLevel consistency, String records) {
        log.info("Fake write records: {}", records);
    }

    @Override
    public void write(String database, String retentionPolicy, ConsistencyLevel consistency, List<String> records) {
        log.info("Fake write records: {}", records);
    }

    @Override
    public void write(int udpPort, String records) {
        log.info("Fake write records: {}", records);
    }

    @Override
    public void write(int udpPort, List<String> records) {
        log.info("Fake write records: {}", records);
    }

    @Override
    public QueryResult query(Query query) {
        throw new IllegalStateException("Not supported");
    }

    @Override
    public void query(Query query, int chunkSize, Consumer<QueryResult> consumer) {
        throw new IllegalStateException("Not supported");
    }

    @Override
    public QueryResult query(Query query, TimeUnit timeUnit) {
        throw new IllegalStateException("Not supported");
    }

    @Override
    public void createDatabase(String name) {
        throw new IllegalStateException("Not supported");
    }

    @Override
    public void deleteDatabase(String name) {
        throw new IllegalStateException("Not supported");
    }

    @Override
    public List<String> describeDatabases() {
        throw new IllegalStateException("Not supported");
    }

    @Override
    public boolean databaseExists(String name) {
        throw new IllegalStateException("Not supported");
    }

    @Override
    public void flush() {
        throw new IllegalStateException("Not supported");
    }

    @Override
    public void close() {
        log.info("InfluxDb closed");
    }

    @Override
    public InfluxDB setConsistency(ConsistencyLevel consistency) {
        throw new IllegalStateException("Not supported");
    }

    @Override
    public InfluxDB setDatabase(String database) {
        throw new IllegalStateException("Not supported");
    }

    @Override
    public InfluxDB setRetentionPolicy(String retentionPolicy) {
        throw new IllegalStateException("Not supported");
    }
}
