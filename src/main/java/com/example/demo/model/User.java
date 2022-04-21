package com.example.demo.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.annotation.sql.DataSourceDefinition;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class User implements Serializable {

    @NotBlank(message = "姓名不能为空")
    private String name;
    @Pattern(regexp = "^[0-9]+$",message = "手机号格式不对")
    private String phone;
    private Integer age;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("name='").append(name).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }
}
