
function getCurrentLanguage (){
    var lang = $('meta[name="cur_lang"]').attr('content');

    var translateObj = null;

    if( lang === 'pt'){
        translateObj = {
            language: {
                processing:     "Processando ...",
                search:         "Pesquisar&nbsp;:",
                lengthMenu:     "Mostrar &nbsp _MENU_ &nbsp registros",
                info:           "_START_ - _END_ de _TOTAL_ registros",
                loadingRecords: "Carregando...",
                zeroRecords:    "Nenhum registro encontrado",
                emptyTable:     "Listagem sem registros",
                paginate: {
                    first:      "Início",
                    previous:   "Anterior",
                    next:       "Próximo",
                    last:       "Fim"
                }}
        };
    }else if( lang === 'en_US'){
        translateObj = {
            language: {
                language: {
                    processing:     "Processing ...",
                    search:         "Search&nbsp;:",
                    lengthMenu:     "Show _MENU_ entries",
                    info:           "_START_ - _END_ of _TOTAL_",
                    loadingRecords: "Loading...",
                    zeroRecords:    "No records were founds",
                    emptyTable:     "Empty list",
                    paginate: {
                        first:      "First",
                        previous:   "Previous",
                        next:       "Next",
                        last:       "Last"
                    }}
            }
        };
    }
    return translateObj;
}
