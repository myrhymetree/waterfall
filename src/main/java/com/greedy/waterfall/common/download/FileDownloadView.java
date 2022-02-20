package com.greedy.waterfall.common.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class FileDownloadView extends AbstractView{

	public FileDownloadView() {
        setContentType("application/download; charset=utf-8");
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map<String, Object> fileInfo = (Map<String, Object>) model.get("downloadFile");
		
		String filePath = (String) fileInfo.get("filePath");
		String fileOriginName = (String) fileInfo.get("fileOriginName");
		String fileRandomName = (String) fileInfo.get("fileRandomName");
		
		File file = new File(filePath, fileRandomName);
		
		response.setContentType(getContentType());
		response.setContentLength((int) file.length());
		String userAgent = request.getHeader("User-Agent");
		String fileName = null;
		
		if(userAgent.indexOf("MSIE") > -1) {
            fileName = URLEncoder.encode(fileOriginName, "utf-8");
		} else {
			fileName = new String(fileOriginName.getBytes("UTF-8"), "iso-8859-1");
		}
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        OutputStream out = response.getOutputStream();
 
        FileInputStream fis = null;
        
        try {
            fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, out);
        } finally {
            if(fis != null)
                try {
                    fis.close();
                } catch(IOException ex) {
                	
                }
        }
        out.flush();
		
	}

}
