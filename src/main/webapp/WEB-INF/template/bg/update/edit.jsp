<div class="page">
    <div class="pageContent">

        <form method="post" action="/bg/update/update.html" class="pageForm required-validate"
              onsubmit="return validateCallback(this, navTabAjaxDone);">
            <input type="hidden" name="id" value="${bean.id}"/>

            <div class="pageFormContent" layoutH="53">
                <div class="unit">
                    <label>产品名称：</label>
                    <input type="text" class="required" name="name" value="${bean.name}"/>
                </div>

                <div class="unit">
                    <label>排序：</label>
                    <input type="text" name="sort" class="required digits" size="10" value="${bean.sort}"/>
                </div>

                <div class="unit">
                    <label>更新日期：</label>
                    <input type="text" name="updateTime" class="date" dateFmt="yyyy年MM月dd日" yearstart="-5" yearend="5" value="${bean.updateTime}"/>
                    <a class="inputDateButton" href="javascript:;">选择</a>
                </div>

                <div class="unit">
                    <label>概述：</label>
                    <textarea name="summary" rows="5" cols="100">${bean.summary}</textarea>
                </div>
                <div class="unit">
                    <label>产品介绍：</label>
                    <textarea class="editor" name="introduce" rows="10" cols="100" class="required">
                    ${bean.introduce}
                    </textarea>
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