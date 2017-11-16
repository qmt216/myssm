<form id="pagerForm" action="${frontPath}/bg/resolve/index.html" method="post">
    <input type="hidden" name="pageNum" value="1"/>
</form>
<div class="page">
    <div class="pageHeader">
        <form onsubmit="return navTabSearch(this);" action="${frontPath}/bg/product/index.html" method="post">
            <input type="hidden" name="pageNum" value="1"/>

            <div class="searchBar">
                <table class="searchContent">
                    <tr>
                        <td>
                            产品名称：
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
                    <a class="add" href="/bg/resolve/add.html" target="navTab"><span>添加</span></a>
                </li>
                <li class="line">line</li>
                <li>
                    <a class="edit" href="/bg/resolve/edit.html?id={sid_Audit}" target="navTab"
                       warn="请选择一条数据"
                       id="edit"><span>修改</span></a>
                </li>
                <li class="line">line</li>
                <li>
                    <a class="delete" href="/bg/resolve/delete.html?id={sid_Audit}"
                       target="ajaxTodo" title="你确定要删除吗？" warn="请选择一条数据"><span>删除</span></a>
                </li>
            </ul>
        </div>


        <table class="list" width="100%" layoutH="90">
            <thead>
            <tr>
                <th width="5%" style="text-align: center">排序</th>
                <th width="30%" style="text-align: center">标题</th>
                <th width="10%" style="text-align: center">图片</th>
                <th width="45%" style="text-align: center">概述</th>
                <th width="10%" style="text-align: center">创建时间</th>
            </tr>
            </thead>

            <tbody>
            [#if list??]
                [#list list as product]
                <tr target="sid_Audit" rel="${product.id}">

                    <td>
                        [#if product.sort??]
                        ${product.sort}
                        [#else]
                            0
                        [/#if]
                    </td>
                    <td>
                    ${product.name}
                    </td>
                    <td style="height: 60px;margin: 0 auto; vertical-align: middle;text-align: center;">
                        [#if product.img??]
                            <img src="${product.img}" style="max-height: 60px;max-width: 60px;">
                        [#else]
                            <img src="/upload/nopic_60.gif" style="max-height: 60px;max-width: 60px;">
                        [/#if]
                    </td>
                    <td>
                        [#if StringUtils.length(product.summary)>90]
                        ${StringUtils.substring(product.summary,0,90)}..
                        [#else]
                        ${product.summary}
                        [/#if]
                    </td>
                    <td>
                    ${product.createTime}
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
