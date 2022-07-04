$(function () {
    $("#jqGrid").jqGrid({
        url: '/admin/link/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'linkId', width: 50, key: true, hidden: true},
            {label: '网站名称', name: 'name', index: 'linkName', width: 100},
            {label: '网站链接', name: 'url', index: 'linkUrl', width: 120},
            {label: '网站描述', name: 'description', index: 'linkDescription', width: 120},
            {label: '排序值', name: 'rank', index: 'linkRank', width: 30},
            {label: '添加时间', name: 'createDate', index: 'createTime', width: 100}
        ],
        height: 560,
        rowNum: 10,
        rowList: [10, 20, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.records",
            page: "data.current",
            total: "data.pages",
            records: "data.total"
        },
        prmNames: {
            page: "page",
            rows: "rows"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });
});

/**
 * jqGrid重新加载
 */
function reload() {
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}

function linkAdd() {
    reset();
    $('.modal-title').html('友链添加');
    $('#linkModal').modal('show');
}

//绑定modal上的保存按钮
$('#saveButton').click(function () {
    var linkId = $("#linkId").val();
    var linkName = $("#linkName").val();
    var linkUrl = $("#linkUrl").val();
    var linkDescription = $("#linkDescription").val();
    var linkRank = $("#linkRank").val();
    if (!validCN_ENString2_18(linkName)) {
        $('#edit-error-msg').css("display", "block");
        $('#edit-error-msg').html("请输入符合规范的名称！");
        return;
    }
    if (!isURL(linkUrl)) {
        $('#edit-error-msg').css("display", "block");
        $('#edit-error-msg').html("请输入符合规范的网址！");
        return;
    }
    if (!validCN_ENString2_100(linkDescription)) {
        $('#edit-error-msg').css("display", "block");
        $('#edit-error-msg').html("请输入符合规范的描述！");
        return;
    }
    if (isNull(linkRank) || linkRank < 0) {
        $('#edit-error-msg').css("display", "block");
        $('#edit-error-msg').html("请输入符合规范的排序值！");
        return;
    }
    var url = '/admin/link';
    // 封装对象
    params = {
        "id": linkId,
        "name": linkName,
        "url": linkUrl,
        "description": linkDescription,
        "rank": parseInt(linkRank),
        "type": parseInt($("#linkType").val())
    }
    console.log(params)
    $.ajax({
        type: 'POST',//方法类型
        url: url,
        contentType: 'application/json',
        data: JSON.stringify(params),
        success: function (result) {
            if (result.code == 200) {
                $('#linkModal').modal('hide');
                swal("保存成功", {
                    icon: "success",
                });
                reload();
            }
            else {
                $('#linkModal').modal('hide');
                swal("保存失败", {
                    icon: "error",
                });
            }
            ;
        },
        error: function () {
            swal("操作失败", {
                icon: "error",
            });
        }
    });

});

function linkEdit() {
    var id = getSelectedRow();
    if (id == null) {
        return;
    }
    reset();
    //请求数据
    $.get("/admin/link/" + id, function (r) {
        if (r.code == 200 && r.data != null) {
            //填充数据至modal
            $("#linkName").val(r.data.name);
            $("#linkUrl").val(r.data.url);
            $("#linkDescription").val(r.data.description);
            $("#linkRank").val(r.data.rank);
            //根据原linkType值设置select选择器为选中状态
            if (r.data.type == 1) {
                $("#linkType option:eq(1)").prop("selected", 'selected');
            }
            if (r.data.linkType == 2) {
                $("#linkType option:eq(2)").prop("selected", 'selected');
            }
        }
    });
    $('.modal-title').html('友链修改');
    $('#linkModal').modal('show');
    $("#linkId").val(id);
}

function deleteLink() {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    swal({
        title: "确认弹框",
        text: "确认要删除数据吗?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((flag) => {
            if (flag) {
                $.ajax({
                    type: "DELETE",
                    url: "/admin/link",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.code == 200) {
                            swal("删除成功", {
                                icon: "success",
                            });
                            $("#jqGrid").trigger("reloadGrid");
                        } else {
                            swal(r.msg, {
                                icon: "error",
                            });
                        }
                    }
                });
            }
        }
    );
}

function reset() {
    $("#linkName").val('');
    $("#linkUrl").val('');
    $("#linkDescription").val('');
    $("#linkRank").val(0);
    $("#linkId").val(null);
    $('#edit-error-msg').css("display", "none");
    $("#linkType option:first").prop("selected", 'selected');
}