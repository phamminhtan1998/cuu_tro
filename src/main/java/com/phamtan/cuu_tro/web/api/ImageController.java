package com.phamtan.cuu_tro.web.api;

import com.phamtan.cuu_tro.common.enumeration.ImageType;
import com.phamtan.cuu_tro.dao.entity.Image;
import com.phamtan.cuu_tro.servie.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/images")
public class ImageController {
    private final ImageService imageService;
    @GetMapping
    public List<Image> getAll(){
        return imageService.findAll();
    }
    @GetMapping("/{id}")
    public Image getAll(@PathVariable("id")String id){
        return imageService.getImage(id);
    }

    @PostMapping()
    public Image uploadImage(@RequestParam(value = "file") MultipartFile file
            , @RequestParam(value = "name") String  name
            , @RequestParam("imagePath") String  imagePath
            , @RequestParam(value = "description",required = false) String  description
            , @RequestParam("idParent") String  idParent
            , @RequestParam("type") ImageType type
            , @RequestParam(value = "specifyType",required = false) String  specifyType
        ){
        Image image = new Image();
        image.setName(name);
        image.setDescription(description);
        image.setImagePath(imagePath);
        image.setType(type);
        image.setSpecifyType(specifyType);
        image.setIdParent(idParent);
       return  imageService.createImage(image,file);
    }
    @PutMapping
    public Image udpate(@RequestBody Image image){
        return imageService.update(image);
    }
    @DeleteMapping
    public void deleteImage(@RequestBody Image image){
        imageService.delete(image);
    }
}
