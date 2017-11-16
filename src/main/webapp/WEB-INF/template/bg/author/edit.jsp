<div class="page">
    <div class="pageContent">

        <form method="post" action="/bg/author/update.html" class="pageForm required-validate"
              onsubmit="return validateCallback(this, navTabAjaxDone);">
            <input type="hidden" name="id" value="${bean.id}"/>
            <div class="pageFormContent" layoutH="53">
                <div class="unit">
                    <label>标题：</label>
                    <input type="text" class="required" name="title" value="${bean.title}"/>
                </div>

                <div class="unit">
                    <label>域名：</label>
                    <input type="text" name="domain" class="required" value="${bean.domain}"/>
                </div>

                <div class="unit">
                    <label>授权结束时间：</label>
                    <input type="text" name="endTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" yearstart="-5" yearend="5" value="${bean.endTime}"/>
                    <a class="inputDateButton" href="javascript:;">选择</a>
                </div>

                <div class="unit">
                    <label>概述：</label>
                    <textarea name="remark" rows="5" cols="100">${bean.remark}</textarea>
                </div>
            </div>
            <div class="formBar">
                <ul>
                    <li>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button type="submit">保存</button>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="button">
                            <div class="buttonContent">
                                <button type="button" class="close">取消</button>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </form>
    </div>
</div>