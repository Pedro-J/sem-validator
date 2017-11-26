
var Answer = function(answerValue, evaluationID, questionID){
    this.value = answerValue;
    this.evaluation = evaluationID;
    this.question = questionID;
};

// Component Model
var tableModel = (function() {

    var data = {
        content: [], //json objects of current page
        answers: [],
        answersType:[],
        currentPage: 1,
        pageSize: 10,
        totalElements: 0
    };

    return {
        getCurrentData: function(){
            var start = (this.getCurrentPage() - 1) * this.getPageSize();
            var end = (this.getCurrentPage() * this.getPageSize()) - 1;

            return data.content.slice(start, end);
        },
        selectAnswer: function(value, evaluationID, questionID){
            var index = this.findAnswerIndexByQuestionID(questionID);

            if( index !== -1 ){
                data.answers.splice(index, 1);
            }

            data.answers.push(new Answer(value, evaluationID, questionID));
        },
        getContent: function(){
            return data.content;
        },
        getAnswers: function(){
            return data.answers;
        },
        setCurrentPage: function(value){
            data.currentPage = value;
        },
        getCurrentPage: function(){
            return data.currentPage;
        },
        getPageSize: function(){
            return data.pageSize;
        },
        getTotalElements: function(){
            return data.totalElements;
        },
        getTotalPages: function(){
            var totalSize = data.content.length;

            if( totalSize === 0){
                return 0;
            }else if( totalSize > 0 && totalSize < this.getPageSize() ){
                return 1;
            }else if( (totalSize % this.getPageSize()) === 0){
                return (totalSize / this.getPageSize());
            }else if( (totalSize % this.getPageSize()) !== 0){
                return (Math.floor((totalSize / this.getPageSize())) + 1);
            }
        },
        setData: function(resultData){
            data.content = resultData;
            data.totalElements = resultData.length;
        },
        getAnswersType: function(){
            return data.answersType;
        },
        setAnswersType: function(answersType){
            data.answersType = answersType;
        },
        findAnswerByQuestionID: function(questionID){
            var wantedAnswer = null;

            for(var i = 0; i < data.answers.length; i++){
                if( data.answers[i].question === questionID){
                    wantedAnswer = data.answers[i];
                    break;
                }
            }

            return wantedAnswer;
        },
        findAnswerIndexByQuestionID: function(questionID){
            for(var i = 0; i < data.answers.length; i++){
                if( data.answers[i].question === questionID){
                    return i;
                }
            }
            return -1;
        },
        print: function() {
            console.log('Content: '+ data.content);
            console.log('Answers IDs: ' + JSON.stringify(data.answers));
            console.log('Current Page: '+ data.currentPage);
            console.log('Total Pages: ' + data.totalPages);
            console.log('Total Elements: ' + data.totalElements);
        }
    };

})();

// Component UI
var tableView = (function() {

    var DOMstrings = {
        appContext: 'meta[name="appContext"]',
        //Search
        evaluation: 'id_evaluation',

        tableContent: 'answers_questions',
        btnSave:'.btn-save',

        //Pagination
        pagination: 'ul.pagination',
        pageLeft:'li.page-left',
        pageMiddle:'li.page-middle',
        pageRight:'li.page-right',
        pagePrev:'li.page-prev',
        pageNext:'li.page-next',
        selectIdPrefix:'select-q-'
    };

    var createSelect = function(idSelect, options, answerChoosed){
        var idSelectPrefix = DOMstrings.selectIdPrefix + idSelect;

        selectHTML = document.createElement('select');
        selectHTML.classList.add('select-answers');
        selectHTML.classList.add('form-control');
        selectHTML.setAttribute("id", idSelectPrefix);

        options.forEach(function(option){
            optionHTML = document.createElement('option');
            var label = document.createTextNode(option.label);
            optionHTML.appendChild(label);
            optionHTML.setAttribute('value', option.value);

            if( answerChoosed !== null && answerChoosed.value === option.value){
                optionHTML.setAttribute('selected', 'selected');
            }

            selectHTML.appendChild(optionHTML);
        });

        return selectHTML;
    };

    var createTipImg = function(tip){
        var img = document.createElement('img');
        img.setAttribute('src', '/resources/images/tip_info.png');
        img.setAttribute('title', tip);
        img.classList.add('tip-info-img');
        return img;
    };

    var createTD = function (element){
        var td = document.createElement('td');
        td.appendChild(element);
        return td;
    };

    var createContainer = function(element){
        var container = document.createElement('div');
        container.classList.add('long-text-column');
        container.appendChild(element);
        return container;
    };

    var updateTable = function(model) {
        var html, newHtml, selectHTML, optionHTML;

        var questions = model.getCurrentData();
        var answers = model.getAnswersType();

        tableContent = document.getElementById(DOMstrings.tableContent);
        tableContent.innerHTML = "";

        questions.forEach(function(question){

            var answerChoosed = model.findAnswerByQuestionID(question.id);

            var selectAnswers = createSelect(question.id, answers, answerChoosed);
            var imgQuestionTip = createTipImg(question.tip);

            //var colspanQuestion = '8';
            var tdQuestionDescription = createTD(document.createTextNode(question.description));
            tdQuestionDescription.classList.add('width400');

            var tdCriterionDescription = createTD(createContainer(document.createTextNode(question.criterion.description)));
            tdCriterionDescription.classList.add('width200', 'text-center');

            var tdQuestionDescription = createTD(document.createTextNode(question.description));
            tdQuestionDescription.classList.add('width100');

            //var colspanTip = '1';
            var tdQuestionTip = createTD(imgQuestionTip);
            tdQuestionTip.classList.add('text-center', 'width100');

            //var colspanAnswer = '1';
            var tdQuestionAnswer = createTD(selectAnswers);
            tdQuestionAnswer.classList.add('text-center', 'width140');

            var trQuestion = document.createElement('tr');

            trQuestion.appendChild(tdQuestionDescription);
            trQuestion.appendChild(tdCriterionDescription);
            trQuestion.appendChild(tdQuestionTip);
            trQuestion.appendChild(tdQuestionAnswer);
            tableContent.appendChild(trQuestion);
        });

    };

    var updatePagination = function(model){

        var currentPage = model.getCurrentPage();
        var maxPage = model.getTotalPages();
        var minPage = 1;

        document.querySelector(DOMstrings.pagination).classList.remove('hide');

        if( maxPage == minPage ){
            document.querySelector(DOMstrings.pagination).classList.add('hide');
        }else if( maxPage == 2){
            document.querySelector(DOMstrings.pageRight).classList.add('disabled');

            if( currentPage == maxPage)
                document.querySelector(DOMstrings.pageNext).classList.add('disabled');
            else
                document.querySelector(DOMstrings.pageNext).classList.remove('disabled');
        }else{
            document.querySelector(DOMstrings.pageRight).classList.remove('disabled');
            document.querySelector(DOMstrings.pageNext).classList.remove('disabled');
        }

        if( currentPage === minPage){
            document.querySelector(DOMstrings.pageLeft).classList.add('active');
            document.querySelector(DOMstrings.pageMiddle).classList.remove('active');
            document.querySelector(DOMstrings.pageRight).classList.remove('active');

            document.querySelector(DOMstrings.pagePrev).classList.add('disabled');

        } else if( currentPage === maxPage && maxPage > 2){
            document.querySelector(DOMstrings.pageLeft).classList.remove('active');
            document.querySelector(DOMstrings.pageMiddle).classList.remove('active');
            document.querySelector(DOMstrings.pageRight).classList.add('active');

            document.querySelector(DOMstrings.pageNext).classList.add('disabled');
            document.querySelector(DOMstrings.pagePrev).classList.remove('disabled');
        }else if( currentPage > 1) {
            document.querySelector(DOMstrings.pageLeft).firstElementChild.textContent = currentPage - 1;
            document.querySelector(DOMstrings.pageMiddle).firstElementChild.textContent = currentPage;
            document.querySelector(DOMstrings.pageRight).firstElementChild.textContent = currentPage + 1;

            document.querySelector(DOMstrings.pageLeft).classList.remove('active');
            document.querySelector(DOMstrings.pageMiddle).classList.add('active');
            document.querySelector(DOMstrings.pageRight).classList.remove('active');

            document.querySelector(DOMstrings.pagePrev).classList.remove('disabled');
        }
    };

    var nodeListForEach = function(list, callback) {
        for (var i = 0; i < list.length; i++) {
            callback(list[i], i);
        }
    };

    return {

        getViewParams: function () {
            return {
                evaluation: document.getElementById(DOMstrings.evaluation).value,
                appContext: document.querySelector(DOMstrings.appContext).content
            };
        },

        getDOMstrings: function () {
            return DOMstrings;
        },

        updateDisplay: function (model) {
            updateTable(model);
            updatePagination(model);
        }

    };

})();


// Component CONTROLLER
var controller = (function(model, view) {

    var setupEventListeners = function() {
        var DOM = view.getDOMstrings();

        document.addEventListener('keypress', function(event) {
            event.preventDefault();
            if (event.keyCode === 13 || event.which === 13) {

            }
        });

        document.querySelector(DOM.btnSave).addEventListener('click', saveAnswers);
        document.getElementById(DOM.tableContent).addEventListener('change', selectAnswer);

        //Pagination events
        document.querySelector(DOM.pageLeft).addEventListener('click', selectPage);
        document.querySelector(DOM.pageMiddle).addEventListener('click', selectPage);
        document.querySelector(DOM.pageRight).addEventListener('click', selectPage);
        document.querySelector(DOM.pageNext).addEventListener('click', selectNextPage);
        document.querySelector(DOM.pagePrev).addEventListener('click', selectPrevPage);
    };

    var addContext = function (url){
        return (view.getViewParams().appContext + url);
    };

    //Insert answer in the model
    var selectAnswer = function(event){
        var idPrefix = view.getDOMstrings().selectIdPrefix;

        var evaluationID = parseInt(view.getViewParams().evaluation);
        var questionID = parseInt(event.target.id.replace(idPrefix,''));
        var answerValueID = parseInt(event.target.value);

        if( event.target.tagName === 'SELECT' ){
            model.selectAnswer(answerValueID, evaluationID, questionID);
        }
    };

    //1. Load answer type data through ajax
    //2. On success -> Update model and view
    loadAnswersTypes = function(view) {

        var xhr = new XMLHttpRequest();
        xhr.open('GET', addContext('answers/types'));
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onload = function() {
            if (xhr.status === 200) {
                var answersTypes = JSON.parse(xhr.responseText);
                model.setAnswersType(answersTypes);
                view.updateDisplay(model);
            }
        };
        xhr.send();
    };

    //1. Load questions data through ajax
    //2. On success -> Update model and load answers
    loadQuestions = function(callback, view){

        var xhr = new XMLHttpRequest();
        xhr.open('GET', addContext('evaluations/' + view.getViewParams().evaluation + '/questions'));
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onload = function() {
            if (xhr.status === 200) {
                var resultData = JSON.parse(xhr.responseText);
                model.setData(resultData);
                callback(view);
            }
        };
        xhr.send();
    };

    loadData = function(){
        loadQuestions(loadAnswersTypes, view);
    }

    var selectPage = function(event){
        updatePage(event, parseInt(event.target.textContent));
    };

    var selectPrevPage = function(event){
        updatePage(event, model.getCurrentPage() - 1);
    };

    var selectNextPage = function(event){
        updatePage(event, model.getCurrentPage() + 1);
    };

    var updatePage = function(event, pageNumber){
        if( !event.target.parentNode.classList.contains('disabled') ) {
            model.setCurrentPage(pageNumber);
            view.updateDisplay(model);
        }
    };

    var saveAnswers = function(event){
        event.preventDefault();

        var answersJSON;

        if( model.getAnswers().length > 0 ) {
            answersJSON = JSON.stringify(model.getAnswers());
        }else{
            answersJSON = JSON.stringify([new Answer(0, view.getViewParams().evaluation, 0)]);
        }

        var xhr = new XMLHttpRequest();
        xhr.open('POST', addContext('answers'));
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onload = function () {
            if (xhr.status === 200) {
                //console.log(xhr.responseText);
                window.location.replace(addContext('evaluations/' + view.getViewParams().evaluation));
            }
        };

        xhr.send(answersJSON);

    };

    return {
        init: function() {
            loadData();
            setupEventListeners();
        }
    };

})(tableModel, tableView);

controller.init();