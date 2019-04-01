package com.controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by sunxusen on 2018/7/22.
 */
public class BaseController {

    protected void write(HttpServletResponse response, Object data) {
        PrintWriter printWriter = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/plain; charset=utf-8");
            printWriter = response.getWriter();
            printWriter.print(data);
        } catch (IOException ex) {

        } finally {
            if (null != printWriter) {
                printWriter.flush();
                printWriter.close();
            }
        }
    }
}
