package org.ismetg.service;

import org.ismetg.repository.ImageRepository;
import org.ismetg.utility.InputUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImageService {
    ImageRepository imageRepository;
    public ImageService()
    {
        this.imageRepository = new ImageRepository();
    }

    public List<String> fotografAl0YazanaKadar(){
        List<String> fotoList = new ArrayList<>();
        String fotograf;
        do
        {
            fotograf = InputUtil.getStringValue("Fotograf Giriniz. Sonlandırmak için 0 giriniz.");
            fotoList.add(fotograf);
        } while (!fotograf.equals("0"));
        fotoList.remove(fotoList.getLast());
        return fotoList;
    }

}
