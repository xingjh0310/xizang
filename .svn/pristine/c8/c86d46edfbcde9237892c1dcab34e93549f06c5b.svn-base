var opt={};
var sysDept=new $.System_SysDept();
$(function(){
	//检查session
	comm_checksession();
	
	//初始化BootStrapTreeList的数据
	sysDept.InitData(opt);
	
	sysDept.initDeptType();
	
	//初始化新增、编辑和删除
	sysDept.InitAddEditDel();
	// 窗体变化时，调整组件的大小
	$(window).resize(function(){
		_AutoSize();
	});
	_AutoSize();
	
})
// 调整界面布局大小
function _AutoSize(){
	// 设置组件的高度
} 
function _reset(){
	sysDept.reset();
}
function _add(id){
	sysDept.add(id);
}
function _edit(id,onlyread){
	sysDept.edit(id,onlyread);
}
function _removeids(id){
	sysDept.removeids(id);
}
