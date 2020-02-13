/**
 * 打开一个iframe
 * 
 * @param url
 * @param title
 * @param width
 * @param height
 * @param type
 * @returns
 */
function layerOpen(url, title, width, height, type) {
	layer.open({
		type : type || 2,
		// 宽和高
		// area : [window.innerWidth-300+'px', window.innerHeight-100+'px' ],
		area : [ width || window.innerWidth - 300 + 'px',
				height || window.innerHeight - 100 + 'px' ],
		fixed : false, // 不固定
		maxmin : true,
		title : title || "窗口",
		content : url
	});
}

function del(url, id) {
	layer.confirm('是否要删除', {
		icon : 5,
		title : '提示'
	}, function(index) {
		$.ajax({
			url : url,
			data : {
				"id" : id
			},
			type : 'POST',
			async : true,
			success : function(status) {
				if (status.code == 200) {
					layer.msg('删除成功', {
						icon : 1
					});
					window.setTimeout(function(){
						window.location.reload();
					},500);
					
					
				} else {
					layer.msg('删除失败');
				}
			}
		});
	});
}

function closeFrame() {
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
	window.parent.location.reload();
}
