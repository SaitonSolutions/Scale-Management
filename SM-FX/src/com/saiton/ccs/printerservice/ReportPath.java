/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saiton.ccs.printerservice;


public enum ReportPath {


    PATH_WEIGHT_ONE_REPORT(".//Reports//WeightScale1.jasper"),
    PATH_WEIGHT_TWO_REPORT(".//Reports//WeightScale2.jasper"),
    PATH_WEIGHT_INFO(".//Reports//WeightInfo.jasper");

    private final String val;

    private ReportPath(String val) {

        this.val = val;
    }

    @Override
    public String toString() {
        return val.toString();
    }
}
