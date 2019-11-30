package com.qugenx.storage.model.session;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FooModel {

    private long sessionId;
    private String sessionAtt;
}
