<div class="page">
    <div class="pageContent">

        <form method="post" action="/bg/user/insert.html" class="pageForm required-validate"
              enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone);">
            <div class="pageFormContent" layoutH="53">
                <div class="unit">
                    <label>用户名：</label>
                    <input type="text" class="required" name="name"/>
                </div>

                <div class="unit">
                    <label>密码：</label>
                    <input type="text" name="password" class="required" value=""/>
                </div>
                <div class="unit">
                    <label>重复密码：</label>
                    <input type="text" name="rePassword" class="required" value=""/>
                </div>
                <div class="unit">
                    <label>资讯条数：</label>
                    <input type="text" name="adNum" class="required digits" size="10" value=""/>
                </div>

                <div class="unit">
                    <label>冻结状态：</label>
                    <select name="freeze">
                        <option value="0">正常</option>
                        <option value="1">冻结</option>
                    </select>
                </div>

                <div class="unit">
                    <label>备注：</label>
                    <textarea name="remark" rows="5" cols="100"></textarea>
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