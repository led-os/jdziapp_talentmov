package com.movjdzi.app.adapter;

import com.movjdzi.app.model.vo.CommonVideoVo;

import java.util.ArrayList;

/**
 * @author huangyong
 * createTime 2019-09-15
 */
public class DetailRecmmendSection {

    public ArrayList<CommonVideoVo> getVideoVos() {
        return videoVos;
    }

    private ArrayList<CommonVideoVo> videoVos;

    public DetailRecmmendSection(ArrayList<CommonVideoVo> videoVos) {
        this.videoVos = videoVos;
    }
}