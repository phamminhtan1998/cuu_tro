package com.phamtan.cuu_tro.web.api;


import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import com.dropbox.core.v2.users.FullAccount;
import com.phamtan.cuu_tro.common.enumeration.DropBoxCommon;
import com.phamtan.cuu_tro.common.enumeration.ImageType;
import com.phamtan.cuu_tro.dao.entity.Image;
import com.phamtan.cuu_tro.servie.DropBoxService;
import com.phamtan.cuu_tro.util.mail.GmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;


@RestController
@RequestMapping("test")
@RequiredArgsConstructor

public class TestController {
    private final GmailService gmailService;
    private final DropBoxService dropBoxService;

    @GetMapping("/{name}")
    public String test(@PathVariable("name")String name){
//        gmailService.sendEmail();
        return name;
    }
//    @GetMapping("/dropbox")
//    public String dropBox() throws DbxException, FileNotFoundException {
//        File file = new File("/home/allinone/Pictures/pexels-lisa-1083822.jpg");
//        Image image = new Image();
//        image.setName("testImage");
//        image.setType(ImageType.AVATAR);
//        image.setIdParent("id cua ong so huu");
//        image.setDescription("image cho test");
//        FileMetadata test = dropBoxService.uploadFile(file,image);
//        return "dropbox";
//    }


}
