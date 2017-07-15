$(function () {
    var host = "http://localhost:8888";

    $("#tabs").tabs();
    $("button").button();
    $(".photos-container img").tooltip();
    $("input[type='radio']").checkboxradio();
    $(".photos-container a").click(function (e) {
        e.preventDefault();
        var self = $(this);
        $.ajax({
            url: host + "/admin/photo/delete/" + +self.attr("alt"),
            type: "GET",
            success: function(data) {
                if (data)
                    self.parent().hide("fade");
            },
            timeout: 30000
        });
    });
    $(".categories-item").click(function (e) {
        e.preventDefault();
        var self = $(this);
        $.ajax({
            url: host + "/admin/category/delete/" + +self.attr("alt"),
            type: "GET",
            success: function(data, st) {
                console.log(st);
                console.log("suss");
                if (data)
                    self.hide("fade");
            },
            error: function (xhr) {
                if (xhr.status == 500)
                    alert("To this category still belongs some photos");
            },
            timeout: 30000
        });
    });
    $(".main-img-container").click(function (e) {
        var self = $(this),
            photoContainer = self.siblings(".backstage-photos");

        if (photoContainer.hasClass("expanded")) {
            photoContainer.slideUp(300);
            photoContainer.removeClass("expanded");
        } else {
            photoContainer.slideDown(300);
            photoContainer.addClass("expanded");
        }
    });
    $(".main-img-container a").click(function (e) {
        e.preventDefault();
        var self = $(this);
        $.ajax({
            url: host + "/admin/backstage/delete/" + +self.attr("alt"),
            type: "GET",
            success: function(data) {
                if (data)
                    self.parent().parent().hide("fade");
            },
            timeout: 30000
        });
    });
});