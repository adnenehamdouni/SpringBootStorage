package com.qugenx.storage.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.qugenx.storage.dto.ArticleDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class UserContext implements Serializable {

    private String status;
    private String message;
    private List<ArticleDTO> articles;

    public UserContext(){}

    @JsonCreator
    public UserContext(@JsonProperty("status") String status,
                       @JsonProperty("message") String message,
                       @JsonProperty("articles") List<ArticleDTO> articles) {
        this.status = status;
        this.message = message;
        this.articles = articles;
    }

}
