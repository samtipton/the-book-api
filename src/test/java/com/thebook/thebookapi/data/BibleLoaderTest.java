package com.thebook.thebookapi.data;


import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import redis.clients.jedis.Jedis;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BibleLoaderTest {
    private static final String BIBLE_JSON_FILENAME = "/home/sam/workspace/bible_databases/json/t_kjv.json";

    private static class ResultSet {
        @SerializedName("resultset")
        public ResultSetRow resultSet;
    }

    private static class ResultSetRow {
        @SerializedName("row")
        private List<Row> row;
    }

    private static class Row {
        @SerializedName("field")
        private List<JsonElement> field;
    }

    @Data
    @AllArgsConstructor
    @ToString
    private static class BibleDatabaseRow {
        private String id;
        private String b;
        private String c;
        private String v;
        private String t;
    }

    @Test
    public void loadKjv() throws IOException {
        Jedis jedis = new Jedis("redis-17838.c240.us-east-1-3.ec2.cloud.redislabs.com", 17838);
        jedis.auth("z9OW0DP2Klh99RWFESXLz4kD1ZkFL0Vb"); // need kms

        final List<BibleDatabaseRow> records = new ArrayList<>();

        try (JsonReader reader = new JsonReader(new FileReader(BIBLE_JSON_FILENAME))) {
            final ResultSet json = new Gson().fromJson(reader, ResultSet.class);

            json.resultSet.row.forEach(entry -> {
                final String id = String.valueOf(entry.field.get(0).getAsInt());
                final String book = String.valueOf(entry.field.get(1).getAsInt());
                final String chapter = String.valueOf(entry.field.get(2).getAsInt());
                final String verse = String.valueOf(entry.field.get(3).getAsInt());
                final String text = entry.field.get(4).getAsString();

                records.add(new BibleDatabaseRow(id, book, chapter, verse, text));
            });
        }

        records.forEach(record -> jedis.hset(record.id, ImmutableMap.of(
                "b", record.b,
                "c", record.c,
                "v", record.v,
                "t", record.t
        )));
    }
}
