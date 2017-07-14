
// Model CONTROLLER
var tableModel = (function() {

    var question = function(description, requirement, criterion){
        this.description = description;
        this.requriement = requirement;
        this.criterion = criterion;
    };

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
        getTotalElements: function(){
            return data.totalElements;
        },
        testing: function() {
            console.log(data);
        }
    };

})();




// UI CONTROLLER
var tableView = (function() {

    var DOMstrings = {
        inputCriterion: '.input-cri',
        inputRequirement: '.input-req',
        inputDescription: '.input-des',
        btnSearch: '.btn-search',
        tableContent: 'ss-table-content',
        btnSave:'.btn-save',
        btnUpdate:'.btn-update'
    };


    var nodeListForEach = function(list, callback) {
        for (var i = 0; i < list.length; i++) {
            callback(list[i], i);
        }
    };


    return {
        getInput: function() {
            return {
                type: document.querySelector(DOMstrings.inputType).value, // Will be either inc or exp
                description: document.querySelector(DOMstrings.inputDescription).value,
                value: parseFloat(document.querySelector(DOMstrings.inputValue).value)
            };
        },

        updateDisplay: function(content, selectedIDs) {
            var html, newHtml, se;
            // Create HTML string with placeholder text



            html = '<tr><td><input type="checkbox" value="%id%"></td> <td></td> <td></td> <td></td></tr>';

            // Replace the placeholder text with some actual data
            newHtml = html.replace('%id%', obj.id);
            newHtml = newHtml.replace('%description%', obj.description);
            newHtml = newHtml.replace('%value%', formatNumber(obj.value, type));

            // Insert the HTML into the DOM
            document.querySelector(element).insertAdjacentHTML('beforeend', newHtml);
        },



        getDOMstrings: function() {
            return DOMstrings;
        }
    };

})();




// GLOBAL APP CONTROLLER
var controller = (function(model, view) {

    var setupEventListeners = function() {
        var DOM = model.getDOMstrings();

        document.querySelector(DOM.tableContent).addEventListener('click', clickCheckbox);
        document.querySelector(DOM.btnSearch).addEventListener('click', search);
    };


    var clickCheckbox = function(event){
        if( event.target.type === 'checkbox' ){
            //1. Inserir/remover id no modelo
            if( event.target.checked ) {
                model.selectElement(event.target.value);
            }else{
                model.unselectElement(event.target.value);
            }
        }
    }

    var search = function(){
        //1. update model content
        model.loadData(view.updateDisplay)
        //
    }

    return {
        init: function() {
            console.log('Application has started.');
            model.displayBudget({
                budget: 0,
                totalInc: 0,
                totalExp: 0,
                percentage: -1
            });
            setupEventListeners();
        }
    };

})(tableModel, tableView);


controller.init();