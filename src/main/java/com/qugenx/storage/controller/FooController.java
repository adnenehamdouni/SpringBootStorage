package com.qugenx.storage.controller;

import com.qugenx.storage.dto.ArticleDTO;
import com.qugenx.storage.model.UserContext;
import com.qugenx.storage.model.enums.StatusCode;
import com.qugenx.storage.model.session.FooModel;
import com.qugenx.storage.storage.SessionProperties;
import com.qugenx.storage.storage.SessionStorage;
import com.qugenx.storage.utils.MyUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping(value={"/session"})
public class FooController {

    @Autowired
    private SessionStorage sessionStorage;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    String foo = "session attribute";

    @GetMapping("/session-max-interval")
    @ResponseBody
    public String retrieveMaxSessionIncativeInterval(HttpSession session) {
        return "Max Inactive Interval before Session expires: " + session.getMaxInactiveInterval();
    }

    @GetMapping("/getLocalStorage")
    @ResponseBody
    public String getLocalStorage() {
        UserContext userContext = sessionStorage.get(SessionProperties.USER_CONTEXT_ATTR);

        logger.info("MUserContext loaded from session storage = {}",
                MyUtils.convertObjectToString(userContext));

        if (null != userContext) {
            return MyUtils.convertObjectToString(userContext);
        } else {
            return "no userContext into session";
        }
    }

    @GetMapping("/setLocalStorage")
    @ResponseBody
    public String setLocalStorage() {

        UserContext userContext = new UserContext();
        userContext.setStatus(StatusCode.ARTICLE_FOUND.getLabel());
        userContext.setMessage("Article found");
        userContext.setArticles(getArticles());

        // Saving session data
        sessionStorage.put(SessionProperties.USER_CONTEXT_ATTR, userContext);

        return "save userContext to session storage";
    }

    private List<ArticleDTO> getArticles() {

        List<ArticleDTO> articles= new ArrayList<>();

        ArticleDTO articleDTO1 = ArticleDTO.builder()
                .id(1)
                .name("name 1")
                .author("author 1")
                .isDeleted(Boolean.FALSE)
                .createdAt(Calendar.getInstance().getTime())
                .updatedAt(Calendar.getInstance().getTime())
                .build();

        ArticleDTO articleDTO2 = ArticleDTO.builder()
                .id(2)
                .name("name 2")
                .author("author 2")
                .isDeleted(Boolean.TRUE)
                .createdAt(Calendar.getInstance().getTime())
                .updatedAt(Calendar.getInstance().getTime())
                .build();

        articles.add(articleDTO1);
        articles.add(articleDTO2);

        return articles;


    }


    @RequestMapping(value = "/start", method = RequestMethod.GET)
    @ResponseBody
    @Async
    public void fooMethod(HttpSession session) {
        session.setAttribute(foo,
                FooModel
                        .builder()
                        .sessionId(1L)
                        .sessionAtt("hello")
                        .build());

        FooModel fooModel = (FooModel) session.getAttribute(foo);

        logger.info("session = {} and fooModel = {} ", session, MyUtils.convertObjectToString(fooModel));


    }
}
