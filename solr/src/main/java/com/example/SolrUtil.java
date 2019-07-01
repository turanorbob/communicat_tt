package com.example;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.io.IOException;
import java.util.List;

/**
 * @Description
 * @Author legend <legendl@synnex.com>
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2019/6/14
 */
public class SolrUtil {

    public static void main(String args[]) throws IOException, SolrServerException {
        String solr_url = "http://localhost:32779/solr/new_core";

        HttpSolrClient solr = null;
        solr = new HttpSolrClient.Builder(solr_url).withConnectionTimeout(10000).withSocketTimeout(60000).build();

        SolrQuery query = new SolrQuery();
        query.set("q", "name:Foundation");

        query.setStart(0);
        query.setRows(10);

        query.setHighlight(true);
        query.addHighlightField("name");
        query.setHighlightSimplePre("<font color='red'>");
        query.setHighlightSimplePost("</font>");

        QueryResponse response = solr.query(query);

        List<Book> bookList = response.getBeans(Book.class);

        bookList.forEach(book -> {
            System.out.println(book);
        });
    }
}
