<form id="pagerForm" action="${frontPath}/bg/author/index.html" method="post">
    <input type="hidden" name="pageNum" value="1"/>
</form>
<div class="page">
    <div class="pageHeader">
        <form onsubmit="return navTabSearch(this);" action="${frontPath}/bg/author/index.html" method="post">
            <input type="hidden" name="pageNum" value="1"/>

            <div class="searchBar">
                <table class="searchContent">
                    <tr>
                        <td>
                            标题：
                            <input type="text" name="keyword" value=""/>
                        </td>
                        <td>
                            <div class="buttonActive">
                                <div class="buttonContent">
                                    <button type="submit">查询</button>
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </form>
    </div>

    <div class="pageContent">
        <div class="panelBar">
            <ul class="toolBar">
                <li>
                    <a class="add" href="/bg/author/add.html" target="navTab"><span>添加</span></a>
                </li>
                <li class="line">line</li>
                <li>
                    <a class="edit" href="/bg/author/edit.html?id={sid_Audit}" target="navTab"
                       warn="请选择一条数据" id="edit"><span>修改</span></a>
                </li>
            [#--<li class="line">line</li>
            <li>
                <a class="delete" href="/bg/author/delete.html?id={sid_Audit}"
                   target="ajaxTodo" title="你确定要删除吗？" warn="请选择一条数据"><span>删除</span></a>
            </li>--]
            </ul>
        </div>

        <table class="list" width="100%" layoutH="90">
            <thead>
            <tr>
                <th width="15%" style="text-align: center">标题</th>
                <th width="20%" style="text-align: center">域名</th>
                <th width="15%" style="text-align: center">授权结束时间</th>
                <th width="10%" style="text-align: center">更新时间</th>
                <th width="40%" style="text-align: center">概述</th>
            </tr>
            </thead>

            <tbody>
            [#if list??]
                [#list list as bean]
                <tr target="sid_Audit" rel="${bean.id}">

                    <td>
                    ${bean.title}
                    </td>
                    <td>
                    ${bean.domain}
                    </td>
                    <td style="margin: 0 auto; vertical-align: middle;text-align: center;">
                    ${bean.endTime}
                    </td>
                    <td>
                    ${bean.updateTime}
                    </td>
                    <td>
                        [#if StringUtils.length(bean.remark)>90]
                        ${StringUtils.substring(bean.remark,0,90)}..
                        [#else]
                        ${bean.remark}
                        [/#if]
                    </td>
                </tr>
                [/#list]
            [/#if]
            </tbody>
        </table>

        <div class="panelBar">
            <div class="pages">
                <span>共${totalCount}条</span>
            </div>
            <div class="pagination" targetType="navTab" totalCount="${totalCount}" numPerPage="${numPerPage}"
                 pageNumShown="10" currentPage="${currentPage}"></div>
        </div>

    </div>

</div>
