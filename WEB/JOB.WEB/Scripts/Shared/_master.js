//CONFIGURAÇÕES GERAIS

$(document).ready(function () {
    ConfigurarTemaIcheck();

    if (!Modernizr.inputtypes.date) {
        $('input[type=date]').each(function () {
            $(this).datepicker({
                format: "dd/mm/yyyy",
                autoclose: true,
                language: 'pt-BR',
                orientation: 'bottom',
            }
           );

            if ($(this).attr('Max')) {
                $(this).datepicker('setEndDate', moment($(this).attr('Max')).format('DD/MM/YYYY'));
            }
            if ($(this).attr('Min')) {
                $(this).datepicker('setStartDate', moment($(this).attr('Min')).format('DD/MM/YYYY'));
            }
            if ($(this).val()) {
                $(this).datepicker('update', moment($(this).val()).format('DD/MM/YYYY'));
            }
        });

        $('input[type=date]').on('update', function () {
            $(this).datepicker('update', moment($(this).val()).format('DD/MM/YYYY'));
        });
    }

    if (!Modernizr.inputtypes.time) {
        $('input[type=time]').each(function () {
            $(this).timepicker();
        });
    }
});

function ConfigurarTemaIcheck() {
    if (jQuery().iCheck) {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue'
        });
    }
}

function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

if (jQuery().dataTable) {
    //CONFIGURAÇÕES DO DATABLES
    $.fn.dataTable.ext.errMode = 'throw'; //desabilita o aparecimento de alert no browser
}

function FormatarData(data) {
    if (data)
        return moment(data).format('DD/MM/YYYY HH:mm');
    else
        return "";
}