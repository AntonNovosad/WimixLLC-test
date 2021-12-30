package by.wimixllc.wimixllctest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String from;
    private String message;

    @Override
    public String toString() {
        return "Message [from=" + from + ", message=" + message + "]";
    }
}
