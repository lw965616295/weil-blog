$(function () {
    //修改站点信息
    $('#updateWebsiteButton').click(function () {
        $("#updateWebsiteButton").attr("disabled", true);
        //ajax提交数据
        var params = [
            {"name": "websiteName", "value": $('#websiteName').val()},
            {"name": "websiteDescription", "value": $('#websiteDescription').val()},
            {"name": "websiteLogo", "value": $('#websiteLogo').val()},
            {"name": "websiteIcon", "value": $('#websiteIcon').val()}
        ];
        $.ajax({
            type: "PUT",
            url: "/admin/config",
            contentType: 'application/json',
            data: JSON.stringify(params),
            success: function (result) {
                if (result.code == 200) {
                    swal("保存成功", {
                        icon: "success",
                    });
                }
                else {
                    swal(result.msg, {
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
    //个人信息
    $('#updateUserInfoButton').click(function () {
        $("#updateUserInfoButton").attr("disabled", true);
        var params = [
            {"name": "yourAvatar", "value": $('#yourAvatar').val()},
            {"name": "yourName", "value": $('#yourName').val()},
            {"name": "yourEmail", "value": $('#yourEmail').val()}
        ];
        $.ajax({
            type: "PUT",
            url: "/admin/config",
            contentType: 'application/json',
            data: JSON.stringify(params),
            success: function (result) {
                if (result.code == 200) {
                    swal("保存成功", {
                        icon: "success",
                    });
                }
                else {
                    swal(result.msg, {
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
    //修改底部设置
    $('#updateFooterButton').click(function () {
        $("#updateFooterButton").attr("disabled", true);
        var params = [
            {"name": "footerAbout", "value": $('#footerAbout').val()},
            {"name": "footerICP", "value": $('#footerICP').val()},
            {"name": "footerCopyRight", "value": $('#footerCopyRight').val()},
            {"name": "footerPoweredBy", "value": $('#footerPoweredBy').val()},
            {"name": "footerPoweredByURL", "value": $('#footerPoweredByURL').val()}
        ];
        $.ajax({
            type: "PUT",
            url: "/admin/config",
            contentType: 'application/json',
            data: JSON.stringify(params),
            success: function (result) {
                if (result.code == 200) {
                    swal("保存成功", {
                        icon: "success",
                    });
                }
                else {
                    swal(result.msg, {
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

})
