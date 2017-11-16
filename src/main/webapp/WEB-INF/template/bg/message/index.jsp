<form id="pagerForm" action="${frontPath}/bg/product/index.html" method="post">
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
                            联系人：
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
                    <a class="add" href="/bg/product/add.html" target="navTab"><span>添加</span></a>
                </li>
                <li class="line">line</li>
                <li>
                    <a class="edit" href="/bg/product/edit.html?id={sid_Audit}" target="navTab"
                       warn="请选择一条数据"
                       id="edit"><span>修改</span></a>
                </li>
                <li class="line">line</li>
                <li>
                    <a class="delete" href="/bg/product/delete.html?id={sid_Audit}"
                       target="ajaxTodo" title="你确定要删除吗？" warn="请选择一条数据"><span>删除</span></a>
                </li>
            </ul>
        </div>


        <table class="list" width="100%" layoutH="90">
            <thead>
            <tr>
                <th width="15%" style="text-align: center">联系人</th>
                <th width="15%" style="text-align: center">联系电话</th>
                <th width="15%" style="text-align: center">邮 箱</th>
                <th width="40%" style="text-align: center">联系地址</th>
                <th width="15%" style="text-align: center">创建时间</th>
            </tr>
            </thead>

            <tbody>
            [#if list??]
                [#list list as bean]
                <tr target="sid_Audit" rel="${product.id}">
                    <td>
                    ${bean.linkMan}
                    </td>
                    <td>
                    ${bean.linkNum}
                    </td>
                    <td>
                    ${bean.email}
                    </td>
                    <td>
                        [#if StringUtils.length(bean.address)>200]
                        ${StringUtils.substring(bean.address,0,200)}..
                        [#else]
                        ${bean.address}
                        [/#if]
                    </td>
                    <td>
                    ${bean.createTime}
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
