
var Checklist = function(id, title, checklistType){
    this.id = id;
    this.title = title;
    this.checklistType = checklistType;
    this.questions = [];
};

Checklist.prototype.setQuestions = function(data){
    this.questions = data.map(function(current){
        return {id:current};
    });
};

// Component Model
var tableModel = (function() {

    var data = {
        content: [],	  //json objects of current page
        selectedIDs: [], //selected questions ids
        currentPage: 1,
        pageSize: 10,
        totalElements: 0,
        totalPages: 1
    };

    return {

        selectElement: function(id){
            data.selectedIDs.push(id);
        },
        unselectElement: function(id){
            var index = data.selectedIDs.indexOf(id);

            if( index !== -1 ){
                data.selectedIDs.splice(index, 1);
            }
        },
        getContent: function(){
            return data.content;
        },
        getSelectedIDs: function(){
            return data.selectedIDs;
        },
        setSelectedIDs: function(selectedQuestionsIds){
            data.selectedIDs = selectedQuestionsIds;
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
            console.log('Selected IDs: ' + data.selectedIDs);
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
        inputCriterion: '.select-criterion',
        inputRequirement: '.select-requirement',
        inputDescription: '.input-description',
        btnSearch: '.btn-search',

        //Form checklist
        selectedChecklist:'selected-checklist',
        inputChecklistTitle: 'checklist-title',
        selectChecklistType: 'checklist-type',

        messageDesc: '.desc-message',
        messageType: '.type-message',
        messageModel: '.model-message',

        tableContent: '.ss-table-content',
        btnSave:'.btn-save',
        btnUpdate:'.btn-update',

        //Pagination
        pagination: 'ul.pagination',
        pageLeft:'li.page-left',
        pageMiddle:'li.page-middle',
        pageRight:'li.page-right',
        pagePrev:'li.page-prev',
        pageNext:'li.page-next'
    };

    var nodeListForEach = function(list, callback) {
        for (var i = 0; i < list.length; i++) {
            callback(list[i], i);
        }
    };

    return {

        getViewParams: function () {
            return {
                appContext: document.querySelector(DOMstrings.appContext).content
            };
        },

        getInputsSearch: function() {
            return {
                criterion: document.querySelector(DOMstrings.inputCriterion).value,
                description: document.querySelector(DOMstrings.inputDescription).value,
                requirement: document.querySelector(DOMstrings.inputRequirement).value
            };
        },

        getInputsForm: function() {
            return {
                description: document.getElementById(DOMstrings.inputChecklistTitle).value,
                type: document.getElementById(DOMstrings.selectChecklistType).value
            };
        },

        setInputsForm: function(title, checklistType) {
            document.getElementById(DOMstrings.selectChecklistType).value = checklistType;
            document.getElementById(DOMstrings.inputChecklistTitle).value = title;
        },

        getMessagesForm: function() {
            return {
                description: document.querySelector(DOMstrings.messageDesc),
                type: document.querySelector(DOMstrings.messageType)
            };
        },

        getSelectedChecklist: function() {
            return document.getElementById(DOMstrings.selectedChecklist);
        },

        updateDisplay: function(content, selectedIDs) {
            var html, newHtml, tableContent;
            // Create HTML string with placeholder text

            tableContent = document.querySelector(DOMstrings.tableContent);
            tableContent.innerHTML = "";

            content.forEach(function(obj){

                html = '<tr><td><input type="checkbox" value="%id%" %checked%></td> <td>%description%</td> <td>%criterion%</td> <td>%requirement%</td> </tr>';

                // Replace the placeholder text with some actual data
                newHtml = html.replace('%id%', obj.id);

                newHtml = newHtml.replace('%description%', obj.description);
                newHtml = newHtml.replace('%criterion%', obj.criterion.description);
                newHtml = newHtml.replace('%requirement%', obj.requirement.description);


                if( selectedIDs.indexOf(parseInt(obj.id)) !== -1 ){
                    newHtml = newHtml.replace('%checked%', 'checked');
                }else{
                    newHtml = newHtml.replace('%checked%', '');
                }

                newHtml = newHtml.replace('%checked%', obj.description);

                // Insert the HTML into the DOM
                tableContent.insertAdjacentHTML('beforeend', newHtml);

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

                document.querySelector(DOMstrings.pageLeft).firstElementChild.textContent = currentPage;
                document.querySelector(DOMstrings.pageMiddle).firstElementChild.textContent = currentPage + 1;
                document.querySelector(DOMstrings.pageRight).firstElementChild.textContent = currentPage + 2;

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

        //Search execution events
        document.querySelector(DOM.btnSearch).addEventListener('click', search);

        document.addEventListener('keypress', function(event) {

            if (event.keyCode === 13 || event.which === 13) {
                event.preventDefault();
                search();
            }
        });

        //Save button
        document.querySelector(DOM.btnSave).addEventListener('click', save);

        //Table select/unselect elements event
        document.querySelector(DOM.tableContent).addEventListener('click', selectElement);

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

    loadQuestions = function() {
        //1. load data through ajax
        var reqUrl = addContext('questions?page=' + (model.getCurrentPage() - 1) + '&size='+ model.getPageSize());
        var xhr = new XMLHttpRequest();
        xhr.open('GET', reqUrl);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onload = function() {
            if (xhr.status === 200) {
                //2. Update model and UI
                //console.log(xhr.responseText);
                var resultData = JSON.parse(xhr.responseText);
                model.setData(resultData);
                view.updateDisplay(model.getContent(), model.getSelectedIDs());
                view.updatePagination(model.getCurrentPage(), model.getTotalPages());
            }
        };
        xhr.send();
    };

    loadSelectedQuestions = function() {
        if( view.getSelectedChecklist() != null ) {
            var reqUrl = addContext('checklists/' + view.getSelectedChecklist().value);
            var xhr = new XMLHttpRequest();
            xhr.open('GET', reqUrl);
            xhr.setRequestHeader('Content-Type', 'application/json');
            xhr.onload = function () {
                if (xhr.status === 200) {
                    //2. Update model and UI
                    console.log(xhr.responseText);
                    var resultData = JSON.parse(xhr.responseText);
                    model.setSelectedIDs(resultData.questions);
                    view.setInputsForm(resultData.title, resultData.checklistType)
                }
            };
            xhr.send();
        }
    };


    var loadQuestionsByFormInputsAndPage = function(page){
        var inputsSearch = view.getInputsSearch();

        var xhr = new XMLHttpRequest();
        xhr.open('POST', addContext('questions/search'));
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onload = function() {
            if (xhr.status === 200) {
                console.log(xhr.responseText);
                var resultData = JSON.parse(xhr.responseText);
                model.setData(resultData);
                view.updateDisplay(model.getContent(), model.getSelectedIDs());
                view.updatePagination(model.getCurrentPage(), model.getTotalPages());
            }
        };
        var searchData = JSON.stringify({
            page: (page - 1),
            size: model.getPageSize(),
            criterion: inputsSearch.criterion,
            requirement: inputsSearch.requirement,
            questionDescription: inputsSearch.description
        });
        xhr.send(searchData);
    };

    var search = function(){
        model.setCurrentPage(1);
        view.updatePagination(model.getCurrentPage(), model.getTotalPages());
        loadQuestionsByFormInputsAndPage(model.getCurrentPage());
    };

    //Insert/remove id in/from the model
    var selectElement = function(event){
        if( event.target.type === 'checkbox' ){
            if( event.target.checked ) {
                model.selectElement(parseInt(event.target.value));
            }else{
                model.unselectElement(parseInt(event.target.value));
            }
        }
    };

    var selectPage = function(event){
        if( !event.target.parentNode.classList.contains('disabled') ) {
            var current = parseInt(event.target.textContent);
            model.setCurrentPage(current);
            view.updatePagination(current, model.getTotalPages());
            loadQuestionsByFormInputsAndPage(model.getCurrentPage());
        }
    };

    var selectPrevPage = function(event){
        if( !event.target.parentNode.classList.contains('disabled')) {
            var current = model.getCurrentPage() - 1;
            model.setCurrentPage(current);
            view.updatePagination(current, model.getTotalPages());
            loadQuestionsByFormInputsAndPage(model.getCurrentPage());
        }
    };

    var selectNextPage = function(event){
        if( !event.target.parentNode.classList.contains('disabled') ) {
            var current = model.getCurrentPage() + 1;
            model.setCurrentPage(current);
            view.updatePagination(current, model.getTotalPages());
            loadQuestionsByFormInputsAndPage(model.getCurrentPage());
        }
    };

    var save = function(event){
        event.preventDefault();

        if( !isFormValid() ){
            return;
        }

        var inputs = view.getInputsForm();

        var checklist = null;
        if( view.getSelectedChecklist() != null)
            checklist = new Checklist(view.getSelectedChecklist().value, inputs.description, inputs.type);
        else
            checklist = new Checklist(null, inputs.description, inputs.type);

        checklist.setQuestions(model.getSelectedIDs());

        var xhr = new XMLHttpRequest();
        xhr.open('POST', addContext('checklists'));
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onload = function() {
            if (xhr.status === 200) {
                //console.log(xhr.responseText);
                window.location.replace(addContext('checklists/list?success=true'));
            }
        };

        var checklistJSON = JSON.stringify(checklist);

        xhr.send(checklistJSON);

    };

    var isFormValid = function(){
        var inputs = view.getInputsForm();
        var messages = view.getMessagesForm();

        if( inputs.description === '' ){
            messages.description.style.display = 'block';
            messages.description.parentNode.parentNode.classList.add('has-error');
            return false;
        }else{
            messages.description.style.display = 'none';
            messages.description.parentNode.parentNode.classList.remove('has-error');
        }

        if( inputs.type === '' ){
            messages.type.style.display = 'block';
            messages.type.parentNode.parentNode.classList.add('has-error');
            return false;
        }else {
            messages.type.style.display = 'none';
            messages.type.parentNode.parentNode.classList.remove('has-error');
        }

        return true;

    }

    return {
        init: function() {
            console.log('Application has started.');
            loadSelectedQuestions();
            loadQuestions();
            setupEventListeners();
        }
    };

})(tableModel, tableView);


controller.init();