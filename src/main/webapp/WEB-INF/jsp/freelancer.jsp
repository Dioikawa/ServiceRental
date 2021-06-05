<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>账单列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/css/layui.css">
    <style>
        .layui-table-cell .layui-form-checkbox[lay-skin="primary"]{
            top: 50%;
            transform: translateY(-50%);
        }
    </style>
</head>
<body>

<!-- 标题 -->
<h1 align="center">帐单列表</h1>

<!-- 查询条件开始 -->
<div style="width: 80%;margin-left: 100px;text-align: center">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
        <legend>搜索条件</legend>
    </fieldset>
    <form class="layui-form layui-form-pane" method="post">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">账单类型</label>
                <div class="layui-input-inline">
                    <select name="typeId" class="layui-input">
                        <option value="">请选择账单类型</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">开始日期</label>
                <div class="layui-input-block">
                    <input type="text" name="startDate" id="startDate" readonly autocomplete="off" class="layui-input" placeholder="请选择开始时间">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">结束日期</label>
                <div class="layui-input-block">
                    <input type="text" name="endDate" id="endDate" readonly autocomplete="off" class="layui-input" placeholder="请选择结束时间">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="doSearch">搜索</button>
                <button type="reset" class="layui-btn layui-btn-warm">重置</button>
            </div>
        </div>
    </form>
</div>
<!-- 查询条件结束 -->

<!-- 数据表格开始 -->
<div style="width: 80%;margin-left: 100px">
    <!-- 表格 -->
    <table class="layui-hide" id="billTable" lay-filter="billTable"></table>

    <!-- 头部工具栏 -->
    <script type="text/html" id="billToolbar">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="add"><i class="layui-icon layui-icon-add-circle"></i>添加账单</button>
            <button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="batchDelete"><i class="layui-icon layui-icon-delete"></i>批量删除</button>
        </div>
    </script>
    <!-- 行工具栏 -->
    <script type="text/html" id="billRowBar">
        <button class="layui-btn layui-btn-xs" lay-event="edit">编辑</button>
        <button class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</button>
    </script>

</div>
<!-- 数据表格结束 -->

<!-- 添加和修改账单的弹出层开始 -->
<div id="addOrUpdateBillDiv" style="display: none;margin: 10px">
    <form id="dataFrm" method="post" class="layui-form  layui-form-pane" lay-filter="dataFrm">
        <!-- 隐藏域，保存当前账单的ID -->
        <input type="hidden" name="id">
        <div class="layui-form-item">
            <label class="layui-form-label">帐单类型</label>
            <div class="layui-input-block" id="billTypeDiv">
                <select name="typeId" class="layui-input" lay-verify="required" lay-reqText="请选择账单类型">
                    <option value="">请选择账单类型</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">帐单标题</label>
            <div class="layui-input-block">
                <input type="text" name="title" placeholder="请输入账单标题" lay-verify="required" lay-reqText="请输入账单标题"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">账单时间</label>
            <div class="layui-input-block">
                <input type="text" name="billTime" id="billtime" readonly="readonly" lay-reqText="请输入选择账单时间"   placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">帐单金额</label>
            <div class="layui-input-inline">
                <input type="text" name="price"   lay-verify="number" placeholder="请输入账单金额"  lay-reqText="请输入账单金额"   autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">帐单备注</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入帐单备注" name="remark" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item" style="text-align: center;">
            <button type="button" class="layui-btn" lay-submit lay-filter="doSubmit">提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </form>
</div>
<!-- 添加和修改账单的弹出层结束-->



<script src="${pageContext.request.contextPath}/statics/layui/layui.js"></script>
<script>
    layui.use(['form','jquery','table','laydate','layer'],function () {
        var form = layui.form;
        var $ = layui.jquery;
        var table = layui.table;
        var laydate = layui.laydate;
        var layer = layui.layer;

        //渲染日期组件
        laydate.render({
            elem:"#startDate",
        });
        laydate.render({
            elem:"#endDate"
        });
        laydate.render({
            elem:"#billtime",
            type:"datetime"
        });

        //发送Ajax请求查询账单类型
        $.get("/billType/list",function(result){
            var html = "";
            //循环遍历集合
            for (let i = 0; i < result.length; i++) {
                html +="<option value='"+result[i].id+"'>"+result[i].name+"</option>"
            }
            //将网页代码追加到下拉列表中
            $("[name='typeId']").append(html);
            //更新渲染select下拉框
            form.render("select");
        },"json");

        //渲染表格组件
        var tableIns = table.render({
            elem:"#billTable",//绑定表格元素，推荐使用ID选择器
            url:"/bill/list",//异步请求地址,加入分页后，默认使用page(当前页码)和limit(每页显示数量)作为参数名称
            page:true,//开启分页
            toolbar:"#billToolbar",
            cols: [[ //表头
                //field属性：字段属性，该属性与实体类的属性名一致
                //title属性：表头文本
                {type:"checkbox",fixed:"left",width:80,align:"center"},
                {field: 'id', title: 'ID',align:"center"}
                ,{field: 'title', title: '账单标题',align:"center"}
                ,{field: 'typeName', title: '账单类型',align:"center"}
                ,{field: 'price', title: '账单金额',align:"center"}
                ,{field: 'billTime', title: '创建时间',align:"center"}
                ,{field: 'remark', title: '备注',align:"center"}
                ,{title:"操作",toolbar: "#billRowBar",align:"center"}
            ]]
        });

        //监听搜索按钮提交事件
        form.on("submit(doSearch)",function (data) {
            tableIns.reload({
                where:data.field,//查询条件
                page:{
                    curr:1
                }
            });
            //禁止页面刷新
            return false;
        });


        //监听表格头部工具栏事件
        table.on("toolbar(billTable)",function (obj) {
            switch (obj.event) {
                //添加
                case 'add':
                    openAddWindow();
                    break;
                //批量删除
                case 'batchDelete':
                    batchDelete();
                    break;
            }
        });

        //监听表格行工具栏事件
        table.on("tool(billTable)",function (obj) {
            switch (obj.event) {
                //编辑
                case 'edit':
                    openUpdateWindow(obj.data);
                    break;
                //删除
                case 'delete':
                    deleteById(obj.data);
                    break;
            }
        });


        var url;//提交地址
        var mainIndex;//窗口索引

        /**
         * 打开添加窗口
         */
        function openAddWindow() {
            mainIndex =layer.open({
                type:1,//弹出层类型
                title:"添加账单",
                area: ['800px', '600px'],
                content:$("#addOrUpdateBillDiv"),//引用的窗口代码
                success:function () {
                    //清空表单数据
                    $("#dataFrm")[0].reset();//JavaScript中的方法
                    url = "/bill/addBill";
                }
            });
        }

        /**
         * 打开编辑窗口
         */
        function openUpdateWindow(data) {
            mainIndex =layer.open({
                type:1,//弹出层类型
                title:"修改账单",
                area: ['800px', '600px'],
                content:$("#addOrUpdateBillDiv"),//引用的窗口代码
                success:function () {
                    //表单数据回显
                    form.val("dataFrm",data);
                    //修改请求
                    url = "/bill/updateBill";
                }
            });
        }




        //监听表单提交事件
        form.on("submit(doSubmit)",function (data) {
            $.post(url,data.field,function(result){
                if(result.success){
                    layer.alert(result.message,{icon:1});
                    //关闭窗口
                    layer.close(mainIndex);
                    //刷新数据表格
                    tableIns.reload();
                }else{
                    layer.alert(result.message,{icon:2});
                }
            },"json");
            return false;
        });

        /**
         * 删除账单
         * @param data  当前行数据
         */
        function deleteById(data) {
            //提示用户确认是否删除
            layer.confirm("确定要删除[<font color='red'>"+data.title+"</font>]账单吗？",{icon:3,title:"提示"},function (index) {
                //发送ajax请求
                $.post("/bill/deleteById",{"id":data.id},function(result){
                    if(result.success){
                        layer.alert(result.message,{icon:1});
                        //刷新数据表格
                        tableIns.reload();
                    }else{
                        layer.alert(result.message,{icon:2});
                    }
                },"json");

                //关闭提示框
                layer.close(index);
            });
        }

        /**
         * 批量删除
         */
        function batchDelete() {
            //获取表格对象
            var checkStatus = table.checkStatus('billTable'); //billTable 即为基础参数 id 对应的值
            //判断是否有选中行
            if(checkStatus.data.length>0){
                //定义数组，保存选中行的ID
                var idArr = [];
                //循环遍历获取选中行(目的是获取选中的每一行的ID值)
                for (let i = 0; i < checkStatus.data.length; i++) {
                    //将选中的ID值添加到数组的末尾
                    idArr.push(checkStatus.data[i].id);
                }
                //将数组转成字符串
                var ids = idArr.join(",");
                //提示用户是否删除
                layer.confirm("确定要删除这<font color='red'>"+checkStatus.data.length+"</font>条数据吗？",{icon:3,title:"提示"},function (index) {
                    //发送ajax请求
                    $.post("/bill/batchDelete",{"ids":ids},function(result){
                        if(result.success){
                            layer.alert(result.message,{icon:1});
                            //刷新数据表格
                            tableIns.reload();
                        }else{
                            layer.alert(result.message,{icon:2});
                        }
                    },"json");

                    //关闭提示框
                    layer.close(index);
                });


            }else{
                layer.msg("请选择要删除的数据");
            }

        }



    });




</script>


</body>
</html>
