package com.synnex.connectwise;

import connectwise.client.Response;
import connectwise.client.common.model.Count;
import connectwise.client.company.api.CompaniesApi;
import connectwise.client.company.model.Company;

import java.util.List;

public class Test {

    public static final  String cw_site = "staging.connectwisedev.com"; //this is your connectwise site without the protocol (ex: api-staging.connectwisedev.com)
    public static final  String cw_companyname = "synnex_f";
    public static final  String public_key = "xrx8KnMJpg2hltzt";
    public static final  String private_key = "ZIOHZmLGzhLZ2W8K";

    public static void main(String[] args) throws Exception{
        SynnexApiClient client=new SynnexApiClient();
        client.setBasePath(cw_site,cw_companyname);
        client.setPublicKey(public_key);
        client.setPrivateKey(private_key);
        client.setClientId("51bda27f-6e91-4d37-8c97-ac4c0185a689");
        client.setDebugging(false);

        CompaniesApi companiesApi = new CompaniesApi(client);
        try{
            Response<List<Company>> response = companiesApi.getCompanies(null, null, null, null, 1, 1, null);
            System.out.println(response.getStatusCode());

            long start  = System.currentTimeMillis();
            for(int i=0; i<1; i++){
                Response<Count> countResponse = companiesApi.getCompaniesCount("", "");
                System.out.println(countResponse.getResult().toString());
            }
            System.out.println("cost:" + (System.currentTimeMillis() - start));
        }
        finally {
            client.close();
        }
    }


}
