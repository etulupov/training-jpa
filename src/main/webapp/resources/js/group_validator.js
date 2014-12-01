var STRINGS = {};

function init(obj) {
    STRINGS = obj;
}

$(document).ready(function () {
    $('#group-form').bootstrapValidator({
        fields: {
            value: {
                validators: {
                    notEmpty: {
                        message: STRINGS.group_empty
                    },
                    stringLength: {
                        min: 3,
                        max: 30,
                        message: STRINGS.group_invalid_size
                    }
                }
            }
        }
    });
});

