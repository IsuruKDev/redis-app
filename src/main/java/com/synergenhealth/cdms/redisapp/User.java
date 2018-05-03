package com.synergenhealth.cdms.redisapp;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@RedisHash("users")
public class User implements Serializable {

    @Id
    private Long id;
    @Indexed
    private String name;
    private String gender;
    private int age;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("name:: "+name).append(", age:: "+age).append(", id:: "+id);
        return sb.toString();
    }
}
