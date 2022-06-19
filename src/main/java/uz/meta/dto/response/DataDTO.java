package uz.meta.dto.response;

import lombok.Getter;

@Getter
public class DataDTO<T> {
    private T body;
    private boolean success;
    private Long total;
    private AppErrorDTO errorDTO;

    public DataDTO(T body, Long total) {
        this(body);
        this.total = total;
    }

    public DataDTO(AppErrorDTO errorDTO) {
        this.errorDTO = errorDTO;
        this.success = false;
    }
    public DataDTO(T body) {
        this.body = body;
        this.success = true;
    }


}
