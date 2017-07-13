

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
                    optionInit.text('--- '+$('#init_value').val()+' ---')
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
    $.when(getAnswers(), getQuestions()).done(function(answers, questions) {

        createTableQuetionsAnswers(answers[0], questions[0]);

    });
}

function createTableQuetionsAnswers(answers,questions){
    var tBody = $('#answers_questions');
    tBody.html("");
    if( questions.length > 0 ) {
        $.each(questions, function (i, question) {
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

function getAnswers(){
    return $.ajax({
        type: 'GET',
        url: '/answers/types',
        success: function (data) {}
    });
}

function getQuestions(){
    return $.ajax({
        type: 'GET',
        url: '/criterions/' + $('#selectCriterions').val() + '/questions',
        success: function (data) {}
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

function saveAnswers() {
    var selects = $("select.form-control.select-answers");
    var answersJsonData = [];

    var modelId = $('#id_model').val();
    var requirementId = $('#requirementSelect').val();

    $.each(selects, function (i, obj) {
        var questionId = obj.id.replace("select_q_","");
        var answerValue = obj.value;

        var answer = new Object();
        answer.value = answerValue;
        answer.model  = modelId;
        answer.requirement = requirementId;
        answer.question = questionId;

        answersJsonData[i] = answer;
    });

    console.log(JSON.stringify(answersJsonData));

    $.ajax({
        type : "POST",
        url : "/answers",
        contentType : "application/json",
        data : JSON.stringify(answersJsonData),
        //dataType : 'json', //expected from backend
        success : function(data) {
            console.log("SUCCESS: ", data);
            var messager = $("div.alert.alert-success");
            messager.show();
            setTimeout(function() { messager.hide(); }, 10000);

        },
        error : function(error) {
            console.log("ERROR: ", error);
            var messager = $("div.alert.alert-danger");
            messager.show();
            setTimeout(function() { messager.hide(); }, 10000);
        }
    });
}
