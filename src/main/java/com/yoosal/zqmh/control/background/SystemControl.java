package com.yoosal.zqmh.control.background;

import com.alibaba.fastjson.JSONObject;
import com.yoosal.zqmh.GlobalModule;
import com.yoosal.zqmh.control.FileUtil;
import com.yoosal.zqmh.control.MessageUtil;
import com.yoosal.zqmh.pojo.Bak;
import com.yoosal.zqmh.pojo.SystemWithBLOBs;
import com.yoosal.zqmh.util.DateUtil;
import com.yoosal.zqmh.util.StringUtil;
import com.yoosal.zqmh.util.ZipCompressorByAnt;
import com.yoosal.zqmh.util.filefilter.CompressFileFilter;
import com.yoosal.zqmh.util.filefilter.SqlFileFilter;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * Created by ibm on 14-1-13.
 */
@Controller
@RequestMapping("/bg/system")
public class SystemControl {
    static final String navTabId = "System_index";

    @RequestMapping(value = "/index.html")
    @SuppressWarnings("unchecked")
    private String getSystem(ModelMap modelMap) {
        List<SystemWithBLOBs> list = GlobalModule.systemService.queryAll("id", 0, 1);
        if (list.size() > 0) {
            modelMap.put("bean", list.get(0));
        }
        return "/bg/system/index";
    }

    @RequestMapping(value = "/update.html")
    @ResponseBody
    @SuppressWarnings("unchecked")
    private JSONObject updateSystem(String id, String companyName, String companySummary, String linkMan, String linkNum, String qq, String email
            , String companyAddress, String introduce, String staticCode, @RequestParam MultipartFile logo, HttpServletRequest request) {
        SystemWithBLOBs bean = GlobalModule.systemService.selectByPrimaryKey(StringUtil.toInt(id));
        if (StringUtils.isBlank(id) || bean == null) {
            return MessageUtil.errorMessage("获取数据失败！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        bean.setCompanyName(companyName);
        bean.setCompanySummary(companySummary);
        bean.setLinkMan(linkMan);
        bean.setLinkNum(linkNum);
        bean.setQq(qq);
        bean.setEmail(email);
        bean.setCompanyAddress(companyAddress);
        bean.setIntroduce(introduce);
        bean.setStaticCode(staticCode);
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (logo != null && logo.getSize() > 0 && isMultipart) {
            String result = FileUtil.uploadFile(request, logo, navTabId);
            if (result.startsWith("/upload/")) {
                bean.setLogo(result);
            } else {
                return JSONObject.parseObject(result);
            }
        }

        GlobalModule.systemService.updateByPrimaryKeyWithBLOBs(bean);
        return MessageUtil.successMessage(MessageUtil.Message.editSuccess, navTabId, MessageUtil.CallBackType.refreshCurrent);
    }

    /**
     * 页脚管理
     */
    @RequestMapping(value = "/footer.html")
    @SuppressWarnings("unchecked")
    private String footer(ModelMap modelMap) {
        List<SystemWithBLOBs> list = GlobalModule.systemService.queryAll("id", 0, 1);
        if (list.size() > 0) {
            modelMap.put("bean", list.get(0));
        }
        return "/bg/system/footer";
    }

    /**
     * 页脚更新
     */
    @RequestMapping(value = "/updateFooter.html")
    @ResponseBody
    @SuppressWarnings("unchecked")
    private JSONObject updateFooter(String id, String footer) {
        SystemWithBLOBs bean = GlobalModule.systemService.selectByPrimaryKey(StringUtil.toInt(id));
        if (StringUtils.isBlank(id) || bean == null) {
            return MessageUtil.errorMessage("获取数据失败！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        bean.setFooter(footer);
        GlobalModule.systemService.updateByPrimaryKeyWithBLOBs(bean);
        return MessageUtil.successMessage(MessageUtil.Message.editSuccess, "System_footer", MessageUtil.CallBackType.refreshCurrent);
    }

    /**
     * 备份
     */
    @RequestMapping(value = "/bak.html")
    public String bak(HttpServletRequest request, ModelMap modelMap) {
        // 文件保存目录路径
        String bakPath = request.getSession().getServletContext().getRealPath("//") + "//bak//";
        // 创建文件夹
        File bakDirFile = new File(bakPath);
        if (bakDirFile.exists()) {
            //sql 文件备份
            File[] sqlFileArray = bakDirFile.listFiles(new SqlFileFilter());
            if (sqlFileArray != null && sqlFileArray.length > 0) {
                List<Bak> sqlFileList = new ArrayList<Bak>();
                for (File item : sqlFileArray) {
                    Bak bak = new Bak();
                    bak.setName(item.getName());
                    bak.setSize((item.length() / (1024)) + "KB");
                    bak.setCreateTime(DateUtil.formatTime(new Date(Long.parseLong(item.getName().replace(".sql", "")))));
                    sqlFileList.add(bak);
                }
                modelMap.put("sqlFileList", sqlFileList);
            }
            //图片文件备份
            File[] uploadFileArray = bakDirFile.listFiles(new CompressFileFilter());
            if (uploadFileArray != null && uploadFileArray.length > 0) {
                List<Bak> uploadFileList = new ArrayList<Bak>();
                for (File item : uploadFileArray) {
                    Bak bak = new Bak();
                    bak.setName(item.getName());
                    bak.setSize((item.length() / 1024) + "KB");
                    bak.setCreateTime(DateUtil.formatTime(new Date(Long.parseLong(item.getName().replace(".zip", "")))));
                    uploadFileList.add(bak);
                }
                modelMap.put("uploadFileList", uploadFileList);
            }
        }
        return "/bg/system/bak";
    }

    /**
     * 数据库备份
     */
    @RequestMapping(value = "/bakSql.html")
    @ResponseBody
    public JSONObject bakSql(HttpServletRequest request) {
        String newFileName = System.currentTimeMillis() + ".sql";
        String sqlPath = request.getSession().getServletContext().getRealPath("//") + "//bak//" + newFileName;
        try {
            Runtime rt = Runtime.getRuntime();
            // 调用 调用mysql的安装目录的命令
            Process child = rt.exec("C:\\Program Files\\MySQL\\MySQL Server 5.1\\bin\\mysqldump -h 127.0.0.1 -uroot -ph0h8u9q1b8  zqmh_lxx");
            // 设置导出编码为utf-8。这里必须是utf-8
            // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
            InputStream in = child.getInputStream();// 控制台的输出信息作为输入流
            InputStreamReader xx = new InputStreamReader(in, "utf-8");
            // 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码
            String inStr;
            StringBuilder sb = new StringBuilder("");
            String outStr;
            // 组合控制台输出信息字符串
            BufferedReader br = new BufferedReader(xx);
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr).append("\r\n");
            }
            outStr = sb.toString();
            // 要用来做导入用的sql目标文件：
            FileOutputStream fout = new FileOutputStream(sqlPath);
            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
            writer.write(outStr);
            writer.flush();
            in.close();
            xx.close();
            br.close();
            writer.close();
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return MessageUtil.errorMessage("抱歉，系统错误", "System_bak", MessageUtil.CallBackType.refreshCurrent);
        }
        return MessageUtil.successMessage("备份成功", "System_bak", MessageUtil.CallBackType.refreshCurrent);
    }

    /**
     * 图片备份
     */
    @RequestMapping(value = "/bakPic.html")
    @ResponseBody
    public JSONObject bakPic(HttpServletRequest request) {
        String newFileName = System.currentTimeMillis() + ".zip";
        String sqlPath = request.getSession().getServletContext().getRealPath("//") + "//bak//" + newFileName;
        try {
            ZipCompressorByAnt compressorByAnt = new ZipCompressorByAnt(sqlPath);
            compressorByAnt.compressExe(request.getSession().getServletContext().getRealPath("//") + "//upload//");
        } catch (Exception e) {
            e.printStackTrace();
            return MessageUtil.errorMessage("抱歉，系统错误", "System_bak", MessageUtil.CallBackType.refreshCurrent);
        }
        return MessageUtil.successMessage("备份任务已执行,请稍等几分钟后查看", "System_bak", MessageUtil.CallBackType.refreshCurrent);
    }
}
