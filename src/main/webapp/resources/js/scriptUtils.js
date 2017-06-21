$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

function reqAjax(url,method) {
    $.ajax({
        url: url, //"./delete/car"
        type: method,
        success: function (response) {
            alert(response);
        }
    });
}

function post(path, params, method) {
    method = method || "post";

    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);

    for ( var key in params) {
        if (params.hasOwnProperty(key)) {
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", key);
            hiddenField.setAttribute("value", params[key]);

            form.appendChild(hiddenField);
        }
    }

    document.body.appendChild(form);
    form.submit();
}


function ajaxGetJson(linkElementId) {
    $("#"+linkElementId).click(function () {
        var link = $(this);
        $.ajax({
            url: this.href,
            beforeSend: function (req) {
                if (!this.url.match(/\.json$/)) {
                    req.setRequestHeader("Accept", "application/json");
                }
            },
            success: function (json) {
                MvcUtil.showSuccessResponse(JSON.stringify(json), link);
            },
            error: function (xhr) {
                MvcUtil.showErrorResponse(xhr.responseText, link);
            }
        });
        return false;
    });
}

function loadSelectBySelect(url, currentSelect, selectToBeLoaded){
    currentSelect.change(function(){
        $.ajax({
            url: url,
            beforeSend: function (req) {
                if (!this.url.match(/\.json$/)) {
                    req.setRequestHeader("Accept", "application/json");
                }
            },
            success: function (json) {
                console.log(json);
            },
            error: function (xhr) {
                MvcUtil.showErrorResponse(xhr.responseText, "msg_error_");
            }
        });
        return false;
    });
}