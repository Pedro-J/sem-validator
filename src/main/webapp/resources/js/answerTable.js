
var Answer = function(answerValue, checklistID, questionID){
    this.value = answerValue;
    this.checklist = checklistID;
    this.question = questionID;
};

// Component Model
var tableModel = (function() {

    var data = {
        content: [], //json objects of current page
        answers: [],
        currentPage: 1,
        pageSize: 10,
        totalElements: 0,
        totalPages: 1,
    };

    return {

        selectAnswer: function(value, checklistID, questionID){
            data.answers.push(new Answer(value, checklistID, questionID));
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
            return data.totalPages;
        },
        setData:function(resultData){
            data.content = resultData.content;
            data.totalElements = resultData.totalElements;
            data.totalPages = resultData.totalPages;
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
        //Search
        checklist: 'id_checklist',

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

    var nodeListForEach = function(list, callback) {
        for (var i = 0; i < list.length; i++) {
            callback(list[i], i);
        }
    };

    var createSelect = function(idSelect, options){
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
            selectHTML.appendChild(optionHTML);
        });
        return selectHTML;
    }


    function createTD(element, colspan){
        var td = document.createElement('td');
        td.setAttribute('colspan', colspan);
        td.appendChild(element);
        return td;
    }

    return {

        getInputsForm: function() {
            return {
                checklist: document.getElementById(DOMstrings.checklist).value,
            };
        },

        updateDisplay: function(questions, answers) {
            var html, newHtml, selectHTML, optionHTML;
            // Create HTML string with placeholder text


            tableContent = document.getElementById(DOMstrings.tableContent);
            tableContent.innerHTML = "";

            console.log(questions);
            questions.forEach(function(question){
                var selectAnswers = createSelect(question.id, answers);

                var colspanQuestion = '3';
                var tdQuestionLabel = createTD(document.createTextNode(question.description), colspanQuestion);

                var colspanAnswer = '1';
                var tdQuestionAnswer = createTD(selectAnswers, colspanAnswer);

                var trQuestion = document.createElement('tr');

                trQuestion.appendChild(tdQuestionLabel);
                trQuestion.appendChild(tdQuestionAnswer);
                tableContent.appendChild(trQuestion);
            });

        },

        updatePagination: function(currentPage, maxPage){
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
        },

        getDOMstrings: function() {
            return DOMstrings;
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

        document.getElementById(DOM.tableContent).addEventListener('change', selectAnswer);

        //Pagination events
        document.querySelector(DOM.pageLeft).addEventListener('click', selectPage);
        document.querySelector(DOM.pageMiddle).addEventListener('click', selectPage);
        document.querySelector(DOM.pageRight).addEventListener('click', selectPage);
        document.querySelector(DOM.pageNext).addEventListener('click', selectNextPage);
        document.querySelector(DOM.pagePrev).addEventListener('click', selectPrevPage);
    };

    //Insert answer in the model
    var selectAnswer = function(event){
        var idPrefix = view.getDOMstrings().selectIdPrefix;

        var checklistID = parseInt(view.getInputsForm().checklist);
        var questionID = parseInt(event.target.id.replace(idPrefix,''));
        var answerValueID = parseInt(event.target.value);

        if( event.target.tagName === 'SELECT' ){
            model.selectAnswer(answerValueID, checklistID, questionID);
        }
    };

    loadAnswersTypes = function(questionsResultData) {
        //1. load data through ajax

        var xhr = new XMLHttpRequest();
        xhr.open('GET', '/answers/types');
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onload = function() {
            if (xhr.status === 200) {
                //2. Update model and UI
                //console.log(xhr.responseText);
                var answersTypes = JSON.parse(xhr.responseText);
                view.updateDisplay(questionsResultData, answersTypes);
                view.updatePagination(model.getCurrentPage(), model.getTotalPages());
            }
        };
        xhr.send();
    };

    loadQuestions = function(){

        var params = '?page='+(model.getCurrentPage()-1) + '&size=' + model.getPageSize();
        var xhr = new XMLHttpRequest();
        xhr.open('GET', '/checklists/' + view.getInputsForm().checklist + '/questions' + params);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onload = function() {
            if (xhr.status === 200) {
                //2. Update model and UI
                console.log(xhr.responseText);
                var resultData = JSON.parse(xhr.responseText);
                model.setData(resultData);
                loadAnswersTypes(model.getContent());
            }
        };
        xhr.send();
    };

    loadData = function(){
        loadQuestions();
    }

    var selectPage = function(event){
        if( !event.target.parentNode.classList.contains('disabled') ) {
            var current = parseInt(event.target.textContent);
            model.setCurrentPage(current);
            view.updatePagination(current, model.getTotalPages());
            loadData();
        }
    };

    var selectPrevPage = function(event){
        if( !event.target.parentNode.classList.contains('disabled')) {
            var current = model.getCurrentPage() - 1;
            model.setCurrentPage(current);
            view.updatePagination(current, model.getTotalPages());
            loadData();
        }
    };

    var selectNextPage = function(event){
        if( !event.target.parentNode.classList.contains('disabled') ) {
            var current = model.getCurrentPage() + 1;
            model.setCurrentPage(current);
            view.updatePagination(current, model.getTotalPages());
            loadData();
        }
    };

    return {
        init: function() {
            console.log('Application has started.');
            loadData();
            setupEventListeners();
        }
    };

})(tableModel, tableView);


controller.init();


var td = document.createElement('td');
console.log('td:' + typeof td);
td.setAttribute('colspan', '1');