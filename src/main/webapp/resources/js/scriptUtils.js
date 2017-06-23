

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

function loadSelectBySelect(url, currentSelectId, responseContainer){
    $(currentSelectId).change(function(){
        var urlRequest = url.replace("{id}", $(currentSelectId).val());
        console.log('url req:  '+urlRequest);
        $.ajax({
            type:'GET',
            url: urlRequest,
            success: function (data) {
                var select = $('#selectCriterions');
                if( data.length > 0 ) {

                    $.each(data, function (i, optionObj) {
                        var option = $('<option />');
                        option.val(optionObj.value);
                        option.text(optionObj.label);
                        select.append(option);
                    });

                    select.change(showTableAnswers);
                }else{
                    var optionInit = $('<option />');
                    optionInit.text('--- '+$('#initValue').val()+' ---')
                    select.html(optionInit);
                    $('#answers_questions').html("")
                }
            },
            error: function(xhr) {
                MvcUtil.showErrorResponse(xhr.responseText, link);
            }
        });
    });
}

function showTableAnswers(){
    $.when(getAnswers()).done(function(answers) {
        console.log(answers);
        $.ajax({
            type: 'GET',
            url: '/criterions/' + $('#selectCriterions').val() + '/questions',
            success: function (data) {
                var tBody = $('#answers_questions');
                if( data.length > 0 ) {
                    $.each(data, function (i, question) {
                        var idSelect = 'select_q_' + question.id;
                        var selectAnswers = createSelectAnswer(idSelect, answers);

                        var tdQuestionLabel = createTD(question.number + ' - ' + question.description, '3');
                        var tdQuestionAnswer = createTD(selectAnswers, '1');

                        var trQuestion = $('<tr />');

                        trQuestion.append(tdQuestionLabel).append(tdQuestionAnswer);
                        tBody.append(trQuestion);

                    });
                }else{
                    tBody.html("");
                }
            }
        });
    });
}

function getAnswers(answers){
    return $.ajax({
        type: 'GET',
        url: '/answers/types'
    });
}

function createSelectAnswer(idSelect, answers){
    var select = $('<select class="form-control select-answers" />');
    select.attr("id", idSelect);

    $.each(answers, function (i, obj) {
        var option = $('<option />');
        option.val(obj.value);
        option.text(obj.label);
        select.append(option);
    });

    return select;
}

function createTD(value, colspan){
    var tdLabel = $('<td/>');
    tdLabel.attr('colspan', colspan);
    tdLabel.html(value);
    return tdLabel;
}