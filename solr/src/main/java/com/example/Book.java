package com.example;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

import java.util.List;

/**
 * @Description
 * @Author legend <legendl@synnex.com>
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2019/6/14
 */
@Data
public class Book {
    @Field("id")
    private String id;
    @Field("name")
    private List<String> name;
    @Field("price")
    private List<String> price;
    @Field("author")
    private List<String> author;
}
