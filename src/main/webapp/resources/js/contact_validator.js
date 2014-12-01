var STRINGS = {};

function init(obj) {
    STRINGS = obj;
}

$(document).ready(function () {
    $("#checkbox-list input[type=\"checkbox\"]").attr('name', 'groups[]');
    $("#container-addresses input").attr('name', 'addresses[]');


    $("#add-form").submit(function (e) {
        $("#checkbox-list input[type=\"checkbox\"]").attr('name', 'groups');
        $("#container-addresses input").attr('name', 'addresses');
        $(".field-row select").parent().parent().find("input").attr('name', "fields");
    });

    var fields = $(".field-row select");
    for (var i = 0; i < fields.length; i++) {
        var item = fields[i];
        var input = $(item).parent().parent().find("input");
        input.attr('name', FIELD_TYPES[$(item).val()]);
    }


    $(".field-row select").on('change', function () {
        var input = $(this).parent().parent().find("input");

        $('#add-form').bootstrapValidator('removeField', input);
        input.removeAttr('data-bv-field');
        input.attr('name', FIELD_TYPES[$(this).val()]);

        $(this).parent().parent().find(".help-block").remove();
        $(this).parent().parent().removeClass("has-error has-success");

        $('#add-form').bootstrapValidator('addField', input);
    });


    $('#add-form').bootstrapValidator({
        fields: {
            firstname: {
                validators: {
                    notEmpty: {
                        message: STRINGS.firstname_invalid_size
                    },
                    stringLength: {
                        min: 3,
                        max: 30,
                        message: STRINGS.firstname_invalid_size
                    }
                }
            },
            lastname: {
                validators: {
                    notEmpty: {
                        message: STRINGS.firstname_invalid_size
                    },
                    stringLength: {
                        min: 3,
                        max: 30,
                        message: STRINGS.firstname_invalid_size
                    }
                }
            },

            'groups[]': {
                validators: {
                    choice: {
                        min: 1,
                        message: 'Please choose group'
                    }
                }
            },

            'addresses[]': {
                validators: {
                    notEmpty: {
                        message: STRINGS.address_empty
                    },
                    stringLength: {
                        min: 3,
                        max: 30,
                        message: STRINGS.address_invalid_size
                    }
                }
            },

            'emails[]': {
                validators: {
                    emailAddress: {
                        message: STRINGS.email_invalid
                    },
                    notEmpty: {
                        message: STRINGS.email_empty
                    }
                }
            },


            'texts[]': {
                validators: {
                    notEmpty: {
                        message: STRINGS.text_empty
                    },
                    stringLength: {
                        min: 3,
                        max: 30,
                        message: STRINGS.text_invalid_size
                    }
                }
            },

            'dates[]': {
                validators: {
                    notEmpty: {
                        message: STRINGS.date_empty
                    },
                    date: {
                        format: 'DD/MM/YYYY',
                        message: STRINGS.date_invalid
                    }
                }
            },

            'links[]': {
                validators: {
                    notEmpty: {
                        message: STRINGS.link_empty
                    },
                    uri: {
                        message: STRINGS.link_invalid
                    }
                }
            },


            'numbers[]': {
                validators: {
                    notEmpty: {
                        message: STRINGS.number_empty
                    },
                    integer: {
                        message: STRINGS.number_invalid
                    }
                }
            },


            photo: {
                validators: {
                    file: {
                        extension: 'jpeg,jpg',
                        type: 'image/jpeg',
                        maxSize: 1024 * 1024,
                        message: STRINGS.file_invalid
                    }
                }
            }
        }
    });
});

