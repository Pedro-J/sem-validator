
var checklist = function(desc, model, type, questions){
    this.description = desc;
    this.model = model;
    this.type = type;
    this.questions = questions;
};

// Model CONTROLLER
var tableModel = (function() {


    var data = {
        content: [],	  //json objects of current page
        selectedIDs: [], //just ids
        currentPage: 1,
        pageSize: 10,
        totalElements: 0
    };

    return {
        loadData: function(updateDisplay) {
            //1. load data through ajax

            var xhr = new XMLHttpRequest();
            xhr.open('GET', '/questions');
            xhr.setRequestHeader('Content-Type', 'application/json');
            xhr.onload = function() {
                if (xhr.status === 200) {
                    var obj = JSON.parse(xhr.responseText);
                    data.content = obj.content;
                    data.totalElements = obj.totalElements;
                    data.pageSize = obj.numberOfElements;
                    updateDisplay(data.content, data.selectedIDs);
                }
            };
            xhr.send();
        },

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
            data.selectedIDs;
        },
        setCurrentPage: function(value){
            data.currentPage = value;
        },
        getCurrentPage: function(){
            return data.currentPage;
        },
        getTotalElements: function(){
            return data.totalElements;
        },
        getTotalPages: function(){
            var totalPages = 0;
            if( data.totalElements > 0) {
                if( data.totalElements < data.pageSize ){
                    return 1;
                } else if (data.totalElements % data.pageSize === 0) {
                    totalPages = data.totalElements / data.pageSize;
                } else {
                    totalPages = Math.floor(data.totalElements / data.pageSize) + 1;
                }
            }
            return totalPages;
        },
        testing: function() {
            console.log(data);
        }
    };

})();




// UI CONTROLLER
var tableView = (function() {

    var DOMstrings = {
        //Search
        inputCriterion: '.select-criterion',
        inputRequirement: '.select-requirement',
        inputDescription: '.input-description',
        btnSearch: '.btn-search',

        //Form checklist
        inputChecklistDesc: 'checklist-desc',
        selectChecklistModel:'checklist-model',
        selectChecklistType: 'checklist-type',


        tableContent: '.ss-table-content',
        btnSave:'.btn-save',
        btnUpdate:'.btn-update',

        //Pagination
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
        getInputSearch: function() {
            return {
                criterion: document.querySelector(DOMstrings.inputCriterion).value,
                description: document.querySelector(DOMstrings.inputDescription).value,
                requirement: document.querySelector(DOMstrings.inputRequirement).value
            };
        },

        getInputForm: function() {
            return {
                description: document.getElementById(DOMstrings.inputChecklistDesc).value,
                type: document.getElementById(DOMstrings.selectChecklistType).value,
                model: document.getElementById(DOMstrings.selectChecklistModel).value
            };
        },

        updateDisplay: function(content, selectedIDs) {
        /*    var html, newHtml, se, tableContent;
            // Create HTML string with placeholder text



            html = '<tr><td><input type="checkbox" value="%id%"></td> <td></td> <td></td> <td></td></tr>';

            // Replace the placeholder text with some actual data
            newHtml = html.replace('%id%', obj.id);
            newHtml = newHtml.replace('%description%', obj.description);
            newHtml = newHtml.replace('%value%', formatNumber(obj.value, type));

            // Insert the HTML into the DOM
            document.querySelector(element).insertAdjacentHTML('beforeend', newHtml);*/
        },

        updatePagination: function(data){
            var currentPage = data.currentPage;
            var maxPage = data.getTotalPages();

            if( currentPage === 1){
                document.querySelector(DOMstrings.pageLeft).classList.add('active');
                document.querySelector(DOMstrings.pageMiddle).classList.remove('active');
                document.querySelector(DOMstrings.pageRight).classList.remove('active');

                document.querySelector(DOMstrings.pagePrev).classList.add('disabled');

            }else if( currentPage === maxPage ){
                document.querySelector(DOMstrings.pageLeft).classList.remove('active');
                document.querySelector(DOMstrings.pageMiddle).classList.remove('active');
                document.querySelector(DOMstrings.pageRight).classList.add('active');

                document.querySelector(DOMstrings.pageNext).classList.add('disabled');
            } else if( currentPage > 1) {
                document.querySelector(DOMstrings.pageLeft).firstElementChild.textContent = currentPage - 1;
                document.querySelector(DOMstrings.pageMiddle).firstElementChild.textContent = currentPage;
                document.querySelector(DOMstrings.pageRight).firstElementChild.textContent = currentPage + 1;

                document.querySelector(DOMstrings.pagePrev).classList.remove('disabled');

                document.querySelector(DOMstrings.pageLeft).classList.remove('active');
                document.querySelector(DOMstrings.pageMiddle).classList.add('active');
                document.querySelector(DOMstrings.pageRight).classList.remove('active');
            }

        },

        getDOMstrings: function() {
            return DOMstrings;
        }
    };

})();




// GLOBAL APP CONTROLLER
var controller = (function(model, view) {


    var setupEventListeners = function() {
        var DOM = view.getDOMstrings();

        document.querySelector(DOM.tableContent).addEventListener('click', selectElement);
        document.querySelector(DOM.btnSearch).addEventListener('click', search);

        //Pagination events
        document.querySelector(DOM.pageLeft).addEventListener('click', selectPage);
        document.querySelector(DOM.pageMiddle).addEventListener('click', selectPage);
        document.querySelector(DOM.pageRight).addEventListener('click', selectPage);
        document.querySelector(DOM.pageNext).addEventListener('click', selectNextPage);
        document.querySelector(DOM.pagePrev).addEventListener('click', selectPrevPage);
    };


    var selectElement = function(event){
        if( event.target.type === 'checkbox' ){
            //1. Inserir/remover id no modelo
            if( event.target.checked ) {
                model.selectElement(event.target.value);
            }else{
                model.unselectElement(event.target.value);
            }
        }
    };

    var search = function(){
        //1. Update model and UI content
        updateDataAndUI();
    };

    var selectPage = function(event){
        var current = parseInt(event.target.textContent);
        model.setCurrentPage(current);
        view.updatePagination(model.data);

    };

    var selectPrevPage = function(){
        if( model.getCurrentPage() > 1 ) {
            var current = model.getCurrentPage() - 1;
            model.setCurrentPage(current);
            view.updatePagination(model.data);
            updateDataAndUI();
        }
    };

    var selectNextPage = function(){
        if( model.getCurrentPage() < model.getTotalPages() ) {
            var current = model.getCurrentPage() + 1;
            model.setCurrentPage(current);
            view.updatePagination(model.data);
            updateDataAndUI();
        }
    };


    var updateDataAndUI = function(){
        //model.loadData(view.updateDisplay);
    };

    return {
        init: function() {
            console.log('Application has started.');
/*            model.displayBudget({
                budget: 0,
                totalInc: 0,
                totalExp: 0,
                percentage: -1
            });*/
            setupEventListeners();
        }
    };

})(tableModel, tableView);


controller.init();