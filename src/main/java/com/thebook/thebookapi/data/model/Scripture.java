package com.thebook.thebookapi.data.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("scripture")
public class Scripture {
    @Id
    public String id;
    public String b;
    public String c;
    public String v;
    public String t;
}
