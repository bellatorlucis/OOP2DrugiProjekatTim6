package com.oop2.tim6.NakitWebTim6.converter;

import com.oop2.tim6.NakitWebTim6.model.Ogla;
import com.oop2.tim6.NakitWebTim6.service.IOglasServiceTim6;
import com.oop2.tim6.NakitWebTim6.service.OglasServiceTim6;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class IdToOglaConverter implements Converter<Integer,Ogla> {

    private IOglasServiceTim6 oglasService;

    @Override
    public Ogla convert(Integer id) {
        return oglasService.getOglasWithId(id);
    }

    @Autowired
    public void setOglasService(IOglasServiceTim6 oglasService) {
        this.oglasService = oglasService;
    }
}
