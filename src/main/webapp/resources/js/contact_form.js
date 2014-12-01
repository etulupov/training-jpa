FIELD_TYPES = {
    'DATE': 'dates[]',
    'EMAIL': 'emails[]',
    'LINK': 'links[]',
    'NUMBER': 'numbers[]',
    'TEXT': 'texts[]'
}


$(document).ready(function () {
    $("#add_address").click(function (e) {
            addNewItem("#container-addresses", "#address-template", 'input', 'addresses[]');

        }
    );

    $("#add_field").click(function (e) {
            addNewItem("#container-fields", "#field-template", 'input', 'dates[]');
        }
    );
});


function addNewItem(container, source, tag, name) {
    var content = $(source)
        .clone()
        .removeClass('hide')
        .removeAttr('id');
    var element = $(content).hide();

    $(container).append(element);

    var input = element.find(tag);
    input.attr('name', name);
    $('#add-form').bootstrapValidator('addField', input);

    element.fadeIn('slow');
}


