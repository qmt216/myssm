package com.yoosal.zqmh;

import com.yoosal.zqmh.service.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by 秦明涛 on 2015/4/15.
 * Desc 全局访问service
 */
@Component
public class GlobalModule {
    public static IAdminService adminService;
    public static IUserService userService;
    public static ICommonService menuService;
    public static ISystemService systemService;
    public static IAdvService advService;
    public static IAdvPositionService advPositionService;
    public static IInformationService informationService;
    public static IInformationCateService informationCateService;
    public static IBrandService brandService;
    public static IDownloadService downloadService;
    public static IFriendService friendService;
    public static ILinkService linkService;
    public static IVideoService videoService;

    @Resource
    public void setUserService(IUserService userService) {
        GlobalModule.userService = userService;
    }

    @Resource
    public void setAdminService(IAdminService adminService) {
        GlobalModule.adminService = adminService;
    }

    @Resource
    public void setMenuService(ICommonService menuService) {
        GlobalModule.menuService = menuService;
    }

    @Resource
    public void setSystemService(ISystemService systemService) {
        GlobalModule.systemService = systemService;
    }

    @Resource
    public void setAdvService(IAdvService advService) {
        GlobalModule.advService = advService;
    }

    @Resource
    public void setAdvPositionService(IAdvPositionService advPositionService) {
        GlobalModule.advPositionService = advPositionService;
    }

    @Resource
    public void setInformationService(IInformationService informationService) {
        GlobalModule.informationService = informationService;
    }

    @Resource
    public void setInformationCateService(IInformationCateService informationCateService) {
        GlobalModule.informationCateService = informationCateService;
    }

    @Resource
    public void setBrandService(IBrandService brandService) {
        GlobalModule.brandService = brandService;
    }

    @Resource
    public void setDownloadService(IDownloadService downloadService) {
        GlobalModule.downloadService = downloadService;
    }

    @Resource
    public void setFriendService(IFriendService friendService) {
        GlobalModule.friendService = friendService;
    }

    @Resource
    public void setLinkService(ILinkService linkService) {
        GlobalModule.linkService = linkService;
    }

    @Resource
    public void setVideoService(IVideoService videoService) {
        GlobalModule.videoService = videoService;
    }
}
