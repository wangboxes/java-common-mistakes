package org.geekbang.time.commonmistakes._11_nullvalue._02_pojonull;

import lombok.Data;

import java.util.Optional;

@Data
public class UserDto {
    private Long id;
    //巧妙使用了 Optional 来区分客户端不传值和传 null 值
    private Optional<String> name;
    private Optional<Integer> age;
}
