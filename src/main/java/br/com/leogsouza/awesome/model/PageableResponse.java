package br.com.leogsouza.awesome.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class PageableResponse<T> extends PageImpl<T> {

    public PageableResponse(@JsonProperty("content")List<T> content,
                            @JsonProperty("number") int page,
                            @JsonProperty("size") int size,
                            @JsonProperty("totalElements") Long totalElements
                            ) {
        super(content, PageRequest.of(page, size), totalElements);
    }

    public PageableResponse() {
        super(new ArrayList<>());
    }
}
