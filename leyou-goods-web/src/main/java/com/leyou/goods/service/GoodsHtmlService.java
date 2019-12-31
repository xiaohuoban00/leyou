package com.leyou.goods.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


/**
 * Created by IntelliJ IDEA.
 * User: zmq
 * Date: 2019/12/29
 */
@Service
public class GoodsHtmlService {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private GoodsService goodsService;

    public void createHtml(Long spuId) {
        //初始化运行上下文
        Context context = new Context();
        context.setVariables(goodsService.loadData(spuId));
        PrintWriter printWriter = null;
        try {
            File file = new File("D:\\nginx-1.16.1\\html\\item\\" + spuId + ".html");
            printWriter = new PrintWriter(file);
            templateEngine.process("item", context, printWriter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(printWriter!=null){
                printWriter.close();
            }
        }
    }

    public void deleteHtml(Long id) {
        File file = new File("D:\\nginx-1.16.1\\html\\item\\" + id + ".html");
        file.deleteOnExit();
    }
}
