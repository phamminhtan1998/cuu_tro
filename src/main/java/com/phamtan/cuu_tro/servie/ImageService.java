package com.phamtan.cuu_tro.servie;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import com.phamtan.cuu_tro.common.enumeration.DropBoxCommon;
import com.phamtan.cuu_tro.dao.entity.Image;
import com.phamtan.cuu_tro.dao.repo.ImageRepo;
import com.phamtan.cuu_tro.web.dto.request.ImageDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@AllArgsConstructor
public class ImageService {
    private final ImageRepo imageRepo;
    private final DropBoxService dropBoxService;
    public List<Image> findAll(){
       return  imageRepo.findAll();
    }
    public Image getImage(String id){
        return imageRepo.findById(id).get();
    }
    public Image createImage(Image image , MultipartFile file)  {
        if(image.getImagePath()==null){
            image.setImagePath("/"+file.getOriginalFilename());
        }
        else if(!image.getImagePath().contains("/")){
            image.setImagePath("/"+image.getImagePath());
        }
        try {
            SharedLinkMetadata response = dropBoxService.uploadFile(file, image.getImagePath());
            if(response.getUrl()!=null){
                image.setUrl(convertUrl(response.getUrl()));
                return imageRepo.save(image);
            }

        } catch (DbxException e) {
            e.printStackTrace();
        }
       return null;
    }
    public Image update(Image image){
        return imageRepo.save(image);
    }
    public void  delete(Image image){
         imageRepo.delete(image);
    }
    public String convertUrl(String sourceUrl){
        sourceUrl = sourceUrl.substring(0,sourceUrl.length()-5);
        sourceUrl = sourceUrl .concat(DropBoxCommon.SUFFIX_IMAGE);
        return sourceUrl;
    }

}
