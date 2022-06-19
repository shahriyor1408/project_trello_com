package uz.meta.dto.response;


import lombok.Getter;

@Getter
public class ResponseEntity<T> {
    private final T data;
    private Integer status;

    public ResponseEntity(T data) {
        this.data = data;
    }

    public ResponseEntity(T data, Integer status) {
        this.data = data;
        this.status = status;
    }
}
