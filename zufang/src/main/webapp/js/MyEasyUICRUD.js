var url;

function SaveDialog(tableid,dialogid){
	//表单异步提交添加
	$("#ModiyDialogForm").form('submit',{
		url:url,
		onSubmit:function(){
			return $(this).form('validate');
		},
		success:function(data){
            var data = eval('(' + data + ')');
			if( data.success ){
                $.messager.alert("系统提示",data.message);
                $("#ModiyDialogForm").form('clear');
                $(tableid).datagrid('reload'); //刷新
                $(dialogid).dialog('close'); //关闭
			}
		}
	});

}
 
function CloseDialog(dialogid){
    $("#ModiyDialogForm").form('clear');
	$(dialogid).dialog('close');
}
 

//设置添加url
function Add(paramurl,dialogid){
	$(dialogid).dialog('open').dialog('setTitle',"添加数据");
	url = paramurl;
}
//删除的代码
function DeleteByFruitName(url,tableid){
    //获取选择行
    var SelectRows = $(tableid).datagrid('getSelections');
    if( 0 == SelectRows.length ){
        $.messager.alert("系统提示", "请选择要删除的数据");
        return;
    }
    var SelectIndexArr = [];
    for( var i = 0 ; i < SelectRows.length; i++ ){
        SelectIndexArr.push(SelectRows[i].id);
    }
    var jsonSTR = JSON.stringify(SelectIndexArr);

    $.messager.confirm("系统提示", "你确定要删除<font color=red> " + SelectRows.length + " </font>条数据吗?", function(xo){
        if( xo ){
            $.ajax(
                {
                    url:url,
                    type:"post",
                    data:jsonSTR,
                    dataType:'json',
                    contentType:"application/json",
                    success:function (data) {
                        if( data.success ){
                            $.messager.alert("系统提示",data.message);
                            $(tableid).datagrid('reload'); //刷新
                        }
                    }
                }
            );
        }
    });
}
//修改的url
function ModifyBySelect(paramurl,tableid,dialogid){
	//获取选中的行
	var SelectRows = $(tableid).datagrid('getSelections');
	if( 1 != SelectRows.length ){
		$.messager.alert("系统提示", "请选择一行要编辑的数据");
		return;
	}
	var SelectRow = SelectRows[0];
	//打开编辑对话框
	$(dialogid).dialog('open').dialog('setTitle',"编辑数据");
	//获得行对象的数据加载到表单中显示
	$("#ModiyDialogForm").form('load',SelectRow);
	url = paramurl;  //设置修改的地址
}
 

 
//打开菜单
function OpenTab(Text,URL){
	if( $("#MenusTabs").tabs('exists', Text)){
		$("#MenusTabs").tabs('select', Text);
	}
	else{
		/*var ts = new Date().getTime();
		var url1 = URL+"?_="+ts;*/
		var Content = "<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src=" + URL + "></iframe>";
		$("#MenusTabs").tabs('add',{
			title:Text,
			closable:true,
			content:Content
		});	
	}
}
 

//制作树型导航菜单
$(function(){
	 //定义树型菜单内容
	var TreeMenusDatas=[{
		text:"图书馆图书系统",
		children:[{
			text:"小说图书信息",
			attributes:{
				url:"data.jsp"
			}
		}]
	}];
	
	//加载树型菜单内容
	$("#TreeMenus").tree({
		data:TreeMenusDatas,
		lines:true,
		onClick:function(node){
			if(node.attributes ){
				OpenTab( node.text, node.attributes.url);
			}
		}
	});
});