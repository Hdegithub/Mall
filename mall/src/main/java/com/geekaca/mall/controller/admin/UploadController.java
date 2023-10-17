package com.geekaca.mall.controller.admin;

import com.geekaca.mall.common.Constants;
import com.geekaca.mall.utils.Result;
import com.geekaca.mall.utils.ResultGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@RestController
@RequestMapping("/manage-api/v1")
public class UploadController {

    /**
     * 图片上传
     */
    @RequestMapping(value = "/upload/file", method = RequestMethod.POST)
    public Result upload(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file){
        //目标：接收用户上传的图片，改名，而且不能冲突
        String fileName = file.getOriginalFilename();
        /**
         * 取文件的扩展名
         * avatar.png   jpg
         */
        //首先找到.在文件名字中最后一次出现的索引
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String curDtime = localDateTime.format(dft);
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        //为了生成随机的文件名字 + 扩展名
        tempName.append(curDtime).append(r.nextInt(100)).append(suffixName);
        String newFileName = tempName.toString();
        File fileDirectory = new File(Constants.FILE_UPLOAD_DIC);
        //创建文件
        File destFile = new File(Constants.FILE_UPLOAD_DIC + newFileName);
        try {
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                }
            }
            //把文件保存到指定位置
            file.transferTo(destFile);
            Result resultSuccess = ResultGenerator.genSuccessResult();
            //给前端返回  图片的访问路径，前端会拿着这个路径url ，执行新增商品
            resultSuccess.setData(getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/upload/" + newFileName);
            return resultSuccess;
        } catch (IOException e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("文件上传失败");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("文件上传失败");

        }


    }

    public static URI getHost(URI uri) {
        URI effectiveURI = null;
        try {
            effectiveURI = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), null, null, null);
        } catch (Throwable var4) {
            effectiveURI = null;
        }
        return effectiveURI;
    }
}
