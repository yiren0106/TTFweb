package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yiren
 * @Description
 * @create 2024/1/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private String account;
    private String password;
}